package com.amazon.ignitionshared;

import com.amazon.livingroom.di.ApplicationScope;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nLifecycleBoundHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LifecycleBoundHandler.kt\ncom/amazon/ignitionshared/LifecycleBoundHandler\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,39:1\n1869#2,2:40\n*S KotlinDebug\n*F\n+ 1 LifecycleBoundHandler.kt\ncom/amazon/ignitionshared/LifecycleBoundHandler\n*L\n36#1:40,2\n*E\n"})
public final class LifecycleBoundHandler {
    public boolean interrupted;

    @NotNull
    public final List<Thread> runningThreads = new ArrayList();

    @Inject
    public LifecycleBoundHandler() {
    }

    public final <T> T run(@NotNull Function0<? extends T> runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        synchronized (this) {
            if (this.interrupted) {
                throw new InterruptedException();
            }
            List<Thread> list = this.runningThreads;
            Thread threadCurrentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "currentThread(...)");
            list.add(threadCurrentThread);
        }
        T tInvoke = runnable.invoke();
        synchronized (this) {
            this.runningThreads.remove(Thread.currentThread());
        }
        return tInvoke;
    }

    public final void setInterrupted(boolean z) {
        List list;
        synchronized (this) {
            this.interrupted = z;
            list = CollectionsKt___CollectionsKt.toList(this.runningThreads);
            this.runningThreads.clear();
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Thread) it.next()).interrupt();
        }
    }
}
