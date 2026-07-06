package kotlinx.serialization.json.internal;

import androidx.activity.ComponentActivity$$ExternalSyntheticNonNull0;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapWithDefaultKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonIgnoreUnknownKeys;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonNamesMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,155:1\n808#2,11:156\n1755#2,3:170\n13402#3,2:167\n1#4:169\n*S KotlinDebug\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n*L\n35#1:156,11\n154#1:170,3\n35#1:167,2\n*E\n"})
public final class JsonNamesMapKt {

    @NotNull
    public static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonDeserializationNamesKey = new DescriptorSchemaCache.Key<>();

    @NotNull
    public static final DescriptorSchemaCache.Key<String[]> JsonSerializationNamesKey = new DescriptorSchemaCache.Key<>();

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.JsonNamesMapKt$tryCoerceValue$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Function0<Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            return Unit.INSTANCE;
        }
    }

    public static final Map<String, Integer> buildDeserializationNamesMap(SerialDescriptor serialDescriptor, Json json) {
        String strSerialNameForJson;
        String[] strArrNames;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean zDecodeCaseInsensitive = decodeCaseInsensitive(json, serialDescriptor);
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = namingStrategy(serialDescriptor, json);
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            List<Annotation> elementAnnotations = serialDescriptor.getElementAnnotations(i);
            ArrayList arrayList = new ArrayList();
            for (Object obj : elementAnnotations) {
                if (obj instanceof JsonNames) {
                    arrayList.add(obj);
                }
            }
            JsonNames jsonNames = (JsonNames) CollectionsKt___CollectionsKt.singleOrNull((List) arrayList);
            if (jsonNames != null && (strArrNames = jsonNames.names()) != null) {
                for (String lowerCase : strArrNames) {
                    if (zDecodeCaseInsensitive) {
                        lowerCase = lowerCase.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    }
                    buildDeserializationNamesMap$putOrThrow(linkedHashMap, serialDescriptor, lowerCase, i);
                }
            }
            if (zDecodeCaseInsensitive) {
                strSerialNameForJson = serialDescriptor.getElementName(i).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(strSerialNameForJson, "toLowerCase(...)");
            } else {
                strSerialNameForJson = jsonNamingStrategyNamingStrategy != null ? jsonNamingStrategyNamingStrategy.serialNameForJson(serialDescriptor, i, serialDescriptor.getElementName(i)) : null;
            }
            if (strSerialNameForJson != null) {
                buildDeserializationNamesMap$putOrThrow(linkedHashMap, serialDescriptor, strSerialNameForJson, i);
            }
        }
        return linkedHashMap.isEmpty() ? MapsKt__MapsKt.emptyMap() : linkedHashMap;
    }

    public static final void buildDeserializationNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor serialDescriptor, String str, int i) {
        String str2 = Intrinsics.areEqual(serialDescriptor.getKind(), SerialKind.ENUM.INSTANCE) ? "enum value" : "property";
        if (!map.containsKey(str)) {
            map.put(str, Integer.valueOf(i));
            return;
        }
        throw new JsonException("The suggested name '" + str + "' for " + str2 + ' ' + serialDescriptor.getElementName(i) + " is already one of the names for " + str2 + ' ' + serialDescriptor.getElementName(((Number) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, str)).intValue()) + " in " + serialDescriptor);
    }

    public static final boolean decodeCaseInsensitive(Json json, SerialDescriptor serialDescriptor) {
        return json.configuration.decodeEnumsCaseInsensitive && Intrinsics.areEqual(serialDescriptor.getKind(), SerialKind.ENUM.INSTANCE);
    }

    @NotNull
    public static final Map<String, Integer> deserializationNamesMap(@NotNull final Json json, @NotNull final SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return (Map) json._schemaCache.getOrPut(descriptor, JsonDeserializationNamesKey, new Function0() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsonNamesMapKt.buildDeserializationNamesMap(descriptor, json);
            }
        });
    }

    @NotNull
    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonDeserializationNamesKey() {
        return JsonDeserializationNamesKey;
    }

    @NotNull
    public static final String getJsonElementName(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = namingStrategy(serialDescriptor, json);
        return jsonNamingStrategyNamingStrategy == null ? serialDescriptor.getElementName(i) : serializationNamesIndices(serialDescriptor, json, jsonNamingStrategyNamingStrategy)[i];
    }

    public static final int getJsonNameIndex(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json, @NotNull String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        if (decodeCaseInsensitive(json, serialDescriptor)) {
            String lowerCase = name.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return getJsonNameIndexSlowPath(serialDescriptor, json, lowerCase);
        }
        if (namingStrategy(serialDescriptor, json) != null) {
            return getJsonNameIndexSlowPath(serialDescriptor, json, name);
        }
        int elementIndex = serialDescriptor.getElementIndex(name);
        return (elementIndex == -3 && json.configuration.useAlternativeNames) ? getJsonNameIndexSlowPath(serialDescriptor, json, name) : elementIndex;
    }

    public static final int getJsonNameIndexOrThrow(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json, @NotNull String name, @NotNull String suffix) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, name);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + name + '\'' + suffix);
    }

    public static /* synthetic */ int getJsonNameIndexOrThrow$default(SerialDescriptor serialDescriptor, Json json, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return getJsonNameIndexOrThrow(serialDescriptor, json, str, str2);
    }

    public static final int getJsonNameIndexSlowPath(SerialDescriptor serialDescriptor, Json json, String str) {
        Integer num = deserializationNamesMap(json, serialDescriptor).get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @NotNull
    public static final DescriptorSchemaCache.Key<String[]> getJsonSerializationNamesKey() {
        return JsonSerializationNamesKey;
    }

    public static final boolean ignoreUnknownKeys(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        if (json.configuration.ignoreUnknownKeys) {
            return true;
        }
        List<Annotation> annotations = serialDescriptor.getAnnotations();
        if (ComponentActivity$$ExternalSyntheticNonNull0.m(annotations) && annotations.isEmpty()) {
            return false;
        }
        Iterator<T> it = annotations.iterator();
        while (it.hasNext()) {
            if (((Annotation) it.next()) instanceof JsonIgnoreUnknownKeys) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static final JsonNamingStrategy namingStrategy(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        if (Intrinsics.areEqual(serialDescriptor.getKind(), StructureKind.CLASS.INSTANCE)) {
            return json.configuration.namingStrategy;
        }
        return null;
    }

    @NotNull
    public static final String[] serializationNamesIndices(@NotNull final SerialDescriptor serialDescriptor, @NotNull Json json, @NotNull final JsonNamingStrategy strategy) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(strategy, "strategy");
        return (String[]) json._schemaCache.getOrPut(serialDescriptor, JsonSerializationNamesKey, new Function0() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsonNamesMapKt.serializationNamesIndices$lambda$4(serialDescriptor, strategy);
            }
        });
    }

    public static final String[] serializationNamesIndices$lambda$4(SerialDescriptor serialDescriptor, JsonNamingStrategy jsonNamingStrategy) {
        int elementsCount = serialDescriptor.getElementsCount();
        String[] strArr = new String[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            strArr[i] = jsonNamingStrategy.serialNameForJson(serialDescriptor, i, serialDescriptor.getElementName(i));
        }
        return strArr;
    }

    public static final boolean tryCoerceValue(@NotNull Json json, @NotNull SerialDescriptor descriptor, int i, @NotNull Function1<? super Boolean, Boolean> peekNull, @NotNull Function0<String> peekString, @NotNull Function0<Unit> onEnumCoercing) {
        String strInvoke;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        boolean zIsElementOptional = descriptor.isElementOptional(i);
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(i);
        if (zIsElementOptional && !elementDescriptor.isNullable() && peekNull.invoke(Boolean.TRUE).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && peekNull.invoke(Boolean.FALSE).booleanValue()) || (strInvoke = peekString.invoke()) == null)) {
            return false;
        }
        int jsonNameIndex = getJsonNameIndex(elementDescriptor, json, strInvoke);
        boolean z = !json.configuration.explicitNulls && elementDescriptor.isNullable();
        if (jsonNameIndex == -3 && (zIsElementOptional || z)) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }

    public static boolean tryCoerceValue$default(Json json, SerialDescriptor descriptor, int i, Function1 peekNull, Function0 peekString, Function0 onEnumCoercing, int i2, Object obj) {
        String str;
        if ((i2 & 16) != 0) {
            onEnumCoercing = AnonymousClass1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        boolean zIsElementOptional = descriptor.isElementOptional(i);
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(i);
        if (zIsElementOptional && !elementDescriptor.isNullable() && ((Boolean) peekNull.invoke(Boolean.TRUE)).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && ((Boolean) peekNull.invoke(Boolean.FALSE)).booleanValue()) || (str = (String) peekString.invoke()) == null)) {
            return false;
        }
        int jsonNameIndex = getJsonNameIndex(elementDescriptor, json, str);
        boolean z = !json.configuration.explicitNulls && elementDescriptor.isNullable();
        if (jsonNameIndex == -3 && (zIsElementOptional || z)) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }
}
