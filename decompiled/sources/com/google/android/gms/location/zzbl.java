package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbl implements Parcelable.Creator<LocationSettingsRequest> {
    @Override // android.os.Parcelable.Creator
    public final LocationSettingsRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        zzbj zzbjVar = null;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c = (char) i;
            if (c == 1) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i, LocationRequest.CREATOR);
            } else if (c == 2) {
                z = SafeParcelReader.readBoolean(parcel, i);
            } else if (c == 3) {
                z2 = SafeParcelReader.readBoolean(parcel, i);
            } else if (c != 5) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                zzbjVar = (zzbj) SafeParcelReader.createParcelable(parcel, i, zzbj.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new LocationSettingsRequest(arrayListCreateTypedList, z, z2, zzbjVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationSettingsRequest[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
