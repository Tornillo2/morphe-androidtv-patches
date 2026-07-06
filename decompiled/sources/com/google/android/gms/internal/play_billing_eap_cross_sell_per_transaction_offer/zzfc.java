package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfc;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzfc<MessageType extends zzfg<MessageType, BuilderType>, BuilderType extends zzfc<MessageType, BuilderType>> extends zzdp<MessageType, BuilderType> {
    public zzfg zza;
    public final zzfg zzb;

    public zzfc(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzz()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzo();
    }

    public static void zza(Object obj, Object obj2) {
        zzgq.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzdp
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzfc clone() {
        zzfc zzfcVar = (zzfc) this.zzb.zzb(5, null, null);
        zzfcVar.zza = zzg();
        return zzfcVar;
    }

    public final zzfc zzd(zzfg zzfgVar) {
        if (!this.zzb.equals(zzfgVar)) {
            if (!this.zza.zzz()) {
                zzj();
            }
            zza(this.zza, zzfgVar);
        }
        return this;
    }

    public final MessageType zze() {
        MessageType messagetype = (MessageType) zzg();
        messagetype.getClass();
        if (zzfg.zzA(messagetype, true)) {
            return messagetype;
        }
        throw new zzhe(messagetype);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgi
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public MessageType zzg() {
        if (!this.zza.zzz()) {
            return (MessageType) this.zza;
        }
        this.zza.zzu();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgk
    public final /* bridge */ /* synthetic */ zzgj zzh() {
        throw null;
    }

    public final void zzi() {
        if (this.zza.zzz()) {
            return;
        }
        zzj();
    }

    public void zzj() {
        zzfg zzfgVarZzo = this.zzb.zzo();
        zza(zzfgVarZzo, this.zza);
        this.zza = zzfgVarZzo;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgk
    public final boolean zzk() {
        return zzfg.zzA(this.zza, false);
    }
}
