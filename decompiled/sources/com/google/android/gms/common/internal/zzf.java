package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzf extends zza {

    @Nullable
    public final IBinder zze;
    public final /* synthetic */ BaseGmsClient zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzf(BaseGmsClient baseGmsClient, @Nullable int i, @Nullable IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i, bundle);
        this.zzf = baseGmsClient;
        this.zze = iBinder;
    }

    @Override // com.google.android.gms.common.internal.zza
    public final void zzb(ConnectionResult connectionResult) {
        BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener = this.zzf.zzx;
        if (baseOnConnectionFailedListener != null) {
            baseOnConnectionFailedListener.onConnectionFailed(connectionResult);
        }
        this.zzf.onConnectionFailed(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.zza
    public final boolean zzd() {
        try {
            IBinder iBinder = this.zze;
            Preconditions.checkNotNull(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if (!this.zzf.getServiceDescriptor().equals(interfaceDescriptor)) {
                Log.w("GmsClient", "service descriptor mismatch: " + this.zzf.getServiceDescriptor() + " vs. " + interfaceDescriptor);
                return false;
            }
            IInterface iInterfaceCreateServiceInterface = this.zzf.createServiceInterface(this.zze);
            if (iInterfaceCreateServiceInterface == null || !(BaseGmsClient.zzn(this.zzf, 2, 4, iInterfaceCreateServiceInterface) || BaseGmsClient.zzn(this.zzf, 3, 4, iInterfaceCreateServiceInterface))) {
                return false;
            }
            BaseGmsClient baseGmsClient = this.zzf;
            baseGmsClient.zzB = null;
            BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks = baseGmsClient.zzw;
            if (baseConnectionCallbacks == null) {
                return true;
            }
            baseConnectionCallbacks.onConnected(null);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
