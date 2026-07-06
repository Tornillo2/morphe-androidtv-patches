package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "SleepSegmentRequestCreator")
@SafeParcelable.Reserved({1000})
public class SleepSegmentRequest extends AbstractSafeParcelable {
    public static final int CLASSIFY_EVENTS_ONLY = 2;

    @NonNull
    public static final Parcelable.Creator<SleepSegmentRequest> CREATOR = new zzbw();
    public static final int SEGMENT_AND_CLASSIFY_EVENTS = 0;
    public static final int SEGMENT_EVENTS_ONLY = 1;

    @Nullable
    @SafeParcelable.Field(getter = "getUserPreferredSleepWindow", id = 1)
    public final List<zzbx> zza;

    @SafeParcelable.Field(defaultValue = "0", getter = "getRequestedDataType", id = 2)
    public final int zzb;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentRequest(@Nullable @SafeParcelable.Param(id = 1) List<zzbx> list, @SafeParcelable.Param(id = 2) int i) {
        this.zza = list;
        this.zzb = i;
    }

    @NonNull
    public static SleepSegmentRequest getDefaultSleepSegmentRequest() {
        return new SleepSegmentRequest(null, 0);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepSegmentRequest)) {
            return false;
        }
        SleepSegmentRequest sleepSegmentRequest = (SleepSegmentRequest) obj;
        return Objects.equal(this.zza, sleepSegmentRequest.zza) && this.zzb == sleepSegmentRequest.zzb;
    }

    public int getRequestedDataType() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        int requestedDataType = getRequestedDataType();
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(requestedDataType);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public SleepSegmentRequest(int i) {
        this(null, i);
    }
}
