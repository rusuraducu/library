package Biblioteca.Presentation.Menu;

import Biblioteca.UserOptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu implements IMenu{

    private UserOptions adminOptions = null;

    public AdminMenu(UserOptions adminOptions) {
        this.adminOptions = adminOptions;
    }

    public void getMenu() {
        int value = 0;
        menuOptions();
        while (value != 5) {
            value = getValue();
            switchBlock(adminOptions, value);
        }
    }

    private void menuOptions() {

        System.out.println("1. Show all users from library.");
        System.out.println("2. Add new user to database.");
        System.out.println("3. Delete user from database.");
        System.out.println("4. Add new item type to database.");
        System.out.println("5. Show menu.");
        System.out.println("5. Quit program.");
    }

    private void switchBlock(UserOptions adminOptions, int value) {
        switch (value) {
            case 1:
                adminOptions.printListWithUsers();
                break;
            case 2:
                adminOptions.addNewUserToDatabaseUsingScanner();
                break;
            case 3:
                adminOptions.deleteUserFromDatabaseUsingScanner();
                break;
            case 4:
                adminOptions.addNewItemTypeToDatabaseUsingScanner();
                break;
            case 5:
                menuOptions();
                break;
        }
    }

    private int getValue(){
        int value = 0;
        try{
            Scanner scanner = new Scanner(System.in);
            value = scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println("You must input a number. ");
            getValue();
        }
        return value;
    }
}
