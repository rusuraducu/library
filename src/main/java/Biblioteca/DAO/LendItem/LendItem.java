package Biblioteca.DAO.LendItem;

import Biblioteca.Actions.CurrentDate;
import Biblioteca.DAO.Item.Item;
import Biblioteca.DAO.User.User;

import java.sql.Date;

public class LendItem {

    private int idLending;
    private Item item;
    private Date lendingDate;
    private int lendingPeriod;
    private User toUser;
    private User byLibrarian;
    private Date retitutionDate;

    public LendItem(Item item, User toUser, User byLibrarian) {
        this.item = item;
        this.lendingDate=new CurrentDate().getCurrentDate();
        this.toUser = toUser;
        this.byLibrarian = byLibrarian;
    }

    public LendItem(int idLending, Item item, Date lendingDate, int lendingPeriod, User toUser, User byLibrarian) {
        this.idLending = idLending;
        this.item = item;
        this.lendingDate=lendingDate;
        this.lendingPeriod = lendingPeriod;
        this.toUser = toUser;
        this.byLibrarian = byLibrarian;
    }

    public LendItem(Item item, Date lendingDate, User toUser, User byLibrarian) {
        this.item = item;
        this.lendingDate=lendingDate;
        this.toUser = toUser;
        this.byLibrarian = byLibrarian;
    }

    public int getIdLending() {
        return idLending;
    }

    public void setIdLending(int idLending) {
        this.idLending = idLending;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(Date lendingDate) {
        this.lendingDate = lendingDate;
    }

    public int getLendingPeriod() {
        return lendingPeriod;
    }

    public void setLendingPeriod(int lendingPeriod) {
        this.lendingPeriod = lendingPeriod;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getByLibrarian() {
        return byLibrarian;
    }

    public void setByLibrarian(User byLibrarian) {
        this.byLibrarian = byLibrarian;
    }

    public Date getRetitutionDate() {
        return retitutionDate;
    }

    public void setRetitutionDate(Date retitutionDate) {
        this.retitutionDate = retitutionDate;
    }

    @Override
    public String toString() {
        return
                item.getName() +
                " LendingDate: " + lendingDate +
                " Period: " + lendingPeriod +
                " To: " + toUser.getUsername() +
                " Librarian: " + byLibrarian.getUsername() +
                " RetitutionDate: " + retitutionDate;
    }
}
