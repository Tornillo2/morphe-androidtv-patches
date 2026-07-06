package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        zze zzeVar = null;
        Bundle bundleCreateBundle = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        long j = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            switch ((char) i) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, i);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, i);
                    break;
                case 3:
                    zzeVar = (zze) SafeParcelReader.createParcelable(parcel, i, zze.CREATOR);
                    break;
                case 4:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, i);
                    break;
                case 5:
                    strCreateString2 = SafeParcelReader.createString(parcel, i);
                    break;
                case 6:
                    strCreateString3 = SafeParcelReader.createString(parcel, i);
                    break;
                case 7:
                    strCreateString4 = SafeParcelReader.createString(parcel, i);
                    break;
                case '\b':
                    strCreateString5 = SafeParcelReader.createString(parcel, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzu(strCreateString, j, zzeVar, bundleCreateBundle, strCreateString2, strCreateString3, strCreateString4, strCreateString5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
