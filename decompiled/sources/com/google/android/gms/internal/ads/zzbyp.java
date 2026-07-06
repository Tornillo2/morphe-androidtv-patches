package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbyp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        zzq zzqVar = null;
        zzl zzlVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c = (char) i;
            if (c == 1) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c == 2) {
                strCreateString2 = SafeParcelReader.createString(parcel, i);
            } else if (c == 3) {
                zzqVar = (zzq) SafeParcelReader.createParcelable(parcel, i, zzq.CREATOR);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                zzlVar = (zzl) SafeParcelReader.createParcelable(parcel, i, zzl.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbyo(strCreateString, strCreateString2, zzqVar, zzlVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbyo[i];
    }
}
