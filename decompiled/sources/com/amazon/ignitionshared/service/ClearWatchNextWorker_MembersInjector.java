package com.amazon.ignitionshared.service;

import com.amazon.ignitionshared.watchnext.WatchNextHandler;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class ClearWatchNextWorker_MembersInjector implements MembersInjector<ClearWatchNextWorker> {
    public final Provider<WatchNextHandler> watchNextHandlerProvider;

    public ClearWatchNextWorker_MembersInjector(Provider<WatchNextHandler> provider) {
        this.watchNextHandlerProvider = provider;
    }

    public static MembersInjector<ClearWatchNextWorker> create(Provider<WatchNextHandler> provider) {
        return new ClearWatchNextWorker_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.ClearWatchNextWorker.watchNextHandler")
    public static void injectWatchNextHandler(ClearWatchNextWorker clearWatchNextWorker, WatchNextHandler watchNextHandler) {
        clearWatchNextWorker.watchNextHandler = watchNextHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ClearWatchNextWorker clearWatchNextWorker) {
        clearWatchNextWorker.watchNextHandler = this.watchNextHandlerProvider.get();
    }
}
