package com.amazon.ignitionshared;

import com.amazon.ignitionshared.DeviceAttestationService;
import java.util.List;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NoopDeviceAttestor implements DeviceAttestationService.Attestor {
    @Override // com.amazon.ignitionshared.DeviceAttestationService.Attestor
    @Nullable
    public Object attestDevice(@NotNull String str, @NotNull List<String> list, @NotNull Continuation<? super DeviceAttestationResult> continuation) {
        return new DeviceAttestationResult(false, "Device attestation not implemented");
    }
}
