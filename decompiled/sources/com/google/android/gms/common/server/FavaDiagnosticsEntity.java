package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();

    @SafeParcelable.VersionField(id = 1)
    public final int zaa;

    @NonNull
    @SafeParcelable.Field(id = 2)
    public final String zab;

    @SafeParcelable.Field(id = 3)
    public final int zac;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(id = 1) int i, @NonNull @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
        this.zaa = i;
        this.zab = str;
        this.zac = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int i2 = this.zaa;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        int i3 = this.zac;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(@NonNull String str, int i) {
        this.zaa = 1;
        this.zab = str;
        this.zac = i;
    }
}
