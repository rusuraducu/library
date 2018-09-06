package Biblioteca.Queries;

import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Rank.Rank;
import Biblioteca.DAO.Rank.RankFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RankQueries extends Query {

    private static String BASIC_RANK_QUERY = "SELECT * FROM ranks ";

    public Rank loadRank(RankFilter rankFilter){

        RankQueries rankQueries = new RankQueries();
        String query = rankQueries.getRankQuery(rankFilter);

        ResultSet resultSet = null;

        try( Connection connection = ConnectionPool.getConnection();
             Statement stmt = connection.createStatement()){

            resultSet = stmt.executeQuery(query);

            if(resultSet.next()){
                int idRank = resultSet.getInt("idRank");
                String RankName = resultSet.getString("RankName");
                return new Rank(idRank, RankName);
            }
        }

        catch (SQLException e){ e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return null;
    }

    public String getRankQuery(RankFilter rankFilter) {

        StringBuilder rankQueryConditions = new StringBuilder(BASIC_RANK_QUERY);

        rankQueryConditions.append("WHERE ");

        if(isSet(rankFilter.getIndex())){
            rankQueryConditions.append("idRank = " + rankFilter.getIndex()+ " AND ");
        }
        if(isSet(rankFilter.getRankName())){
            rankQueryConditions.append("RankName = '" + rankFilter.getRankName()+ "' AND ");
        }
        rankQueryConditions.append("idRank > 0");

        rankQueryConditions.append(rowLimit(rankFilter.getRowLimit()));

        return rankQueryConditions.toString();
    }

}
