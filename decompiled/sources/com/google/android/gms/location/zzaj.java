package com.google.android.gms.location;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaj extends LocationCallback {
    public final /* synthetic */ TaskCompletionSource zza;
    public final /* synthetic */ FusedLocationProviderClient zzb;

    public zzaj(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        this.zzb = fusedLocationProviderClient;
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationResult(LocationResult locationResult) {
        this.zza.trySetResult(locationResult.getLastLocation());
        this.zzb.removeLocationUpdates(this);
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationAvailability(LocationAvailability locationAvailability) {
    }
}
