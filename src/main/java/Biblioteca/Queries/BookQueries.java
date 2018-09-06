package Biblioteca.Queries;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.DAO.Category.Category;
import Biblioteca.Actions.CategoryProcessor;
import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.Type.Type;
import Biblioteca.Actions.TypeProcessor;
import Biblioteca.DAO.User.User;
import Biblioteca.Actions.UserProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookQueries extends Query {

    private static String SELECT_BOOK_QUERY = "SELECT * FROM books ";

    private static String UPDATE_BOOK_QUERY = "UPDATE books SET BookName=?, Author=?, Description=?, NoPages=?, VolumeNumber=?," +
            " idCategory=?, AddedBy=?, ItemType=?, Status=?, LastUpdateDate = ?, LastUpdateBy=? WHERE idBook=?";

    private static String INSERT_NEW_BOOK_QUERY = "INSERT INTO books(BookName, Author, Description, NoPages, VolumeNumber, Date, idCategory, AddedBy, ItemType) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    private static String UPDATE_BOOK_STATUS = "UPDATE books SET Status = ? WHERE idBook = ?";

    public int insertNewBookToDatabase(Book book, User user) {

        int rowsAffected = 0;

        try( Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BOOK_QUERY))  {

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setInt   (4, book.getNumberOfPages());
            preparedStatement.setInt   (5, book.getNumberOfVolume());
            preparedStatement.setDate  (6, currentDate.getCurrentDate());
            preparedStatement.setInt   (7, book.getCategory().getId());
            preparedStatement.setInt   (8, user.getId());

            preparedStatement.setInt(9, TypeProcessor.loadTypeInstance("Book").getId());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int updateBookFromDatabase(Book book, User user) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_QUERY))  {

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setInt(4, book.getNumberOfPages());
            preparedStatement.setInt(5, book.getNumberOfVolume());
            preparedStatement.setInt(6, book.getCategory().getId());
            preparedStatement.setInt(7, book.getAddedBy().getId());
            preparedStatement.setInt(8, book.getItemType().getId());
            preparedStatement.setString(9, book.getStatus().name());
            preparedStatement.setDate(10, currentDate.getCurrentDate());
            preparedStatement.setInt(11, user.getId());
            preparedStatement.setInt(12, book.getIdItem());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int changeBookStatusFromDatabase(Book book, Status status) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_STATUS)) {

            book.setStatus(status);
            preparedStatement.setString(1, book.getStatus().name());
            preparedStatement.setInt(2, book.getIdItem());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }


    public List<Book> getBooksFromDatabaseByFilter(BookFilter bookFilter) {

        String queryBookFilter = getBookFromDatabaseByFilterQUERY(bookFilter);

        List<Book> booksList = new ArrayList<>();

        try ( Connection connection = ConnectionPool.getConnection();
              Statement stmt = connection.createStatement();
              ResultSet resultSet = stmt.executeQuery(queryBookFilter)) {

            while (resultSet.next()) {
                String bookName = resultSet.getString("BookName");
                String author = resultSet.getString("Author");
                String description = resultSet.getString("Description");
                int noPages = resultSet.getInt("NoPages");
                int volumeNumber = resultSet.getInt("VolumeNumber");
                Date date = resultSet.getDate("Date");
                Category category = CategoryProcessor.loadCategoryInstance(resultSet.getInt("idCategory"));
                User addedBy = UserProcessor.loadUserInstance(resultSet.getInt("AddedBy"));
                Type itemType = TypeProcessor.loadTypeInstance(resultSet.getInt("ItemType"));
                Status status = Status.valueOf(resultSet.getString("Status"));

                Book book = new Book(bookName, author, description, category, date, addedBy, itemType, status, volumeNumber, noPages);

                booksList.add(book);
            }

        }
        catch (SQLException e) { e.printStackTrace(); }
        return booksList;
    }

    public Book loadBookFromDatabaseByFilter(BookFilter bookFilter) {

        String queryBookFilter = getBookFromDatabaseByFilterQUERY(bookFilter);

        try ( Connection connection = ConnectionPool.getConnection();
              Statement stmt = connection.createStatement();
              ResultSet resultSet = stmt.executeQuery(queryBookFilter)) {

            if (resultSet.next()) {

                int idBook = resultSet.getInt("idBook");
                String bookName = resultSet.getString("BookName");
                String author = resultSet.getString("Author");
                String description = resultSet.getString("Description");
                int noPages = resultSet.getInt("NoPages");
                int volumeNumber = resultSet.getInt("VolumeNumber");
                Date date = resultSet.getDate("Date");
                Category category = CategoryProcessor.loadCategoryInstance(resultSet.getInt("idCategory"));
                User addedBy = UserProcessor.loadUserInstance(resultSet.getInt("AddedBy"));
                Type itemType = TypeProcessor.loadTypeInstance(resultSet.getInt("ItemType"));
                Status status = Status.valueOf(resultSet.getString("Status"));

                return new Book(idBook, bookName, author, description, category, date, addedBy, itemType, status, volumeNumber, noPages);
            }

        }
        catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    private String getBookFromDatabaseByFilterQUERY(BookFilter bookFilter) {

        StringBuilder bookQuery = new StringBuilder();

        bookQuery.append(SELECT_BOOK_QUERY);

        bookQuery.append(getBookInnerJoinDependenciesQuery(bookFilter));
        bookQuery.append(getBookQueryConditionsQuery(bookFilter));

        return bookQuery.toString();
    }

    private String getBookInnerJoinDependenciesQuery(BookFilter bookFilter) {

        StringBuilder innerJoinTables = new StringBuilder();

        if (bookFilter.getCategory() != null) {

            innerJoinTables.append(" INNER JOIN  categories ON books.idCategory = categories.idCategory ");

        }
        if (bookFilter.getAddedBy() != null) {

            innerJoinTables.append(" INNER JOIN users ON books.AddedBy=users.idUsername ");

        }
        return innerJoinTables.toString();
    }

    private String getBookQueryConditionsQuery(BookFilter bookFilter) {

        StringBuilder bookQueryConditions = new StringBuilder();

        bookQueryConditions.append("WHERE ");
        if (isSet(bookFilter.getIdItem())) {
            bookQueryConditions.append("idBook = " + bookFilter.getIdItem() + " AND ");
        }
        if (isSet(bookFilter.getName())) {
            bookQueryConditions.append("BookName = '" + bookFilter.getName() + "' AND ");
        }
        if (isSet(bookFilter.getAuthor())) {
            bookQueryConditions.append("Author = '" + bookFilter.getAuthor() + "' AND ");
        }
        if (isSet(bookFilter.getVolumeNumber())) {
            bookQueryConditions.append("VolumeNumber = " + bookFilter.getVolumeNumber() + " AND ");
        }
        if (isSet(bookFilter.getCategory())) {
            bookQueryConditions.append("books.idCategory = " + bookFilter.getCategory().getId() + " AND ");
        }
        if (isSet(bookFilter.getAddedBy())) {
            bookQueryConditions.append("AddedBy = " + bookFilter.getAddedBy().getId() + " AND ");
        }
        if (isSet(bookFilter.getStatus())) {
            bookQueryConditions.append("Status = '" + bookFilter.getStatus() + "' AND ");
        }

        bookQueryConditions.append("idBook > 0");

        bookQueryConditions.append(rowLimit(bookFilter.getRowLimit()));

        return bookQueryConditions.toString();
    }

}
