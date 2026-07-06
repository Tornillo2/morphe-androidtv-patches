package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzak extends IInterface {
    void zzb(int i, String[] strArr) throws RemoteException;

    void zzc(int i, String[] strArr) throws RemoteException;

    void zzd(int i, PendingIntent pendingIntent) throws RemoteException;
}
