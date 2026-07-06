package com.amazon.livingroom.di;

import android.content.Context;
import android.content.pm.ApplicationInfo;
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
public final class CoreModule_Companion_ProvideApplicationInfoFactory implements Factory<ApplicationInfo> {
    public final Provider<Context> contextProvider;

    public CoreModule_Companion_ProvideApplicationInfoFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CoreModule_Companion_ProvideApplicationInfoFactory create(Provider<Context> provider) {
        return new CoreModule_Companion_ProvideApplicationInfoFactory(provider);
    }

    public static ApplicationInfo provideApplicationInfo(Context context) {
        return CoreModule.Companion.provideApplicationInfo(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ApplicationInfo get() {
        return CoreModule.Companion.provideApplicationInfo(this.contextProvider.get());
    }
}
