package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.proto.ProtobufEncoder;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Encodable
public abstract class ProtoEncoderDoNotUse {
    public static final ProtobufEncoder ENCODER;

    static {
        ProtobufEncoder.Builder builder = new ProtobufEncoder.Builder();
        AutoProtoEncoderDoNotUseEncoder.CONFIG.configure(builder);
        ENCODER = builder.build();
    }

    public static byte[] encode(Object obj) {
        return ENCODER.encode(obj);
    }

    public abstract ClientMetrics getClientMetrics();

    public static void encode(Object obj, OutputStream outputStream) throws IOException {
        ENCODER.encode(obj, outputStream);
    }
}
