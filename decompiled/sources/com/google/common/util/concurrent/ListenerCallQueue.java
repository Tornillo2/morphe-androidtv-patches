package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatJellybean;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.util.DesugarCollections;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class ListenerCallQueue<L> {
    public static final LazyLogger logger = new LazyLogger(ListenerCallQueue.class);
    public final List<PerListenerQueue<L>> listeners = DesugarCollections.synchronizedList(new ArrayList());

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Event<L> {
        void call(L listener);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PerListenerQueue<L> implements Runnable {
        public final Executor executor;

        @GuardedBy("this")
        public boolean isThreadScheduled;
        public final L listener;

        @GuardedBy("this")
        public final Queue<Event<L>> waitQueue = new ArrayDeque();

        @GuardedBy("this")
        public final Queue<Object> labelQueue = new ArrayDeque();

        public PerListenerQueue(L listener, Executor executor) {
            listener.getClass();
            this.listener = listener;
            executor.getClass();
            this.executor = executor;
        }

        public synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        public void dispatch() throws Exception {
            boolean z;
            synchronized (this) {
                try {
                    if (this.isThreadScheduled) {
                        z = false;
                    } else {
                        z = true;
                        this.isThreadScheduled = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z) {
                try {
                    this.executor.execute(this);
                } catch (Exception e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.get().log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, (Throwable) e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
        
            r2.call(r9.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x002c, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x002d, code lost:
        
            com.google.common.util.concurrent.ListenerCallQueue.logger.get().log(java.util.logging.Level.SEVERE, "Exception while executing callback: " + r9.listener + org.apache.commons.lang3.StringUtils.SPACE + r3, (java.lang.Throwable) r2);
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                r9 = this;
            L0:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch: java.lang.Throwable -> L2a
                boolean r2 = r9.isThreadScheduled     // Catch: java.lang.Throwable -> L1f
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L1f
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L1f
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L1f
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L1f
                if (r2 != 0) goto L23
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L1f
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L1c
                return
            L1c:
                r1 = move-exception
                r2 = 0
                goto L56
            L1f:
                r2 = move-exception
                r1 = r2
                r2 = 1
                goto L56
            L23:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L1f
                L r4 = r9.listener     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
                r2.call(r4)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
                goto L0
            L2a:
                r2 = move-exception
                goto L5f
            L2c:
                r2 = move-exception
                com.google.common.util.concurrent.LazyLogger r4 = com.google.common.util.concurrent.ListenerCallQueue.access$000()     // Catch: java.lang.Throwable -> L2a
                java.util.logging.Logger r4 = r4.get()     // Catch: java.lang.Throwable -> L2a
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L2a
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2a
                r6.<init>()     // Catch: java.lang.Throwable -> L2a
                java.lang.String r7 = "Exception while executing callback: "
                r6.append(r7)     // Catch: java.lang.Throwable -> L2a
                L r7 = r9.listener     // Catch: java.lang.Throwable -> L2a
                r6.append(r7)     // Catch: java.lang.Throwable -> L2a
                java.lang.String r7 = " "
                r6.append(r7)     // Catch: java.lang.Throwable -> L2a
                r6.append(r3)     // Catch: java.lang.Throwable -> L2a
                java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> L2a
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L2a
                goto L0
            L56:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5d
                throw r1     // Catch: java.lang.Throwable -> L58
            L58:
                r1 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
                goto L5f
            L5d:
                r1 = move-exception
                goto L56
            L5f:
                if (r1 == 0) goto L69
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L66
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L66
                goto L69
            L66:
                r0 = move-exception
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L66
                throw r0
            L69:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(listener, executor));
    }

    public void dispatch() throws Exception {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public final void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(label, NotificationCompatJellybean.KEY_LABEL);
        synchronized (this.listeners) {
            try {
                Iterator<PerListenerQueue<L>> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().add(event, label);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void enqueue(Event<L> event, String label) {
        enqueueHelper(event, label);
    }
}
