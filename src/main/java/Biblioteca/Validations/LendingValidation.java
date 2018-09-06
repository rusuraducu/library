package Biblioteca.Validations;

import Biblioteca.DAO.Book.Book;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.User.User;

public class LendingValidation {

    public String lending(User toUser, Book book){

        if(!toUser.getStatus().equals("Active")){

            return "The user must be active";
        }
        if(!toUser.getRank().getRankName().equals("Student")){

            return "Only students can lend book from this library";
        }

        if(!book.getStatus().equals(Status.AVAILABLE)){
           return  "The book is not available for the moment.";
        }

        if(book.getStatus().equals(Status.LENT))
        {
            return "This book is currently lent.";
        }
        return "OK";
    }
}
