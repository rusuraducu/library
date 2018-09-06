package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.BookProcessor;
import Biblioteca.Presentation.OutputStreams.CategoryOutputStream;
import Biblioteca.Validations.BookValidations;
import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.Type.Type;
import Biblioteca.Actions.TypeProcessor;
import Biblioteca.DAO.User.User;

import java.util.Scanner;

public class BookInputStream {

    private static BookValidations validate = new BookValidations();

    public String setBookName() {

        boolean readyToReturn = false;

        String bookName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the book: ");
            bookName = scanner.nextLine();
            if (validate.name(bookName) != "OK") {
                String validationMessage = validate.name(bookName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return bookName;
    }

    public String setAuthorName() {

        boolean readyToReturn = false;

        String authorName = new String();

        while (!readyToReturn) {
            System.out.println("Enter the author's name: ");

            Scanner scanner = new Scanner(System.in);
            authorName = scanner.nextLine();

            if (validate.author(authorName) != "OK") {
                String validationMessage = validate.author(authorName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return authorName;
    }

    public String setDescription() {

        boolean readyToReturn = false;

        String description = new String();

        while (!readyToReturn) {
            System.out.println("Enter the description: ");

            Scanner scanner = new Scanner(System.in);
            description = scanner.nextLine();

            if (validate.description(description) != "OK") {
                String validationMessage = validate.description(description);
                System.out.println(validationMessage);
                continue;
            }
            readyToReturn = true;
        }
        return description;
    }

    public String setNumberOfPages() {

        boolean readyToReturn = false;

        String numberOfPages = new String();

        while (!readyToReturn) {
            System.out.println("Enter the number of pages: ");

            Scanner scanner = new Scanner(System.in);
            numberOfPages = scanner.nextLine();

            if (validate.pages(numberOfPages) != "OK") {
                String validationMessage = validate.pages(numberOfPages);
                System.out.println(validationMessage);
                continue;
            }
            readyToReturn = true;
        }
        return numberOfPages;
    }

    public String setVolumeNumber() {

        boolean readyToReturn = false;

        String volumeNumber = new String();

        while (!readyToReturn) {
            System.out.println("Enter the number of volume: ");

            Scanner scanner = new Scanner(System.in);
            volumeNumber = scanner.nextLine();

            if (validate.volume(volumeNumber) != "OK") {
                String validationMessage = validate.volume(volumeNumber);
                System.out.println(validationMessage);
                continue;
            }
            readyToReturn = true;
        }
        return volumeNumber;
    }

    public void addNewBookToDatabaseByKeyboard(User user){

        BookProcessor bookProcessor = new BookProcessor();
        CategoryOutputStream outputStream = new CategoryOutputStream();

        System.out.println("Add new book to database: ");
        String bookName = setBookName();
        String author = setAuthorName();
        String description = setDescription();
        Category category = outputStream.getCategoryFromDatabaseUsingScanner();
        int volumeNumber = Integer.parseInt(setVolumeNumber());
        int numberOfPages = Integer.parseInt(setNumberOfPages());
        Type type = TypeProcessor.loadTypeInstance("BOOK");

        Book book = new Book(bookName, author, description, category, user,type, Status.AVAILABLE, volumeNumber, numberOfPages );

        bookProcessor.addNewBookToDatabase(book, user);
 }

}