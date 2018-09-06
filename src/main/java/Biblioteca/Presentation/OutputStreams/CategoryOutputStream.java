package Biblioteca.Presentation.OutputStreams;

import Biblioteca.Actions.CategoryProcessor;
import Biblioteca.DAO.Category.Category;

import java.util.Scanner;

public class CategoryOutputStream {

    public Category getCategoryFromDatabaseUsingScanner() {

        Scanner scanner = new Scanner(System.in);
        boolean readyToReturn = false;

        Category category = null;

        while (!readyToReturn) {

            System.out.println("Enter the category's name: ");
            String categoryName = scanner.nextLine();
            category = CategoryProcessor.loadCategoryInstance(categoryName);
            if (category == null) {
                continue;
            }
          readyToReturn = true;
        }
        return category;
    }
}
