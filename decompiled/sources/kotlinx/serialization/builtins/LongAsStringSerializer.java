package kotlinx.serialization.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class LongAsStringSerializer implements KSerializer<Long> {

    @NotNull
    public static final LongAsStringSerializer INSTANCE = new LongAsStringSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlinx.serialization.LongAsStringSerializer", PrimitiveKind.STRING.INSTANCE);

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, ((Number) obj).longValue());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public Long deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return Long.valueOf(Long.parseLong(decoder.decodeString()));
    }

    public void serialize(@NotNull Encoder encoder, long j) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeString(String.valueOf(j));
    }
}
