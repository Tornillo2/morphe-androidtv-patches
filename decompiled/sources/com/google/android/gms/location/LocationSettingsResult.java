package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationSettingsResultCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {

    @NonNull
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzbm();

    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status zza;

    @Nullable
    @SafeParcelable.Field(getter = "getLocationSettingsStates", id = 2)
    public final LocationSettingsStates zzb;

    @SafeParcelable.Constructor
    public LocationSettingsResult(@NonNull @SafeParcelable.Param(id = 1) Status status, @Nullable @SafeParcelable.Param(id = 2) LocationSettingsStates locationSettingsStates) {
        this.zza = status;
        this.zzb = locationSettingsStates;
    }

    @Nullable
    public LocationSettingsStates getLocationSettingsStates() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
