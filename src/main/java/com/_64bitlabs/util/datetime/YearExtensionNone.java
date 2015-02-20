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
package com._64bitlabs.util.datetime;

/**
 * Null object for year extension policy.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.08.00
 */
public class YearExtensionNone implements YearExtensionPolicy {

	public static final YearExtensionNone YEAR_EXTEND_NONE = new YearExtensionNone();

	private YearExtensionNone(){
		// singleton, private constructor
	}

	/**
	 * Does NOT extend the year.
	 *
	 * @return a two digit year unaltered.
	 * @since 64bitlabsutils 1.08.00
	 */
	public int extendYear(int twoDigitYear) {
		return twoDigitYear;
	}
}
