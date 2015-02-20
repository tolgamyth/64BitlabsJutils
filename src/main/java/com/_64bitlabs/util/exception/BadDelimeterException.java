/*
 * Copyright (C) 2001-2011 Tolga Yilmaz
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

package com._64bitlabs.util.exception;

/**
 * An Illegal delimiter was specified.
 * <p>
 * This class has been replaced by BadDelimiterException.  It is not deprecated,
 * and it may be used, however the name of this class contains a spelling error.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.02.08
 * @see BadDelimiterException
 */
public class BadDelimeterException extends IllegalArgumentException {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 7603007141975623144L;

	/**
	 * Constructs an exception with null as its error detail message.
	 *
	 * @since 64bitlabsutils 1.02.08
	 */
	public BadDelimeterException(){
		super();
	}

	/**
	 * Constructs an exception with the specified detail message.
	 * The error message string s can later be retrieved by the
	 * Throwable.getMessage()  method of class java.lang.Throwable.
	 *
	 * @param s the detail message.
	 *
	 * @since 64bitlabsutils 1.02.08
	 */
	public BadDelimeterException(String s){
		super(s);
	}
}
