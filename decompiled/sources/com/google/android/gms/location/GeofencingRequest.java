package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "GeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
public class GeofencingRequest extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzau();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;

    @SafeParcelable.Field(getter = "getParcelableGeofences", id = 1)
    public final List<com.google.android.gms.internal.location.zzbe> zza;

    @InitialTrigger
    @SafeParcelable.Field(getter = "getInitialTrigger", id = 2)
    public final int zzb;

    @SafeParcelable.Field(defaultValue = "", getter = "getTag", id = 3)
    public final String zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getContextAttributionTag", id = 4)
    public final String zzd;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final List<com.google.android.gms.internal.location.zzbe> zza = new ArrayList();

        @InitialTrigger
        public int zzb = 5;
        public String zzc = "";

        @NonNull
        public Builder addGeofence(@NonNull Geofence geofence) {
            Preconditions.checkNotNull(geofence, "geofence can't be null.");
            Preconditions.checkArgument(geofence instanceof com.google.android.gms.internal.location.zzbe, "Geofence must be created using Geofence.Builder.");
            this.zza.add((com.google.android.gms.internal.location.zzbe) geofence);
            return this;
        }

        @NonNull
        public Builder addGeofences(@NonNull List<Geofence> list) {
            if (list != null && !list.isEmpty()) {
                for (Geofence geofence : list) {
                    if (geofence != null) {
                        addGeofence(geofence);
                    }
                }
            }
            return this;
        }

        @NonNull
        public GeofencingRequest build() {
            Preconditions.checkArgument(!this.zza.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.zza, this.zzb, this.zzc, null);
        }

        @NonNull
        public Builder setInitialTrigger(@InitialTrigger int i) {
            this.zzb = i & 7;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public @interface InitialTrigger {
    }

    @SafeParcelable.Constructor
    public GeofencingRequest(@SafeParcelable.Param(id = 1) List<com.google.android.gms.internal.location.zzbe> list, @SafeParcelable.Param(id = 2) @InitialTrigger int i, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2) {
        this.zza = list;
        this.zzb = i;
        this.zzc = str;
        this.zzd = str2;
    }

    @NonNull
    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zza);
        return arrayList;
    }

    @InitialTrigger
    public int getInitialTrigger() {
        return this.zzb;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("GeofencingRequest[geofences=");
        sb.append(this.zza);
        sb.append(", initialTrigger=");
        sb.append(this.zzb);
        sb.append(", tag=");
        sb.append(this.zzc);
        sb.append(", attributionTag=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, this.zzd, "]");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        int initialTrigger = getInitialTrigger();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(initialTrigger);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @NonNull
    public final GeofencingRequest zza(@Nullable String str) {
        return new GeofencingRequest(this.zza, this.zzb, this.zzc, str);
    }
}
