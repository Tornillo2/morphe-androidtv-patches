package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zad extends com.google.android.gms.internal.base.zab implements zae {
    public zad() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    public final boolean zaa(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 3:
                com.google.android.gms.internal.base.zac.zab(parcel);
                break;
            case 4:
                com.google.android.gms.internal.base.zac.zab(parcel);
                break;
            case 5:
            default:
                return false;
            case 6:
                com.google.android.gms.internal.base.zac.zab(parcel);
                break;
            case 7:
                com.google.android.gms.internal.base.zac.zab(parcel);
                break;
            case 8:
                zak zakVar = (zak) com.google.android.gms.internal.base.zac.zaa(parcel, zak.CREATOR);
                com.google.android.gms.internal.base.zac.zab(parcel);
                zab(zakVar);
                break;
            case 9:
                com.google.android.gms.internal.base.zac.zab(parcel);
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
