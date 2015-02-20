/*
 * Converts an iterator to an enumerator.
 * Copyright (C) 2004-2010 Tolga Yilmaz
 * info@64bitlabs.com
 * Copyright (C) 2006 Jonathan Faivre-Vuillin
 * public dot lp at free dot fr
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
 * Converts an iterator to an enumerator.
 * <p>
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/Iterator_Enumeration.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @param <ElementType> Type of element being enumerated
 * @since 64bitlabsutils 1.03.00
 */
public class IteratorEnumeration<ElementType> implements Enumeration<ElementType> {

	/**
	 * Iterator being converted to enumeration.
	 */
	private Iterator<ElementType> iterator;

	/**
	 * Create an Enumeration from an Iterator.
	 *
	 * @param iterator Iterator to convert to an enumeration.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public IteratorEnumeration(Iterator<ElementType> iterator){
		this.iterator = iterator;
	}

	/**
	 * Tests if this enumeration contains more elements.
	 *
	 * @return true if and only if this enumeration object contains at least
	 * one more element to provide; false otherwise.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public boolean hasMoreElements(){
		return iterator.hasNext();
	}

	/**
	 * Returns the next element of this enumeration if this enumeration
	 * object has at least one more element to provide.
	 *
	 * @return the next element of this enumeration.
	 * @throws NoSuchElementException if no more elements exist.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public ElementType nextElement() throws NoSuchElementException {
		return iterator.next();
	}
}