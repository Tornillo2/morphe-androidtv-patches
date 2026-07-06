package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbn implements Parcelable.Creator<LocationSettingsStates> {
    @Override // android.os.Parcelable.Creator
    public final LocationSettingsStates createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            switch ((char) i) {
                case 1:
                    z = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 2:
                    z2 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 3:
                    z3 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 4:
                    z4 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 5:
                    z5 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                case 6:
                    z6 = SafeParcelReader.readBoolean(parcel, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new LocationSettingsStates(z, z2, z3, z4, z5, z6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationSettingsStates[] newArray(int i) {
        return new LocationSettingsStates[i];
    }
}
