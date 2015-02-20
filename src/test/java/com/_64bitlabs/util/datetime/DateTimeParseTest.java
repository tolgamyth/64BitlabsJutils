/*
 * Copyright (C) 2010-2011 Tolga Yilmaz
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
package com._64bitlabs.util.datetime;

import java.util.TimeZone;
import java.util.Locale;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import com._64bitlabs.util.datetime.DateTimeParse;
import com._64bitlabs.util.datetime.YearExtensionAround;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.08.00
 */
public class DateTimeParseTest extends TestCase {

	public void testNull(){
		assertNull(parse(null));
	}

	public void testEmpty(){
		assertNull(parse(""));
	}

	public void testNotDateFoo(){
		assertNull(parse("foo"));
	}

	public void testTooLongForYear(){
		assertNull(parse("10000"));
		assertNull(parse("999999"));
	}

	public void testNotDateBadMonthName(){
		assertNull(new DateTimeParse().parse("1 festival, 1032"));
	}

	public void testYyyyMmDdObvious(){
		assertJustDateEquals("1982-11-30", parse("1982-11-30"));
	}

	public void testYyyyDdMmObvious(){
		assertJustDateEquals("2012-03-15", parse("2012-15-03"));
	}

	public void testDdMmYyyyObvious(){
		assertJustDateEquals("1676-02-13", parse("13-02-1676"));
	}

	public void testMmDdYyyyObvious(){
		assertJustDateEquals("1676-02-13", parse("02-13-1676"));
	}

	public void testSlashesMmDdYyyyObvious(){
		assertJustDateEquals("1998-11-23", parse("11/23/1998"));
	}

	public void testDotsMmDdYyyyObvious(){
		assertJustDateEquals("2005-12-25", parse("25.12.2005"));
	}

	public void testDotsMmDdYyyyAposObvious(){
		assertJustDateEquals("1992-12-25", parse("25.12.'92"));
	}

	public void testOrdinals(){
		assertJustDateEquals("1900-01-01", parse("Jan 1st 1900"));
		assertJustDateEquals("1900-01-02", parse("Jan 2nd 1900"));
		assertJustDateEquals("1900-01-03", parse("Jan 3rd 1900"));
		assertJustDateEquals("1900-01-04", parse("Jan 4th 1900"));
		assertJustDateEquals("1900-01-05", parse("Jan 5th 1900"));
		assertJustDateEquals("1900-01-06", parse("Jan 6th 1900"));
		assertJustDateEquals("1900-01-07", parse("Jan 7th 1900"));
		assertJustDateEquals("1900-01-08", parse("Jan 8th 1900"));
		assertJustDateEquals("1900-01-09", parse("Jan 9th 1900"));
		assertJustDateEquals("1900-01-10", parse("Jan 10th 1900"));
		assertJustDateEquals("1900-01-11", parse("Jan 11th 1900"));
		assertJustDateEquals("1900-01-12", parse("Jan 12th 1900"));
		assertJustDateEquals("1900-01-13", parse("Jan 13th 1900"));
		assertJustDateEquals("1900-01-14", parse("Jan 14th 1900"));
		assertJustDateEquals("1900-01-15", parse("Jan 15th 1900"));
		assertJustDateEquals("1900-01-16", parse("Jan 16th 1900"));
		assertJustDateEquals("1900-01-17", parse("Jan 17th 1900"));
		assertJustDateEquals("1900-01-18", parse("Jan 18th 1900"));
		assertJustDateEquals("1900-01-19", parse("Jan 19th 1900"));
		assertJustDateEquals("1900-01-20", parse("Jan 20th 1900"));
		assertJustDateEquals("1900-01-21", parse("Jan 21st 1900"));
		assertJustDateEquals("1900-01-22", parse("Jan 22nd 1900"));
		assertJustDateEquals("1900-01-23", parse("Jan 23rd 1900"));
		assertJustDateEquals("1900-01-24", parse("Jan 24th 1900"));
		assertJustDateEquals("1900-01-25", parse("Jan 25th 1900"));
		assertJustDateEquals("1900-01-26", parse("Jan 26th 1900"));
		assertJustDateEquals("1900-01-27", parse("Jan 27th 1900"));
		assertJustDateEquals("1900-01-28", parse("Jan 28th 1900"));
		assertJustDateEquals("1900-01-29", parse("Jan 29th 1900"));
		assertJustDateEquals("1900-01-30", parse("Jan 30th 1900"));
		assertJustDateEquals("1900-01-31", parse("Jan 31st 1900"));
	}

