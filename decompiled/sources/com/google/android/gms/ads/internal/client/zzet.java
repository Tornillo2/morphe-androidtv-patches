package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzet extends zzbm {
    public final /* synthetic */ zzeu zza;

    public /* synthetic */ zzet(zzeu zzeuVar, zzes zzesVar) {
        this.zza = zzeuVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    @Nullable
    public final String zze() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    @Nullable
    public final String zzf() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final void zzg(zzl zzlVar) throws RemoteException {
        zzh(zzlVar, 1);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final void zzh(zzl zzlVar, int i) throws RemoteException {
        zzbzt.zzg("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbzm.zza.post(new zzer(this));
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final boolean zzi() throws RemoteException {
        return false;
    }
}
