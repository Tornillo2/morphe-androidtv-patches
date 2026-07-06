package kotlinx.serialization.json.internal;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class TreeJsonDecoderKt {
    @JsonFriendModuleApi
    public static final <T> T readJson(@NotNull Json json, @NotNull JsonElement element, @NotNull DeserializationStrategy<? extends T> deserializer) {
        Decoder jsonPrimitiveDecoder;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (element instanceof JsonObject) {
            jsonPrimitiveDecoder = new JsonTreeDecoder(json, (JsonObject) element, null, null, 12, null);
        } else if (element instanceof JsonArray) {
            jsonPrimitiveDecoder = new JsonTreeListDecoder(json, (JsonArray) element);
        } else {
            if (!(element instanceof JsonLiteral) && !element.equals(JsonNull.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            jsonPrimitiveDecoder = new JsonPrimitiveDecoder(json, (JsonPrimitive) element, null, 4, null);
        }
        return (T) jsonPrimitiveDecoder.decodeSerializableValue(deserializer);
    }

    public static final <T> T readPolymorphicJson(@NotNull Json json, @NotNull String discriminator, @NotNull JsonObject element, @NotNull DeserializationStrategy<? extends T> deserializer) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(discriminator, "discriminator");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) new JsonTreeDecoder(json, element, discriminator, deserializer.getDescriptor()).decodeSerializableValue(deserializer);
    }
}
