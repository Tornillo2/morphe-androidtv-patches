package com.google.common.eventbus;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SubscriberExceptionContext {
    public final Object event;
    public final EventBus eventBus;
    public final Object subscriber;
    public final Method subscriberMethod;

    public SubscriberExceptionContext(EventBus eventBus, Object event, Object subscriber, Method subscriberMethod) {
        eventBus.getClass();
        this.eventBus = eventBus;
        event.getClass();
        this.event = event;
        subscriber.getClass();
        this.subscriber = subscriber;
        subscriberMethod.getClass();
        this.subscriberMethod = subscriberMethod;
    }

    public Object getEvent() {
        return this.event;
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public Object getSubscriber() {
        return this.subscriber;
    }

    public Method getSubscriberMethod() {
        return this.subscriberMethod;
    }
}
