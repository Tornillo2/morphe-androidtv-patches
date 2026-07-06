package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zacp;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ResultTransform<R extends Result, S extends Result> {
    @NonNull
    public final PendingResult<S> createFailedResult(@NonNull Status status) {
        return new zacp(status);
    }

    @Nullable
    @WorkerThread
    public abstract PendingResult<S> onSuccess(@NonNull R r);

    @NonNull
    public Status onFailure(@NonNull Status status) {
        return status;
    }
}
