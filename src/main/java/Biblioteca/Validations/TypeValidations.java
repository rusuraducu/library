package Biblioteca.Validations;

import Biblioteca.Actions.TypeProcessor;

public class TypeValidations extends Validation {

    public String typeName(String typeName) {

        if (isBlankOrNull(typeName)) {
            return "You must set the type's name.";
        }
        if (overMaximumLength(typeName, 20)) {
            return "The type's name can have maximum 20 characters.";
        }
        if (underMinimumLength(typeName, 2)) {
            return "The type's name can have at least 2 characters.";
        }
        if (overMaximumLimitOfWords(typeName, 3)) {
            return "The type's name can have maximum 3 words.";
        }
        if(!isWord(typeName)){
            return "The type's name must contain only letters.";
        }
        if (TypeProcessor.loadTypeInstance(typeName) != null) {
            return "There is already a type name with this name.";
        }

        return "OK";
    }
}
