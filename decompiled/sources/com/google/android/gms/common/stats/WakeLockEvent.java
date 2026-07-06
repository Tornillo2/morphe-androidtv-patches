package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "WakeLockEventCreator")
@Deprecated
public final class WakeLockEvent extends StatsEvent {

    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();

    @SafeParcelable.VersionField(id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getTimeMillis", id = 2)
    public final long zzb;

    @SafeParcelable.Field(getter = "getEventType", id = 11)
    public final int zzc;

    @SafeParcelable.Field(getter = "getWakeLockName", id = 4)
    public final String zzd;

    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", id = 10)
    public final String zze;

    @SafeParcelable.Field(getter = "getCodePackage", id = 17)
    public final String zzf;

    @SafeParcelable.Field(getter = "getWakeLockType", id = 5)
    public final int zzg;

    @Nullable
    @SafeParcelable.Field(getter = "getCallingPackages", id = 6)
    public final List zzh;

    @SafeParcelable.Field(getter = "getEventKey", id = 12)
    public final String zzi;

    @SafeParcelable.Field(getter = "getElapsedRealtime", id = 8)
    public final long zzj;

    @SafeParcelable.Field(getter = "getDeviceState", id = 14)
    public final int zzk;

    @SafeParcelable.Field(getter = "getHostPackage", id = 13)
    public final String zzl;

    @SafeParcelable.Field(getter = "getBeginPowerPercentage", id = 15)
    public final float zzm;

    @SafeParcelable.Field(getter = "getTimeout", id = 16)
    public final long zzn;

    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", id = 18)
    public final boolean zzo;

    @SafeParcelable.Constructor
    public WakeLockEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 11) int i2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) @Nullable List list, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 14) int i4, @SafeParcelable.Param(id = 10) String str3, @SafeParcelable.Param(id = 13) String str4, @SafeParcelable.Param(id = 15) float f, @SafeParcelable.Param(id = 16) long j3, @SafeParcelable.Param(id = 17) String str5, @SafeParcelable.Param(id = 18) boolean z) {
        this.zza = i;
        this.zzb = j;
        this.zzc = i2;
        this.zzd = str;
        this.zze = str3;
        this.zzf = str5;
        this.zzg = i3;
        this.zzh = list;
        this.zzi = str2;
        this.zzj = j2;
        this.zzk = i4;
        this.zzl = str4;
        this.zzm = f;
        this.zzn = j3;
        this.zzo = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        long j = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(j);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        int i3 = this.zzg;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzh, false);
        long j2 = this.zzj;
        SafeParcelWriter.zzc(parcel, 8, 8);
        parcel.writeLong(j2);
        SafeParcelWriter.writeString(parcel, 10, this.zze, false);
        int i4 = this.zzc;
        SafeParcelWriter.zzc(parcel, 11, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.writeString(parcel, 12, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        int i5 = this.zzk;
        SafeParcelWriter.zzc(parcel, 14, 4);
        parcel.writeInt(i5);
        float f = this.zzm;
        SafeParcelWriter.zzc(parcel, 15, 4);
        parcel.writeFloat(f);
        long j3 = this.zzn;
        SafeParcelWriter.zzc(parcel, 16, 8);
        parcel.writeLong(j3);
        SafeParcelWriter.writeString(parcel, 17, this.zzf, false);
        boolean z = this.zzo;
        SafeParcelWriter.zzc(parcel, 18, 4);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    @NonNull
    public final String zzc() {
        List list = this.zzh;
        String strJoin = list == null ? "" : TextUtils.join(",", list);
        int i = this.zzk;
        String str = this.zze;
        String str2 = this.zzl;
        float f = this.zzm;
        String str3 = this.zzf;
        int i2 = this.zzg;
        String str4 = this.zzd;
        boolean z = this.zzo;
        StringBuilder sb = new StringBuilder("\t");
        sb.append(str4);
        sb.append("\t");
        sb.append(i2);
        sb.append("\t");
        sb.append(strJoin);
        sb.append("\t");
        sb.append(i);
        sb.append("\t");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("\t");
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("\t");
        sb.append(f);
        sb.append("\t");
        sb.append(str3 != null ? str3 : "");
        sb.append("\t");
        sb.append(z);
        return sb.toString();
    }
}
