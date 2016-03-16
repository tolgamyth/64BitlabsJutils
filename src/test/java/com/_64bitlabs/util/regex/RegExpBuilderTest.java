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

package com._64bitlabs.util.regex;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tolga Yilmaz on 20.02.2015.
 */
public class RegExpBuilderTest extends TestCase {

    public void testEmailRegex(){
        Pattern p = Pattern.compile(CommonRegex.EMAIL_PATTERN.toString());
        for(String email : ValidEmailProvider()){
            Matcher m = p.matcher(email);
            assertEquals(true,m.find());
        }
        for(String email : InvalidEmailProvider()){
            Matcher m = p.matcher(email);
            assertEquals(false,m.find());
        }
    }

    private String[] ValidEmailProvider() {
        return new String[] { "email@yahoo.com",
                "email-100@yahoo.com.tr", "email.100@yahoo.com",
                "email111@email.com", "email-100@email.net",
                "email.100@email.com.au", "email@ççç1.Çom",
                "email@gmail.com.com", "email+100@gmail.com",
                "email-100@yahoo-test.com" };
    }

    private String[] InvalidEmailProvider() {
        return new String[] { "email", "email@.com.my",
                "email123@gmail.a", "email123@.com", "email123@.com.com",
                ".email@email.com", "email()*@gmail.com", "email@%*.com",
                "email..2002@gmail.com", "email.@gmail.com",
                "email@email@gmail.com", "email@gmail.com.1a" };
    }

    public void testDomainRegex(){
        Pattern p = Pattern.compile(CommonRegex.DOMAIN_NAME_PATTERN.toString());
        for(String domain : ValidDomainNameProvider()){
            Matcher m = p.matcher(domain);
            assertEquals(true, m.find());
        }
        for(String domain : InvalidDomainNameProvider()){
            Matcher m = p.matcher(domain);
            assertEquals(false,m.find());
        }
    }

    private String[] ValidDomainNameProvider() {
        return new String[] { "yahoo.com",
                "google.com.tr", "test-valid.yahoo.com",
                "double--dom.abc.com", "hebe1223.net",
                "666.com.au", "434-33--2.Çom",
                "com.com", "şğıüu.com",
                "1-334ğ.com"
        };
    }

    private String[] InvalidDomainNameProvider() {
        return new String[] { "invalid", "--invalid.com.my","invalid.c",
                "-invalid.net", "invalid-.com", "--cvsdsds.com", "invalidchar!s.com","?invalid.net",
                "1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1234567890.com"
        };
    }

    public void testPhoneRegex(){
        Pattern p = Pattern.compile(CommonRegex.PHONE_PATTERN.toString());
        for(String phone : ValidPhoneProvider()){
            Matcher m = p.matcher(phone);
            System.out.println("Valid " + phone + " " +m.find());
            //assertEquals(true,m.find());
        }
        for(String phone : InvalidPhoneProvider()){
            Matcher m = p.matcher(phone);
            System.out.println("Inalid " + phone + " " +m.find());
            //assertEquals(false,m.find());
        }
    }

    private String[] InvalidPhoneProvider() {
        return new String[] {
                "3-44-12121-32",
                "031223312(22)",
                "0312--333-22-33",
                "------",
                "3---211",
                "2-3-4-4-2-1",
                "321--33--1--22--3"
        };
    }

    private String[] ValidPhoneProvider() {
        return new String[] {
                "+90(322)9998877",
                "+90(322)999-88-77",
                "0(322)999-88-77",
                "03229998877",
                "(0312)9991188",
                "(0312)999-1188"
        };
    }

}
