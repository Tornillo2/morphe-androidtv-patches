package com.amazon.primevideo.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class ApplicationModule_ProvideIgniteDataDirFactory implements Factory<File> {
    public final ApplicationModule module;

    public ApplicationModule_ProvideIgniteDataDirFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideIgniteDataDirFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideIgniteDataDirFactory(applicationModule);
    }

    public static File provideIgniteDataDir(ApplicationModule applicationModule) {
        return applicationModule.provideIgniteDataDir();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public File get() {
        return this.module.provideIgniteDataDir();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return this.module.provideIgniteDataDir();
    }
}
