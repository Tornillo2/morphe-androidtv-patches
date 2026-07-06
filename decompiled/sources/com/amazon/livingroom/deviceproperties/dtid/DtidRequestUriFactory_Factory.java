package com.amazon.livingroom.deviceproperties.dtid;

import com.amazon.ignitionshared.network.TerminatorIdProvider;
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
public final class DtidRequestUriFactory_Factory implements Factory<DtidRequestUriFactory> {
    public final Provider<String> acmConfigNameProvider;
    public final Provider<String> defaultDtidProvider;
    public final Provider<TerminatorIdProvider> terminatorIdProvider;

    public DtidRequestUriFactory_Factory(Provider<String> provider, Provider<String> provider2, Provider<TerminatorIdProvider> provider3) {
        this.defaultDtidProvider = provider;
        this.acmConfigNameProvider = provider2;
        this.terminatorIdProvider = provider3;
    }

    public static DtidRequestUriFactory_Factory create(Provider<String> provider, Provider<String> provider2, Provider<TerminatorIdProvider> provider3) {
        return new DtidRequestUriFactory_Factory(provider, provider2, provider3);
    }

    public static DtidRequestUriFactory newInstance(String str, String str2, TerminatorIdProvider terminatorIdProvider) {
        return new DtidRequestUriFactory(str, str2, terminatorIdProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DtidRequestUriFactory get() {
        return new DtidRequestUriFactory(this.defaultDtidProvider.get(), this.acmConfigNameProvider.get(), this.terminatorIdProvider.get());
    }
}
