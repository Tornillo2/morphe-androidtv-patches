package kotlinx.serialization.json.internal;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nTreeJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonTreeDecoder\n+ 2 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 3 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt$tryCoerceValue$1\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/AbstractJsonTreeDecoder\n+ 6 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n*L\n1#1,342:1\n125#2,22:343\n147#2,4:366\n131#3:365\n1#4:370\n73#5:371\n270#6,8:372\n*S KotlinDebug\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonTreeDecoder\n*L\n204#1:343,22\n204#1:366,4\n204#1:365\n265#1:371\n265#1:372,8\n*E\n"})
public class JsonTreeDecoder extends AbstractJsonTreeDecoder {
    public boolean forceNull;

    @Nullable
    public final SerialDescriptor polyDescriptor;
    public int position;

    @NotNull
    public final JsonObject value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeDecoder(@NotNull Json json, @NotNull JsonObject value, @Nullable String str, @Nullable SerialDescriptor serialDescriptor) {
        super(json, value, str);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.polyDescriptor = serialDescriptor;
    }

    public final boolean absenceIsNull(SerialDescriptor serialDescriptor, int i) {
        boolean z = (getJson().configuration.explicitNulls || serialDescriptor.isElementOptional(i) || !serialDescriptor.getElementDescriptor(i).isNullable()) ? false : true;
        this.forceNull = z;
        return z;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor != this.polyDescriptor) {
            return super.beginStructure(descriptor);
        }
        Json json = getJson();
        JsonElement jsonElementCurrentObject = currentObject();
        String serialName = this.polyDescriptor.getSerialName();
        if (jsonElementCurrentObject instanceof JsonObject) {
            return new JsonTreeDecoder(json, (JsonObject) jsonElementCurrentObject, this.polymorphicDiscriminator, this.polyDescriptor);
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonObject.class), sb, ", but had ", jsonElementCurrentObject))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(renderTagStack());
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementCurrentObject.toString());
    }

    public final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i, String str) {
        Json json = getJson();
        boolean zIsElementOptional = serialDescriptor.isElementOptional(i);
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (!zIsElementOptional || elementDescriptor.isNullable() || !(currentElement(str) instanceof JsonNull)) {
            if (Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) && (!elementDescriptor.isNullable() || !(currentElement(str) instanceof JsonNull))) {
                JsonElement jsonElementCurrentElement = currentElement(str);
                JsonPrimitive jsonPrimitive = jsonElementCurrentElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElementCurrentElement : null;
                String contentOrNull = jsonPrimitive != null ? JsonElementKt.getContentOrNull(jsonPrimitive) : null;
                if (contentOrNull != null) {
                    int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, contentOrNull);
                    boolean z = !json.configuration.explicitNulls && elementDescriptor.isNullable();
                    if (jsonNameIndex != -3 || (!zIsElementOptional && !z)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    @NotNull
    public JsonElement currentElement(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return (JsonElement) MapsKt__MapsKt.getValue(getValue(), tag);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        while (this.position < descriptor.getElementsCount()) {
            int i = this.position;
            this.position = i + 1;
            String tag = getTag(descriptor, i);
            int i2 = this.position - 1;
            this.forceNull = false;
            if (getValue().containsKey((Object) tag) || absenceIsNull(descriptor, i2)) {
                if (!this.configuration.coerceInputValues || !coerceInputValue(descriptor, i2, tag)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !this.forceNull && super.decodeNotNullMark();
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    @NotNull
    public String elementName(@NotNull SerialDescriptor descriptor, int i) {
        Object next;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        String elementName = descriptor.getElementName(i);
        if (jsonNamingStrategyNamingStrategy != null || (this.configuration.useAlternativeNames && !getValue().content.keySet().contains(elementName))) {
            Map<String, Integer> mapDeserializationNamesMap = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor);
            Iterator<T> it = getValue().content.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Integer num = mapDeserializationNamesMap.get((String) next);
                if (num != null && num.intValue() == i) {
                    break;
                }
            }
            String str = (String) next;
            if (str != null) {
                return str;
            }
            String strSerialNameForJson = jsonNamingStrategyNamingStrategy != null ? jsonNamingStrategyNamingStrategy.serialNameForJson(descriptor, i, elementName) : null;
            if (strSerialNameForJson != null) {
                return strSerialNameForJson;
            }
        }
        return elementName;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Set<String> setPlus;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (JsonNamesMapKt.ignoreUnknownKeys(descriptor, getJson()) || (descriptor.getKind() instanceof PolymorphicKind)) {
            return;
        }
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        if (jsonNamingStrategyNamingStrategy == null && !this.configuration.useAlternativeNames) {
            setPlus = Platform_commonKt.cachedSerialNames(descriptor);
        } else if (jsonNamingStrategyNamingStrategy != null) {
            setPlus = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor).keySet();
        } else {
            Set<String> setCachedSerialNames = Platform_commonKt.cachedSerialNames(descriptor);
            Map map = (Map) JsonSchemaCacheKt.getSchemaCache(getJson()).get(descriptor, JsonNamesMapKt.JsonDeserializationNamesKey);
            Set setKeySet = map != null ? map.keySet() : null;
            if (setKeySet == null) {
                setKeySet = EmptySet.INSTANCE;
            }
            setPlus = SetsKt___SetsKt.plus((Set) setCachedSerialNames, (Iterable) setKeySet);
        }
        for (String str : getValue().content.keySet()) {
            if (!setPlus.contains(str) && !Intrinsics.areEqual(str, this.polymorphicDiscriminator)) {
                StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Encountered an unknown key '", str, "' at element: ");
                sbM.append(renderTagStack());
                sbM.append("\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys.\nJSON input: ");
                sbM.append((Object) JsonExceptionsKt.minify$default(getValue().toString(), 0, 1, null));
                throw JsonExceptionsKt.JsonDecodingException(-1, sbM.toString());
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    @NotNull
    public JsonObject getValue() {
        return this.value;
    }

    public /* synthetic */ JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonObject, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : serialDescriptor);
    }
}
