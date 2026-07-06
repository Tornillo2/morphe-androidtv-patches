package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EventStoreModule_StoreConfigFactory implements Factory<EventStoreConfig> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final EventStoreModule_StoreConfigFactory INSTANCE = new EventStoreModule_StoreConfigFactory();
    }

    public static EventStoreModule_StoreConfigFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static EventStoreConfig storeConfig() {
        EventStoreConfig eventStoreConfig = EventStoreConfig.DEFAULT;
        Preconditions.checkNotNull(eventStoreConfig, "Cannot return null from a non-@Nullable @Provides method");
        return eventStoreConfig;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public EventStoreConfig get() {
        return storeConfig();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return storeConfig();
    }
}
