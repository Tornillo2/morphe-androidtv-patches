package com.google.android.gms.internal.base;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zan {
    @ChecksSdkIntAtLeast(api = 33)
    public static boolean zaa() {
        return Build.VERSION.SDK_INT >= 33;
    }
}
