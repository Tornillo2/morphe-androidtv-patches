package com.amazon.ignitionshared.pear;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class PearResponseParser_Factory implements Factory<PearResponseParser> {
    public final Provider<String> recommendationApplicationNameProvider;

    public PearResponseParser_Factory(Provider<String> provider) {
        this.recommendationApplicationNameProvider = provider;
    }

    public static PearResponseParser_Factory create(Provider<String> provider) {
        return new PearResponseParser_Factory(provider);
    }

    public static PearResponseParser newInstance(String str) {
        return new PearResponseParser(str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearResponseParser get() {
        return new PearResponseParser(this.recommendationApplicationNameProvider.get());
    }
}
