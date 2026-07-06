package com.amazon.ignitionshared.pear;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable(with = WatchStateSerializer.class)
public interface WatchState {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public final KSerializer<WatchState> serializer() {
            return WatchStateSerializer.INSTANCE;
        }
    }

    @NotNull
    Id getFocusId();

    @Nullable
    String getLastEngagementTime();

    @Nullable
    Integer getTotalTimeSeconds();

    @Nullable
    String getWatchNextType();
}
