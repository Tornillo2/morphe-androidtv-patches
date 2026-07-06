package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zao implements Runnable {
    public final /* synthetic */ zap zaa;
    public final zam zab;

    public zao(zap zapVar, zam zamVar) {
        this.zaa = zapVar;
        this.zab = zamVar;
    }

    @Override // java.lang.Runnable
    @MainThread
    public final void run() {
        if (this.zaa.zaa) {
            ConnectionResult connectionResult = this.zab.zab;
            if (connectionResult.hasResolution()) {
                zap zapVar = this.zaa;
                LifecycleFragment lifecycleFragment = zapVar.mLifecycleFragment;
                Activity activity = zapVar.getActivity();
                PendingIntent pendingIntent = connectionResult.zzc;
                Preconditions.checkNotNull(pendingIntent);
                lifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(activity, pendingIntent, this.zab.zaa, false), 1);
                return;
            }
            zap zapVar2 = this.zaa;
            if (zapVar2.zac.getErrorResolutionIntent(zapVar2.getActivity(), connectionResult.zzb, null) != null) {
                zap zapVar3 = this.zaa;
                zapVar3.zac.zag(zapVar3.getActivity(), zapVar3.mLifecycleFragment, connectionResult.zzb, 2, this.zaa);
                return;
            }
            if (connectionResult.zzb != 18) {
                this.zaa.zaa(connectionResult, this.zab.zaa);
                return;
            }
            zap zapVar4 = this.zaa;
            Dialog dialogZab = zapVar4.zac.zab(zapVar4.getActivity(), zapVar4);
            zap zapVar5 = this.zaa;
            zapVar5.zac.zac(zapVar5.getActivity().getApplicationContext(), new zan(this, dialogZab));
        }
    }
}
