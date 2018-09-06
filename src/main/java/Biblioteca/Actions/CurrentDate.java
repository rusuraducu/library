package Biblioteca.Actions;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDate {

    public Date getCurrentDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime().getTime());
        Date currentDateSQL = Date.valueOf(currentDate);
        return currentDateSQL;

    }
}
