package kotlinx.serialization.json;

import kotlin.NoWhenBranchMatchedException;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.JsonElementSerializersKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class JsonElementSerializer implements KSerializer<JsonElement> {

    @NotNull
    public static final JsonElementSerializer INSTANCE = new JsonElementSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], new JsonElementSerializer$$ExternalSyntheticLambda0());

    public static final Unit descriptor$lambda$5(ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonPrimitive", new JsonElementSerializersKt.AnonymousClass1(new JsonElementSerializer$$ExternalSyntheticLambda1()), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonNull", new JsonElementSerializersKt.AnonymousClass1(new JsonElementSerializer$$ExternalSyntheticLambda2()), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonLiteral", new JsonElementSerializersKt.AnonymousClass1(new JsonElementSerializer$$ExternalSyntheticLambda3()), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonObject", new JsonElementSerializersKt.AnonymousClass1(new JsonElementSerializer$$ExternalSyntheticLambda4()), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonArray", new JsonElementSerializersKt.AnonymousClass1(new JsonElementSerializer$$ExternalSyntheticLambda5()), null, false, 12, null);
        return Unit.INSTANCE;
    }

    public static final SerialDescriptor descriptor$lambda$5$lambda$0() {
        JsonPrimitiveSerializer.INSTANCE.getClass();
        return JsonPrimitiveSerializer.descriptor;
    }

    public static final SerialDescriptor descriptor$lambda$5$lambda$1() {
        JsonNullSerializer.INSTANCE.getClass();
        return JsonNullSerializer.descriptor;
    }

    public static final SerialDescriptor descriptor$lambda$5$lambda$2() {
        JsonLiteralSerializer.INSTANCE.getClass();
        return JsonLiteralSerializer.descriptor;
    }

    public static final SerialDescriptor descriptor$lambda$5$lambda$3() {
        JsonObjectSerializer.INSTANCE.getClass();
        return JsonObjectSerializer.descriptor;
    }

    public static final SerialDescriptor descriptor$lambda$5$lambda$4() {
        JsonArraySerializer.INSTANCE.getClass();
        return JsonArraySerializer.descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonElement deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonElement value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.asJsonEncoder(encoder);
        if (value instanceof JsonPrimitive) {
            encoder.encodeSerializableValue(JsonPrimitiveSerializer.INSTANCE, value);
        } else if (value instanceof JsonObject) {
            encoder.encodeSerializableValue(JsonObjectSerializer.INSTANCE, value);
        } else {
            if (!(value instanceof JsonArray)) {
                throw new NoWhenBranchMatchedException();
            }
            encoder.encodeSerializableValue(JsonArraySerializer.INSTANCE, value);
        }
    }
}
