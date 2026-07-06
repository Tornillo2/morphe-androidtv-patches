package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes3.dex */
public final class zza implements Parcelable.Creator<PlaceReport> {
    @Override // android.os.Parcelable.Creator
    public final PlaceReport createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c = (char) i2;
            if (c == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            } else if (c == 3) {
                strCreateString2 = SafeParcelReader.createString(parcel, i2);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                strCreateString3 = SafeParcelReader.createString(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PlaceReport(i, strCreateString, strCreateString2, strCreateString3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlaceReport[] newArray(int i) {
        return new PlaceReport[i];
    }
}
