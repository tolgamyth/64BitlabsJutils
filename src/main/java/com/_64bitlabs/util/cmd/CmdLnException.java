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
package com._64bitlabs.util.cmd;

/**
 * Exception thrown when a command line cannot be parsed.
 *
 * More information about this class and code samples for suggested use are
 * available from <a target="_top" href=
 * "http://64bitlabs.com/utils/CmdLn.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.07.00
 */
public class CmdLnException extends IllegalArgumentException
{
	/**
	 * serial version id
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	private static final long serialVersionUID = 3984942697362044497L;

	/**
	 * @param message detail message
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	public CmdLnException(String message) {
		super(message);
	}
}
