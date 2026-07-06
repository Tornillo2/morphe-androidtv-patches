package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationStatusCodes;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzax extends zzaj {
    public BaseImplementation.ResultHolder<Status> zza;

    public zzax(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zza = resultHolder;
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzb(int i, String[] strArr) {
        Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult", new Exception());
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzc(int i, String[] strArr) {
        zze(i);
    }

    @Override // com.google.android.gms.internal.location.zzak
    public final void zzd(int i, PendingIntent pendingIntent) {
        zze(i);
    }

    public final void zze(int i) {
        if (this.zza == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times", new Exception());
            return;
        }
        this.zza.setResult(LocationStatusCodes.zzb(LocationStatusCodes.zza(i)));
        this.zza = null;
    }
}
