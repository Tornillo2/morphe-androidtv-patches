package org.apache.commons.lang3.concurrent;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Memoizer<I, O> implements Computable<I, O> {
    public final ConcurrentMap<I, Future<O>> cache;
    public final Computable<I, O> computable;
    public final boolean recalculate;

    public Memoizer(Computable<I, O> computable) {
        this(computable, false);
    }

    @Override // org.apache.commons.lang3.concurrent.Computable
    public O compute(final I i) throws InterruptedException {
        FutureTask futureTask;
        while (true) {
            Future<O> futurePutIfAbsent = this.cache.get(i);
            if (futurePutIfAbsent == null && (futurePutIfAbsent = this.cache.putIfAbsent(i, (futureTask = new FutureTask(new Callable<O>() { // from class: org.apache.commons.lang3.concurrent.Memoizer.1
                @Override // java.util.concurrent.Callable
                public O call() throws InterruptedException {
                    return Memoizer.this.computable.compute((I) i);
                }
            })))) == null) {
                futureTask.run();
                futurePutIfAbsent = futureTask;
            }
            try {
                continue;
                return futurePutIfAbsent.get();
            } catch (CancellationException unused) {
                this.cache.remove(i, futurePutIfAbsent);
            } catch (ExecutionException e) {
                if (this.recalculate) {
                    this.cache.remove(i, futurePutIfAbsent);
                }
                throw launderException(e.getCause());
            }
        }
    }

    public final RuntimeException launderException(Throwable th) {
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new IllegalStateException("Unchecked exception", th);
    }

    public Memoizer(Computable<I, O> computable, boolean z) {
        this.cache = new ConcurrentHashMap();
        this.computable = computable;
        this.recalculate = z;
    }
}
