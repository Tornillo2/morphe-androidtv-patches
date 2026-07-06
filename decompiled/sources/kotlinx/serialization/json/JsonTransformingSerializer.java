package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class JsonTransformingSerializer<T> implements KSerializer<T> {

    @NotNull
    public final KSerializer<T> tSerializer;

    public JsonTransformingSerializer(@NotNull KSerializer<T> tSerializer) {
        Intrinsics.checkNotNullParameter(tSerializer, "tSerializer");
        this.tSerializer = tSerializer;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonDecoder jsonDecoderAsJsonDecoder = JsonElementSerializersKt.asJsonDecoder(decoder);
        return (T) jsonDecoderAsJsonDecoder.getJson().decodeFromJsonElement(this.tSerializer, transformDeserialize(jsonDecoderAsJsonDecoder.decodeJsonElement()));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.tSerializer.getDescriptor();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonEncoder jsonEncoderAsJsonEncoder = JsonElementSerializersKt.asJsonEncoder(encoder);
        jsonEncoderAsJsonEncoder.encodeJsonElement(transformSerialize(TreeJsonEncoderKt.writeJson(jsonEncoderAsJsonEncoder.getJson(), value, this.tSerializer)));
    }

    @NotNull
    public JsonElement transformDeserialize(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return element;
    }

    @NotNull
    public JsonElement transformSerialize(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return element;
    }
}
