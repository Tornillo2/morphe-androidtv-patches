package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbu implements Parcelable.Creator<SleepClassifyEvent> {
    @Override // android.os.Parcelable.Creator
    public final SleepClassifyEvent createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        int i8 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i9 = parcel.readInt();
            switch ((char) i9) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 4:
                    i4 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 5:
                    i5 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 6:
                    i6 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case 7:
                    i7 = SafeParcelReader.readInt(parcel, i9);
                    break;
                case '\b':
                    z = SafeParcelReader.readBoolean(parcel, i9);
                    break;
                case '\t':
                    i8 = SafeParcelReader.readInt(parcel, i9);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i9);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SleepClassifyEvent(i, i2, i3, i4, i5, i6, i7, z, i8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ SleepClassifyEvent[] newArray(int i) {
        return new SleepClassifyEvent[i];
    }
}
