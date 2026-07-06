package com.google.common.eventbus;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterators;
import com.google.common.eventbus.Dispatcher;
import com.google.common.util.concurrent.DirectExecutor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class EventBus {
    public static final Logger logger = Logger.getLogger(EventBus.class.getName());
    public final Dispatcher dispatcher;
    public final SubscriberExceptionHandler exceptionHandler;
    public final Executor executor;
    public final String identifier;
    public final SubscriberRegistry subscribers;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LoggingHandler implements SubscriberExceptionHandler {
        public static final LoggingHandler INSTANCE = new LoggingHandler();

        public static Logger logger(SubscriberExceptionContext context) {
            return Logger.getLogger(EventBus.class.getName() + ExternalFourCCMapper.CODEC_NAME_SPLITTER + context.getEventBus().identifier);
        }

        public static String message(SubscriberExceptionContext context) {
            Method subscriberMethod = context.getSubscriberMethod();
            return "Exception thrown by subscriber method " + subscriberMethod.getName() + '(' + subscriberMethod.getParameterTypes()[0].getName() + ") on subscriber " + context.getSubscriber() + " when dispatching event: " + context.getEvent();
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            Logger logger = logger(context);
            Level level = Level.SEVERE;
            if (logger.isLoggable(level)) {
                logger.log(level, message(context), exception);
            }
        }
    }

    public EventBus() {
        this("default");
    }

    public final Executor executor() {
        return this.executor;
    }

    public void handleSubscriberException(Throwable e, SubscriberExceptionContext context) {
        e.getClass();
        context.getClass();
        try {
            this.exceptionHandler.handleException(e, context);
        } catch (Throwable th) {
            logger.log(Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", th, e), th);
        }
    }

    public final String identifier() {
        return this.identifier;
    }

    public void post(Object event) {
        Iterator<Subscriber> subscribers = this.subscribers.getSubscribers(event);
        if (((Iterators.ConcatenatedIterator) subscribers).hasNext()) {
            this.dispatcher.dispatch(event, subscribers);
        } else {
            if (event instanceof DeadEvent) {
                return;
            }
            post(new DeadEvent(this, event));
        }
    }

    public void register(Object object) {
        this.subscribers.register(object);
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.addHolder().value = this.identifier;
        return stringHelper.toString();
    }

    public void unregister(Object object) {
        this.subscribers.unregister(object);
    }

    public EventBus(String identifier, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler) {
        this.subscribers = new SubscriberRegistry(this);
        identifier.getClass();
        this.identifier = identifier;
        executor.getClass();
        this.executor = executor;
        dispatcher.getClass();
        this.dispatcher = dispatcher;
        exceptionHandler.getClass();
        this.exceptionHandler = exceptionHandler;
    }

    public EventBus(SubscriberExceptionHandler exceptionHandler) {
        this("default", DirectExecutor.INSTANCE, new Dispatcher.PerThreadQueuedDispatcher(), exceptionHandler);
    }

    public EventBus(String identifier) {
        this(identifier, DirectExecutor.INSTANCE, new Dispatcher.PerThreadQueuedDispatcher(), LoggingHandler.INSTANCE);
    }
}
