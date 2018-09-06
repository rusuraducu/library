package Biblioteca.Actions;

import Biblioteca.Validations.Validation;

import java.util.List;

public class Processor {

    public static Validation validate = new Validation();

    //Afiseaza in consola orice tip de lista

    public <T> void printList(List<T> collection){
        for (T c:collection){
            System.out.println(c);
        }
    }


    //Printeaza la consola un mesaj. Metoda utila pentru un cod mai curant in cadrul instructiunilor "if".

    public void print(String message){
        System.out.println(message);
    }
}
