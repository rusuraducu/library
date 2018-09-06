package Biblioteca.Presentation.OutputStreams;

import Biblioteca.Actions.BookProcessor;
import Biblioteca.Actions.OverdueItem;
import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.DAO.LendItem.LendItem;
import Biblioteca.Presentation.TableFormat;

import java.util.List;
import java.util.Scanner;

public class BookOutputStream extends OutputStream {

    public Book getBookFromDatabaseUsingScanner() {

        Scanner scanner = new Scanner(System.in);
        boolean readyToReturn = false;

        Book book = null;

        while (!readyToReturn) {

            System.out.println("Enter the book's name: ");
            String categoryName = scanner.nextLine();
            BookFilter bookFilter = new BookFilter(categoryName);
            book = BookProcessor.loadBookInstance(bookFilter);
            if (book == null) {
                continue;
            }
            readyToReturn = true;
        }
        return book;
    }


}
