package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "ModuleInstallIntentResponseCreator")
public class ModuleInstallIntentResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleInstallIntentResponse> CREATOR = new zab();

    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    public final PendingIntent zaa;

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleInstallIntentResponse(@Nullable @SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.zaa = pendingIntent;
    }

    @Nullable
    public PendingIntent getPendingIntent() {
        return this.zaa;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
