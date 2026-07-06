package com.google.android.gms.ads.internal.client;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.OnPaidEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfe extends zzdf {

    @Nullable
    public final OnPaidEventListener zza;

    public zzfe(@Nullable OnPaidEventListener onPaidEventListener) {
        this.zza = onPaidEventListener;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdg
    public final void zze(zzs zzsVar) {
        OnPaidEventListener onPaidEventListener = this.zza;
        if (onPaidEventListener != null) {
            onPaidEventListener.onPaidEvent(new AdValue(zzsVar.zzb, zzsVar.zzc, zzsVar.zzd));
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzdg
    public final boolean zzf() {
        return this.zza == null;
    }
}
