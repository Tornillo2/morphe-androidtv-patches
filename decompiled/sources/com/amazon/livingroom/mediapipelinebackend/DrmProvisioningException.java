package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.Nullable;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DrmProvisioningException extends RuntimeException {
    public final int errorCode;

    public DrmProvisioningException(int i, @Nullable String str) {
        this(i, str, null);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public DrmProvisioningException(int i, @Nullable String str, @Nullable Throwable th) {
        super(String.format(Locale.US, "%s (errorCode=%d)", str, Integer.valueOf(i)), th);
        this.errorCode = i;
    }
}
