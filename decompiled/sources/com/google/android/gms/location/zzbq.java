package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "RemoveGeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
public final class zzbq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbq> CREATOR = new zzbr();

    @SafeParcelable.Field(getter = "getGeofenceIds", id = 1)
    public final List<String> zza;

    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 2)
    public final PendingIntent zzb;

    @SafeParcelable.Field(defaultValue = "", getter = "getTag", id = 3)
    public final String zzc;

    @SafeParcelable.Constructor
    public zzbq(@Nullable @SafeParcelable.Param(id = 1) List<String> list, @Nullable @SafeParcelable.Param(id = 2) PendingIntent pendingIntent, @SafeParcelable.Param(id = 3) String str) {
        this.zza = list == null ? com.google.android.gms.internal.location.zzbs.zzi() : com.google.android.gms.internal.location.zzbs.zzj(list);
        this.zzb = pendingIntent;
        this.zzc = str;
    }

    public static zzbq zza(List<String> list) {
        Preconditions.checkNotNull(list, "geofence can't be null.");
        Preconditions.checkArgument(!list.isEmpty(), "Geofences must contains at least one id.");
        return new zzbq(list, null, "");
    }

    public static zzbq zzb(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent can not be null.");
        return new zzbq(null, pendingIntent, "");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeStringList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
