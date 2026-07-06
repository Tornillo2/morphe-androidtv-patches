package com.google.android.gms.ads.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzbee;
import com.google.android.gms.internal.ads.zzbfo;
import com.google.android.gms.internal.ads.zzbfr;
import com.google.android.gms.internal.ads.zzbfu;
import com.google.android.gms.internal.ads.zzbfx;
import com.google.android.gms.internal.ads.zzbgb;
import com.google.android.gms.internal.ads.zzbge;
import com.google.android.gms.internal.ads.zzbkq;
import com.google.android.gms.internal.ads.zzbkz;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzbq extends IInterface {
    zzbn zze() throws RemoteException;

    void zzf(zzbfo zzbfoVar) throws RemoteException;

    void zzg(zzbfr zzbfrVar) throws RemoteException;

    void zzh(String str, zzbfx zzbfxVar, @Nullable zzbfu zzbfuVar) throws RemoteException;

    void zzi(zzbkz zzbkzVar) throws RemoteException;

    void zzj(zzbgb zzbgbVar, zzq zzqVar) throws RemoteException;

    void zzk(zzbge zzbgeVar) throws RemoteException;

    void zzl(zzbh zzbhVar) throws RemoteException;

    void zzm(AdManagerAdViewOptions adManagerAdViewOptions) throws RemoteException;

    void zzn(zzbkq zzbkqVar) throws RemoteException;

    void zzo(zzbee zzbeeVar) throws RemoteException;

    void zzp(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException;

    void zzq(zzcf zzcfVar) throws RemoteException;
}
