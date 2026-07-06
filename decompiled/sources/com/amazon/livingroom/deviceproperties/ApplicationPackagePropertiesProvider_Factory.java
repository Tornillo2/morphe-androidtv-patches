package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class ApplicationPackagePropertiesProvider_Factory implements Factory<ApplicationPackagePropertiesProvider> {
    public final Provider<Context> contextProvider;
    public final Provider<PackageManager> packageManagerProvider;

    public ApplicationPackagePropertiesProvider_Factory(Provider<Context> provider, Provider<PackageManager> provider2) {
        this.contextProvider = provider;
        this.packageManagerProvider = provider2;
    }

    public static ApplicationPackagePropertiesProvider_Factory create(Provider<Context> provider, Provider<PackageManager> provider2) {
        return new ApplicationPackagePropertiesProvider_Factory(provider, provider2);
    }

    public static ApplicationPackagePropertiesProvider newInstance(Context context, PackageManager packageManager) {
        return new ApplicationPackagePropertiesProvider(context, packageManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ApplicationPackagePropertiesProvider get() {
        return new ApplicationPackagePropertiesProvider(this.contextProvider.get(), this.packageManagerProvider.get());
    }
}
