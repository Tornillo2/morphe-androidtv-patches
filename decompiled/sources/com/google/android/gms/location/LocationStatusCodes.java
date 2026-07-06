package com.google.android.gms.location;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class LocationStatusCodes {
    public static final int ERROR = 1;
    public static final int GEOFENCE_NOT_AVAILABLE = 1000;
    public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
    public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
    public static final int SUCCESS = 0;

    public static int zza(int i) {
        if ((i < 0 || i > 1) && (i < 1000 || i >= 1006)) {
            return 1;
        }
        return i;
    }

    @NonNull
    public static Status zzb(int i) {
        if (i == 1) {
            i = 13;
        }
        return new Status(i, null, null, null);
    }
}
