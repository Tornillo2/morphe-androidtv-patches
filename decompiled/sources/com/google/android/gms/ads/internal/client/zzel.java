package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzel extends zzcv {
    public final String zza;
    public final String zzb;

    public zzel(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcw
    public final String zze() throws RemoteException {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcw
    public final String zzf() throws RemoteException {
        return this.zzb;
    }
}
