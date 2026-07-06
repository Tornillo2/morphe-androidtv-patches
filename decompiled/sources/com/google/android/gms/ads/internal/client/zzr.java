package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzr implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        zzq[] zzqVarArr = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            switch ((char) i5) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, i5);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 6:
                    i3 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case '\b':
                    zzqVarArr = (zzq[]) SafeParcelReader.createTypedArray(parcel, i5, zzq.CREATOR);
                    break;
                case '\t':
                    z2 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case '\n':
                    z3 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 11:
                    z4 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case '\f':
                    z5 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case '\r':
                    z6 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 14:
                    z7 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 15:
                    z8 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 16:
                    z9 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzq(strCreateString, i, i2, z, i3, i4, zzqVarArr, z2, z3, z4, z5, z6, z7, z8, z9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzq[i];
    }
}
