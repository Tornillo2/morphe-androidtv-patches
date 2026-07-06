package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "ActivityTransitionEventCreator")
@SafeParcelable.Reserved({1000})
public class ActivityTransitionEvent extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ActivityTransitionEvent> CREATOR = new zzm();

    @SafeParcelable.Field(getter = "getActivityType", id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getTransitionType", id = 2)
    public final int zzb;

    @SafeParcelable.Field(getter = "getElapsedRealTimeNanos", id = 3)
    public final long zzc;

    @SafeParcelable.Constructor
    public ActivityTransitionEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) long j) {
        ActivityTransition.zza(i2);
        this.zza = i;
        this.zzb = i2;
        this.zzc = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransitionEvent)) {
            return false;
        }
        ActivityTransitionEvent activityTransitionEvent = (ActivityTransitionEvent) obj;
        return this.zza == activityTransitionEvent.zza && this.zzb == activityTransitionEvent.zzb && this.zzc == activityTransitionEvent.zzc;
    }

    public int getActivityType() {
        return this.zza;
    }

    public long getElapsedRealTimeNanos() {
        return this.zzc;
    }

    public int getTransitionType() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Long.valueOf(this.zzc)});
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.zza;
        StringBuilder sb2 = new StringBuilder(24);
        sb2.append("ActivityType ");
        sb2.append(i);
        sb.append(sb2.toString());
        sb.append(StringUtils.SPACE);
        int i2 = this.zzb;
        StringBuilder sb3 = new StringBuilder(26);
        sb3.append("TransitionType ");
        sb3.append(i2);
        sb.append(sb3.toString());
        sb.append(StringUtils.SPACE);
        long j = this.zzc;
        StringBuilder sb4 = new StringBuilder(41);
        sb4.append("ElapsedRealTimeNanos ");
        sb4.append(j);
        sb.append(sb4.toString());
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int activityType = getActivityType();
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(activityType);
        int transitionType = getTransitionType();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(transitionType);
        long elapsedRealTimeNanos = getElapsedRealTimeNanos();
        SafeParcelWriter.zzc(parcel, 3, 8);
        parcel.writeLong(elapsedRealTimeNanos);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
