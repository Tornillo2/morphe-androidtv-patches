package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbno extends zzcgr {
    public final AppMeasurementSdk zza;

    public zzbno(AppMeasurementSdk appMeasurementSdk) {
        this.zza = appMeasurementSdk;
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final int zzb(String str) throws RemoteException {
        return this.zza.getMaxUserProperties(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final long zzc() throws RemoteException {
        return this.zza.generateEventId();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final Bundle zzd(Bundle bundle) throws RemoteException {
        return this.zza.performActionWithResponse(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final String zze() throws RemoteException {
        return this.zza.getAppIdOrigin();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final String zzf() throws RemoteException {
        return this.zza.getAppInstanceId();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final String zzg() throws RemoteException {
        return this.zza.getCurrentScreenClass();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final String zzh() throws RemoteException {
        return this.zza.getCurrentScreenName();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final String zzi() throws RemoteException {
        return this.zza.getGmpAppId();
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final List zzj(String str, String str2) throws RemoteException {
        return this.zza.getConditionalUserProperties(str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final Map zzk(String str, String str2, boolean z) throws RemoteException {
        return this.zza.getUserProperties(str, str2, z);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzl(String str) throws RemoteException {
        this.zza.beginAdUnitExposure(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzm(String str, String str2, Bundle bundle) throws RemoteException {
        this.zza.clearConditionalUserProperty(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzn(String str) throws RemoteException {
        this.zza.endAdUnitExposure(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzo(String str, String str2, Bundle bundle) throws RemoteException {
        this.zza.logEvent(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzp(Bundle bundle) throws RemoteException {
        this.zza.performAction(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzq(Bundle bundle) throws RemoteException {
        this.zza.setConditionalUserProperty(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzr(Bundle bundle) throws RemoteException {
        this.zza.setConsent(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzs(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        this.zza.setCurrentScreen(iObjectWrapper != null ? (Activity) ObjectWrapper.unwrap(iObjectWrapper) : null, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzcgs
    public final void zzt(String str, String str2, IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zza.setUserProperty(str, str2, iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null);
    }
}
