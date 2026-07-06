package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import androidx.annotation.Nullable;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzk implements ConsentInformation {
    public final zzam zza;
    public final zzv zzb;
    public final zzba zzc;

    public zzk(zzam zzamVar, zzv zzvVar, zzba zzbaVar) {
        this.zza = zzamVar;
        this.zzb = zzvVar;
        this.zzc = zzbaVar;
    }

    @Override // com.google.android.ump.ConsentInformation
    public final int getConsentStatus() {
        return this.zza.zza();
    }

    @Override // com.google.android.ump.ConsentInformation
    public final boolean isConsentFormAvailable() {
        return this.zzc.zzc();
    }

    @Override // com.google.android.ump.ConsentInformation
    public final void requestConsentInfoUpdate(@Nullable Activity activity, ConsentRequestParameters consentRequestParameters, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        this.zzb.zzc(activity, consentRequestParameters, onConsentInfoUpdateSuccessListener, onConsentInfoUpdateFailureListener);
    }

    @Override // com.google.android.ump.ConsentInformation
    public final void reset() {
        this.zzc.zzb(null);
        this.zza.zzd();
    }
}
