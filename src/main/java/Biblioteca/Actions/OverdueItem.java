package Biblioteca.Actions;

import Biblioteca.DAO.Item.Item;
import Biblioteca.DAO.User.User;

import java.sql.Date;

public class OverdueItem {

    private Item item;
    private User toUser;
    private Date lendingDate;
    private Date returnDate;
    private int overdue;

    public OverdueItem(Item item, User toUser, Date lendingDate, Date returnDate, int overdue) {
        this.item = item;
        this.toUser = toUser;
        this.lendingDate = lendingDate;
        this.returnDate = returnDate;
        this.overdue = overdue;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Date getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(Date lendingDate) {
        this.lendingDate = lendingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return  " Item: " + item.getName() +
                "| Item Type: "+item.getItemType().getTypeName()+
                "| To Student: " + toUser.getUsername() +
                "| Lending Date: " + lendingDate +
                "| Return Date: " + returnDate +
                "| Overdue: " + overdue + " days";
    }
}
