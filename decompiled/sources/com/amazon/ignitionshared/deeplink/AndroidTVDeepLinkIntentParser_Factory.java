package com.amazon.ignitionshared.deeplink;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class AndroidTVDeepLinkIntentParser_Factory implements Factory<AndroidTVDeepLinkIntentParser> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final AndroidTVDeepLinkIntentParser_Factory INSTANCE = new AndroidTVDeepLinkIntentParser_Factory();
    }

    public static AndroidTVDeepLinkIntentParser_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static AndroidTVDeepLinkIntentParser newInstance() {
        return new AndroidTVDeepLinkIntentParser();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AndroidTVDeepLinkIntentParser get() {
        return new AndroidTVDeepLinkIntentParser();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new AndroidTVDeepLinkIntentParser();
    }
}
