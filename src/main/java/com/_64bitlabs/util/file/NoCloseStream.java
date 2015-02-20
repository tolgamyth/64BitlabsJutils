/*
 * Streams that have a different close mechanism.
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
package com._64bitlabs.util.file;

import java.io.*;

/**
 * A wrapper for a stream (either input or output)
 * which has a close method with no effect.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/NoCloseStream.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.01.00
 */
public interface NoCloseStream {

	/**
	 * Actually closes this stream and releases any system
	 * resources associated with the stream, as opposed to
	 * the close() method, which does nothing.
	 *
	 * @throws IOException if an I/O error occurs.
	 *
	 * @since 64bitlabsutils 1.01.00
	 */
	public void reallyClose() throws IOException;

}
