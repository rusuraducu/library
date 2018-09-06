package Biblioteca.Presentation.OutputStreams;

import Biblioteca.Presentation.TableFormat;

public class OutputStream {

    protected void setTableHeaderContent(String [] tableFormat){

        System.out.println(tableFormat[0]);
        System.out.println(tableFormat[1]);
        System.out.println(tableFormat[2]);
        System.out.println(tableFormat[3]);
        System.out.println(tableFormat[4]);
        System.out.println(tableFormat[5]);

    }

    protected void setTableBottomBorder(String bottomBorder){

        System.out.println(bottomBorder);

    }

}
