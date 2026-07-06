package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class FusedLocationProviderClient extends GoogleApi<Api.ApiOptions.NoOptions> {

    @NonNull
    public static final String KEY_MOCK_LOCATION = "mockLocation";

    @NonNull
    public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";

    @VisibleForTesting(otherwise = 3)
    public FusedLocationProviderClient(@NonNull Activity activity) {
        super(activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    @NonNull
    public Task<Void> flushLocations() {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = zzw.zza;
        builder.zad = 2422;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Location> getCurrentLocation(int i, @NonNull final CancellationToken cancellationToken) {
        LocationRequest locationRequestCreate = LocationRequest.create();
        locationRequestCreate.setPriority(i);
        locationRequestCreate.setInterval(0L);
        locationRequestCreate.setFastestInterval(0L);
        locationRequestCreate.setExpirationDuration(30000L);
        final com.google.android.gms.internal.location.zzba zzbaVarZza = com.google.android.gms.internal.location.zzba.zza(null, locationRequestCreate);
        zzbaVarZza.zzj = true;
        zzbaVarZza.zzb(10000L);
        RemoteCall remoteCall = new RemoteCall(this, cancellationToken, zzbaVarZza) { // from class: com.google.android.gms.location.zzab
            public final FusedLocationProviderClient zza;
            public final CancellationToken zzb;
            public final com.google.android.gms.internal.location.zzba zzc;

            {
                this.zza = this;
                this.zzb = cancellationToken;
                this.zzc = zzbaVarZza;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzc(this.zzb, this.zzc, (com.google.android.gms.internal.location.zzaz) obj, (TaskCompletionSource) obj2);
            }
        };
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = remoteCall;
        builder.zac = new Feature[]{zzu.zzd};
        builder.zad = 2415;
        Task taskDoRead = doRead(builder.build());
        if (cancellationToken == null) {
            return taskDoRead;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        taskDoRead.continueWithTask(new Continuation(taskCompletionSource) { // from class: com.google.android.gms.location.zzac
            public final TaskCompletionSource zza;

            {
                this.zza = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                TaskCompletionSource taskCompletionSource2 = this.zza;
                if (task.isSuccessful()) {
                    taskCompletionSource2.trySetResult((Location) task.getResult());
                } else {
                    Exception exception = task.getException();
                    if (exception != null) {
                        taskCompletionSource2.setException(exception);
                    }
                }
                return taskCompletionSource2.getTask();
            }
        });
        return taskCompletionSource.zza;
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Location> getLastLocation() {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(this) { // from class: com.google.android.gms.location.zzv
            public final FusedLocationProviderClient zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzd((com.google.android.gms.internal.location.zzaz) obj, (TaskCompletionSource) obj2);
            }
        };
        builder.zad = 2414;
        return doRead(builder.build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<LocationAvailability> getLocationAvailability() {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = zzad.zza;
        builder.zad = 2416;
        return doRead(builder.build());
    }

    @NonNull
    public Task<Void> removeLocationUpdates(@NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(pendingIntent) { // from class: com.google.android.gms.location.zzag
            public final PendingIntent zza;

            {
                this.zza = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzG(this.zza, new zzao((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2418;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull final PendingIntent pendingIntent) {
        final com.google.android.gms.internal.location.zzba zzbaVarZza = com.google.android.gms.internal.location.zzba.zza(null, locationRequest);
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(this, zzbaVarZza, pendingIntent) { // from class: com.google.android.gms.location.zzaf
            public final FusedLocationProviderClient zza;
            public final com.google.android.gms.internal.location.zzba zzb;
            public final PendingIntent zzc;

            {
                this.zza = this;
                this.zzb = zzbaVarZza;
                this.zzc = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zza(this.zzb, this.zzc, (com.google.android.gms.internal.location.zzaz) obj, (TaskCompletionSource) obj2);
            }
        };
        builder.zad = 2417;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Void> setMockLocation(@NonNull final Location location) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(location) { // from class: com.google.android.gms.location.zzai
            public final Location zza;

            {
                this.zza = location;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzJ(this.zza);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        builder.zad = 2421;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Void> setMockMode(final boolean z) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(z) { // from class: com.google.android.gms.location.zzah
            public final boolean zza;

            {
                this.zza = z;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzI(this.zza);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        builder.zad = 2420;
        return doWrite(builder.build());
    }

    public final void zza(com.google.android.gms.internal.location.zzba zzbaVar, PendingIntent pendingIntent, com.google.android.gms.internal.location.zzaz zzazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzao zzaoVar = new zzao(taskCompletionSource);
        zzbaVar.zzk = getContextAttributionTag();
        zzazVar.zzD(zzbaVar, pendingIntent, zzaoVar);
    }

    public final void zzb(final zzap zzapVar, final LocationCallback locationCallback, final zzan zzanVar, com.google.android.gms.internal.location.zzba zzbaVar, ListenerHolder listenerHolder, com.google.android.gms.internal.location.zzaz zzazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzam zzamVar = new zzam(taskCompletionSource, new zzan(this, zzapVar, locationCallback, zzanVar) { // from class: com.google.android.gms.location.zzx
            public final FusedLocationProviderClient zza;
            public final zzap zzb;
            public final LocationCallback zzc;
            public final zzan zzd;

            {
                this.zza = this;
                this.zzb = zzapVar;
                this.zzc = locationCallback;
                this.zzd = zzanVar;
            }

            @Override // com.google.android.gms.location.zzan
            public final void zza() {
                FusedLocationProviderClient fusedLocationProviderClient = this.zza;
                zzap zzapVar2 = this.zzb;
                LocationCallback locationCallback2 = this.zzc;
                zzan zzanVar2 = this.zzd;
                zzapVar2.zza = false;
                fusedLocationProviderClient.removeLocationUpdates(locationCallback2);
                if (zzanVar2 != null) {
                    zzanVar2.zza();
                }
            }
        });
        zzbaVar.zzk = getContextAttributionTag();
        zzazVar.zzB(zzbaVar, listenerHolder, zzamVar);
    }

    public final /* synthetic */ void zzc(CancellationToken cancellationToken, com.google.android.gms.internal.location.zzba zzbaVar, com.google.android.gms.internal.location.zzaz zzazVar, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        final zzaj zzajVar = new zzaj(this, taskCompletionSource);
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(new OnTokenCanceledListener(this, zzajVar) { // from class: com.google.android.gms.location.zzy
                public final FusedLocationProviderClient zza;
                public final LocationCallback zzb;

                {
                    this.zza = this;
                    this.zzb = zzajVar;
                }

                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    this.zza.removeLocationUpdates(this.zzb);
                }
            });
        }
        zze(zzbaVar, zzajVar, Looper.getMainLooper(), new zzan(taskCompletionSource) { // from class: com.google.android.gms.location.zzz
            public final TaskCompletionSource zza;

            {
                this.zza = taskCompletionSource;
            }

            @Override // com.google.android.gms.location.zzan
            public final void zza() {
                this.zza.trySetResult(null);
            }
        }, 2437).continueWithTask(new Continuation(taskCompletionSource) { // from class: com.google.android.gms.location.zzaa
            public final TaskCompletionSource zza;

            {
                this.zza = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                TaskCompletionSource taskCompletionSource2 = this.zza;
                if (!task.isSuccessful()) {
                    if (task.getException() != null) {
                        Exception exception = task.getException();
                        if (exception != null) {
                            taskCompletionSource2.setException(exception);
                        }
                    } else {
                        taskCompletionSource2.trySetResult(null);
                    }
                }
                return taskCompletionSource2.getTask();
            }
        });
    }

    public final /* synthetic */ void zzd(com.google.android.gms.internal.location.zzaz zzazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzazVar.zzz(getContextAttributionTag()));
    }

    public final Task<Void> zze(final com.google.android.gms.internal.location.zzba zzbaVar, final LocationCallback locationCallback, Looper looper, final zzan zzanVar, int i) {
        if (looper == null) {
            looper = com.google.android.gms.internal.location.zzbj.zzb();
        }
        final ListenerHolder listenerHolderCreateListenerHolder = ListenerHolders.createListenerHolder(locationCallback, looper, "LocationCallback");
        final zzak zzakVar = new zzak(this, listenerHolderCreateListenerHolder);
        RemoteCall remoteCall = new RemoteCall(this, zzakVar, locationCallback, zzanVar, zzbaVar, listenerHolderCreateListenerHolder) { // from class: com.google.android.gms.location.zzae
            public final FusedLocationProviderClient zza;
            public final zzap zzb;
            public final LocationCallback zzc;
            public final zzan zzd;
            public final com.google.android.gms.internal.location.zzba zze;
            public final ListenerHolder zzf;

            {
                this.zza = this;
                this.zzb = zzakVar;
                this.zzc = locationCallback;
                this.zzd = zzanVar;
                this.zze = zzbaVar;
                this.zzf = listenerHolderCreateListenerHolder;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzb(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, (com.google.android.gms.internal.location.zzaz) obj, (TaskCompletionSource) obj2);
            }
        };
        RegistrationMethods.Builder builder = RegistrationMethods.builder();
        builder.zaa = remoteCall;
        builder.zab = zzakVar;
        builder.zad = listenerHolderCreateListenerHolder;
        builder.zag = i;
        return doRegisterEventListener(builder.build());
    }

    @VisibleForTesting(otherwise = 3)
    public FusedLocationProviderClient(@NonNull Context context) {
        super(context, LocationServices.API, Api.ApiOptions.NO_OPTIONS, new ApiExceptionMapper());
    }

    @NonNull
    public Task<Void> removeLocationUpdates(@NonNull LocationCallback locationCallback) {
        return TaskUtil.toVoidTaskThatFailsOnFalse(doUnregisterEventListener(ListenerHolders.createListenerKey(locationCallback, "LocationCallback")));
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull LocationCallback locationCallback, @NonNull Looper looper) {
        return zze(com.google.android.gms.internal.location.zzba.zza(null, locationRequest), locationCallback, looper, null, 2436);
    }
}
