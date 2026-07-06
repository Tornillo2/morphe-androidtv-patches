package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzn implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString = null;
        zzfh zzfhVar = null;
        Location location = null;
        String strCreateString2 = null;
        Bundle bundleCreateBundle2 = null;
        Bundle bundleCreateBundle3 = null;
        ArrayList<String> arrayListCreateStringList2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        zzc zzcVar = null;
        String strCreateString5 = null;
        ArrayList<String> arrayListCreateStringList3 = null;
        String strCreateString6 = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i6 = parcel.readInt();
            switch ((char) i6) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, i6);
                    break;
                case 3:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, i6);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 5:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, i6);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case 7:
                    i3 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case '\b':
                    z2 = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case '\t':
                    strCreateString = SafeParcelReader.createString(parcel, i6);
                    break;
                case '\n':
                    zzfhVar = (zzfh) SafeParcelReader.createParcelable(parcel, i6, zzfh.CREATOR);
                    break;
                case 11:
                    location = (Location) SafeParcelReader.createParcelable(parcel, i6, Location.CREATOR);
                    break;
                case '\f':
                    strCreateString2 = SafeParcelReader.createString(parcel, i6);
                    break;
                case '\r':
                    bundleCreateBundle2 = SafeParcelReader.createBundle(parcel, i6);
                    break;
                case 14:
                    bundleCreateBundle3 = SafeParcelReader.createBundle(parcel, i6);
                    break;
                case 15:
                    arrayListCreateStringList2 = SafeParcelReader.createStringList(parcel, i6);
                    break;
                case 16:
                    strCreateString3 = SafeParcelReader.createString(parcel, i6);
                    break;
                case 17:
                    strCreateString4 = SafeParcelReader.createString(parcel, i6);
                    break;
                case 18:
                    z3 = SafeParcelReader.readBoolean(parcel, i6);
                    break;
                case 19:
                    zzcVar = (zzc) SafeParcelReader.createParcelable(parcel, i6, zzc.CREATOR);
                    break;
                case 20:
                    i4 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 21:
                    strCreateString5 = SafeParcelReader.createString(parcel, i6);
                    break;
                case 22:
                    arrayListCreateStringList3 = SafeParcelReader.createStringList(parcel, i6);
                    break;
                case 23:
                    i5 = SafeParcelReader.readInt(parcel, i6);
                    break;
                case 24:
                    strCreateString6 = SafeParcelReader.createString(parcel, i6);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i6);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzl(i, j, bundleCreateBundle, i2, arrayListCreateStringList, z, i3, z2, strCreateString, zzfhVar, location, strCreateString2, bundleCreateBundle2, bundleCreateBundle3, arrayListCreateStringList2, strCreateString3, strCreateString4, z3, zzcVar, i4, strCreateString5, arrayListCreateStringList3, i5, strCreateString6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzl[i];
    }
}
