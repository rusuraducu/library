package Biblioteca.DAO.LendItem;

import Biblioteca.DAO.Item.Item;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.User.User;

import java.sql.Date;

public class LendItemFilter {

    private Item item;
    private Date lendingDate;
    private Type type;
    private User toUser;
    private User byLibrarian;
    private Date retitutionDate;
    private boolean isLent; // True - Daca se doreste sa se afle cartile care sunt imprumutate momentan.
    private int rowLimit;

    public LendItemFilter(boolean isLent, int rowLimit) {
        this.isLent = isLent;
        this.rowLimit = rowLimit;
    }

    public LendItemFilter(Item item, Type type, boolean isLent) {
        this.item = item;
        this.type = type;
        this.isLent = isLent;
    }

    public Item getItem() {
        return item;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isLent() {
        return isLent;
    }

    public Date getLendingDate() {
        return lendingDate;
    }

    public User getToUser() {
        return toUser;
    }

    public User getByLibrarian() {
        return byLibrarian;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
}
