package com.amazon.livingroom.auth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class RefreshTokenParser_Factory implements Factory<RefreshTokenParser> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final RefreshTokenParser_Factory INSTANCE = new RefreshTokenParser_Factory();
    }

    public static RefreshTokenParser_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RefreshTokenParser newInstance() {
        return new RefreshTokenParser();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RefreshTokenParser get() {
        return new RefreshTokenParser();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new RefreshTokenParser();
    }
}
