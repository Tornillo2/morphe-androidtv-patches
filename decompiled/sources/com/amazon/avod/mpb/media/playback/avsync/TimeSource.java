package com.amazon.avod.mpb.media.playback.avsync;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface TimeSource {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @JvmField
    @NotNull
    public static final TimeSource DEFAULT_INSTANCE = new TimeSource$Companion$DEFAULT_INSTANCE$1();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    long getCurrentRealTimeUs();

    boolean hasStartedTicking();
}
