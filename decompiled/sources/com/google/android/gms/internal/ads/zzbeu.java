package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzbeu extends IInterface {
    IObjectWrapper zzb(String str) throws RemoteException;

    void zzbs(String str, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzbt(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzbu(@Nullable zzben zzbenVar) throws RemoteException;

    void zzbv(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzbw(@Nullable IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzc() throws RemoteException;

    void zzd(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException;
}
