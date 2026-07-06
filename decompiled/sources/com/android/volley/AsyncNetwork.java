package com.android.volley;

import androidx.annotation.RestrictTo;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncNetwork implements Network {
    public ExecutorService mBlockingExecutor;
    public ExecutorService mNonBlockingExecutor;
    public ScheduledExecutorService mNonBlockingScheduledExecutor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnRequestComplete {
        void onError(VolleyError volleyError);

        void onSuccess(NetworkResponse networkResponse);
    }

    public ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    public ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    public ScheduledExecutorService getNonBlockingScheduledExecutor() {
        return this.mNonBlockingScheduledExecutor;
    }

    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        performRequest(request, new OnRequestComplete() { // from class: com.android.volley.AsyncNetwork.1
            @Override // com.android.volley.AsyncNetwork.OnRequestComplete
            public void onError(VolleyError volleyError) {
                atomicReference2.set(volleyError);
                countDownLatch.countDown();
            }

            @Override // com.android.volley.AsyncNetwork.OnRequestComplete
            public void onSuccess(NetworkResponse networkResponse) {
                atomicReference.set(networkResponse);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            if (atomicReference.get() != null) {
                return (NetworkResponse) atomicReference.get();
            }
            if (atomicReference2.get() != null) {
                throw ((VolleyError) atomicReference2.get());
            }
            throw new VolleyError("Neither response entry was set");
        } catch (InterruptedException e) {
            VolleyLog.e(e, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new VolleyError(e);
        }
    }

    public abstract void performRequest(Request<?> request, OnRequestComplete onRequestComplete);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setBlockingExecutor(ExecutorService executorService) {
        this.mBlockingExecutor = executorService;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNonBlockingExecutor(ExecutorService executorService) {
        this.mNonBlockingExecutor = executorService;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNonBlockingScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
        this.mNonBlockingScheduledExecutor = scheduledExecutorService;
    }
}
