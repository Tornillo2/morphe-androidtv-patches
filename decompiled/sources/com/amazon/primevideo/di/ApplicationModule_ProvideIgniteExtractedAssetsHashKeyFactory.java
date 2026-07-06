package com.amazon.primevideo.di;

import com.amazon.ignitionshared.EngineAssetsHashKeys;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory implements Factory<String> {
    public final ApplicationModule module;

    public ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory(applicationModule);
    }

    public static String provideIgniteExtractedAssetsHashKey(ApplicationModule applicationModule) {
        applicationModule.getClass();
        return EngineAssetsHashKeys.IGNITE_ASSETS_HASH_KEY;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        get();
        return EngineAssetsHashKeys.IGNITE_ASSETS_HASH_KEY;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        provideIgniteExtractedAssetsHashKey(this.module);
        return EngineAssetsHashKeys.IGNITE_ASSETS_HASH_KEY;
    }
}
