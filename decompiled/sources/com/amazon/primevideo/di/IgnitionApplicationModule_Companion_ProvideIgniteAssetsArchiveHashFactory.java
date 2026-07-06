package com.amazon.primevideo.di;

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
@QualifierMetadata({"javax.inject.Named", "com.amazon.livingroom.di.ApplicationContext"})
public final class IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory implements Factory<String> {
    public final Provider<Context> contextProvider;

    public IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory create(Provider<Context> provider) {
        return new IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory(provider);
    }

    public static String provideIgniteAssetsArchiveHash(Context context) {
        return IgnitionApplicationModule.Companion.provideIgniteAssetsArchiveHash(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        return IgnitionApplicationModule.Companion.provideIgniteAssetsArchiveHash(this.contextProvider.get());
    }
}
