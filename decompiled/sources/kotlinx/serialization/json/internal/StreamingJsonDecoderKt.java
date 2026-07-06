package kotlinx.serialization.json.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StreamingJsonDecoderKt {
    @JsonFriendModuleApi
    @NotNull
    public static final <T> JsonElement decodeStringToJsonTree(@NotNull Json json, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull String source) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(source, "source");
        StringJsonLexer StringJsonLexer = StringJsonLexerKt.StringJsonLexer(json, source);
        JsonElement jsonElementDecodeJsonElement = new StreamingJsonDecoder(json, WriteMode.OBJ, StringJsonLexer, deserializer.getDescriptor(), null).decodeJsonElement();
        StringJsonLexer.expectEof();
        return jsonElementDecodeJsonElement;
    }

    public static final <T> T parseString(AbstractJsonLexer abstractJsonLexer, String str, Function1<? super String, ? extends T> function1) {
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            return function1.invoke(strConsumeStringLenient);
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type '" + str + "' for input '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw null;
        }
    }
}
