/*
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

package com._64bitlabs.util.array;

import java.util.*;

/**
 * Converts an array to an enumerator.
 * <p>
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/Iterator_Enumeration.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @param <ElementType> Type of object enumerated
 * @since 64bitlabsutils 1.03.00
 */
public class ArrayEnumeration<ElementType> implements Enumeration<ElementType> {

	/**
	 * Array being converted to enumeration.
	 */
	private ElementType[] array;

	/**
	 * Current index into the array.
	 */
	private int index = 0;

	/**
	 * Create an Enumeration from an Array.
	 *
	 * @param array of objects on which to enumerate.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public ArrayEnumeration(ElementType[] array){
		this.array = array;
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
		return (index < array.length);
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
		if (index >= array.length) throw new NoSuchElementException("Array index: " + index);
		ElementType object = array[index];
		index++;
		return object;
	}
}