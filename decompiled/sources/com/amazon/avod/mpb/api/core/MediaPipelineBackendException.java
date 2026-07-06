package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.annotate.CalledFromNative;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineBackendException extends Exception {
    public final int mpbErrorCode;

    @NotNull
    public final MediaPipelineBackendResultCode resultCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPipelineBackendException(@NotNull String message, @NotNull MediaPipelineBackendResultCode resultCode) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(resultCode, "resultCode");
        this.resultCode = resultCode;
        this.mpbErrorCode = resultCode.resultCode;
    }

    @CalledFromNative
    public final int getIgniteMpbErrorCode() {
        return this.mpbErrorCode;
    }

    @NotNull
    public final MediaPipelineBackendResultCode getResultCode() {
        return this.resultCode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPipelineBackendException(@NotNull String message, @NotNull MediaPipelineBackendResultCode resultCode, @NotNull Throwable cause) {
        super(message, cause);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(resultCode, "resultCode");
        Intrinsics.checkNotNullParameter(cause, "cause");
        this.resultCode = resultCode;
        this.mpbErrorCode = resultCode.resultCode;
    }
}
