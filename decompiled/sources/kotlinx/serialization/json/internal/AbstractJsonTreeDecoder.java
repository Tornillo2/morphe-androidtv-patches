package kotlinx.serialization.json.internal;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nTreeJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 4 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 5 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,342:1\n73#1:367\n73#1:381\n73#1:392\n73#1:402\n74#1:427\n74#1:436\n84#1:445\n74#1:446\n87#1:455\n74#1:456\n88#1,5:465\n87#1:470\n74#1:471\n88#1,5:480\n87#1:485\n74#1:486\n88#1,5:495\n87#1:500\n74#1:501\n88#1,5:510\n87#1:515\n74#1:516\n88#1,5:525\n87#1:530\n74#1:531\n88#1,5:540\n87#1:545\n74#1:546\n88#1,5:555\n87#1:560\n74#1:561\n88#1,5:570\n74#1:575\n84#1:584\n74#1:585\n1#2:343\n78#3,6:344\n84#3,9:358\n270#4,8:350\n270#4,8:368\n270#4,8:382\n270#4,8:393\n270#4,8:403\n270#4,8:411\n270#4,8:419\n270#4,8:428\n270#4,8:437\n270#4,8:447\n270#4,8:457\n270#4,8:472\n270#4,8:487\n270#4,8:502\n270#4,8:517\n270#4,8:532\n270#4,8:547\n270#4,8:562\n270#4,8:576\n270#4,8:586\n36#5,5:376\n41#5,2:390\n44#5:401\n*S KotlinDebug\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n*L\n63#1:367\n66#1:381\n67#1:392\n69#1:402\n84#1:427\n87#1:436\n104#1:445\n104#1:446\n111#1:455\n111#1:456\n111#1:465,5\n113#1:470\n113#1:471\n113#1:480,5\n119#1:485\n119#1:486\n119#1:495,5\n125#1:500\n125#1:501\n125#1:510,5\n131#1:515\n131#1:516\n131#1:525,5\n134#1:530\n134#1:531\n134#1:540,5\n141#1:545\n141#1:546\n141#1:555,5\n147#1:560\n147#1:561\n147#1:570,5\n150#1:575\n163#1:584\n163#1:585\n55#1:344,6\n55#1:358,9\n55#1:350,8\n63#1:368,8\n66#1:382,8\n67#1:393,8\n69#1:403,8\n73#1:411,8\n74#1:419,8\n84#1:428,8\n87#1:437,8\n104#1:447,8\n111#1:457,8\n113#1:472,8\n119#1:487,8\n125#1:502,8\n131#1:517,8\n134#1:532,8\n141#1:547,8\n147#1:562,8\n150#1:576,8\n163#1:586,8\n64#1:376,5\n64#1:390,2\n64#1:401\n*E\n"})
public abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {

    @JvmField
    @NotNull
    public final JsonConfiguration configuration;

    @NotNull
    public final Json json;

    @Nullable
    public final String polymorphicDiscriminator;

    @NotNull
    public final JsonElement value;

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, (i & 4) != 0 ? null : str);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement jsonElementCurrentObject = currentObject();
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || (kind instanceof PolymorphicKind)) {
            Json json = getJson();
            String serialName = descriptor.getSerialName();
            if (jsonElementCurrentObject instanceof JsonArray) {
                return new JsonTreeListDecoder(json, (JsonArray) jsonElementCurrentObject);
            }
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonArray.class), sb, ", but had ", jsonElementCurrentObject))).getSimpleName());
            sb.append(" as the serialized body of ");
            sb.append(serialName);
            sb.append(" at element: ");
            sb.append(renderTagStack());
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentObject.toString());
        }
        if (!Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            Json json2 = getJson();
            String serialName2 = descriptor.getSerialName();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json2, (JsonObject) jsonElementCurrentObject, this.polymorphicDiscriminator, null, 8, null);
            }
            StringBuilder sb2 = new StringBuilder("Expected ");
            sb2.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonObject.class), sb2, ", but had ", jsonElementCurrentObject))).getSimpleName());
            sb2.append(" as the serialized body of ");
            sb2.append(serialName2);
            sb2.append(" at element: ");
            sb2.append(renderTagStack());
            throw JsonExceptionsKt.JsonDecodingException(-1, sb2.toString(), jsonElementCurrentObject.toString());
        }
        Json json3 = getJson();
        SerialDescriptor serialDescriptorCarrierDescriptor = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), json3.getSerializersModule());
        SerialKind kind2 = serialDescriptorCarrierDescriptor.getKind();
        if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
            Json json4 = getJson();
            String serialName3 = descriptor.getSerialName();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeMapDecoder(json4, (JsonObject) jsonElementCurrentObject);
            }
            StringBuilder sb3 = new StringBuilder("Expected ");
            sb3.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonObject.class), sb3, ", but had ", jsonElementCurrentObject))).getSimpleName());
            sb3.append(" as the serialized body of ");
            sb3.append(serialName3);
            sb3.append(" at element: ");
            sb3.append(renderTagStack());
            throw JsonExceptionsKt.JsonDecodingException(-1, sb3.toString(), jsonElementCurrentObject.toString());
        }
        if (!json3.configuration.allowStructuredMapKeys) {
            throw JsonExceptionsKt.InvalidKeyKindException(serialDescriptorCarrierDescriptor);
        }
        Json json5 = getJson();
        String serialName4 = descriptor.getSerialName();
        if (jsonElementCurrentObject instanceof JsonArray) {
            return new JsonTreeListDecoder(json5, (JsonArray) jsonElementCurrentObject);
        }
        StringBuilder sb4 = new StringBuilder("Expected ");
        sb4.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonArray.class), sb4, ", but had ", jsonElementCurrentObject))).getSimpleName());
        sb4.append(" as the serialized body of ");
        sb4.append(serialName4);
        sb4.append(" at element: ");
        sb4.append(renderTagStack());
        throw JsonExceptionsKt.JsonDecodingException(-1, sb4.toString(), jsonElementCurrentObject.toString());
    }

    public final <T extends JsonElement> T cast(JsonElement value, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        descriptor.getSerialName();
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    @NotNull
    public abstract JsonElement currentElement(@NotNull String str);

    @NotNull
    public final JsonElement currentObject() {
        JsonElement jsonElementCurrentElement;
        String str = (String) CollectionsKt___CollectionsKt.lastOrNull(this.tagStack);
        return (str == null || (jsonElementCurrentElement = currentElement(str)) == null) ? getValue() : jsonElementCurrentElement;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public Decoder decodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return CollectionsKt___CollectionsKt.lastOrNull(this.tagStack) != null ? super.decodeInline(descriptor) : new JsonPrimitiveDecoder(getJson(), getValue(), this.polymorphicDiscriminator).decodeInline(descriptor);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    @NotNull
    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<? extends T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || getJson().configuration.useArrayPolymorphism) {
            return deserializer.deserialize(this);
        }
        AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) deserializer;
        String strClassDiscriminator = PolymorphicKt.classDiscriminator(abstractPolymorphicSerializer.getDescriptor(), getJson());
        JsonElement jsonElementDecodeJsonElement = decodeJsonElement();
        String serialName = abstractPolymorphicSerializer.getDescriptor().getSerialName();
        if (jsonElementDecodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator);
            try {
                return (T) TreeJsonDecoderKt.readPolymorphicJson(getJson(), strClassDiscriminator, jsonObject, PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, this, jsonElement != null ? JsonElementKt.getContentOrNull(JsonElementKt.getJsonPrimitive(jsonElement)) : null));
            } catch (SerializationException e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                throw JsonExceptionsKt.JsonDecodingException(-1, message, jsonObject.toString());
            }
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonObject.class), sb, ", but had ", jsonElementDecodeJsonElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(renderTagStack());
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementDecodeJsonElement.toString());
    }

    @Nullable
    /* JADX INFO: renamed from: decodeTaggedNull, reason: avoid collision after fix types in other method */
    public Void decodeTaggedNull2(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return null;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    @NotNull
    public Json getJson() {
        return this.json;
    }

    @Nullable
    public final String getPolymorphicDiscriminator() {
        return this.polymorphicDiscriminator;
    }

    @NotNull
    public final JsonPrimitive getPrimitiveValue(@NotNull String tag, @NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        String serialName = descriptor.getSerialName();
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            return (JsonPrimitive) jsonElementCurrentElement;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(renderTagStack(tag));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return getJson().getSerializersModule();
    }

    @NotNull
    public JsonElement getValue() {
        return this.value;
    }

    @NotNull
    public final String renderTagStack(@NotNull String currentTag) {
        Intrinsics.checkNotNullParameter(currentTag, "currentTag");
        return renderTagStack() + '.' + currentTag;
    }

    public final Void unparsedPrimitive(JsonPrimitive jsonPrimitive, String str, String str2) {
        throw JsonExceptionsKt.JsonDecodingException(-1, "Failed to parse literal '" + jsonPrimitive + "' as " + (StringsKt__StringsJVMKt.startsWith$default(str, "i", false, 2, null) ? "an " : "a ").concat(str) + " value at element: " + renderTagStack(str2), currentObject().toString());
    }

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, str);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of boolean at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            Boolean booleanOrNull = JsonElementKt.getBooleanOrNull(jsonPrimitive);
            if (booleanOrNull != null) {
                return booleanOrNull.booleanValue();
            }
            unparsedPrimitive(jsonPrimitive, "boolean", tag);
            throw null;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "boolean", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of byte at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
            Byte bValueOf = (-128 > longImpl || longImpl > 127) ? null : Byte.valueOf((byte) longImpl);
            if (bValueOf != null) {
                return bValueOf.byteValue();
            }
            unparsedPrimitive(jsonPrimitive, "byte", tag);
            throw null;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "byte", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                return StringsKt___StringsKt.single(jsonPrimitive.getContent());
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "char", tag);
                throw null;
            }
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of char at element: ");
        sb.append(renderTagStack(tag));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of double at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            double d = JsonElementKt.getDouble(jsonPrimitive);
            if (getJson().configuration.allowSpecialFloatingPointValues || !(Double.isInfinite(d) || Double.isNaN(d))) {
                return d;
            }
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(Double.valueOf(d), tag, currentObject().toString());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "double", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(@NotNull String tag, @NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Json json = getJson();
        JsonElement jsonElementCurrentElement = currentElement(tag);
        String serialName = enumDescriptor.getSerialName();
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            return JsonNamesMapKt.getJsonNameIndexOrThrow$default(enumDescriptor, json, ((JsonPrimitive) jsonElementCurrentElement).getContent(), null, 4, null);
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(renderTagStack(tag));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of float at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            float f = JsonElementKt.getFloat(jsonPrimitive);
            if (getJson().configuration.allowSpecialFloatingPointValues || !(Float.isInfinite(f) || Float.isNaN(f))) {
                return f;
            }
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(Float.valueOf(f), tag, currentObject().toString());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "float", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    @NotNull
    public Decoder decodeTaggedInline(@NotNull String tag, @NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        if (!StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor)) {
            pushTag(tag);
            return this;
        }
        Json json = getJson();
        JsonElement jsonElementCurrentElement = currentElement(tag);
        String serialName = inlineDescriptor.getSerialName();
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            return new JsonDecoderForUnsignedTypes(StringJsonLexerKt.StringJsonLexer(json, ((JsonPrimitive) jsonElementCurrentElement).getContent()), getJson());
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(renderTagStack(tag));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of int at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
            Integer numValueOf = (-2147483648L > longImpl || longImpl > 2147483647L) ? null : Integer.valueOf((int) longImpl);
            if (numValueOf != null) {
                return numValueOf.intValue();
            }
            unparsedPrimitive(jsonPrimitive, "int", tag);
            throw null;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "int", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                return JsonElementKt.parseLongImpl(jsonPrimitive);
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "long", tag);
                throw null;
            }
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of long at element: ");
        sb.append(renderTagStack(tag));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedNotNullMark(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return currentElement(tag) != JsonNull.INSTANCE;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public /* bridge */ /* synthetic */ Void decodeTaggedNull(String str) {
        decodeTaggedNull2(str);
        return null;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of short at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        try {
            long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
            Short shValueOf = (-32768 > longImpl || longImpl > 32767) ? null : Short.valueOf((short) longImpl);
            if (shValueOf != null) {
                return shValueOf.shortValue();
            }
            unparsedPrimitive(jsonPrimitive, "short", tag);
            throw null;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(jsonPrimitive, "short", tag);
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    @NotNull
    public String decodeTaggedString(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        if (!(jsonElementCurrentElement instanceof JsonPrimitive)) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
            sb.append(" as the serialized body of string at element: ");
            sb.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
        if (!(jsonPrimitive instanceof JsonLiteral)) {
            StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Expected string value for a non-null key '", tag, "', got null literal instead at element: ");
            sbM.append(renderTagStack(tag));
            throw JsonExceptionsKt.JsonDecodingException(-1, sbM.toString(), currentObject().toString());
        }
        JsonLiteral jsonLiteral = (JsonLiteral) jsonPrimitive;
        if (jsonLiteral.isString || getJson().configuration.isLenient) {
            return jsonLiteral.content;
        }
        StringBuilder sbM2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("String literal for key '", tag, "' should be quoted at element: ");
        sbM2.append(renderTagStack(tag));
        sbM2.append(".\nUse 'isLenient = true' in 'Json {}' builder to accept non-compliant JSON.");
        throw JsonExceptionsKt.JsonDecodingException(-1, sbM2.toString(), currentObject().toString());
    }

    public AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str) {
        this.json = json;
        this.value = jsonElement;
        this.polymorphicDiscriminator = str;
        this.configuration = getJson().configuration;
    }

    public final <T extends JsonElement> T cast(JsonElement value, String serialName, String tag) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public final <T> T getPrimitiveValue(String str, String str2, Function1<? super JsonPrimitive, ? extends T> function1) {
        JsonElement jsonElementCurrentElement = currentElement(str);
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                T tInvoke = function1.invoke(jsonPrimitive);
                if (tInvoke != null) {
                    return tInvoke;
                }
                unparsedPrimitive(jsonPrimitive, str2, str);
                throw null;
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, str2, str);
                throw null;
            }
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonPrimitive.class), sb, ", but had ", jsonElementCurrentElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(str2);
        sb.append(" at element: ");
        sb.append(renderTagStack(str));
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentElement.toString());
    }
}
