package Biblioteca;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Book.BookFilter;
import Biblioteca.Presentation.InputStreams.*;
import Biblioteca.Actions.BookProcessor;
import Biblioteca.Actions.LendingProcessor;
import Biblioteca.DAO.RetireItem.RetireReason;
import Biblioteca.Actions.RetiringProcessor;
import Biblioteca.DAO.User.User;
import Biblioteca.Actions.UserProcessor;

public class UserOptions {

    private static User user = null;

    private static UserProcessor userProcessor = new UserProcessor();
    private static BookProcessor bookProcessor = new BookProcessor();
    private static LendingProcessor lendingProcessor = new LendingProcessor();
    private static RetiringProcessor retiredItem = new RetiringProcessor();
    private static UserInputStream userInputStream = new UserInputStream();
    private static LendItemInputStream lendItemInputStream = new LendItemInputStream();
    private static RetireItemInputStream retireItemInputStream = new RetireItemInputStream();
    private static TypeInputStream typeInputStream = new TypeInputStream();

    public User getUser() {
        return user;
    }

    private UserOptions(User user) {
        this.user = user;
    }

    public static UserOptions getUserOptions(User user) {

        return new UserOptions(user);
    }

    /****
     *
     * The following 3 methods from this class ensure that the content of this object is accessed only the the users
     * who has the right to do it.
     *
     ****/

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

    /**
     *  Admin options
     */

    public void updateUserFromDatabase(User user) {
        if (isAdmin ( )) {
            userProcessor.updateUserFromDatabase(user);}
    }

    public void deleteUserFromDatabase(User user) {
        if (isAdmin ( )) {
            userProcessor.deleteUser(user);}
    }

    public void  addNewUserToDatabase(User user){
        if (isAdmin ( )) {
            userProcessor.addNewUserToDatabase(user);}
    }

    public void printListWithUsers() {

        if (isAdmin ( )) {      userProcessor.printUserList();}
        System.out.println("\n");
    }


    /**
     * Librarian options
     */

    public void addNewBookToDatabase(Book book) {

        if (isLibrarian ( )) {    bookProcessor.addNewBookToDatabase(book, user);                   } }


    public void searchContent(BookFilter bookFilter){

        if (isLibrarian ( )) {    bookProcessor.loadListOfBooksFromDatabaseUsingFilter(bookFilter); }

    }

    public void updateBookDetails(Book book){

        if (isLibrarian ( )) { bookProcessor.updateBookFromDatabaseDatabase(book, user);} }


    public void showBooksUsingFilter(BookFilter bookFilter) {

        if (isLibrarian ( )) {  bookProcessor.loadListOfBooksFromDatabaseUsingFilter(bookFilter);       }
        System.out.println("\n");
    }

    public void printRetiredBooks(){

        if (isLibrarian ( )) { retiredItem.showRetiredBooks                     (   );  }

    }

    public void printLentBooksWhichAreOverdue(){

        if (isLibrarian( ) ) { lendingProcessor.showLentBooksWhichAreOverdue    (   );  }

    }

    public void printAllBooksFromLibrary() {

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


    public void printCurrentlyLentItems(){

        if (isLibrarian ( )) { lendingProcessor.showBooksWhichAreCurrentlyLent  (   );  }

    }

    /**
     * The following methods are used for 'Presentation'. They are the same as some of the previous ones but
     * they don't need any parameters. The will get all the parameters they need using Scanner object during
     * the presentation time.
     */

    //Admin options

    public void addNewUserToDatabaseUsingScanner() {
        if (isAdmin ( )) { userInputStream.addNewUserToDatabaseUsingScanner();}
    }

    public void deleteUserFromDatabaseUsingScanner() {
        if (isAdmin ( )) { userInputStream.deleteUserFromDatabaseUsingScanner();}
    }

    public void addNewItemTypeToDatabaseUsingScanner(){
        if(isAdmin      ( )) { typeInputStream.addNewTypeToDatabaseUsingScanner(); }
    }

    //Librarian options

    public void lendBookUsingScanner(){

        if (isLibrarian ( )) {  lendItemInputStream.lendBookUsingKeyboard(user); }// Default 14 days

    }

    public void addNewBookToDatabaseUsingScanner() {

        if (isLibrarian ( )) {     new BookInputStream().addNewBookToDatabaseByKeyboard(user);      } }


    public void returnBookUsingScanner(){

        if (isLibrarian ( )) { lendItemInputStream.returnBookUsingKeyboard(); }// Default 14 days

    }

    public void retireBookUsingScanner(){
        if (isLibrarian ( )) {  retireItemInputStream.retireBookUsingScanner(user); }
    }
}
