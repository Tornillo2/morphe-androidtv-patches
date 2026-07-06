package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zab implements PendingResult.StatusListener {
    public final /* synthetic */ Batch zaa;

    public zab(Batch batch) {
        this.zaa = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        synchronized (this.zaa.zai) {
            try {
                if (this.zaa.isCanceled()) {
                    return;
                }
                if (status.isCanceled()) {
                    this.zaa.zag = true;
                } else if (!status.isSuccess()) {
                    this.zaa.zaf = true;
                }
                Batch batch = this.zaa;
                int i = batch.zae - 1;
                batch.zae = i;
                if (i == 0) {
                    if (batch.zag) {
                        super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                    } else {
                        batch.setResult(new BatchResult(batch.zaf ? new Status(13, null, null, null) : Status.RESULT_SUCCESS, batch.zah));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
