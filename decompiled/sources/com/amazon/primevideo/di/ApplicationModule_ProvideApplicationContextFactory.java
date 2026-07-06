package com.amazon.primevideo.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class ApplicationModule_ProvideApplicationContextFactory implements Factory<Context> {
    public final ApplicationModule module;

    public ApplicationModule_ProvideApplicationContextFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideApplicationContextFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideApplicationContextFactory(applicationModule);
    }

    public static Context provideApplicationContext(ApplicationModule applicationModule) {
        return applicationModule.provideApplicationContext();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Context get() {
        return this.module.provideApplicationContext();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return this.module.provideApplicationContext();
    }
}
