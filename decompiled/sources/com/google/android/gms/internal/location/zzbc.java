package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationRequestUpdateDataCreator")
@SafeParcelable.Reserved({1000})
public final class zzbc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbc> CREATOR = new zzbd();

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", id = 1)
    public final int zza;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 2)
    public final zzba zzb;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, getter = "getLocationListenerBinder", id = 3, type = "android.os.IBinder")
    public final com.google.android.gms.location.zzbd zzc;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 4)
    public final PendingIntent zzd;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, getter = "getLocationCallbackBinder", id = 5, type = "android.os.IBinder")
    public final com.google.android.gms.location.zzba zze;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, getter = "getFusedLocationProviderCallbackBinder", id = 6, type = "android.os.IBinder")
    public final zzai zzf;

    @SafeParcelable.Constructor
    public zzbc(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) zzba zzbaVar, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 4) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 5) IBinder iBinder2, @Nullable @SafeParcelable.Param(id = 6) IBinder iBinder3) {
        this.zza = i;
        this.zzb = zzbaVar;
        zzai zzagVar = null;
        this.zzc = iBinder == null ? null : com.google.android.gms.location.zzbc.zzb(iBinder);
        this.zzd = pendingIntent;
        this.zze = iBinder2 == null ? null : com.google.android.gms.location.zzaz.zzb(iBinder2);
        if (iBinder3 != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzagVar = iInterfaceQueryLocalInterface instanceof zzai ? (zzai) iInterfaceQueryLocalInterface : new zzag(iBinder3);
        }
        this.zzf = zzagVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.IBinder, com.google.android.gms.location.zzbd] */
    public static zzbc zza(com.google.android.gms.location.zzbd zzbdVar, @Nullable zzai zzaiVar) {
        if (zzaiVar == null) {
            zzaiVar = null;
        }
        return new zzbc(2, null, zzbdVar, null, null, zzaiVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzbc zzb(zzba zzbaVar, PendingIntent pendingIntent, @Nullable zzai zzaiVar) {
        return new zzbc(1, zzbaVar, null, pendingIntent, null, zzaiVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.IBinder, com.google.android.gms.location.zzba] */
    public static zzbc zzc(com.google.android.gms.location.zzba zzbaVar, @Nullable zzai zzaiVar) {
        if (zzaiVar == null) {
            zzaiVar = null;
        }
        return new zzbc(2, null, null, null, zzbaVar, zzaiVar);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        com.google.android.gms.location.zzbd zzbdVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbdVar == null ? null : zzbdVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        com.google.android.gms.location.zzba zzbaVar = this.zze;
        SafeParcelWriter.writeIBinder(parcel, 5, zzbaVar == null ? null : zzbaVar.asBinder(), false);
        zzai zzaiVar = this.zzf;
        SafeParcelWriter.writeIBinder(parcel, 6, zzaiVar != null ? zzaiVar.asBinder() : null, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
