/*
 * Converts an enumeration to an iterator.
 * Copyright (C) 2004-2010 Tolga Yilmaz
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

import java.util.*;

/**
 * Converts an Enumeration to an iterator.
 * <p>
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/Iterator_Enumeration.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @param <ElementType> type of object over which to iterate
 * @since 64bitlabsutils 1.03.00
 */
public class EnumerationIterator<ElementType> implements Iterator<ElementType> {

	/**
	 * Enumeration being converted to iterator.
	 */
	private Enumeration<ElementType> enumeration;

	/**
	 * Create an Iterator from an Enumeration.
	 *
	 * @param enumeration Enumeration to convert to an Iterator.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public EnumerationIterator(Enumeration<ElementType> enumeration){
		this.enumeration = enumeration;
	}

	/**
	 * Tests if this Iterator contains more elements.
	 *
	 * @return true if and only if this Iterator object contains at least
	 * one more element to provide; false otherwise.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public boolean hasNext(){
		return enumeration.hasMoreElements();
	}

	/**
	 * Returns the next element of this Iterator if this Iterator
	 * object has at least one more element to provide.
	 *
	 * @return the next element of this Iterator.
	 * @throws NoSuchElementException if no more elements exist.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public ElementType next() throws NoSuchElementException {
		return enumeration.nextElement();
	}

	/**
	 * Operation not supported.
	 *
	 * @throws UnsupportedOperationException every time.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public void remove(){
		throw new UnsupportedOperationException("EnumerationIterator does not support remove()");
	}
}