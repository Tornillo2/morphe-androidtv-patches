package com.google.android.gms.ads.admanager;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdRequest;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdManagerAdRequest extends AdRequest {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends AdRequest.Builder {
        @NonNull
        public Builder addCategoryExclusion(@NonNull String str) {
            this.zza.zzp(str);
            return this;
        }

        @NonNull
        public Builder addCustomTargeting(@NonNull String str, @NonNull String str2) {
            this.zza.zzr(str, str2);
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        @NonNull
        public final AdRequest build() {
            return new AdManagerAdRequest(this);
        }

        @NonNull
        public Builder setPublisherProvidedId(@NonNull String str) {
            this.zza.zzk = str;
            return this;
        }

        @NonNull
        public Builder addCustomTargeting(@NonNull String str, @NonNull List<String> list) {
            if (list != null) {
                this.zza.zzr(str, TextUtils.join(",", list));
            }
            return this;
        }

        @Override // com.google.android.gms.ads.AdRequest.Builder
        @NonNull
        public AdManagerAdRequest build() {
            return new AdManagerAdRequest(this);
        }
    }

    public /* synthetic */ AdManagerAdRequest(Builder builder, zza zzaVar) {
        super(builder);
    }

    @Override // com.google.android.gms.ads.AdRequest
    @NonNull
    public Bundle getCustomTargeting() {
        return this.zza.zzm;
    }

    @NonNull
    public String getPublisherProvidedId() {
        return this.zza.zzh;
    }
}
