package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class ULongSerializer implements KSerializer<ULong> {

    @NotNull
    public static final ULongSerializer INSTANCE = new ULongSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlin.ULong", BuiltinSerializersKt.serializer(LongCompanionObject.INSTANCE));

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* synthetic */ Object deserialize(Decoder decoder) {
        return new ULong(m2179deserializeI7RO_PI(decoder));
    }

    /* JADX INFO: renamed from: deserialize-I7RO_PI, reason: not valid java name */
    public long m2179deserializeI7RO_PI(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return decoder.decodeInline(descriptor).decodeLong();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m2180serialize2TYgG_w(encoder, ((ULong) obj).data);
    }

    /* JADX INFO: renamed from: serialize-2TYgG_w, reason: not valid java name */
    public void m2180serialize2TYgG_w(@NotNull Encoder encoder, long j) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeInline(descriptor).encodeLong(j);
    }
}
