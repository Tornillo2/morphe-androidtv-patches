package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbv implements Parcelable.Creator<SleepSegmentEvent> {
    @Override // android.os.Parcelable.Creator
    public final SleepSegmentEvent createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c = (char) i4;
            if (c == 1) {
                j = SafeParcelReader.readLong(parcel, i4);
            } else if (c == 2) {
                j2 = SafeParcelReader.readLong(parcel, i4);
            } else if (c == 3) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c == 4) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                i3 = SafeParcelReader.readInt(parcel, i4);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SleepSegmentEvent(j, j2, i, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ SleepSegmentEvent[] newArray(int i) {
        return new SleepSegmentEvent[i];
    }
}
