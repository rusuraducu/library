package Biblioteca.Validations;

import Biblioteca.Actions.CategoryProcessor;

public class CategoryValidations extends Validation {

    public String name(String categoryName) {

        if (isBlankOrNull(categoryName)) {
            return "You must set the category's name.";
        }
        if (overMaximumLength(categoryName, 20)) {
            return "The category's name can have maximum 20 characters.";
        }
        if (underMinimumLength(categoryName, 2)) {
            return "The category's name can have at least 2 characters.";
        }
        if (overMaximumLimitOfWords(categoryName, 5)) {
            return "The category's name can have maximum 5 words.";
        }
        if(!containsLettersAndNumbers(categoryName)){
            return "The category's name must contain only letters and numbers.";
        }
        if (CategoryProcessor.loadCategoryInstance(categoryName) != null) {
            return "There is already a category with this name.";
        }

        return "OK";
    }
}
