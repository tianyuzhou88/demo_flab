package org.webdriver.seleniumUI.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestTime {

    private String timeFormat;
    private String timeZone;

    public TestTime(String timeFormat, String timeZone) {
        this.timeFormat = timeFormat;
        this.timeZone = timeZone;
    }

    public String getTestTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(timeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(date);
    }
}
