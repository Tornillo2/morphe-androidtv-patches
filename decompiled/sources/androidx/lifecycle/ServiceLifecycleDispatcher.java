package androidx.lifecycle;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceLifecycleDispatcher {

    @NotNull
    public final Handler handler;

    @Nullable
    public DispatchRunnable lastDispatchRunnable;

    @NotNull
    public final LifecycleRegistry registry;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DispatchRunnable implements Runnable {

        @NotNull
        public final Lifecycle.Event event;

        @NotNull
        public final LifecycleRegistry registry;
        public boolean wasExecuted;

        public DispatchRunnable(@NotNull LifecycleRegistry registry, @NotNull Lifecycle.Event event) {
            Intrinsics.checkNotNullParameter(registry, "registry");
            Intrinsics.checkNotNullParameter(event, "event");
            this.registry = registry;
            this.event = event;
        }

        @NotNull
        public final Lifecycle.Event getEvent() {
            return this.event;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wasExecuted) {
                return;
            }
            this.registry.handleLifecycleEvent(this.event);
            this.wasExecuted = true;
        }
    }

    public ServiceLifecycleDispatcher(@NotNull LifecycleOwner provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.registry = new LifecycleRegistry(provider);
        this.handler = new Handler();
    }

    @NotNull
    public Lifecycle getLifecycle() {
        return this.registry;
    }

    public void onServicePreSuperOnBind() {
        postDispatchRunnable(Lifecycle.Event.ON_START);
    }

    public void onServicePreSuperOnCreate() {
        postDispatchRunnable(Lifecycle.Event.ON_CREATE);
    }

    public void onServicePreSuperOnDestroy() {
        postDispatchRunnable(Lifecycle.Event.ON_STOP);
        postDispatchRunnable(Lifecycle.Event.ON_DESTROY);
    }

    public void onServicePreSuperOnStart() {
        postDispatchRunnable(Lifecycle.Event.ON_START);
    }

    public final void postDispatchRunnable(Lifecycle.Event event) {
        DispatchRunnable dispatchRunnable = this.lastDispatchRunnable;
        if (dispatchRunnable != null) {
            dispatchRunnable.run();
        }
        DispatchRunnable dispatchRunnable2 = new DispatchRunnable(this.registry, event);
        this.lastDispatchRunnable = dispatchRunnable2;
        this.handler.postAtFrontOfQueue(dispatchRunnable2);
    }
}
