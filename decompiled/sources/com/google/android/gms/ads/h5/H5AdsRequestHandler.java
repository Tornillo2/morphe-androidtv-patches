package com.google.android.gms.ads.h5;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.gms.internal.ads.zzbjm;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 21)
public final class H5AdsRequestHandler {
    public final zzbjm zza;

    public H5AdsRequestHandler(@NonNull Context context, @NonNull OnH5AdsEventListener onH5AdsEventListener) {
        this.zza = new zzbjm(context, onH5AdsEventListener);
    }

    public void clearAdObjects() {
        this.zza.zza();
    }

    public boolean handleH5AdsRequest(@NonNull String str) {
        return this.zza.zzb(str);
    }

    public boolean shouldInterceptRequest(@NonNull String str) {
        return zzbjm.zzc(str);
    }
}
