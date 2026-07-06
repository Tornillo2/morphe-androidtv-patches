package com.amazon.avod.mpb.api.callback;

import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DisplayModeManager {
    void clearTargetFrameRate() throws InterruptedException, CancellationException;

    void setTargetFrameRate(float f, @NotNull Consumer<Exception> consumer);
}
