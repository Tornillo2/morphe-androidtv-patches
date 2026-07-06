package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzl extends zzd {
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final void zza(zzm zzmVar, zzm zzmVar2) {
        zzmVar.zzc = zzmVar2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final void zzb(zzm zzmVar, Thread thread) {
        zzmVar.zzb = thread;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zzc(zzo zzoVar, zzh zzhVar, zzh zzhVar2) {
        synchronized (zzoVar) {
            try {
                if (zzoVar.zzd != zzhVar) {
                    return false;
                }
                zzoVar.zzd = zzhVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zzd(zzo zzoVar, Object obj, Object obj2) {
        synchronized (zzoVar) {
            try {
                if (zzoVar.zzc != obj) {
                    return false;
                }
                zzoVar.zzc = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zze(zzo zzoVar, zzm zzmVar, zzm zzmVar2) {
        synchronized (zzoVar) {
            try {
                if (zzoVar.zze != zzmVar) {
                    return false;
                }
                zzoVar.zze = zzmVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
