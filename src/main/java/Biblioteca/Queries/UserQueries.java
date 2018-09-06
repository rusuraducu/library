package Biblioteca.Queries;

import Biblioteca.Connection.ConnectionPool;
import Biblioteca.DAO.Rank.Rank;
import Biblioteca.Actions.RankProcessor;
import Biblioteca.DAO.User.User;
import Biblioteca.DAO.User.UserFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserQueries extends Query {

    private static String SELECT_USER_QUERY = "SELECT * FROM users ";

    private static String ADD_NEW_USER_TO_DATABASE_QUERY = "INSERT INTO users (idRank, Username, Password, Email, FirstName, SecondName, Joined) values (?,?,?,?,?,?,?)";

    private static String COUNT_ACTIVE_USERS_FOR_EACH_RANK_QUERY = "SELECT COUNT(idRank) AS Total FROM users WHERE idRank= ? AND Status = 'Active'";

    private static String UPDATE_USER_FROM_DATABASE_QUERY = "UPDATE users SET idRank=?, Username=?,Password=?, Email=?, FirstName=?, SecondName=?,Joined=?,Status=? WHERE idUsername =?";

    public int updateUserFromDatabase(User user) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FROM_DATABASE_QUERY)) {

            preparedStatement.setInt   (9, user.getId               ());
            preparedStatement.setInt   (1, user.getRank().getId());
            preparedStatement.setString(2, user.getUsername         ());
            preparedStatement.setString(3, user.getPassword         ());
            preparedStatement.setString(4, user.getEmail            ());
            preparedStatement.setString(5, user.getFirstname        ());
            preparedStatement.setString(6, user.getSecondname       ());
            preparedStatement.setDate  (7, user.getJoined           ());
            preparedStatement.setString(8, user.getStatus           ());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        return rowsAffected;

    }

    public int setUserStatusAsInactiveOrDeleted(User user) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FROM_DATABASE_QUERY)) {

            user.setStatus("Inactive/Deleted");

            preparedStatement.setInt   (9, user.getId               ());
            preparedStatement.setInt   (1, user.getRank().getId());
            preparedStatement.setString(2, user.getUsername         ());
            preparedStatement.setString(3, user.getPassword         ());
            preparedStatement.setString(4, user.getEmail            ());
            preparedStatement.setString(5, user.getFirstname        ());
            preparedStatement.setString(6, user.getSecondname       ());
            preparedStatement.setDate  (7, user.getJoined           ());
            preparedStatement.setString(8, user.getStatus           ());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        return rowsAffected;

    }

    public int insertNewUserToDatabase(User user) {

        int rowsAffected = 0;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_TO_DATABASE_QUERY)) {

            preparedStatement.setInt   (1, user.getRank().getId());
            preparedStatement.setString(2, user.getUsername           ());
            preparedStatement.setString(3, user.getPassword           ());
            preparedStatement.setString(4, user.getEmail              ());
            preparedStatement.setString(5, user.getFirstname          ());
            preparedStatement.setString(6, user.getSecondname         ());
            preparedStatement.setDate  (7, currentDate.getCurrentDate ());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        return rowsAffected;
    }


    public User loadUserFromDatabase(UserFilter userFilter) {

        UserQueries userQueries = new UserQueries();

        ResultSet resultSet = null;

        try ( Connection connection = ConnectionPool.getConnection();
              Statement stmt = connection.createStatement()) {

           resultSet = stmt.executeQuery(userQueries.getUserFromDatabaseQuery(userFilter));

            if (resultSet.next()) {

                Integer userId = resultSet.getInt                       ("idUsername");
                Rank rank = RankProcessor.loadRankInstance(resultSet.getInt("idRank"   ));
                String username = resultSet.getString                   ("Username"  );
                String password = resultSet.getString                   ("Password"  );
                String email = resultSet.getString                      ("Email"     );
                String firstName = resultSet.getString                  ("FirstName" );
                String secondName = resultSet.getString                 ("SecondName");
                Date joined = resultSet.getDate                         ("Joined"    );
                String status = resultSet.getString                     ("Status"    );

                User user = new User(userId, username, password, email, rank, firstName, secondName, joined, status);

                return user;

            }
        }

        catch (SQLException e) { e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return null;
    }

    public List<User> getUserListFromDatabase() {

        List<User> userList = new ArrayList<>();
        UserFilter userFilter = new UserFilter();
        UserQueries userQueries = new UserQueries();

        ResultSet resultSet = null;

        try ( Connection connection = ConnectionPool.getConnection();
              Statement stmt = connection.createStatement()) {

           resultSet = stmt.executeQuery(userQueries.getUserFromDatabaseQuery(userFilter));

            while (resultSet.next()) {
                Rank rank = RankProcessor.loadRankInstance(resultSet.getInt("idRank"));
                String username = resultSet.getString("Username");
                String email = resultSet.getString("Email");
                Date joined = resultSet.getDate("Joined");
                User user = new User(username, rank, email, joined);
                userList.add(user);
            }
        }

        catch (SQLException e) { e.printStackTrace(); }

        finally { closeResultSet(resultSet); }

        return userList;
    }

    public int getNumberOfAdmins() {

        Rank rank = RankProcessor.loadRankInstance("Administrator");

        ResultSet resultSet = null;

        try ( Connection connection = ConnectionPool.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ACTIVE_USERS_FOR_EACH_RANK_QUERY)) {

            preparedStatement.setInt(1, rank.getId());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { return resultSet.getInt("Total"); }

        }

        catch (SQLException e) { e.printStackTrace();}

        finally { closeResultSet(resultSet); }

        return 0;
    }

    private String getUserFromDatabaseQuery(UserFilter userFilter) {

        StringBuilder selectUser = new StringBuilder(SELECT_USER_QUERY);

        selectUser.append(getUserInnerJoinDependenciesQuery(userFilter));

        selectUser.append(getUserQueryConditionsQueries(userFilter));

        return selectUser.toString();
    }

    private String getUserInnerJoinDependenciesQuery(UserFilter userFilter) {

        StringBuilder innerJoinTables = new StringBuilder();

        if (userFilter.getRank() != null)
        {
            innerJoinTables.append(" INNER JOIN  ranks ON user.idRank = ranks.idRank ");
        }

        return innerJoinTables.toString();

    }

    private String getUserQueryConditionsQueries(UserFilter userFilter) {
        StringBuilder userQueryConditions = new StringBuilder();

        userQueryConditions.append("WHERE ");

        if (isSet(userFilter.getId())) {
            userQueryConditions.append(" idUsername = " + userFilter.getId() + " AND");
        }
        if (isSet(userFilter.getJoined())) {

            userQueryConditions.append(" Joined = '" + userFilter.getJoined() + "' AND");

        }
        if (isSet(userFilter.getRank())) {

            userQueryConditions.append(" RankName = '" + userFilter.getRank().getRankName() + "' AND");

        }
        if (isSet(userFilter.getUsername())) {

            userQueryConditions.append(" Username = '" + userFilter.getUsername() + "' AND");

        }
        if (isSet(userFilter.getStatus())) {

            userQueryConditions.append(" Status = '" + userFilter.getStatus() + "' AND");

        }

        userQueryConditions.append(" idUsername > 0");

        userQueryConditions.append(rowLimit(userFilter.getRowLimit()));

        return userQueryConditions.toString();

    }

}
