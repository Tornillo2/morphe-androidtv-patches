package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.android.gms.ads.VersionInfo;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
@SafeParcelable.Class(creator = "RtbVersionInfoParcelCreator")
public final class zzbqj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbqj> CREATOR = new zzbqk();

    @SafeParcelable.Field(id = 1)
    public final int zza;

    @SafeParcelable.Field(id = 2)
    public final int zzb;

    @SafeParcelable.Field(id = 3)
    public final int zzc;

    @SafeParcelable.Constructor
    public zzbqj(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
    }

    public static zzbqj zza(VersionInfo versionInfo) {
        return new zzbqj(versionInfo.getMajorVersion(), versionInfo.getMinorVersion(), versionInfo.getMicroVersion());
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzbqj)) {
            zzbqj zzbqjVar = (zzbqj) obj;
            if (zzbqjVar.zzc == this.zzc && zzbqjVar.zzb == this.zzb && zzbqjVar.zza == this.zza) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new int[]{this.zza, this.zzb, this.zzc});
    }

    public final String toString() {
        return this.zza + ExternalFourCCMapper.CODEC_NAME_SPLITTER + this.zzb + ExternalFourCCMapper.CODEC_NAME_SPLITTER + this.zzc;
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
        int i4 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
