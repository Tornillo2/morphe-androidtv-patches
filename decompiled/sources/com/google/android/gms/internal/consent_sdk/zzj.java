package com.google.android.gms.internal.consent_sdk;

import android.util.Log;
import com.google.android.ump.FormError;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzj extends Exception {
    public final int zza;

    public zzj(int i, String str) {
        super(str);
        this.zza = i;
    }

    public final FormError zza() {
        if (getCause() == null) {
            Log.w("UserMessagingPlatform", getMessage());
        } else {
            Log.w("UserMessagingPlatform", getMessage(), getCause());
        }
        return new FormError(this.zza, getMessage());
    }

    public zzj(int i, String str, Throwable th) {
        super(str, th);
        this.zza = i;
    }
}
