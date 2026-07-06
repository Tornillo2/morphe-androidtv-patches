package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "ActivityRecognitionResultCreator")
@SafeParcelable.Reserved({1000})
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzk();

    @SafeParcelable.Field(id = 1)
    public List<DetectedActivity> zza;

    @SafeParcelable.Field(id = 2)
    public long zzb;

    @SafeParcelable.Field(id = 3)
    public long zzc;

    @SafeParcelable.Field(id = 4)
    public int zzd;

    @Nullable
    @SafeParcelable.Field(id = 5)
    public Bundle zze;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ActivityRecognitionResult(@NonNull @SafeParcelable.Param(id = 1) List<DetectedActivity> list, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) int i, @Nullable @SafeParcelable.Param(id = 5) Bundle bundle) {
        Preconditions.checkArgument(list != null && list.size() > 0, "Must have at least 1 detected activity");
        Preconditions.checkArgument(j > 0 && j2 > 0, "Must set times");
        this.zza = list;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = i;
        this.zze = bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0007  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.location.ActivityRecognitionResult extractResult(@androidx.annotation.NonNull android.content.Intent r3) {
        /*
            boolean r0 = hasResult(r3)
            r1 = 0
            if (r0 != 0) goto L9
        L7:
            r0 = r1
            goto L2b
        L9:
            android.os.Bundle r0 = r3.getExtras()
            if (r0 != 0) goto L10
            goto L7
        L10:
            java.lang.String r2 = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT"
            java.lang.Object r0 = r0.get(r2)
            boolean r2 = r0 instanceof byte[]
            if (r2 == 0) goto L25
            byte[] r0 = (byte[]) r0
            android.os.Parcelable$Creator<com.google.android.gms.location.ActivityRecognitionResult> r2 = com.google.android.gms.location.ActivityRecognitionResult.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromBytes(r0, r2)
            com.google.android.gms.location.ActivityRecognitionResult r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0
            goto L2b
        L25:
            boolean r2 = r0 instanceof com.google.android.gms.location.ActivityRecognitionResult
            if (r2 == 0) goto L7
            com.google.android.gms.location.ActivityRecognitionResult r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0
        L2b:
            if (r0 == 0) goto L2e
            return r0
        L2e:
            java.util.List r3 = zza(r3)
            if (r3 == 0) goto L4a
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L3d
            goto L4a
        L3d:
            int r0 = r3.size()
            int r0 = r0 + (-1)
            java.lang.Object r3 = r3.get(r0)
            com.google.android.gms.location.ActivityRecognitionResult r3 = (com.google.android.gms.location.ActivityRecognitionResult) r3
            return r3
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.extractResult(android.content.Intent):com.google.android.gms.location.ActivityRecognitionResult");
    }

    public static boolean hasResult(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List<ActivityRecognitionResult> listZza = zza(intent);
        return (listZza == null || ((ArrayList) listZza).isEmpty()) ? false : true;
    }

    @Nullable
    public static List<ActivityRecognitionResult> zza(@NonNull Intent intent) {
        if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
        }
        return null;
    }

    public static boolean zzb(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        int length;
        if (bundle == null) {
            return bundle2 == null;
        }
        if (bundle2 == null || bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (obj instanceof Bundle) {
                if (!zzb(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else {
                if (obj.getClass().isArray()) {
                    if (obj2 != null && obj2.getClass().isArray() && (length = Array.getLength(obj)) == Array.getLength(obj2)) {
                        for (int i = 0; i < length; i++) {
                            if (Objects.equal(Array.get(obj, i), Array.get(obj2, i))) {
                            }
                        }
                    }
                    return false;
                }
                if (!obj.equals(obj2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @ShowFirstParty
    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
            if (this.zzb == activityRecognitionResult.zzb && this.zzc == activityRecognitionResult.zzc && this.zzd == activityRecognitionResult.zzd && Objects.equal(this.zza, activityRecognitionResult.zza) && zzb(this.zze, activityRecognitionResult.zze)) {
                return true;
            }
        }
        return false;
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.zza) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzc;
    }

    @NonNull
    public DetectedActivity getMostProbableActivity() {
        return this.zza.get(0);
    }

    @NonNull
    public List<DetectedActivity> getProbableActivities() {
        return this.zza;
    }

    public long getTime() {
        return this.zzb;
    }

    @ShowFirstParty
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzb), Long.valueOf(this.zzc), Integer.valueOf(this.zzd), this.zza, this.zze});
    }

    @NonNull
    public String toString() {
        String strValueOf = String.valueOf(this.zza);
        long j = this.zzb;
        long j2 = this.zzc;
        StringBuilder sb = new StringBuilder(strValueOf.length() + 124);
        sb.append("ActivityRecognitionResult [probableActivities=");
        sb.append(strValueOf);
        sb.append(", timeMillis=");
        sb.append(j);
        sb.append(", elapsedRealtimeMillis=");
        sb.append(j2);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        long j = this.zzb;
        SafeParcelWriter.zzc(parcel, 2, 8);
        parcel.writeLong(j);
        long j2 = this.zzc;
        SafeParcelWriter.zzc(parcel, 3, 8);
        parcel.writeLong(j2);
        int i2 = this.zzd;
        SafeParcelWriter.zzc(parcel, 4, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeBundle(parcel, 5, this.zze, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @VisibleForTesting
    public ActivityRecognitionResult(@NonNull DetectedActivity detectedActivity, long j, long j2) {
        this(Collections.singletonList(detectedActivity), j, j2, 0, null);
    }

    public ActivityRecognitionResult(@NonNull List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, null);
    }
}
