package com._64bitlabs.util;

import junit.framework.TestCase;

/**
 * Created by tolga on 11.04.2016.
 */
public class EncryptorsTest extends TestCase {

    public void testEncryptAndDecrypt() {
        String encryptedValue,decryptedValue,
                value = "String to decrypt --- 01234567890!=?'_+^-*'%&/(){}[]";

        encryptedValue = Encryptors.encrypt(value);
        decryptedValue = Encryptors.decrypt(encryptedValue);

        assertNotNull(decryptedValue);
        assertNotNull(encryptedValue);
        assertNotSame(decryptedValue, encryptedValue);
        assertEquals(decryptedValue, value);
    }

    public void testOperateAndDeoperateWithSalt() {
        String encryptedValue,decryptedValue,
                value = "String to decrypt --- 01234567890!=?'_+^-*'%&/(){}[]",
                salt = Encryptors.nextSalt();

        encryptedValue = Encryptors.operateWithSalt(value,salt);
        decryptedValue = Encryptors.deoperateWithSalt(encryptedValue, Encryptors.nextSalt());

        System.out.println(encryptedValue);
        System.out.println(decryptedValue);
        System.out.println(salt);

        assertNotNull(salt);
        assertNotNull(decryptedValue);
        assertNotNull(encryptedValue);
        assertNotSame(decryptedValue, encryptedValue);
        assertNotSame(salt, Encryptors.nextSalt());
        assertEquals(decryptedValue, value);
    }
}
