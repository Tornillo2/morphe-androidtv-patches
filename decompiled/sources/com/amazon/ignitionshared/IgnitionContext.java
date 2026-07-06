package com.amazon.ignitionshared;

import android.view.SurfaceView;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IgnitionContext {

    @NotNull
    public final ExploreByTouchHelper accessibilityDelegate;

    @NotNull
    public final AppCompatActivity activity;

    @NotNull
    public final SurfaceView igniteSurfaceView;

    @NotNull
    public final FrameLayout playbackSurfacesContainer;

    @NotNull
    public final Window window;

    public IgnitionContext(@NotNull AppCompatActivity activity, @NotNull Window window, @NotNull FrameLayout playbackSurfacesContainer, @NotNull SurfaceView igniteSurfaceView, @NotNull ExploreByTouchHelper accessibilityDelegate) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(playbackSurfacesContainer, "playbackSurfacesContainer");
        Intrinsics.checkNotNullParameter(igniteSurfaceView, "igniteSurfaceView");
        Intrinsics.checkNotNullParameter(accessibilityDelegate, "accessibilityDelegate");
        this.activity = activity;
        this.window = window;
        this.playbackSurfacesContainer = playbackSurfacesContainer;
        this.igniteSurfaceView = igniteSurfaceView;
        this.accessibilityDelegate = accessibilityDelegate;
    }

    public static /* synthetic */ IgnitionContext copy$default(IgnitionContext ignitionContext, AppCompatActivity appCompatActivity, Window window, FrameLayout frameLayout, SurfaceView surfaceView, ExploreByTouchHelper exploreByTouchHelper, int i, Object obj) {
        if ((i & 1) != 0) {
            appCompatActivity = ignitionContext.activity;
        }
        if ((i & 2) != 0) {
            window = ignitionContext.window;
        }
        if ((i & 4) != 0) {
            frameLayout = ignitionContext.playbackSurfacesContainer;
        }
        if ((i & 8) != 0) {
            surfaceView = ignitionContext.igniteSurfaceView;
        }
        if ((i & 16) != 0) {
            exploreByTouchHelper = ignitionContext.accessibilityDelegate;
        }
        ExploreByTouchHelper exploreByTouchHelper2 = exploreByTouchHelper;
        FrameLayout frameLayout2 = frameLayout;
        return ignitionContext.copy(appCompatActivity, window, frameLayout2, surfaceView, exploreByTouchHelper2);
    }

    @NotNull
    public final AppCompatActivity component1() {
        return this.activity;
    }

    @NotNull
    public final Window component2() {
        return this.window;
    }

    @NotNull
    public final FrameLayout component3() {
        return this.playbackSurfacesContainer;
    }

    @NotNull
    public final SurfaceView component4() {
        return this.igniteSurfaceView;
    }

    @NotNull
    public final ExploreByTouchHelper component5() {
        return this.accessibilityDelegate;
    }

    @NotNull
    public final IgnitionContext copy(@NotNull AppCompatActivity activity, @NotNull Window window, @NotNull FrameLayout playbackSurfacesContainer, @NotNull SurfaceView igniteSurfaceView, @NotNull ExploreByTouchHelper accessibilityDelegate) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(playbackSurfacesContainer, "playbackSurfacesContainer");
        Intrinsics.checkNotNullParameter(igniteSurfaceView, "igniteSurfaceView");
        Intrinsics.checkNotNullParameter(accessibilityDelegate, "accessibilityDelegate");
        return new IgnitionContext(activity, window, playbackSurfacesContainer, igniteSurfaceView, accessibilityDelegate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IgnitionContext)) {
            return false;
        }
        IgnitionContext ignitionContext = (IgnitionContext) obj;
        return Intrinsics.areEqual(this.activity, ignitionContext.activity) && Intrinsics.areEqual(this.window, ignitionContext.window) && Intrinsics.areEqual(this.playbackSurfacesContainer, ignitionContext.playbackSurfacesContainer) && Intrinsics.areEqual(this.igniteSurfaceView, ignitionContext.igniteSurfaceView) && Intrinsics.areEqual(this.accessibilityDelegate, ignitionContext.accessibilityDelegate);
    }

    @NotNull
    public final ExploreByTouchHelper getAccessibilityDelegate() {
        return this.accessibilityDelegate;
    }

    @NotNull
    public final AppCompatActivity getActivity() {
        return this.activity;
    }

    @NotNull
    public final SurfaceView getIgniteSurfaceView() {
        return this.igniteSurfaceView;
    }

    @NotNull
    public final FrameLayout getPlaybackSurfacesContainer() {
        return this.playbackSurfacesContainer;
    }

    @NotNull
    public final Window getWindow() {
        return this.window;
    }

    public int hashCode() {
        return this.accessibilityDelegate.hashCode() + ((this.igniteSurfaceView.hashCode() + ((this.playbackSurfacesContainer.hashCode() + ((this.window.hashCode() + (this.activity.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        return "IgnitionContext(activity=" + this.activity + ", window=" + this.window + ", playbackSurfacesContainer=" + this.playbackSurfacesContainer + ", igniteSurfaceView=" + this.igniteSurfaceView + ", accessibilityDelegate=" + this.accessibilityDelegate + ")";
    }
}
