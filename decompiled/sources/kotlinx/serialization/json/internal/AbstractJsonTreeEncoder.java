package kotlinx.serialization.json.internal;

import kotlin.UByte;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.NamedValueEncoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonElementKt$$ExternalSyntheticBackport1;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nTreeJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeEncoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,279:1\n21#2,12:280\n35#2,15:293\n1#3:292\n36#4,9:308\n*S KotlinDebug\n*F\n+ 1 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeEncoder\n*L\n83#1:280,12\n83#1:293,15\n83#1:292\n153#1:308,9\n*E\n"})
public abstract class AbstractJsonTreeEncoder extends NamedValueEncoder implements JsonEncoder {

    @JvmField
    @NotNull
    public final JsonConfiguration configuration;

    @NotNull
    public final Json json;

    @NotNull
    public final Function1<JsonElement, Unit> nodeConsumer;

    @Nullable
    public String polymorphicDiscriminator;

    @Nullable
    public String polymorphicSerialName;

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends AbstractEncoder {
        public final /* synthetic */ SerialDescriptor $inlineDescriptor;
        public final /* synthetic */ String $tag;

        public AnonymousClass1(String str, SerialDescriptor serialDescriptor) {
            this.$tag = str;
            this.$inlineDescriptor = serialDescriptor;
        }

        @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
        public void encodeString(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            AbstractJsonTreeEncoder.this.putElement(this.$tag, new JsonLiteral(value, false, this.$inlineDescriptor));
        }

        @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
        public SerializersModule getSerializersModule() {
            return AbstractJsonTreeEncoder.this.json.getSerializersModule();
        }
    }

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C01401 extends AbstractEncoder {
        public final /* synthetic */ String $tag;
        public final SerializersModule serializersModule;

        public C01401(String str) {
            this.$tag = str;
            this.serializersModule = AbstractJsonTreeEncoder.this.json.getSerializersModule();
        }

        @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
        public void encodeByte(byte b) {
            putUnquotedString(UByte.m641toStringimpl(b));
        }

        @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
        public void encodeInt(int i) {
            putUnquotedString(Long.toString(((long) i) & 4294967295L, 10));
        }

        @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
        public void encodeLong(long j) {
            putUnquotedString(JsonElementKt$$ExternalSyntheticBackport1.m(j, 10));
        }

        @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
        public void encodeShort(short s) {
            putUnquotedString(UShort.m904toStringimpl(s));
        }

        @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
        public SerializersModule getSerializersModule() {
            return this.serializersModule;
        }

        public final void putUnquotedString(String s) {
            Intrinsics.checkNotNullParameter(s, "s");
            AbstractJsonTreeEncoder.this.putElement(this.$tag, new JsonLiteral(s, false, null, 4, null));
        }
    }

