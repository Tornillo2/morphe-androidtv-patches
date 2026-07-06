package com.amazon.ignitionshared.tvinput;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nTifOverlaySurfaceHolderCallbackProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TifOverlaySurfaceHolderCallbackProvider.kt\ncom/amazon/ignitionshared/tvinput/TifOverlaySurfaceHolderCallbackProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,67:1\n1#2:68\n*E\n"})
public final class TifOverlaySurfaceHolderCallbackProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG;

    @Nullable
    public SurfaceHolder.Callback callback;

    @Nullable
    public WeakReference<SurfaceView> currentSurfaceViewRef;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        String simpleName = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(TifOverlaySurfaceHolderCallbackProvider.class)).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        LOG_TAG = simpleName;
    }

    @Inject
    public TifOverlaySurfaceHolderCallbackProvider() {
    }

    public final synchronized void registerRendererCallback(@Nullable SurfaceView surfaceView) {
        SurfaceHolder holder;
        SurfaceHolder holder2;
        try {
            WeakReference<SurfaceView> weakReference = this.currentSurfaceViewRef;
            SurfaceView surfaceView2 = weakReference != null ? weakReference.get() : null;
            if (surfaceView2 == surfaceView) {
                Log.d(LOG_TAG, "Same SurfaceView already registered, skipping");
                return;
            }
            if (this.callback == null) {
                Log.w(LOG_TAG, "registerRendererCallback called but no callback set. Ensure setCallback() is called before registerRendererCallback()");
                return;
            }
            if (surfaceView2 != null && (holder2 = surfaceView2.getHolder()) != null) {
                holder2.removeCallback(this.callback);
            }
            this.currentSurfaceViewRef = surfaceView != null ? new WeakReference<>(surfaceView) : null;
            if (surfaceView != null && (holder = surfaceView.getHolder()) != null) {
                holder.addCallback(this.callback);
            }
            Log.i(LOG_TAG, "Registered renderer callback on SurfaceView: " + surfaceView);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void setCallback(@NotNull SurfaceHolder.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }
}
