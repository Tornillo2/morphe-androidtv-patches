package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "FusedLocationProviderResultCreator")
@SafeParcelable.Reserved({1000})
public final class zzaa extends AbstractSafeParcelable implements Result {

    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status zzb;
    public static final zzaa zza = new zzaa(Status.RESULT_SUCCESS);
    public static final Parcelable.Creator<zzaa> CREATOR = new zzab();

    @SafeParcelable.Constructor
    public zzaa(@SafeParcelable.Param(id = 1) Status status) {
        this.zzb = status;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzb, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
