package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbsm {
    public static zzbyk zza;
    public final Context zzb;
    public final AdFormat zzc;

    @Nullable
    public final zzdx zzd;

    public zzbsm(Context context, AdFormat adFormat, @Nullable zzdx zzdxVar) {
        this.zzb = context;
        this.zzc = adFormat;
        this.zzd = zzdxVar;
    }

    @Nullable
    public static zzbyk zza(Context context) {
        zzbyk zzbykVar;
        synchronized (zzbsm.class) {
            try {
                if (zza == null) {
                    zza = zzay.zza().zzr(context, new zzbnv());
                }
                zzbykVar = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzbykVar;
    }

    public final void zzb(QueryInfoGenerationCallback queryInfoGenerationCallback) {
        zzbyk zzbykVarZza = zza(this.zzb);
        if (zzbykVarZza == null) {
            queryInfoGenerationCallback.getClass();
            return;
        }
        IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(this.zzb);
        zzdx zzdxVar = this.zzd;
        try {
            zzbykVarZza.zze(iObjectWrapperWrap, new zzbyo(null, this.zzc.name(), null, zzdxVar == null ? new zzm().zza() : zzp.zza.zza(this.zzb, zzdxVar)), new zzbsl(this, queryInfoGenerationCallback));
        } catch (RemoteException unused) {
            queryInfoGenerationCallback.getClass();
        }
    }
}
