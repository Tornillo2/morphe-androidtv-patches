package com.google.android.gms.ads.query;

import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzbsr;
import com.google.android.gms.internal.ads.zzbss;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class ReportingInfo {
    public final zzbss zza;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static final class Builder {
        public final zzbsr zza;

        @KeepForSdk
        public Builder(@NonNull View view) {
            zzbsr zzbsrVar = new zzbsr();
            this.zza = zzbsrVar;
            zzbsrVar.zza = view;
        }

        @NonNull
        @KeepForSdk
        public ReportingInfo build() {
            return new ReportingInfo(this, null);
        }

        @NonNull
        @KeepForSdk
        public Builder setAssetViews(@NonNull Map<String, View> map) {
            this.zza.zzc(map);
            return this;
        }
    }

    public /* synthetic */ ReportingInfo(Builder builder, zzb zzbVar) {
        this.zza = new zzbss(builder.zza);
    }

    @KeepForSdk
    public void recordClick(@NonNull List<Uri> list) {
        this.zza.zza(list);
    }

    @KeepForSdk
    public void recordImpression(@NonNull List<Uri> list) {
        this.zza.zzb(list);
    }

    @KeepForSdk
    public void reportTouchEvent(@NonNull MotionEvent motionEvent) {
        this.zza.zzc(motionEvent);
    }

    @KeepForSdk
    public void updateClickUrl(@NonNull Uri uri, @NonNull UpdateClickUrlCallback updateClickUrlCallback) {
        this.zza.zzd(uri, updateClickUrlCallback);
    }

    @KeepForSdk
    public void updateImpressionUrls(@NonNull List<Uri> list, @NonNull UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        this.zza.zze(list, updateImpressionUrlsCallback);
    }
}
