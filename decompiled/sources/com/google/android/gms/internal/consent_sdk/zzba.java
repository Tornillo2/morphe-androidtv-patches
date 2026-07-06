package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.UserMessagingPlatform;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzba {
    public final zzcl<zzas> zza;
    public final AtomicReference<zzbc> zzb = new AtomicReference<>();

    public zzba(zzcl<zzas> zzclVar) {
        this.zza = zzclVar;
    }

    public final void zza(UserMessagingPlatform.OnConsentFormLoadSuccessListener onConsentFormLoadSuccessListener, UserMessagingPlatform.OnConsentFormLoadFailureListener onConsentFormLoadFailureListener) {
        zzcd.zza();
        zzbc zzbcVar = this.zzb.get();
        if (zzbcVar == null) {
            onConsentFormLoadFailureListener.onConsentFormLoadFailure(new zzj(3, "No available form can be built.").zza());
            return;
        }
        zzas zzasVarZzb = this.zza.zzb();
        zzasVarZzb.zza(zzbcVar);
        ((zzai) ((zzah) zzasVarZzb).zzb()).zza().zzb(onConsentFormLoadSuccessListener, onConsentFormLoadFailureListener);
    }

    public final void zzb(zzbc zzbcVar) {
        this.zzb.set(zzbcVar);
    }

    public final boolean zzc() {
        return this.zzb.get() != null;
    }
}
