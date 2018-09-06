package Biblioteca.Validations;

import Biblioteca.Actions.BookProcessor;

public class BookValidations extends Validation {

    public String name(String bookName) {

        if (isBlankOrNull(bookName)) {
            return "You have to set book's name.";
        }
        if(BookProcessor.loadBookInstance(bookName)!=null){
            return "There is a book with this name.";
        }
        if (overMaximumLength(bookName, 100)){
            return "The book's name is to long.";
        }
        if (overMaximumLimitOfWords(bookName, 20)) {
            return "The book's name should contain maximum 20 words.";
        }

        return "OK";
    }

    public String author(String author) {

        if (isBlankOrNull(author)) {
            return "You have to set author's name.";
        }
        if(!isStringOfLetters(author)){
            return "The author's name should contain only letters.";
        }
        if(underMinimumLimitOfWords(author, 2)){
            return "You have to input the complete name of the author";
        }
        if (overMaximumLimitOfWords(author, 4)) {
            return "The author's name can contain maximum 4 names.";
        }
        return "OK";
    }

    public String description(String description) {

        if (isBlankOrNull(description)) {
            return "You have to write the book's description.";
        }
        if (underMinimumLength(description, 30)) {
            return "The description must contain at least 30 characters.";
        }
        if (overMaximumLength(description, 255)) {
            return "The description cannot contain more than 255 characters.";
        }
        if (underMinimumLimitOfWords(description, 6)) {
            return "The description must contain at least 5 words.";
        }
        return "OK";
    }

    public String pages(String numberOfPages){

        if(parseException(numberOfPages)){
            return "The number of pages should be formatted only from digits.";
        }

        int numberOfPagesInt = Integer.parseInt(numberOfPages);

        if(lessThan(numberOfPagesInt, 50)){
            return "You cannot add books which have less than 50 pages.";
        }
        if(greaterThan(numberOfPagesInt, 1500)){
            return "You cannot add books which have more than 1500 pages.";
        }

        return "OK";
    }

    public String volume(String volumeNumber){


        if(parseException(volumeNumber)){
            return "The volume number should be formatted only from digits.";
        }

        int volumeNumberInt = Integer.parseInt(volumeNumber);

        if(lessThan(volumeNumberInt, 1)){
            return "The volume's number cannot be less than 1.";
        }
        if(greaterThan(volumeNumberInt, 15)){
            return "The volume's number cannot be greater then 15";
        }
        return "OK";
    }

}
