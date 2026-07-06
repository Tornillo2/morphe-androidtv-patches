package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api zaa;
    public final boolean zab;

    @Nullable
    public zau zac;

    public zat(Api api, boolean z) {
        this.zaa = api;
        this.zab = z;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        zab().onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zab().zaa(connectionResult, this.zaa, this.zab);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        zab().onConnectionSuspended(i);
    }

    public final void zaa(zau zauVar) {
        this.zac = zauVar;
    }

    public final zau zab() {
        Preconditions.checkNotNull(this.zac, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.zac;
    }
}
