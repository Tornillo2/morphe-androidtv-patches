package kotlinx.serialization.json.internal;

import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.ChunkedDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nStreamingJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 4 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 5 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 6 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoderKt\n*L\n1#1,392:1\n78#2,6:393\n84#2,9:407\n270#3,8:399\n517#4,3:416\n517#4,3:419\n133#5,18:422\n385#6,5:440\n385#6,5:445\n*S KotlinDebug\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n*L\n75#1:393,6\n75#1:407,9\n75#1:399,8\n202#1:416,3\n203#1:419,3\n215#1:422,18\n309#1:440,5\n316#1:445,5\n*E\n"})
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder, ChunkedDecoder {

    @NotNull
    public final JsonConfiguration configuration;
    public int currentIndex;

    @Nullable
    public DiscriminatorHolder discriminatorHolder;

    @Nullable
    public final JsonElementMarker elementMarker;

    @NotNull
    public final Json json;

    @JvmField
    @NotNull
    public final AbstractJsonLexer lexer;

    @NotNull
    public final WriteMode mode;

    @NotNull
    public final SerializersModule serializersModule;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DiscriminatorHolder {

        @JvmField
        @Nullable
        public String discriminatorToSkip;

        public DiscriminatorHolder(@Nullable String str) {
            this.discriminatorToSkip = str;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            try {
                iArr[WriteMode.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WriteMode.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WriteMode.OBJ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StreamingJsonDecoder(@NotNull Json json, @NotNull WriteMode mode, @NotNull AbstractJsonLexer lexer, @NotNull SerialDescriptor descriptor, @Nullable DiscriminatorHolder discriminatorHolder) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = discriminatorHolder;
        JsonConfiguration jsonConfiguration = json.configuration;
        this.configuration = jsonConfiguration;
        this.elementMarker = jsonConfiguration.explicitNulls ? null : new JsonElementMarker(descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode writeModeSwitchMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.path.pushDescriptor(descriptor);
        this.lexer.consumeNextToken(writeModeSwitchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[writeModeSwitchMode.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder) : (this.mode == writeModeSwitchMode && this.json.configuration.explicitNulls) ? this : new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder);
    }

    public final void checkLeadingComma() {
        if (this.lexer.peekNextToken() != 4) {
            return;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        throw null;
    }

    public final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i) {
        String strPeekString;
        Json json = this.json;
        boolean zIsElementOptional = serialDescriptor.isElementOptional(i);
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (zIsElementOptional && !elementDescriptor.isNullable() && this.lexer.tryConsumeNull(true)) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && this.lexer.tryConsumeNull(false)) || (strPeekString = this.lexer.peekString(this.configuration.isLenient)) == null)) {
            return false;
        }
        int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, strPeekString);
        boolean z = !json.configuration.explicitNulls && elementDescriptor.isNullable();
        if (jsonNameIndex == -3 && (zIsElementOptional || z)) {
            this.lexer.consumeString();
            return true;
        }
        return false;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        return this.lexer.consumeBooleanLenient();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() throws Throwable {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == b) {
            return b;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse byte for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String strConsumeStringLenient = this.lexer.consumeStringLenient();
        if (strConsumeStringLenient.length() == 1) {
            return strConsumeStringLenient.charAt(0);
        }
        AbstractJsonLexer.fail$default(this.lexer, "Expected single char, but got '" + strConsumeStringLenient + '\'', 0, null, 6, null);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            double d = Double.parseDouble(strConsumeStringLenient);
            if (this.json.configuration.allowSpecialFloatingPointValues) {
                return d;
            }
            if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                return d;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(d));
            throw null;
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'double' for input '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw null;
        }
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        int iDecodeListIndex = i != 2 ? i != 4 ? decodeListIndex() : decodeObjectIndex(descriptor) : decodeMapIndex();
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(iDecodeListIndex);
        }
        return iDecodeListIndex;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(@NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString(), " at path " + this.lexer.path.getPath());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            float f = Float.parseFloat(strConsumeStringLenient);
            if (this.json.configuration.allowSpecialFloatingPointValues) {
                return f;
            }
            if (!Float.isInfinite(f) && !Float.isNaN(f)) {
                return f;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(f));
            throw null;
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'float' for input '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw null;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public Decoder decodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(descriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : this;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() throws Throwable {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == i) {
            return i;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw null;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    @NotNull
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.configuration, this.lexer).read();
    }

    public final int decodeListIndex() {
        boolean zTryConsumeComma = this.lexer.tryConsumeComma();
        if (!this.lexer.canConsumeValue()) {
            if (!zTryConsumeComma || this.json.configuration.allowTrailingComma) {
                return -1;
            }
            JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
            throw null;
        }
        int i = this.currentIndex;
        if (i != -1 && !zTryConsumeComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
            throw null;
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    public final int decodeMapIndex() {
        int i = this.currentIndex;
        boolean zTryConsumeComma = false;
        boolean z = i % 2 != 0;
        if (!z) {
            this.lexer.consumeNextToken(':');
        } else if (i != -1) {
            zTryConsumeComma = this.lexer.tryConsumeComma();
        }
        if (!this.lexer.canConsumeValue()) {
            if (!zTryConsumeComma || this.json.configuration.allowTrailingComma) {
                return -1;
            }
            JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
            throw null;
        }
        if (z) {
            if (this.currentIndex == -1) {
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                int i2 = abstractJsonLexer.currentPosition;
                if (zTryConsumeComma) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected leading comma", i2, null, 4, null);
                    throw null;
                }
            } else {
                AbstractJsonLexer abstractJsonLexer2 = this.lexer;
                int i3 = abstractJsonLexer2.currentPosition;
                if (!zTryConsumeComma) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer2, "Expected comma after the key-value pair", i3, null, 4, null);
                    throw null;
                }
            }
        }
        int i4 = this.currentIndex + 1;
        this.currentIndex = i4;
        return i4;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return ((jsonElementMarker != null ? jsonElementMarker.isUnmarkedNull : false) || AbstractJsonLexer.tryConsumeNull$default(this.lexer, false, 1, null)) ? false : true;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    @Nullable
    public Void decodeNull() {
        return null;
    }

    public final int decodeObjectIndex(SerialDescriptor serialDescriptor) {
        int jsonNameIndex;
        boolean zTryConsumeComma;
        boolean zTryConsumeComma2 = this.lexer.tryConsumeComma();
        while (true) {
            boolean z = true;
            if (!this.lexer.canConsumeValue()) {
                if (zTryConsumeComma2 && !this.json.configuration.allowTrailingComma) {
                    JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
                    throw null;
                }
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker != null) {
                    return jsonElementMarker.origin.nextUnmarkedIndex();
                }
                return -1;
            }
            String strDecodeStringKey = decodeStringKey();
            this.lexer.consumeNextToken(':');
            jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(serialDescriptor, this.json, strDecodeStringKey);
            if (jsonNameIndex == -3) {
                zTryConsumeComma = false;
            } else {
                if (!this.configuration.coerceInputValues || !coerceInputValue(serialDescriptor, jsonNameIndex)) {
                    break;
                }
                zTryConsumeComma = this.lexer.tryConsumeComma();
                z = false;
            }
            zTryConsumeComma2 = z ? handleUnknown(serialDescriptor, strDecodeStringKey) : zTryConsumeComma;
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            jsonElementMarker2.mark$kotlinx_serialization_json(jsonNameIndex);
        }
        return jsonNameIndex;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<? extends T> deserializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        boolean z = this.mode == WriteMode.MAP && (i & 1) == 0;
        if (z) {
            this.lexer.path.resetCurrentMapKey();
        }
        T t2 = (T) super.decodeSerializableElement(descriptor, i, deserializer, t);
        if (z) {
            this.lexer.path.updateCurrentMapKey(t2);
        }
        return t2;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0145  */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T decodeSerializableValue(@org.jetbrains.annotations.NotNull kotlinx.serialization.DeserializationStrategy<? extends T> r12) {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonDecoder.decodeSerializableValue(kotlinx.serialization.DeserializationStrategy):java.lang.Object");
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() throws Throwable {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == s) {
            return s;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse short for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public String decodeString() {
        return this.configuration.isLenient ? this.lexer.consumeStringLenientNotNull() : this.lexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.ChunkedDecoder
    public void decodeStringChunked(@NotNull Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        this.lexer.consumeStringChunked(this.configuration.isLenient, consumeChunk);
    }

    public final String decodeStringKey() {
        return this.configuration.isLenient ? this.lexer.consumeStringLenientNotNull() : this.lexer.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor.getElementsCount() == 0 && JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.json)) {
            skipLeftoverElements(descriptor);
        }
        if (this.lexer.tryConsumeComma() && !this.json.configuration.allowTrailingComma) {
            JsonExceptionsKt.invalidTrailingComma(this.lexer, "");
            throw null;
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    @NotNull
    public final Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public final boolean handleUnknown(SerialDescriptor serialDescriptor, String str) {
        if (JsonNamesMapKt.ignoreUnknownKeys(serialDescriptor, this.json) || trySkip(this.discriminatorHolder, str)) {
            this.lexer.skipElement(this.configuration.isLenient);
            return this.lexer.tryConsumeComma();
        }
        this.lexer.path.popDescriptor();
        this.lexer.failOnUnknownKey(str);
        throw null;
    }

    public final void skipLeftoverElements(SerialDescriptor serialDescriptor) {
        while (decodeElementIndex(serialDescriptor) != -1) {
        }
    }

    public final boolean trySkip(DiscriminatorHolder discriminatorHolder, String str) {
        if (discriminatorHolder == null || !Intrinsics.areEqual(discriminatorHolder.discriminatorToSkip, str)) {
            return false;
        }
        discriminatorHolder.discriminatorToSkip = null;
        return true;
    }
}
