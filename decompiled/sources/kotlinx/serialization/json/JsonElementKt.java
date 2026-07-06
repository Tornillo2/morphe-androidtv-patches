package kotlinx.serialization.json;

import kotlin.PublishedApi;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.InlineClassDescriptorKt;
import kotlinx.serialization.json.internal.JsonDecodingException;
import kotlinx.serialization.json.internal.JsonEncodingException;
import kotlinx.serialization.json.internal.StringJsonLexer;
import kotlinx.serialization.json.internal.StringOpsKt;
import kotlinx.serialization.json.internal.SuppressAnimalSniffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElement.kt\nkotlinx/serialization/json/JsonElementKt\n*L\n1#1,350:1\n337#1,4:351\n329#1,4:355\n337#1,4:359\n329#1,4:363\n*S KotlinDebug\n*F\n+ 1 JsonElement.kt\nkotlinx/serialization/json/JsonElementKt\n*L\n259#1:351,4\n269#1:355,4\n278#1:359,4\n284#1:363,4\n*E\n"})
public final class JsonElementKt {

    @NotNull
    public static final SerialDescriptor jsonUnquotedLiteralDescriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlinx.serialization.json.JsonUnquotedLiteral", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));

    @NotNull
    public static final JsonPrimitive JsonPrimitive(@Nullable Boolean bool) {
        if (bool == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(bool, false, null, 4, null);
    }

    @ExperimentalSerializationApi
    @NotNull
    /* JADX INFO: renamed from: JsonPrimitive-7apg3OU, reason: not valid java name */
    public static final JsonPrimitive m2190JsonPrimitive7apg3OU(byte b) {
        return m2191JsonPrimitiveVKZWuLQ(((long) b) & 255);
    }

    @ExperimentalSerializationApi
    @SuppressAnimalSniffer
    @NotNull
    /* JADX INFO: renamed from: JsonPrimitive-VKZWuLQ, reason: not valid java name */
    public static final JsonPrimitive m2191JsonPrimitiveVKZWuLQ(long j) {
        return JsonUnquotedLiteral(JsonElementKt$$ExternalSyntheticBackport1.m(j, 10));
    }

    @ExperimentalSerializationApi
    @NotNull
    /* JADX INFO: renamed from: JsonPrimitive-WZ4Q5Ns, reason: not valid java name */
    public static final JsonPrimitive m2192JsonPrimitiveWZ4Q5Ns(int i) {
        return m2191JsonPrimitiveVKZWuLQ(((long) i) & 4294967295L);
    }

    @ExperimentalSerializationApi
    @NotNull
    /* JADX INFO: renamed from: JsonPrimitive-xj2QHRw, reason: not valid java name */
    public static final JsonPrimitive m2193JsonPrimitivexj2QHRw(short s) {
        return m2191JsonPrimitiveVKZWuLQ(((long) s) & 65535);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final JsonPrimitive JsonUnquotedLiteral(@Nullable String str) {
        if (str == null) {
            return JsonNull.INSTANCE;
        }
        JsonNull.INSTANCE.getClass();
        if (str.equals(JsonNull.content)) {
            throw new JsonEncodingException("Creating a literal unquoted value of 'null' is forbidden. If you want to create JSON null literal, use JsonNull object, otherwise, use JsonPrimitive");
        }
        return new JsonLiteral(str, false, jsonUnquotedLiteralDescriptor);
    }

    public static final Void error(JsonElement jsonElement, String str) {
        throw new IllegalArgumentException("Element " + Reflection.getOrCreateKotlinClass(jsonElement.getClass()) + " is not a " + str);
    }

    public static final <T> T exceptionToNull(Function0<? extends T> function0) {
        try {
            return function0.invoke();
        } catch (JsonDecodingException unused) {
            return null;
        }
    }

    public static final <T> T exceptionToNumberFormatException(Function0<? extends T> function0) {
        try {
            return function0.invoke();
        } catch (JsonDecodingException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    public static final boolean getBoolean(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        Boolean booleanStrictOrNull = StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
        if (booleanStrictOrNull != null) {
            return booleanStrictOrNull.booleanValue();
        }
        throw new IllegalStateException(jsonPrimitive + " does not represent a Boolean");
    }

    @Nullable
    public static final Boolean getBooleanOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
    }

    @Nullable
    public static final String getContentOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        if (jsonPrimitive instanceof JsonNull) {
            return null;
        }
        return jsonPrimitive.getContent();
    }

    public static final double getDouble(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Double.parseDouble(jsonPrimitive.getContent());
    }

    @Nullable
    public static final Double getDoubleOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(jsonPrimitive.getContent());
    }

    public static final float getFloat(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Float.parseFloat(jsonPrimitive.getContent());
    }

    @Nullable
    public static final Float getFloatOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(jsonPrimitive.getContent());
    }

