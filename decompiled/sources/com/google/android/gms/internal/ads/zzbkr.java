package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbkr implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        String strCreateString = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c = (char) i4;
            if (c == 1) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i4);
            } else if (c == 3) {
                i3 = SafeParcelReader.readInt(parcel, i4);
            } else if (c != 1000) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                i = SafeParcelReader.readInt(parcel, i4);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbkq(i, i2, strCreateString, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbkq[i];
    }
}
