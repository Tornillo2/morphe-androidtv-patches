package com.amazon.ignitionshared;

import com.amazon.livingroom.di.ApplicationScope;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class IgnitionContextProvider {

    @Nullable
    public volatile IgnitionContext context;

    @NotNull
    public final CopyOnWriteArrayList<IgnitionContextListener> listeners = new CopyOnWriteArrayList<>();

    @Inject
    public IgnitionContextProvider() {
    }

    public final void addListener(@NotNull IgnitionContextListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    @Nullable
    public final IgnitionContext getContext() {
        return this.context;
    }

    public final void onContextCreated(@NotNull IgnitionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.context != null) {
            throw new IllegalStateException("Expected context to be null, was: " + this.context);
        }
        this.context = context;
        Iterator<IgnitionContextListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            it.next().onContextCreated(context);
        }
    }

    public final void onContextDestroyed() {
        Iterator<IgnitionContextListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            it.next().onContextDestroyed();
        }
        this.context = null;
    }

    public final void removeListener(@NotNull IgnitionContextListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }

    public final void setContext(@Nullable IgnitionContext ignitionContext) {
        this.context = ignitionContext;
    }
}
