package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class ClientLibraryUtils {
    @KeepForSdk
    public static int getClientVersion(@NonNull Context context, @NonNull String str) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        PackageInfo packageInfo = getPackageInfo(context, str);
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (bundle = applicationInfo.metaData) == null) {
            return -1;
        }
        return bundle.getInt("com.google.android.gms.version", -1);
    }

    @Nullable
    @KeepForSdk
    public static PackageInfo getPackageInfo(@NonNull Context context, @NonNull String str) {
        try {
            return Wrappers.zza.zza(context).getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @KeepForSdk
    public static boolean isPackageSide() {
        return false;
    }
}
