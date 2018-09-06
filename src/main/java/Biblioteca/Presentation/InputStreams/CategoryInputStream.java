package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.CategoryProcessor;
import Biblioteca.Validations.CategoryValidations;

import java.util.Scanner;

public class CategoryInputStream {

    private static CategoryValidations validate = new CategoryValidations();

    public String setCategoryName() {

        boolean readyToReturn = false;

        String categoryName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the category's name: ");
            categoryName = scanner.nextLine();
            if (validate.name(categoryName) != "OK") {
                String validationMessage = validate.name(categoryName);
                System.out.println(validationMessage);
                continue;
            }

            readyToReturn = true;
        }
        return categoryName;
    }

    public void addNewCcategoryToDatabaseUsingScanner(){

        CategoryProcessor categoryProcessor = new CategoryProcessor();

        categoryProcessor.addNewCategoryToDatabase(setCategoryName());
    }
}
