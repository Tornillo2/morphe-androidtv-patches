package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {

    @SafeParcelable.Field(getter = "getStatusCode", id = 1)
    public final int zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getStatusMessage", id = 2)
    public final String zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    public final PendingIntent zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getConnectionResult", id = 4)
    public final ConnectionResult zze;

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS_CACHE = new Status(-1, null, null, null);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS = new Status(0, null, null, null);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED = new Status(14, null, null, null);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR = new Status(8, null, null, null);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_TIMEOUT = new Status(15, null, null, null);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_CANCELED = new Status(16, null, null, null);

    @NonNull
    @ShowFirstParty
    public static final Status zza = new Status(17, null, null, null);

    @NonNull
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT = new Status(18, null, null, null);

    @NonNull
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    @SafeParcelable.Constructor
    public Status(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 4) ConnectionResult connectionResult) {
        this.zzb = i;
        this.zzc = str;
        this.zzd = pendingIntent;
        this.zze = connectionResult;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzb == status.zzb && Objects.equal(this.zzc, status.zzc) && Objects.equal(this.zzd, status.zzd) && Objects.equal(this.zze, status.zze);
    }

    @Nullable
    public ConnectionResult getConnectionResult() {
        return this.zze;
    }

    @Nullable
    public PendingIntent getResolution() {
        return this.zzd;
    }

    @ResultIgnorabilityUnspecified
    public int getStatusCode() {
        return this.zzb;
    }

    @Nullable
    public String getStatusMessage() {
        return this.zzc;
    }

    public boolean hasResolution() {
        return this.zzd != null;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze});
    }

    public boolean isCanceled() {
        return this.zzb == 16;
    }

    public boolean isInterrupted() {
        return this.zzb == 14;
    }

    @CheckReturnValue
    public boolean isSuccess() {
        return this.zzb <= 0;
    }

    public void startResolutionForResult(@NonNull Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            PendingIntent pendingIntent = this.zzd;
            Preconditions.checkNotNull(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    @NonNull
    public String toString() {
        Objects.ToStringHelper toStringHelper = new Objects.ToStringHelper(this, null);
        toStringHelper.add("statusCode", zza());
        toStringHelper.add("resolution", this.zzd);
        return toStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zzb;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zze, i, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @NonNull
    public final String zza() {
        String str = this.zzc;
        return str != null ? str : CommonStatusCodes.getStatusCodeString(this.zzb);
    }

    @KeepForSdk
    @Deprecated
    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str, int i) {
        this(i, str, connectionResult.zzc, connectionResult);
    }

    public void startResolutionForResult(@NonNull ActivityResultLauncher<IntentSenderRequest> activityResultLauncher) {
        if (hasResolution()) {
            PendingIntent pendingIntent = this.zzd;
            Preconditions.checkNotNull(pendingIntent);
            activityResultLauncher.launch(new IntentSenderRequest.Builder(pendingIntent.getIntentSender()).build());
        }
    }

    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str) {
        this(connectionResult, str, 17);
    }

    public Status(int i) {
        this(i, null, null, null);
    }

    public Status(int i, @Nullable String str) {
        this(i, str, null, null);
    }

    public Status(int i, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this(i, str, pendingIntent, null);
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    @CanIgnoreReturnValue
    public Status getStatus() {
        return this;
    }
}
