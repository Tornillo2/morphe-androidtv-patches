package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzbfk extends IInterface {
    zzdq zze() throws RemoteException;

    zzben zzf() throws RemoteException;

    zzbeq zzg(String str) throws RemoteException;

    IObjectWrapper zzh() throws RemoteException;

    String zzi() throws RemoteException;

    String zzj(String str) throws RemoteException;

    List zzk() throws RemoteException;

    void zzl() throws RemoteException;

    void zzm() throws RemoteException;

    void zzn(String str) throws RemoteException;

    void zzo() throws RemoteException;

    void zzp(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzq() throws RemoteException;

    boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzs(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzt() throws RemoteException;
}
