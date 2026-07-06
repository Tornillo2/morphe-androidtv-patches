package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbd implements Parcelable.Creator<zzbc> {
    @Override // android.os.Parcelable.Creator
    public final zzbc createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzba zzbaVar = null;
        IBinder iBinder = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        int i = 1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    zzbaVar = (zzba) SafeParcelReader.createParcelable(parcel, i2, zzba.CREATOR);
                    break;
                case 3:
                    iBinder = SafeParcelReader.readIBinder(parcel, i2);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, i2, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = SafeParcelReader.readIBinder(parcel, i2);
                    break;
                case 6:
                    iBinder3 = SafeParcelReader.readIBinder(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbc(i, zzbaVar, iBinder, pendingIntent, iBinder2, iBinder3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzbc[] newArray(int i) {
        return new zzbc[i];
    }
}
