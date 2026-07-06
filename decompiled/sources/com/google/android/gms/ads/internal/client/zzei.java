package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.internal.ads.zzbkk;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzei extends zzbkk {
    public final /* synthetic */ zzej zza;

    @Override // com.google.android.gms.internal.ads.zzbkl
    public final void zzb(List list) throws RemoteException {
        int i;
        ArrayList arrayList;
        synchronized (this.zza.zzb) {
            zzej zzejVar = this.zza;
            zzejVar.zzd = false;
            zzejVar.zze = true;
            arrayList = new ArrayList(this.zza.zzc);
            this.zza.zzc.clear();
        }
        InitializationStatus initializationStatusZzy = zzej.zzy(list);
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            ((OnInitializationCompleteListener) arrayList.get(i)).onInitializationComplete(initializationStatusZzy);
        }
    }
}
