package Biblioteca.Actions;

import Biblioteca.DAO.User.User;
import Biblioteca.DAO.User.UserFilter;
import Biblioteca.Queries.UserQueries;

public class UserProcessor extends Processor {

    private static UserQueries QUERIES = new UserQueries();

    public static User loadUserInstance(int id) {

        UserFilter userFilter = new UserFilter(id);

        User user = new UserQueries().loadUserFromDatabase(userFilter);

        try {
            if(!user.getUsername().isEmpty()) { return user; } }

        catch (NullPointerException e){
            return null;
        }
        return user;
    }

    public static User loadUserInstance(String userName) {

        UserFilter userFilter = new UserFilter(userName);

        User user = QUERIES.loadUserFromDatabase(userFilter);

        try {
            if(!user.getUsername().isEmpty()) { return user; } }

        catch (NullPointerException e){
            System.out.println("This username doesn't exist."); return null;
        }
        return user;
    }

    public void addNewUserToDatabase(User user) {

        if (QUERIES.insertNewUserToDatabase(user) == 1) {
            print("The username " + user.getUsername() + " has been added to database.");
            return;
        }

        print("This username couldn't be added to database.");
    }


    public void updateUserFromDatabase(User user) {

        if (QUERIES.updateUserFromDatabase(user) == 1) {
            print("The user  update has been done.");
            return;
        }
        print("The user update couldn't be done.");

    }

    public void printUserList(){
       printList(QUERIES.getUserListFromDatabase());
    }

    public void deleteUser(User user) {

        UserQueries userQueries = new UserQueries();

        if (user.getStatus().equals("Inactive/Deleted")) {
            print("This username has already been deleted.");
            return;
        }

        boolean isAdmin = user.getRank().getRankName().equals("Administrator");

        if (isAdmin && userQueries.getNumberOfAdmins() < 2) {
            print("There must be at least one Administrator.");
            return;
        }

        int rowsAffected = userQueries.setUserStatusAsInactiveOrDeleted(user);

        if (rowsAffected == 1) {
            print("The user " + user.getUsername() + " has been deleted from database.");
            return;
        }

        print("This user couldn't be deleted.");
    }



}
