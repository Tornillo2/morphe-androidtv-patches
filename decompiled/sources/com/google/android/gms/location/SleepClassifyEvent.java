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
@SafeParcelable.Class(creator = "SleepClassifyEventCreator")
public class SleepClassifyEvent extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<SleepClassifyEvent> CREATOR = new zzbu();

    @SafeParcelable.Field(getter = "getTimestampSec", id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getConfidence", id = 2)
    public final int zzb;

    @SafeParcelable.Field(getter = "getMotion", id = 3)
    public final int zzc;

    @SafeParcelable.Field(getter = "getLight", id = 4)
    public final int zzd;

    @SafeParcelable.Field(getter = "getNoise", id = 5)
    public final int zze;

    @SafeParcelable.Field(getter = "getLightDiff", id = 6)
    public final int zzf;

    @SafeParcelable.Field(getter = "getNightOrDay", id = 7)
    public final int zzg;

    @SafeParcelable.Field(getter = "getConfidenceOverwrittenByAlarmClockTrigger", id = 8)
    public final boolean zzh;

    @SafeParcelable.Field(getter = "getPresenceConfidence", id = 9)
    public final int zzi;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepClassifyEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) int i5, @SafeParcelable.Param(id = 6) int i6, @SafeParcelable.Param(id = 7) int i7, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) int i8) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = i5;
        this.zzf = i6;
        this.zzg = i7;
        this.zzh = z;
        this.zzi = i8;
    }

    @NonNull
    public static List<SleepClassifyEvent> extractEvents(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        if (!intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_CLASSIFY_RESULT")) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.internal.EXTRA_SLEEP_CLASSIFY_RESULT");
        if (arrayList == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            byte[] bArr = (byte[]) arrayList.get(i);
            Preconditions.checkNotNull(bArr);
            arrayList2.add((SleepClassifyEvent) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR));
        }
        return DesugarCollections.unmodifiableList(arrayList2);
    }

    public static boolean hasEvents(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_SLEEP_CLASSIFY_RESULT");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepClassifyEvent)) {
            return false;
        }
        SleepClassifyEvent sleepClassifyEvent = (SleepClassifyEvent) obj;
        return this.zza == sleepClassifyEvent.zza && this.zzb == sleepClassifyEvent.zzb;
    }

    public int getConfidence() {
        return this.zzb;
    }

    public int getLight() {
        return this.zzd;
    }

    public int getMotion() {
        return this.zzc;
    }

    public long getTimestampMillis() {
        return ((long) this.zza) * 1000;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Integer.valueOf(this.zzb)});
    }

    @NonNull
    public String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        int i4 = this.zzd;
        StringBuilder sb = new StringBuilder(65);
        sb.append(i);
        sb.append(" Conf:");
        sb.append(i2);
        sb.append(" Motion:");
        sb.append(i3);
        sb.append(" Light:");
        sb.append(i4);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int confidence = getConfidence();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(confidence);
        int motion = getMotion();
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(motion);
        int light = getLight();
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(light);
        int i3 = this.zze;
        SafeParcelWriter.zzc(parcel, 5, 4);
        parcel.writeInt(i3);
        int i4 = this.zzf;
        SafeParcelWriter.zzc(parcel, 6, 4);
        parcel.writeInt(i4);
        int i5 = this.zzg;
        SafeParcelWriter.zzc(parcel, 7, 4);
        parcel.writeInt(i5);
        boolean z = this.zzh;
        SafeParcelWriter.zzc(parcel, 8, 4);
        parcel.writeInt(z ? 1 : 0);
        int i6 = this.zzi;
        SafeParcelWriter.zzc(parcel, 9, 4);
        parcel.writeInt(i6);
        SafeParcelWriter.zzb(parcel, iZza);
    }
}
