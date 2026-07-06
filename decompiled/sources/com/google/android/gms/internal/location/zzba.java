package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "LocationRequestInternalCreator")
@SafeParcelable.Reserved({1000, 2, 3, 4})
public final class zzba extends AbstractSafeParcelable {

    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 1)
    public final LocationRequest zzb;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_CLIENTS", id = 5)
    public final List<ClientIdentity> zzc;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 6)
    public final String zzd;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_HIDE_FROM_APP_OPS", id = 7)
    public final boolean zze;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_FORCE_COARSE_LOCATION", id = 8)
    public final boolean zzf;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_EXEMPT_FROM_THROTTLE", id = 9)
    public final boolean zzg;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 10)
    public final String zzh;

    @SafeParcelable.Field(defaultValueUnchecked = "false", id = 11)
    public final boolean zzi;

    @SafeParcelable.Field(defaultValueUnchecked = "false", id = 12)
    public boolean zzj;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 13)
    public String zzk;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_MAX_LOCATION_AGE_MILLIS", id = 14)
    public long zzl;
    public static final List<ClientIdentity> zza = Collections.EMPTY_LIST;
    public static final Parcelable.Creator<zzba> CREATOR = new zzbb();

    @SafeParcelable.Constructor
    public zzba(@SafeParcelable.Param(id = 1) LocationRequest locationRequest, @SafeParcelable.Param(id = 5) List<ClientIdentity> list, @Nullable @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) boolean z3, @Nullable @SafeParcelable.Param(id = 10) String str2, @SafeParcelable.Param(id = 11) boolean z4, @SafeParcelable.Param(id = 12) boolean z5, @Nullable @SafeParcelable.Param(id = 13) String str3, @SafeParcelable.Param(id = 14) long j) {
        this.zzb = locationRequest;
        this.zzc = list;
        this.zzd = str;
        this.zze = z;
        this.zzf = z2;
        this.zzg = z3;
        this.zzh = str2;
        this.zzi = z4;
        this.zzj = z5;
        this.zzk = str3;
        this.zzl = j;
    }

    public static zzba zza(@Nullable String str, LocationRequest locationRequest) {
        return new zzba(locationRequest, zza, null, false, false, false, null, false, false, null, Long.MAX_VALUE);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzba) {
            zzba zzbaVar = (zzba) obj;
            if (Objects.equal(this.zzb, zzbaVar.zzb) && Objects.equal(this.zzc, zzbaVar.zzc) && Objects.equal(this.zzd, zzbaVar.zzd) && this.zze == zzbaVar.zze && this.zzf == zzbaVar.zzf && this.zzg == zzbaVar.zzg && Objects.equal(this.zzh, zzbaVar.zzh) && this.zzi == zzbaVar.zzi && this.zzj == zzbaVar.zzj && Objects.equal(this.zzk, zzbaVar.zzk)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        if (this.zzd != null) {
            sb.append(" tag=");
            sb.append(this.zzd);
        }
        if (this.zzh != null) {
            sb.append(" moduleId=");
            sb.append(this.zzh);
        }
        if (this.zzk != null) {
            sb.append(" contextAttributionTag=");
            sb.append(this.zzk);
        }
        sb.append(" hideAppOps=");
        sb.append(this.zze);
        sb.append(" clients=");
        sb.append(this.zzc);
        sb.append(" forceCoarseLocation=");
        sb.append(this.zzf);
        if (this.zzg) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        if (this.zzi) {
            sb.append(" locationSettingsIgnored");
        }
        if (this.zzj) {
            sb.append(" inaccurateLocationsDelayed");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzb, i, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzd, false);
        boolean z = this.zze;
        SafeParcelWriter.zzc(parcel, 7, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzf;
        SafeParcelWriter.zzc(parcel, 8, 4);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = this.zzg;
        SafeParcelWriter.zzc(parcel, 9, 4);
        parcel.writeInt(z3 ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 10, this.zzh, false);
        boolean z4 = this.zzi;
        SafeParcelWriter.zzc(parcel, 11, 4);
        parcel.writeInt(z4 ? 1 : 0);
        boolean z5 = this.zzj;
        SafeParcelWriter.zzc(parcel, 12, 4);
        parcel.writeInt(z5 ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 13, this.zzk, false);
        long j = this.zzl;
        SafeParcelWriter.zzc(parcel, 14, 8);
        parcel.writeLong(j);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public final zzba zzb(long j) {
        if (this.zzb.getMaxWaitTime() <= this.zzb.zzb) {
            this.zzl = 10000L;
            return this;
        }
        LocationRequest locationRequest = this.zzb;
        long j2 = locationRequest.zzb;
        long maxWaitTime = locationRequest.getMaxWaitTime();
        StringBuilder sb = new StringBuilder(120);
        sb.append("could not set max age when location batching is requested, interval=");
        sb.append(j2);
        sb.append("maxWaitTime=");
        sb.append(maxWaitTime);
        throw new IllegalArgumentException(sb.toString());
    }

    public final zzba zzc(@Nullable String str) {
        this.zzk = str;
        return this;
    }

    public final zzba zzd(boolean z) {
        this.zzj = true;
        return this;
    }
}
