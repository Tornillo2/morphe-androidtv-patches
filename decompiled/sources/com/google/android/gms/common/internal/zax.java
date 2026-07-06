package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
public final class zax extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zax> CREATOR = new zay();

    @SafeParcelable.VersionField(id = 1)
    public final int zaa;

    @SafeParcelable.Field(getter = "getButtonSize", id = 2)
    public final int zab;

    @SafeParcelable.Field(getter = "getColorScheme", id = 3)
    public final int zac;

    @Nullable
    @SafeParcelable.Field(getter = "getScopes", id = 4)
    @Deprecated
    public final Scope[] zad;

    @SafeParcelable.Constructor
    public zax(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @Nullable @SafeParcelable.Param(id = 4) Scope[] scopeArr) {
        this.zaa = i;
        this.zab = i2;
        this.zac = i3;
        this.zad = scopeArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zaa;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zab;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        int i4 = this.zac;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zad, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
