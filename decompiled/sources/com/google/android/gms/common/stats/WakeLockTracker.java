package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@Deprecated
public class WakeLockTracker {
    public static final WakeLockTracker zza = new WakeLockTracker();

    @NonNull
    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return zza;
    }

    @KeepForSdk
    public void registerEvent(@NonNull Context context, @NonNull String str, int i, @NonNull String str2, @NonNull String str3, @NonNull String str4, int i2, @NonNull List<String> list) {
    }

    @KeepForSdk
    public void registerEvent(@NonNull Context context, @NonNull String str, int i, @NonNull String str2, @NonNull String str3, @NonNull String str4, int i2, @NonNull List<String> list, long j) {
    }

    @KeepForSdk
    public void registerReleaseEvent(@NonNull Context context, @NonNull Intent intent) {
    }

    @KeepForSdk
    public void registerDeadlineEvent(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, int i, @NonNull List<String> list, boolean z, long j) {
    }

    @KeepForSdk
    public void registerAcquireEvent(@NonNull Context context, @NonNull Intent intent, @NonNull String str, @NonNull String str2, @NonNull String str3, int i, @NonNull String str4) {
    }
}
