package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zan implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i6 = parcel.readInt();
            switch ((char) i6) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, i6);
                    break;
                case 5:
                    j2 = SafeParcelReader.readLong(parcel, i6);
                    break;
                case 6:
                    strCreateString = SafeParcelReader.createString(parcel, i6);
                    break;
                case 7:
                    strCreateString2 = SafeParcelReader.createString(parcel, i6);
                    break;
                case '\b':
                    i4 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case '\t':
                    i5 = SafeParcelReader.readInt(parcel, i6);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i6);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new MethodInvocation(i, i2, i3, j, j2, strCreateString, strCreateString2, i4, i5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MethodInvocation[i];
    }
}
