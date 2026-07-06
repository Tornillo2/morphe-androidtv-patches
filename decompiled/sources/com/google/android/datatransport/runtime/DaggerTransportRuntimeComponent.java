package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.ExecutionModule_ExecutorFactory;
import com.google.android.datatransport.runtime.TransportRuntimeComponent;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_PackageNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {
    public Provider<SchedulerConfig> configProvider;
    public Provider creationContextFactoryProvider;
    public Provider<DefaultScheduler> defaultSchedulerProvider;
    public Provider<Executor> executorProvider;
    public Provider metadataBackendRegistryProvider;
    public Provider<String> packageNameProvider;
    public Provider<SQLiteEventStore> sQLiteEventStoreProvider;
    public Provider schemaManagerProvider;
    public Provider<Context> setApplicationContextProvider;
    public Provider<TransportRuntime> transportRuntimeProvider;
    public Provider<Uploader> uploaderProvider;
    public Provider<WorkInitializer> workInitializerProvider;
    public Provider<WorkScheduler> workSchedulerProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder implements TransportRuntimeComponent.Builder {
        public Context setApplicationContext;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new DaggerTransportRuntimeComponent(this.setApplicationContext);
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public Builder setApplicationContext(Context context) {
            context.getClass();
            this.setApplicationContext = context;
            return this;
        }

        public Builder(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent.Builder setApplicationContext(Context context) {
            context.getClass();
            this.setApplicationContext = context;
            return this;
        }
    }

    public static TransportRuntimeComponent.Builder builder() {
        return new Builder();
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    public EventStore getEventStore() {
        return this.sQLiteEventStoreProvider.get();
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    public TransportRuntime getTransportRuntime() {
        return this.transportRuntimeProvider.get();
    }

    public final void initialize(Context context) {
        this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.InstanceHolder.INSTANCE);
        Factory factoryCreate = InstanceFactory.create(context);
        this.setApplicationContextProvider = factoryCreate;
        TimeModule_EventClockFactory timeModule_EventClockFactory = TimeModule_EventClockFactory.InstanceHolder.INSTANCE;
        TimeModule_UptimeClockFactory timeModule_UptimeClockFactory = TimeModule_UptimeClockFactory.InstanceHolder.INSTANCE;
        CreationContextFactory_Factory creationContextFactory_Factory = new CreationContextFactory_Factory(factoryCreate, timeModule_EventClockFactory, timeModule_UptimeClockFactory);
        this.creationContextFactoryProvider = creationContextFactory_Factory;
        this.metadataBackendRegistryProvider = DoubleCheck.provider(new MetadataBackendRegistry_Factory(factoryCreate, creationContextFactory_Factory));
        Provider<Context> provider = this.setApplicationContextProvider;
        this.schemaManagerProvider = new SchemaManager_Factory(provider, EventStoreModule_DbNameFactory.InstanceHolder.INSTANCE, EventStoreModule_SchemaVersionFactory.InstanceHolder.INSTANCE);
        Provider<String> provider2 = DoubleCheck.provider(new EventStoreModule_PackageNameFactory(provider));
        this.packageNameProvider = provider2;
        Provider<SQLiteEventStore> provider3 = DoubleCheck.provider(new SQLiteEventStore_Factory(timeModule_EventClockFactory, timeModule_UptimeClockFactory, EventStoreModule_StoreConfigFactory.InstanceHolder.INSTANCE, this.schemaManagerProvider, provider2));
        this.sQLiteEventStoreProvider = provider3;
        SchedulingConfigModule_ConfigFactory schedulingConfigModule_ConfigFactory = new SchedulingConfigModule_ConfigFactory(timeModule_EventClockFactory);
        this.configProvider = schedulingConfigModule_ConfigFactory;
        Provider<Context> provider4 = this.setApplicationContextProvider;
        SchedulingModule_WorkSchedulerFactory schedulingModule_WorkSchedulerFactory = new SchedulingModule_WorkSchedulerFactory(provider4, provider3, schedulingConfigModule_ConfigFactory, timeModule_UptimeClockFactory);
        this.workSchedulerProvider = schedulingModule_WorkSchedulerFactory;
        Provider<Executor> provider5 = this.executorProvider;
        Provider provider6 = this.metadataBackendRegistryProvider;
        DefaultScheduler_Factory defaultScheduler_Factory = new DefaultScheduler_Factory(provider5, provider6, schedulingModule_WorkSchedulerFactory, provider3, provider3);
        this.defaultSchedulerProvider = defaultScheduler_Factory;
        Uploader_Factory uploader_Factory = new Uploader_Factory(provider4, provider6, provider3, schedulingModule_WorkSchedulerFactory, provider5, provider3, timeModule_EventClockFactory, timeModule_UptimeClockFactory, provider3);
        this.uploaderProvider = uploader_Factory;
        WorkInitializer_Factory workInitializer_Factory = new WorkInitializer_Factory(provider5, provider3, schedulingModule_WorkSchedulerFactory, provider3);
        this.workInitializerProvider = workInitializer_Factory;
        this.transportRuntimeProvider = DoubleCheck.provider(new TransportRuntime_Factory(timeModule_EventClockFactory, timeModule_UptimeClockFactory, defaultScheduler_Factory, uploader_Factory, workInitializer_Factory));
    }

    public DaggerTransportRuntimeComponent(Context context) {
        initialize(context);
    }
}
