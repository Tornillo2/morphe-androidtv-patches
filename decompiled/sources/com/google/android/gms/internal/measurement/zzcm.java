package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        Bundle bundleCreateBundle = null;
        String strCreateString4 = null;
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            switch ((char) i) {
                case 1:
                    j = SafeParcelReader.readLong(parcel, i);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel, i);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, i);
                    break;
                case 5:
                    strCreateString2 = SafeParcelReader.createString(parcel, i);
                    break;
                case 6:
                    strCreateString3 = SafeParcelReader.createString(parcel, i);
                    break;
                case 7:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, i);
                    break;
                case '\b':
                    strCreateString4 = SafeParcelReader.createString(parcel, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzcl(j, j2, z, strCreateString, strCreateString2, strCreateString3, bundleCreateBundle, strCreateString4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcl[i];
    }
}
