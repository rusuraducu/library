package Biblioteca.Validations;

import Biblioteca.Actions.UserProcessor;

public class UserValidations extends Validation {

    public String username(String username) {

        if (isBlankOrNull(username)) {
            return "You have to set the username.";
        }
        if (underMinimumLength(username, 8)) {
            return "The username should contain at leat 8 letters.";
        }
        if (overMaximumLength(username, 15)) {
            return "The username should contain maximum 15 letters.";
        }
        if (!containsLettersAndNumbers(username)) {
            return "The username should contain only letters and numbers.";
        }
        if(UserProcessor.loadUserInstance(username) != null){
            return "This username already exists.";
    }
        return "OK";
    }

    public String firstName(String firstName) {

        if (isBlankOrNull(firstName)) {
            return "You have to set the FirstName.";
        }
        if (underMinimumLength(firstName, 2)) {
            return "The FirstName should contain at least 8 letters.";
        }
        if (overMaximumLength(firstName, 15)) {
            return "The FirstName should contain maximum 15 letters.";
        }
        if (!isWord(firstName)) {
            return "The FirstName doesn't look like a name.";
        }
        return "OK";
    }

    public String lastName(String lastName) {

        if (isBlankOrNull(lastName)) {
            return "You have to set the FirstName.";
        }
        if (underMinimumLength(lastName, 2)) {
            return "The LastName should contain at least 8 letters.";
        }
        if (overMaximumLength(lastName, 15)) {
            return "The LastName should contain maximum 15 letters.";
        }
        if (!isWord(lastName)) {
            return "The LastName doesn't look like a name.";
        }
        return "OK";
    }

    public String password(String password) {

        if (isBlankOrNull(password)) {
            return "The password cannot be null.";
        }
        if (underMinimumLength(password, 8)) {
            return "The password should contain at least 8 letters.";
        }
        if (overMaximumLength(password, 15)) {
            return "The password should contain maximum 15 letters.";
        }
        if (!passwordMatchPattern(password)) {
            return "The password must contain at least one letter, one number and one special character";
        }
        return "OK";
    }

    public String email(String email){
        if(!emailMatchPattern(email)){
            return "This email doesn't seem to be right.";
        }
        return "OK";
    }


}
