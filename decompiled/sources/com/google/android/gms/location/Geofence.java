package com.google.android.gms.location;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class Builder {
        public double zze;
        public double zzf;
        public float zzg;
        public String zza = null;

        @TransitionTypes
        public int zzb = 0;
        public long zzc = Long.MIN_VALUE;
        public short zzd = -1;
        public int zzh = 0;
        public int zzi = -1;

        @NonNull
        public Geofence build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("Request ID not set.");
            }
            int i = this.zzb;
            if (i == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            }
            if ((i & 4) != 0 && this.zzi < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            }
            long j = this.zzc;
            if (j == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            }
            if (this.zzd == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            }
            int i2 = this.zzh;
            if (i2 >= 0) {
                return new com.google.android.gms.internal.location.zzbe(str, i, (short) 1, this.zze, this.zzf, this.zzg, j, i2, this.zzi);
            }
            throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
        }

        @NonNull
        public Builder setCircularRegion(@FloatRange(from = -90.0d, to = 90.0d) double d, @FloatRange(from = -180.0d, to = 180.0d) double d2, @FloatRange(from = 0.0d, fromInclusive = false) float f) {
            boolean z = d >= -90.0d && d <= 90.0d;
            StringBuilder sb = new StringBuilder(42);
            sb.append("Invalid latitude: ");
            sb.append(d);
            Preconditions.checkArgument(z, sb.toString());
            boolean z2 = d2 >= -180.0d && d2 <= 180.0d;
            StringBuilder sb2 = new StringBuilder(43);
            sb2.append("Invalid longitude: ");
            sb2.append(d2);
            Preconditions.checkArgument(z2, sb2.toString());
            boolean z3 = f > 0.0f;
            StringBuilder sb3 = new StringBuilder(31);
            sb3.append("Invalid radius: ");
            sb3.append(f);
            Preconditions.checkArgument(z3, sb3.toString());
            this.zzd = (short) 1;
            this.zze = d;
            this.zzf = d2;
            this.zzg = f;
            return this;
        }

        @NonNull
        public Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.zzc = -1L;
                return this;
            }
            this.zzc = DefaultClock.zza.elapsedRealtime() + j;
            return this;
        }

        @NonNull
        public Builder setLoiteringDelay(int i) {
            this.zzi = i;
            return this;
        }

        @NonNull
        public Builder setNotificationResponsiveness(@IntRange(from = 0) int i) {
            this.zzh = i;
            return this;
        }

        @NonNull
        public Builder setRequestId(@NonNull String str) {
            Preconditions.checkNotNull(str, "Request ID can't be set to null");
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setTransitionTypes(@TransitionTypes int i) {
            this.zzb = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public @interface GeofenceTransition {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public @interface TransitionTypes {
    }

    @NonNull
    String getRequestId();
}
