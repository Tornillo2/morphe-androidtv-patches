package kotlinx.serialization.json;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.internal.JsonDecodingException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class JsonNullSerializer implements KSerializer<JsonNull> {

    @NotNull
    public static final JsonNullSerializer INSTANCE = new JsonNullSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor$default("kotlinx.serialization.json.JsonNull", SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], null, 8, null);

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonNull deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.asJsonDecoder(decoder);
        if (decoder.decodeNotNullMark()) {
            throw new JsonDecodingException("Expected 'null' literal");
        }
        return JsonNull.INSTANCE;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonNull value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.asJsonEncoder(encoder);
        encoder.encodeNull();
    }
}
