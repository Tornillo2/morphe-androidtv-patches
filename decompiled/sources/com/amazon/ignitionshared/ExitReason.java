package com.amazon.ignitionshared;

import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ExitReason {
    public final int code;

    @NotNull
    public final String details;

    public ExitReason(int i, @NotNull String details) {
        Intrinsics.checkNotNullParameter(details, "details");
        this.code = i;
        this.details = details;
    }

    @CalledFromNative
    public final int getCode() {
        return this.code;
    }

    @CalledFromNative
    @NotNull
    public final String getDetails() {
        return this.details;
    }
}
