package com.amazon.ignitionshared.filesystem;

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
@QualifierMetadata({"javax.inject.Named"})
public final class LocalStorage_Factory implements Factory<LocalStorage> {
    public final Provider<File> igniteDataDirProvider;

    public LocalStorage_Factory(Provider<File> provider) {
        this.igniteDataDirProvider = provider;
    }

    public static LocalStorage_Factory create(Provider<File> provider) {
        return new LocalStorage_Factory(provider);
    }

    public static LocalStorage newInstance(File file) {
        return new LocalStorage(file);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public LocalStorage get() {
        return new LocalStorage(this.igniteDataDirProvider.get());
    }
}
