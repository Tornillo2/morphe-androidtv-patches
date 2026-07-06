package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zae implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Long longObject = null;
        Long longObject2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c = (char) i4;
            if (c == 1) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 2) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 3) {
                longObject = SafeParcelReader.readLongObject(parcel, i4);
            } else if (c == 4) {
                longObject2 = SafeParcelReader.readLongObject(parcel, i4);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                i3 = SafeParcelReader.readInt(parcel, i4);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ModuleInstallStatusUpdate(i, i2, longObject, longObject2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ModuleInstallStatusUpdate[i];
    }
}
