package com.amazon.ignitionshared.watchnext;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class WatchNextDatabaseAdapter_Factory implements Factory<WatchNextDatabaseAdapter> {
    public final Provider<Context> contextProvider;

    public WatchNextDatabaseAdapter_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static WatchNextDatabaseAdapter_Factory create(Provider<Context> provider) {
        return new WatchNextDatabaseAdapter_Factory(provider);
    }

    public static WatchNextDatabaseAdapter newInstance(Context context) {
        return new WatchNextDatabaseAdapter(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WatchNextDatabaseAdapter get() {
        return new WatchNextDatabaseAdapter(this.contextProvider.get());
    }
}
