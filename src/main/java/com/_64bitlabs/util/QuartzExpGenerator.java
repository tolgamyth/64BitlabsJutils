/*
 * Copyright (C) 2014-2016 Tolga Yilmaz
 * info@64bitlabs.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See LICENSE.txt for details.
 */

package com._64bitlabs.util;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import org.quartz.CronExpression;
import org.quartz.core.QuartzScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilities for Quartz Cron expression.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/QuartzExpGenerator.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.0.0
 */
public class QuartzExpGenerator {

    private static final String seconds = "0";
    private static final String daysOfWeek = "?";
    private static String expression;

    private QuartzExpGenerator() {
    }

    public static String generateCronExpressionForDate(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = dateFormat.format(date);
        Date cronDate = dateFormat.parse(dt);

        calendar.setTime(cronDate);

        String mins = String.valueOf(calendar.get(Calendar.MINUTE));
        String hours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String days = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String months = new java.text.SimpleDateFormat("MM").format(calendar.getTime());
        String years = String.valueOf(calendar.get(Calendar.YEAR));

        String cron =   seconds + " " + mins + " " + hours + " " + days + " " +
                        months + " " + daysOfWeek + " " + years;
        return cron;
    }

    public static String forEveryMinute(){
        return "0 0/1 * 1/1 * ? *";
    }

    public static String forEveryMinuteAtSecond(int second) throws ParseException {
        String exp;
        CronExpression.validateExpression(exp = (second%60 + " 0/1 * 1/1 * ? *"));
        return exp;
    }

    public static String forEveryHour(){
        return "0 0 0/1 1/1 * ? *";
    }

    public static String forEveryHourAtMin(int minute) throws ParseException {
        String exp;
        CronExpression.validateExpression(exp = ("0 " + minute%60 +" 0/1 1/1 * ? *"));
        return exp;
    }

    public static String forEveryDay(){
        return "0 0 0 1/1 * ? *";
    }

    public static String forEveryDayAtHour(int hour) throws ParseException {
        String exp;
        CronExpression.validateExpression(exp = ("0 0 "+ hour%24+" 1/1 * ? *"));
        return exp;
    }

    public static String everyMonday(){
        return "0 0 0 ? * MON *";
    }

    public static String everyTuesday(){
        return "0 0 0 ? * TUE *";
    }

    public static String getCronRegex()
    {
        String cronRegex = "";
        if (cronRegex == null)
        {
            // numbers intervals and regex
            TMap<String, String> numbers = new THashMap<>();
            numbers.put("sec", "[0-5]?\\d");
            numbers.put("min", "[0-5]?\\d");
            numbers.put("hour", "[01]?\\d|2[0-3]");
            numbers.put("day", "0?[1-9]|[12]\\d|3[01]");
            numbers.put("month", "[1-9]|1[012]");
            numbers.put("dow", "[0-6]");
            numbers.put("year", "|\\d{4}");

            TMap<String, String> field_re = new THashMap<>();

            // expand regex to contain different time specifiers
            for (String field : numbers.keySet())
            {
                String number = numbers.get(field);
                String range = "(?:" + number + ")(?:(?:-|\\/|\\," +  ("dow".equals(field)? "|#" :    "") +

                        ")(?:" + number + "))?" +  ("dow".equals(field)? "(?:L)?" : ("month".equals(field)? "(?:L|W)?" : ""));
                field_re.put(field, "\\?|\\*|" + range + "(?:," + range + ")*");
            }

            // add string specifiers
            String monthRE = field_re.get("month");
            String monthREVal =   "JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC";
            String monthRERange = "(?:" + monthREVal + ")(?:(?:-)(?:" + monthREVal + "))?" ;
            monthRE = monthRE +  "|\\?|\\*|" + monthRERange + "(?:," + monthRERange + ")*" ;
            field_re.put("month", monthRE);

            String dowRE = field_re.get("dow");
            String dowREVal = "MON|TUE|WED|THU|FRI|SAT|SUN";
            String dowRERange = "(?:" + dowREVal + ")(?:(?:-)(?:" + dowREVal + "))?" ;

            dowRE = dowRE + "|\\?|\\*|" + dowRERange + "(?:," + dowRERange + ")*" ;
            field_re.put("dow", dowRE);

            StringBuilder fieldsReSB = new StringBuilder();
            fieldsReSB.append("^\\s*(").append("$") //
                    .append("|#") //
                    .append("|\\w+\\s*=") //
                    .append("|") //
                    .append("(")//
                    .append(field_re.get("sec")).append(")\\s+(")//
                    .append(field_re.get("min")).append(")\\s+(")//
                    .append(field_re.get("hour")).append(")\\s+(")//
                    .append(field_re.get("day")).append(")\\s+(")//
                    .append(field_re.get("month")).append(")\\s+(")//
                    .append(field_re.get("dow")).append(")(|\\s)+(")//
                    .append(field_re.get("year"))//
                    .append(")")//
                    .append(")")//
                    .append("$");

            cronRegex = fieldsReSB.toString();
        }
        return cronRegex;
    }

}
