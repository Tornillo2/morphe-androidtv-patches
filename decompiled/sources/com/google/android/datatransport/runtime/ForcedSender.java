package com.google.android.datatransport.runtime;

import androidx.annotation.WorkerThread;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ForcedSender {
    public static TransportContext getTransportContextOrThrow(Transport<?> transport) {
        if (transport instanceof TransportImpl) {
            return ((TransportImpl) transport).transportContext;
        }
        throw new IllegalArgumentException("Expected instance of TransportImpl.");
    }

    @WorkerThread
    public static void sendBlocking(Transport<?> transport, Priority priority) {
        TransportRuntime.getInstance().getUploader().logAndUpdateState(getTransportContextOrThrow(transport).withPriority(priority), 1);
    }
}
