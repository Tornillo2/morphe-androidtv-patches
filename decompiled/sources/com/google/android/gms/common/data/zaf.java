package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String[] strArrCreateStringArray = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundleCreateBundle = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c = (char) i3;
            if (c == 1) {
                strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, i3);
            } else if (c == 2) {
                cursorWindowArr = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel, i3, CursorWindow.CREATOR);
            } else if (c == 3) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c == 4) {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, i3);
            } else if (c != 1000) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                i = SafeParcelReader.readInt(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        DataHolder dataHolder = new DataHolder(i, strArrCreateStringArray, cursorWindowArr, i2, bundleCreateBundle);
        dataHolder.zad();
        return dataHolder;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataHolder[i];
    }
}
