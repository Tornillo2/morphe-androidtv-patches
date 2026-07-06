package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "TelemetryDataCreator")
public class TelemetryData extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<TelemetryData> CREATOR = new zaab();

    @SafeParcelable.Field(getter = "getTelemetryConfigVersion", id = 1)
    public final int zaa;

    @Nullable
    @SafeParcelable.Field(getter = "getMethodInvocations", id = 2)
    public List zab;

    @SafeParcelable.Constructor
    public TelemetryData(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) @Nullable List list) {
        this.zaa = i;
        this.zab = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zaa;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zab, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public final int zaa() {
        return this.zaa;
    }

    @androidx.annotation.Nullable
    public final List zab() {
        return this.zab;
    }

    public final void zac(@NonNull MethodInvocation methodInvocation) {
        if (this.zab == null) {
            this.zab = new ArrayList();
        }
        this.zab.add(methodInvocation);
    }
}
