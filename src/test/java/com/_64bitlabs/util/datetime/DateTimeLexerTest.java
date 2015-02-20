/*
 * Copyright (C) 2010 Tolga Yilmaz
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

import java.io.IOException;
import java.io.StringReader;

import com._64bitlabs.util.datetime.DateTimeToken;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.08.00
 */
public class DateTimeLexerTest extends TestCase {

	public void testInt(){
		Assert.assertEquals(DateTimeToken.DateTimeTokenType.NUMBER, getFirstToken("1").getType());
	}

	public void testAscii(){
		Assert.assertEquals(DateTimeToken.DateTimeTokenType.WORD, getFirstToken("abc").getType());
	}

	public void testLatin1(){
		DateTimeToken t = getFirstToken("M\u00e4r");
		Assert.assertEquals(DateTimeToken.DateTimeTokenType.WORD, t.getType());
		assertEquals("M\u00e4r", t.getText());
	}


	private DateTimeToken getFirstToken(String s){
		try {
			return new DateTimeLexer(new StringReader(s)).getNextToken();
		} catch (IOException iox){
			throw new RuntimeException(iox);
		}
	}
}