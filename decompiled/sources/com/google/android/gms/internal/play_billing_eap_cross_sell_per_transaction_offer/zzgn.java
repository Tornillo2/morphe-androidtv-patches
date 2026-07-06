package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgn implements zzgt {
    public final zzgj zza;
    public final zzhf zzb;
    public final boolean zzc;
    public final zzet zzd;

    public zzgn(zzhf zzhfVar, zzet zzetVar, zzgj zzgjVar) {
        this.zzb = zzhfVar;
        this.zzc = zzgjVar instanceof zzfd;
        this.zzd = zzetVar;
        this.zza = zzgjVar;
    }

    public static zzgn zzc(zzhf zzhfVar, zzet zzetVar, zzgj zzgjVar) {
        return new zzgn(zzhfVar, zzetVar, zzgjVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final int zza(Object obj) {
        int iZzb = ((zzfg) obj).zzc.zzb();
        if (this.zzc) {
            ((zzfd) obj).zzb.zzd();
        }
        return iZzb;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final int zzb(Object obj) {
        int iHashCode = ((zzfg) obj).zzc.hashCode();
        if (!this.zzc) {
            return iHashCode;
        }
        return ((zzfd) obj).zzb.zza.hashCode() + (iHashCode * 53);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final Object zze() {
        zzgj zzgjVar = this.zza;
        return zzgjVar instanceof zzfg ? ((zzfg) zzgjVar).zzo() : zzgjVar.zzK().zzg();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final void zzf(Object obj) {
        this.zzb.zzb(obj);
        this.zzd.zza(obj);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final void zzg(Object obj, Object obj2) {
        zzgv.zzp(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgv.zzo(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzdu zzduVar) throws IOException {
        zzfg zzfgVar = (zzfg) obj;
        if (zzfgVar.zzc == zzhg.zza) {
            zzfgVar.zzc = zzhg.zzf();
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final void zzi(Object obj, zzhs zzhsVar) throws IOException {
        Iterator itZzf = ((zzfd) obj).zzb.zzf();
        if (itZzf.hasNext()) {
            ((zzew) ((Map.Entry) itZzf.next()).getKey()).zzc();
            throw null;
        }
        ((zzfg) obj).zzc.zzk(zzhsVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final boolean zzj(Object obj, Object obj2) {
        if (!((zzfg) obj).zzc.equals(((zzfg) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzfd) obj).zzb.equals(((zzfd) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgt
    public final boolean zzk(Object obj) {
        ((zzfd) obj).zzb.zzi();
        return true;
    }
}
