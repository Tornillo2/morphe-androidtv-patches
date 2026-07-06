package com.amazon.livingroom.mediapipelinebackend;

import android.view.Surface;
import com.amazon.livingroom.di.ApplicationScope;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class ExternalPlaybackSurfaceRegistry {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static AtomicInteger nextId = new AtomicInteger();

    @NotNull
    public final ConcurrentHashMap<String, ExternalPlaybackSurface> surfaceRefsByKey = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final AtomicInteger getNextId() {
            return ExternalPlaybackSurfaceRegistry.nextId;
        }

        public final void setNextId(@NotNull AtomicInteger atomicInteger) {
            Intrinsics.checkNotNullParameter(atomicInteger, "<set-?>");
            ExternalPlaybackSurfaceRegistry.nextId = atomicInteger;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public ExternalPlaybackSurfaceRegistry() {
    }

    public static /* synthetic */ PlaybackSurface register$default(ExternalPlaybackSurfaceRegistry externalPlaybackSurfaceRegistry, String str, Surface surface, int i, Object obj) {
        if ((i & 2) != 0) {
            surface = null;
        }
        return externalPlaybackSurfaceRegistry.register(str, surface);
    }

    @Nullable
    public final ExternalPlaybackSurface get(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.surfaceRefsByKey.get(key);
    }

    @NotNull
    public final PlaybackSurface register(@NotNull String name, @Nullable Surface surface) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name + "-" + nextId.incrementAndGet();
        ExternalPlaybackSurface externalPlaybackSurface = new ExternalPlaybackSurface(this, str, surface);
        this.surfaceRefsByKey.put(str, externalPlaybackSurface);
        return externalPlaybackSurface;
    }

    public final void unregister(String str) {
        this.surfaceRefsByKey.remove(str);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ExternalPlaybackSurface extends PlaybackSurface {

        @NotNull
        public final ExternalPlaybackSurfaceRegistry registry;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExternalPlaybackSurface(@NotNull ExternalPlaybackSurfaceRegistry registry, @NotNull String name, @Nullable Surface surface) {
            super(name, surface);
            Intrinsics.checkNotNullParameter(registry, "registry");
            Intrinsics.checkNotNullParameter(name, "name");
            this.registry = registry;
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface, java.lang.AutoCloseable
        public void close() {
            setSurface(null);
            this.registry.unregister(this.name);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
        public void commitPendingAspectRatio() {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
        public void recreateSurfaceView() {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
        public void resetViewport() {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
        public void setPendingAspectRatio(float f) {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
        public void setViewport(int i, int i2, int i3, int i4) {
        }
    }
}
