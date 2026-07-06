package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public ActivityRecognitionClient(@NonNull Activity activity) {
        super(activity, activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityTransitionUpdates(@NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(pendingIntent) { // from class: com.google.android.gms.location.zzg
            public final PendingIntent zza;

            {
                this.zza = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzs(this.zza, new zzj((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2406;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityUpdates(@NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(pendingIntent) { // from class: com.google.android.gms.location.zze
            public final PendingIntent zza;

            {
                this.zza = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzt(this.zza);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        builder.zad = 2402;
        return doWrite(builder.build());
    }

    @NonNull
    public Task<Void> removeSleepSegmentUpdates(@NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(pendingIntent) { // from class: com.google.android.gms.location.zzh
            public final PendingIntent zza;

            {
                this.zza = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzu(this.zza, new zzj((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2411;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityTransitionUpdates(@NonNull final ActivityTransitionRequest activityTransitionRequest, @NonNull final PendingIntent pendingIntent) {
        activityTransitionRequest.zzd = getContextAttributionTag();
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(activityTransitionRequest, pendingIntent) { // from class: com.google.android.gms.location.zzf
            public final ActivityTransitionRequest zza;
            public final PendingIntent zzb;

            {
                this.zza = activityTransitionRequest;
                this.zzb = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzr(this.zza, this.zzb, new zzj((TaskCompletionSource) obj2));
            }
        };
        builder.zad = 2405;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityUpdates(final long j, @NonNull final PendingIntent pendingIntent) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(j, pendingIntent) { // from class: com.google.android.gms.location.zzc
            public final long zza;
            public final PendingIntent zzb;

            {
                this.zza = j;
                this.zzb = pendingIntent;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.location.zzaz) obj).zzq(this.zza, this.zzb);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        builder.zad = 2401;
        return doWrite(builder.build());
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestSleepSegmentUpdates(@NonNull final PendingIntent pendingIntent, @NonNull final SleepSegmentRequest sleepSegmentRequest) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zaa = new RemoteCall(this, pendingIntent, sleepSegmentRequest) { // from class: com.google.android.gms.location.zzd
            public final ActivityRecognitionClient zza;
            public final PendingIntent zzb;
            public final SleepSegmentRequest zzc;

            {
                this.zza = this;
                this.zzb = pendingIntent;
                this.zzc = sleepSegmentRequest;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ActivityRecognitionClient activityRecognitionClient = this.zza;
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzaz) obj).getService()).zzv(this.zzb, this.zzc, new zzi(activityRecognitionClient, (TaskCompletionSource) obj2));
            }
        };
        builder.zac = new Feature[]{zzu.zzb};
        builder.zad = 2410;
        return doRead(builder.build());
    }

    public ActivityRecognitionClient(@NonNull Context context) {
        super(context, (Activity) null, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
