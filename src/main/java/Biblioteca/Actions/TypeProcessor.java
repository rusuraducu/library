package Biblioteca.Actions;

import Biblioteca.DAO.Type.Type;
import Biblioteca.Queries.TypeQueries;

public class TypeProcessor extends Processor {

    static TypeQueries QUERIES = new TypeQueries();

    public static Type loadTypeInstance(int id) {

        Type type = QUERIES.loadType(id);

        try {
            if (!type.getTypeName().isEmpty()) {
                return type;
            }

        } catch (NullPointerException e) {
            System.out.println("This type doesn't exist.");
            return null;
        }
        return null;
    }

    public static Type loadTypeInstance(String typeName) {

        Type type = QUERIES.loadType(typeName);

        try {
            if (!type.getTypeName().isEmpty()) {
                return type;
            }
        } catch (NullPointerException e) { return null; }

        return type;
    }

    public void addNewItemTypeToDatabase(String typeName) {

        Type  type = new Type(typeName);

        if (QUERIES.insertNewItemTypeToDatabase(type.getTypeName()) == 1) {

            print("The item type '" + type.getTypeName() + "' has been added to database.");

            return;
        }
        print("The item type '" + type.getTypeName() + "' couldn't be added to database.");
    }

}
