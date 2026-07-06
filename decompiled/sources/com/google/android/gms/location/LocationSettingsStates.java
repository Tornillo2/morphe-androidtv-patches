package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationSettingsStatesCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsStates extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzbn();

    @SafeParcelable.Field(getter = "isGpsUsable", id = 1)
    public final boolean zza;

    @SafeParcelable.Field(getter = "isNetworkLocationUsable", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(getter = "isBleUsable", id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(getter = "isGpsPresent", id = 4)
    public final boolean zzd;

    @SafeParcelable.Field(getter = "isNetworkLocationPresent", id = 5)
    public final boolean zze;

    @SafeParcelable.Field(getter = "isBlePresent", id = 6)
    public final boolean zzf;

    @SafeParcelable.Constructor
    public LocationSettingsStates(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) boolean z3, @SafeParcelable.Param(id = 4) boolean z4, @SafeParcelable.Param(id = 5) boolean z5, @SafeParcelable.Param(id = 6) boolean z6) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = z3;
        this.zzd = z4;
        this.zze = z5;
        this.zzf = z6;
    }

    @Nullable
    public static LocationSettingsStates fromIntent(@NonNull Intent intent) {
        return (LocationSettingsStates) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public boolean isBlePresent() {
        return this.zzf;
    }

    public boolean isBleUsable() {
        return this.zzc;
    }

    public boolean isGpsPresent() {
        return this.zzd;
    }

    public boolean isGpsUsable() {
        return this.zza;
    }

    public boolean isLocationPresent() {
        return this.zzd || this.zze;
    }

    public boolean isLocationUsable() {
        return this.zza || this.zzb;
    }

    public boolean isNetworkLocationPresent() {
        return this.zze;
    }

    public boolean isNetworkLocationUsable() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean z = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(z3 ? 1 : 0);
        boolean z4 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(z4 ? 1 : 0);
        boolean z5 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(z5 ? 1 : 0);
        boolean z6 = this.zzf;
        SafeParcelWriter.zzc(parcel, 6, 4);
        parcel.writeInt(z6 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
