package Biblioteca.Presentation.Menu;

import Biblioteca.UserOptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibrarianMenu implements IMenu{

    private UserOptions librarianOptions = null;

    public LibrarianMenu(UserOptions librarianOptions) {
        this.librarianOptions = librarianOptions;
    }

    public void getMenu() {
        int value = 0;
        menuOptions();
        while (value != 9) {
            value = getValue();
            switchBlock(librarianOptions, value);
        }
    }

    private void menuOptions() {

        System.out.println("1. Show all book from library.");
        System.out.println("2. Print currently lent items.");
        System.out.println("3. Print lent book which are overdue.");
        System.out.println("4. Print retired books.");
        System.out.println("5. Lend a book from library.");
        System.out.println("6. Set a book as return.");
        System.out.println("7. Retire a book from library.");
        System.out.println("8. Show librarian's Menu.");
        System.out.println("9. Quit program.");
    }

    private void switchBlock(UserOptions librarianOptions, int value) {
        switch (value) {
            case 1:
                librarianOptions.printAllBooksFromLibrary();
                break;
            case 2:
                librarianOptions.printCurrentlyLentItems();
                break;
            case 3:
                librarianOptions.printLentBooksWhichAreOverdue();
                break;
            case 4:
                librarianOptions.printRetiredBooks();
                break;
            case 5:
                librarianOptions.lendBookUsingScanner();
                break;
            case 6:
                librarianOptions.returnBookUsingScanner();
                break;
            case 7:
                librarianOptions.retireBookUsingScanner();
                break;
            case 8:
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
