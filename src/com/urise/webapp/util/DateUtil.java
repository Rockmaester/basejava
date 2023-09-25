package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {

    /* Special case: заведение константы с гарантировано недостижимым будущим. Будет использовано для обозначения статуса
    вида "до сих пор работает в этой организации и тп.". Делается для того, чтобы проверки в диапазон работали и не
    приходилось бы вставлять null и добавлять проверки на null */
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month){
        // Будет использоваться год и месяц. Единица ставится для заполнения конструктора.
        return LocalDate.of(year, month, 1);
    }
}
