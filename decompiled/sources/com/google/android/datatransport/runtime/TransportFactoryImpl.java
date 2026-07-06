package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TransportFactoryImpl implements TransportFactory {
    public final Set<Encoding> supportedPayloadEncodings;
    public final TransportContext transportContext;
    public final TransportInternal transportInternal;

    public TransportFactoryImpl(Set<Encoding> set, TransportContext transportContext, TransportInternal transportInternal) {
        this.supportedPayloadEncodings = set;
        this.transportContext = transportContext;
        this.transportInternal = transportInternal;
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String str, Class<T> cls, Transformer<T, byte[]> transformer) {
        return getTransport(str, cls, new Encoding("proto"), transformer);
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String str, Class<T> cls, Encoding encoding, Transformer<T, byte[]> transformer) {
        if (this.supportedPayloadEncodings.contains(encoding)) {
            return new TransportImpl(this.transportContext, str, encoding, transformer, this.transportInternal);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", encoding, this.supportedPayloadEncodings));
    }
}
