package Biblioteca.DAO.Item;

import Biblioteca.Actions.CurrentDate;
import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.User.User;

import java.sql.Date;


public class Item {
    private int idItem;
    private String name;
    private String author;
    private String description;
    private Category category;
    private Date dateAdded;
    private User addedBy;
    private Type itemType;
    private Status status;


    public Item() { }

    public Item(int idItem, String name, String author, String description, Category category, Date dateAdded, User addedBy, Type itemType, Status status) {
        this.idItem = idItem;
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.dateAdded = dateAdded;
        this.addedBy = addedBy;
        this.itemType = itemType;
        this.status = status;
    }
    public Item(String name, String author, String description, Category category, Date dateAdded, User addedBy, Type itemType, Status status) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.dateAdded = dateAdded;
        this.addedBy = addedBy;
        this.itemType = itemType;
        this.status = status;
    }

    public Item(String name, String author, String description, Category category, User addedBy, Type itemType, Status status) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.dateAdded = new CurrentDate().getCurrentDate();
        this.addedBy = addedBy;
        this.itemType = itemType;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
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

    public Type getItemType() {
        return itemType;
    }


    public void setItemType(Type itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "" +
                " Name: " + name + '\'' +
                " Author: " + author + '\'' +
                " Description: " + description + '\'' +
                " Category: " + category.getCategoryName() +
                " DateAdded: " + dateAdded +
                " AddedBy: " + addedBy.getUsername() +
                " Status: " + status;
    }
}
