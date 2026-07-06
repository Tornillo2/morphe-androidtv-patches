package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zab implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        Uri uri = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        ArrayList arrayListCreateTypedList = null;
        String strCreateString7 = null;
        String strCreateString8 = null;
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, i2);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, i2, Uri.CREATOR);
                    break;
                case 7:
                    strCreateString5 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\b':
                    j = SafeParcelReader.readLong(parcel, i2);
                    break;
                case '\t':
                    strCreateString6 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\n':
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, Scope.CREATOR);
                    break;
                case 11:
                    strCreateString7 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\f':
                    strCreateString8 = SafeParcelReader.createString(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GoogleSignInAccount(i, strCreateString, strCreateString2, strCreateString3, strCreateString4, uri, strCreateString5, j, strCreateString6, arrayListCreateTypedList, strCreateString7, strCreateString8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
