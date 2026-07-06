package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "AuthAccountResultCreator")
public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zaa> CREATOR = new zab();

    @SafeParcelable.VersionField(id = 1)
    public final int zaa;

    @SafeParcelable.Field(getter = "getConnectionResultCode", id = 2)
    public int zab;

    @Nullable
    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", id = 3)
    public Intent zac;

    @SafeParcelable.Constructor
    public zaa(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @Nullable @SafeParcelable.Param(id = 3) Intent intent) {
        this.zaa = i;
        this.zab = i2;
        this.zac = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zab == 0 ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zaa;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zab;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public zaa() {
        this(2, 0, null);
    }
}
