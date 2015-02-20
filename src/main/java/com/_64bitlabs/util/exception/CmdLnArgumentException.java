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
import com._64bitlabs.util.cmd.CmdLnOption;
import com._64bitlabs.util.cmd.CmdLnResult;

/**
 * Exception thrown for a problem with a specific command line option.
 *
 * More information about this class and code samples for suggested use are
 * available from <a target="_top" href=
 * "http://64bitlabs.com/utils/CmdLn.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.07.00
 */
public class CmdLnArgumentException extends CmdLnException {

	/**
	 * serial version id
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	private static final long serialVersionUID = -5457270771303129044L;

	/**
	 * @param message message explaining the exception
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	CmdLnArgumentException(String message) {
		super(message);
	}

	private CmdLnResult result;

	/**
	 * Get the partial result with missing arguments.
	 *
	 * @return the partial result
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	public CmdLnResult getResult() {
		return result;
	}

	/**
	 * Get the option that caused this exception
	 *
	 * @return the option
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	public CmdLnOption getOption() {
		return result.getOption();
	}

	/**
	 * Set the result
	 *
	 * @param result partial result
	 * @return this for method chaining
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	public CmdLnArgumentException setResult(CmdLnResult result) {
		this.result = result;
		return this;
	}

	/**
	 * @return message with the option name
	 *
	 * @since 64bitlabsutils 1.07.00
	 */
	@Override public String getMessage(){
		return super.getMessage() + ": " + getOption();
	}
}
