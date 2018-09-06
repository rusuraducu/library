package Biblioteca.DAO.Category;

public class CategoryFilter {
    private  int id;
    private  String categoryName;
    private int rowLimit;

    public CategoryFilter(int id) {
        this.id = id;
    }

    public CategoryFilter(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
}
