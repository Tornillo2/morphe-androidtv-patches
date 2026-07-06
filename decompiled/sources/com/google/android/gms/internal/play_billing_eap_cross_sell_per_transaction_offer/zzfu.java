package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzfu {
    public volatile zzgj zza;
    public volatile zzeg zzb;
    public volatile boolean zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfu)) {
            return false;
        }
        zzfu zzfuVar = (zzfu) obj;
        zzgj zzgjVar = this.zza;
        zzgj zzgjVar2 = zzfuVar.zza;
        if (zzgjVar == null && zzgjVar2 == null) {
            return zzb().equals(zzfuVar.zzb());
        }
        if (zzgjVar != null && zzgjVar2 != null) {
            return zzgjVar.equals(zzgjVar2);
        }
        if (zzgjVar != null) {
            zzfuVar.zzd(zzgjVar.zzh());
            return zzgjVar.equals(zzfuVar.zza);
        }
        zzd(zzgjVar2.zzh());
        return this.zza.equals(zzgjVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzee) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzj();
        }
        return 0;
    }

    public final zzeg zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            try {
                if (this.zzb != null) {
                    return this.zzb;
                }
                if (this.zza == null) {
                    this.zzb = zzeg.zzb;
                } else {
                    this.zzb = this.zza.zzf();
                }
                return this.zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzgj zzc(zzgj zzgjVar) {
        zzgj zzgjVar2 = this.zza;
        this.zzb = null;
        this.zza = zzgjVar;
        return zzgjVar2;
    }

    public final void zzd(zzgj zzgjVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza != null) {
                return;
            }
            try {
                this.zza = zzgjVar;
                this.zzb = zzeg.zzb;
            } catch (zzfo unused) {
                this.zzc = true;
                this.zza = zzgjVar;
                this.zzb = zzeg.zzb;
            }
        }
    }
}