	public void testYearExtend(){
		assertJustDateEquals("1999-01-01", parse("Jan 1st 99"));
	}

	public void testFullMonthDayYear(){
		assertJustDateEquals("1545-01-01", parse("January 1, 1545"));
		assertJustDateEquals("1545-02-01", parse("February 1, 1545"));
		assertJustDateEquals("1545-03-01", parse("March 1, 1545"));
		assertJustDateEquals("1545-04-01", parse("April 1, 1545"));
		assertJustDateEquals("1545-05-01", parse("May 1, 1545"));
		assertJustDateEquals("1545-06-01", parse("June 1, 1545"));
		assertJustDateEquals("1545-07-01", parse("July 1, 1545"));
		assertJustDateEquals("1545-08-01", parse("August 1, 1545"));
		assertJustDateEquals("1545-09-01", parse("September 1, 1545"));
		assertJustDateEquals("1545-10-01", parse("October 1, 1545"));
		assertJustDateEquals("1545-11-01", parse("November 1, 1545"));
		assertJustDateEquals("1545-12-01", parse("December 1, 1545"));
	}

	public void testPartMonthDayYear(){
		assertJustDateEquals("1545-01-01", parse("Jan 1, 1545"));
		assertJustDateEquals("1545-02-01", parse("Feb 1, 1545"));
		assertJustDateEquals("1545-03-01", parse("Mar 1, 1545"));
		assertJustDateEquals("1545-04-01", parse("Apr 1, 1545"));
		assertJustDateEquals("1545-05-01", parse("May 1, 1545"));
		assertJustDateEquals("1545-06-01", parse("Jun 1, 1545"));
		assertJustDateEquals("1545-07-01", parse("Jul 1, 1545"));
		assertJustDateEquals("1545-08-01", parse("Aug 1, 1545"));
		assertJustDateEquals("1545-09-01", parse("Sep 1, 1545"));
		assertJustDateEquals("1545-10-01", parse("Oct 1, 1545"));
		assertJustDateEquals("1545-11-01", parse("Nov 1, 1545"));
		assertJustDateEquals("1545-12-01", parse("Dec 1, 1545"));
	}

	public void testPartMonthPeriodDayYear(){
		assertJustDateEquals("1545-02-01", parse("Feb. 1, 1545"));
	}

	public void testFullMonthDayYearDayFirst(){
		assertJustDateEquals("1232-07-04", parse("4 July 1232"));
	}

	public void testPartMonthDayYearDayFirst(){
		assertJustDateEquals("2028-01-26", parse("26 Jan 2028"));
	}

	public void testOrdinalsWords(){
		assertJustDateEquals("1900-01-01", parse("Jan First 1900"));
		assertJustDateEquals("1900-01-02", parse("Jan Second 1900"));
		assertJustDateEquals("1900-01-03", parse("Jan Third 1900"));
		assertJustDateEquals("1900-01-04", parse("Jan Fourth 1900"));
		assertJustDateEquals("1900-01-05", parse("Jan Fifth 1900"));
		assertJustDateEquals("1900-01-06", parse("Jan Sixth 1900"));
		assertJustDateEquals("1900-01-07", parse("Jan Seventh 1900"));
		assertJustDateEquals("1900-01-08", parse("Jan Eighth 1900"));
		assertJustDateEquals("1900-01-09", parse("Jan Ninth 1900"));
		assertJustDateEquals("1900-01-10", parse("Jan Tenth 1900"));
		assertJustDateEquals("1900-01-11", parse("Jan Eleventh 1900"));
		assertJustDateEquals("1900-01-12", parse("Jan Twelfth 1900"));
		assertJustDateEquals("1900-01-13", parse("Jan Thirteenth 1900"));
		assertJustDateEquals("1900-01-14", parse("Jan Fourteenth 1900"));
		assertJustDateEquals("1900-01-15", parse("Jan Fifteenth 1900"));
		assertJustDateEquals("1900-01-16", parse("Jan Sixteenth 1900"));
		assertJustDateEquals("1900-01-17", parse("Jan Seventeenth 1900"));
		assertJustDateEquals("1900-01-18", parse("Jan Eighteenth 1900"));
		assertJustDateEquals("1900-01-19", parse("Jan Ninteenth 1900"));
		assertJustDateEquals("1900-01-20", parse("Jan Twentieth 1900"));
		assertJustDateEquals("1900-01-21", parse("Jan Twenty-first 1900"));
		assertJustDateEquals("1900-01-22", parse("Jan Twenty-second 1900"));
		assertJustDateEquals("1900-01-23", parse("Jan Twenty-third  1900"));
		assertJustDateEquals("1900-01-24", parse("Jan Twenty-fourth 1900"));
		assertJustDateEquals("1900-01-25", parse("Jan Twenty-fifth 1900"));
		assertJustDateEquals("1900-01-26", parse("Jan Twenty-sixth 1900"));
		assertJustDateEquals("1900-01-27", parse("Jan Twenty-seventh 1900"));
		assertJustDateEquals("1900-01-28", parse("Jan Twenty-eighth 1900"));
		assertJustDateEquals("1900-01-29", parse("Jan Twenty-ninth 1900"));
		assertJustDateEquals("1900-01-30", parse("Jan Thirtieth 1900"));
		assertJustDateEquals("1900-01-31", parse("Jan Thirty-first 1900"));
	}

