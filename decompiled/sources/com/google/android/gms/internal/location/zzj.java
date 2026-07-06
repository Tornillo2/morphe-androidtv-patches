package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "DeviceOrientationRequestInternalCreator")
public final class zzj extends AbstractSafeParcelable {

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)
    public final com.google.android.gms.location.zzs zzc;

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)
    public final List<ClientIdentity> zzd;

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = AbstractJsonLexerKt.NULL, id = 3)
    public final String zze;

    @VisibleForTesting
    public static final List<ClientIdentity> zza = Collections.EMPTY_LIST;
    public static final com.google.android.gms.location.zzs zzb = new com.google.android.gms.location.zzs();
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) com.google.android.gms.location.zzs zzsVar, @SafeParcelable.Param(id = 2) List<ClientIdentity> list, @SafeParcelable.Param(id = 3) String str) {
        this.zzc = zzsVar;
        this.zzd = list;
        this.zze = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzjVar = (zzj) obj;
        return Objects.equal(this.zzc, zzjVar.zzc) && Objects.equal(this.zzd, zzjVar.zzd) && Objects.equal(this.zze, zzjVar.zze);
    }

    public final int hashCode() {
        return this.zzc.hashCode();
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzc);
        String strValueOf2 = String.valueOf(this.zzd);
        String str = this.zze;
        int length = strValueOf.length();
        StringBuilder sb = new StringBuilder(length + 77 + strValueOf2.length() + String.valueOf(str).length());
        sb.append("DeviceOrientationRequestInternal{deviceOrientationRequest=");
        sb.append(strValueOf);
        sb.append(", clients=");
        sb.append(strValueOf2);
        sb.append(", tag='");
        sb.append(str);
        sb.append("'}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzc, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 3, this.zze, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
