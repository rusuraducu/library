package Biblioteca.Actions;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.RetireItem.RetiredItem;
import Biblioteca.DAO.RetireItem.RetiredItemsFilter;
import Biblioteca.DAO.Item.Status;
import Biblioteca.Queries.BookQueries;
import Biblioteca.Queries.RetireQuery;
import Biblioteca.DAO.User.User;
import Biblioteca.DAO.RetireItem.RetireReason;

import java.util.List;

public class RetiringProcessor extends Processor {

    private static RetireQuery QUERY = new RetireQuery();
    private static BookQueries bookQueries = new BookQueries();

    public void retireBook(Book book, User user, RetireReason retireReason) {

        if(book.getStatus().equals(Status.LENT)){
            print("The book " +book.getName()+" is currently lent.");           return;
        }

        if(book.getStatus().equals(Status.RETIRED)){
            print("The book " +book.getName()+" has already been retired.");     return;
        }

        if(QUERY.retireBookFromLibrary(book, user, retireReason) == 2){
            print("The book " + book.getName() + " has been retired.");          return;
        }

        print("The book " + book.getName() + " couldn't be retired.");
    }

    public void showRetiredBooks() {

        RetiredItemsFilter retiredFilter = new RetiredItemsFilter();

        List<RetiredItem> retiredItems = QUERY.getRetiredBooksHistory(retiredFilter);

        if (retiredItems.isEmpty()) {
            print("The are not retired books."); return;
        }

        printList(retiredItems);
    }
}
