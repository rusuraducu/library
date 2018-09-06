package Biblioteca.DAO.Type;

public class TypeFilter {
    private  int id;
    private  String typeName;
    private  int rowLimit;

    public TypeFilter(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public TypeFilter(int id) {
        this.id = id;
    }

    public TypeFilter(String typeName) {
        this.typeName = typeName;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
