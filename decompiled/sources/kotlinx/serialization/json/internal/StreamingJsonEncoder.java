package kotlinx.serialization.json.internal;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nStreamingJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,232:1\n178#1,2:261\n178#1,2:263\n21#2,12:233\n35#2,15:246\n1#3:245\n1#3:265\n*S KotlinDebug\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n*L\n168#1:261,2\n169#1:263,2\n68#1:233,12\n68#1:246,15\n68#1:245\n*E\n"})
public final class StreamingJsonEncoder extends AbstractEncoder implements JsonEncoder {

    @NotNull
    public final Composer composer;

    @NotNull
    public final JsonConfiguration configuration;
    public boolean forceQuoting;

    @NotNull
    public final Json json;

    @NotNull
    public final WriteMode mode;

    @Nullable
    public final JsonEncoder[] modeReuseCache;

    @Nullable
    public String polymorphicDiscriminator;

    @Nullable
    public String polymorphicSerialName;

    @NotNull
    public final SerializersModule serializersModule;

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
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StreamingJsonEncoder(@NotNull Composer composer, @NotNull Json json, @NotNull WriteMode mode, @Nullable JsonEncoder[] jsonEncoderArr) {
        Intrinsics.checkNotNullParameter(composer, "composer");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.composer = composer;
        this.json = json;
        this.mode = mode;
        this.modeReuseCache = jsonEncoderArr;
        this.serializersModule = json.getSerializersModule();
        this.configuration = json.configuration;
        int iOrdinal = mode.ordinal();
        if (jsonEncoderArr != null) {
            JsonEncoder jsonEncoder = jsonEncoderArr[iOrdinal];
            if (jsonEncoder == null && jsonEncoder == this) {
                return;
            }
            jsonEncoderArr[iOrdinal] = this;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor) {
        JsonEncoder jsonEncoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode writeModeSwitchMode = WriteModeKt.switchMode(this.json, descriptor);
        char c = writeModeSwitchMode.begin;
        if (c != 0) {
            this.composer.print(c);
            this.composer.indent();
        }
        String str = this.polymorphicDiscriminator;
        if (str != null) {
            String serialName = this.polymorphicSerialName;
            if (serialName == null) {
                serialName = descriptor.getSerialName();
            }
            encodeTypeInfo(str, serialName);
            this.polymorphicDiscriminator = null;
            this.polymorphicSerialName = null;
        }
        if (this.mode == writeModeSwitchMode) {
            return this;
        }
        JsonEncoder[] jsonEncoderArr = this.modeReuseCache;
        return (jsonEncoderArr == null || (jsonEncoder = jsonEncoderArr[writeModeSwitchMode.ordinal()]) == null) ? new StreamingJsonEncoder(this.composer, this.json, writeModeSwitchMode, this.modeReuseCache) : jsonEncoder;
    }

    public final <T extends Composer> T composerAs(Function2<? super InternalJsonWriter, ? super Boolean, ? extends T> function2) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean z) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(z));
        } else {
            this.composer.print(z);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte b) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) b));
        } else {
            this.composer.print(b);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeChar(char c) {
        encodeString(String.valueOf(c));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double d) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(d));
        } else {
            this.composer.print(d);
        }
        if (this.configuration.allowSpecialFloatingPointValues) {
            return;
        }
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Double.valueOf(d), this.composer.writer.toString());
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public boolean encodeElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i2 == 1) {
            Composer composer = this.composer;
            if (!composer.writingFirst) {
                composer.print(',');
            }
            this.composer.nextItem();
            return true;
        }
        boolean z = false;
        if (i2 == 2) {
            Composer composer2 = this.composer;
            if (composer2.writingFirst) {
                this.forceQuoting = true;
                composer2.nextItem();
                return true;
            }
            if (i % 2 == 0) {
                composer2.print(',');
                this.composer.nextItem();
                z = true;
            } else {
                composer2.print(':');
                this.composer.space();
            }
            this.forceQuoting = z;
            return true;
        }
        if (i2 == 3) {
            if (i == 0) {
                this.forceQuoting = true;
            }
            if (i == 1) {
                this.composer.print(',');
                this.composer.space();
                this.forceQuoting = false;
            }
            return true;
        }
        Composer composer3 = this.composer;
        if (!composer3.writingFirst) {
            composer3.print(',');
        }
        this.composer.nextItem();
        encodeString(JsonNamesMapKt.getJsonElementName(descriptor, this.json, i));
        this.composer.print(':');
        this.composer.space();
        return true;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeString(enumDescriptor.getElementName(i));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float f) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(f));
        } else {
            this.composer.print(f);
        }
        if (this.configuration.allowSpecialFloatingPointValues) {
            return;
        }
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Float.valueOf(f), this.composer.writer.toString());
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    @NotNull
    public Encoder encodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(descriptor)) {
            Composer composerForUnsignedNumbers = this.composer;
            if (!(composerForUnsignedNumbers instanceof ComposerForUnsignedNumbers)) {
                composerForUnsignedNumbers = new ComposerForUnsignedNumbers(composerForUnsignedNumbers.writer, this.forceQuoting);
            }
            return new StreamingJsonEncoder(composerForUnsignedNumbers, this.json, this.mode, (JsonEncoder[]) null);
        }
        if (!StreamingJsonEncoderKt.isUnquotedLiteral(descriptor)) {
            if (this.polymorphicDiscriminator != null) {
                this.polymorphicSerialName = descriptor.getSerialName();
            }
            return this;
        }
        Composer composerForUnquotedLiterals = this.composer;
        if (!(composerForUnquotedLiterals instanceof ComposerForUnquotedLiterals)) {
            composerForUnquotedLiterals = new ComposerForUnquotedLiterals(composerForUnquotedLiterals.writer, this.forceQuoting);
        }
        return new StreamingJsonEncoder(composerForUnquotedLiterals, this.json, this.mode, (JsonEncoder[]) null);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeInt(int i) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(i));
        } else {
            this.composer.print(i);
        }
    }

    @Override // kotlinx.serialization.json.JsonEncoder
    public void encodeJsonElement(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (this.polymorphicDiscriminator == null || (element instanceof JsonObject)) {
            encodeSerializableValue(JsonElementSerializer.INSTANCE, element);
        } else {
            PolymorphicKt.throwJsonElementPolymorphicException(this.polymorphicSerialName, element);
            throw null;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeLong(long j) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(j));
        } else {
            this.composer.print(j);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        this.composer.print(AbstractJsonLexerKt.NULL);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (t != null || this.configuration.explicitNulls) {
            super.encodeNullableSerializableElement(descriptor, i, serializer, t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> void encodeSerializableValue(@org.jetbrains.annotations.NotNull kotlinx.serialization.SerializationStrategy<? super T> r4, T r5) {
        /*
            r3 = this;
            java.lang.String r0 = "serializer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            kotlinx.serialization.json.Json r0 = r3.json
            kotlinx.serialization.json.JsonConfiguration r0 = r0.configuration
            boolean r1 = r0.useArrayPolymorphism
            if (r1 == 0) goto L11
            r4.serialize(r3, r5)
            return
        L11:
            boolean r1 = r4 instanceof kotlinx.serialization.internal.AbstractPolymorphicSerializer
            if (r1 == 0) goto L1c
            kotlinx.serialization.json.ClassDiscriminatorMode r0 = r0.classDiscriminatorMode
            kotlinx.serialization.json.ClassDiscriminatorMode r2 = kotlinx.serialization.json.ClassDiscriminatorMode.NONE
            if (r0 == r2) goto L58
            goto L47
        L1c:
            kotlinx.serialization.json.ClassDiscriminatorMode r0 = r0.classDiscriminatorMode
            int[] r2 = kotlinx.serialization.json.internal.PolymorphicKt.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r2[r0]
            r2 = 1
            if (r0 == r2) goto L58
            r2 = 2
            if (r0 == r2) goto L58
            r2 = 3
            if (r0 != r2) goto L52
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r4.getDescriptor()
            kotlinx.serialization.descriptors.SerialKind r0 = r0.getKind()
            kotlinx.serialization.descriptors.StructureKind$CLASS r2 = kotlinx.serialization.descriptors.StructureKind.CLASS.INSTANCE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 != 0) goto L47
            kotlinx.serialization.descriptors.StructureKind$OBJECT r2 = kotlinx.serialization.descriptors.StructureKind.OBJECT.INSTANCE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 == 0) goto L58
        L47:
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r4.getDescriptor()
            kotlinx.serialization.json.Json r2 = r3.json
            java.lang.String r0 = kotlinx.serialization.json.internal.PolymorphicKt.classDiscriminator(r0, r2)
            goto L59
        L52:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L58:
            r0 = 0
        L59:
            if (r1 == 0) goto L97
            r1 = r4
            kotlinx.serialization.internal.AbstractPolymorphicSerializer r1 = (kotlinx.serialization.internal.AbstractPolymorphicSerializer) r1
            if (r5 == 0) goto L76
            kotlinx.serialization.SerializationStrategy r1 = kotlinx.serialization.PolymorphicSerializerKt.findPolymorphicSerializer(r1, r3, r5)
            if (r0 == 0) goto L74
            kotlinx.serialization.json.internal.PolymorphicKt.validateIfSealed(r4, r1, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r4 = r1.getDescriptor()
            kotlinx.serialization.descriptors.SerialKind r4 = r4.getKind()
            kotlinx.serialization.json.internal.PolymorphicKt.checkKind(r4)
        L74:
            r4 = r1
            goto L97
        L76:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Value for serializer "
            r4.<init>(r5)
            kotlinx.serialization.descriptors.SerialDescriptor r5 = r1.getDescriptor()
            r4.append(r5)
            java.lang.String r5 = " should always be non-null. Please report issue to the kotlinx.serialization tracker."
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        L97:
            if (r0 == 0) goto La5
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r4.getDescriptor()
            java.lang.String r1 = r1.getSerialName()
            r3.polymorphicDiscriminator = r0
            r3.polymorphicSerialName = r1
        La5:
            r4.serialize(r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonEncoder.encodeSerializableValue(kotlinx.serialization.SerializationStrategy, java.lang.Object):void");
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeShort(short s) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) s));
        } else {
            this.composer.print(s);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeString(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.composer.printQuoted(value);
    }

    public final void encodeTypeInfo(String str, String str2) {
        this.composer.nextItem();
        encodeString(str);
        this.composer.print(':');
        this.composer.space();
        encodeString(str2);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.mode.end != 0) {
            this.composer.unIndent();
            this.composer.nextItemIfNotFirst();
            this.composer.print(this.mode.end);
        }
    }

    @Override // kotlinx.serialization.json.JsonEncoder
    @NotNull
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this.configuration.encodeDefaults;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StreamingJsonEncoder(@NotNull InternalJsonWriter output, @NotNull Json json, @NotNull WriteMode mode, @NotNull JsonEncoder[] modeReuseCache) {
        this(ComposersKt.Composer(output, json), json, mode, modeReuseCache);
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(modeReuseCache, "modeReuseCache");
    }
}
