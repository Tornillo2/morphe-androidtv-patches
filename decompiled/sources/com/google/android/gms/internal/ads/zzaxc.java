package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaxc {
    public final /* synthetic */ zzaxd zza;
    public final byte[] zzb;
    public int zzc;

    public /* synthetic */ zzaxc(zzaxd zzaxdVar, byte[] bArr, zzaxb zzaxbVar) {
        this.zza = zzaxdVar;
        this.zzb = bArr;
    }

    public final zzaxc zza(int i) {
        this.zzc = i;
        return this;
    }

    public final synchronized void zzc() {
        this.zza.zzc.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzaxa
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzd();
            }
        });
    }

    public final synchronized void zzd() {
        try {
            zzaxd zzaxdVar = this.zza;
            if (zzaxdVar.zzb) {
                zzaxdVar.zza.zzj(this.zzb);
                this.zza.zza.zzi(0);
                this.zza.zza.zzg(this.zzc);
                this.zza.zza.zzh(null);
                this.zza.zza.zzf();
            }
        } catch (RemoteException e) {
            zzbzt.zzf("Clearcut log failed", e);
        }
    }
}
