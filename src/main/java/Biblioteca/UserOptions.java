package Biblioteca;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.Presentation.InputStreams.BookInputStream;
import Biblioteca.Actions.BookProcessor;
import Biblioteca.Actions.LendingProcessor;
import Biblioteca.DAO.RetireItem.RetireReason;
import Biblioteca.Actions.RetiringProcessor;
import Biblioteca.DAO.User.User;
import Biblioteca.Presentation.InputStreams.UserInputStream;
import Biblioteca.Actions.UserProcessor;

public class UserOptions {

    private static User user = null;

    private static UserProcessor userProcessor = new UserProcessor();
    private static BookProcessor bookProcessor = new BookProcessor();
    private static LendingProcessor lendingProcessor = new LendingProcessor();
    private static RetiringProcessor retiredItem = new RetiringProcessor();
    private static UserInputStream userInputStream = new UserInputStream();


    private UserOptions(User user) {
        this.user = user;
    }

    public static UserOptions getUserOptions(User user) {

        return new UserOptions(user);
    }

    public void addNewUserToDatabase() {
        if (isAdmin ( )) { userInputStream.addNewUserToDatabaseByKeyboard();}
    }

    public void updateUserFromDatabase(User user) {
        if (isAdmin ( )) {
            userProcessor.updateUserFromDatabase(user);}
    }

    public void deleteUserFromDatabase(User user) {
        if (isAdmin ( )) { userProcessor.deleteUser(user);}
    }

    public void searchUser(String userName){

        User user = UserProcessor.loadUserInstance(userName);

        if(user!=null){
            System.out.println(user);
        }

    }

    public void printListOfUsers() {

        if (isAdmin ( )) {      userProcessor.printUserList();}
        System.out.println("\n");
    }

    public void addNewBook(Book book) {

        if (isLibrarian ( )) {    bookProcessor.addNewBookToDatabase(book, user);                   }

    }

    public void addNewBookToDatabaseUsingScanner() {

        if (isLibrarian ( )) {     new BookInputStream().addNewBookToDatabaseByKeyboard(user);      }

    }

    public void searchContent(BookFilter bookFilter){

        if (isLibrarian ( )) {    bookProcessor.loadListOfBooksFromDatabaseUsingFilter(bookFilter); }

    }

    public void updateBookDetails(Book book){

        if (isLibrarian ( )) { bookProcessor.updateBookFromDatabaseDatabase(book, user);}

    }

    public void showBooksUsingFilter(BookFilter bookFilter) {

        if (isLibrarian ( )) {  bookProcessor.loadListOfBooksFromDatabaseUsingFilter(bookFilter);       }

        System.out.println("\n");
    }

    public void showAllBooksFromLibrary() {

        if (isLibrarian ( )) {  bookProcessor.loadListOfBooksFromDatabaseUsingFilter(new BookFilter()); }

        System.out.println("\n");
    }

    public void lendBook(Book book, User toStudent){

        if (isLibrarian ( )) {  lendingProcessor.lendBookToStudent(book, toStudent, user); }// Default 14 days

    }

    public void returnBook(Book book){

        if (isLibrarian ( )) { lendingProcessor.returnBookToLibrary(book             ); }

    }

    public void retireBook(Book book, RetireReason retireReason){

        if (isLibrarian ( )) { retiredItem.retireBook(book, user, retireReason      );  }

    }

    public void printRetiredBooks(){

        if (isLibrarian ( )) { retiredItem.showRetiredBooks                     (   );  }

    }

    public void printCurrentlyLentItems(){

        if (isLibrarian ( )) { lendingProcessor.showBooksWhichAreCurrentlyLent  (   );  }

    }

    public void printLentBooksWhichAreOverdue(){

        if (isLibrarian( ) ) { lendingProcessor.showLentBooksWhichAreOverdue    (   );  }

    }

    private boolean loggedIn() {
        if (user == null) {
            System.out.println("Trebuie sa fii autentificat!");
            return false;
        }
        return true;
    }

    private boolean isLibrarian() {
        if (!user.getRank().getRankName().equals("Librarian")) {
            System.out.println("Only the librarians can access this method.");
            return false;
        }
        return true;
    }

    private boolean isAdmin() {
        if (!user.getRank().getRankName().equals("Administrator")) {
            System.out.println("Only the admins can access this method.");
            return false;
        }
        return true;
    }
}
