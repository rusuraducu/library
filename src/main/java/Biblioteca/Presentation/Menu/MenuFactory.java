package Biblioteca.Presentation.Menu;

import Biblioteca.DAO.Rank.Rank;
import Biblioteca.UserOptions;

public class MenuFactory {

    public IMenu getUserMenu(UserOptions userOptions){

        Rank rank = userOptions.getUser().getRank();

        switch (rank.getRankName()){

            case "Administrator":

                return new AdminMenu(userOptions);

            case "Librarian":

                return new LibrarianMenu(userOptions);

        }
        return null;
    }

}
