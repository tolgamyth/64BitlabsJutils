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
package com._64bitlabs.util.string;

import java.nio.charset.Charset;

/**
 * Created by tolga on 12.04.2016.
 */
public class UnicodeStringUtils {
    /**
     * Contains non ascii boolean.
     *
     * @param stringList the string list
     * @return the boolean
     */
    public static boolean containsNonAscii(String... stringList) {
        boolean returnValue = false;
        for (String s : stringList) {
            if (s != null) {
                returnValue = returnValue == false ? s.length() != s.getBytes(Charset.forName("UTF-8")).length : returnValue;
            }
        }
        return returnValue;
    }

    /**
     * Contains non ascii or nulls boolean.
     *
     * @param stringList the string list
     * @return the boolean
     */
    public static boolean containsNonAsciiOrNulls(String... stringList) {
        boolean returnValue = false;
        for (String s : stringList) {
            if (s == null) {
                returnValue = true;
            } else {
                returnValue = returnValue == false ? s.length() != s.getBytes(Charset.forName("UTF-8")).length : returnValue;
            }
        }
        return returnValue;
    }

    /**
     * String contains non ascii boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public static boolean stringContainsNonAscii(String s) {
        return s != null && s.length() != s.getBytes(Charset.forName("UTF-8")).length;
    }
}
