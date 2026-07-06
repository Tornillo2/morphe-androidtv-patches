package com.google.common.eventbus;

import com.google.common.eventbus.Dispatcher;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String identifier, Executor executor) {
        super(identifier, executor, new Dispatcher.LegacyAsyncDispatcher(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor) {
        super("default", executor, new Dispatcher.LegacyAsyncDispatcher(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super("default", executor, new Dispatcher.LegacyAsyncDispatcher(), subscriberExceptionHandler);
    }
}
