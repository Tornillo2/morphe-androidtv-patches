package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.Synchronized;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.time.Duration;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Queues {
    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    @IgnoreJRERequirement
    public static <E> int drain(BlockingQueue<E> q, Collection<? super E> buffer, int numElements, Duration timeout) throws InterruptedException {
        return drain(q, buffer, numElements, timeout.toNanos(), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    @IgnoreJRERequirement
    public static <E> int drainUninterruptibly(BlockingQueue<E> q, Collection<? super E> buffer, int numElements, Duration timeout) {
        return drainUninterruptibly(q, buffer, numElements, timeout.toNanos(), TimeUnit.NANOSECONDS);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> ArrayBlockingQueue<E> newArrayBlockingQueue(int capacity) {
        return new ArrayBlockingQueue<>(capacity);
    }

    public static <E> ArrayDeque<E> newArrayDeque() {
        return new ArrayDeque<>();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> ConcurrentLinkedQueue<E> newConcurrentLinkedQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingDeque<E> newLinkedBlockingDeque() {
        return new LinkedBlockingDeque<>();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingQueue<E> newLinkedBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E extends Comparable> PriorityBlockingQueue<E> newPriorityBlockingQueue() {
        return new PriorityBlockingQueue<>();
    }

    public static <E extends Comparable> PriorityQueue<E> newPriorityQueue() {
        return new PriorityQueue<>();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> SynchronousQueue<E> newSynchronousQueue() {
        return new SynchronousQueue<>();
    }

    @J2ktIncompatible
    public static <E> Deque<E> synchronizedDeque(Deque<E> deque) {
        return new Synchronized.SynchronizedDeque((Object) deque, (Object) null);
    }

    @J2ktIncompatible
    public static <E> Queue<E> synchronizedQueue(Queue<E> queue) {
        return Synchronized.queue(queue, null);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static <E> int drain(BlockingQueue<E> q, Collection<? super E> buffer, int numElements, long timeout, TimeUnit unit) throws InterruptedException {
        buffer.getClass();
        long nanos = unit.toNanos(timeout) + System.nanoTime();
        int iDrainTo = 0;
        while (iDrainTo < numElements) {
            iDrainTo += q.drainTo(buffer, numElements - iDrainTo);
            if (iDrainTo < numElements) {
                E ePoll = q.poll(nanos - System.nanoTime(), TimeUnit.NANOSECONDS);
                if (ePoll == null) {
                    return iDrainTo;
                }
                buffer.add(ePoll);
                iDrainTo++;
            }
        }
        return iDrainTo;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static <E> int drainUninterruptibly(BlockingQueue<E> q, Collection<? super E> buffer, int numElements, long timeout, TimeUnit unit) {
        E ePoll;
        buffer.getClass();
        long nanos = unit.toNanos(timeout) + System.nanoTime();
        int iDrainTo = 0;
        boolean z = false;
        while (iDrainTo < numElements) {
            try {
                iDrainTo += q.drainTo(buffer, numElements - iDrainTo);
                if (iDrainTo < numElements) {
                    while (true) {
                        try {
                            ePoll = q.poll(nanos - System.nanoTime(), TimeUnit.NANOSECONDS);
                            break;
                        } catch (InterruptedException unused) {
                            z = true;
                        }
                    }
                    if (ePoll == null) {
                        break;
                    }
                    buffer.add(ePoll);
                    iDrainTo++;
                }
            } finally {
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return iDrainTo;
    }

    public static <E> ArrayDeque<E> newArrayDeque(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new ArrayDeque<>((Collection) elements);
        }
        ArrayDeque<E> arrayDeque = new ArrayDeque<>();
        Iterables.addAll(arrayDeque, elements);
        return arrayDeque;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> ConcurrentLinkedQueue<E> newConcurrentLinkedQueue(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new ConcurrentLinkedQueue<>((Collection) elements);
        }
        ConcurrentLinkedQueue<E> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        Iterables.addAll(concurrentLinkedQueue, elements);
        return concurrentLinkedQueue;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingDeque<E> newLinkedBlockingDeque(int capacity) {
        return new LinkedBlockingDeque<>(capacity);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingQueue<E> newLinkedBlockingQueue(int capacity) {
        return new LinkedBlockingQueue<>(capacity);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E extends Comparable> PriorityBlockingQueue<E> newPriorityBlockingQueue(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new PriorityBlockingQueue<>((Collection) elements);
        }
        PriorityBlockingQueue<E> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Iterables.addAll(priorityBlockingQueue, elements);
        return priorityBlockingQueue;
    }

    public static <E extends Comparable> PriorityQueue<E> newPriorityQueue(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new PriorityQueue<>((Collection) elements);
        }
        PriorityQueue<E> priorityQueue = new PriorityQueue<>();
        Iterables.addAll(priorityQueue, elements);
        return priorityQueue;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingDeque<E> newLinkedBlockingDeque(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new LinkedBlockingDeque<>((Collection) elements);
        }
        LinkedBlockingDeque<E> linkedBlockingDeque = new LinkedBlockingDeque<>();
        Iterables.addAll(linkedBlockingDeque, elements);
        return linkedBlockingDeque;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> LinkedBlockingQueue<E> newLinkedBlockingQueue(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new LinkedBlockingQueue<>((Collection) elements);
        }
        LinkedBlockingQueue<E> linkedBlockingQueue = new LinkedBlockingQueue<>();
        Iterables.addAll(linkedBlockingQueue, elements);
        return linkedBlockingQueue;
    }
}
