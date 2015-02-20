package com._64bitlabs.util.regex;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tolga on 20.02.2015.
 */
public class RegExpBuilderTest extends TestCase {

    public void testEmailRegex(){
        Pattern p = Pattern.compile(RegExpBuilder.EMAIL_PATTERN);
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
        Pattern p = Pattern.compile(RegExpBuilder.DOMAINNAME_PATTERN);
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

}