	public void testOrdinalsCaseInsensitive(){
		assertJustDateEquals("1900-01-28", parse("Jan 28Th 1900"));
		assertJustDateEquals("1900-01-28", parse("Jan 28TH 1900"));
		assertJustDateEquals("1900-01-28", parse("Jan 28tH 1900"));
		assertJustDateEquals("1900-01-02", parse("Jan second 1900"));
		assertJustDateEquals("1900-01-02", parse("Jan SECOND 1900"));
		assertJustDateEquals("1900-01-02", parse("Jan SeCoNd 1900"));
	}

	public void testOrdinalOrder(){
		assertJustDateEquals("1900-01-02", parse("Jan 1900 2nd"));
		assertJustDateEquals("1900-01-02", parse("2nd Jan 1900"));
		assertJustDateEquals("1900-01-02", parse("2nd 1900 Jan"));
		assertJustDateEquals("1900-01-02", parse("1900 2nd Jan"));
		assertJustDateEquals("1900-01-02", parse("1900 Jan 2nd"));
	}

	public void testOrdinalOrderNonObvious(){
		assertJustDateEquals("1900-01-02", parse("1 2nd 1900"));
		assertJustDateEquals("1900-01-02", parse("1 1900 2nd"));
		assertJustDateEquals("1900-01-02", parse("2nd 1 1900"));
		assertJustDateEquals("1900-01-02", parse("2nd 1900 1"));
		assertJustDateEquals("1900-01-02", parse("1900 2nd 1"));
		assertJustDateEquals("1900-01-02", parse("1900 1 2nd"));
	}

	public void testObviousYearExtension(){
		assertJustDateEquals("1976-02-03", parse("Feb 3 76"));
	}

	public void testYearExtensionApos(){
		assertJustDateEquals("1976-02-03", parse("Feb 3 '76"));
	}

	public void testYearExtensionAposMakesItObvious(){
		assertJustDateEquals("1902-01-31", parse("'02 31 1"));
		assertJustDateEquals("1902-01-31", parse("31 1 '02"));
		assertJustDateEquals("1902-01-31", parse("31 '02 1"));
	}

	public void testYearExtensionSmartApos(){
		assertJustDateEquals("1976-02-03", parse("Feb 3 \u821676"));
		assertJustDateEquals("1976-02-03", parse("Feb 3 \u821776"));
	}

	public void testJustYear(){
		assertJustDateEquals("1988-01-01", parse("1988"));
	}

	public void testJustYearTwoDigit(){
		assertJustDateEquals("1987-01-01", parse("87"));
	}

	public void testJustYearApos(){
		assertJustDateEquals("1908-01-01", parse("'08"));
	}

	public void testJustMonth(){
		assertJustDateEquals("1981-01-01", parse("January"));
		assertJustDateEquals("1981-02-01", parse("Feb"));
	}

	public void testJustMonthAndDay(){
		assertJustDateEquals("1981-01-01", parse("January 1st"));
		assertJustDateEquals("1981-02-28", parse("Feb 28th"));
	}

