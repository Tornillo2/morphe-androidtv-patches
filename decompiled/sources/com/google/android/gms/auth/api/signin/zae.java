package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zae implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        Account account = null;
        String strCreateString = null;
        String strCreateString2 = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString3 = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.createParcelable(parcel, i2, Account.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 6:
                    z3 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 7:
                    strCreateString = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\b':
                    strCreateString2 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\t':
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, i2, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case '\n':
                    strCreateString3 = SafeParcelReader.createString(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GoogleSignInOptions(i, arrayListCreateTypedList, account, z, z2, z3, strCreateString, strCreateString2, arrayListCreateTypedList2, strCreateString3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
