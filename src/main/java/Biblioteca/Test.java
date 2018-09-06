package Biblioteca;

import Biblioteca.Actions.UserProcessor;
import Biblioteca.DAO.User.User;

public class Test {
    public static void main(String[] args) {

        //ADMIN

        User admin = Login.getUsernameInstance("Raducu", "myPassword");
        UserOptions adminOptions = UserOptions.getUserOptions(admin);

        //Adauna un nou utilizator:                 //adminOptions.addNewUserToDatabase();

        //Afiseaza lista utilizatori:               //adminOptions.printListOfUsers();

        //Cauta utilizator in baza de date         //adminOptions.searchUser("Raducu");

        //Update user

                //User user01 = UserProcessor.loadUserInstance("Marius");
                //user01.setEmail("marius@gmail.com");
                //adminOptions.updateUserFromDatabase(user01);

        //Delete user

                //adminOptions.deleteUserFromDatabase(UserProcessor.loadUserInstance("Bogdan"));



    }
}
