package com.google.android.datatransport.runtime.scheduling.persistence;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@WorkerThread
public interface EventStore extends Closeable {
    int cleanUp();

    long getNextCallTime(TransportContext transportContext);

    boolean hasPendingEventsFor(TransportContext transportContext);

    Iterable<TransportContext> loadActiveContexts();

    Iterable<PersistedEvent> loadBatch(TransportContext transportContext);

    @Nullable
    PersistedEvent persist(TransportContext transportContext, EventInternal eventInternal);

    void recordFailure(Iterable<PersistedEvent> iterable);

    void recordNextCallTime(TransportContext transportContext, long j);

    void recordSuccess(Iterable<PersistedEvent> iterable);
}
