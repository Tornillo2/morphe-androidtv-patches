package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzbpv extends IInterface {
    zzdq zze() throws RemoteException;

    zzbqj zzf() throws RemoteException;

    zzbqj zzg() throws RemoteException;

    void zzh(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzq zzqVar, zzbpy zzbpyVar) throws RemoteException;

    void zzi(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpg zzbpgVar, zzboe zzboeVar) throws RemoteException;

    void zzj(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException;

    void zzk(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException;

    void zzl(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpm zzbpmVar, zzboe zzboeVar) throws RemoteException;

    void zzm(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar) throws RemoteException;

    void zzn(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar, zzbee zzbeeVar) throws RemoteException;

    void zzo(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException;

    void zzp(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException;

    void zzq(String str) throws RemoteException;

    boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzs(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzt(IObjectWrapper iObjectWrapper) throws RemoteException;
}
