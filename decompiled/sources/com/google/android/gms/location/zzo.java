package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzo implements Parcelable.Creator<ActivityTransitionRequest> {
    @Override // android.os.Parcelable.Creator
    public final ActivityTransitionRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        String strCreateString = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c = (char) i;
            if (c == 1) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i, ActivityTransition.CREATOR);
            } else if (c == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c == 3) {
                arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, i, ClientIdentity.CREATOR);
            } else if (c != 4) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                strCreateString2 = SafeParcelReader.createString(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ActivityTransitionRequest(arrayListCreateTypedList, strCreateString, arrayListCreateTypedList2, strCreateString2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ ActivityTransitionRequest[] newArray(int i) {
        return new ActivityTransitionRequest[i];
    }
}
