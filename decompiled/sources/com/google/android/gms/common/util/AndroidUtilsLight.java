package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@KeepForSdk
public class AndroidUtilsLight {
    public static volatile int zza = -1;

    @Nullable
    @KeepForSdk
    @Deprecated
    public static byte[] getPackageCertificateHashBytes(@NonNull Context context, @NonNull String str) throws PackageManager.NameNotFoundException {
        MessageDigest messageDigestZza;
        PackageInfo packageInfo = Wrappers.zza.zza(context).getPackageInfo(str, 64);
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || signatureArr.length != 1 || (messageDigestZza = zza("SHA1")) == null) {
            return null;
        }
        return messageDigestZza.digest(packageInfo.signatures[0].toByteArray());
    }

    @Nullable
    public static MessageDigest zza(@NonNull String str) {
        MessageDigest messageDigest;
        for (int i = 0; i < 2; i++) {
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }
}
