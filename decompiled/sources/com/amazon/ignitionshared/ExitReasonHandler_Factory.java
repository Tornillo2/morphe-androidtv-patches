package com.amazon.ignitionshared;

import android.app.ActivityManager;
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
public final class ExitReasonHandler_Factory implements Factory<ExitReasonHandler> {
    public final Provider<ActivityManager> activityManagerProvider;
    public final Provider<Context> contextProvider;

    public ExitReasonHandler_Factory(Provider<Context> provider, Provider<ActivityManager> provider2) {
        this.contextProvider = provider;
        this.activityManagerProvider = provider2;
    }

    public static ExitReasonHandler_Factory create(Provider<Context> provider, Provider<ActivityManager> provider2) {
        return new ExitReasonHandler_Factory(provider, provider2);
    }

    public static ExitReasonHandler newInstance(Context context, ActivityManager activityManager) {
        return new ExitReasonHandler(context, activityManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ExitReasonHandler get() {
        return new ExitReasonHandler(this.contextProvider.get(), this.activityManagerProvider.get());
    }
}
