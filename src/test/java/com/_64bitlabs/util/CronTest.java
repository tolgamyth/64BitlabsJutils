package com._64bitlabs.util;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by tolga on 21/02/15.
 */
public class CronTest extends TestCase{

    public void testCron(){
        String s = "";
        try {
            s = QuartzExpGenerator.generateCronExpressionForDate(new Date());
            System.out.println(s);
        } catch (ParseException e) {
            e.toString();
        }
    }
}
