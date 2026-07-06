package com.google.android.gms.ads.rewarded;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ServerSideVerificationOptions {
    public final String zza;
    public final String zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public String zza = "";
        public String zzb = "";

        @NonNull
        public ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this, null);
        }

        @NonNull
        public Builder setCustomData(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setUserId(@NonNull String str) {
            this.zza = str;
            return this;
        }
    }

    public /* synthetic */ ServerSideVerificationOptions(Builder builder, zzd zzdVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
    }

    @NonNull
    public String getCustomData() {
        return this.zzb;
    }

    @NonNull
    public String getUserId() {
        return this.zza;
    }
}
