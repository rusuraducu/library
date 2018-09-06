package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.RetiringProcessor;
import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.User.User;
import Biblioteca.DAO.RetireItem.RetireReason;
import Biblioteca.Presentation.OutputStreams.BookOutputStream;

import java.util.Scanner;

public class RetireItemInputStream {

    private static RetiringProcessor retiringProcessor = new RetiringProcessor();

    public void retireBookUsingScanner(User librarian) {
        BookOutputStream outputStream = new BookOutputStream();
        Book book = outputStream.getBookFromDatabaseUsingScanner();
        Scanner scanner = new Scanner(System.in);
        System.out.println("The retire reason must be: STOLEN, UNAVAILABLE or DESTROYED");
        RetireReason retireReason = RetireReason.valueOf(scanner.nextLine());

        retiringProcessor.retireBook(book, librarian, retireReason);

    }
}
