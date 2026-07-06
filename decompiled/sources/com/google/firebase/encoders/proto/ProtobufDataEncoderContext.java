package com.google.firebase.encoders.proto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.proto.Protobuf;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    public static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER;
    public static final FieldDescriptor MAP_KEY_DESC;
    public static final FieldDescriptor MAP_VALUE_DESC;
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public final ObjectEncoder<Object> fallbackEncoder;
    public final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    public OutputStream output;
    public final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    public final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* JADX INFO: renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding;

        static {
            int[] iArr = new int[Protobuf.IntEncoding.values().length];
            $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = iArr;
            try {
                iArr[Protobuf.IntEncoding.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.SIGNED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.FIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$HMpWU6Ryr444SahjVbEVRqty-xs, reason: not valid java name */
    public static /* synthetic */ void m565$r8$lambda$HMpWU6Ryr444SahjVbEVRqtyxs(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add(MAP_VALUE_DESC, entry.getValue());
    }

    static {
        FieldDescriptor.Builder builder = new FieldDescriptor.Builder("key");
        AtProtobuf atProtobuf = new AtProtobuf();
        atProtobuf.tag = 1;
        builder.withProperty(atProtobuf.build());
        MAP_KEY_DESC = builder.build();
        FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("value");
        AtProtobuf atProtobuf2 = new AtProtobuf();
        atProtobuf2.tag = 2;
        builder2.withProperty(atProtobuf2.build());
        MAP_VALUE_DESC = builder2.build();
        DEFAULT_MAP_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0();
    }

    public ProtobufDataEncoderContext(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    public static ByteBuffer allocateBuffer(int i) {
        return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
    }

    public static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    public static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws IOException {
        add(FieldDescriptor.of(str), obj, true);
        return this;
    }

    public final <T> long determineSize(ObjectEncoder<T> objectEncoder, T t) throws IOException {
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            OutputStream outputStream = this.output;
            this.output = lengthCountingOutputStream;
            try {
                objectEncoder.encode(t, this);
                this.output = outputStream;
                long j = lengthCountingOutputStream.length;
                lengthCountingOutputStream.close();
                return j;
            } catch (Throwable th) {
                this.output = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                lengthCountingOutputStream.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    public final <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        long jDetermineSize = determineSize(objectEncoder, t);
        if (z && jDetermineSize == 0) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
        writeVarInt64(jDetermineSize);
        objectEncoder.encode(t, this);
        return this;
    }

    public ProtobufDataEncoderContext encode(@Nullable Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for " + obj.getClass());
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        encode(obj);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext nested(@NonNull String str) throws IOException {
        nested(FieldDescriptor.of(str));
        throw null;
    }

    public final void writeVarInt32(int i) throws IOException {
        while ((i & (-128)) != 0) {
            this.output.write((i & 127) | 128);
            i >>>= 7;
        }
        this.output.write(i & 127);
    }

    public final void writeVarInt64(long j) throws IOException {
        while (((-128) & j) != 0) {
            this.output.write((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.output.write(((int) j) & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull String str, double d) throws IOException {
        add(FieldDescriptor.of(str), d, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull String str, int i) throws IOException {
        add(FieldDescriptor.of(str), i, true);
        return this;
    }

    public final <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        this.valueEncoderContext.resetContext(fieldDescriptor, z);
        valueEncoder.encode(t, this.valueEncoderContext);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull String str, long j) throws IOException {
        add(FieldDescriptor.of(str), j, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull String str, boolean z) throws IOException {
        add(FieldDescriptor.of(str), z ? 1 : 0, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        add(fieldDescriptor, obj, true);
        return this;
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj, boolean z) throws IOException {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(UTF_8);
                    writeVarInt32(bytes.length);
                    this.output.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    add(fieldDescriptor, it.next(), false);
                }
            } else if (obj instanceof Map) {
                Iterator it2 = ((Map) obj).entrySet().iterator();
                while (it2.hasNext()) {
                    doEncode((ObjectEncoder<Map.Entry>) DEFAULT_MAP_ENCODER, fieldDescriptor, (Map.Entry) it2.next(), false);
                }
            } else {
                if (obj instanceof Double) {
                    add(fieldDescriptor, ((Double) obj).doubleValue(), z);
                    return this;
                }
                if (obj instanceof Float) {
                    add(fieldDescriptor, ((Float) obj).floatValue(), z);
                    return this;
                }
                if (obj instanceof Number) {
                    add(fieldDescriptor, ((Number) obj).longValue(), z);
                    return this;
                }
                if (obj instanceof Boolean) {
                    add(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z);
                    return this;
                }
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    if (!z || bArr.length != 0) {
                        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                        writeVarInt32(bArr.length);
                        this.output.write(bArr);
                        return this;
                    }
                } else {
                    ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
                    if (objectEncoder != null) {
                        doEncode(objectEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
                    if (valueEncoder != null) {
                        doEncode(valueEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    if (obj instanceof ProtoEnum) {
                        add(fieldDescriptor, ((ProtoEnum) obj).getNumber(), true);
                        return this;
                    }
                    if (obj instanceof Enum) {
                        add(fieldDescriptor, ((Enum) obj).ordinal(), true);
                        return this;
                    }
                    doEncode(this.fallbackEncoder, fieldDescriptor, obj, z);
                    return this;
                }
            }
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d) throws IOException {
        add(fieldDescriptor, d, true);
        return this;
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d, boolean z) throws IOException {
        if (z && d == 0.0d) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(d).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f) throws IOException {
        add(fieldDescriptor, f, true);
        return this;
    }

    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f, boolean z) throws IOException {
        if (z && f == 0.0f) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(f).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i) throws IOException {
        add(fieldDescriptor, i, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i) throws IOException {
        add(fieldDescriptor, i, true);
        return this;
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i, boolean z) throws IOException {
        if (!z || i != 0) {
            Protobuf protobuf = getProtobuf(fieldDescriptor);
            int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
            if (i2 == 1) {
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt32(i);
                return this;
            }
            if (i2 == 2) {
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt32((i << 1) ^ (i >> 31));
                return this;
            }
            if (i2 == 3) {
                writeVarInt32((protobuf.tag() << 3) | 5);
                this.output.write(allocateBuffer(4).putInt(i).array());
                return this;
            }
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException {
        add(fieldDescriptor, j, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException {
        add(fieldDescriptor, j, true);
        return this;
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j, boolean z) throws IOException {
        if (!z || j != 0) {
            Protobuf protobuf = getProtobuf(fieldDescriptor);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
            if (i == 1) {
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt64(j);
                return this;
            }
            if (i == 2) {
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt64((j >> 63) ^ (j << 1));
                return this;
            }
            if (i == 3) {
                writeVarInt32((protobuf.tag() << 3) | 1);
                this.output.write(allocateBuffer(8).putLong(j).array());
                return this;
            }
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        add(fieldDescriptor, z ? 1 : 0, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        add(fieldDescriptor, z ? 1 : 0, true);
        return this;
    }

    public ProtobufDataEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z, boolean z2) throws IOException {
        add(fieldDescriptor, z ? 1 : 0, z2);
        return this;
    }
}
