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
 * Interface for extending two digit years to full years.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.08.00
 */
public interface YearExtensionPolicy {

	/**
	 * Extend a two digit year.
	 * For example: 99 -> 1999
	 *
	 * @param twoDigitYear year from 0 to 99
	 * @return Full year representation
	 *
	 * @since 64bitlabsutils 1.08.00
	 */
	public int extendYear(int twoDigitYear);

}
