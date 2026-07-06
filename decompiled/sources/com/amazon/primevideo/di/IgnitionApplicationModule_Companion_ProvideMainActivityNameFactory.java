package com.amazon.primevideo.di;

import android.content.ComponentName;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class IgnitionApplicationModule_Companion_ProvideMainActivityNameFactory implements Factory<ComponentName> {
    public final Provider<String> applicationPackageNameProvider;

    public IgnitionApplicationModule_Companion_ProvideMainActivityNameFactory(Provider<String> provider) {
        this.applicationPackageNameProvider = provider;
    }

    public static IgnitionApplicationModule_Companion_ProvideMainActivityNameFactory create(Provider<String> provider) {
        return new IgnitionApplicationModule_Companion_ProvideMainActivityNameFactory(provider);
    }

    public static ComponentName provideMainActivityName(String str) {
        return IgnitionApplicationModule.Companion.provideMainActivityName(str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ComponentName get() {
        return IgnitionApplicationModule.Companion.provideMainActivityName(this.applicationPackageNameProvider.get());
    }
}
