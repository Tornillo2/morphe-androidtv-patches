package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzby implements Parcelable.Creator<zzbx> {
    @Override // android.os.Parcelable.Creator
    public final zzbx createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            char c = (char) i5;
            if (c == 1) {
                i = SafeParcelReader.readInt(parcel, i5);
            } else if (c == 2) {
                i2 = SafeParcelReader.readInt(parcel, i5);
            } else if (c == 3) {
                i3 = SafeParcelReader.readInt(parcel, i5);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i5);
            } else {
                i4 = SafeParcelReader.readInt(parcel, i5);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbx(i, i2, i3, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzbx[] newArray(int i) {
        return new zzbx[i];
    }
}
