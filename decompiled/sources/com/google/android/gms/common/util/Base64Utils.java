package com.google.android.gms.common.util;

import android.util.Base64;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class Base64Utils {
    @NonNull
    @KeepForSdk
    public static byte[] decode(@NonNull String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    @NonNull
    @KeepForSdk
    public static byte[] decodeUrlSafe(@NonNull String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 10);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static byte[] decodeUrlSafeNoPadding(@NonNull String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 11);
    }

    @NonNull
    @KeepForSdk
    public static String encode(@NonNull byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 0);
    }

    @NonNull
    @KeepForSdk
    public static String encodeUrlSafe(@NonNull byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 10);
    }

    @NonNull
    @KeepForSdk
    public static String encodeUrlSafeNoPadding(@NonNull byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 11);
    }
}
