package com.google.firebase.encoders.json;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import j$.util.DesugarTimeZone;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig<JsonDataEncoderBuilder> {
    public static final ObjectEncoder<Object> DEFAULT_FALLBACK_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda0();
    public static final ValueEncoder<String> STRING_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda1();
    public static final ValueEncoder<Boolean> BOOLEAN_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda2();
    public static final TimestampEncoder TIMESTAMP_ENCODER = new TimestampEncoder();
    public final Map<Class<?>, ObjectEncoder<?>> objectEncoders = new HashMap();
    public final Map<Class<?>, ValueEncoder<?>> valueEncoders = new HashMap();
    public ObjectEncoder<Object> fallbackEncoder = DEFAULT_FALLBACK_ENCODER;
    public boolean ignoreNullValues = false;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TimestampEncoder implements ValueEncoder<Date> {
        public static final DateFormat rfc339;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            rfc339 = simpleDateFormat;
            simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        }

        public TimestampEncoder() {
        }

        public TimestampEncoder(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(@NonNull Date date, @NonNull ValueEncoderContext valueEncoderContext) throws IOException {
            valueEncoderContext.add(rfc339.format(date));
        }
    }

    public static void $r8$lambda$bnoichjKU0v4bRcIU8iyVmXGe3M(Object obj, ObjectEncoderContext objectEncoderContext) {
        throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
    }

    public JsonDataEncoderBuilder() {
        registerEncoder(String.class, (ValueEncoder) STRING_ENCODER);
        registerEncoder(Boolean.class, (ValueEncoder) BOOLEAN_ENCODER);
        registerEncoder(Date.class, (ValueEncoder) TIMESTAMP_ENCODER);
    }

    @NonNull
    public DataEncoder build() {
        return new AnonymousClass1();
    }

    @NonNull
    public JsonDataEncoderBuilder configureWith(@NonNull Configurator configurator) {
        configurator.configure(this);
        return this;
    }

    @NonNull
    public JsonDataEncoderBuilder ignoreNullValues(boolean z) {
        this.ignoreNullValues = z;
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public /* bridge */ /* synthetic */ EncoderConfig registerEncoder(@NonNull Class cls, @NonNull ObjectEncoder objectEncoder) {
        registerEncoder(cls, objectEncoder);
        return this;
    }

    @NonNull
    public JsonDataEncoderBuilder registerFallbackEncoder(@NonNull ObjectEncoder<Object> objectEncoder) {
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
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ObjectEncoder<? super T> objectEncoder) {
        this.objectEncoders.put(cls, objectEncoder);
        this.valueEncoders.remove(cls);
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ValueEncoder<? super T> valueEncoder) {
        this.valueEncoders.put(cls, valueEncoder);
        this.objectEncoders.remove(cls);
        return this;
    }

    /* JADX INFO: renamed from: com.google.firebase.encoders.json.JsonDataEncoderBuilder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements DataEncoder {
        public AnonymousClass1() {
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException {
            Map map = JsonDataEncoderBuilder.this.objectEncoders;
            JsonDataEncoderBuilder jsonDataEncoderBuilder = JsonDataEncoderBuilder.this;
            JsonValueObjectEncoderContext jsonValueObjectEncoderContext = new JsonValueObjectEncoderContext(writer, map, jsonDataEncoderBuilder.valueEncoders, jsonDataEncoderBuilder.fallbackEncoder, jsonDataEncoderBuilder.ignoreNullValues);
            jsonValueObjectEncoderContext.add(obj, false);
            jsonValueObjectEncoderContext.close();
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public String encode(@NonNull Object obj) {
            StringWriter stringWriter = new StringWriter();
            try {
                encode(obj, stringWriter);
            } catch (IOException unused) {
            }
            return stringWriter.toString();
        }
    }
}
