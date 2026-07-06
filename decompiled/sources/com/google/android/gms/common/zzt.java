package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzt implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        String strCreateString = null;
        IBinder iBinder = null;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c = (char) i;
            if (c == 1) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, i);
            } else if (c == 3) {
                z = SafeParcelReader.readBoolean(parcel, i);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzs(strCreateString, iBinder, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzs[i];
    }
}
