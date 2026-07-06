package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class PersistedEvent {
    public static PersistedEvent create(long j, TransportContext transportContext, EventInternal eventInternal) {
        return new AutoValue_PersistedEvent(j, transportContext, eventInternal);
    }

    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();
}
