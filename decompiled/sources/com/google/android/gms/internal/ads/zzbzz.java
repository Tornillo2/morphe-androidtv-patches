package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "VersionInfoParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzbzz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbzz> CREATOR = new zzcaa();

    @SafeParcelable.Field(id = 2)
    public String zza;

    @SafeParcelable.Field(id = 3)
    public int zzb;

    @SafeParcelable.Field(id = 4)
    public int zzc;

    @SafeParcelable.Field(id = 5)
    public boolean zzd;

    @SafeParcelable.Field(id = 6)
    public boolean zze;

    @SafeParcelable.Constructor
    public zzbzz(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) boolean z2) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = z;
        this.zze = z2;
    }

    public static zzbzz zza() {
        return new zzbzz(12451000, 12451000, true, false, false);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        int i2 = this.zzb;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i2);
        int i3 = this.zzc;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i3);
        boolean z = this.zzd;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zze;
        SafeParcelWriter.zzc(parcel, 6, 4);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public zzbzz(int i, int i2, boolean z, boolean z2) {
        this(231700000, i2, true, false, z2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public zzbzz(int i, int i2, boolean z, boolean z2, boolean z3) {
        String str;
        if (z) {
            str = "0";
        } else {
            str = "1";
        }
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("afma-sdk-a-v", i, ExternalFourCCMapper.CODEC_NAME_SPLITTER, i2, ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        sbM.append(str);
        this(sbM.toString(), i, i2, z, z3);
    }
}
