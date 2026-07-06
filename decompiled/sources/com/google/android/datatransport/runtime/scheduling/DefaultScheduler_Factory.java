package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultScheduler_Factory implements Factory<DefaultScheduler> {
    public final Provider<BackendRegistry> backendRegistryProvider;
    public final Provider<EventStore> eventStoreProvider;
    public final Provider<Executor> executorProvider;
    public final Provider<SynchronizationGuard> guardProvider;
    public final Provider<WorkScheduler> workSchedulerProvider;

    public DefaultScheduler_Factory(Provider<Executor> provider, Provider<BackendRegistry> provider2, Provider<WorkScheduler> provider3, Provider<EventStore> provider4, Provider<SynchronizationGuard> provider5) {
        this.executorProvider = provider;
        this.backendRegistryProvider = provider2;
        this.workSchedulerProvider = provider3;
        this.eventStoreProvider = provider4;
        this.guardProvider = provider5;
    }

    public static DefaultScheduler_Factory create(Provider<Executor> provider, Provider<BackendRegistry> provider2, Provider<WorkScheduler> provider3, Provider<EventStore> provider4, Provider<SynchronizationGuard> provider5) {
        return new DefaultScheduler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static DefaultScheduler newInstance(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        return new DefaultScheduler(executor, backendRegistry, workScheduler, eventStore, synchronizationGuard);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DefaultScheduler get() {
        return new DefaultScheduler(this.executorProvider.get(), this.backendRegistryProvider.get(), this.workSchedulerProvider.get(), this.eventStoreProvider.get(), this.guardProvider.get());
    }
}
