package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
@SafeParcelable.Class(creator = "SignalConfigurationParcelCreator")
public final class zzbyo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbyo> CREATOR = new zzbyp();

    @SafeParcelable.Field(id = 1)
    @Deprecated
    public final String zza;

    @SafeParcelable.Field(id = 2)
    public final String zzb;

    @SafeParcelable.Field(id = 3)
    @Deprecated
    public final zzq zzc;

    @SafeParcelable.Field(id = 4)
    public final zzl zzd;

    @SafeParcelable.Constructor
    public zzbyo(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) zzq zzqVar, @SafeParcelable.Param(id = 4) zzl zzlVar) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzqVar;
        this.zzd = zzlVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
