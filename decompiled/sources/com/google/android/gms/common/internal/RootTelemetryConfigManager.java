package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class RootTelemetryConfigManager {

    @Nullable
    public static RootTelemetryConfigManager zza;
    public static final RootTelemetryConfiguration zzb = new RootTelemetryConfiguration(0, false, false, 0, 0);

    @Nullable
    public RootTelemetryConfiguration zzc;

    @NonNull
    @KeepForSdk
    public static synchronized RootTelemetryConfigManager getInstance() {
        try {
            if (zza == null) {
                zza = new RootTelemetryConfigManager();
            }
        } catch (Throwable th) {
            throw th;
        }
        return zza;
    }

    @Nullable
    @KeepForSdk
    public RootTelemetryConfiguration getConfig() {
        return this.zzc;
    }

    @VisibleForTesting
    public final synchronized void zza(@Nullable RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.zzc = zzb;
            return;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration2 = this.zzc;
        if (rootTelemetryConfiguration2 == null || rootTelemetryConfiguration2.getVersion() < rootTelemetryConfiguration.getVersion()) {
            this.zzc = rootTelemetryConfiguration;
        }
    }
}
