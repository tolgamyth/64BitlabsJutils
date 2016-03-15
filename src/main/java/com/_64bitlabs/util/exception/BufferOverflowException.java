/*
 * Buffer Overflow Exception
 * Copyright (C) 2002-2010 Tolga Yilmaz
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

import java.io.IOException;

/**
 * An indication that there was a buffer overflow.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.0.0
 */
public class BufferOverflowException extends IOException {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -322401823167626048L;

	/**
	 * Create a new Exception
	 *
	 * @since 64bitlabsutils 1.0.0
	 */
	public BufferOverflowException(){
		super();
	}

	/**
	 * Create a new Exception with the given message.
	 *
	 * @param msg Error message.
	 *
	 * @since 64bitlabsutils 1.0.0
	 */
	public BufferOverflowException(String msg){
		super(msg);
	}
}
