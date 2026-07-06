package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DefaultScheduler implements Scheduler {
    public static final Logger LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    public final BackendRegistry backendRegistry;
    public final EventStore eventStore;
    public final Executor executor;
    public final SynchronizationGuard guard;
    public final WorkScheduler workScheduler;

    public static /* synthetic */ Object $r8$lambda$GlqmrSMOOB_fezGeVe6EN3_zTX0(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal eventInternal) {
        defaultScheduler.eventStore.persist(transportContext, eventInternal);
        defaultScheduler.workScheduler.schedule(transportContext, 1);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$e6JsNcl7eJ_uvseaJMOS9AGgb2A(final DefaultScheduler defaultScheduler, final TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        defaultScheduler.getClass();
        try {
            TransportBackend transportBackend = defaultScheduler.backendRegistry.get(transportContext.getBackendName());
            if (transportBackend != null) {
                final EventInternal eventInternalDecorate = transportBackend.decorate(eventInternal);
                defaultScheduler.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.DefaultScheduler$$ExternalSyntheticLambda1
                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        DefaultScheduler.$r8$lambda$GlqmrSMOOB_fezGeVe6EN3_zTX0(this.f$0, transportContext, eventInternalDecorate);
                        return null;
                    }
                });
                transportScheduleCallback.getClass();
            } else {
                String str = String.format("Transport backend '%s' is not registered", transportContext.getBackendName());
                LOGGER.warning(str);
                new IllegalArgumentException(str);
                transportScheduleCallback.getClass();
            }
        } catch (Exception e) {
            LOGGER.warning("Error scheduling event " + e.getMessage());
            transportScheduleCallback.getClass();
        }
    }

    @Inject
    public DefaultScheduler(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        this.executor = executor;
        this.backendRegistry = backendRegistry;
        this.workScheduler = workScheduler;
        this.eventStore = eventStore;
        this.guard = synchronizationGuard;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(final TransportContext transportContext, final EventInternal eventInternal, final TransportScheduleCallback transportScheduleCallback) {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.DefaultScheduler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultScheduler.$r8$lambda$e6JsNcl7eJ_uvseaJMOS9AGgb2A(this.f$0, transportContext, transportScheduleCallback, eventInternal);
            }
        });
    }
}
