package com.amazon.livingroom.di;

import android.content.Context;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
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
public final class CoreModule_Companion_ProvideMinervaClientFactory implements Factory<AmazonMinerva> {
    public final Provider<Context> contextProvider;
    public final Provider<PlatformDeviceProperties> devicePropertiesProvider;

    public CoreModule_Companion_ProvideMinervaClientFactory(Provider<Context> provider, Provider<PlatformDeviceProperties> provider2) {
        this.contextProvider = provider;
        this.devicePropertiesProvider = provider2;
    }

    public static CoreModule_Companion_ProvideMinervaClientFactory create(Provider<Context> provider, Provider<PlatformDeviceProperties> provider2) {
        return new CoreModule_Companion_ProvideMinervaClientFactory(provider, provider2);
    }

    public static AmazonMinerva provideMinervaClient(Context context, PlatformDeviceProperties platformDeviceProperties) {
        return CoreModule.Companion.provideMinervaClient(context, platformDeviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AmazonMinerva get() {
        return CoreModule.Companion.provideMinervaClient(this.contextProvider.get(), this.devicePropertiesProvider.get());
    }
}