    public static final int getInt(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        try {
            long longImpl = parseLongImpl(jsonPrimitive);
            if (-2147483648L <= longImpl && longImpl <= 2147483647L) {
                return (int) longImpl;
            }
            throw new NumberFormatException(jsonPrimitive.getContent() + " is not an Int");
        } catch (JsonDecodingException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    @Nullable
    public static final Integer getIntOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Long lValueOf;
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        try {
            lValueOf = Long.valueOf(parseLongImpl(jsonPrimitive));
        } catch (JsonDecodingException unused) {
            lValueOf = null;
        }
        if (lValueOf != null) {
            long jLongValue = lValueOf.longValue();
            if (-2147483648L <= jLongValue && jLongValue <= 2147483647L) {
                return Integer.valueOf((int) jLongValue);
            }
        }
        return null;
    }

    @NotNull
    public static final JsonArray getJsonArray(@NotNull JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonArray jsonArray = jsonElement instanceof JsonArray ? (JsonArray) jsonElement : null;
        if (jsonArray != null) {
            return jsonArray;
        }
        error(jsonElement, "JsonArray");
        throw null;
    }

    @NotNull
    public static final JsonNull getJsonNull(@NotNull JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonNull jsonNull = jsonElement instanceof JsonNull ? (JsonNull) jsonElement : null;
        if (jsonNull != null) {
            return jsonNull;
        }
        error(jsonElement, "JsonNull");
        throw null;
    }

    @NotNull
    public static final JsonObject getJsonObject(@NotNull JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonObject jsonObject = jsonElement instanceof JsonObject ? (JsonObject) jsonElement : null;
        if (jsonObject != null) {
            return jsonObject;
        }
        error(jsonElement, "JsonObject");
        throw null;
    }

    @NotNull
    public static final JsonPrimitive getJsonPrimitive(@NotNull JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonPrimitive jsonPrimitive = jsonElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        error(jsonElement, "JsonPrimitive");
        throw null;
    }

    @NotNull
    public static final SerialDescriptor getJsonUnquotedLiteralDescriptor() {
        return jsonUnquotedLiteralDescriptor;
    }

    public static final long getLong(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        try {
            return parseLongImpl(jsonPrimitive);
        } catch (JsonDecodingException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    @Nullable
    public static final Long getLongOrNull(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        try {
            return Long.valueOf(parseLongImpl(jsonPrimitive));
        } catch (JsonDecodingException unused) {
            return null;
        }
    }

    public static final long parseLongImpl(@NotNull JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return new StringJsonLexer(jsonPrimitive.getContent()).consumeNumericLiteralFully();
    }

    @PublishedApi
    @NotNull
    public static final Void unexpectedJson(@NotNull String key, @NotNull String expected) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(expected, "expected");
        throw new IllegalArgumentException("Element " + key + " is not a " + expected);
    }

    @NotNull
    public static final JsonPrimitive JsonPrimitive(@Nullable Number number) {
        if (number == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(number, false, null, 4, null);
    }

    @NotNull
    public static final JsonPrimitive JsonPrimitive(@Nullable String str) {
        if (str == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(str, true, null, 4, null);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final JsonNull JsonPrimitive(@Nullable Void r0) {
        return JsonNull.INSTANCE;
    }
}
