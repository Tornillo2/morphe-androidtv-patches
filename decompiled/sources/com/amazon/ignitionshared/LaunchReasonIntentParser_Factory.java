package com.amazon.ignitionshared;

import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class LaunchReasonIntentParser_Factory implements Factory<LaunchReasonIntentParser> {
    public final Provider<DeepLinkIntentParser> deepLinkIntentParserProvider;

    public LaunchReasonIntentParser_Factory(Provider<DeepLinkIntentParser> provider) {
        this.deepLinkIntentParserProvider = provider;
    }

    public static LaunchReasonIntentParser_Factory create(Provider<DeepLinkIntentParser> provider) {
        return new LaunchReasonIntentParser_Factory(provider);
    }

    public static LaunchReasonIntentParser newInstance(DeepLinkIntentParser deepLinkIntentParser) {
        return new LaunchReasonIntentParser(deepLinkIntentParser);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public LaunchReasonIntentParser get() {
        return new LaunchReasonIntentParser(this.deepLinkIntentParserProvider.get());
    }
}
