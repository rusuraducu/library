package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.UserProcessor;
import Biblioteca.DAO.Rank.Rank;
import Biblioteca.DAO.User.User;
import Biblioteca.Presentation.OutputStreams.RankOutputStream;
import Biblioteca.Presentation.OutputStreams.UserOutputStream;
import Biblioteca.Validations.UserValidations;

import java.util.Scanner;

public class UserInputStream {

    private static UserValidations validate = new UserValidations();

    public void deleteUserFromDatabaseUsingScanner(){

        UserProcessor userProcessor = new UserProcessor();
        UserOutputStream userOutputStream = new UserOutputStream();

        User user =userOutputStream.getUserFromDatabaseUsingScanner();

        userProcessor.deleteUser(user);
    }

    public void addNewUserToDatabaseUsingScanner(){
        UserProcessor userProcessor = new UserProcessor();

        RankOutputStream outputStream = new RankOutputStream();

        System.out.println("Add new user to database: ");
        String username = setUserName();
        String password = setPassword();
        Rank rank = outputStream.getRankFromDatabaseUsingScanner();
        String email = setEmail();
        String firstName = setFirstName();
        String secondName = setLastName();

        User user = new User(username, password, rank, email, firstName, secondName);

        userProcessor.addNewUserToDatabase(user);
    }

    public String setUserName() {

        boolean readyToReturn = false;

        String userName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set the username: ");
            userName = scanner.nextLine();
            if (validate.username(userName) != "OK") {
                String validationMessage = validate.username(userName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return userName;
    }

    public String setFirstName() {

        boolean readyToReturn = false;

        String firstName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set the FirstName: ");
            firstName = scanner.nextLine();
            if (validate.firstName(firstName) != "OK") {
                String validationMessage = validate.firstName(firstName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return firstName;
    }

    public String setLastName() {

        boolean readyToReturn = false;

        String lastName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set the LastName: ");
            lastName = scanner.nextLine();
            if (validate.lastName(lastName) != "OK") {
                String validationMessage = validate.lastName(lastName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return lastName;
    }

    public String setPassword() {

        boolean readyToReturn = false;

        String password = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set the password: ");
            password = scanner.nextLine();
            if (validate.password(password) != "OK") {
                String validationMessage = validate.password(password);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return password;
    }

    public String setEmail() {

        boolean readyToReturn = false;

        String email = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set the email: ");
            email = scanner.nextLine();
            if (validate.email(email) != "OK") {
                String validationMessage = validate.email(email);
                System.out.println(validationMessage);
                continue;
            }
            readyToReturn = true;
        }
        return email;
    }

}
