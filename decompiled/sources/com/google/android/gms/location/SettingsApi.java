package com.google.android.gms.location;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface SettingsApi {
    @NonNull
    PendingResult<LocationSettingsResult> checkLocationSettings(@NonNull GoogleApiClient googleApiClient, @NonNull LocationSettingsRequest locationSettingsRequest);
}
