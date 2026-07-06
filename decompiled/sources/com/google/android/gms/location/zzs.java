package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)
    public boolean zza;

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)
    public long zzb;

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)
    public float zzc;

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)
    public long zzd;

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)
    public int zze;

    @SafeParcelable.Constructor
    public zzs(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) int i) {
        this.zza = z;
        this.zzb = j;
        this.zzc = f;
        this.zzd = j2;
        this.zze = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzs)) {
            return false;
        }
        zzs zzsVar = (zzs) obj;
        return this.zza == zzsVar.zza && this.zzb == zzsVar.zzb && Float.compare(this.zzc, zzsVar.zzc) == 0 && this.zzd == zzsVar.zzd && this.zze == zzsVar.zze;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zza), Long.valueOf(this.zzb), Float.valueOf(this.zzc), Long.valueOf(this.zzd), Integer.valueOf(this.zze)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeviceOrientationRequest[mShouldUseMag=");
        sb.append(this.zza);
        sb.append(" mMinimumSamplingPeriodMs=");
        sb.append(this.zzb);
        sb.append(" mSmallestAngleChangeRadians=");
        sb.append(this.zzc);
        long j = this.zzd;
        if (j != Long.MAX_VALUE) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j - jElapsedRealtime);
            sb.append("ms");
        }
        if (this.zze != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zze);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean z = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(z ? 1 : 0);
        long j = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(j);
        float f = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeFloat(f);
        long j2 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 8);
        parcel.writeLong(j2);
        int i2 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public zzs() {
        this(true, 50L, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }
}
