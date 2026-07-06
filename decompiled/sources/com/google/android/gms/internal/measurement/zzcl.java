package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "InitializationParamsCreator")
public final class zzcl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcl> CREATOR = new zzcm();

    @SafeParcelable.Field(id = 1)
    public final long zza;

    @SafeParcelable.Field(id = 2)
    public final long zzb;

    @SafeParcelable.Field(id = 3)
    public final boolean zzc;

    @Nullable
    @SafeParcelable.Field(id = 4)
    public final String zzd;

    @Nullable
    @SafeParcelable.Field(id = 5)
    public final String zze;

    @Nullable
    @SafeParcelable.Field(id = 6)
    public final String zzf;

    @Nullable
    @SafeParcelable.Field(id = 7)
    public final Bundle zzg;

    @Nullable
    @SafeParcelable.Field(id = 8)
    public final String zzh;

    @SafeParcelable.Constructor
    public zzcl(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) boolean z, @Nullable @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) String str2, @Nullable @SafeParcelable.Param(id = 6) String str3, @Nullable @SafeParcelable.Param(id = 7) Bundle bundle, @Nullable @SafeParcelable.Param(id = 8) String str4) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = z;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = bundle;
        this.zzh = str4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        long j = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 8);
        parcel.writeLong(j);
        long j2 = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(j2);
        boolean z = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
