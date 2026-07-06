package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public abstract class AbstractEncoder implements Encoder, CompositeEncoder {
    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        return Encoder.CC.$default$beginCollection(this, serialDescriptor, i);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean z) {
        encodeValue(Boolean.valueOf(z));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeBooleanElement(@NotNull SerialDescriptor descriptor, int i, boolean z) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeBoolean(z);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte b) {
        encodeValue(Byte.valueOf(b));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeByteElement(@NotNull SerialDescriptor descriptor, int i, byte b) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeByte(b);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeChar(char c) {
        encodeValue(Character.valueOf(c));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeCharElement(@NotNull SerialDescriptor descriptor, int i, char c) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeChar(c);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double d) {
        encodeValue(Double.valueOf(d));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeDoubleElement(@NotNull SerialDescriptor descriptor, int i, double d) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeDouble(d);
    }

    public boolean encodeElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return true;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeValue(Integer.valueOf(i));
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float f) {
        encodeValue(Float.valueOf(f));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeFloatElement(@NotNull SerialDescriptor descriptor, int i, float f) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeFloat(f);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public Encoder encodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public final Encoder encodeInlineElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        return encodeInline(descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeInt(int i) {
        encodeValue(Integer.valueOf(i));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(@NotNull SerialDescriptor descriptor, int i, int i2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeInt(i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeLong(long j) {
        encodeValue(Long.valueOf(j));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(@NotNull SerialDescriptor descriptor, int i, long j) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeLong(j);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        throw new SerializationException("'null' is not supported by default");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        encodeElement(descriptor, i);
        encodeNullableSerializableValue(serializer, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ void encodeNullableSerializableValue(SerializationStrategy serializationStrategy, Object obj) {
        Encoder.CC.$default$encodeNullableSerializableValue(this, serializationStrategy, obj);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        encodeElement(descriptor, i);
        encodeSerializableValue(serializer, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ void encodeSerializableValue(SerializationStrategy serializationStrategy, Object obj) {
        Encoder.CC.$default$encodeSerializableValue(this, serializationStrategy, obj);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeShort(short s) {
        encodeValue(Short.valueOf(s));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeShortElement(@NotNull SerialDescriptor descriptor, int i, short s) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeElement(descriptor, i);
        encodeShort(s);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeString(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeValue(value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(@NotNull SerialDescriptor descriptor, int i, @NotNull String value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(value, "value");
        encodeElement(descriptor, i);
        encodeString(value);
    }

    public void encodeValue(@NotNull Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new SerializationException("Non-serializable " + Reflection.getOrCreateKotlinClass(value.getClass()) + " is not supported by " + Reflection.factory.getOrCreateKotlinClass(getClass()) + " encoder");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public /* synthetic */ boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        CompositeEncoder.CC.$default$shouldEncodeElementDefault(this, serialDescriptor, i);
        return true;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ void encodeNotNullMark() {
    }
}
