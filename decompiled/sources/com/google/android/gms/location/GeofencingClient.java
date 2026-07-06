package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class GeofencingClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public GeofencingClient(@NonNull Activity activity) {
        super(activity, activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Task<Void> addGeofences(@NonNull GeofencingRequest geofencingRequest, @NonNull final PendingIntent pendingIntent) {
        final GeofencingRequest geofencingRequestZza = geofencingRequest.zza(getContextAttributionTag());
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(geofencingRequestZza, pendingIntent) { // from class: com.google.android.gms.location.zzaq
            public final GeofencingRequest zza;
            public final PendingIntent zzb;

            {
                this.zza = geofencingRequestZza;
                this.zzb = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzv(this.zza, this.zzb, new zzat((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2424;
        return doWrite(builder.build());
    }

    @NonNull
    public Task<Void> removeGeofences(@NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(pendingIntent) { // from class: com.google.android.gms.location.zzar
            public final PendingIntent zza;

            {
                this.zza = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzx(this.zza, new zzat((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2425;
        return doWrite(builder.build());
    }

    public GeofencingClient(@NonNull Context context) {
        super(context, (Activity) null, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<Void> removeGeofences(@NonNull final List<String> list) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(list) { // from class: com.google.android.gms.location.zzas
            public final List zza;

            {
                this.zza = list;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzy(this.zza, new zzat((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2425;
        return doWrite(builder.build());
    }
}
