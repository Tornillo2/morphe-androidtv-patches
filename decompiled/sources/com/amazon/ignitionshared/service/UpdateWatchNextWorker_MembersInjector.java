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
public final class UpdateWatchNextWorker_MembersInjector implements MembersInjector<UpdateWatchNextWorker> {
    public final Provider<WatchNextHandler> watchNextHandlerProvider;

    public UpdateWatchNextWorker_MembersInjector(Provider<WatchNextHandler> provider) {
        this.watchNextHandlerProvider = provider;
    }

    public static MembersInjector<UpdateWatchNextWorker> create(Provider<WatchNextHandler> provider) {
        return new UpdateWatchNextWorker_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.UpdateWatchNextWorker.watchNextHandler")
    public static void injectWatchNextHandler(UpdateWatchNextWorker updateWatchNextWorker, WatchNextHandler watchNextHandler) {
        updateWatchNextWorker.watchNextHandler = watchNextHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateWatchNextWorker updateWatchNextWorker) {
        updateWatchNextWorker.watchNextHandler = this.watchNextHandlerProvider.get();
    }
}
