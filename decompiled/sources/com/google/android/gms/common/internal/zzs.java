package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzs extends GmsClientSupervisor {

    @GuardedBy("connectionStatus")
    public final HashMap zzb = new HashMap();
    public final Context zzc;
    public volatile Handler zzd;
    public final zzr zze;
    public final ConnectionTracker zzf;
    public final long zzg;
    public final long zzh;

    @Nullable
    public volatile Executor zzi;

    public zzs(Context context, Looper looper, @Nullable Executor executor) {
        zzr zzrVar = new zzr(this, null);
        this.zze = zzrVar;
        this.zzc = context.getApplicationContext();
        this.zzd = new com.google.android.gms.internal.common.zzi(looper, zzrVar);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = 5000L;
        this.zzh = 300000L;
        this.zzi = executor;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final void zza(zzo zzoVar, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzb) {
            try {
                zzp zzpVar = (zzp) this.zzb.get(zzoVar);
                if (zzpVar == null) {
                    throw new IllegalStateException("Nonexistent connection status for service config: " + zzoVar.toString());
                }
                if (!zzpVar.zzb.containsKey(serviceConnection)) {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + zzoVar.toString());
                }
                zzpVar.zzf(serviceConnection, str);
                if (zzpVar.zzb.isEmpty()) {
                    this.zzd.sendMessageDelayed(this.zzd.obtainMessage(0, zzoVar), this.zzg);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final boolean zzc(zzo zzoVar, ServiceConnection serviceConnection, String str, @Nullable Executor executor) {
        boolean z;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzb) {
            try {
                zzp zzpVar = (zzp) this.zzb.get(zzoVar);
                if (executor == null) {
                    executor = this.zzi;
                }
                if (zzpVar == null) {
                    zzpVar = new zzp(this, zzoVar);
                    zzpVar.zzd(serviceConnection, serviceConnection, str);
                    zzpVar.zze(str, executor);
                    this.zzb.put(zzoVar, zzpVar);
                } else {
                    this.zzd.removeMessages(0, zzoVar);
                    if (zzpVar.zzb.containsKey(serviceConnection)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zzoVar.toString());
                    }
                    zzpVar.zzd(serviceConnection, serviceConnection, str);
                    int i = zzpVar.zzc;
                    if (i == 1) {
                        serviceConnection.onServiceConnected(zzpVar.zzg, zzpVar.zze);
                    } else if (i == 2) {
                        zzpVar.zze(str, executor);
                    }
                }
                z = zzpVar.zzd;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public final void zzi(@Nullable Executor executor) {
        synchronized (this.zzb) {
            this.zzi = executor;
        }
    }

    public final void zzj(Looper looper) {
        synchronized (this.zzb) {
            this.zzd = new com.google.android.gms.internal.common.zzi(looper, this.zze);
        }
    }
}
