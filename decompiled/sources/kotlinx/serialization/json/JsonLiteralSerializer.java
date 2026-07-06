package kotlinx.serialization.json;

import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.UStringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.json.internal.JsonExceptionsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonElementSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElementSerializers.kt\nkotlinx/serialization/json/JsonLiteralSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,236:1\n1#2:237\n*E\n"})
public final class JsonLiteralSerializer implements KSerializer<JsonLiteral> {

    @NotNull
    public static final JsonLiteralSerializer INSTANCE = new JsonLiteralSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlinx.serialization.json.JsonLiteral", PrimitiveKind.STRING.INSTANCE);

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonLiteral deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElement jsonElementDecodeJsonElement = JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
        if (jsonElementDecodeJsonElement instanceof JsonLiteral) {
            return (JsonLiteral) jsonElementDecodeJsonElement;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected JSON element, expected JsonLiteral, had " + Reflection.getOrCreateKotlinClass(jsonElementDecodeJsonElement.getClass()), jsonElementDecodeJsonElement.toString());
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonLiteral value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.asJsonEncoder(encoder);
        if (value.isString) {
            encoder.encodeString(value.content);
            return;
        }
        SerialDescriptor serialDescriptor = value.coerceToInlineType;
        if (serialDescriptor != null) {
            encoder.encodeInline(serialDescriptor).encodeString(value.content);
            return;
        }
        Long longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(value.content);
        if (longOrNull != null) {
            encoder.encodeLong(longOrNull.longValue());
            return;
        }
        ULong uLongOrNull = UStringsKt.toULongOrNull(value.content);
        if (uLongOrNull != null) {
            long j = uLongOrNull.data;
            ((ULongSerializer) BuiltinSerializersKt.serializer(ULong.Companion)).getClass();
            encoder.encodeInline(ULongSerializer.descriptor).encodeLong(j);
            return;
        }
        Double doubleOrNull = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(value.content);
        if (doubleOrNull != null) {
            encoder.encodeDouble(doubleOrNull.doubleValue());
            return;
        }
        Boolean booleanStrictOrNull = StringsKt__StringsKt.toBooleanStrictOrNull(value.content);
        if (booleanStrictOrNull != null) {
            encoder.encodeBoolean(booleanStrictOrNull.booleanValue());
        } else {
            encoder.encodeString(value.content);
        }
    }
}
