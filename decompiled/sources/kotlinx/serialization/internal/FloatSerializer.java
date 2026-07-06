package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class FloatSerializer implements KSerializer<Float> {

    @NotNull
    public static final FloatSerializer INSTANCE = new FloatSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = new PrimitiveSerialDescriptor("kotlin.Float", PrimitiveKind.FLOAT.INSTANCE);

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, ((Number) obj).floatValue());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public Float deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return Float.valueOf(decoder.decodeFloat());
    }

    public void serialize(@NotNull Encoder encoder, float f) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeFloat(f);
    }
}