	public void testIgnoreWeekdayWords(){
		assertJustDateEquals("1999-01-01", parse("Monday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Tuesday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Wednesday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Thursday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Friday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Saturday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Sunday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Mon, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Tue, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Wed, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Thu, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Fri, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Sat, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Sun, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Mo, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Tu, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("We, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Th, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Fr, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Sa, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("Su, Jan 1st 1999"));
	}

	public void testIgnoreWeekdayWordsCaseInsensitive(){
		assertJustDateEquals("1999-01-01", parse("monday, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("MONDAY, Jan 1st 1999"));
		assertJustDateEquals("1999-01-01", parse("MoNdAy, Jan 1st 1999"));
	}

	public void testIgnoreWeekdayWordsAbbreviated(){
		assertJustDateEquals("1999-01-01", parse("mon., Jan 1st 1999"));
	}

	public void testFailOnFakeWeekdayWords(){
		assertNull(parse("Workday, Jan 1st 1999"));
	}

	public void testFieldOrderMonthDayYear(){
		assertJustDateEquals("1903-01-02", parseMonthDayYear("1 2 3"));
	}

	public void testFieldOrderDayMonthYear(){
		assertJustDateEquals("1903-02-01", parseDayMonthYear("1 2 3"));
	}

	public void testFieldOrderMonthYearDay(){
		assertJustDateEquals("1902-01-03", parseMonthYearDay("1 2 3"));
	}

	public void testFieldOrderDayYearMonth(){
		assertJustDateEquals("1902-03-01", parseDayYearMonth("1 2 3"));
	}

	public void testFieldOrderYearMonthDay(){
		assertJustDateEquals("1901-02-03", parseYearMonthDay("1 2 3"));
	}

	public void testFieldOrderYearDayMonth(){
		assertJustDateEquals("1901-03-02", parseYearDayMonth("1 2 3"));
	}

	public void testMonthDayOrderMonthDayYear(){
		assertJustDateEquals("1903-01-02", parseMonthDayYear("1 2 1903"));
		assertJustDateEquals("1903-01-02", parseMonthDayYear("1 1903 2"));
	}

	public void testMonthDayOrderDayMonthYear(){
		assertJustDateEquals("1903-02-01", parseDayMonthYear("1 2 1903"));
		assertJustDateEquals("1903-02-01", parseDayMonthYear("1 1903 2"));
	}

	public void testMonthDayOrderMonthYearDay(){
		assertJustDateEquals("1903-01-02", parseMonthYearDay("1 2 1903"));
		assertJustDateEquals("1903-01-02", parseMonthYearDay("1 1903 2"));
	}

	public void testMonthDayOrderDayYearMonth(){
		assertJustDateEquals("1903-02-01", parseDayYearMonth("1 2 1903"));
		assertJustDateEquals("1903-02-01", parseDayYearMonth("1 1903 2"));
	}

	public void testMonthDayOrderYearMonthDay(){
		assertJustDateEquals("1903-01-02", parseYearMonthDay("1 2 1903"));
		assertJustDateEquals("1903-01-02", parseYearMonthDay("1 1903 2"));
	}

	public void testMonthDayOrderYearDayMonth(){
		assertJustDateEquals("1903-02-01", parseYearDayMonth("1 2 1903"));
		assertJustDateEquals("1903-02-01", parseYearDayMonth("1 1903 2"));
	}

	public void testDayYearOrderMonthDayYear(){
		assertJustDateEquals("1903-01-02", parseMonthDayYear("Jan 2 3"));
		assertJustDateEquals("1903-01-02", parseMonthDayYear("2 Jan 3"));
		assertJustDateEquals("1903-01-02", parseMonthDayYear("2 3 Jan"));
	}

	public void testDayYearOrderDayMonthYear(){
		assertJustDateEquals("1903-01-02", parseDayMonthYear("Jan 2 3"));
		assertJustDateEquals("1903-01-02", parseDayMonthYear("2 Jan 3"));
		assertJustDateEquals("1903-01-02", parseDayMonthYear("2 3 Jan"));
	}

	public void testDayYearOrderMonthYearDay(){
		assertJustDateEquals("1902-01-03", parseMonthYearDay("Jan 2 3"));
		assertJustDateEquals("1902-01-03", parseMonthYearDay("2 Jan 3"));
		assertJustDateEquals("1902-01-03", parseMonthYearDay("2 3 Jan"));
	}

	public void testDayYearOrderDayYearMonth(){
		assertJustDateEquals("1903-01-02", parseDayYearMonth("Jan 2 3"));
		assertJustDateEquals("1903-01-02", parseDayYearMonth("2 Jan 3"));
		assertJustDateEquals("1903-01-02", parseDayYearMonth("2 3 Jan"));
	}

	public void testDayYearOrderYearMonthDay(){
		assertJustDateEquals("1902-01-03", parseYearMonthDay("Jan 2 3"));
		assertJustDateEquals("1902-01-03", parseYearMonthDay("2 Jan 3"));
		assertJustDateEquals("1902-01-03", parseYearMonthDay("2 3 Jan"));
	}

	public void testDayYearOrderYearDayMonth(){
		assertJustDateEquals("1902-01-03", parseYearDayMonth("Jan 2 3"));
		assertJustDateEquals("1902-01-03", parseYearDayMonth("2 Jan 3"));
		assertJustDateEquals("1902-01-03", parseYearDayMonth("2 3 Jan"));
	}

	public void testYearMonthOrderMonthDayYear(){
		assertJustDateEquals("1903-02-01", parseMonthDayYear("1st 2 3"));
		assertJustDateEquals("1903-02-01", parseMonthDayYear("2 1st 3"));
		assertJustDateEquals("1903-02-01", parseMonthDayYear("2 3 1st"));
	}

	public void testYearMonthOrderDayMonthYear(){
		assertJustDateEquals("1903-02-01", parseDayMonthYear("1st 2 3"));
		assertJustDateEquals("1903-02-01", parseDayMonthYear("2 1st 3"));
		assertJustDateEquals("1903-02-01", parseDayMonthYear("2 3 1st"));
	}

	public void testYearMonthOrderMonthYearDay(){
		assertJustDateEquals("1903-02-01", parseMonthYearDay("1st 2 3"));
		assertJustDateEquals("1903-02-01", parseMonthYearDay("2 1st 3"));
		assertJustDateEquals("1903-02-01", parseMonthYearDay("2 3 1st"));
	}

	public void testYearMonthOrderDayYearMonth(){
		assertJustDateEquals("1902-03-01", parseDayYearMonth("1st 2 3"));
		assertJustDateEquals("1902-03-01", parseDayYearMonth("2 1st 3"));
		assertJustDateEquals("1902-03-01", parseDayYearMonth("2 3 1st"));
	}

	public void testYearMonthOrderYearMonthDay(){
		assertJustDateEquals("1902-03-01", parseYearMonthDay("1st 2 3"));
		assertJustDateEquals("1902-03-01", parseYearMonthDay("2 1st 3"));
		assertJustDateEquals("1902-03-01", parseYearMonthDay("2 3 1st"));
	}

	public void testYearMonthOrderYearDayMonth(){
		assertJustDateEquals("1902-03-01", parseYearDayMonth("1st 2 3"));
		assertJustDateEquals("1902-03-01", parseYearDayMonth("2 1st 3"));
		assertJustDateEquals("1902-03-01", parseYearDayMonth("2 3 1st"));
	}

	public void testYearFirstThenMonthDay(){
		assertJustDateEquals("1997-01-02", parseMonthDayYear("1997-01-02"));
		assertJustDateEquals("1997-01-02", parseDayMonthYear("1997-01-02"));
		assertJustDateEquals("1997-01-02", parseMonthYearDay("1997-01-02"));
		assertJustDateEquals("1997-01-02", parseYearMonthDay("1997-01-02"));
		assertJustDateEquals("1997-01-02", parseYearMonthDay("1997-01-02"));
	}

	public void testYearFirstOverride(){
		assertJustDateEquals("1997-02-01", parseYearDayMonth("1997-01-02"));
	}

	public void testEarlyYear(){
		assertJustDateEquals("0009-01-01", parse("0009"));
		assertJustDateEquals("0009-01-01", parse("Jan 0009"));
		assertJustDateEquals("0009-01-02", parse("Jan 0009 2"));
		assertJustDateEquals("0009-02-03", parse("0009-02-03"));
	}

	public void testBc(){
		assertJustDateEqualsBc("0200-01-01", parse("200 BC"));
		assertJustDateEqualsBc("1431-01-10", parse("Jan 10 1431 BC"));
	}

	public void testBce(){
		assertJustDateEqualsBc("0200-01-01", parse("200 BCE"));
		assertJustDateEqualsBc("1431-01-10", parse("Jan 10 1431 BCE"));
	}

	public void testAd(){
		assertJustDateEquals("0200-01-01", parse("200 AD"));
		assertJustDateEquals("1431-01-10", parse("Jan 10 1431 AD"));
	}

	public void testCe(){
		assertJustDateEquals("0200-01-01", parse("200 CE"));
		assertJustDateEquals("1431-01-10", parse("Jan 10 1431 CE"));
	}

	public void testAdBcCaseInsensitive(){
		assertJustDateEqualsBc("0200-01-01", parse("200 bc"));
		assertJustDateEqualsBc("1431-01-10", parse("Jan 10 1431 BcE"));
		assertJustDateEquals("0200-01-01", parse("200 aD"));
		assertJustDateEquals("1431-01-10", parse("Jan 10 1431 Ce"));
	}

	public void testFieldOrderMonth(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.MONTH});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(2));
	}

