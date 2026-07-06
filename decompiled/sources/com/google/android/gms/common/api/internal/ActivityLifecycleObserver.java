package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public abstract class ActivityLifecycleObserver {
    @NonNull
    @KeepForSdk
    public static final ActivityLifecycleObserver of(@NonNull Activity activity) {
        return new zab(zaa.zaa(activity));
    }

    @NonNull
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(@NonNull Runnable runnable);
}
