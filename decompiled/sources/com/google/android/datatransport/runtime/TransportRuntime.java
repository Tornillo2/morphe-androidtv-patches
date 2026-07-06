package com.google.android.datatransport.runtime;

import android.content.Context;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import com.google.android.datatransport.runtime.DaggerTransportRuntimeComponent;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Singleton
public class TransportRuntime implements TransportInternal {
    public static volatile TransportRuntimeComponent instance;
    public final Clock eventClock;
    public final Scheduler scheduler;
    public final Uploader uploader;
    public final Clock uptimeClock;

    @Inject
    public TransportRuntime(@WallTime Clock clock, @Monotonic Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        this.eventClock = clock;
        this.uptimeClock = clock2;
        this.scheduler = scheduler;
        this.uploader = uploader;
        workInitializer.ensureContextsScheduled();
    }

    public static TransportRuntime getInstance() {
        TransportRuntimeComponent transportRuntimeComponent = instance;
        if (transportRuntimeComponent != null) {
            return transportRuntimeComponent.getTransportRuntime();
        }
        throw new IllegalStateException("Not initialized!");
    }

    public static Set<Encoding> getSupportedEncodings(Destination destination) {
        return destination instanceof EncodedDestination ? DesugarCollections.unmodifiableSet(((EncodedDestination) destination).getSupportedEncodings()) : Collections.singleton(new Encoding("proto"));
    }

    public static void initialize(Context context) {
        if (instance == null) {
            synchronized (TransportRuntime.class) {
                try {
                    if (instance == null) {
                        DaggerTransportRuntimeComponent.Builder builder = new DaggerTransportRuntimeComponent.Builder();
                        context.getClass();
                        builder.setApplicationContext = context;
                        instance = builder.build();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    public static void withInstance(TransportRuntimeComponent transportRuntimeComponent, Callable<Void> callable) throws Throwable {
        TransportRuntimeComponent transportRuntimeComponent2;
        synchronized (TransportRuntime.class) {
            transportRuntimeComponent2 = instance;
            instance = transportRuntimeComponent;
        }
        try {
            callable.call();
            synchronized (TransportRuntime.class) {
                instance = transportRuntimeComponent2;
            }
        } catch (Throwable th) {
            synchronized (TransportRuntime.class) {
                instance = transportRuntimeComponent2;
                throw th;
            }
        }
    }

    public final EventInternal convert(SendRequest sendRequest) {
        AutoValue_EventInternal.Builder builder = (AutoValue_EventInternal.Builder) EventInternal.builder();
        builder.eventMillis = Long.valueOf(this.eventClock.getTime());
        builder.uptimeMillis = Long.valueOf(this.uptimeClock.getTime());
        builder.setTransportName(sendRequest.getTransportName());
        builder.encodedPayload = new EncodedPayload(sendRequest.getEncoding(), sendRequest.getPayload());
        builder.code = sendRequest.getEvent().getCode();
        return builder.build();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Uploader getUploader() {
        return this.uploader;
    }

    @Deprecated
    public TransportFactory newFactory(String str) {
        Set<Encoding> supportedEncodings = getSupportedEncodings(null);
        TransportContext.Builder builder = TransportContext.builder();
        builder.setBackendName(str);
        return new TransportFactoryImpl(supportedEncodings, builder.build(), this);
    }

    @Override // com.google.android.datatransport.runtime.TransportInternal
    public void send(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback) {
        this.scheduler.schedule(sendRequest.getTransportContext().withPriority(sendRequest.getEvent().getPriority()), convert(sendRequest), transportScheduleCallback);
    }

    public TransportFactory newFactory(Destination destination) {
        Set<Encoding> supportedEncodings = getSupportedEncodings(destination);
        TransportContext.Builder builder = TransportContext.builder();
        destination.getClass();
        ((AutoValue_TransportContext.Builder) builder).backendName = "cct";
        ((AutoValue_TransportContext.Builder) builder).extras = destination.getExtras();
        return new TransportFactoryImpl(supportedEncodings, builder.build(), this);
    }
}
