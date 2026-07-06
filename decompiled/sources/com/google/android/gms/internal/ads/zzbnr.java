package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzee;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbnr {
    public static zzbnr zza;
    public final AtomicBoolean zzb = new AtomicBoolean(false);

    @VisibleForTesting
    public zzbnr() {
    }

    public static zzbnr zza() {
        if (zza == null) {
            zza = new zzbnr();
        }
        return zza;
    }

    @Nullable
    public final Thread zzb(final Context context, @Nullable final String str) {
        if (!this.zzb.compareAndSet(false, true)) {
            return null;
        }
        Thread thread = new Thread(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbnq
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                String str2 = str;
                zzbbk.zza(context2);
                Bundle bundle = new Bundle();
                zzbbc zzbbcVar = zzbbk.zzai;
                zzba zzbaVar = zzba.zza;
                bundle.putBoolean("measurementEnabled", ((Boolean) zzbaVar.zzd.zzb(zzbbcVar)).booleanValue());
                if (((Boolean) zzbaVar.zzd.zzb(zzbbk.zzap)).booleanValue()) {
                    bundle.putString("ad_storage", "denied");
                    bundle.putString("analytics_storage", "denied");
                }
                try {
                    ((zzcgv) zzbzx.zzb(context2, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", zzbnp.zza)).zze(ObjectWrapper.wrap(context2), new zzbno(zzee.zzg(context2, "FA-Ads", "am", str2, bundle).zze));
                } catch (RemoteException | zzbzw | NullPointerException e) {
                    zzbzt.zzl("#007 Could not call remote method.", e);
                }
            }
        });
        thread.start();
        return thread;
    }
}