	public void testFieldOrderDay(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.DAY});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(2));
	}

	public void testFieldOrderYear(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.YEAR});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(2));
	}

	public void testFieldOrderMonthDay(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.MONTH, DateTimeParse.Field.DAY});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(2));
	}

	public void testFieldOrderMonthYear(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.MONTH, DateTimeParse.Field.YEAR});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(2));
	}

	public void testFieldOrderDayMonth(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.DAY, DateTimeParse.Field.MONTH});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(2));
	}

	public void testFieldOrderDayYear(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.DAY, DateTimeParse.Field.YEAR});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(2));
	}

	public void testFieldOrderYearMonth(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.YEAR, DateTimeParse.Field.MONTH});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(2));
	}

	public void testFieldOrderYearDay(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.YEAR, DateTimeParse.Field.DAY});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(2));
	}

	public void testFieldOrderDuplicate(){
		DateTimeParse p = getParser(new DateTimeParse.Field[]{DateTimeParse.Field.YEAR, DateTimeParse.Field.YEAR});
		List<DateTimeParse.Field> fields = p.getFieldOrder();
		assertEquals(3, fields.size());
		Assert.assertEquals(DateTimeParse.Field.YEAR, fields.get(0));
		Assert.assertEquals(DateTimeParse.Field.MONTH, fields.get(1));
		Assert.assertEquals(DateTimeParse.Field.DAY, fields.get(2));
	}

	public void testGermanAscii(){
		assertJustDateEquals("2100-05-27", parse("27 Mai 2100"));
	}

	public void testGermanUmlaut(){
		assertJustDateEquals("2054-03-25", parse("25 M\u00e4r 2054"));
	}

	public void testGermanFormat(){
		assertJustDateEquals("1994-08-03", parse("3. Aug. 1994"));
		assertJustDateEquals("1994-08-03", parse("3. 8 1994"));
	}

	public void testSpanish(){
		assertJustDateEquals("1994-08-01", parse("primero Ago 1994"));
	}

	public void testGermanFormatPeriodAfterDay(){
		assertJustDateEquals("1994-08-03", parse("8 3. 1994"));
	}

	public void testTimeHoursMinutes(){
		assertJustTimeEquals("19:20:00", parse("19:20"));
	}

	public void testTimeHoursMinutesSpaces(){
		assertJustTimeEquals("19:20:00", parse(" 19 : 20 "));
	}

	public void testTimeHoursMinutesColon(){
		assertNull(parse("19:20:"));
	}

	public void testTimeMinutesTooBig(){
		assertNull(parse("19:70:00"));
	}

	public void testTimeSecondsTooBig(){
		assertNull(parse("19:20:71"));
	}
	public void testTimeMidnight(){
		assertJustTimeEquals("00:00:00", parse("00:00"));
		assertJustTimeEquals("00:00:00", parse("24:00"));
	}

	public void testTimeHoursMinutesSeconds(){
		assertJustTimeEquals("19:20:11", parse("19:20:11"));
		assertJustTimeEquals("23:59:59", parse("23:59:59"));
	}

	public void testDateTimeSimple(){
		assertJustDateTimeEquals("1997-07-16 19:20:00", parse("1997-07-16 19:20"));
		assertJustDateTimeEquals("1997-07-16 19:20:13", parse("1997-07-16 19:20:13"));
	}

	public void testDateTimeTSeparater(){
		assertJustDateTimeEquals("1997-07-16 19:20:00", parse("1997-07-16T19:20"));
		assertJustDateTimeEquals("1997-07-16 19:20:13", parse("1997-07-16T19:20:13"));
	}

	public void testHttpTimeDateSimple(){
		assertJustDateTimeEquals("1994-11-06 08:49:37", parse("Sun Nov 6 08:49:37 1994"));
	}

	public void testTimeAmPm(){
		assertJustTimeEquals("08:30:00", parse("8:30 AM"));
		assertJustTimeEquals("20:30:00", parse("8:30 PM"));
	}

	public void testTimeAmWithPeriods(){
		assertJustTimeEquals("08:30:00", parse("8:30 a.m."));
		assertJustTimeEquals("20:30:00", parse("8:30 p.m."));
	}

	public void testTimeAmPmRunTogether(){
		assertJustTimeEquals("08:30:00", parse("8:30AM"));
		assertJustTimeEquals("20:30:00", parse("8:30PM"));
	}

	public void testDateTimeOrderTimeFirst(){
		assertJustDateTimeEquals("2055-11-10 01:02:00", parse("1:02 2055-11-10"));
	}

	public void testDateTimeOrderDateFirst(){
		assertJustDateTimeEquals("2055-11-10 01:02:00", parse("2055-11-10 1:02"));
	}

	public void testDateTimeOrderWithSpelledOutDate(){
		assertJustDateTimeEquals("2055-11-10 01:02:00", parse("1:02 November 10th 2055"));
	}

	public void testDateTimeOrderWithPM(){
		assertJustDateTimeEquals("2055-11-10 13:02:00", parse("1:02 PM November 10th 2055"));
	}

	public void testDateTimeNumericZone(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00+0100"));
	}

	public void testDateTimeNumericZoneMinus(){
		assertJustDateTimeEquals("1997-07-16 20:20:00", parse("1997-07-16 19:20:00-0100"));
	}

	public void testDateTimeNumericZoneSpaceBefore(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00 +0100"));
	}

	public void testDateTimeNumericZoneHalfHour(){
		assertJustDateTimeEquals("1997-07-16 17:50:00", parse("1997-07-16 19:20:00+0130"));
	}

	public void testDateTimeNumericZoneColon(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00+01:00"));
	}

	public void testDateTimeNumericZoneSpaceBeforeWithColon(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00 +01:00"));
	}

	public void testDateTimeNumericZoneSpaceInsteadOfColon(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00 +01 00"));
	}

	public void testDateTimeNumericZoneSpaceAfterPlus(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16 19:20:00 + 01:00"));
	}

	public void testDateTimeNumericZoneSeparatedT(){
		assertJustDateTimeEquals("1997-07-16 18:20:00", parse("1997-07-16T19:20+01:00"));
	}

	public void testGermanOrder(){
		assertJustDateEquals("2000-01-02", parseGerman("02.01.2000"));
	}

	/* Not supported yet

	public void testSpanishWithSpace(){
		assertJustDateEquals("1994-08-19", parse("decimo noveno Ago 1994"));
	}

	public void testTimeHoursTooBig(){
		assertNull(parse("99:20:00"));
	}

	public void testBrazil(){
		assertJustDateEquals("1927-05-01", parse("1o. de maio de 1927"));
	}

	public void testYearWords(){
		assertJustDateEquals("1900-01-01", parse("nineteen hundred"));
		assertJustDateEquals("2011-01-01", parse("twenty eleven"));
		assertJustDateEquals("1984-05-02", parse("may second nineteen eighty-four"));
		assertJustDateEquals("2001-01-01", parse("two thousand one"));
		assertJustDateEquals("2001-01-01", parse("two thousand and two"));
	}

	public void testDateTime(){
		assertJustDateTimeEquals("1994-11-05 08:15:30", parse("November 5, 1994, 8:15:30 am, US Eastern Standard Time"));
		assertJustDateTimeEquals("1994-11-05 08:15:30", parse("1994-11-05T13:15:30Z"));
		assertJustDateTimeEquals("1994-11-05 08:15:30", parse("1492-05-11T04:04:40.33423343-4000"));
	}

	public void testRelativeDates(){
		assertNull(parse("now"));
		assertNull(parse("today"));
		assertNull(parse("two days ago"));
		assertNull(parse("yesterday"));
		assertNull(parse("tomorrow"));
		assertNull(parse("in 1 day"));
		assertNull(parse("first saturday of 1974"));
		assertNull(parse("last thur in sept"));
	}

	public void testHttpTime(){
		assertJustDateTimeEquals("1994-11-06 08:49:37", parse("Sun, 06 Nov 1994 08:49:37 GMT"));
		assertJustDateTimeEquals("1994-11-06 08:49:37", parse("Sun, 6 Nov 1994 08:49:37 GMT"));
		assertJustDateTimeEquals("1994-11-06 08:49:37", parse("Sunday, 06-Nov-94 08:49:37 GMT"));
	}

	public void testDateWithWeekNumber(){
		assertNull(parse("2010-W36-5"));
		assertNull(parse("1997W01"));
		assertNull(parse("1997-W01"));
	}

	public void testOrdinalDates(){
		assertNull(parse("2010-253"));
	}

	public void testRunTogetherDate(){
		assertNull(parse("20100704"));
	}

	public void testJustTime(){
		parse("23:59:59.9942");
		assertNull(parse("235959.9942"));
	}

	public void testTimeSpelledOut(){
		assertNull(parse("midnight"));
		assertNull(parse("quarter to two"));
		assertNull(parse("half twelve"));
		assertNull(parse("eleven thirty"));
		assertNull(parse("two o'clock"));
	}

	public void testDateTimeRunTogether(){
		assertNull(parse("19951231T235959"));
	}

	public void testRelativeTimes(){
		assertNull(parse("10 seconds ago"));
		assertNull(parse("10 minutes ago"));
		assertNull(parse("in an hour"));
		assertNull(parse("in a minute"));
		assertNull(parse("an hour from now"));
	}
	*/

	// END TESTS

	private static DateTimeParse getParser(DateTimeParse.Field[] fieldOrder){
		return getParser(fieldOrder, null);
	}

	private static DateTimeParse getParser(Locale locale){
		return getParser(null, locale);
	}

	private static DateTimeParse getParser(DateTimeParse.Field[] fieldOrder, Locale locale){
		if (locale == null) locale = Locale.US;
		DateTimeParse p = new DateTimeParse(locale);
		p.setDefaultYear(1981);
		if (fieldOrder != null){
			p.setFieldOrder(fieldOrder);
		}
		p.setYearExtensionPolicy(YearExtensionAround.CENTURY_1900);
		p.setTimeZone(TimeZone.getTimeZone("GMT"));
		return p;
	}

	public static String parse(String date){
		return parseDayMonthYear(date);
	}

	private static final DateTimeParse PARSER_DAY_MONTH_YEAR = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.DAY, DateTimeParse.Field.MONTH, DateTimeParse.Field.YEAR}
	);

	public static String parseDayMonthYear(String date){
		return formatDate(PARSER_DAY_MONTH_YEAR.parse(date));
	}

	private static final DateTimeParse PARSER_MONTH_DAY_YEAR = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.MONTH, DateTimeParse.Field.DAY, DateTimeParse.Field.YEAR}
	);

	public static String parseMonthDayYear(String date){
		return formatDate(PARSER_MONTH_DAY_YEAR.parse(date));
	}

	private static final DateTimeParse PARSER_DAY_YEAR_MONTH = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.DAY, DateTimeParse.Field.YEAR, DateTimeParse.Field.MONTH}
	);

	public static String parseDayYearMonth(String date){
		return formatDate(PARSER_DAY_YEAR_MONTH.parse(date));
	}

	private static final DateTimeParse PARSER_MONTH_YEAR_DAY = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.MONTH, DateTimeParse.Field.YEAR, DateTimeParse.Field.DAY}
	);

	public static String parseMonthYearDay(String date){
		return formatDate(PARSER_MONTH_YEAR_DAY.parse(date));
	}

	private static final DateTimeParse PARSER_YEAR_MONTH_DAY = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.YEAR, DateTimeParse.Field.MONTH, DateTimeParse.Field.DAY}
	);

	public static String parseYearMonthDay(String date){
		return formatDate(PARSER_YEAR_MONTH_DAY.parse(date));
	}

	private static final DateTimeParse PARSER_YEAR_DAY_MONTH = getParser(
		new DateTimeParse.Field[]{DateTimeParse.Field.YEAR, DateTimeParse.Field.DAY, DateTimeParse.Field.MONTH}
	);

	private static final DateTimeParse PARSER_GERMAN = getParser(
		Locale.GERMANY
	);

	public static String parseGerman(String date){
		return formatDate(PARSER_GERMAN.parse(date));
	}

	public static String parseYearDayMonth(String date){
		return formatDate(PARSER_YEAR_DAY_MONTH.parse(date));
	}

	public void assertJustDateEquals(String s, String d){
		assertEquals("AD " + s + " 00:00:00 +0000", d);
	}

	public void assertJustTimeEquals(String s, String d){
		assertNotNull(d);
		assertEquals(s+" +0000", d.substring(14));
	}

	public void assertJustDateTimeEquals(String s, String d){
		assertEquals("AD " + s + " +0000", d);
	}

	public void assertJustDateEqualsBc(String s, String d){
		assertEquals("BC " + s + " 00:00:00 +0000", d);
	}

	public static String formatDate(Date date){
		if (date == null) return null;
		SimpleDateFormat format = new SimpleDateFormat("G yyyy-MM-dd HH:mm:ss Z");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(date);
	}
}
