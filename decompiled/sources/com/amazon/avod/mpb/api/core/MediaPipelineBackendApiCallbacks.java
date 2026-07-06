package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.callback.LogCallback;
import com.amazon.avod.mpb.api.callback.PropertyChangedCallback;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineBackendApiCallbacks {

    @NotNull
    public final PropertyChangedCallback apiPropertyChangedCallback;

    @NotNull
    public final LogCallback logCallback;

    public MediaPipelineBackendApiCallbacks(@NotNull LogCallback logCallback, @NotNull PropertyChangedCallback apiPropertyChangedCallback) {
        Intrinsics.checkNotNullParameter(logCallback, "logCallback");
        Intrinsics.checkNotNullParameter(apiPropertyChangedCallback, "apiPropertyChangedCallback");
        this.logCallback = logCallback;
        this.apiPropertyChangedCallback = apiPropertyChangedCallback;
    }

    public static /* synthetic */ MediaPipelineBackendApiCallbacks copy$default(MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks, LogCallback logCallback, PropertyChangedCallback propertyChangedCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            logCallback = mediaPipelineBackendApiCallbacks.logCallback;
        }
        if ((i & 2) != 0) {
            propertyChangedCallback = mediaPipelineBackendApiCallbacks.apiPropertyChangedCallback;
        }
        return mediaPipelineBackendApiCallbacks.copy(logCallback, propertyChangedCallback);
    }

    @NotNull
    public final LogCallback component1() {
        return this.logCallback;
    }

    @NotNull
    public final PropertyChangedCallback component2() {
        return this.apiPropertyChangedCallback;
    }

    @NotNull
    public final MediaPipelineBackendApiCallbacks copy(@NotNull LogCallback logCallback, @NotNull PropertyChangedCallback apiPropertyChangedCallback) {
        Intrinsics.checkNotNullParameter(logCallback, "logCallback");
        Intrinsics.checkNotNullParameter(apiPropertyChangedCallback, "apiPropertyChangedCallback");
        return new MediaPipelineBackendApiCallbacks(logCallback, apiPropertyChangedCallback);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPipelineBackendApiCallbacks)) {
            return false;
        }
        MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks = (MediaPipelineBackendApiCallbacks) obj;
        return Intrinsics.areEqual(this.logCallback, mediaPipelineBackendApiCallbacks.logCallback) && Intrinsics.areEqual(this.apiPropertyChangedCallback, mediaPipelineBackendApiCallbacks.apiPropertyChangedCallback);
    }

    @NotNull
    public final PropertyChangedCallback getApiPropertyChangedCallback() {
        return this.apiPropertyChangedCallback;
    }

    @NotNull
    public final LogCallback getLogCallback() {
        return this.logCallback;
    }

    public int hashCode() {
        return this.apiPropertyChangedCallback.hashCode() + (this.logCallback.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "MediaPipelineBackendApiCallbacks(logCallback=" + this.logCallback + ", apiPropertyChangedCallback=" + this.apiPropertyChangedCallback + ")";
    }
}
