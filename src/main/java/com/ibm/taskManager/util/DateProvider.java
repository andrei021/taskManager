package com.ibm.taskManager.util;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    public static Date getCurrentDate() {
       return Calendar.getInstance().getTime();
    }
}

