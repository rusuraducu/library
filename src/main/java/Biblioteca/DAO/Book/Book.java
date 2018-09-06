package Biblioteca.DAO.Book;

import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Item.Item;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.User.User;

import java.sql.Date;

public class Book extends Item {

    private int numberOfVolume;
    private int numberOfPages;

    public Book(int idItem, String name, String author, String description, Category category, Date dateAdded, User addedBy, Type itemType, Status status, int numberOfVolume, int numberOfPages) {
        super(idItem, name, author, description, category, dateAdded, addedBy, itemType, status);
        this.numberOfVolume = numberOfVolume;
        this.numberOfPages = numberOfPages; }

    public Book(String name, String author, String description, Category category, Date dateAdded, User addedBy, Type itemType, Status status, int numberOfVolume, int numberOfPages) {
        super(name, author, description, category, dateAdded, addedBy, itemType, status);
        this.numberOfVolume = numberOfVolume;
        this.numberOfPages = numberOfPages;
    }

    public Book(String name, String author, String description, Category category, User addedBy, Type itemType, Status status, int numberOfVolume, int numberOfPages) {
        super(name, author, description, category, addedBy, itemType, status);
        this.numberOfVolume = numberOfVolume;
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfVolume() {
        return numberOfVolume;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfVolume(int numberOfVolume) {
        this.numberOfVolume = numberOfVolume;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return " " + super.toString()+
                " Volume: " + numberOfVolume +
                " Pages: " + numberOfPages;
    }
    
}
