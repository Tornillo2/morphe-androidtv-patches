package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.EnvironmentCompat;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getTag", id = 3)
    public final String tag;

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(getter = "getPlaceId", id = 2)
    public final String zza;

    @SafeParcelable.Field(getter = "getSource", id = 4)
    public final String zzb;

    @SafeParcelable.Constructor
    public PlaceReport(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3) {
        this.versionCode = i;
        this.zza = str;
        this.tag = str2;
        this.zzb = str3;
    }

    @VisibleForTesting
    public static PlaceReport create(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(EnvironmentCompat.MEDIA_UNKNOWN);
        return new PlaceReport(1, str, str2, EnvironmentCompat.MEDIA_UNKNOWN);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return Objects.equal(this.zza, placeReport.zza) && Objects.equal(this.tag, placeReport.tag) && Objects.equal(this.zzb, placeReport.zzb);
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.tag, this.zzb});
    }

    public String toString() {
        Objects.ToStringHelper toStringHelper = new Objects.ToStringHelper(this, null);
        toStringHelper.add("placeId", this.zza);
        toStringHelper.add("tag", this.tag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.zzb)) {
            toStringHelper.add(GlideExecutor.DEFAULT_SOURCE_EXECUTOR_NAME, this.zzb);
        }
        return toStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.versionCode;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, getPlaceId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTag(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzb, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
