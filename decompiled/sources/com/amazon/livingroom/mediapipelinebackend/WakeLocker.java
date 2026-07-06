package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import android.view.Window;
import com.amazon.ignitionshared.IgnitionContext;
import com.amazon.ignitionshared.IgnitionContextListener;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Function;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class WakeLocker {

    @NotNull
    public final Handler handler;

    @NotNull
    public final IgnitionContextProvider ignitionContextProvider;

    @NotNull
    public Set<Object> wakeLocks;

    @Inject
    public WakeLocker(@NotNull IgnitionContextProvider ignitionContextProvider, @NotNull Handler handler) {
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "ignitionContextProvider");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.ignitionContextProvider = ignitionContextProvider;
        this.handler = handler;
        ignitionContextProvider.addListener(new AnonymousClass1());
        this.wakeLocks = new LinkedHashSet();
    }

    public static final void addWakeLock$lambda$0(WakeLocker wakeLocker, Object obj) {
        if (wakeLocker.wakeLocks.add(obj) && wakeLocker.wakeLocks.size() == 1) {
            wakeLocker.setWakeLock(true);
        }
    }

    public static final void removeWakeLock$lambda$1(WakeLocker wakeLocker, Object obj) {
        if (wakeLocker.wakeLocks.remove(obj) && wakeLocker.wakeLocks.isEmpty()) {
            wakeLocker.setWakeLock(false);
        }
    }

    public final void addWakeLock(@NotNull final Object lock) {
        Intrinsics.checkNotNullParameter(lock, "lock");
        this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.WakeLocker$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WakeLocker.addWakeLock$lambda$0(this.f$0, lock);
            }
        });
    }

    public final void onContextCreated(IgnitionContext ignitionContext) {
        if (this.wakeLocks.isEmpty()) {
            return;
        }
        setWakeLock(ignitionContext, true);
    }

    public final void removeWakeLock(@NotNull final Object lock) {
        Intrinsics.checkNotNullParameter(lock, "lock");
        this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.WakeLocker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WakeLocker.removeWakeLock$lambda$1(this.f$0, lock);
            }
        });
    }

    public final void setWakeLock(IgnitionContext ignitionContext, boolean z) {
        Window window = ignitionContext.window;
        int i = z ? 128 : 0;
        MpbLog.i("Setting keep screen on to " + z);
        window.setFlags(i, 128);
    }

    public final void setWakeLock(boolean z) {
        IgnitionContext ignitionContext = this.ignitionContextProvider.context;
        if (ignitionContext != null) {
            setWakeLock(ignitionContext, z);
        }
    }

    /* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.WakeLocker$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass1 implements IgnitionContextListener, FunctionAdapter {
        public AnonymousClass1() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof IgnitionContextListener) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, WakeLocker.this, WakeLocker.class, "onContextCreated", "onContextCreated(Lcom/amazon/ignitionshared/IgnitionContext;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.amazon.ignitionshared.IgnitionContextListener
        public final void onContextCreated(IgnitionContext p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            WakeLocker.this.onContextCreated(p0);
        }

        @Override // com.amazon.ignitionshared.IgnitionContextListener
        public void onContextDestroyed() {
        }
    }
}
