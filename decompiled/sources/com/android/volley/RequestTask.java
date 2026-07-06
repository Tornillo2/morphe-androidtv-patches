package com.android.volley;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class RequestTask<T> implements Runnable {
    public final Request<T> mRequest;

    public RequestTask(Request<T> request) {
        this.mRequest = request;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public int compareTo(RequestTask<?> requestTask) {
        return this.mRequest.compareTo((Request) requestTask.mRequest);
    }
}
