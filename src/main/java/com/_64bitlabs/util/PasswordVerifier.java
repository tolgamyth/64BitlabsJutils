/*
 * Interface to verify passwords.
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

package com._64bitlabs.util;

/**
 * Interface to verify passwords.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.0.0
 */
public interface PasswordVerifier {
	/**
	 * Verify that this password is an OK password.  If a password
	 * is not verified it is thrown out and a new password is tried.
	 * Always returning false from this method will cause an infinite
	 * loop.
	 *
	 * @param password an array of characters representing a password.
	 * @return true iff this password is OK.
	 *
	 * @since 64bitlabsutils 1.0.0
	 */
	public boolean verify(char[] password);

}
