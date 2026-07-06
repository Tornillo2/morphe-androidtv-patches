package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class DurationSerializer implements KSerializer<Duration> {

    @NotNull
    public static final DurationSerializer INSTANCE = new DurationSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = new PrimitiveSerialDescriptor("kotlin.time.Duration", PrimitiveKind.STRING.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* synthetic */ Object deserialize(Decoder decoder) {
        return new Duration(m2149deserialize5sfh64U(decoder));
    }

    /* JADX INFO: renamed from: deserialize-5sfh64U, reason: not valid java name */
    public long m2149deserialize5sfh64U(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return Duration.Companion.m2018parseIsoStringUwyO8pc(decoder.decodeString());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m2150serializeHG0u8IE(encoder, ((Duration) obj).rawValue);
    }

    /* JADX INFO: renamed from: serialize-HG0u8IE, reason: not valid java name */
    public void m2150serializeHG0u8IE(@NotNull Encoder encoder, long j) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeString(Duration.m1963toIsoStringimpl(j));
    }
}
