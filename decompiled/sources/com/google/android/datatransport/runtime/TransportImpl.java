package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TransportImpl<T> implements Transport<T> {
    public final String name;
    public final Encoding payloadEncoding;
    public final Transformer<T, byte[]> transformer;
    public final TransportContext transportContext;
    public final TransportInternal transportInternal;

    public TransportImpl(TransportContext transportContext, String str, Encoding encoding, Transformer<T, byte[]> transformer, TransportInternal transportInternal) {
        this.transportContext = transportContext;
        this.name = str;
        this.payloadEncoding = encoding;
        this.transformer = transformer;
        this.transportInternal = transportInternal;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override // com.google.android.datatransport.Transport
    public void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback) {
        TransportInternal transportInternal = this.transportInternal;
        AutoValue_SendRequest.Builder builder = new AutoValue_SendRequest.Builder();
        builder.setTransportContext(this.transportContext);
        builder.setEvent(event);
        builder.setTransportName(this.name);
        builder.setTransformer(this.transformer);
        builder.setEncoding(this.payloadEncoding);
        transportInternal.send(builder.build(), transportScheduleCallback);
    }

    @Override // com.google.android.datatransport.Transport
    public void send(Event<T> event) {
        schedule(event, new TransportImpl$$ExternalSyntheticLambda0());
    }

    public static /* synthetic */ void $r8$lambda$bZYIR2AWMsK3OiMdU9QsskqwQec(Exception exc) {
    }

    public static /* synthetic */ void lambda$send$0(Exception exc) {
    }
}
