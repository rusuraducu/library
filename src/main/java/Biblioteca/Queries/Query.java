package Biblioteca.Queries;

import Biblioteca.Actions.CurrentDate;
import Biblioteca.Connection.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    public static CurrentDate currentDate = new CurrentDate();

    public String rowLimit(int limit) {
        if (limit == 0) {
            return " LIMIT 25";
        }
        return " LIMIT " + limit;
    }

    public java.sql.Connection getConnection(){
        return ConnectionPool.getConnection();
    }



    public boolean isSet(Object field) {
        if (field != null) {
            return true;
        }
        return false;
    }

    public boolean isSet(String field) {
        if (field != null && !field.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isSet(int field) {
        if (field != 0) {
            return true;
        }
        return false;
    }


    public void closeResultSet(ResultSet resultSet) {

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
