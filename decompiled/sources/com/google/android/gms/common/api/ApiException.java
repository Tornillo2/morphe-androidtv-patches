package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ApiException extends Exception {

    @NonNull
    @Deprecated
    public final Status mStatus;

    /* JADX WARN: Illegal instructions before constructor call */
    public ApiException(@NonNull Status status) {
        int i = status.zzb;
        String str = status.zzc;
        super(i + ": " + (str == null ? "" : str));
        this.mStatus = status;
    }

    @NonNull
    public Status getStatus() {
        return this.mStatus;
    }

    public int getStatusCode() {
        return this.mStatus.zzb;
    }

    @Nullable
    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.zzc;
    }
}
