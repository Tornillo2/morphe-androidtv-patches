package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbf implements Parcelable.Creator<zzbe> {
    @Override // android.os.Parcelable.Creator
    public final zzbe createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        double d = 0.0d;
        double d2 = 0.0d;
        long j = 0;
        int i = 0;
        short s = 0;
        float f = 0.0f;
        int i2 = 0;
        int i3 = -1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            switch ((char) i4) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, i4);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, i4);
                    break;
                case 3:
                    s = SafeParcelReader.readShort(parcel, i4);
                    break;
                case 4:
                    d = SafeParcelReader.readDouble(parcel, i4);
                    break;
                case 5:
                    d2 = SafeParcelReader.readDouble(parcel, i4);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel, i4);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, i4);
                    break;
                case '\b':
                    i2 = SafeParcelReader.readInt(parcel, i4);
                    break;
                case '\t':
                    i3 = SafeParcelReader.readInt(parcel, i4);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i4);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbe(strCreateString, i, s, d, d2, f, j, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzbe[] newArray(int i) {
        return new zzbe[i];
    }
}
