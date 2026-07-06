package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.content.Context;
import androidx.annotation.GuardedBy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzd {

    @GuardedBy("AppComponent.class")
    public static zzd zza;

    public static zzd zza(Context context) {
        zzd zzdVar;
        synchronized (zzd.class) {
            try {
                if (zza == null) {
                    zzag zzagVar = new zzag();
                    zzagVar.zzb((Application) context.getApplicationContext());
                    zza = zzagVar.zza();
                }
                zzdVar = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzdVar;
    }

    public abstract zzk zzb();

    public abstract zzba zzc();
}
