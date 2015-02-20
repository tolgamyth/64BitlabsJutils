/*
 * Copyright (C) 1996 Santeri Paavolainen, Helsinki Finland
 *
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
 *
 * The original work by Santeri Paavolainen can be found a
 * http://santtu.iki.fi/md5/
 */
package com._64bitlabs.util.file;

import java.io.*;

/**
 * Implements MD5 functionality on a stream.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/MD5.html">64bitlabs.com</a>.
 * <p>
 * This class produces a 128-bit "fingerprint" or "message digest" for
 * all data written to this stream.
 * It is conjectured that it is computationally infeasible to produce
 * two messages having the same message digest, or to produce any
 * message having a given pre-specified target message digest. The MD5
 * algorithm is intended for digital signature applications, where a
 * large file must be "compressed" in a secure manner before being
 * encrypted with a private (secret) key under a public-key cryptosystem
 * such as RSA.
 * <p>
 * For more information see RFC1321.
 *
 * @see MD5
 * @see MD5InputStream
 *
 * @author Santeri Paavolainen http://santtu.iki.fi/md5/
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.00.00
 */
public class MD5OutputStream extends FilterOutputStream {

	/**
	 * MD5 context
	 */
	private MD5	md5;

	/**
	 * Creates MD5OutputStream
	 * @param out	The output stream
	 *
	 * @since 64bitlabsutils 1.00.00
	 */
	public MD5OutputStream(OutputStream out) {
		super(out);
		md5 = new MD5();
	}

	/**
	 * Writes the specified byte to this output stream.
	 *
	 * @param b the byte.
	 * @throws IOException if an I/O error occurs.
	 *
	 * @since 64bitlabsutils 1.00.00
	 */
	@Override public void write(int b) throws IOException {
		out.write(b);
		md5.update((byte)(b & 0xff));
	}

	/**
	 * Writes length bytes from the specified byte array starting a
	 * offset off to this output stream.
	 *
	 * @param b the data.
	 * @param off the start offset in the data.
	 * @param len the number of bytes to write.
	 * @throws IOException if an I/O error occurs.
	 *
	 * @since 64bitlabsutils 1.00.00
	 */
	@Override public void write(byte b[], int off, int len) throws IOException {
		out.write(b, off, len);
		md5.update(b, off, len);
	}

	/**
	 * Returns array of bytes representing hash of the stream so far.
	 *
	 * @return Array of 16 bytes, the hash of all written bytes.
	 *
	 * @since 64bitlabsutils 1.00.00
	 */
	public byte[] getHash(){
		return md5.getHash();
	}

	/**
	 * Get a 32-character hex representation representing hash of the stream so far.
	 *
	 * @return A string containing  the hash of all written bytes.
	 *
	 * @since 64bitlabsutils 1.00.00
	 */
	public String getHashString(){
		return md5.getHashString();
	}
}

