package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzr implements Handler.Callback {
    public final /* synthetic */ zzs zza;

    public /* synthetic */ zzr(zzs zzsVar, zzq zzqVar) {
        this.zza = zzsVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.zza.zzb) {
                try {
                    zzo zzoVar = (zzo) message.obj;
                    zzp zzpVar = (zzp) this.zza.zzb.get(zzoVar);
                    if (zzpVar != null && zzpVar.zzb.isEmpty()) {
                        if (zzpVar.zzd) {
                            zzpVar.zzg("GmsClientSupervisor");
                        }
                        this.zza.zzb.remove(zzoVar);
                    }
                } finally {
                }
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this.zza.zzb) {
            try {
                zzo zzoVar2 = (zzo) message.obj;
                zzp zzpVar2 = (zzp) this.zza.zzb.get(zzoVar2);
                if (zzpVar2 != null && zzpVar2.zzc == 3) {
                    Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback ".concat(String.valueOf(zzoVar2)), new Exception());
                    ComponentName componentName = zzpVar2.zzg;
                    if (componentName == null) {
                        componentName = zzoVar2.zzd;
                    }
                    if (componentName == null) {
                        String str = zzoVar2.zzc;
                        Preconditions.checkNotNull(str);
                        componentName = new ComponentName(str, EnvironmentCompat.MEDIA_UNKNOWN);
                    }
                    zzpVar2.onServiceDisconnected(componentName);
                }
            } finally {
            }
        }
        return true;
    }
}
