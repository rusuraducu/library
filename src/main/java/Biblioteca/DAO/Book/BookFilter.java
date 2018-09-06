package Biblioteca.DAO.Book;

import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Item.Status;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.User.User;

import java.sql.Date;

public class BookFilter {
    private int idItem;
    private String name;
    private String author;
    private Category category;
    private Date dateAdded;
    private User addedBy;
    private int volumeNumber;
    private Type itemType;
    private Status status;
    private int rowLimit;

    public BookFilter() {
    }

    public BookFilter(String name) {
        this.name = name;
    }

    public BookFilter(int idItem) {
        this.idItem = idItem;
    }

    public BookFilter(String name, String author, int volumeNumber) {
        this.name = name;
        this.author = author;
        this.volumeNumber = volumeNumber;
    }

    public BookFilter(Status status) {
        this.status = status;
    }

    public BookFilter(Category category) {
        this.category = category;
    }


    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public Type getItemType() {
        return itemType;
    }

    public void setItemType(Type itemType) {
        this.itemType = itemType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRowLimit() {
        return rowLimit;
    }
}
