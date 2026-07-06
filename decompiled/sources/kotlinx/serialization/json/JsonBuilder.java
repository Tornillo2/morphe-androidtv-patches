package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJson.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,724:1\n1069#2,2:725\n*S KotlinDebug\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n*L\n687#1:725,2\n*E\n"})
public final class JsonBuilder {
    public boolean allowComments;
    public boolean allowSpecialFloatingPointValues;
    public boolean allowStructuredMapKeys;
    public boolean allowTrailingComma;

    @NotNull
    public String classDiscriminator;

    @NotNull
    public ClassDiscriminatorMode classDiscriminatorMode;
    public boolean coerceInputValues;
    public boolean decodeEnumsCaseInsensitive;
    public boolean encodeDefaults;
    public boolean explicitNulls;
    public boolean ignoreUnknownKeys;
    public boolean isLenient;

    @Nullable
    public JsonNamingStrategy namingStrategy;
    public boolean prettyPrint;

    @NotNull
    public String prettyPrintIndent;

    @NotNull
    public SerializersModule serializersModule;
    public boolean useAlternativeNames;
    public boolean useArrayPolymorphism;

    public JsonBuilder(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        JsonConfiguration jsonConfiguration = json.configuration;
        this.encodeDefaults = jsonConfiguration.encodeDefaults;
        this.explicitNulls = jsonConfiguration.explicitNulls;
        this.ignoreUnknownKeys = jsonConfiguration.ignoreUnknownKeys;
        this.isLenient = jsonConfiguration.isLenient;
        this.prettyPrint = jsonConfiguration.prettyPrint;
        this.prettyPrintIndent = jsonConfiguration.prettyPrintIndent;
        this.coerceInputValues = jsonConfiguration.coerceInputValues;
        this.classDiscriminator = jsonConfiguration.classDiscriminator;
        this.classDiscriminatorMode = jsonConfiguration.classDiscriminatorMode;
        this.useAlternativeNames = jsonConfiguration.useAlternativeNames;
        this.namingStrategy = jsonConfiguration.namingStrategy;
        this.decodeEnumsCaseInsensitive = jsonConfiguration.decodeEnumsCaseInsensitive;
        this.allowTrailingComma = jsonConfiguration.allowTrailingComma;
        this.allowComments = jsonConfiguration.allowComments;
        this.allowSpecialFloatingPointValues = jsonConfiguration.allowSpecialFloatingPointValues;
        this.allowStructuredMapKeys = jsonConfiguration.allowStructuredMapKeys;
        this.useArrayPolymorphism = jsonConfiguration.useArrayPolymorphism;
        this.serializersModule = json.getSerializersModule();
    }

