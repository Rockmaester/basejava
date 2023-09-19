package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {

    public static LocalDate of(int year, Month month){
        // Будет использоваться год и месяц. Единица ставится для заполнения конструктора.
        return LocalDate.of(year, month, 1);
    }
}