    public /* synthetic */ AbstractJsonTreeEncoder(Json json, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, function1);
    }

    public static final Unit beginStructure$lambda$2(AbstractJsonTreeEncoder abstractJsonTreeEncoder, JsonElement node) {
        Intrinsics.checkNotNullParameter(node, "node");
        abstractJsonTreeEncoder.putElement((String) CollectionsKt___CollectionsKt.last(abstractJsonTreeEncoder.tagStack), node);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor) {
        AbstractJsonTreeEncoder jsonTreeListEncoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Function1<JsonElement, Unit> function1 = CollectionsKt___CollectionsKt.lastOrNull(this.tagStack) == null ? this.nodeConsumer : new Function1() { // from class: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractJsonTreeEncoder.beginStructure$lambda$2(this.f$0, (JsonElement) obj);
            }
        };
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || (kind instanceof PolymorphicKind)) {
            jsonTreeListEncoder = new JsonTreeListEncoder(this.json, function1);
        } else if (Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            Json json = this.json;
            SerialDescriptor serialDescriptorCarrierDescriptor = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), json.getSerializersModule());
            SerialKind kind2 = serialDescriptorCarrierDescriptor.getKind();
            if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
                jsonTreeListEncoder = new JsonTreeMapEncoder(this.json, function1);
            } else {
                if (!json.configuration.allowStructuredMapKeys) {
                    throw JsonExceptionsKt.InvalidKeyKindException(serialDescriptorCarrierDescriptor);
                }
                jsonTreeListEncoder = new JsonTreeListEncoder(this.json, function1);
            }
        } else {
            jsonTreeListEncoder = new JsonTreeEncoder(this.json, function1);
        }
        String str = this.polymorphicDiscriminator;
        if (str != null) {
            if (jsonTreeListEncoder instanceof JsonTreeMapEncoder) {
                JsonTreeMapEncoder jsonTreeMapEncoder = (JsonTreeMapEncoder) jsonTreeListEncoder;
                jsonTreeMapEncoder.putElement("key", JsonElementKt.JsonPrimitive(str));
                String serialName = this.polymorphicSerialName;
                if (serialName == null) {
                    serialName = descriptor.getSerialName();
                }
                jsonTreeMapEncoder.putElement("value", JsonElementKt.JsonPrimitive(serialName));
            } else {
                String serialName2 = this.polymorphicSerialName;
                if (serialName2 == null) {
                    serialName2 = descriptor.getSerialName();
                }
                jsonTreeListEncoder.putElement(str, JsonElementKt.JsonPrimitive(serialName2));
            }
            this.polymorphicDiscriminator = null;
            this.polymorphicSerialName = null;
        }
        return jsonTreeListEncoder;
    }

    @Override // kotlinx.serialization.internal.NamedValueEncoder
    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    @Override // kotlinx.serialization.internal.NamedValueEncoder
    @NotNull
    public String elementName(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return JsonNamesMapKt.getJsonElementName(descriptor, this.json, i);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    @NotNull
    public Encoder encodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (CollectionsKt___CollectionsKt.lastOrNull(this.tagStack) == null) {
            return new JsonPrimitiveEncoder(this.json, this.nodeConsumer).encodeInline(descriptor);
        }
        if (this.polymorphicDiscriminator != null) {
            this.polymorphicSerialName = descriptor.getSerialName();
        }
        return super.encodeInline(descriptor);
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

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        String str = (String) CollectionsKt___CollectionsKt.lastOrNull(this.tagStack);
        if (str == null) {
            this.nodeConsumer.invoke(JsonNull.INSTANCE);
        } else {
            encodeTaggedNull(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007f  */
    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> void encodeSerializableValue(@org.jetbrains.annotations.NotNull kotlinx.serialization.SerializationStrategy<? super T> r4, T r5) {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder.encodeSerializableValue(kotlinx.serialization.SerializationStrategy, java.lang.Object):void");
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void endEncode(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.nodeConsumer.invoke(getCurrent());
    }

    @NotNull
    public abstract JsonElement getCurrent();

    @Override // kotlinx.serialization.json.JsonEncoder
    @NotNull
    public final Json getJson() {
        return this.json;
    }

    @NotNull
    public final Function1<JsonElement, Unit> getNodeConsumer() {
        return this.nodeConsumer;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public final SerializersModule getSerializersModule() {
        return this.json.getSerializersModule();
    }

    public final AnonymousClass1 inlineUnquotedLiteralEncoder(String str, SerialDescriptor serialDescriptor) {
        return new AnonymousClass1(str, serialDescriptor);
    }

    @SuppressAnimalSniffer
    public final C01401 inlineUnsignedNumberEncoder(String str) {
        return new C01401(str);
    }

    public abstract void putElement(@NotNull String str, @NotNull JsonElement jsonElement);

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this.configuration.encodeDefaults;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractJsonTreeEncoder(Json json, Function1<? super JsonElement, Unit> function1) {
        this.json = json;
        this.nodeConsumer = function1;
        this.configuration = json.configuration;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedBoolean(@NotNull String tag, boolean z) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Boolean.valueOf(z)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedByte(@NotNull String tag, byte b) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Byte.valueOf(b)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedChar(@NotNull String tag, char c) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(String.valueOf(c)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedDouble(@NotNull String tag, double d) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Double.valueOf(d)));
        if (this.configuration.allowSpecialFloatingPointValues) {
            return;
        }
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Double.valueOf(d), tag, getCurrent().toString());
        }
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedEnum(@NotNull String tag, @NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        putElement(tag, JsonElementKt.JsonPrimitive(enumDescriptor.getElementName(i)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedFloat(@NotNull String tag, float f) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Float.valueOf(f)));
        if (this.configuration.allowSpecialFloatingPointValues) {
            return;
        }
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Float.valueOf(f), tag, getCurrent().toString());
        }
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    @NotNull
    public Encoder encodeTaggedInline(@NotNull String tag, @NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor)) {
            return new C01401(tag);
        }
        if (StreamingJsonEncoderKt.isUnquotedLiteral(inlineDescriptor)) {
            return new AnonymousClass1(tag, inlineDescriptor);
        }
        pushTag(tag);
        return this;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedInt(@NotNull String tag, int i) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Integer.valueOf(i)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedLong(@NotNull String tag, long j) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Long.valueOf(j)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedNull(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonNull.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedShort(@NotNull String tag, short s) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Short.valueOf(s)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedString(@NotNull String tag, @NotNull String value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        putElement(tag, JsonElementKt.JsonPrimitive(value));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedValue(@NotNull String tag, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        putElement(tag, JsonElementKt.JsonPrimitive(value.toString()));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
    }
}
