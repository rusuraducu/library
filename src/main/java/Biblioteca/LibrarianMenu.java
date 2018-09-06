package Biblioteca;

import Biblioteca.Actions.CategoryProcessor;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.DAO.User.User;

public class LibrarianMenu {
    public static void main(String[] args) {

        User librarian = Login.getUsernameInstance("Grigore", "myPassword");
        UserOptions librarianOptions = UserOptions.getUserOptions(librarian);

        // librarianOptions.addNewBookToDatabaseUsingScanner();

        // librarianOptions.showAllBooksFromLibrary();

        //--->Actualizeaza Carte

        //Book book = BookProcessor.loadBookInstance("Povesti");
        //book.setAuthor("Ion Creanca");
        //librarianOptions.updateBookDetails(book);

        //--->Lend book

        //librarianOptions.lendBook(BookProcessor.loadBookInstance("Baltagul"), UserProcessor.loadUserInstance("Gratian"));

        //--->Returneaza carte

        //librarianOptions.returnBook(BookProcessor.loadBookInstance("Baltagul"));

        //--->Retrage o carte

        //librarianOptions.retireBook(BookProcessor.loadBookInstance("Baltagul"), RetireReason.DESTROYED);

        //Afiseaza cartile imprumutate momentan.

        // librarianOptions.printCurrentlyLentItems();

        //Afiseaza cartile care au depasit perioada pentru care au fost imprumutate

        // librarianOptions.printLentBooksWhichAreOverdue();

        //Get books from category

        //librarianOptions.showBooksUsingFilter(new BookFilter(CategoryProcessor.loadCategoryInstance("Literatura")));

    }
}
