package Biblioteca;

import Biblioteca.Actions.CategoryProcessor;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.DAO.User.User;

public class LibrarianMenu {
    public static void main(String[] args) {

        User librarian = Login.getUsernameInstance("Grigore", "myPassword");
        UserOptions librarianOptions = UserOptions.getUserOptions(librarian);

        //  librarianOptions.addNewBookToDatabaseUsingScanner();

        //  librarianOptions.showAllBooksFromLibrary();

        //  Book book = BookProcessor.loadBookInstance("Povesti");
        //  book.setAuthor("Ion Creanga");
        //  librarianOptions.updateBookDetails(book);

        //  librarianOptions.lendBook(BookProcessor.loadBookInstance("Baltagul"), UserProcessor.loadUserInstance("Gratian"));

        //  librarianOptions.returnBook(BookProcessor.loadBookInstance("Baltagul"));

        //  librarianOptions.retireBook(BookProcessor.loadBookInstance("Baltagul"), RetireReason.DESTROYED);

        //  librarianOptions.printCurrentlyLentItems();

        //  librarianOptions.printLentBooksWhichAreOverdue();

        //  librarianOptions.showBooksUsingFilter(new BookFilter(CategoryProcessor.loadCategoryInstance("Literatura")));

    }
}
