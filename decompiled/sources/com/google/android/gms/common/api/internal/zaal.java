package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaal implements BaseGmsClient.ConnectionProgressReportCallbacks {
    public final WeakReference zaa;
    public final Api zab;
    public final boolean zac;

    public zaal(zaaw zaawVar, Api api, boolean z) {
        this.zaa = new WeakReference(zaawVar);
        this.zab = api;
        this.zac = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        zaaw zaawVar = (zaaw) this.zaa.get();
        if (zaawVar == null) {
            return;
        }
        Preconditions.checkState(Looper.myLooper() == zaawVar.zaa.zag.zao, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zaawVar.zab.lock();
        try {
            if (zaawVar.zaG(0)) {
                if (!connectionResult.isSuccess()) {
                    zaawVar.zaE(connectionResult, this.zab, this.zac);
                }
                if (zaawVar.zaH()) {
                    zaawVar.zaF();
                }
            }
        } finally {
            zaawVar.zab.unlock();
        }
    }
}
