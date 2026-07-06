package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "AdapterStatusParcelCreator")
public final class zzbke extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbke> CREATOR = new zzbkf();

    @SafeParcelable.Field(id = 1)
    public final String zza;

    @SafeParcelable.Field(id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(id = 3)
    public final int zzc;

    @SafeParcelable.Field(id = 4)
    public final String zzd;

    @SafeParcelable.Constructor
    public zzbke(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) String str2) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
        this.zzd = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        boolean z = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        int i2 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
