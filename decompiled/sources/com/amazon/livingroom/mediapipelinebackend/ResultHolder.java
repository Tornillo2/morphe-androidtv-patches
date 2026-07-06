package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ResultHolder<T> {
    public final T result;
    public final int returnCode;

    public ResultHolder(int i, @Nullable T t) {
        if (t == null && i == 0) {
            throw new IllegalArgumentException("A null result data require a non-0 return code");
        }
        if (t != null && i != 0) {
            throw new IllegalArgumentException("A non-null result data require a 0 return code");
        }
        this.returnCode = i;
        this.result = t;
    }

    public static <T> ResultHolder<T> fromErrorCode(int i) {
        return new ResultHolder<>(i, null);
    }

    public static <T> ResultHolder<T> fromResult(@NonNull T t) {
        return new ResultHolder<>(0, t);
    }

    @Nullable
    @CalledFromNative
    public T getResult() {
        return this.result;
    }

    @CalledFromNative
    public int getReturnCode() {
        return this.returnCode;
    }
}
