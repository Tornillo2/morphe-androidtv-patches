package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EventStoreModule_DbNameFactory implements Factory<String> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final EventStoreModule_DbNameFactory INSTANCE = new EventStoreModule_DbNameFactory();
    }

    public static EventStoreModule_DbNameFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String dbName() {
        return SchemaManager.DB_NAME;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return SchemaManager.DB_NAME;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        return SchemaManager.DB_NAME;
    }
}
