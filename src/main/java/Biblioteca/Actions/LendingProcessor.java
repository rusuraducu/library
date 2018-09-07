package Biblioteca.Actions;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.LendItem.LendItem;
import Biblioteca.Presentation.OutputStreams.BookOutputStream;
import Biblioteca.Queries.BookQueries;
import Biblioteca.DAO.Item.Status;
import Biblioteca.Queries.LendingQueries;
import Biblioteca.DAO.User.User;
import Biblioteca.Validations.LendingValidation;

import java.util.List;

public class LendingProcessor extends Processor {

    private static LendingQueries lendQueries = new LendingQueries();

    public void lendBookToStudent(Book book, User toStudent, User byLibrarian){

        LendingValidation validate = new LendingValidation();

        String lendingMessageValidation = validate.lending(toStudent, book);

        if(!lendingMessageValidation.equals("OK")){

            print(lendingMessageValidation); return;

        }

        if(lendQueries.lendBookFromDatabase(book, toStudent, byLibrarian) != 2){

            print("It occurred an error and the book couldn't be lent.");               return;
        }

        print("The book " + book.getName()+ " has been lent to "+ toStudent.getUsername()+ " by librarian " + byLibrarian.getUsername());
 }

    public void returnBookToLibrary(Book book){

        BookQueries bookQueries = new BookQueries();

        if(book.getStatus().equals(Status.AVAILABLE)){
            print("This book it is not currently lent.");                          return;
        }
        if(book.getStatus().equals(Status.RETIRED)){
            print("This book is retired.");                                        return;
        }
        if(lendQueries.returnBookToLibrary(book) != 2){
            print("The book cannot be set as return.");                            return;
        }
        print("The book "+book.getName()+" has been return.");
    }

    public void showBooksWhichAreCurrentlyLent(){

        List<LendItem> currentlyLentBooksList = lendQueries.getBooksThatAreCurrentlyLent();

        if(currentlyLentBooksList.isEmpty()){
            print("There aren't any lent books."); return;
        }
        printList(currentlyLentBooksList);
    }

    public void showLentBooksWhichAreOverdue(){

        BookOutputStream outputStream = new BookOutputStream();

        List<OverdueItem> overdueBooksList = lendQueries.getBooksItemsWhichAreOverdue(100);

        if(overdueBooksList.isEmpty()){ print("There aren't any overdue books."); return; }

        printList(overdueBooksList);

    }

}
