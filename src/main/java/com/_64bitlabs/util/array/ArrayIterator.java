/*
 * Converts an array to an iterator.
 * Copyright (C) 2014-2016 Tolga Yilmaz
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
 * Converts an array to an iterator.
 * <p>
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/Iterator_Enumeration.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @param <ElementType> Type of array over which to iterate
 * @since 64bitlabsutils 1.03.00
 */
public class ArrayIterator<ElementType> implements Iterator<ElementType> {

	/**
	 * Array being converted to iterator.
	 */
	private ElementType[] array;

	/**
	 * Current index into the array.
	 */
	private int index = 0;

	/**
	 * Whether the last element has been removed.
	 */
	private boolean lastRemoved = false;

	/**
	 * Create an Iterator from an Array.
	 *
	 * @param array of objects on which to enumerate.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public ArrayIterator(ElementType[] array){
		this.array = array;
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
		return (index < array.length);
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
		if (index >= array.length) throw new NoSuchElementException("Array index: " + index);
		ElementType object = array[index];
		index++;
		lastRemoved = false;
		return object;
	}

	/**
	 * Removes the last object from the array by setting the slot in
	 * the array to null.
	 * This method can be called only once per call to next.
	 *
	 * @throws IllegalStateException if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
	 *
	 * @since 64bitlabsutils 1.03.00
	 */
	public void remove(){
		if (index == 0) throw new IllegalStateException();
		if (lastRemoved) throw new IllegalStateException();
		array[index-1] = null;
		lastRemoved = true;
	}
}