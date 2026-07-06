package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaf extends com.google.android.gms.internal.base.zaa implements IInterface {
    public zaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zae(int i) throws RemoteException {
        Parcel parcelZaa = zaa();
        parcelZaa.writeInt(i);
        zac(7, parcelZaa);
    }

    public final void zaf(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zad(parcelZaa, iAccountAccessor);
        parcelZaa.writeInt(i);
        parcelZaa.writeInt(z ? 1 : 0);
        zac(9, parcelZaa);
    }

    public final void zag(zai zaiVar, zae zaeVar) throws RemoteException {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zac(parcelZaa, zaiVar);
        com.google.android.gms.internal.base.zac.zad(parcelZaa, zaeVar);
        zac(12, parcelZaa);
    }
}
