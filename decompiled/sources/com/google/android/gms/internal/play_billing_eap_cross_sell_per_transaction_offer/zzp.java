package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzp {
    public Object zza;
    public zzt zzb;
    public zzv zzc = zzv.zze();
    public boolean zzd;

    public final void finalize() {
        zzv zzvVar;
        zzt zztVar = this.zzb;
        if (zztVar != null && !zztVar.zzb.isDone()) {
            zztVar.zzc(new zzq("The completer object was garbage collected - this future would otherwise never complete. The tag was: ".concat(String.valueOf(this.zza))));
        }
        if (this.zzd || (zzvVar = this.zzc) == null) {
            return;
        }
        zzvVar.zzd(null);
    }

    public final void zza() {
        this.zza = null;
        this.zzb = null;
        this.zzc.zzd(null);
    }

    public final boolean zzb(Object obj) {
        this.zzd = true;
        zzt zztVar = this.zzb;
        boolean z = zztVar != null && zztVar.zzb.zzd(obj);
        if (z) {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
        return z;
    }
}
