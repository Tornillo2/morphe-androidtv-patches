package com.google.android.gms.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Response;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LocationSettingsResponse extends Response<LocationSettingsResult> {
    public LocationSettingsResponse() {
    }

    @Nullable
    public LocationSettingsStates getLocationSettingsStates() {
        return getResult().zzb;
    }

    public LocationSettingsResponse(@NonNull LocationSettingsResult locationSettingsResult) {
        super(locationSettingsResult);
    }
}
