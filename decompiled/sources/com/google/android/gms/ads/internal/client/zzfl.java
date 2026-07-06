package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "VideoOptionsParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzfl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfl> CREATOR = new zzfm();

    @SafeParcelable.Field(id = 2)
    public final boolean zza;

    @SafeParcelable.Field(id = 3)
    public final boolean zzb;

    @SafeParcelable.Field(id = 4)
    public final boolean zzc;

    public zzfl(VideoOptions videoOptions) {
        this(videoOptions.zza, videoOptions.zzb, videoOptions.zzc);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean z = this.zza;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzb;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = this.zzc;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(z3 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @SafeParcelable.Constructor
    public zzfl(@SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) boolean z3) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = z3;
    }
}
