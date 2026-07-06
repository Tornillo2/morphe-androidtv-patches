package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzbvm;
import com.google.android.gms.internal.ads.zzbvo;
import com.google.android.gms.internal.ads.zzbvs;
import com.google.android.gms.internal.ads.zzbvw;
import com.google.android.gms.internal.ads.zzbvx;
import com.google.android.gms.internal.ads.zzbwd;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfc extends zzbvo {
    public static void zzr(final zzbvw zzbvwVar) {
        zzbzt.zzg("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbzm.zza.post(new Runnable() { // from class: com.google.android.gms.ads.internal.client.zzfb
            @Override // java.lang.Runnable
            public final void run() {
                zzbvw zzbvwVar2 = zzbvwVar;
                if (zzbvwVar2 != null) {
                    try {
                        zzbvwVar2.zze(1);
                    } catch (RemoteException e) {
                        zzbzt.zzl("#007 Could not call remote method.", e);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final Bundle zzb() throws RemoteException {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final zzdn zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    @Nullable
    public final zzbvm zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final String zze() throws RemoteException {
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzf(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException {
        zzr(zzbvwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzg(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException {
        zzr(zzbvwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final boolean zzo() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzh(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzi(zzdd zzddVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzj(zzdg zzdgVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzk(zzbvs zzbvsVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzl(zzbwd zzbwdVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzp(zzbvx zzbvxVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzn(IObjectWrapper iObjectWrapper, boolean z) {
    }
}
