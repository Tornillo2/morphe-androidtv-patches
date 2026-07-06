package com.amazon.primevideo.di;

import com.amazon.livingroom.deviceproperties.PlatformProperty;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class ApplicationModule_ProvidePlatformPropertiesFactory implements Factory<List<PlatformProperty<?>>> {
    public final ApplicationModule module;

    public ApplicationModule_ProvidePlatformPropertiesFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidePlatformPropertiesFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidePlatformPropertiesFactory(applicationModule);
    }

    public static List<PlatformProperty<?>> providePlatformProperties(ApplicationModule applicationModule) {
        applicationModule.getClass();
        EmptyList emptyList = EmptyList.INSTANCE;
        Preconditions.checkNotNullFromProvides(emptyList);
        return emptyList;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return providePlatformProperties(this.module);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public List<PlatformProperty<?>> get() {
        return providePlatformProperties(this.module);
    }
}
