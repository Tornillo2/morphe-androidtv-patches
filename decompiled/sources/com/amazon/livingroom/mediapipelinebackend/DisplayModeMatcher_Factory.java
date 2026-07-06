package com.amazon.livingroom.mediapipelinebackend;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class DisplayModeMatcher_Factory implements Factory<DisplayModeMatcher> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final DisplayModeMatcher_Factory INSTANCE = new DisplayModeMatcher_Factory();
    }

    public static DisplayModeMatcher_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DisplayModeMatcher newInstance() {
        return new DisplayModeMatcher();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DisplayModeMatcher get() {
        return new DisplayModeMatcher();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new DisplayModeMatcher();
    }
}
