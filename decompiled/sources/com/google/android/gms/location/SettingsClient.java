package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SettingsClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public SettingsClient(@NonNull Activity activity) {
        super(activity, activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<LocationSettingsResponse> checkLocationSettings(@NonNull final LocationSettingsRequest locationSettingsRequest) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(locationSettingsRequest) { // from class: com.google.android.gms.location.zzbs
            public final LocationSettingsRequest zza;

            {
                this.zza = locationSettingsRequest;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzL(this.zza, new zzbt((TaskCompletionSource) obj2), null);
            }
        };
        builder.zad = 2426;
        return doRead(builder.build());
    }

    public SettingsClient(@NonNull Context context) {
        super(context, (Activity) null, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
