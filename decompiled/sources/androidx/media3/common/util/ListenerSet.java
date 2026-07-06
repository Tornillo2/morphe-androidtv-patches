package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CheckResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.media3.common.FlagSet;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ListenerSet<T> {
    public static final int MSG_ITERATION_FINISHED = 0;
    public final Clock clock;
    public final ArrayDeque<Runnable> flushingEvents;
    public final HandlerWrapper handler;
    public final IterationFinishedEvent<T> iterationFinishedEvent;
    public final CopyOnWriteArraySet<ListenerHolder<T>> listeners;
    public final ArrayDeque<Runnable> queuedEvents;

    @GuardedBy("releasedLock")
    public boolean released;
    public final Object releasedLock;
    public boolean throwsWhenUsingWrongThread;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Event<T> {
        void invoke(T t);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface IterationFinishedEvent<T> {
        void invoke(T t, FlagSet flagSet);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ListenerHolder<T> {
        public FlagSet.Builder flagsBuilder = new FlagSet.Builder();
        public final T listener;
        public boolean needsIterationFinishedEvent;
        public boolean released;

        public ListenerHolder(T t) {
            this.listener = t;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ListenerHolder.class != obj.getClass()) {
                return false;
            }
            return this.listener.equals(((ListenerHolder) obj).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        public void invoke(int i, Event<T> event) {
            if (this.released) {
                return;
            }
            if (i != -1) {
                this.flagsBuilder.add(i);
            }
            this.needsIterationFinishedEvent = true;
            event.invoke(this.listener);
        }

        public void iterationFinished(IterationFinishedEvent<T> iterationFinishedEvent) {
            if (this.released || !this.needsIterationFinishedEvent) {
                return;
            }
            FlagSet flagSetBuild = this.flagsBuilder.build();
            this.flagsBuilder = new FlagSet.Builder();
            this.needsIterationFinishedEvent = false;
            iterationFinishedEvent.invoke(this.listener, flagSetBuild);
        }

        public void release(IterationFinishedEvent<T> iterationFinishedEvent) {
            this.released = true;
            if (this.needsIterationFinishedEvent) {
                this.needsIterationFinishedEvent = false;
                iterationFinishedEvent.invoke(this.listener, this.flagsBuilder.build());
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Maws-DUsVhcg5uFQgolq5mcQJiM, reason: not valid java name */
    public static /* synthetic */ void m82$r8$lambda$MawsDUsVhcg5uFQgolq5mcQJiM(CopyOnWriteArraySet copyOnWriteArraySet, int i, Event event) {
        Iterator it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            ((ListenerHolder) it.next()).invoke(i, event);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$rFcF5Pkb99AL585p5-2u78YfNkY, reason: not valid java name */
    public static /* synthetic */ boolean m83$r8$lambda$rFcF5Pkb99AL585p52u78YfNkY(ListenerSet listenerSet, Message message) {
        listenerSet.handleMessage(message);
        return true;
    }

    public ListenerSet(Looper looper, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent) {
        this(new CopyOnWriteArraySet(), looper, clock, iterationFinishedEvent, true);
    }

    public void add(T t) {
        t.getClass();
        synchronized (this.releasedLock) {
            try {
                if (this.released) {
                    return;
                }
                this.listeners.add(new ListenerHolder<>(t));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void clear() {
        verifyCurrentThread();
        this.listeners.clear();
    }

    @CheckResult
    public ListenerSet<T> copy(Looper looper, IterationFinishedEvent<T> iterationFinishedEvent) {
        return copy(looper, this.clock, iterationFinishedEvent);
    }

    public void flushEvents() {
        verifyCurrentThread();
        if (this.queuedEvents.isEmpty()) {
            return;
        }
        if (!this.handler.hasMessages(0)) {
            HandlerWrapper handlerWrapper = this.handler;
            handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(0));
        }
        boolean zIsEmpty = this.flushingEvents.isEmpty();
        this.flushingEvents.addAll(this.queuedEvents);
        this.queuedEvents.clear();
        if (zIsEmpty) {
            while (!this.flushingEvents.isEmpty()) {
                this.flushingEvents.peekFirst().run();
                this.flushingEvents.removeFirst();
            }
        }
    }

    public final boolean handleMessage(Message message) {
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().iterationFinished(this.iterationFinishedEvent);
            if (this.handler.hasMessages(0)) {
                return true;
            }
        }
        return true;
    }

    public void queueEvent(final int i, final Event<T> event) {
        verifyCurrentThread();
        final CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet(this.listeners);
        this.queuedEvents.add(new Runnable() { // from class: androidx.media3.common.util.ListenerSet$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ListenerSet.m82$r8$lambda$MawsDUsVhcg5uFQgolq5mcQJiM(copyOnWriteArraySet, i, event);
            }
        });
    }

    public void release() {
        verifyCurrentThread();
        synchronized (this.releasedLock) {
            this.released = true;
        }
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().release(this.iterationFinishedEvent);
        }
        this.listeners.clear();
    }

    public void remove(T t) {
        verifyCurrentThread();
        for (ListenerHolder<T> listenerHolder : this.listeners) {
            if (listenerHolder.listener.equals(t)) {
                listenerHolder.release(this.iterationFinishedEvent);
                this.listeners.remove(listenerHolder);
            }
        }
    }

    public void sendEvent(int i, Event<T> event) {
        queueEvent(i, event);
        flushEvents();
    }

    @Deprecated
    public void setThrowsWhenUsingWrongThread(boolean z) {
        this.throwsWhenUsingWrongThread = z;
    }

    public int size() {
        verifyCurrentThread();
        return this.listeners.size();
    }

    public final void verifyCurrentThread() {
        if (this.throwsWhenUsingWrongThread) {
            Assertions.checkState(Thread.currentThread() == this.handler.getLooper().getThread());
        }
    }

    public ListenerSet(CopyOnWriteArraySet<ListenerHolder<T>> copyOnWriteArraySet, Looper looper, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent, boolean z) {
        this.clock = clock;
        this.listeners = copyOnWriteArraySet;
        this.iterationFinishedEvent = iterationFinishedEvent;
        this.releasedLock = new Object();
        this.flushingEvents = new ArrayDeque<>();
        this.queuedEvents = new ArrayDeque<>();
        this.handler = clock.createHandler(looper, new Handler.Callback() { // from class: androidx.media3.common.util.ListenerSet$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                this.f$0.handleMessage(message);
                return true;
            }
        });
        this.throwsWhenUsingWrongThread = z;
    }

    @CheckResult
    public ListenerSet<T> copy(Looper looper, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent) {
        return new ListenerSet<>(this.listeners, looper, clock, iterationFinishedEvent, this.throwsWhenUsingWrongThread);
    }
}
