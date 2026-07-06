package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public final class zze implements ServiceConnection {
    public final /* synthetic */ BaseGmsClient zza;
    public final int zzb;

    public zze(BaseGmsClient baseGmsClient, int i) {
        this.zza = baseGmsClient;
        this.zzb = i;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        BaseGmsClient baseGmsClient = this.zza;
        if (iBinder == null) {
            BaseGmsClient.zzk(baseGmsClient, 16);
            return;
        }
        synchronized (baseGmsClient.zzq) {
            try {
                BaseGmsClient baseGmsClient2 = this.zza;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                baseGmsClient2.zzr = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IGmsServiceBroker)) ? new zzad(iBinder) : (IGmsServiceBroker) iInterfaceQueryLocalInterface;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.zza.zzl(0, null, this.zzb);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        BaseGmsClient baseGmsClient;
        synchronized (this.zza.zzq) {
            baseGmsClient = this.zza;
            baseGmsClient.zzr = null;
        }
        int i = this.zzb;
        Handler handler = baseGmsClient.zzb;
        handler.sendMessage(handler.obtainMessage(6, i, 1));
    }
}
