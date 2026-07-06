package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zacc extends zap {
    public TaskCompletionSource zad;

    public zacc(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.zad = new TaskCompletionSource();
        lifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    public static zacc zaa(@NonNull Activity activity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zacc zaccVar = (zacc) fragment.getCallbackOrNull("GmsAvailabilityHelper", zacc.class);
        if (zaccVar == null) {
            return new zacc(fragment);
        }
        if (zaccVar.zad.getTask().isComplete()) {
            zaccVar.zad = new TaskCompletionSource();
        }
        return zaccVar;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        this.zad.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zab(ConnectionResult connectionResult, int i) {
        String str = connectionResult.zzd;
        if (str == null) {
            str = "Error connecting to Google Play services";
        }
        this.zad.setException(new ApiException(new Status(connectionResult.zzb, str, connectionResult.zzc, connectionResult)));
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zac() {
        Activity lifecycleActivity = this.mLifecycleFragment.getLifecycleActivity();
        if (lifecycleActivity == null) {
            this.zad.trySetException(new ApiException(new Status(8, null, null, null)));
            return;
        }
        int iIsGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(lifecycleActivity);
        if (iIsGooglePlayServicesAvailable == 0) {
            this.zad.trySetResult(null);
        } else {
            if (this.zad.getTask().isComplete()) {
                return;
            }
            zah(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        }
    }

    public final Task zad() {
        return this.zad.getTask();
    }
}
