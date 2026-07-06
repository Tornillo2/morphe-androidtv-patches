package com.amazon.livingroom.mediapipelinebackend;

import android.view.Surface;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackSurface implements AutoCloseable {

    @Nullable
    public Listener listener;

    @NotNull
    public final String name;

    @Nullable
    public Surface surface;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onSurfaceChanged(@Nullable Surface surface);
    }

    public PlaybackSurface(@NotNull String name, @Nullable Surface surface) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.surface = surface;
    }

    public final synchronized void clearListener(@NotNull Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (Intrinsics.areEqual(this.listener, listener)) {
            this.listener = null;
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        setSurface(null);
    }

    public abstract void commitPendingAspectRatio();

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final synchronized Surface getSurface() {
        return this.surface;
    }

    public abstract void recreateSurfaceView();

    public abstract void resetViewport();

    @Nullable
    public final synchronized Surface setListener(@NotNull Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.listener != null) {
            throw new MediaPipelineBackendException(this + " already has a listener: " + this.listener + " when trying to set new one " + listener, MediaPipelineBackendResultCode.AV_MPB_SURFACE_MULTIPLE_LISTENERS);
        }
        this.listener = listener;
        return this.surface;
    }

    public abstract void setPendingAspectRatio(float f);

    public final void setSurface(@Nullable Surface surface) {
        Listener listener;
        synchronized (this) {
            if (Intrinsics.areEqual(this.surface, surface)) {
                listener = null;
            } else {
                this.surface = surface;
                listener = this.listener;
            }
        }
        if (listener != null) {
            listener.onSurfaceChanged(this.surface);
        }
    }

    public abstract void setViewport(int i, int i2, int i3, int i4);

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + "{" + this.name + "=" + this.surface + "}";
    }

    public /* synthetic */ PlaybackSurface(String str, Surface surface, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : surface);
    }
}
