package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcaa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c = (char) i3;
            if (c == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i3);
            } else if (c == 3) {
                i = SafeParcelReader.readInt(parcel, i3);
            } else if (c == 4) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c == 5) {
                z = SafeParcelReader.readBoolean(parcel, i3);
            } else if (c != 6) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbzz(strCreateString, i, i2, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbzz[i];
    }
}
