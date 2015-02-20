/*
 * Copyright (C) 2007-2010 Tolga Yilmaz
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

import com._64bitlabs.util.cmd.CmdLnException;

/**
 * Exception thrown when a command line option that was unexpected is found.
 */
public class UnknownCmdLnOptionException extends CmdLnException {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 6461128374875358108L;

	/**
	 * Constructs an <code>UnknownCmdLnOptionException</code>
	 */
	public UnknownCmdLnOptionException() {
		super("Unknown command line option");
	}

	private String argument;

	/**
	 * Get the argument that caused this exception
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * Set the argument
	 * @param argument the argument to set
	 * @return this for method chaining
	 */
	public UnknownCmdLnOptionException setArgument(String argument) {
		this.argument = argument;
		return this;
	}

	private String option;

	/**
	 * Get the option that caused this exception
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Set the option
	 * @param option the option to set
	 * @return this for method chaining
	 */
	public UnknownCmdLnOptionException setOption(String option) {
		this.option = option;
		return this;
	}

	@Override public String getMessage(){
		return super.getMessage() + ": " + getArgument() + ": " + getOption();
	}
}
