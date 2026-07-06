package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.UInt;
import kotlin.jvm.internal.IntCompanionObject;
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
public final class UIntSerializer implements KSerializer<UInt> {

    @NotNull
    public static final UIntSerializer INSTANCE = new UIntSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.UInt", BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE));

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* synthetic */ Object deserialize(Decoder decoder) {
        return new UInt(m2171deserializeOGnWXxg(decoder));
    }

    /* JADX INFO: renamed from: deserialize-OGnWXxg, reason: not valid java name */
    public int m2171deserializeOGnWXxg(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return decoder.decodeInline(descriptor).decodeInt();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m2172serializeQn1smSk(encoder, ((UInt) obj).data);
    }

    /* JADX INFO: renamed from: serialize-Qn1smSk, reason: not valid java name */
    public void m2172serializeQn1smSk(@NotNull Encoder encoder, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeInline(descriptor).encodeInt(i);
    }
}
