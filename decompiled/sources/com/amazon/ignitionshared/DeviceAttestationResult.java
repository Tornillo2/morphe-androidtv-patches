package com.amazon.ignitionshared;

import androidx.annotation.Keep;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Keep
public final class DeviceAttestationResult {

    @NotNull
    private final String errorMessage;
    private final boolean success;

    public DeviceAttestationResult(boolean z, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.success = z;
        this.errorMessage = errorMessage;
    }

    public static /* synthetic */ DeviceAttestationResult copy$default(DeviceAttestationResult deviceAttestationResult, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = deviceAttestationResult.success;
        }
        if ((i & 2) != 0) {
            str = deviceAttestationResult.errorMessage;
        }
        return deviceAttestationResult.copy(z, str);
    }

    public final boolean component1() {
        return this.success;
    }

    @NotNull
    public final String component2() {
        return this.errorMessage;
    }

    @NotNull
    public final DeviceAttestationResult copy(boolean z, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        return new DeviceAttestationResult(z, errorMessage);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceAttestationResult)) {
            return false;
        }
        DeviceAttestationResult deviceAttestationResult = (DeviceAttestationResult) obj;
        return this.success == deviceAttestationResult.success && Intrinsics.areEqual(this.errorMessage, deviceAttestationResult.errorMessage);
    }

    @NotNull
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public int hashCode() {
        return this.errorMessage.hashCode() + (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.success) * 31);
    }

    @NotNull
    public String toString() {
        return "DeviceAttestationResult(success=" + this.success + ", errorMessage=" + this.errorMessage + ")";
    }
}
