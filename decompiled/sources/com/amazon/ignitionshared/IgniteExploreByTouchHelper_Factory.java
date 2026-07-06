package com.amazon.ignitionshared;

import android.view.View;
import com.amazon.ignitionshared.IgniteRenderer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ActivityScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class IgniteExploreByTouchHelper_Factory implements Factory<IgniteExploreByTouchHelper> {
    public final Provider<IgniteRenderer.EventHandler> eventHandlerProvider;
    public final Provider<View> hostProvider;

    public IgniteExploreByTouchHelper_Factory(Provider<View> provider, Provider<IgniteRenderer.EventHandler> provider2) {
        this.hostProvider = provider;
        this.eventHandlerProvider = provider2;
    }

    public static IgniteExploreByTouchHelper_Factory create(Provider<View> provider, Provider<IgniteRenderer.EventHandler> provider2) {
        return new IgniteExploreByTouchHelper_Factory(provider, provider2);
    }

    public static IgniteExploreByTouchHelper newInstance(View view, IgniteRenderer.EventHandler eventHandler) {
        return new IgniteExploreByTouchHelper(view, eventHandler);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public IgniteExploreByTouchHelper get() {
        return new IgniteExploreByTouchHelper(this.hostProvider.get(), this.eventHandlerProvider.get());
    }
}
