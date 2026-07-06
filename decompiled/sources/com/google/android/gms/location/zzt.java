package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzt implements Parcelable.Creator<zzs> {
    @Override // android.os.Parcelable.Creator
    public final zzs createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 50;
        long j2 = Long.MAX_VALUE;
        boolean z = true;
        float f = 0.0f;
        int i = Integer.MAX_VALUE;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c = (char) i2;
            if (c == 1) {
                z = SafeParcelReader.readBoolean(parcel, i2);
            } else if (c == 2) {
                j = SafeParcelReader.readLong(parcel, i2);
            } else if (c == 3) {
                f = SafeParcelReader.readFloat(parcel, i2);
            } else if (c == 4) {
                j2 = SafeParcelReader.readLong(parcel, i2);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                i = SafeParcelReader.readInt(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzs(z, j, f, j2, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzs[] newArray(int i) {
        return new zzs[i];
    }
}
