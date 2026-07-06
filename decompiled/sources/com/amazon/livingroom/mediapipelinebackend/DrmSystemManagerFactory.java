package com.amazon.livingroom.mediapipelinebackend;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DrmSystemManagerFactory {

    @NotNull
    public final DrmProvisioner drmProvisioner;

    public DrmSystemManagerFactory(@NotNull DrmProvisioner drmProvisioner) {
        Intrinsics.checkNotNullParameter(drmProvisioner, "drmProvisioner");
        this.drmProvisioner = drmProvisioner;
    }

    @CalledFromNative
    @NotNull
    public final ResultHolder<DrmSystemManager> createInstance() {
        return ResultHolder.fromResult(new DrmSystemManager(this.drmProvisioner));
    }

    @NotNull
    public final DrmProvisioner getDrmProvisioner() {
        return this.drmProvisioner;
    }
}
