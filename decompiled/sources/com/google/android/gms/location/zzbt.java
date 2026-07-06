package com.google.android.gms.location;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbt implements BaseImplementation.ResultHolder<LocationSettingsResult> {
    public final TaskCompletionSource<LocationSettingsResponse> zza;

    public zzbt(TaskCompletionSource<LocationSettingsResponse> taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final void setFailedResult(Status status) {
        this.zza.setException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final void setResult(LocationSettingsResult locationSettingsResult) {
        LocationSettingsResult locationSettingsResult2 = locationSettingsResult;
        Status status = locationSettingsResult2.zza;
        if (status.isSuccess()) {
            this.zza.setResult(new LocationSettingsResponse(locationSettingsResult2));
        } else if (status.hasResolution()) {
            this.zza.setException(new ResolvableApiException(status));
        } else {
            this.zza.setException(new ApiException(status));
        }
    }
}
