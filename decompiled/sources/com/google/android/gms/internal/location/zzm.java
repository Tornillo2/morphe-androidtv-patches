package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzm implements Parcelable.Creator<zzl> {
    @Override // android.os.Parcelable.Creator
    public final zzl createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzj zzjVar = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        int i = 1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c = (char) i2;
            if (c == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c == 2) {
                zzjVar = (zzj) SafeParcelReader.createParcelable(parcel, i2, zzj.CREATOR);
            } else if (c == 3) {
                iBinder = SafeParcelReader.readIBinder(parcel, i2);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                iBinder2 = SafeParcelReader.readIBinder(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzl(i, zzjVar, iBinder, iBinder2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzl[] newArray(int i) {
        return new zzl[i];
    }
}
