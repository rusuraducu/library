package Biblioteca.DAO.RetireItem;

import Biblioteca.DAO.Type.Type;

public class RetiredItemsFilter {
    private Type type;
    private RetireReason retireReason;
    private int rowLimit;

    public RetiredItemsFilter() {
    }

    public RetiredItemsFilter(Type type, int rowLimit) {
        this.type = type;
        this.rowLimit = rowLimit;
    }

    public RetiredItemsFilter(RetireReason retireReason, int rowLimit) {
        this.retireReason = retireReason;
        this.rowLimit = rowLimit;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public RetireReason getRetireReason() {
        return retireReason;
    }

    public void setRetireReason(RetireReason retireReason) {
        this.retireReason = retireReason;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
}
