package com._64bitlabs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utilities for Quartz Cron expression.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/CronUtil.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.00.00
 */
public class CronUtil {

    private final Date date;
    private final Calendar calendar;
    private final String seconds = "0";
    private final String daysOfWeek = "?";

    private String mins;
    private String hours;
    private String daysOfMonth;
    private String months;
    private String years;

    public CronUtil(Date pDate) {
        this.date = pDate;
        calendar = Calendar.getInstance();
        this.generateCronExpression();
    }

    private void generateCronExpression() {
        calendar.setTime(date);

        String hours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        this.hours = hours;

        String mins = String.valueOf(calendar.get(Calendar.MINUTE));
        this.mins = mins;

        String days = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        this.daysOfMonth = days;

        String months = new java.text.SimpleDateFormat("MM").format(calendar.getTime());
        this.months = months;

        String years = String.valueOf(calendar.get(Calendar.YEAR));
        this.years = years;

    }

    public Date getDate() {
        return date;
    }

    public String getSeconds() {
        return seconds;
    }

    public String getMins() {
        return mins;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getHours() {
        return hours;
    }

    public String getDaysOfMonth() {
        return daysOfMonth;
    }

    public String getMonths() {
        return months;
    }

    public String getYears() {
        return years;
    }

    public static String generateCronExpression(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = dateFormat.format(date);

        Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);

        CronUtil calHelper = new CronUtil(cronDate);
        String cron =   calHelper.getSeconds() + " " +
                calHelper.getMins() + " " +
                calHelper.getHours() + " " +
                calHelper.getDaysOfMonth() + " " +
                calHelper.getMonths() + " " +
                calHelper.getDaysOfWeek() + " " +
                calHelper.getYears();
        System.out.println("Cron Expression " + cron);
        return cron;
    }

}
