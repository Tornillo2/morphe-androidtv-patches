package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbb implements Parcelable.Creator<zzba> {
    @Override // android.os.Parcelable.Creator
    public final zzba createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        List<ClientIdentity> listCreateTypedList = zzba.zza;
        LocationRequest locationRequest = null;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        long j = Long.MAX_VALUE;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c = (char) i;
            if (c != 1) {
                switch (c) {
                    case 5:
                        listCreateTypedList = SafeParcelReader.createTypedList(parcel, i, ClientIdentity.CREATOR);
                        break;
                    case 6:
                        strCreateString = SafeParcelReader.createString(parcel, i);
                        break;
                    case 7:
                        z = SafeParcelReader.readBoolean(parcel, i);
                        break;
                    case '\b':
                        z2 = SafeParcelReader.readBoolean(parcel, i);
                        break;
                    case '\t':
                        z3 = SafeParcelReader.readBoolean(parcel, i);
                        break;
                    case '\n':
                        strCreateString2 = SafeParcelReader.createString(parcel, i);
                        break;
                    case 11:
                        z4 = SafeParcelReader.readBoolean(parcel, i);
                        break;
                    case '\f':
                        z5 = SafeParcelReader.readBoolean(parcel, i);
                        break;
                    case '\r':
                        strCreateString3 = SafeParcelReader.createString(parcel, i);
                        break;
                    case 14:
                        j = SafeParcelReader.readLong(parcel, i);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, i);
                        break;
                }
            } else {
                locationRequest = (LocationRequest) SafeParcelReader.createParcelable(parcel, i, LocationRequest.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzba(locationRequest, listCreateTypedList, strCreateString, z, z2, z3, strCreateString2, z4, z5, strCreateString3, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzba[] newArray(int i) {
        return new zzba[i];
    }
}
