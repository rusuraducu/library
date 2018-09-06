package Biblioteca;

import Biblioteca.DAO.User.User;
import Biblioteca.Actions.UserProcessor;

public class Login {

    private Login() { }

    public static User getUsernameInstance(String userName, String givenPassword) {
        User user = UserProcessor.loadUserInstance(userName);
        try {

            if(!user.isActive(user)){ System.out.println("This username is deleted."); return null; }

            if (user.getPassword().equals(givenPassword)){
                System.out.println("Welcome "+user.getUsername()+ " you've been logged in as " + user.getRank().getRankName()+".\n");
                return user;
            }
        } catch (NullPointerException e) { return null; }

        System.out.println("The password is wrong.");

       return null;
    }
}
