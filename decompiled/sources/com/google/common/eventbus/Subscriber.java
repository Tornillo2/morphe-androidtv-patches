package com.google.common.eventbus;

import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import com.google.common.annotations.VisibleForTesting;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Subscriber {

    @Weak
    public EventBus bus;
    public final Executor executor;
    public final Method method;

    @VisibleForTesting
    public final Object target;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class SynchronizedSubscriber extends Subscriber {
        public SynchronizedSubscriber(EventBus bus, Object target, Method method) {
            super(bus, target, method);
        }

        @Override // com.google.common.eventbus.Subscriber
        public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
            synchronized (this) {
                super.invokeSubscriberMethod(event);
            }
        }

        public SynchronizedSubscriber(EventBus eventBus, Object obj, Method method, AnonymousClass1 anonymousClass1) {
            super(eventBus, obj, method);
        }
    }

    public static /* synthetic */ void $r8$lambda$5RMZHSoK_8g4urG8TLFCZfFcbUk(Subscriber subscriber, Object obj) {
        subscriber.getClass();
        try {
            subscriber.invokeSubscriberMethod(obj);
        } catch (InvocationTargetException e) {
            subscriber.bus.handleSubscriberException(e.getCause(), subscriber.context(obj));
        }
    }

    public static Subscriber create(EventBus bus, Object listener, Method method) {
        return isDeclaredThreadSafe(method) ? new Subscriber(bus, listener, method) : new SynchronizedSubscriber(bus, listener, method);
    }

    public static boolean isDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    public final SubscriberExceptionContext context(Object event) {
        return new SubscriberExceptionContext(this.bus, event, this.target, this.method);
    }

    public final void dispatchEvent(final Object event) {
        this.executor.execute(new Runnable() { // from class: com.google.common.eventbus.Subscriber$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Subscriber.$r8$lambda$5RMZHSoK_8g4urG8TLFCZfFcbUk(this.f$0, event);
            }
        });
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Subscriber) {
            Subscriber subscriber = (Subscriber) obj;
            if (this.target == subscriber.target && this.method.equals(subscriber.method)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this.target) + ((this.method.hashCode() + 31) * 31);
    }

    @VisibleForTesting
    public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
        try {
            Method method = this.method;
            Object obj = this.target;
            event.getClass();
            method.invoke(obj, event);
        } catch (IllegalAccessException e) {
            throw new Error(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Method became inaccessible: ", event), e);
        } catch (IllegalArgumentException e2) {
            throw new Error(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Method rejected target/argument: ", event), e2);
        } catch (InvocationTargetException e3) {
            if (!(e3.getCause() instanceof Error)) {
                throw e3;
            }
            throw ((Error) e3.getCause());
        }
    }

    public Subscriber(EventBus bus, Object target, Method method) {
        this.bus = bus;
        target.getClass();
        this.target = target;
        this.method = method;
        method.setAccessible(true);
        this.executor = bus.executor;
    }
}
