package com.amazon.ignitionshared;

import com.amazon.ignitionshared.IgniteRenderer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class IgniteRenderer_EventHandler_Factory implements Factory<IgniteRenderer.EventHandler> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgniteRenderer_EventHandler_Factory INSTANCE = new IgniteRenderer_EventHandler_Factory();
    }

    public static IgniteRenderer_EventHandler_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static IgniteRenderer.EventHandler newInstance() {
        return new IgniteRenderer.EventHandler();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public IgniteRenderer.EventHandler get() {
        return new IgniteRenderer.EventHandler();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new IgniteRenderer.EventHandler();
    }
}
