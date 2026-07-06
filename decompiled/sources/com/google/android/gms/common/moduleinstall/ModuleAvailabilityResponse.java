package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "ModuleAvailabilityResponseCreator")
public class ModuleAvailabilityResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleAvailabilityResponse> CREATOR = new zaa();

    @SafeParcelable.Field(getter = "areModulesAvailable", id = 1)
    public final boolean zaa;

    @SafeParcelable.Field(getter = "getAvailabilityStatus", id = 2)
    public final int zab;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.CLASS)
    public @interface AvailabilityStatus {
        public static final int STATUS_ALREADY_AVAILABLE = 0;
        public static final int STATUS_READY_TO_DOWNLOAD = 1;
        public static final int STATUS_UNKNOWN_MODULE = 2;
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleAvailabilityResponse(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) int i) {
        this.zaa = z;
        this.zab = i;
    }

    public boolean areModulesAvailable() {
        return this.zaa;
    }

    @AvailabilityStatus
    public int getAvailabilityStatus() {
        return this.zab;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean zAreModulesAvailable = areModulesAvailable();
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(zAreModulesAvailable ? 1 : 0);
        int availabilityStatus = getAvailabilityStatus();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(availabilityStatus);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
