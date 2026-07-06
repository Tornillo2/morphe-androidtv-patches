package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class UShortSerializer implements KSerializer<UShort> {

    @NotNull
    public static final UShortSerializer INSTANCE = new UShortSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UShort", BuiltinSerializersKt.serializer(ShortCompanionObject.INSTANCE));

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* synthetic */ Object deserialize(Decoder decoder) {
        return new UShort(m2187deserializeBwKQO78(decoder));
    }

    /* JADX INFO: renamed from: deserialize-BwKQO78, reason: not valid java name */
    public short m2187deserializeBwKQO78(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return decoder.decodeInline(descriptor).decodeShort();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m2188serializei8woANY(encoder, ((UShort) obj).data);
    }

    /* JADX INFO: renamed from: serialize-i8woANY, reason: not valid java name */
    public void m2188serializei8woANY(@NotNull Encoder encoder, short s) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeInline(descriptor).encodeShort(s);
    }
}
