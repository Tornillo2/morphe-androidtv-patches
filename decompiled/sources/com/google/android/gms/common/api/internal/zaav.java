package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zaav implements Runnable {
    public final /* synthetic */ zaaw zab;

    public /* synthetic */ zaav(zaaw zaawVar, zaau zaauVar) {
        this.zab = zaawVar;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        this.zab.zab.lock();
        try {
            try {
                if (!Thread.interrupted()) {
                    zaa();
                }
            } catch (RuntimeException e) {
                this.zab.zaa.zam(e);
            }
        } finally {
            this.zab.zab.unlock();
        }
    }

    @WorkerThread
    public abstract void zaa();
}
