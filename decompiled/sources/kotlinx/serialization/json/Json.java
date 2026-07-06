package kotlinx.serialization.json;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import kotlinx.serialization.json.internal.JsonStreamsKt;
import kotlinx.serialization.json.internal.JsonToStringWriter;
import kotlinx.serialization.json.internal.StreamingJsonDecoder;
import kotlinx.serialization.json.internal.StringJsonLexer;
import kotlinx.serialization.json.internal.StringJsonLexerKt;
import kotlinx.serialization.json.internal.TreeJsonDecoderKt;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import kotlinx.serialization.json.internal.WriteMode;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class Json implements StringFormat {

    @NotNull
    public static final Default Default = new Default();

    @NotNull
    public final DescriptorSchemaCache _schemaCache;

    @NotNull
    public final JsonConfiguration configuration;

    @NotNull
    public final SerializersModule serializersModule;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Default extends Json {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public Default() {
            super(new JsonConfiguration(false, false, false, false, false, false, null, false, false, null, false, false, null, false, false, false, null, 131071, null), SerializersModuleKt.getEmptySerializersModule());
        }
    }

    public /* synthetic */ Json(JsonConfiguration jsonConfiguration, SerializersModule serializersModule, DefaultConstructorMarker defaultConstructorMarker) {
        this(jsonConfiguration, serializersModule);
    }

    public final <T> T decodeFromJsonElement(@NotNull DeserializationStrategy<? extends T> deserializer, @NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(element, "element");
        return (T) TreeJsonDecoderKt.readJson(this, element, deserializer);
    }

    @Override // kotlinx.serialization.StringFormat
    public final <T> T decodeFromString(@NotNull DeserializationStrategy<? extends T> deserializer, @Language(prefix = "", suffix = "", value = "json") @NotNull String string) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(string, "string");
        StringJsonLexer StringJsonLexer = StringJsonLexerKt.StringJsonLexer(this, string);
        T t = (T) new StreamingJsonDecoder(this, WriteMode.OBJ, StringJsonLexer, deserializer.getDescriptor(), null).decodeSerializableValue(deserializer);
        StringJsonLexer.expectEof();
        return t;
    }

    @NotNull
    public final <T> JsonElement encodeToJsonElement(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        return TreeJsonEncoderKt.writeJson(this, t, serializer);
    }

    @Override // kotlinx.serialization.StringFormat
    @NotNull
    public final <T> String encodeToString(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        JsonToStringWriter jsonToStringWriter = new JsonToStringWriter();
        try {
            JsonStreamsKt.encodeByWriter(this, jsonToStringWriter, serializer, t);
            return jsonToStringWriter.toString();
        } finally {
            jsonToStringWriter.release();
        }
    }

    @NotNull
    public final JsonConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // kotlinx.serialization.SerialFormat
    @NotNull
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @NotNull
    public final DescriptorSchemaCache get_schemaCache$kotlinx_serialization_json() {
        return this._schemaCache;
    }

    @NotNull
    public final JsonElement parseToJsonElement(@Language(prefix = "", suffix = "", value = "json") @NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return (JsonElement) decodeFromString(JsonElementSerializer.INSTANCE, string);
    }

    public Json(JsonConfiguration jsonConfiguration, SerializersModule serializersModule) {
        this.configuration = jsonConfiguration;
        this.serializersModule = serializersModule;
        this._schemaCache = new DescriptorSchemaCache();
    }

    public final <T> T decodeFromString(@Language(prefix = "", suffix = "", value = "json") String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public final <T> String encodeToString(T t) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Should not be accessed directly, use Json.schemaCache accessor instead", replaceWith = @ReplaceWith(expression = "schemaCache", imports = {}))
    public static /* synthetic */ void get_schemaCache$kotlinx_serialization_json$annotations() {
    }
}
