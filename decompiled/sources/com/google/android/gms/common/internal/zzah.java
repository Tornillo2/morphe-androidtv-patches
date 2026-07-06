package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.errorprone.annotations.concurrent.GuardedBy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzah {
    public static final Object zza = new Object();

    @GuardedBy("lock")
    public static boolean zzb;

    @Nullable
    public static String zzc;
    public static int zzd;

    public static int zza(Context context) {
        zzc(context);
        return zzd;
    }

    @Nullable
    public static String zzb(Context context) {
        zzc(context);
        return zzc;
    }

    public static void zzc(Context context) {
        synchronized (zza) {
            try {
                if (zzb) {
                    return;
                }
                zzb = true;
                try {
                    Bundle bundle = Wrappers.zza.zza(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (bundle == null) {
                        return;
                    }
                    zzc = bundle.getString("com.google.app.id");
                    zzd = bundle.getInt("com.google.android.gms.version");
                } catch (PackageManager.NameNotFoundException e) {
                    Log.wtf("MetadataValueReader", "This should never happen.", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
