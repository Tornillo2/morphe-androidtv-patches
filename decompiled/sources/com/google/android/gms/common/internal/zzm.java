package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        RootTelemetryConfiguration rootTelemetryConfiguration = null;
        int[] iArrCreateIntArray = null;
        int[] iArrCreateIntArray2 = null;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    rootTelemetryConfiguration = (RootTelemetryConfiguration) SafeParcelReader.createParcelable(parcel, i2, RootTelemetryConfiguration.CREATOR);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 3:
                    z2 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 4:
                    iArrCreateIntArray = SafeParcelReader.createIntArray(parcel, i2);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 6:
                    iArrCreateIntArray2 = SafeParcelReader.createIntArray(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ConnectionTelemetryConfiguration(rootTelemetryConfiguration, z, z2, iArrCreateIntArray, i, iArrCreateIntArray2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ConnectionTelemetryConfiguration[i];
    }
}
