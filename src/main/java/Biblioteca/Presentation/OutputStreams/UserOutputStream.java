package Biblioteca.Presentation.OutputStreams;

import Biblioteca.Actions.UserProcessor;
import Biblioteca.DAO.User.User;

import java.util.Scanner;

public class UserOutputStream {

    public User getUserFromDatabaseUsingScanner() {

        Scanner scanner = new Scanner(System.in);
        boolean readyToReturn = false;

        User user = null;

        while (!readyToReturn) {

            System.out.println("Enter the user's name: ");
            String userNamee = scanner.nextLine();
            user = UserProcessor.loadUserInstance(userNamee);
            if (user == null) {
                continue;
            }
            readyToReturn = true;
        }
        return user;
    }

}
