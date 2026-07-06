package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbe implements Parcelable.Creator<LocationAvailability> {
    @Override // android.os.Parcelable.Creator
    public final LocationAvailability createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        zzbo[] zzboVarArr = null;
        int i = 1000;
        int i2 = 1;
        int i3 = 1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c = (char) i4;
            if (c == 1) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 2) {
                i3 = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 3) {
                j = SafeParcelReader.readLong(parcel, i4);
            } else if (c == 4) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                zzboVarArr = (zzbo[]) SafeParcelReader.createTypedArray(parcel, i4, zzbo.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new LocationAvailability(i, i2, i3, j, zzboVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationAvailability[] newArray(int i) {
        return new LocationAvailability[i];
    }
}
