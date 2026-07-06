package com.google.android.gms.internal.consent_sdk;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import androidx.annotation.UiThread;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.UserMessagingPlatform;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@UiThread
public final class zzay implements ConsentForm {
    public final Application zza;
    public final zzac zzb;
    public final zzbi zzc;
    public final zzam zzd;
    public final zzbc zze;
    public final zzcl<zzbg> zzf;
    public Dialog zzg;
    public zzbg zzh;
    public final AtomicBoolean zzi = new AtomicBoolean();
    public final AtomicReference<zzax> zzj = new AtomicReference<>();
    public final AtomicReference<ConsentForm.OnConsentFormDismissedListener> zzk = new AtomicReference<>();
    public final AtomicReference<zzaw> zzl = new AtomicReference<>();

    public zzay(Application application, zzac zzacVar, zzbi zzbiVar, zzam zzamVar, zzbc zzbcVar, zzcl<zzbg> zzclVar) {
        this.zza = application;
        this.zzb = zzacVar;
        this.zzc = zzbiVar;
        this.zzd = zzamVar;
        this.zze = zzbcVar;
        this.zzf = zzclVar;
    }

    @Override // com.google.android.ump.ConsentForm
    public final void show(Activity activity, ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        zzcd.zza();
        if (!this.zzi.compareAndSet(false, true)) {
            onConsentFormDismissedListener.onConsentFormDismissed(new zzj(3, "ConsentForm#show can only be invoked once.").zza());
            return;
        }
        zzaw zzawVar = new zzaw(this, activity);
        this.zza.registerActivityLifecycleCallbacks(zzawVar);
        this.zzl.set(zzawVar);
        this.zzc.zza = activity;
        Dialog dialog = new Dialog(activity, R.style.Theme.Translucent.NoTitleBar);
        dialog.setContentView(this.zzh);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        if (window == null) {
            onConsentFormDismissedListener.onConsentFormDismissed(new zzj(3, "Activity with null windows is passed in.").zza());
            return;
        }
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        this.zzk.set(onConsentFormDismissedListener);
        dialog.show();
        this.zzg = dialog;
        this.zzh.zzb("UMP_messagePresented", "");
    }

    public final zzbg zza() {
        return this.zzh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzb(UserMessagingPlatform.OnConsentFormLoadSuccessListener onConsentFormLoadSuccessListener, UserMessagingPlatform.OnConsentFormLoadFailureListener onConsentFormLoadFailureListener) {
        zzbg zzbgVarZza = ((zzbh) this.zzf).zzb();
        this.zzh = zzbgVarZza;
        zzbgVarZza.setBackgroundColor(0);
        zzbgVarZza.getSettings().setJavaScriptEnabled(true);
        zzbgVarZza.setWebViewClient(new zzbf(zzbgVarZza, null));
        this.zzj.set(new zzax(onConsentFormLoadSuccessListener, onConsentFormLoadFailureListener, 0 == true ? 1 : 0));
        zzbg zzbgVar = this.zzh;
        zzbc zzbcVar = this.zze;
        zzbgVar.loadDataWithBaseURL(zzbcVar.zza, zzbcVar.zzb, "text/html", "UTF-8", null);
        zzcd.zza.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzau
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzf(new zzj(4, "Web view timed out."));
            }
        }, 10000L);
    }

    public final void zzc(int i) {
        zzg();
        ConsentForm.OnConsentFormDismissedListener andSet = this.zzk.getAndSet(null);
        if (andSet == null) {
            return;
        }
        this.zzd.zzf(3);
        andSet.onConsentFormDismissed(null);
    }

    public final void zzd(zzj zzjVar) {
        zzg();
        ConsentForm.OnConsentFormDismissedListener andSet = this.zzk.getAndSet(null);
        if (andSet == null) {
            return;
        }
        andSet.onConsentFormDismissed(zzjVar.zza());
    }

    public final void zze() {
        zzax andSet = this.zzj.getAndSet(null);
        if (andSet == null) {
            return;
        }
        andSet.onConsentFormLoadSuccess(this);
    }

    public final void zzf(zzj zzjVar) {
        zzax andSet = this.zzj.getAndSet(null);
        if (andSet == null) {
            return;
        }
        andSet.onConsentFormLoadFailure(zzjVar.zza());
    }

    public final void zzg() {
        Dialog dialog = this.zzg;
        if (dialog != null) {
            dialog.dismiss();
            this.zzg = null;
        }
        this.zzc.zza = null;
        zzaw andSet = this.zzl.getAndSet(null);
        if (andSet != null) {
            andSet.zza.zza.unregisterActivityLifecycleCallbacks(andSet);
        }
    }
}
