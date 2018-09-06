package Biblioteca.Actions;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.Queries.BookQueries;
import Biblioteca.DAO.User.User;

import java.util.List;

public class BookProcessor extends Processor {

    private static BookQueries QUERIES = new BookQueries();

    public void searchBookFromDatabase(String bookName) {

        BookFilter bookFilter = new BookFilter(bookName);

        Book book = QUERIES.loadBookFromDatabaseByFilter(bookFilter);

        try {
            if (!book.getName().isEmpty()) {
                print(book.toString());
            }
        } catch (NullPointerException e) {
            print("The book doesn't exist.");
        }
    }

    public void addNewBookToDatabase(Book book, User user) {

        if (QUERIES.insertNewBookToDatabase(book, user) == 1) {
            print("The book " + book.getName() + " has been added to database.");
            return;
        }
        print("The book " + book + " couldn't be added to database...");

    }

    public void updateBookFromDatabaseDatabase(Book book, User user) {

        if (QUERIES.updateBookFromDatabase(book, user) == 1) {
            print("The book " + book.getName() + " has been updated.");
            return;
        }
        print("The book " + book + " couldn't be updated.");

    }

    public Book loadBookFromDatabaseUsingFilter(BookFilter bookFilter) {

        Book book = QUERIES.loadBookFromDatabaseByFilter(bookFilter);

        try {

            if (!book.getName().isEmpty()) { return book; }

        } catch (NullPointerException e) {
            print("The book doesn't exist.");
            return null;
        }

        return book;
    }


    public void loadListOfBooksFromDatabaseUsingFilter(BookFilter bookFilter) {

        List<Book> listOfBooks = QUERIES.getBooksFromDatabaseByFilter(bookFilter);

        if (listOfBooks.isEmpty()) {
            print("Couldn't be found books in database using this filter.");
        }

        printList(listOfBooks);
    }

    public static Book loadBookInstance(int bookId){

        BookFilter bookFilter = new BookFilter(bookId);

        Book book = QUERIES.loadBookFromDatabaseByFilter(bookFilter);

        try{ if(!book.getName().isEmpty()) { return  book; } }

        catch (NullPointerException e){ System.out.println("The book doesn't exist."); return null; }

        return book;
    }

    public static Book loadBookInstance(BookFilter bookFilter){

        Book book = QUERIES.loadBookFromDatabaseByFilter(bookFilter);

        try{ if(!book.getName().isEmpty()) { return  book; } }

        catch (NullPointerException e){
            System.out.println("This book doesn't exist.");
            return null; }

        return book;
    }

    public static Book loadBookInstance(String bookName){

        Book book = QUERIES.loadBookFromDatabaseByFilter(new BookFilter(bookName));

        try{ if(!book.getName().isEmpty()) { return  book; } }

        catch (NullPointerException e){
            System.out.println("This book doesn't exist.");
            return null; }

        return book;
    }

    public static Book loadBookInstance(String bookName, String author, int numberOfVolume){

        BookFilter bookFilter = new BookFilter(bookName, author, numberOfVolume);

        Book book = QUERIES.loadBookFromDatabaseByFilter(bookFilter);

        try{ if(!book.getName().isEmpty()) { return  book; } }

        catch (NullPointerException e){ System.out.println("The book doesn't exist."); return null; }

        return book;
    }
}
