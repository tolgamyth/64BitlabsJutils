/*
 * Copyright (C) 2002-2011 Tolga Yilmaz
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
 * Exception thrown when a command line option is missing an argument
 */
public class MissingCmdLnArgumentException extends CmdLnArgumentException
{
	/**
	 * serial version id
	 */
	private static final long serialVersionUID = -8552921685243918697L;

	/**
	 * Construct a new exception.
	 */
	public MissingCmdLnArgumentException() {
		super("Additional argument required");
	}

}
