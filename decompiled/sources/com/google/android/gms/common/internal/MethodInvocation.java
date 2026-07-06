package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.errorprone.annotations.InlineMe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "MethodInvocationCreator")
public class MethodInvocation extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new zan();

    @SafeParcelable.Field(getter = "getMethodKey", id = 1)
    public final int zaa;

    @SafeParcelable.Field(getter = "getResultStatusCode", id = 2)
    public final int zab;

    @SafeParcelable.Field(getter = "getConnectionResultStatusCode", id = 3)
    public final int zac;

    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 4)
    public final long zad;

    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 5)
    public final long zae;

    @Nullable
    @SafeParcelable.Field(getter = "getCallingModuleId", id = 6)
    public final String zaf;

    @Nullable
    @SafeParcelable.Field(getter = "getCallingEntryPoint", id = 7)
    public final String zag;

    @SafeParcelable.Field(defaultValue = "0", getter = "getServiceId", id = 8)
    public final int zah;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getLatencyMillis", id = 9)
    public final int zai;

    @SafeParcelable.Constructor
    public MethodInvocation(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) long j, @SafeParcelable.Param(id = 5) long j2, @Nullable @SafeParcelable.Param(id = 6) String str, @Nullable @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) int i4, @SafeParcelable.Param(id = 9) int i5) {
        this.zaa = i;
        this.zab = i2;
        this.zac = i3;
        this.zad = j;
        this.zae = j2;
        this.zaf = str;
        this.zag = str2;
        this.zah = i4;
        this.zai = i5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int i2 = this.zaa;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zab;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        int i4 = this.zac;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i4);
        long j = this.zad;
        SafeParcelWriter.zzc(parcel, 4, 8);
        parcel.writeLong(j);
        long j2 = this.zae;
        SafeParcelWriter.zzc(parcel, 5, 8);
        parcel.writeLong(j2);
        SafeParcelWriter.writeString(parcel, 6, this.zaf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zag, false);
        int i5 = this.zah;
        SafeParcelWriter.zzc(parcel, 8, 4);
        parcel.writeInt(i5);
        int i6 = this.zai;
        SafeParcelWriter.zzc(parcel, 9, 4);
        parcel.writeInt(i6);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @InlineMe(replacement = "this(methodKey, resultStatusCode, connectionResultStatusCode, startTimeMillis, endTimeMillis, callingModuleId, callingEntryPoint, serviceId, -1)")
    @KeepForSdk
    @Deprecated
    public MethodInvocation(int i, int i2, int i3, long j, long j2, @Nullable String str, @Nullable String str2, int i4) {
        this(i, i2, i3, j, j2, str, str2, i4, -1);
    }
}
