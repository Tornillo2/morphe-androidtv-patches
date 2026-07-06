package com.google.android.gms.location;

import android.app.PendingIntent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface GeofencingApi {
    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    PendingResult<Status> addGeofences(@NonNull GoogleApiClient googleApiClient, @NonNull GeofencingRequest geofencingRequest, @NonNull PendingIntent pendingIntent);

    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @Deprecated
    PendingResult<Status> addGeofences(@NonNull GoogleApiClient googleApiClient, @NonNull List<Geofence> list, @NonNull PendingIntent pendingIntent);

    @NonNull
    PendingResult<Status> removeGeofences(@NonNull GoogleApiClient googleApiClient, @NonNull PendingIntent pendingIntent);

    @NonNull
    PendingResult<Status> removeGeofences(@NonNull GoogleApiClient googleApiClient, @NonNull List<String> list);
}
