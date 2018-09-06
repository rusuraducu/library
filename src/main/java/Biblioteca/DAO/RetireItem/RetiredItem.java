package Biblioteca.DAO.RetireItem;

import Biblioteca.Actions.CurrentDate;
import Biblioteca.DAO.Item.Item;
import Biblioteca.DAO.Type.Type;
import Biblioteca.DAO.User.User;

import java.util.Date;

public class RetiredItem {

    private int idRetiredItem;
    private Item item;
    private Type type;
    private User retiredBy;
    private RetireReason retireReason;
    private Date date;

    public RetiredItem(Item item, Type type,User retiredBy, RetireReason retireReason) {
        this.item = item;
        this.type = type;
        this.retiredBy = retiredBy;
        this.retireReason = retireReason;
        this.date = new CurrentDate().getCurrentDate();
    }

    public RetiredItem(int idRetiredItem, Item item, Type type, RetireReason retireReason, Date date) {
        this.idRetiredItem = idRetiredItem;
        this.item = item;
        this.type = type;
        this.retireReason = retireReason;
        this.date = date;
    }

    @Override
    public String toString() {
        return "RetiredItem{" +
                "idRetiredItem=" + idRetiredItem +
                ", item=" + item.getName() +
                ", type=" + type.getTypeName() +
                ", retireReason=" + retireReason +
                ", date=" + date +
                '}';
    }
}
