package com.amazon.ignitionshared;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class HashingHandler {
    public static final int ITERATIONS = 1000;

    public static String generatePBKDF2Hash(String str) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (str == null || str.isEmpty()) {
            return "";
        }
        char[] charArray = str.toLowerCase(Locale.US).toCharArray();
        byte[] salt = getSalt();
        return toHex(salt) + ":" + toHex(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(charArray, salt, 1000, 512)).getEncoded());
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException {
        byte[] bArrDigest = MessageDigest.getInstance("MD5").digest("2XHMeZL1kGVlHYzt1Ssc".getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bArrDigest) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString().getBytes();
    }

    public static String toHex(byte[] bArr) {
        String string = new BigInteger(1, bArr).toString(16);
        int length = (bArr.length * 2) - string.length();
        return length > 0 ? String.format(ObjectListKt$$ExternalSyntheticOutline1.m("%0", length, "d"), 0).concat(string) : string;
    }
}
