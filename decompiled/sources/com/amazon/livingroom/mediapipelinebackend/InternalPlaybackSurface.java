package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.AnyThread;
import androidx.annotation.UiThread;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import com.amazon.ignitionshared.IgnitionContext;
import com.amazon.ignitionshared.IgnitionContextListener;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nInternalPlaybackSurface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InternalPlaybackSurface.kt\ncom/amazon/livingroom/mediapipelinebackend/InternalPlaybackSurface\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,258:1\n1#2:259\n*E\n"})
public final class InternalPlaybackSurface extends PlaybackSurface implements IgnitionContextListener {
    public float currentContentAspectRatio;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final IgnitionContextProvider ignitionContextProvider;

    @NotNull
    public final View.OnLayoutChangeListener onLayoutChange;
    public float pendingContentAspectRatio;
    public float relativeViewportHeight;
    public float relativeViewportLeft;
    public float relativeViewportTop;
    public float relativeViewportWidth;

    @NotNull
    public final SurfaceHolderCallback surfaceHolderCallback;

    @Nullable
    public SurfaceView surfaceView;

    @NotNull
    public final Handler uiThreadHandler;

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String CLASS_NAME = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(InternalPlaybackSurface.class)).getSimpleName();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @Nullable
        public final String getCLASS_NAME() {
            return InternalPlaybackSurface.CLASS_NAME;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AssistedFactory
    public interface Factory {
        @NotNull
        InternalPlaybackSurface create(@NotNull String str);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SurfaceHolderCallback implements SurfaceHolder.Callback {
        public SurfaceHolderCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(@NotNull SurfaceHolder holder, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            String str = InternalPlaybackSurface.this.name;
            Surface surface = holder.getSurface();
            StringBuilder sb = new StringBuilder("Surface changed: ");
            sb.append(str);
            sb.append("=");
            sb.append(surface);
            sb.append(" format=");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb, i, " width=", i2, " height=");
            sb.append(i3);
            MpbLog.t(sb.toString());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            MpbLog.i("Surface created: " + InternalPlaybackSurface.this.name + "=" + holder.getSurface());
            InternalPlaybackSurface.this.setSurface(holder.getSurface());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            MpbLog.i("Surface destroyed: " + InternalPlaybackSurface.this.name);
            InternalPlaybackSurface.this.setSurface(null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public InternalPlaybackSurface(@Assisted @NotNull String name, @NotNull IgnitionContextProvider ignitionContextProvider, @NotNull Handler uiThreadHandler, @NotNull DeviceProperties deviceProperties) {
        super(name, null, 2, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "ignitionContextProvider");
        Intrinsics.checkNotNullParameter(uiThreadHandler, "uiThreadHandler");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        this.ignitionContextProvider = ignitionContextProvider;
        this.uiThreadHandler = uiThreadHandler;
        this.deviceProperties = deviceProperties;
        this.onLayoutChange = new View.OnLayoutChangeListener() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                InternalPlaybackSurface.onLayoutChange$lambda$0(this.f$0, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        };
        this.surfaceHolderCallback = new SurfaceHolderCallback();
        this.relativeViewportWidth = 1.0f;
        this.relativeViewportHeight = 1.0f;
        uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface._init_$lambda$2(this.f$0);
            }
        });
    }

    public static final void _init_$lambda$2(InternalPlaybackSurface internalPlaybackSurface) {
        IgnitionContext ignitionContext = internalPlaybackSurface.ignitionContextProvider.context;
        if (ignitionContext != null) {
            internalPlaybackSurface.createSurfaceView(ignitionContext);
        }
        internalPlaybackSurface.ignitionContextProvider.addListener(internalPlaybackSurface);
    }

    public static final void close$lambda$3(InternalPlaybackSurface internalPlaybackSurface) {
        internalPlaybackSurface.ignitionContextProvider.removeListener(internalPlaybackSurface);
        internalPlaybackSurface.removeSurfaceView();
    }

    public static final void commitPendingAspectRatio$lambda$7(InternalPlaybackSurface internalPlaybackSurface) {
        float f = internalPlaybackSurface.currentContentAspectRatio;
        float f2 = internalPlaybackSurface.pendingContentAspectRatio;
        if (f == f2) {
            return;
        }
        internalPlaybackSurface.currentContentAspectRatio = f2;
        internalPlaybackSurface.updateLayoutParams();
    }

    public static final void onLayoutChange$lambda$0(InternalPlaybackSurface internalPlaybackSurface, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        MpbLog.t(CLASS_NAME + ".onLayoutChange(view=" + view + ", left=" + i + ", top=" + i2 + ", right=" + i3 + ", bottom=" + i4 + ", oldLeft=" + i5 + ", oldTop=" + i6 + ", oldRight=" + i7 + ", oldBottom=" + i8 + ")");
        internalPlaybackSurface.updateLayoutParams();
    }

    public static final void recreateSurfaceView$lambda$5(InternalPlaybackSurface internalPlaybackSurface) {
        IgnitionContext ignitionContext = internalPlaybackSurface.ignitionContextProvider.context;
        if (ignitionContext == null) {
            return;
        }
        internalPlaybackSurface.removeSurfaceView();
        internalPlaybackSurface.setSurface(null);
        internalPlaybackSurface.createSurfaceView(ignitionContext);
    }

    public static final void resetViewport$lambda$9(InternalPlaybackSurface internalPlaybackSurface) {
        internalPlaybackSurface.relativeViewportLeft = 0.0f;
        internalPlaybackSurface.relativeViewportTop = 0.0f;
        internalPlaybackSurface.relativeViewportWidth = 1.0f;
        internalPlaybackSurface.relativeViewportHeight = 1.0f;
        internalPlaybackSurface.updateLayoutParams();
    }

    public static final void setViewport$lambda$8(InternalPlaybackSurface internalPlaybackSurface, int i, int i2, int i3, int i4) {
        Object obj = internalPlaybackSurface.deviceProperties.get(DeviceProperties.MAX_VIDEO_WIDTH);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        int iIntValue = ((Number) obj).intValue();
        Object obj2 = internalPlaybackSurface.deviceProperties.get(DeviceProperties.MAX_VIDEO_HEIGHT);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        int iIntValue2 = ((Number) obj2).intValue();
        float f = iIntValue;
        internalPlaybackSurface.relativeViewportLeft = i / f;
        float f2 = i2;
        float f3 = iIntValue2;
        internalPlaybackSurface.relativeViewportTop = f2 / f3;
        internalPlaybackSurface.relativeViewportWidth = i3 / f;
        internalPlaybackSurface.relativeViewportHeight = i4 / f3;
        internalPlaybackSurface.updateLayoutParams();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface, java.lang.AutoCloseable
    @AnyThread
    public void close() {
        setSurface(null);
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface.close$lambda$3(this.f$0);
            }
        });
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
    @AnyThread
    public void commitPendingAspectRatio() {
        MpbLog.t(CLASS_NAME + ".commitPendingAspectRatio(): name=" + this.name);
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface.commitPendingAspectRatio$lambda$7(this.f$0);
            }
        });
    }

    @UiThread
    public final void createSurfaceView(IgnitionContext ignitionContext) {
        MpbLog.i(CLASS_NAME + ".createSurfaceView(): name=" + this.name);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 51);
        SurfaceView surfaceView = new SurfaceView(ignitionContext.activity);
        surfaceView.addOnLayoutChangeListener(this.onLayoutChange);
        surfaceView.getHolder().addCallback(this.surfaceHolderCallback);
        ignitionContext.playbackSurfacesContainer.addView(surfaceView, layoutParams);
        this.surfaceView = surfaceView;
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    @UiThread
    public void onContextCreated(@NotNull IgnitionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        createSurfaceView(context);
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    @UiThread
    public void onContextDestroyed() {
        removeSurfaceView();
        setSurface(null);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
    @AnyThread
    public void recreateSurfaceView() {
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface.recreateSurfaceView$lambda$5(this.f$0);
            }
        });
    }

    @UiThread
    public final void removeSurfaceView() {
        MpbLog.i(CLASS_NAME + ".removeSurfaceView(): name=" + this.name + " surfaceView=" + this.surfaceView);
        SurfaceView surfaceView = this.surfaceView;
        if (surfaceView != null) {
            ViewParent parent = surfaceView.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(surfaceView);
            surfaceView.getHolder().removeCallback(this.surfaceHolderCallback);
            surfaceView.removeOnLayoutChangeListener(this.onLayoutChange);
            this.surfaceView = null;
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
    @AnyThread
    public void resetViewport() {
        MpbLog.t(CLASS_NAME + ".resetViewport(): name=" + this.name);
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface.resetViewport$lambda$9(this.f$0);
            }
        });
    }

    public final void setLayoutParams(View view, int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (i == marginLayoutParams.leftMargin && i2 == marginLayoutParams.topMargin && i3 == marginLayoutParams.width && i4 == marginLayoutParams.height) {
            MpbLog.t(CLASS_NAME + ".setLayoutParameters(view=" + view + ", left=" + i + ", top=" + i2 + ", width=" + i3 + ", height=" + i4 + "): Parameters haven't changed");
            return;
        }
        MpbLog.i(CLASS_NAME + ".setLayoutParameters(view=" + view + ", left=" + i + ", top=" + i2 + ", width=" + i3 + ", height=" + i4 + "): Parameters have changed");
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.topMargin = i2;
        marginLayoutParams.width = i3;
        marginLayoutParams.height = i4;
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
    @AnyThread
    public void setPendingAspectRatio(final float f) {
        MpbLog.t(CLASS_NAME + ".setPendingAspectRatio(" + f + "): name=" + this.name);
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.pendingContentAspectRatio = f;
            }
        });
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface
    @AnyThread
    public void setViewport(final int i, final int i2, final int i3, final int i4) {
        MpbLog.i(CLASS_NAME + ".setViewport(left=" + i + " top=" + i2 + " width=" + i3 + " height=" + i4 + "): name=" + this.name);
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                InternalPlaybackSurface.setViewport$lambda$8(this.f$0, i, i2, i3, i4);
            }
        });
    }

    @UiThread
    public final void updateLayoutParams() {
        float f;
        float f2;
        SurfaceView surfaceView = this.surfaceView;
        if (surfaceView == null) {
            return;
        }
        ViewParent parent = surfaceView.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) parent;
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (width <= 0 || height <= 0) {
            MpbLog.w(CLASS_NAME + ".updateLayoutParams(): Aborting because parent has size " + width + "x" + height);
            return;
        }
        float f3 = width;
        float f4 = this.relativeViewportLeft * f3;
        float f5 = height;
        float f6 = this.relativeViewportTop * f5;
        float f7 = this.relativeViewportWidth * f3;
        float f8 = this.relativeViewportHeight * f5;
        float f9 = f7 / f8;
        Float fValueOf = Float.valueOf(this.currentContentAspectRatio);
        if (fValueOf.floatValue() <= 0.0f) {
            fValueOf = null;
        }
        float fFloatValue = fValueOf != null ? fValueOf.floatValue() : f9;
        if (f9 > fFloatValue) {
            f2 = fFloatValue * f8;
            f = f8;
        } else {
            f = f7 / fFloatValue;
            f2 = f7;
        }
        setLayoutParams(surfaceView, MathKt__MathJVMKt.roundToInt(((f7 - f2) / 2.0f) + f4), MathKt__MathJVMKt.roundToInt(((f8 - f) / 2.0f) + f6), MathKt__MathJVMKt.roundToInt(f2), MathKt__MathJVMKt.roundToInt(f));
    }
}