    @NotNull
    public final JsonConfiguration build$kotlinx_serialization_json() {
        if (this.useArrayPolymorphism) {
            if (!Intrinsics.areEqual(this.classDiscriminator, "type")) {
                throw new IllegalArgumentException("Class discriminator should not be specified when array polymorphism is specified");
            }
            if (this.classDiscriminatorMode != ClassDiscriminatorMode.POLYMORPHIC) {
                throw new IllegalArgumentException("useArrayPolymorphism option can only be used if classDiscriminatorMode in a default POLYMORPHIC state.");
            }
        }
        if (this.prettyPrint) {
            if (!Intrinsics.areEqual(this.prettyPrintIndent, JsonKt.defaultIndent)) {
                String str = this.prettyPrintIndent;
                for (int i = 0; i < str.length(); i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt != ' ' && cCharAt != '\t' && cCharAt != '\r' && cCharAt != '\n') {
                        throw new IllegalArgumentException(("Only whitespace, tab, newline and carriage return are allowed as pretty print symbols. Had " + this.prettyPrintIndent).toString());
                    }
                }
            }
        } else if (!Intrinsics.areEqual(this.prettyPrintIndent, JsonKt.defaultIndent)) {
            throw new IllegalArgumentException("Indent should not be specified when default printing mode is used");
        }
        return new JsonConfiguration(this.encodeDefaults, this.ignoreUnknownKeys, this.isLenient, this.allowStructuredMapKeys, this.prettyPrint, this.explicitNulls, this.prettyPrintIndent, this.coerceInputValues, this.useArrayPolymorphism, this.classDiscriminator, this.allowSpecialFloatingPointValues, this.useAlternativeNames, this.namingStrategy, this.decodeEnumsCaseInsensitive, this.allowTrailingComma, this.allowComments, this.classDiscriminatorMode);
    }

    public final boolean getAllowComments() {
        return this.allowComments;
    }

    public final boolean getAllowSpecialFloatingPointValues() {
        return this.allowSpecialFloatingPointValues;
    }

    public final boolean getAllowStructuredMapKeys() {
        return this.allowStructuredMapKeys;
    }

    public final boolean getAllowTrailingComma() {
        return this.allowTrailingComma;
    }

    @NotNull
    public final String getClassDiscriminator() {
        return this.classDiscriminator;
    }

    @NotNull
    public final ClassDiscriminatorMode getClassDiscriminatorMode() {
        return this.classDiscriminatorMode;
    }

    public final boolean getCoerceInputValues() {
        return this.coerceInputValues;
    }

    public final boolean getDecodeEnumsCaseInsensitive() {
        return this.decodeEnumsCaseInsensitive;
    }

    public final boolean getEncodeDefaults() {
        return this.encodeDefaults;
    }

    public final boolean getExplicitNulls() {
        return this.explicitNulls;
    }

    public final boolean getIgnoreUnknownKeys() {
        return this.ignoreUnknownKeys;
    }

    @Nullable
    public final JsonNamingStrategy getNamingStrategy() {
        return this.namingStrategy;
    }

    public final boolean getPrettyPrint() {
        return this.prettyPrint;
    }

    @NotNull
    public final String getPrettyPrintIndent() {
        return this.prettyPrintIndent;
    }

    @NotNull
    public final SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public final boolean getUseAlternativeNames() {
        return this.useAlternativeNames;
    }

    public final boolean getUseArrayPolymorphism() {
        return this.useArrayPolymorphism;
    }

    public final boolean isLenient() {
        return this.isLenient;
    }

    public final void setAllowComments(boolean z) {
        this.allowComments = z;
    }

    public final void setAllowSpecialFloatingPointValues(boolean z) {
        this.allowSpecialFloatingPointValues = z;
    }

    public final void setAllowStructuredMapKeys(boolean z) {
        this.allowStructuredMapKeys = z;
    }

    public final void setAllowTrailingComma(boolean z) {
        this.allowTrailingComma = z;
    }

    public final void setClassDiscriminator(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.classDiscriminator = str;
    }

    public final void setClassDiscriminatorMode(@NotNull ClassDiscriminatorMode classDiscriminatorMode) {
        Intrinsics.checkNotNullParameter(classDiscriminatorMode, "<set-?>");
        this.classDiscriminatorMode = classDiscriminatorMode;
    }

    public final void setCoerceInputValues(boolean z) {
        this.coerceInputValues = z;
    }

    public final void setDecodeEnumsCaseInsensitive(boolean z) {
        this.decodeEnumsCaseInsensitive = z;
    }

    public final void setEncodeDefaults(boolean z) {
        this.encodeDefaults = z;
    }

    public final void setExplicitNulls(boolean z) {
        this.explicitNulls = z;
    }

    public final void setIgnoreUnknownKeys(boolean z) {
        this.ignoreUnknownKeys = z;
    }

    public final void setLenient(boolean z) {
        this.isLenient = z;
    }

    public final void setNamingStrategy(@Nullable JsonNamingStrategy jsonNamingStrategy) {
        this.namingStrategy = jsonNamingStrategy;
    }

    public final void setPrettyPrint(boolean z) {
        this.prettyPrint = z;
    }

    public final void setPrettyPrintIndent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.prettyPrintIndent = str;
    }

    public final void setSerializersModule(@NotNull SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(serializersModule, "<set-?>");
        this.serializersModule = serializersModule;
    }

    public final void setUseAlternativeNames(boolean z) {
        this.useAlternativeNames = z;
    }

    public final void setUseArrayPolymorphism(boolean z) {
        this.useArrayPolymorphism = z;
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getAllowComments$annotations() {
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getAllowTrailingComma$annotations() {
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getClassDiscriminatorMode$annotations() {
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getDecodeEnumsCaseInsensitive$annotations() {
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getNamingStrategy$annotations() {
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getPrettyPrintIndent$annotations() {
    }
}
