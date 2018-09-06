package Biblioteca.Presentation;

public class TableFormat {

    public String [] getTableFormatForLentBooksWhichAreOverdue(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+------------------------+-----------+--------------+--------------+--------------+-------------+%n");
        tableFormat[1] = String.format("| Item                   | Type      | To Student   | Lending Date | Return Date  | Overdue     |%n");
        tableFormat[2] = String.format("+------------------------+-----------+--------------+--------------+--------------+-------------+%n");
        tableFormat[3] = String.format("|           1            |     2     |       3      |       4      |       5      |       6     |%n");
        tableFormat[4] = String.format("+------------------------+-----------+--------------+--------------+--------------+-------------+%n");
        tableFormat[5] = String.format("+------------------------+-----------+--------------+--------------+--------------+-------------+%n");

        return tableFormat;
    }

    public String [] getTableFormatForLentBooks(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+------------------------+----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[1] = String.format("| Item                   | Lending Date   | Period       | To Student   | By Librarian | Return Date  |%n");
        tableFormat[2] = String.format("+------------------------+----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[3] = String.format("|           1            |        2       |       3      |      4       |       5      |       6      |%n");
        tableFormat[4] = String.format("+------------------------+----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[5] = String.format("+------------------------+----------------+--------------+--------------+--------------+--------------+%n");

        return tableFormat;
    }

    public String [] getTableFormatForUsersList(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+-----------------+------------------------+-----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[1] = String.format("| Username        | Email                  | Rank            | Joined       | First Name   | Last Name    | Status       +%n");
        tableFormat[2] = String.format("+-----------------+------------------------+-----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[3] = String.format("|         1       |            2           |        3        |      4       |       5      |       6      |       7      |%n");
        tableFormat[4] = String.format("+-----------------+------------------------+-----------------+--------------+--------------+--------------+--------------+%n");
        tableFormat[5] = String.format("+-----------------+------------------------+-----------------+--------------+--------------+--------------+--------------+%n");

        return tableFormat;
    }

    public String [] getTableFormatForBooksList(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+------------------------+----------------------+-----------------------------+-----------------+--------------+--------+---------+------------+%n");
        tableFormat[1] = String.format("| Item                   | Author               | Description                 | Date Added      | Added By     | Volume | NoPages | Status     |%n");
        tableFormat[2] = String.format("+------------------------+----------------------+-----------------------------+-----------------+--------------+--------+---------+------------+%n");
        tableFormat[3] = String.format("|            1           |            2         |              3              |        4        |       5      |    6   |    7    |      8     |%n");
        tableFormat[4] = String.format("+------------------------+----------------------+-----------------------------+-----------------+--------------+--------+---------+------------|%n");
        tableFormat[5] = String.format("+------------------------+----------------------+-----------------------------+-----------------+--------------+--------+---------+------------|%n");

        return tableFormat;
    }


    public String [] getTableFormatForCategories(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+------------------------+%n");
        tableFormat[1] = String.format("| Categories             |%n");
        tableFormat[2] = String.format("+------------------------+%n");
        tableFormat[3] = String.format("|            1           |%n");
        tableFormat[4] = String.format("+------------------------|%n");
        tableFormat[5] = String.format("+------------------------|%n");

        return tableFormat;
    }

    public String [] getTableFormatForItemType(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+----------------+%n");
        tableFormat[1] = String.format("| Types          |%n");
        tableFormat[2] = String.format("+----------------+%n");
        tableFormat[3] = String.format("|        1       |%n");
        tableFormat[4] = String.format("+----------------|%n");
        tableFormat[5] = String.format("+----------------|%n");

        return tableFormat;
    }

    public String [] getTableFormatForUserRanks(){

        String [] tableFormat = new String[6];
        tableFormat[0] = String.format("+----------------+%n");
        tableFormat[1] = String.format("| Ranks          |%n");
        tableFormat[2] = String.format("+----------------+%n");
        tableFormat[3] = String.format("|        1       |%n");
        tableFormat[4] = String.format("+----------------|%n");
        tableFormat[5] = String.format("+----------------|%n");

        return tableFormat;
    }


}
