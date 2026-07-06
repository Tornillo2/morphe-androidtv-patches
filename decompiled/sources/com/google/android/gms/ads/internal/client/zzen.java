package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LiteSdkVersionsParcelCreator")
public final class zzen extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzen> CREATOR = new zzeo();

    @SafeParcelable.Field(getter = "getAdsDynamiteVersion", id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getSdkVersionLite", id = 2)
    public final int zzb;

    @SafeParcelable.Field(getter = "getGranularVersion", id = 3)
    public final String zzc;

    @SafeParcelable.Constructor
    public zzen(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) String str) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public final int zza() {
        return this.zzb;
    }

    public zzen() {
        this(231710100, 231700000, "22.2.0");
    }
}
