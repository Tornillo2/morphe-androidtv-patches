package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.client.zzfl;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbef implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzfl zzflVar = null;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        int i4 = 0;
        int i5 = 0;
        boolean z4 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i6 = parcel.readInt();
            switch ((char) i6) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 4:
                    z2 = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case 5:
                    i3 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 6:
                    zzflVar = (zzfl) SafeParcelReader.createParcelable(parcel, i6, zzfl.CREATOR);
                    break;
                case 7:
                    z3 = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case '\b':
                    i4 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case '\t':
                    i5 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case '\n':
                    z4 = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i6);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbee(i, z, i2, z2, i3, zzflVar, z3, i4, i5, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbee[i];
    }
}
