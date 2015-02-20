/*
 * Copyright (C) 2003-2010 Tolga Yilmaz
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
package com._64bitlabs.util.file;

import java.io.*;

/**
 * A writer with a close method with no effect.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/NoCloseStream.html">64bitlabs.com</a>.
 * <p>
 * This class is designed to wrap a normal writer
 * so that it can be passed to methods that write to it
 * and may erroneously close it.  This class is a workaround
 * when the method cannot be modified because it is in a
 * library.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.01.00
 */
public class NoCloseWriter extends Writer implements NoCloseStream {

	/**
	 * The writer that is being protected.
	 * All methods should be forwarded to it,
	 * except for the close method, which should
	 * do nothing.  The reallyClose method should
	 * actually close this stream.
	 *
	 * @since 64bitlabsutils 1.01.00
	 */
	protected Writer out;

	/**
	 * Protect a new writer.
	 *
	 * @param out The writer that is being protected.
	 */
	public NoCloseWriter(Writer out){
		this.out = out;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void write(int c) throws IOException {
		out.write(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void write(char[] cbuf) throws IOException {
		out.write(cbuf);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void write(char[] cbuf, int off, int len) throws IOException {
		out.write(cbuf, off, len);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void write(String str) throws IOException {
		out.write(str);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void write(String str, int off, int len) throws IOException {
		out.write(str, off, len);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void flush() throws IOException {
		out.flush();
	}

	/**
	 * Has no effect.
	 *
	 * @see #reallyClose()
	 *
	 * @since 64bitlabsutils 1.01.00
	 */
	@Override public void close() throws IOException {
		// Does nothing
	}

	/**
	 * {@inheritDoc}
	 */
	public void reallyClose() throws IOException {
		out.close();
	}
}
