package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class UByteSerializer implements KSerializer<UByte> {

    @NotNull
    public static final UByteSerializer INSTANCE = new UByteSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UByte", BuiltinSerializersKt.serializer(ByteCompanionObject.INSTANCE));

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* synthetic */ Object deserialize(Decoder decoder) {
        return new UByte(m2163deserializeWa3L5BU(decoder));
    }

    /* JADX INFO: renamed from: deserialize-Wa3L5BU, reason: not valid java name */
    public byte m2163deserializeWa3L5BU(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return decoder.decodeInline(descriptor).decodeByte();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m2164serializeEK6454(encoder, ((UByte) obj).data);
    }

    /* JADX INFO: renamed from: serialize-EK-6454, reason: not valid java name */
    public void m2164serializeEK6454(@NotNull Encoder encoder, byte b) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeInline(descriptor).encodeByte(b);
    }
}
