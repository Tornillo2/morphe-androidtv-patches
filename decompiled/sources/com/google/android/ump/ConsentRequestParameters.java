package com.google.android.ump;

import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ConsentRequestParameters {
    public final boolean zza;

    @Nullable
    public final String zzb;

    @Nullable
    public final ConsentDebugSettings zzc;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean zza;

        @Nullable
        public String zzb;

        @Nullable
        public ConsentDebugSettings zzc;

        @RecentlyNonNull
        public ConsentRequestParameters build() {
            return new ConsentRequestParameters(this, null);
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder setAdMobAppId(@Nullable String str) {
            this.zzb = str;
            return this;
        }

        @RecentlyNonNull
        public Builder setConsentDebugSettings(@Nullable ConsentDebugSettings consentDebugSettings) {
            this.zzc = consentDebugSettings;
            return this;
        }

        @RecentlyNonNull
        public Builder setTagForUnderAgeOfConsent(boolean z) {
            this.zza = z;
            return this;
        }
    }

    public /* synthetic */ ConsentRequestParameters(Builder builder, zzb zzbVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = builder.zzc;
    }

    @RecentlyNullable
    public ConsentDebugSettings getConsentDebugSettings() {
        return this.zzc;
    }

    public boolean isTagForUnderAgeOfConsent() {
        return this.zza;
    }

    @RecentlyNullable
    public final String zza() {
        return this.zzb;
    }
}
