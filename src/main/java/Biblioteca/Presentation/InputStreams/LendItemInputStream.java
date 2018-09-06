package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.LendingProcessor;
import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.User.User;
import Biblioteca.Presentation.OutputStreams.BookOutputStream;
import Biblioteca.Presentation.OutputStreams.UserOutputStream;

public class LendItemInputStream {

    private static LendingProcessor lendingProcessor = new LendingProcessor();

    public void lendBookUsingKeyboard(User librarian){

        LendingProcessor bookProcessor = new LendingProcessor();
        BookOutputStream bookOutputStream = new BookOutputStream();
        UserOutputStream userOutputStream = new UserOutputStream();

        System.out.println("Lend a book: ");
        Book book = bookOutputStream.getBookFromDatabaseUsingScanner();
        User toStudent = userOutputStream.getUserFromDatabaseUsingScanner();

        lendingProcessor.lendBookToStudent(book, toStudent, librarian);
    }

    public void returnBookUsingKeyboard(User librarian){

        LendingProcessor bookProcessor = new LendingProcessor();
        BookOutputStream bookOutputStream = new BookOutputStream();

        System.out.println("Lend a book: ");
        Book book = bookOutputStream.getBookFromDatabaseUsingScanner();


        lendingProcessor.returnBookToLibrary(book);
    }

}
