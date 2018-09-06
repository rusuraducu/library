package Biblioteca.Queries;

import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Category.CategoryFilter;
import Biblioteca.Connection.ConnectionPool;

import java.sql.*;


public class CategoryQueries extends Query {

    private static String BASIC_CATEGORY_QUERY = "SELECT * FROM categories ";

    private static String INSERT_NEW_CATEGORY = "INSERT INTO categories(CategoryName) VALUES(?)";

    public int insertNewCategoryToDatabase(String categoryName) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_CATEGORY)) {

            preparedStatement.setString(1, categoryName);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        return rowsAffected;
    }


    public Category loadCategoryFromDatabase(CategoryFilter categoryFilter) {

        try ( Connection connection = ConnectionPool.getConnection();
              Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getCategoryQuery(categoryFilter));

            if (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String categoryName = resultSet.getString("CategoryName");
                return new Category(idCategory, categoryName);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }


        return null;
    }

    private String getCategoryQuery(CategoryFilter categoryFilter) {

        StringBuilder getCategoryQuery = new StringBuilder(BASIC_CATEGORY_QUERY);
        getCategoryQuery.append(getCategoryQueryConditions(categoryFilter));

        return getCategoryQuery.toString();
    }

    private String getCategoryQueryConditions(CategoryFilter categoryFilter) {

        StringBuilder categoryQueryConditions = new StringBuilder();

        categoryQueryConditions.append("WHERE ");

        if (isSet(categoryFilter.getId())) {
            categoryQueryConditions.append(" idCategory = " + categoryFilter.getId() + " AND ");
        }
        if (isSet(categoryFilter.getCategoryName())) {
            categoryQueryConditions.append(" CategoryName = '" + categoryFilter.getCategoryName() + "' AND ");
        }
        categoryQueryConditions.append("idCategory>0");

        categoryQueryConditions.append(rowLimit(categoryFilter.getRowLimit()));

        return categoryQueryConditions.toString();
    }
}
