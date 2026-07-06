package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzw extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zzb() throws RemoteException {
        Parcel parcelZzB = zzB(2, zza());
        Account account = (Account) com.google.android.gms.internal.common.zzc.zza(parcelZzB, Account.CREATOR);
        parcelZzB.recycle();
        return account;
    }
}
