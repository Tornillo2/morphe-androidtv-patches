package com.amazon.ignitionshared.service;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.dtid.DtidCache;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequester;
import com.amazon.livingroom.di.Names;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class DtidRequestOnStartupWorker_MembersInjector implements MembersInjector<DtidRequestOnStartupWorker> {
    public final Provider<String> defaultDtidProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<DtidCache> dtidCacheProvider;
    public final Provider<DtidRequester> dtidRequesterProvider;

    public DtidRequestOnStartupWorker_MembersInjector(Provider<DtidRequester> provider, Provider<DtidCache> provider2, Provider<String> provider3, Provider<DeviceProperties> provider4) {
        this.dtidRequesterProvider = provider;
        this.dtidCacheProvider = provider2;
        this.defaultDtidProvider = provider3;
        this.devicePropertiesProvider = provider4;
    }

    public static MembersInjector<DtidRequestOnStartupWorker> create(Provider<DtidRequester> provider, Provider<DtidCache> provider2, Provider<String> provider3, Provider<DeviceProperties> provider4) {
        return new DtidRequestOnStartupWorker_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.DtidRequestOnStartupWorker.defaultDtid")
    @Named(Names.IGNITION_APPLICATION_DEFAULT_DTID)
    public static void injectDefaultDtid(DtidRequestOnStartupWorker dtidRequestOnStartupWorker, String str) {
        dtidRequestOnStartupWorker.defaultDtid = str;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.DtidRequestOnStartupWorker.deviceProperties")
    @Named(Names.NON_OVERRIDABLE)
    public static void injectDeviceProperties(DtidRequestOnStartupWorker dtidRequestOnStartupWorker, DeviceProperties deviceProperties) {
        dtidRequestOnStartupWorker.deviceProperties = deviceProperties;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.DtidRequestOnStartupWorker.dtidCache")
    public static void injectDtidCache(DtidRequestOnStartupWorker dtidRequestOnStartupWorker, DtidCache dtidCache) {
        dtidRequestOnStartupWorker.dtidCache = dtidCache;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.DtidRequestOnStartupWorker.dtidRequester")
    public static void injectDtidRequester(DtidRequestOnStartupWorker dtidRequestOnStartupWorker, DtidRequester dtidRequester) {
        dtidRequestOnStartupWorker.dtidRequester = dtidRequester;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DtidRequestOnStartupWorker dtidRequestOnStartupWorker) {
        dtidRequestOnStartupWorker.dtidRequester = this.dtidRequesterProvider.get();
        dtidRequestOnStartupWorker.dtidCache = this.dtidCacheProvider.get();
        dtidRequestOnStartupWorker.defaultDtid = this.defaultDtidProvider.get();
        dtidRequestOnStartupWorker.deviceProperties = this.devicePropertiesProvider.get();
    }
}
