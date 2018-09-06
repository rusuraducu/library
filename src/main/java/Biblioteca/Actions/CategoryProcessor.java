package Biblioteca.Actions;

import Biblioteca.DAO.Category.Category;
import Biblioteca.DAO.Category.CategoryFilter;
import Biblioteca.Queries.CategoryQueries;


public class CategoryProcessor extends Processor {

    private static CategoryQueries QUERIES = new CategoryQueries();

    public static Category loadCategoryInstance(int id) {

        CategoryFilter filter = new CategoryFilter(id);

        Category category = QUERIES.loadCategoryFromDatabase(filter);
        try {
            if (!category.getCategoryName().isEmpty()) {
                return category;
            }
        } catch (NullPointerException e) {
            System.out.println("This category doesn't exist.");
        }
        return null;
    }

    public static Category loadCategoryInstance(String categoryName) {

        CategoryFilter filter = new CategoryFilter(categoryName);

        Category category = QUERIES.loadCategoryFromDatabase(filter);
        try {
            if (!category.getCategoryName().isEmpty()) {
                return category;
            }
        } catch (NullPointerException e) {
            System.out.println("This category doesn't exist.");
        }
        return null;

    }

    public void addNewCategoryToDatabase(String categoryName) {
        Category category = new Category(categoryName);
        if (QUERIES.insertNewCategoryToDatabase(category.getCategoryName()) == 1) {
            print("The book " + category.getCategoryName() + " has been added to database.");
            return;
        }
        print("The book " + category.getCategoryName() + " couldn't be added to database...");

    }

}
