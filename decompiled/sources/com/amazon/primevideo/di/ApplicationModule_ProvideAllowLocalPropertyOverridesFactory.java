package com.amazon.primevideo.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class ApplicationModule_ProvideAllowLocalPropertyOverridesFactory implements Factory<Boolean> {
    public final ApplicationModule module;

    public ApplicationModule_ProvideAllowLocalPropertyOverridesFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideAllowLocalPropertyOverridesFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideAllowLocalPropertyOverridesFactory(applicationModule);
    }

    public static boolean provideAllowLocalPropertyOverrides(ApplicationModule applicationModule) {
        applicationModule.getClass();
        return false;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        get();
        return Boolean.FALSE;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        this.module.getClass();
        return Boolean.FALSE;
    }
}
