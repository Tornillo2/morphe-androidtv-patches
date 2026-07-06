package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbf implements Parcelable.Creator<LocationRequest> {
    @Override // android.os.Parcelable.Creator
    public final LocationRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 3600000;
        long j2 = 600000;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        int i = 102;
        boolean z = false;
        int i2 = Integer.MAX_VALUE;
        float f = 0.0f;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            switch ((char) i3) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i3);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, i3);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, i3);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, i3);
                    break;
                case 5:
                    j3 = SafeParcelReader.readLong(parcel, i3);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, i3);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel, i3);
                    break;
                case '\b':
                    j4 = SafeParcelReader.readLong(parcel, i3);
                    break;
                case '\t':
                    z2 = SafeParcelReader.readBoolean(parcel, i3);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new LocationRequest(i, j, j2, z, j3, i2, f, j4, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
