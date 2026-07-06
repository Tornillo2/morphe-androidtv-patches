package com.google.common.eventbus;

import j$.util.Objects;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Dispatcher {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ImmediateDispatcher extends Dispatcher {
        public static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            event.getClass();
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LegacyAsyncDispatcher extends Dispatcher {
        public final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class EventWithSubscriber {
            public final Object event;
            public final Subscriber subscriber;

            public EventWithSubscriber(Object event, Subscriber subscriber) {
                this.event = event;
                this.subscriber = subscriber;
            }
        }

        public LegacyAsyncDispatcher() {
            this.queue = new ConcurrentLinkedQueue<>();
        }

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            event.getClass();
            while (subscribers.hasNext()) {
                this.queue.add(new EventWithSubscriber(event, subscribers.next()));
            }
            while (true) {
                EventWithSubscriber eventWithSubscriberPoll = this.queue.poll();
                if (eventWithSubscriberPoll == null) {
                    return;
                } else {
                    eventWithSubscriberPoll.subscriber.dispatchEvent(eventWithSubscriberPoll.event);
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PerThreadQueuedDispatcher extends Dispatcher {
        public final ThreadLocal<Boolean> dispatching;
        public final ThreadLocal<Queue<Event>> queue;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Event {
            public final Object event;
            public final Iterator<Subscriber> subscribers;

            public Event(Object event, Iterator<Subscriber> subscribers) {
                this.event = event;
                this.subscribers = subscribers;
            }
        }

        public PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>() { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.1
                @Override // java.lang.ThreadLocal
                public Queue<Event> initialValue() {
                    return new ArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>() { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.2
                @Override // java.lang.ThreadLocal
                public /* bridge */ /* synthetic */ Boolean initialValue() {
                    return Boolean.FALSE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.lang.ThreadLocal
                public Boolean initialValue() {
                    return Boolean.FALSE;
                }
            };
        }

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            event.getClass();
            subscribers.getClass();
            Queue<Event> queue = this.queue.get();
            Objects.requireNonNull(queue);
            Queue<Event> queue2 = queue;
            queue2.offer(new Event(event, subscribers));
            if (this.dispatching.get().booleanValue()) {
                return;
            }
            this.dispatching.set(Boolean.TRUE);
            while (true) {
                try {
                    Event eventPoll = queue2.poll();
                    if (eventPoll == null) {
                        return;
                    }
                    while (eventPoll.subscribers.hasNext()) {
                        eventPoll.subscribers.next().dispatchEvent(eventPoll.event);
                    }
                } finally {
                    this.dispatching.remove();
                    this.queue.remove();
                }
            }
        }
    }

    public static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    public static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    public static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    public abstract void dispatch(Object event, Iterator<Subscriber> subscribers);
}
