package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.internal.client.zzfl;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "NativeAdOptionsParcelCreator")
public final class zzbee extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbee> CREATOR = new zzbef();

    @SafeParcelable.Field(id = 1)
    public final int zza;

    @SafeParcelable.Field(id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(id = 3)
    public final int zzc;

    @SafeParcelable.Field(id = 4)
    public final boolean zzd;

    @SafeParcelable.Field(id = 5)
    public final int zze;

    @Nullable
    @SafeParcelable.Field(id = 6)
    public final zzfl zzf;

    @SafeParcelable.Field(id = 7)
    public final boolean zzg;

    @SafeParcelable.Field(id = 8)
    public final int zzh;

    @SafeParcelable.Field(id = 9)
    public final int zzi;

    @SafeParcelable.Field(id = 10)
    public final boolean zzj;

    @SafeParcelable.Constructor
    public zzbee(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) boolean z2, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) zzfl zzflVar, @SafeParcelable.Param(id = 7) boolean z3, @SafeParcelable.Param(id = 8) int i4, @SafeParcelable.Param(id = 9) int i5, @SafeParcelable.Param(id = 10) boolean z4) {
        this.zza = i;
        this.zzb = z;
        this.zzc = i2;
        this.zzd = z2;
        this.zze = i3;
        this.zzf = zzflVar;
        this.zzg = z3;
        this.zzh = i4;
        this.zzj = z4;
        this.zzi = i5;
    }

    @NonNull
    public static NativeAdOptions zza(@Nullable zzbee zzbeeVar) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (zzbeeVar == null) {
            return builder.build();
        }
        int i = zzbeeVar.zza;
        if (i == 2) {
            builder.zze = zzbeeVar.zze;
        } else {
            if (i != 3) {
                if (i == 4) {
                    builder.zzf = zzbeeVar.zzg;
                    builder.zzb = zzbeeVar.zzh;
                    int i2 = zzbeeVar.zzi;
                    builder.zzg = zzbeeVar.zzj;
                    builder.zzh = i2;
                }
            }
            zzfl zzflVar = zzbeeVar.zzf;
            if (zzflVar != null) {
                builder.zzd = new VideoOptions(zzflVar);
            }
            builder.zze = zzbeeVar.zze;
        }
        builder.zza = zzbeeVar.zzb;
        builder.zzc = zzbeeVar.zzd;
        return builder.build();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        boolean z = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        int i3 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i3);
        boolean z2 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(z2 ? 1 : 0);
        int i4 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        boolean z3 = this.zzg;
        SafeParcelWriter.zzc(parcel, 7, 4);
        parcel.writeInt(z3 ? 1 : 0);
        int i5 = this.zzh;
        SafeParcelWriter.zzc(parcel, 8, 4);
        parcel.writeInt(i5);
        int i6 = this.zzi;
        SafeParcelWriter.zzc(parcel, 9, 4);
        parcel.writeInt(i6);
        boolean z4 = this.zzj;
        SafeParcelWriter.zzc(parcel, 10, 4);
        parcel.writeInt(z4 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public zzbee(@NonNull com.google.android.gms.ads.formats.NativeAdOptions nativeAdOptions) {
        boolean z = nativeAdOptions.zza;
        int i = nativeAdOptions.zzb;
        boolean z2 = nativeAdOptions.zzd;
        int i2 = nativeAdOptions.zze;
        VideoOptions videoOptions = nativeAdOptions.zzf;
        this(4, z, i, z2, i2, videoOptions != null ? new zzfl(videoOptions) : null, nativeAdOptions.zzg, nativeAdOptions.zzc, 0, false);
    }
}
