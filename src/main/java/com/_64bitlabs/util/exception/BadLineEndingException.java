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
 * An illegal line ending was specified.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.06.01
 */
public class BadLineEndingException extends IllegalArgumentException {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = -3300235286182152695L;

	/**
	 * Constructs an exception with null as its error detail message.
	 *
	 * @since 64bitlabsutils 1.06.01
	 */
	public BadLineEndingException(){
		super();
	}

	/**
	 * Constructs an exception with the specified detail message.
	 * The error message string s can later be retrieved by the
	 * Throwable.getMessage()  method of class java.lang.Throwable.
	 *
	 * @param s the detail message.
	 *
	 * @since 64bitlabsutils 1.06.01
	 */
	public BadLineEndingException(String s){
		super(s);
	}
}
