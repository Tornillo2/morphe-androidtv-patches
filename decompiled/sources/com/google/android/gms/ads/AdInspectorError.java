package com.google.android.gms.ads;

import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdInspectorError extends AdError {
    public static final int ERROR_CODE_ALREADY_OPEN = 3;
    public static final int ERROR_CODE_FAILED_TO_LOAD = 1;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_NOT_IN_TEST_MODE = 2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdInspectorErrorCode {
    }

    public AdInspectorError(int i, @NonNull String str, @NonNull String str2) {
        super(i, str, str2, null);
    }

    @Override // com.google.android.gms.ads.AdError
    public int getCode() {
        return this.zza;
    }
}
