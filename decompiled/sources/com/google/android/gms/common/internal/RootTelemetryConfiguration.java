package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "RootTelemetryConfigurationCreator")
public class RootTelemetryConfiguration extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = new zzaj();

    @SafeParcelable.Field(getter = "getVersion", id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getMethodInvocationTelemetryEnabled", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(getter = "getMethodTimingTelemetryEnabled", id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(getter = "getBatchPeriodMillis", id = 4)
    public final int zzd;

    @SafeParcelable.Field(getter = "getMaxMethodInvocationsInBatch", id = 5)
    public final int zze;

    @SafeParcelable.Constructor
    public RootTelemetryConfiguration(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) int i3) {
        this.zza = i;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = i2;
        this.zze = i3;
    }

    @KeepForSdk
    public int getBatchPeriodMillis() {
        return this.zzd;
    }

    @KeepForSdk
    public int getMaxMethodInvocationsInBatch() {
        return this.zze;
    }

    @KeepForSdk
    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    @KeepForSdk
    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @KeepForSdk
    public int getVersion() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int version = getVersion();
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(version);
        boolean methodInvocationTelemetryEnabled = getMethodInvocationTelemetryEnabled();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(methodInvocationTelemetryEnabled ? 1 : 0);
        boolean methodTimingTelemetryEnabled = getMethodTimingTelemetryEnabled();
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(methodTimingTelemetryEnabled ? 1 : 0);
        int batchPeriodMillis = getBatchPeriodMillis();
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(batchPeriodMillis);
        int maxMethodInvocationsInBatch = getMaxMethodInvocationsInBatch();
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(maxMethodInvocationsInBatch);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
