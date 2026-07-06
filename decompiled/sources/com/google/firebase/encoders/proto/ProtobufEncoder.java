package com.google.firebase.encoders.proto;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ProtobufEncoder {
    public final ObjectEncoder<Object> fallbackEncoder;
    public final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    public final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder implements EncoderConfig<Builder> {
        public static final ObjectEncoder<Object> DEFAULT_FALLBACK_ENCODER = new ProtobufEncoder$Builder$$ExternalSyntheticLambda0();
        public final Map<Class<?>, ObjectEncoder<?>> objectEncoders = new HashMap();
        public final Map<Class<?>, ValueEncoder<?>> valueEncoders = new HashMap();
        public ObjectEncoder<Object> fallbackEncoder = DEFAULT_FALLBACK_ENCODER;

        /* JADX INFO: renamed from: $r8$lambda$HcoNKxmNqN-9duQx_r41_DwbVtE, reason: not valid java name */
        public static void m566$r8$lambda$HcoNKxmNqN9duQx_r41_DwbVtE(Object obj, ObjectEncoderContext objectEncoderContext) {
            throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
        }

        public ProtobufEncoder build() {
            return new ProtobufEncoder(new HashMap(this.objectEncoders), new HashMap(this.valueEncoders), this.fallbackEncoder);
        }

        @NonNull
        public Builder configureWith(@NonNull Configurator configurator) {
            configurator.configure(this);
            return this;
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        @NonNull
        public /* bridge */ /* synthetic */ EncoderConfig registerEncoder(@NonNull Class cls, @NonNull ObjectEncoder objectEncoder) {
            registerEncoder(cls, objectEncoder);
            return this;
        }

        @NonNull
        public Builder registerFallbackEncoder(@NonNull ObjectEncoder<Object> objectEncoder) {
            this.fallbackEncoder = objectEncoder;
            return this;
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        @NonNull
        public /* bridge */ /* synthetic */ EncoderConfig registerEncoder(@NonNull Class cls, @NonNull ValueEncoder valueEncoder) {
            registerEncoder(cls, valueEncoder);
            return this;
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        @NonNull
        public <U> Builder registerEncoder(@NonNull Class<U> cls, @NonNull ObjectEncoder<? super U> objectEncoder) {
            this.objectEncoders.put(cls, objectEncoder);
            this.valueEncoders.remove(cls);
            return this;
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        @NonNull
        public <U> Builder registerEncoder(@NonNull Class<U> cls, @NonNull ValueEncoder<? super U> valueEncoder) {
            this.valueEncoders.put(cls, valueEncoder);
            this.objectEncoders.remove(cls);
            return this;
        }
    }

    public ProtobufEncoder(Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void encode(@NonNull Object obj, @NonNull OutputStream outputStream) throws IOException {
        new ProtobufDataEncoderContext(outputStream, this.objectEncoders, this.valueEncoders, this.fallbackEncoder).encode(obj);
    }

    @NonNull
    public byte[] encode(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(obj, byteArrayOutputStream);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
