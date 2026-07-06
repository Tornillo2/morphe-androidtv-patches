package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzht;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhx;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzia;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzie;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzih;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjk;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjm;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjq;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjr;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjt;
import j$.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbl implements ServiceConnection {
    public final /* synthetic */ BillingClientImpl zza;
    public final BillingClientStateListener zzb;
    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzc;
    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzd;
    public final int zze;

    public zzbl(BillingClientImpl billingClientImpl, BillingClientStateListener billingClientStateListener, int i, zzbv zzbvVar) {
        Objects.requireNonNull(billingClientImpl);
        this.zza = billingClientImpl;
        this.zzc = new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg(billingClientImpl.zzJ);
        this.zzd = new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg(billingClientImpl.zzJ);
        this.zzb = billingClientStateListener;
        this.zze = i;
    }

    public static /* synthetic */ Object zza(zzbl zzblVar) {
        Bundle bundle;
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        BillingClientImpl billingClientImpl = zzblVar.zza;
        synchronized (billingClientImpl.zza) {
            try {
                if (billingClientImpl.zzb != 3) {
                    boolean z = billingClientImpl.zzb == 1;
                    if (TextUtils.isEmpty(null)) {
                        bundle = null;
                    } else {
                        bundle = new Bundle();
                        bundle.putString("accountName", null);
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzc(bundle, billingClientImpl.zzc, billingClientImpl.zzd, billingClientImpl.zzI.longValue());
                    }
                    zzic zzicVar = zzic.REASON_UNSPECIFIED;
                    synchronized (billingClientImpl.zza) {
                        zzamVar = billingClientImpl.zzi;
                    }
                    if (zzamVar == null) {
                        BillingClientImpl billingClientImpl2 = zzblVar.zza;
                        billingClientImpl2.zzaS(0);
                        int i = zzblVar.zze;
                        zzic zzicVar2 = zzic.SERVICE_RESET_TO_NULL;
                        BillingResult billingResult = zzcp.zzj;
                        billingClientImpl2.zzaR(zzicVar2, billingResult, i);
                        zzblVar.zzg(billingResult);
                    } else {
                        BillingClientImpl billingClientImpl3 = zzblVar.zza;
                        String packageName = billingClientImpl3.zzg.getPackageName();
                        int i2 = 25;
                        int i3 = 25;
                        int iZzw = 3;
                        while (true) {
                            if (i3 < 3) {
                                i3 = 0;
                                break;
                            }
                            if (bundle == null) {
                                try {
                                    iZzw = zzamVar.zzw(i3, packageName, "subs");
                                } catch (Exception e) {
                                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                                    zzic zzicVar3 = e instanceof DeadObjectException ? zzic.IS_BILLING_SUPPORTED_DEAD_OBJECT_EXCEPTION : e instanceof RemoteException ? zzic.IS_BILLING_SUPPORTED_REMOTE_EXCEPTION : e instanceof SecurityException ? zzic.IS_BILLING_SUPPORTED_SECURITY_EXCEPTION : zzic.IS_BILLING_SUPPORTED_SERVICE_CALL_EXCEPTION;
                                    String strZza = zzicVar3.equals(zzic.IS_BILLING_SUPPORTED_SERVICE_CALL_EXCEPTION) ? zzcm.zza(e) : null;
                                    zzblVar.zza.zzaS(0);
                                    zzblVar.zzf(BillingClientImpl.zzl(e), zzicVar3, strZza, z);
                                    zzblVar.zzg(BillingClientImpl.zzl(e));
                                }
                            } else {
                                iZzw = zzamVar.zzc(i3, packageName, "subs", bundle);
                            }
                            if (iZzw == 0) {
                                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "highestLevelSupportedForSubs: " + i3);
                                break;
                            }
                            i3--;
                        }
                        billingClientImpl3.zzl = i3 >= 5;
                        billingClientImpl3.zzk = i3 >= 3;
                        if (i3 < 3) {
                            zzicVar = zzic.SUBSCRIPTIONS_NOT_SUPPORTED;
                            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "In-app billing API does not support subscription on this device.");
                        }
                        while (true) {
                            if (i2 < 3) {
                                break;
                            }
                            iZzw = bundle == null ? zzamVar.zzw(i2, packageName, "inapp") : zzamVar.zzc(i2, packageName, "inapp", bundle);
                            if (iZzw == 0) {
                                billingClientImpl3.zzm = i2;
                                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "mHighestLevelSupportedForInApp: " + i2);
                                break;
                            }
                            i2--;
                        }
                        BillingClientImpl.zzac(billingClientImpl3, billingClientImpl3.zzm);
                        if (billingClientImpl3.zzm < 3) {
                            zzicVar = zzic.ONE_TIME_PRODUCT_NOT_SUPPORTED;
                            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "In-app billing API version 3 is not supported on this device.");
                        }
                        BillingClientImpl.zzae(billingClientImpl3, iZzw);
                        if (iZzw != 0) {
                            BillingResult billingResult2 = zzcp.zzb;
                            zzblVar.zzf(billingResult2, zzicVar, null, z);
                            zzblVar.zzg(billingResult2);
                        } else {
                            try {
                                Long lZze = zzblVar.zze(z);
                                if (z) {
                                    zzhx zzhxVarZzc = zzhz.zzc();
                                    zzhxVarZzc.zzo(6);
                                    zzjr zzjrVarZzc = zzjt.zzc();
                                    int i4 = zzblVar.zze;
                                    zzjrVarZzc.zza(i4 > 0);
                                    zzjrVarZzc.zzl(i4);
                                    if (lZze != null) {
                                        zzjrVarZzc.zzm(lZze.longValue());
                                    }
                                    BillingClientImpl billingClientImpl4 = zzblVar.zza;
                                    zzhxVarZzc.zzn(zzjrVarZzc);
                                    billingClientImpl4.zzaQ((zzhz) zzhxVarZzc.zze());
                                } else {
                                    zzjk zzjkVarZzc = zzjm.zzc();
                                    zzia zziaVarZzc = zzie.zzc();
                                    zziaVarZzc.zzo(0);
                                    zzjkVarZzc.zza(zziaVarZzc);
                                    if (lZze != null) {
                                        zzjkVarZzc.zzl(lZze.longValue());
                                    }
                                    zzblVar.zza.zzh.zzj((zzjm) zzjkVarZzc.zze());
                                }
                            } catch (Throwable th) {
                                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
                            }
                            zzblVar.zzg(zzcp.zzi);
                        }
                    }
                }
            } finally {
            }
        }
        return null;
    }

    public static /* synthetic */ void zzb(zzbl zzblVar) {
        BillingClientImpl billingClientImpl = zzblVar.zza;
        billingClientImpl.zzaS(0);
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzaR(zzicVar, billingResult, zzblVar.zze);
        zzblVar.zzg(billingResult);
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Billing service died.");
        try {
            BillingClientImpl billingClientImpl = this.zza;
            if (BillingClientImpl.zzai(billingClientImpl)) {
                zzcn zzcnVar = billingClientImpl.zzh;
                zzht zzhtVarZzc = zzhv.zzc();
                zzhtVarZzc.zzp(6);
                zzia zziaVarZzc = zzie.zzc();
                zziaVarZzc.zzn(zzic.BINDING_DIED);
                zzhtVarZzc.zzl(zziaVarZzc);
                zzjr zzjrVarZzc = zzjt.zzc();
                int i = this.zze;
                zzjrVarZzc.zza(i > 0);
                zzjrVarZzc.zzl(i);
                zzhtVarZzc.zzo(zzjrVarZzc);
                zzcnVar.zza((zzhv) zzhtVarZzc.zze());
            } else {
                billingClientImpl.zzh.zzi(zzih.zzd());
            }
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
        BillingClientImpl billingClientImpl2 = this.zza;
        synchronized (billingClientImpl2.zza) {
            if (billingClientImpl2.zzb != 3 && billingClientImpl2.zzb != 0) {
                billingClientImpl2.zzaS(0);
                billingClientImpl2.zzaV();
                try {
                    this.zzb.onBillingServiceDisconnected();
                } catch (Throwable th2) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception while calling onBillingServiceDisconnected.", th2);
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Billing service connected.");
        BillingClientImpl billingClientImpl = this.zza;
        synchronized (billingClientImpl.zza) {
            try {
                if (billingClientImpl.zzb == 3) {
                    return;
                }
                billingClientImpl.zzi = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzal.zzs(iBinder);
                if (BillingClientImpl.zzG(new Callable() { // from class: com.android.billingclient.api.zzbj
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        zzbl.zza(this.zza);
                        return null;
                    }
                }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzbk
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbl.zzb(this.zza);
                    }
                }, billingClientImpl.zzan(), billingClientImpl.zzF()) == null) {
                    int i = this.zze;
                    BillingResult billingResultZzaq = billingClientImpl.zzaq();
                    billingClientImpl.zzaR(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, billingResultZzaq, i);
                    zzg(billingResultZzaq);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Billing service disconnected.");
        try {
            BillingClientImpl billingClientImpl = this.zza;
            if (BillingClientImpl.zzai(billingClientImpl)) {
                zzcn zzcnVar = billingClientImpl.zzh;
                zzht zzhtVarZzc = zzhv.zzc();
                zzhtVarZzc.zzp(6);
                zzia zziaVarZzc = zzie.zzc();
                zziaVarZzc.zzn(zzic.SERVICE_DISCONNECTED);
                zzhtVarZzc.zzl(zziaVarZzc);
                zzjr zzjrVarZzc = zzjt.zzc();
                int i = this.zze;
                zzjrVarZzc.zza(i > 0);
                zzjrVarZzc.zzl(i);
                zzhtVarZzc.zzo(zzjrVarZzc);
                zzcnVar.zza((zzhv) zzhtVarZzc.zze());
            } else {
                billingClientImpl.zzh.zzk(zzjq.zzd());
            }
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzbgVar = this.zzd;
        zzbgVar.zzd();
        zzbgVar.zze();
        BillingClientImpl billingClientImpl2 = this.zza;
        synchronized (billingClientImpl2.zza) {
            try {
                if (billingClientImpl2.zzb == 3) {
                    return;
                }
                billingClientImpl2.zzaS(0);
                try {
                    this.zzb.onBillingServiceDisconnected();
                } catch (Throwable th2) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception while calling onBillingServiceDisconnected.", th2);
                }
            } finally {
            }
        }
    }

    public final void zzc() {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzbgVar = this.zzc;
        zzbgVar.zzd();
        zzbgVar.zze();
    }

    public final boolean zzd() {
        return this.zze > 0;
    }

    @Nullable
    public final Long zze(boolean z) {
        if (z) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzbgVar = this.zzc;
            if (!zzbgVar.zzb) {
                return null;
            }
            zzbgVar.zzf();
            return Long.valueOf(zzbgVar.zza(TimeUnit.MILLISECONDS));
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzbgVar2 = this.zzd;
        if (!zzbgVar2.zzb) {
            return null;
        }
        zzbgVar2.zzf();
        return Long.valueOf(zzbgVar2.zza(TimeUnit.MILLISECONDS));
    }

    public final void zzf(BillingResult billingResult, zzic zzicVar, @Nullable String str, boolean z) {
        try {
            zzia zziaVarZzc = zzie.zzc();
            zziaVarZzc.zzo(billingResult.zza);
            zziaVarZzc.zzl(billingResult.zzc);
            zziaVarZzc.zzn(zzicVar);
            if (str != null) {
                zziaVarZzc.zza(str);
            }
            Long lZze = zze(z);
            if (!z) {
                zzjk zzjkVarZzc = zzjm.zzc();
                zzjkVarZzc.zza(zziaVarZzc);
                if (lZze != null) {
                    zzjkVarZzc.zzl(lZze.longValue());
                }
                this.zza.zzh.zzj((zzjm) zzjkVarZzc.zze());
                return;
            }
            zzjr zzjrVarZzc = zzjt.zzc();
            int i = this.zze;
            zzjrVarZzc.zza(i > 0);
            zzjrVarZzc.zzl(i);
            if (lZze != null) {
                zzjrVarZzc.zzm(lZze.longValue());
            }
            BillingClientImpl billingClientImpl = this.zza;
            zzht zzhtVarZzc = zzhv.zzc();
            zzhtVarZzc.zzl(zziaVarZzc);
            zzhtVarZzc.zzp(6);
            zzhtVarZzc.zzo(zzjrVarZzc);
            billingClientImpl.zzaO((zzhv) zzhtVarZzc.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzg(BillingResult billingResult) {
        BillingClientImpl billingClientImpl = this.zza;
        synchronized (billingClientImpl.zza) {
            try {
                if (billingClientImpl.zzb == 3) {
                    return;
                }
                try {
                    this.zzb.onBillingSetupFinished(billingResult);
                } catch (Throwable th) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception while calling onBillingSetupFinished.", th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
