package com.amazon.avod.mpb.api.query;

import com.amazon.avod.mpb.annotate.CalledFromNative;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CodecQueryResult {
    public final boolean isHardwareAccelerated;
    public final boolean isSmooth;
    public final boolean isSupported;

    public CodecQueryResult(boolean z, boolean z2, boolean z3) {
        this.isSupported = z;
        this.isSmooth = z2;
        this.isHardwareAccelerated = z3;
    }

    public static CodecQueryResult copy$default(CodecQueryResult codecQueryResult, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = codecQueryResult.isSupported;
        }
        if ((i & 2) != 0) {
            z2 = codecQueryResult.isSmooth;
        }
        if ((i & 4) != 0) {
            z3 = codecQueryResult.isHardwareAccelerated;
        }
        codecQueryResult.getClass();
        return new CodecQueryResult(z, z2, z3);
    }

    public final boolean component1() {
        return this.isSupported;
    }

    public final boolean component2() {
        return this.isSmooth;
    }

    public final boolean component3() {
        return this.isHardwareAccelerated;
    }

    @NotNull
    public final CodecQueryResult copy(boolean z, boolean z2, boolean z3) {
        return new CodecQueryResult(z, z2, z3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CodecQueryResult)) {
            return false;
        }
        CodecQueryResult codecQueryResult = (CodecQueryResult) obj;
        return this.isSupported == codecQueryResult.isSupported && this.isSmooth == codecQueryResult.isSmooth && this.isHardwareAccelerated == codecQueryResult.isHardwareAccelerated;
    }

    public int hashCode() {
        return MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isHardwareAccelerated) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isSmooth) + (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isSupported) * 31)) * 31);
    }

    @CalledFromNative
    public final boolean isHardwareAccelerated() {
        return this.isHardwareAccelerated;
    }

    @CalledFromNative
    public final boolean isSmooth() {
        return this.isSmooth;
    }

    @CalledFromNative
    public final boolean isSupported() {
        return this.isSupported;
    }

    @NotNull
    public String toString() {
        return "CodecQueryResult(isSupported=" + this.isSupported + ", isSmooth=" + this.isSmooth + ", isHardwareAccelerated=" + this.isHardwareAccelerated + ")";
    }
}
