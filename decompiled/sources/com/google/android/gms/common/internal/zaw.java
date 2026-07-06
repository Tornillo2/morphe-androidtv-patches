package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaw implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c = (char) i2;
            if (c == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, i2);
            } else if (c == 3) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, i2, ConnectionResult.CREATOR);
            } else if (c == 4) {
                z = SafeParcelReader.readBoolean(parcel, i2);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zav(i, iBinder, connectionResult, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zav[i];
    }
}
