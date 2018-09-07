package Biblioteca.Presentation.InputStreams;

import Biblioteca.Actions.TypeProcessor;
import Biblioteca.Validations.TypeValidations;

import java.util.Scanner;

public class TypeInputStream {

    private TypeValidations validate = new TypeValidations();

    public String setNewItemType() {

        boolean readyToReturn = false;

        String typeName = new String();

        while (!readyToReturn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Add new type to database: ");
            typeName = scanner.nextLine();
            if (validate.typeName(typeName) != "OK") {
                String validationMessage = validate.typeName(typeName);
                System.out.println(validationMessage);
                continue;
            }
            readyToReturn = true;
        }
        return typeName;
    }

    public void addNewTypeToDatabaseUsingScanner(){

        TypeProcessor typeProcessor = new TypeProcessor();

        typeProcessor.addNewItemTypeToDatabase( setNewItemType( ) );
    }
}
