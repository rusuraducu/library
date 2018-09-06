package Biblioteca.Queries;

import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.Type.TypeFilter;

import java.sql.*;

public class TypeQueries extends  Query{

    private static String BASIC_ITEM_TYPE_QUERY = "SELECT * FROM item_type ";

    private static String INSERT_NEW_TYPE = "INSERT INTO item_type(TypeName) VALUES(?)";

    public int insertNewItemTypeToDatabase(String typeName) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_TYPE)) {

            preparedStatement.setString(1, typeName);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        return rowsAffected;
    }

    public Type loadType(int id){

        TypeFilter typeFilter = new TypeFilter(id);

        ResultSet resultSet = null;

        try( Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {

           resultSet = statement.executeQuery(getItemTypeQuery(typeFilter));

            if(resultSet.next()){
                int idType = resultSet.getInt("idType");
                String typeName = resultSet.getString("TypeName");
                return new Type(idType, typeName);
            }
        }

        catch (SQLException e) { e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return null;
    }

    public Type loadType(String type){

        TypeFilter typeFilter = new TypeFilter(type);

        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(getItemTypeQuery(typeFilter));

            if(resultSet.next()){
                int idType = resultSet.getInt("idType");
                String typeName = resultSet.getString("TypeName");
                return new Type(idType, typeName);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return null;
    }

    private String getItemTypeQuery(TypeFilter typeFilter) {

        StringBuilder getTypeQuery = new StringBuilder(BASIC_ITEM_TYPE_QUERY);
        getTypeQuery.append(getTypeQueryConditions(typeFilter));
        return getTypeQuery.toString();

    }

    private String getTypeQueryConditions(TypeFilter typeFilter) {

        StringBuilder typeQueryConditions = new StringBuilder();

        typeQueryConditions.append("WHERE ");

        if (isSet(typeFilter.getId())){
            typeQueryConditions.append(" idType = " + typeFilter.getId() + " AND ");
        }
        if (isSet(typeFilter.getTypeName())) {
            typeQueryConditions.append(" TypeName = '" + typeFilter.getTypeName() + "' AND ");
        }
        typeQueryConditions.append("idType > 0");

        typeQueryConditions.append(rowLimit(typeFilter.getRowLimit()));

        return typeQueryConditions.toString();
    }

}
