package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationSettingsRequestCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsRequest extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzbl();

    @SafeParcelable.Field(getter = "getLocationRequests", id = 1)
    public final List<LocationRequest> zza;

    @SafeParcelable.Field(defaultValue = "false", getter = "alwaysShow", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(getter = "needBle", id = 3)
    public final boolean zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getConfiguration", id = 5)
    public zzbj zzd;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final ArrayList<LocationRequest> zza = new ArrayList<>();
        public boolean zzb = false;
        public boolean zzc = false;

        @NonNull
        public Builder addAllLocationRequests(@NonNull Collection<LocationRequest> collection) {
            for (LocationRequest locationRequest : collection) {
                if (locationRequest != null) {
                    this.zza.add(locationRequest);
                }
            }
            return this;
        }

        @NonNull
        public Builder addLocationRequest(@NonNull LocationRequest locationRequest) {
            if (locationRequest != null) {
                this.zza.add(locationRequest);
            }
            return this;
        }

        @NonNull
        public LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.zza, this.zzb, this.zzc, null);
        }

        @NonNull
        public Builder setAlwaysShow(boolean z) {
            this.zzb = z;
            return this;
        }

        @NonNull
        public Builder setNeedBle(boolean z) {
            this.zzc = z;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public LocationSettingsRequest(@SafeParcelable.Param(id = 1) List<LocationRequest> list, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @Nullable @SafeParcelable.Param(id = 5) zzbj zzbjVar) {
        this.zza = list;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = zzbjVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeTypedList(parcel, 1, DesugarCollections.unmodifiableList(this.zza), false);
        boolean z = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
