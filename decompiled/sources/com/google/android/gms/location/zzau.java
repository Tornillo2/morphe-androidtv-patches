package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzau implements Parcelable.Creator<GeofencingRequest> {
    @Override // android.os.Parcelable.Creator
    public final GeofencingRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = "";
        ArrayList arrayListCreateTypedList = null;
        String strCreateString2 = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c = (char) i2;
            if (c == 1) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, com.google.android.gms.internal.location.zzbe.CREATOR);
            } else if (c == 2) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c == 3) {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                strCreateString2 = SafeParcelReader.createString(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GeofencingRequest(arrayListCreateTypedList, i, strCreateString, strCreateString2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ GeofencingRequest[] newArray(int i) {
        return new GeofencingRequest[i];
    }
}
