package Biblioteca.Queries;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.Actions.BookProcessor;
import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.LendItem.LendItem;
import Biblioteca.DAO.LendItem.LendItemFilter;
import Biblioteca.Actions.OverdueItem;
import Biblioteca.DAO.User.User;
import Biblioteca.Actions.UserProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LendingQueries extends Query {

    private static String LEND_A_BOOK_QUERY = " INSERT INTO lent_items(idItem, lending_date, toUser, " +
                                              " byLibrarian, ItemType) VALUES(?,?,?,?,?)";

    private static String RETURN_BOOK_QUERY = " UPDATE lent_items SET restitution_date=? WHERE idItem = ? AND ItemType=?";

    private static String SELECT_LENT_ITEMS_QUERY = "SELECT * FROM lent_items ";

    private static String LENT_ITEMS_THAT_ARE_OVERDUE_QUERY = "SELECT ItemType, idItem, toUser, lending_Date, ADDDATE(lending_date," +
            " INTERVAL lending_period DAY) AS ReturnDate, datediff(CURDATE(),lending_date) AS Overdue FROM library.lent_items " +
            " WHERE datediff(CURDATE(),lending_date)>lending_period AND restitution_date IS NULL LIMIT ?";

    private static String UPDATE_BOOK_STATUS = "UPDATE books SET Status = ? WHERE idBook = ?";

    public List<OverdueItem> getBooksItemsWhichAreOverdue(int rowLimit){

        List<OverdueItem> overdueItemsList = new ArrayList<>();

        ResultSet resultSet = null;

        try( Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LENT_ITEMS_THAT_ARE_OVERDUE_QUERY)) {

            preparedStatement.setInt(1, rowLimit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Book book = BookProcessor.loadBookInstance(resultSet.getInt("idItem"));
                User toUser = UserProcessor.loadUserInstance(resultSet.getInt("toUser"));
                Date lendingDate = resultSet.getDate("lending_date");
                Date returnDate = resultSet.getDate("ReturnDate");
                int overdue = resultSet.getInt("Overdue");

                overdueItemsList.add(new OverdueItem(book, toUser, lendingDate, returnDate, overdue));
            }

        }
        catch (SQLException e){ e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return overdueItemsList;
    }

    public int lendBookFromDatabase(Book book, User toStudent, User byLibrarian){

        int rowsAffected = 0;

        Connection connection = null;  PreparedStatement lendBook = null; PreparedStatement updateStatus = null;

        try
        {
            connection = ConnectionPool.getConnection();   connection.setAutoCommit(false);
            lendBook = connection.prepareStatement(LEND_A_BOOK_QUERY);
            updateStatus = connection.prepareStatement(UPDATE_BOOK_STATUS);

            lendBook.setInt    (1, book.getIdItem(             ));
            lendBook.setDate   (2, currentDate.getCurrentDate( ));
            lendBook.setInt    (3, toStudent.getId(            ));
            lendBook.setInt    (4, byLibrarian.getId(          ));
            lendBook.setInt    (5, book.getItemType().getId(   ));

            updateStatus.setString(1, Status.LENT.name());
            updateStatus.setInt(2, book.getIdItem());

            rowsAffected = lendBook.executeUpdate() + updateStatus.executeUpdate();

            connection.commit();

        }
        catch (SQLException e) { e.printStackTrace();

            try { if(connection != null){ connection.rollback();} }

            catch (SQLException e1) { e1.printStackTrace(); }
        }

        finally {

            try {
                if (lendBook != null) {
                    lendBook.close();
                }
                if (lendBook != null) {
                    updateStatus.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }

            catch (SQLException e) { e.printStackTrace(); } }

        return rowsAffected;

    }

    public int returnBookToLibrary(Book book){

        int rowsAffected = 0;

        Connection connection = null;  PreparedStatement returnBook = null; PreparedStatement updateStatus = null;

        try
        {
            connection = ConnectionPool.getConnection();   connection.setAutoCommit(false);
            returnBook = connection.prepareStatement(RETURN_BOOK_QUERY);
            updateStatus = connection.prepareStatement(UPDATE_BOOK_STATUS);

            returnBook.setDate(1, currentDate.getCurrentDate());
            returnBook.setInt (2, book.getIdItem());
            returnBook.setInt (3, book.getItemType().getId());

            updateStatus.setString(1, Status.AVAILABLE.name());
            updateStatus.setInt(2, book.getIdItem());

            rowsAffected = returnBook.executeUpdate() + updateStatus.executeUpdate();

            connection.commit();

        }
        catch (SQLException e) { e.printStackTrace();

            try { if(connection != null){ connection.rollback();} }

            catch (SQLException e1) { e1.printStackTrace(); }
        }

        finally {

            try {
                if (returnBook != null) {
                    returnBook.close();
                }
                if (returnBook != null) {
                    updateStatus.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }

            catch (SQLException e) { e.printStackTrace(); } }

        return rowsAffected;

    }

    public List<LendItem> getBooksThatAreCurrentlyLent(){

        List<LendItem> currentlyLentItemsList = new ArrayList<>();

        LendItemFilter lendItemFilter = new LendItemFilter(true, 100);

        ResultSet resultSet = null;

        try( Connection connection = ConnectionPool.getConnection();
             Statement stmt = connection.createStatement()){

           resultSet = stmt.executeQuery(getLentBookByFilter(lendItemFilter));

            while(resultSet.next()){

                int idLending = resultSet.getInt("idLending");
                int idBook = resultSet.getInt("idItem");
                Book book = new BookProcessor().loadBookFromDatabaseUsingFilter(new BookFilter(idBook));
                Date lendingDate = resultSet.getDate("lending_date");
                int lending_period = resultSet.getInt("lending_period");
                User toUser = UserProcessor.loadUserInstance(resultSet.getInt("toUser"));
                User byLibrarian = UserProcessor.loadUserInstance(resultSet.getInt("byLibrarian"));

                 currentlyLentItemsList.add(new LendItem(idLending, book, lendingDate,lending_period, toUser, byLibrarian));
            }
        }
        catch (SQLException e){ e.printStackTrace(); }

        finally {  closeResultSet(resultSet);  }

        return currentlyLentItemsList;
    }

    private String getLentBookByFilter(LendItemFilter lendItemFilter){

        StringBuilder getLentBooksQuery = new StringBuilder();

        getLentBooksQuery.append(SELECT_LENT_ITEMS_QUERY);

        getLentBooksQuery.append(getLentBooksConditionsQuery(lendItemFilter));

        return getLentBooksQuery.toString();
    }

    private String getLentBooksConditionsQuery(LendItemFilter lendItemFilter){

        StringBuilder lentBookQueryConditions = new StringBuilder();

        lentBookQueryConditions.append("WHERE ");
        if (isSet(lendItemFilter.getItem())) {
            lentBookQueryConditions.append("idItem = " + lendItemFilter.getItem().getIdItem() + " AND ");
        }
        if (isSet(lendItemFilter.getLendingDate())) {
            lentBookQueryConditions.append("lending_date = " + lendItemFilter.getLendingDate() + " AND ");
        }
        if (isSet(lendItemFilter.getToUser())) {
            lentBookQueryConditions.append("toUser = " + lendItemFilter.getToUser().getId() + " AND ");
        }
        if (isSet(lendItemFilter.getByLibrarian())) {
            lentBookQueryConditions.append("byLibrarian = " + lendItemFilter.getByLibrarian().getId() + " AND ");
        }
        if (isSet(lendItemFilter.isLent() && lendItemFilter.isLent() == true)) {
            lentBookQueryConditions.append("restitution_date IS NULL AND ");
        }
        lentBookQueryConditions.append("idItem > 0");
        lentBookQueryConditions.append(rowLimit(lendItemFilter.getRowLimit()));

        return lentBookQueryConditions.toString();
    }

}
