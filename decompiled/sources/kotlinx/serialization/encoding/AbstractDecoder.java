package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
@SourceDebugExtension({"SMAP\nAbstractDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractDecoder.kt\nkotlinx/serialization/encoding/AbstractDecoder\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,81:1\n271#2,2:82\n*S KotlinDebug\n*F\n+ 1 AbstractDecoder.kt\nkotlinx/serialization/encoding/AbstractDecoder\n*L\n77#1:82,2\n*E\n"})
public abstract class AbstractDecoder implements Decoder, CompositeDecoder {
    public static /* synthetic */ Object decodeSerializableValue$default(AbstractDecoder abstractDecoder, DeserializationStrategy deserializationStrategy, Object obj, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeSerializableValue");
        }
        if ((i & 2) != 0) {
            obj = null;
        }
        return abstractDecoder.decodeSerializableValue(deserializationStrategy, obj);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeByte();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeChar();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public /* synthetic */ int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        CompositeDecoder.CC.$default$decodeCollectionSize(this, serialDescriptor);
        return -1;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeDouble();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeEnum(@NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeFloat();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public Decoder decodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public Decoder decodeInlineElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeInline(descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeLong();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @Nullable
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @Nullable
    public final <T> T decodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<? extends T> deserializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (deserializer.getDescriptor().isNullable() || decodeNotNullMark()) {
            return (T) decodeSerializableValue(deserializer, t);
        }
        return null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public /* synthetic */ Object decodeNullableSerializableValue(DeserializationStrategy deserializationStrategy) {
        return Decoder.CC.$default$decodeNullableSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public /* synthetic */ boolean decodeSequentially() {
        CompositeDecoder.CC.$default$decodeSequentially(this);
        return false;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<? extends T> deserializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) decodeSerializableValue(deserializer, t);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public /* synthetic */ Object decodeSerializableValue(DeserializationStrategy deserializationStrategy) {
        return Decoder.CC.$default$decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeShort();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public String decodeString() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public final String decodeStringElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeString();
    }

    @NotNull
    public Object decodeValue() {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<? extends T> deserializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) decodeSerializableValue(deserializer);
    }
}
