package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdd;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzbvp extends IInterface {
    Bundle zzb() throws RemoteException;

    zzdn zzc() throws RemoteException;

    zzbvm zzd() throws RemoteException;

    String zze() throws RemoteException;

    void zzf(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException;

    void zzg(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException;

    void zzh(boolean z) throws RemoteException;

    void zzi(zzdd zzddVar) throws RemoteException;

    void zzj(zzdg zzdgVar) throws RemoteException;

    void zzk(zzbvs zzbvsVar) throws RemoteException;

    void zzl(zzbwd zzbwdVar) throws RemoteException;

    void zzm(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzn(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException;

    boolean zzo() throws RemoteException;

    void zzp(zzbvx zzbvxVar) throws RemoteException;
}
