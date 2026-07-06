package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        com.google.android.gms.common.server.converter.zaa zaaVar = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        int i4 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            switch ((char) i5) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 4:
                    i3 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 6:
                    strCreateString = SafeParcelReader.createString(parcel, i5);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case '\b':
                    strCreateString2 = SafeParcelReader.createString(parcel, i5);
                    break;
                case '\t':
                    zaaVar = (com.google.android.gms.common.server.converter.zaa) SafeParcelReader.createParcelable(parcel, i5, com.google.android.gms.common.server.converter.zaa.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new FastJsonResponse.Field(i, i2, z, i3, z2, strCreateString, i4, strCreateString2, zaaVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new FastJsonResponse.Field[i];
    }
}
