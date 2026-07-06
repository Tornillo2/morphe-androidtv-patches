package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbu;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzavr extends IInterface {
    zzbu zze() throws RemoteException;

    zzdn zzf() throws RemoteException;

    void zzg(boolean z) throws RemoteException;

    void zzh(zzdg zzdgVar) throws RemoteException;

    void zzi(IObjectWrapper iObjectWrapper, zzavy zzavyVar) throws RemoteException;
}
