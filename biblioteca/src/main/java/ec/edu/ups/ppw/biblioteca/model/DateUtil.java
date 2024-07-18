package ec.edu.ups.ppw.biblioteca.model;

import java.util.Date;
import java.util.Calendar;

public class DateUtil {

    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Los meses en Calendar son 0-based, restamos 1
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
}

