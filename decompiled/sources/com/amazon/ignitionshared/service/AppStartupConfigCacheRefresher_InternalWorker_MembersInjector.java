package com.amazon.ignitionshared.service;

import com.amazon.ignitionshared.service.AppStartupConfigCacheRefresher;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class AppStartupConfigCacheRefresher_InternalWorker_MembersInjector implements MembersInjector<AppStartupConfigCacheRefresher.InternalWorker> {
    public final Provider<AppStartupConfigRequester> requesterProvider;

    public AppStartupConfigCacheRefresher_InternalWorker_MembersInjector(Provider<AppStartupConfigRequester> provider) {
        this.requesterProvider = provider;
    }

    public static MembersInjector<AppStartupConfigCacheRefresher.InternalWorker> create(Provider<AppStartupConfigRequester> provider) {
        return new AppStartupConfigCacheRefresher_InternalWorker_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.AppStartupConfigCacheRefresher.InternalWorker.requester")
    public static void injectRequester(AppStartupConfigCacheRefresher.InternalWorker internalWorker, AppStartupConfigRequester appStartupConfigRequester) {
        internalWorker.requester = appStartupConfigRequester;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AppStartupConfigCacheRefresher.InternalWorker internalWorker) {
        internalWorker.requester = this.requesterProvider.get();
    }
}
