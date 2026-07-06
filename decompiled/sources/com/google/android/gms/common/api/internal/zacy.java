package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zacy implements Runnable {
    public final /* synthetic */ Result zaa;
    public final /* synthetic */ zada zab;

    public zacy(zada zadaVar, Result result) {
        this.zab = zadaVar;
        this.zaa = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        GoogleApiClient googleApiClient;
        try {
            try {
                ThreadLocal threadLocal = BasePendingResult.zaa;
                threadLocal.set(Boolean.TRUE);
                ResultTransform resultTransform = this.zab.zaa;
                Preconditions.checkNotNull(resultTransform);
                PendingResult pendingResultOnSuccess = resultTransform.onSuccess(this.zaa);
                zacz zaczVar = this.zab.zah;
                zaczVar.sendMessage(zaczVar.obtainMessage(0, pendingResultOnSuccess));
                threadLocal.set(Boolean.FALSE);
                zada.zan(this.zaa);
                googleApiClient = (GoogleApiClient) this.zab.zag.get();
                if (googleApiClient == null) {
                    return;
                }
            } catch (RuntimeException e) {
                zacz zaczVar2 = this.zab.zah;
                zaczVar2.sendMessage(zaczVar2.obtainMessage(1, e));
                BasePendingResult.zaa.set(Boolean.FALSE);
                zada.zan(this.zaa);
                googleApiClient = (GoogleApiClient) this.zab.zag.get();
                if (googleApiClient == null) {
                    return;
                }
            }
            googleApiClient.zap(this.zab);
        } catch (Throwable th) {
            BasePendingResult.zaa.set(Boolean.FALSE);
            zada.zan(this.zaa);
            GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zab.zag.get();
            if (googleApiClient2 != null) {
                googleApiClient2.zap(this.zab);
            }
            throw th;
        }
    }
}
