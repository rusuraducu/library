package Biblioteca.Queries;

import Biblioteca.DAO.Book.Book;
import Biblioteca.Actions.BookProcessor;
import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.RetireItem.RetireReason;
import Biblioteca.DAO.RetireItem.RetiredItemsFilter;
import Biblioteca.DAO.RetireItem.RetiredItem;
import Biblioteca.DAO.Type.Type;
import Biblioteca.Actions.TypeProcessor;
import Biblioteca.DAO.User.User;
import Biblioteca.Actions.UserProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetireQuery extends Query {

    private static String SELECT_RETIRED_ITEM_QUERY = "SELECT * FROM retired_items";

    private static String RETIRE_A_BOOK_QUERY = "INSERT INTO retired_items(idItem, ItemType, RetiredBy, Reason, RetireDate) VALUES(?,?,?,?,?)";

    private static String SET_STATUS_AS_RETIRED_QUERY = "UPDATE books SET Status = ? WHERE idBook = ?";

    public int retireBookFromLibrary(Book book, User user, RetireReason retireReason) {

        int rowsAffected = 0;
        Connection connection = null;  PreparedStatement retireBook = null; PreparedStatement updateStatus = null;

        try
        {
            connection = ConnectionPool.getConnection();   connection.setAutoCommit(false);
            retireBook = connection.prepareStatement(RETIRE_A_BOOK_QUERY);
            updateStatus = connection.prepareStatement(SET_STATUS_AS_RETIRED_QUERY);

            retireBook.setInt(1, book.getIdItem());
            retireBook.setInt(2, book.getItemType().getId());
            retireBook.setInt(3, user.getId());
            retireBook.setString(4, retireReason.name());
            retireBook.setDate(5, currentDate.getCurrentDate());

            updateStatus.setString(1, Status.RETIRED.name());
            updateStatus.setInt(2, book.getIdItem());

            rowsAffected = retireBook.executeUpdate() + updateStatus.executeUpdate();

            connection.commit();

        }
        catch (SQLException e) { e.printStackTrace();

             try { if(connection != null){ connection.rollback();} }

             catch (SQLException e1) { e1.printStackTrace(); }
        }

        finally {

            try {
                if (retireBook != null) {
                    retireBook.close();
                }
                if (retireBook != null) {
                    updateStatus.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }

            catch (SQLException e) { e.printStackTrace(); } }

            return rowsAffected;
    }

    public List<RetiredItem> getRetiredBooksHistory(RetiredItemsFilter retiredFilter) {

        List<RetiredItem> retiredItems = new ArrayList<>();

        ResultSet resultSet = null;

        try ( Connection connection = ConnectionPool.getConnection();
              Statement stmt = connection.createStatement()) {

            resultSet = stmt.executeQuery(getRetiredItemsQuery(retiredFilter));

            while (resultSet.next()) {

                Book book = BookProcessor.loadBookInstance(resultSet.getInt("idItem"));
                Type type = TypeProcessor.loadTypeInstance(resultSet.getInt("ItemType"));
                User user = UserProcessor.loadUserInstance(resultSet.getInt("RetiredBy"));
                Date date = resultSet.getDate("RetireDate");
                RetireReason retireReason = RetireReason.valueOf(resultSet.getString("Reason"));

                RetiredItem retiredItem = new RetiredItem(book, type, user, retireReason);

                retiredItems.add(retiredItem);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }

        finally { closeResultSet(resultSet);}

        return retiredItems;
    }

    private String getRetiredItemsQuery(RetiredItemsFilter retiredFilter) {


        StringBuilder loadItemQuery = new StringBuilder(SELECT_RETIRED_ITEM_QUERY);

        loadItemQuery.append(innerJoinDependenciesQuery(retiredFilter));

        loadItemQuery.append(conditionsQuery(retiredFilter));

        return loadItemQuery.toString();

    }


    private String innerJoinDependenciesQuery(RetiredItemsFilter retiredFilter) {

        StringBuilder innerJoinDependencies = new StringBuilder();

        if (isSet(retiredFilter.getType())) {

            innerJoinDependencies.append(" INNER JOIN item_type ON retired_items.ItemType = item_type.idType ");

        }
        return innerJoinDependencies.toString();
    }

    private String conditionsQuery(RetiredItemsFilter retiredFilter) {

        StringBuilder conditions = new StringBuilder();
        conditions.append(" WHERE ");
        if (isSet(retiredFilter.getType())) {
            conditions.append(" idType = " + retiredFilter.getType().getId() + " AND ");
        }
        if (isSet(retiredFilter.getRetireReason())) {
            conditions.append(" Reason = '" + retiredFilter.getRetireReason().name() + "' AND ");
        }
        conditions.append(" idRetiredItem > 0 ");

        conditions.append(rowLimit(retiredFilter.getRowLimit()));

        return conditions.toString();
    }
}
