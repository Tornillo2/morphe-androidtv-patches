package com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear;

import android.util.Log;
import com.google.common.base.Ascii;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LocalConfigInstanceIdGenerationHelper {
    public static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static final String TAG = "LocalConfigInstanceIdGenerationHelper";

    public static String convertBytesToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public static String generateLocalConfigInstanceId(String str) {
        Checks.checkArgument((str == null || str.isEmpty()) ? false : true, "Invalid string for hashing");
        try {
            return convertBytesToHexString(MessageDigest.getInstance("SHA-256").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            Log.e(TAG, "Unable to generate hash from string identifier");
            return "";
        }
    }
}
