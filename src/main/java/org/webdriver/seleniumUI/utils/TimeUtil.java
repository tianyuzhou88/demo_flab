package org.webdriver.seleniumUI.utils;

import org.openqa.selenium.JavascriptExecutor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/28.
 */
public class TimeUtil extends TestBaseCase {
    /**
     *
     * @param inputName
     * @param time
     * @param timeFormat
     */
    public void timeWidgetMange(String inputName,String time,String timeFormat)
    {
        String date=formatDate(time, timeFormat);
        String js="$(function(){$(\"input[name='"
                + inputName
                +"']\""
                + ").removeAttr('readonly');"
                + "$(\"input[name='"
                + inputName
                +"']\""
                + ").val(\""
                + date
                + "\");"
                + "})";
        ((JavascriptExecutor) driver).executeScript(js);
        System.out.println(js);
    }
    /**
     *
     * @param date
     * @param format
     * @return
     */
    public  static String formatDate(Date date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        System.out.println(formatter.format(date).toString());
        return formatter.format(date).toString();

    }
    /**
     *
     * @param date
     * @param format
     * @return
     */
    public  static String formatDate(String date,String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        String sss = null;
        try {
            sss = sdf2.format(sdf.parse(date));
            //System.out.println(sss);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sss;
    }
    /**
     *
     * @param date
     * @return
     */
    public  static String formatDate(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        return formatter.format(date);
    }
    /**
     *
     * @param date
     * @return
     */
    public  static String formatTime(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
        System.out.println(formatter.format(date));
        return formatter.format(date);
    }

    /**
     *
     * @param start_time
     * @param end_time
     * @return
     */
    public  static long getDaySub(String start_time,String end_time)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long day=0;
        try {
            long start_timeStamp=sdf.parse(start_time).getTime();
            long end_timeStamp=sdf.parse(end_time).getTime();
            day=(end_timeStamp-start_timeStamp)/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  day;


    }
    public  static  void main(String[] args)
    {
        System.out.print(TimeUtil.getDaySub("2016-08-25","2016-08-28"));
       System.out.println(TimeUtil.formatDate("2016/10/12","yyyy/MM/DD"));
    }
}
