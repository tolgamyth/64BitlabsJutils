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
 * An input stream with a close method with no effect.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/NoCloseStream.html">64bitlabs.com</a>.
 * <p>
 * This class is designed to wrap a normal input stream
 * so that it can be passed to methods that read from it
 * and may erroneously close it.  This class is a workaround
 * when the method cannot be modified because it is in a
 * library.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.01.00
 */
public class NoCloseInputStream extends InputStream implements NoCloseStream {

	/**
	 * The input stream that is being protected.
	 * All methods should be forwarded to it,
	 * except for the close method, which should
	 * do nothing.  The reallyClose method should
	 * actually close this stream.
	 *
	 * @since 64bitlabsutils 1.01.00
	 */
	protected InputStream in;

	/**
	 * Protect a new input stream.
	 *
	 * @param in The input stream that is being protected.
	 *
	 * @since 64bitlabsutils 1.01.00
	 */
	public NoCloseInputStream(InputStream in){
		this.in = in;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int read() throws IOException {
		return in.read();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int read(byte[] b) throws IOException {
		return in.read(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int read(byte[] b, int off, int len) throws IOException {
		return in.read(b, off, len);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public long skip(long n) throws IOException {
		return in.skip(n);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int available() throws IOException {
		return in.available();
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
	@Override public void mark(int readlimit) {
		in.mark(readlimit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void reset() throws IOException {
		in.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public boolean markSupported(){
		return in.markSupported();
	}

	/**
	 * {@inheritDoc}
	 */
	public void reallyClose() throws IOException {
		in.close();
	}
}
