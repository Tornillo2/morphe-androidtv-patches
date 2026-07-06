package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationAvailabilityCreator")
@SafeParcelable.Reserved({1000})
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzbe();

    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 1)
    @Deprecated
    public int zza;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 2)
    @Deprecated
    public int zzb;

    @SafeParcelable.Field(defaultValueUnchecked = "0", id = 3)
    public long zzc;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", id = 4)
    public int zzd;

    @SafeParcelable.Field(id = 5)
    public zzbo[] zze;

    @SafeParcelable.Constructor
    public LocationAvailability(@SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 5) zzbo[] zzboVarArr) {
        this.zzd = i;
        this.zza = i2;
        this.zzb = i3;
        this.zzc = j;
        this.zze = zzboVarArr;
    }

    @NonNull
    public static LocationAvailability extractLocationAvailability(@NonNull Intent intent) {
        if (!hasLocationAvailability(intent)) {
            return null;
        }
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                return (LocationAvailability) extras.getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
            }
        } catch (ClassCastException unused) {
        }
        return null;
    }

    public static boolean hasLocationAvailability(@NonNull Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof LocationAvailability) {
            LocationAvailability locationAvailability = (LocationAvailability) obj;
            if (this.zza == locationAvailability.zza && this.zzb == locationAvailability.zzb && this.zzc == locationAvailability.zzc && this.zzd == locationAvailability.zzd && Arrays.equals(this.zze, locationAvailability.zze)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Long.valueOf(this.zzc), this.zze});
    }

    public boolean isLocationAvailable() {
        return this.zzd < 1000;
    }

    @NonNull
    public String toString() {
        boolean zIsLocationAvailable = isLocationAvailable();
        StringBuilder sb = new StringBuilder(48);
        sb.append("LocationAvailability[isLocationAvailable: ");
        sb.append(zIsLocationAvailable);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        long j = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 8);
        parcel.writeLong(j);
        int i4 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
