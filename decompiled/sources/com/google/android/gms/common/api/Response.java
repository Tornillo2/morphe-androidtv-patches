package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Response<T extends Result> {
    public Result zza;

    public Response() {
    }

    @NonNull
    public T getResult() {
        return (T) this.zza;
    }

    public void setResult(@NonNull T t) {
        this.zza = t;
    }

    public Response(@NonNull T t) {
        this.zza = t;
    }
}
