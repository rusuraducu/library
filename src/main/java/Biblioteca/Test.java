package Biblioteca;

import Biblioteca.Actions.UserProcessor;
import Biblioteca.DAO.User.User;

public class Test {
    public static void main(String[] args) {

        //ADMIN

        User admin = Login.getUsernameInstance("Raducu", "myPassword");
        UserOptions adminOptions = UserOptions.getUserOptions(admin);

        //adminOptions.addNewUserToDatabase();

        //adminOptions.printListOfUsers();

        //adminOptions.searchUser("Raducu");

        //User user01 = UserProcessor.loadUserInstance("Marius");
        //user01.setEmail("marius@gmail.com");
        //adminOptions.updateUserFromDatabase(user01);

        //adminOptions.deleteUserFromDatabase(UserProcessor.loadUserInstance("Bogdan"));



    }
}
