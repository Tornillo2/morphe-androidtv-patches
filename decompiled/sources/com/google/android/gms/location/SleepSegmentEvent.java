package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "SleepSegmentEventCreator")
@SafeParcelable.Reserved({1000})
public class SleepSegmentEvent extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<SleepSegmentEvent> CREATOR = new zzbv();
    public static final int STATUS_MISSING_DATA = 1;
    public static final int STATUS_NOT_DETECTED = 2;
    public static final int STATUS_SUCCESSFUL = 0;

    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long zza;

    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long zzb;

    @SafeParcelable.Field(getter = "getStatus", id = 3)
    public final int zzc;

    @SafeParcelable.Field(getter = "getMissingDataDurationMinutes", id = 4)
    public final int zzd;

    @SafeParcelable.Field(getter = "getNinetiethPctConfidence", id = 5)
    public final int zze;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentEvent(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) int i3) {
        Preconditions.checkArgument(j <= j2, "endTimeMillis must be greater than or equal to startTimeMillis");
        this.zza = j;
        this.zzb = j2;
        this.zzc = i;
        this.zzd = i2;
        this.zze = i3;
    }

    @NonNull
    public static List<SleepSegmentEvent> extractEvents(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        if (!intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_SEGMENT_RESULT")) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.internal.EXTRA_SLEEP_SEGMENT_RESULT");
        if (arrayList == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            byte[] bArr = (byte[]) arrayList.get(i);
            Preconditions.checkNotNull(bArr);
            arrayList2.add((SleepSegmentEvent) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR));
        }
        return DesugarCollections.unmodifiableList(arrayList2);
    }

    public static boolean hasEvents(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_SEGMENT_RESULT");
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SleepSegmentEvent)) {
            return false;
        }
        SleepSegmentEvent sleepSegmentEvent = (SleepSegmentEvent) obj;
        return this.zza == sleepSegmentEvent.getStartTimeMillis() && this.zzb == sleepSegmentEvent.getEndTimeMillis() && this.zzc == sleepSegmentEvent.getStatus() && this.zzd == sleepSegmentEvent.zzd && this.zze == sleepSegmentEvent.zze;
    }

    public long getEndTimeMillis() {
        return this.zzb;
    }

    public long getSegmentDurationMillis() {
        return this.zzb - this.zza;
    }

    public long getStartTimeMillis() {
        return this.zza;
    }

    public int getStatus() {
        return this.zzc;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zza), Long.valueOf(this.zzb), Integer.valueOf(this.zzc)});
    }

    @NonNull
    public String toString() {
        long j = this.zza;
        long j2 = this.zzb;
        int i = this.zzc;
        StringBuilder sb = new StringBuilder(84);
        sb.append("startMillis=");
        sb.append(j);
        sb.append(", endMillis=");
        sb.append(j2);
        sb.append(", status=");
        sb.append(i);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        long startTimeMillis = getStartTimeMillis();
        SafeParcelWriter.zzc(parcel, 1, 8);
        parcel.writeLong(startTimeMillis);
        long endTimeMillis = getEndTimeMillis();
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(endTimeMillis);
        int status = getStatus();
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(status);
        int i2 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i2);
        int i3 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
