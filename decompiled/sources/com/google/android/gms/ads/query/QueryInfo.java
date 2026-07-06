package com.google.android.gms.ads.query;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzem;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbdb;
import com.google.android.gms.internal.ads.zzbsm;
import com.google.android.gms.internal.ads.zzbzi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class QueryInfo {
    public final zzem zza;

    public QueryInfo(zzem zzemVar) {
        this.zza = zzemVar;
    }

    public static void generate(@NonNull final Context context, @NonNull final AdFormat adFormat, @Nullable final AdRequest adRequest, @NonNull final QueryInfoGenerationCallback queryInfoGenerationCallback) {
        zzbbk.zza(context);
        if (((Boolean) zzbdb.zzk.zze()).booleanValue()) {
            if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjG)).booleanValue()) {
                zzbzi.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.query.zza
                    @Override // java.lang.Runnable
                    public final void run() {
                        Context context2 = context;
                        AdFormat adFormat2 = adFormat;
                        AdRequest adRequest2 = adRequest;
                        new zzbsm(context2, adFormat2, adRequest2 == null ? null : adRequest2.zza).zzb(queryInfoGenerationCallback);
                    }
                });
                return;
            }
        }
        new zzbsm(context, adFormat, adRequest == null ? null : adRequest.zza).zzb(queryInfoGenerationCallback);
    }

    @NonNull
    public String getQuery() {
        return this.zza.zza;
    }

    @NonNull
    @KeepForSdk
    public Bundle getQueryBundle() {
        return this.zza.zzb;
    }

    @NonNull
    @KeepForSdk
    public String getRequestId() {
        return this.zza.zzc();
    }
}
