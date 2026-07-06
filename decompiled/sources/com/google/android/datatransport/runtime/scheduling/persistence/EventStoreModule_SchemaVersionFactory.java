package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EventStoreModule_SchemaVersionFactory implements Factory<Integer> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final EventStoreModule_SchemaVersionFactory INSTANCE = new EventStoreModule_SchemaVersionFactory();
    }

    public static EventStoreModule_SchemaVersionFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Integer get() {
        return Integer.valueOf(SchemaManager.SCHEMA_VERSION);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return Integer.valueOf(SchemaManager.SCHEMA_VERSION);
    }
}
