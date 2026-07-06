package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ads.zzbzu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "AdRequestParcelCreator")
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzn();

    @SafeParcelable.Field(id = 1)
    public final int zza;

    @SafeParcelable.Field(id = 2)
    @Deprecated
    public final long zzb;

    @SafeParcelable.Field(id = 3)
    public final Bundle zzc;

    @SafeParcelable.Field(id = 4)
    @Deprecated
    public final int zzd;

    @SafeParcelable.Field(id = 5)
    public final List zze;

    @SafeParcelable.Field(id = 6)
    public final boolean zzf;

    @SafeParcelable.Field(id = 7)
    public final int zzg;

    @SafeParcelable.Field(id = 8)
    public final boolean zzh;

    @SafeParcelable.Field(id = 9)
    public final String zzi;

    @SafeParcelable.Field(id = 10)
    public final zzfh zzj;

    @SafeParcelable.Field(id = 11)
    public final Location zzk;

    @SafeParcelable.Field(id = 12)
    public final String zzl;

    @SafeParcelable.Field(id = 13)
    public final Bundle zzm;

    @SafeParcelable.Field(id = 14)
    public final Bundle zzn;

    @SafeParcelable.Field(id = 15)
    public final List zzo;

    @SafeParcelable.Field(id = 16)
    public final String zzp;

    @SafeParcelable.Field(id = 17)
    public final String zzq;

    @SafeParcelable.Field(id = 18)
    @Deprecated
    public final boolean zzr;

    @Nullable
    @SafeParcelable.Field(id = 19)
    public final zzc zzs;

    @SafeParcelable.Field(id = 20)
    public final int zzt;

    @Nullable
    @SafeParcelable.Field(id = 21)
    public final String zzu;

    @SafeParcelable.Field(id = 22)
    public final List zzv;

    @SafeParcelable.Field(id = 23)
    public final int zzw;

    @Nullable
    @SafeParcelable.Field(id = 24)
    public final String zzx;

    @SafeParcelable.Constructor
    public zzl(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) Bundle bundle, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) List list, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) int i3, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) String str, @SafeParcelable.Param(id = 10) zzfh zzfhVar, @SafeParcelable.Param(id = 11) Location location, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 13) Bundle bundle2, @SafeParcelable.Param(id = 14) Bundle bundle3, @SafeParcelable.Param(id = 15) List list2, @SafeParcelable.Param(id = 16) String str3, @SafeParcelable.Param(id = 17) String str4, @SafeParcelable.Param(id = 18) boolean z3, @SafeParcelable.Param(id = 19) zzc zzcVar, @SafeParcelable.Param(id = 20) int i4, @Nullable @SafeParcelable.Param(id = 21) String str5, @SafeParcelable.Param(id = 22) List list3, @SafeParcelable.Param(id = 23) int i5, @SafeParcelable.Param(id = 24) String str6) {
        this.zza = i;
        this.zzb = j;
        this.zzc = bundle == null ? new Bundle() : bundle;
        this.zzd = i2;
        this.zze = list;
        this.zzf = z;
        this.zzg = i3;
        this.zzh = z2;
        this.zzi = str;
        this.zzj = zzfhVar;
        this.zzk = location;
        this.zzl = str2;
        this.zzm = bundle2 == null ? new Bundle() : bundle2;
        this.zzn = bundle3;
        this.zzo = list2;
        this.zzp = str3;
        this.zzq = str4;
        this.zzr = z3;
        this.zzs = zzcVar;
        this.zzt = i4;
        this.zzu = str5;
        this.zzv = list3 == null ? new ArrayList() : list3;
        this.zzw = i5;
        this.zzx = str6;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzlVar = (zzl) obj;
        return this.zza == zzlVar.zza && this.zzb == zzlVar.zzb && zzbzu.zza(this.zzc, zzlVar.zzc) && this.zzd == zzlVar.zzd && Objects.equal(this.zze, zzlVar.zze) && this.zzf == zzlVar.zzf && this.zzg == zzlVar.zzg && this.zzh == zzlVar.zzh && Objects.equal(this.zzi, zzlVar.zzi) && Objects.equal(this.zzj, zzlVar.zzj) && Objects.equal(this.zzk, zzlVar.zzk) && Objects.equal(this.zzl, zzlVar.zzl) && zzbzu.zza(this.zzm, zzlVar.zzm) && zzbzu.zza(this.zzn, zzlVar.zzn) && Objects.equal(this.zzo, zzlVar.zzo) && Objects.equal(this.zzp, zzlVar.zzp) && Objects.equal(this.zzq, zzlVar.zzq) && this.zzr == zzlVar.zzr && this.zzt == zzlVar.zzt && Objects.equal(this.zzu, zzlVar.zzu) && Objects.equal(this.zzv, zzlVar.zzv) && this.zzw == zzlVar.zzw && Objects.equal(this.zzx, zzlVar.zzx);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Long.valueOf(this.zzb), this.zzc, Integer.valueOf(this.zzd), this.zze, Boolean.valueOf(this.zzf), Integer.valueOf(this.zzg), Boolean.valueOf(this.zzh), this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, Boolean.valueOf(this.zzr), Integer.valueOf(this.zzt), this.zzu, this.zzv, Integer.valueOf(this.zzw), this.zzx});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        long j = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(j);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzc, false);
        int i3 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.writeStringList(parcel, 5, this.zze, false);
        boolean z = this.zzf;
        SafeParcelWriter.zzc(parcel, 6, 4);
        parcel.writeInt(z ? 1 : 0);
        int i4 = this.zzg;
        SafeParcelWriter.zzc(parcel, 7, 4);
        parcel.writeInt(i4);
        boolean z2 = this.zzh;
        SafeParcelWriter.zzc(parcel, 8, 4);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 9, this.zzi, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzl, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzm, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzn, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzo, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzp, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzq, false);
        boolean z3 = this.zzr;
        SafeParcelWriter.zzc(parcel, 18, 4);
        parcel.writeInt(z3 ? 1 : 0);
        SafeParcelWriter.writeParcelable(parcel, 19, this.zzs, i, false);
        int i5 = this.zzt;
        SafeParcelWriter.zzc(parcel, 20, 4);
        parcel.writeInt(i5);
        SafeParcelWriter.writeString(parcel, 21, this.zzu, false);
        SafeParcelWriter.writeStringList(parcel, 22, this.zzv, false);
        int i6 = this.zzw;
        SafeParcelWriter.zzc(parcel, 23, 4);
        parcel.writeInt(i6);
        SafeParcelWriter.writeString(parcel, 24, this.zzx, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
