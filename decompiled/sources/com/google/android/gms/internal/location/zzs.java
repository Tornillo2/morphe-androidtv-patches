package com.google.android.gms.internal.location;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzs extends zzx {
    public final /* synthetic */ LocationRequest zza;
    public final /* synthetic */ LocationListener zzb;
    public final /* synthetic */ Looper zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzz zzzVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        super(googleApiClient);
        this.zza = locationRequest;
        this.zzb = locationListener;
        this.zzc = looper;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaz zzazVar = (zzaz) anyClient;
        zzy zzyVar = new zzy(this);
        LocationRequest locationRequest = this.zza;
        LocationListener locationListener = this.zzb;
        Looper looperZzb = this.zzc;
        if (looperZzb == null) {
            looperZzb = zzbj.zzb();
        }
        zzazVar.zzC(locationRequest, ListenerHolders.createListenerHolder(locationListener, looperZzb, "LocationListener"), zzyVar);
    }
}
