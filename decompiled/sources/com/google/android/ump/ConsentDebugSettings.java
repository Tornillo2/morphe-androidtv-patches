package com.google.android.ump;

import android.content.Context;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.consent_sdk.zzbx;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ConsentDebugSettings {
    public final boolean zza;
    public final int zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public final Context zzb;
        public boolean zzd;
        public final List<String> zza = new ArrayList();
        public int zzc = 0;

        public Builder(@RecentlyNonNull Context context) {
            this.zzb = context.getApplicationContext();
        }

        @RecentlyNonNull
        public Builder addTestDeviceHashedId(@RecentlyNonNull String str) {
            this.zza.add(str);
            return this;
        }

        @RecentlyNonNull
        public ConsentDebugSettings build() {
            Context context = this.zzb;
            List<String> list = this.zza;
            boolean z = true;
            if (!zzbx.zzb() && !list.contains(zzbx.zza(context)) && !this.zzd) {
                z = false;
            }
            return new ConsentDebugSettings(z, this, null);
        }

        @RecentlyNonNull
        public Builder setDebugGeography(int i) {
            this.zzc = i;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder setForceTesting(boolean z) {
            this.zzd = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface DebugGeography {
        public static final int DEBUG_GEOGRAPHY_DISABLED = 0;
        public static final int DEBUG_GEOGRAPHY_EEA = 1;
        public static final int DEBUG_GEOGRAPHY_NOT_EEA = 2;
    }

    public /* synthetic */ ConsentDebugSettings(boolean z, Builder builder, zza zzaVar) {
        this.zza = z;
        this.zzb = builder.zzc;
    }

    public int getDebugGeography() {
        return this.zzb;
    }

    public boolean isTestDevice() {
        return this.zza;
    }
}
