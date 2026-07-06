package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            switch ((char) i) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, i);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 3:
                    z2 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 4:
                    iBinder = SafeParcelReader.readIBinder(parcel, i);
                    break;
                case 5:
                    z3 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 6:
                    z4 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzo(strCreateString, z, z2, iBinder, z3, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzo[i];
    }
}
