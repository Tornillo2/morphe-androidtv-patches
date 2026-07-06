package com.amazon.ignitionshared;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext", "javax.inject.Named"})
public final class CachedTarExtractor_Factory implements Factory<CachedTarExtractor> {
    public final Provider<Context> contextProvider;
    public final Provider<String> fileHashProvider;
    public final Provider<String> fileToExtractProvider;
    public final Provider<String> hashKeyProvider;
    public final Provider<File> outputPathProvider;

    public CachedTarExtractor_Factory(Provider<Context> provider, Provider<String> provider2, Provider<String> provider3, Provider<String> provider4, Provider<File> provider5) {
        this.contextProvider = provider;
        this.fileToExtractProvider = provider2;
        this.fileHashProvider = provider3;
        this.hashKeyProvider = provider4;
        this.outputPathProvider = provider5;
    }

    public static CachedTarExtractor_Factory create(Provider<Context> provider, Provider<String> provider2, Provider<String> provider3, Provider<String> provider4, Provider<File> provider5) {
        return new CachedTarExtractor_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CachedTarExtractor newInstance(Context context, String str, String str2, String str3, File file) {
        return new CachedTarExtractor(context, str, str2, str3, file);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CachedTarExtractor get() {
        return new CachedTarExtractor(this.contextProvider.get(), this.fileToExtractProvider.get(), this.fileHashProvider.get(), this.hashKeyProvider.get(), this.outputPathProvider.get());
    }
}
