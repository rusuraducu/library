package Biblioteca;

import Biblioteca.DAO.User.User;
import Biblioteca.Presentation.Menu.IMenu;
import Biblioteca.Presentation.Menu.MenuFactory;

public class Demo {
    public static void main(String[] args) {

        /**
         *  Admin:          | Username: Administrator | Password: myPassword |
         *
         *  Librarian       | Username: Librarian     | Password: myPassword |
         */

        User user = Login.getUsernameInstance("Octavian", "myPassword");

        UserOptions userOptions = UserOptions.getUserOptions(user);

        IMenu userMenu = new MenuFactory().getUserMenu(userOptions);

        userMenu.getMenu();
    }
}
/*****
 ****
 * In versiunea demo sunt introduse doar o parte din optiunile pe care le ofera aceasta aplicatie.
 *
 * Pe langa aceaste optiuni din versiunea demo, aplicatia mai ofera si alte optiuni:
 *
 * ---> Pentru admin: -| Modifica detalii utilizator (nume, prenume, parola, email etc.)
 *                    -| Adauga un nou tip de iteme
 *                    -| Listare ranguri
 *                    -| Adauga un nou rang
 *                    -| etc.
 *
 * ---> Pentru bibliotecar: -| Modifica detalii carte (nume, descriere, categorie etc.)
 *                          -| Cautare iteme disponibile utilizand diverse filtre de cautare
 *                          -| Generare rapoarte (Carti Imprumutate, Carti Retrase etc) in consola
 *                          -| Rapoartele pot fi filtrate dupa diverse caracteristici (Data, User, Tip Item etc.)
 *
 * --> Versinea full a acestui program va avea implementate si module pentru gestionarea altor tipuri de date din
 * biblioteca, cum ar fi: Media, Articole, Reviste etc. Baza de date dar si alte clase din cadrul acestui program
 * au fost concepute in asa fel incat sa permita extinderea programului fara prea multe modificari.
 *****
