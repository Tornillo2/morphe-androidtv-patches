package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "GoogleCertificatesLookupQueryCreator")
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @SafeParcelable.Field(getter = "getCallingPackage", id = 1)
    public final String zza;

    @SafeParcelable.Field(getter = "getAllowTestKeys", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(getter = "getCallingContextBinder", id = 4, type = "android.os.IBinder")
    public final Context zzd;

    @SafeParcelable.Field(getter = "getIsChimeraPackage", id = 5)
    public final boolean zze;

    @SafeParcelable.Field(getter = "getIncludeHashesInErrorMessage", id = 6)
    public final boolean zzf;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) IBinder iBinder, @SafeParcelable.Param(id = 5) boolean z3, @SafeParcelable.Param(id = 6) boolean z4) {
        this.zza = str;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = (Context) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        this.zze = z3;
        this.zzf = z4;
    }

    /* JADX WARN: Type inference failed for: r6v5, types: [android.os.IBinder, com.google.android.gms.dynamic.IObjectWrapper] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.zza;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        boolean z = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzd), false);
        boolean z3 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(z3 ? 1 : 0);
        boolean z4 = this.zzf;
        SafeParcelWriter.zzc(parcel, 6, 4);
        parcel.writeInt(z4 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
