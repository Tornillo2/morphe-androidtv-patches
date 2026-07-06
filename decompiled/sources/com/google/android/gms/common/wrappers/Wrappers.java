package com.google.android.gms.common.wrappers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class Wrappers {
    public static final Wrappers zza = new Wrappers();

    @Nullable
    public PackageManagerWrapper zzb = null;

    @NonNull
    @KeepForSdk
    public static PackageManagerWrapper packageManager(@NonNull Context context) {
        return zza.zza(context);
    }

    @NonNull
    @VisibleForTesting
    public final synchronized PackageManagerWrapper zza(@NonNull Context context) {
        try {
            if (this.zzb == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.zzb = new PackageManagerWrapper(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.zzb;
    }
}
