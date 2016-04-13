/*
 * A utility class for encrypting and decrypting sensitive data
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

package com._64bitlabs.util;

import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * A utility class for encrypting and decrypting sensitive data
 */
public class Encryptors {

    private static final String ALGORITHM = "AES";
    public static final byte[] keyValues = new byte[] { 'Y', 's', 'b', 'X', 'p', 's', 'd', 'S', 'y', 'u', 'm', 'n', 'q', 'K', 'e', 'y' };

    /**
     * Encrypts string value with the specified AES algorithm
     * @param Data string to be encrypted
     * @return encrypted string
     */
    public static String encrypt(String Data) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            Base64 encoder = new Base64();
            String encryptedValue = new String(encoder.encode(encVal)).trim();
            return encryptedValue;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Given AES encryption algorithm, decrypts the string value.
     * @param encryptedData
     * @return decrypted String
     */
    public static String decrypt(String encryptedData) {
        Key key = generateKey();
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);

            Base64 dec = new Base64();
            byte[] decodedValue = dec.decode(encryptedData.getBytes());
            byte[] decValue = c.doFinal(decodedValue);

            return new String(decValue);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Generates key required for encrypt and decrypt operations
     * @return Key
     */
    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValues, ALGORITHM);
        return key;
    }

    /**
     * Encrypts string str with the salt
     * @param str string to be salted
     * @param salt salt
     * @return encrypted string
     */
    public static String operateWithSalt(String str, String salt) {
        return Encryptors.encrypt(salt + str);
    }

    /**
     * Decrypts string str with the salt
     * @param str salted string
     * @param salt salt
     * @return decrypted string
     */
    public static String deoperateWithSalt(String str, String salt) {
        return Encryptors.decrypt(str).substring(salt.length());
    }

    /**
     * Generates random salt string
     * @return random salt string
     */
    public static String nextSalt() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
