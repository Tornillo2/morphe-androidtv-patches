package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "GoogleCertificatesLookupResponseCreator")
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();

    @SafeParcelable.Field(getter = "getResult", id = 1)
    public final boolean zza;

    @Nullable
    @SafeParcelable.Field(getter = "getErrorMessage", id = 2)
    public final String zzb;

    @SafeParcelable.Field(getter = "getStatusValue", id = 3)
    public final int zzc;

    @SafeParcelable.Field(getter = "getFirstPartyStatusValue", id = 4)
    public final int zzd;

    @SafeParcelable.Constructor
    public zzq(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2) {
        this.zza = z;
        this.zzb = str;
        this.zzc = zzy.zza(i) - 1;
        this.zzd = zzd.zza(i2) - 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean z = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        int i2 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i2);
        int i3 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zza;
    }

    public final int zzc() {
        return zzd.zza(this.zzd);
    }

    public final int zzd() {
        return zzy.zza(this.zzc);
    }
}
