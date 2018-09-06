package Biblioteca.Validations;

import javax.sql.rowset.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {

    public boolean isStringOfLetters(String firstName) {
        String regex = "[a-zA-Z ]*";
        if (firstName.matches(regex)) {
            return true;
        }
        return false;
    }

    public  boolean emailMatchPattern(String email) {
        String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (email.matches(emailPattern)) {
            return true;
        }
        return false;
    }

    //Lita maxima de cuvinte
    public boolean overMaximumLimitOfWords(String string, int maximumNumberOfWords) {

        if (string.split(" ").length > maximumNumberOfWords) {
            return true;
        }
        return false;
    }

    public boolean isBlankOrNull(String str) {
        if (str.isEmpty() || str == null) {
            System.out.println(str);
            return true;
        }
        return false;
    }


    public boolean containsLettersAndNumbers(String username) {
        String usernameRegex = "^[a-zA-Z0-9_.-]*$";
        if (!username.matches(usernameRegex)) {
            return false;
        }
        return true;
    }

    public boolean overMaximumLength(String string, int maximumLength) {
        if (string.length() > maximumLength) {
            return true;
        }
        return false;
    }

    public boolean underMinimumLength(String string, int minimumLength) {
        if (string.length() < minimumLength) {
            return true;
        }
        return false;
    }

    public boolean lessThan(int number, final int lowerLimit) {
        if (number < lowerLimit) {
            return true;
        }
        return false;
    }

    public boolean greaterThan(int number, int upperLimit) {
        if (number > upperLimit) {
            return true;
        }
        return false;
    }

    public boolean parseException(String str) {
        try {
            Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    //Minimum eight characters, at least one letter, one number and one special character:

    public boolean passwordMatchPattern(String password) {

        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        if (password.matches(passwordRegex)) {
            return true;
        }
        return false;
    }

    public Boolean underMinimumLimitOfWords(String words, int limit) {
        if (words.split(" ").length < limit) {
            return true;
        }
        return false;
    }

    public boolean isWord(String string) {
        String regexWordsAndSpaces = "^[a-zA-Z]*$";
        if (string.matches(regexWordsAndSpaces)) {
            return true;
        }
        return false;
    }


}
