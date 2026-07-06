package com.android.billingclient.api;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzht;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhx;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzih;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzio;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zziq;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zziy;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzje;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjg;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjm;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjq;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcr implements zzcn {
    public zziq zzb;
    public final zzct zzc;

    public zzcr(Context context, zziq zziqVar) {
        this.zzc = new zzct(context);
        this.zzb = zziqVar;
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zza(@Nullable zzhv zzhvVar) {
        try {
            zzl(zzhvVar, this.zzb);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzb(@Nullable zzhv zzhvVar, int i) {
        try {
            zzio zzioVar = (zzio) this.zzb.zzm();
            zzioVar.zzm(i);
            this.zzb = (zziq) zzioVar.zze();
            zza(zzhvVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzc(zzhv zzhvVar, int i, long j) {
        try {
            zzio zzioVar = (zzio) this.zzb.zzm();
            zzioVar.zzm(i);
            zziq zziqVar = (zziq) zzioVar.zze();
            this.zzb = zziqVar;
            if (j != 0) {
                zzio zzioVar2 = (zzio) zziqVar.zzm();
                zzioVar2.zzo(j);
                zziqVar = (zziq) zzioVar2.zze();
            }
            zzl(zzhvVar, zziqVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzd(zzhv zzhvVar, long j, boolean z) {
        zziq zziqVar;
        try {
            zzht zzhtVar = (zzht) zzhvVar.zzm();
            zziy zziyVar = (zziy) zzhvVar.zzB().zzm();
            zziyVar.zza(z);
            zzhtVar.zzn(zziyVar);
            zzhv zzhvVar2 = (zzhv) zzhtVar.zze();
            if (j == 0) {
                zziqVar = this.zzb;
            } else {
                zzio zzioVar = (zzio) this.zzb.zzm();
                zzioVar.zzo(j);
                zziqVar = (zziq) zzioVar.zze();
            }
            zzl(zzhvVar2, zziqVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zze(zzhv zzhvVar, int i, long j, boolean z) {
        zziq zziqVar;
        try {
            zzio zzioVar = (zzio) this.zzb.zzm();
            zzioVar.zzm(i);
            this.zzb = (zziq) zzioVar.zze();
            zzht zzhtVar = (zzht) zzhvVar.zzm();
            zziy zziyVar = (zziy) zzhvVar.zzB().zzm();
            zziyVar.zza(z);
            zzhtVar.zzn(zziyVar);
            zzhv zzhvVar2 = (zzhv) zzhtVar.zze();
            if (j == 0) {
                zziqVar = this.zzb;
            } else {
                zzio zzioVar2 = (zzio) this.zzb.zzm();
                zzioVar2.zzo(j);
                zziqVar = (zziq) zzioVar2.zze();
            }
            zzl(zzhvVar2, zziqVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzf(@Nullable zzhz zzhzVar) {
        try {
            zzm(zzhzVar, this.zzb);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzg(@Nullable zzhz zzhzVar, int i) {
        try {
            zzio zzioVar = (zzio) this.zzb.zzm();
            zzioVar.zzm(i);
            this.zzb = (zziq) zzioVar.zze();
            zzf(zzhzVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzh(zzhz zzhzVar, long j, boolean z) {
        zziq zziqVar;
        try {
            zzhx zzhxVar = (zzhx) zzhzVar.zzm();
            zziy zziyVar = (zziy) zzhzVar.zzA().zzm();
            zziyVar.zza(z);
            zzhxVar.zzm(zziyVar);
            zzhz zzhzVar2 = (zzhz) zzhxVar.zze();
            if (j == 0) {
                zziqVar = this.zzb;
            } else {
                zzio zzioVar = (zzio) this.zzb.zzm();
                zzioVar.zzo(j);
                zziqVar = (zziq) zzioVar.zze();
            }
            zzm(zzhzVar2, zziqVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzi(zzih zzihVar) {
        try {
            zzje zzjeVarZzc = zzjg.zzc();
            zzjeVarZzc.zzn(this.zzb);
            zzjeVarZzc.zzm(zzihVar);
            this.zzc.zza((zzjg) zzjeVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzj(zzjm zzjmVar) {
        try {
            zzct zzctVar = this.zzc;
            zzje zzjeVarZzc = zzjg.zzc();
            zzjeVarZzc.zzn(this.zzb);
            zzjeVarZzc.zzo(zzjmVar);
            zzctVar.zza((zzjg) zzjeVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzcn
    public final void zzk(@Nullable zzjq zzjqVar) {
        if (zzjqVar == null) {
            return;
        }
        try {
            zzje zzjeVarZzc = zzjg.zzc();
            zzjeVarZzc.zzn(this.zzb);
            zzjeVarZzc.zzp(zzjqVar);
            this.zzc.zza((zzjg) zzjeVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    public final void zzl(@Nullable zzhv zzhvVar, zziq zziqVar) {
        if (zzhvVar == null) {
            return;
        }
        try {
            zzje zzjeVarZzc = zzjg.zzc();
            zzjeVarZzc.zzn(zziqVar);
            zzjeVarZzc.zza(zzhvVar);
            this.zzc.zza((zzjg) zzjeVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }

    public final void zzm(@Nullable zzhz zzhzVar, zziq zziqVar) {
        if (zzhzVar == null) {
            return;
        }
        try {
            zzje zzjeVarZzc = zzjg.zzc();
            zzjeVarZzc.zzn(zziqVar);
            zzjeVarZzc.zzl(zzhzVar);
            this.zzc.zza((zzjg) zzjeVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to log.", th);
        }
    }
}
