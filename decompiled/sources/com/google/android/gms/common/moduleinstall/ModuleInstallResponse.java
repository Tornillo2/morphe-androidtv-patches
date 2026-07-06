package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "ModuleInstallResponseCreator")
public class ModuleInstallResponse extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleInstallResponse> CREATOR = new zad();

    @SafeParcelable.Field(getter = "getSessionId", id = 1)
    public final int zaa;

    @SafeParcelable.Field(defaultValue = "false", getter = "getShouldUnregisterListener", id = 2)
    public final boolean zab;

    @SafeParcelable.Constructor
    public ModuleInstallResponse(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z) {
        this.zaa = i;
        this.zab = z;
    }

    public boolean areModulesAlreadyInstalled() {
        return this.zaa == 0;
    }

    public int getSessionId() {
        return this.zaa;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int sessionId = getSessionId();
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(sessionId);
        boolean z = this.zab;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public final boolean zaa() {
        return this.zab;
    }

    @KeepForSdk
    public ModuleInstallResponse(int i) {
        this(i, false);
    }
}
