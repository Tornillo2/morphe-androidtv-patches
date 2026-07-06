package com.android.billingclient.api;

import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class InAppMessageResult {
    public final int zza;

    @Nullable
    public final String zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface InAppMessageResponseCode {
        public static final int NO_ACTION_NEEDED = 0;
        public static final int SUBSCRIPTION_STATUS_UPDATED = 1;
    }

    public InAppMessageResult(int i, @Nullable String str) {
        this.zza = i;
        this.zzb = str;
    }

    @Nullable
    public String getPurchaseToken() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }
}
