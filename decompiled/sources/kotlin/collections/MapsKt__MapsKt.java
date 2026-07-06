package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.builders.MapBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nMaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,816:1\n415#1:826\n426#1:831\n523#1,6:836\n548#1,6:842\n1#2:817\n1252#3,4:818\n1252#3,4:822\n1252#3,4:827\n1252#3,4:832\n*S KotlinDebug\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n465#1:826\n480#1:831\n538#1:836,6\n563#1:842,6\n415#1:818,4\n426#1:822,4\n465#1:827,4\n480#1:832,4\n*E\n"})
public class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final <K, V> Map<K, V> buildMap(@BuilderInference Function1<? super Map<K, V>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        MapBuilder mapBuilder = new MapBuilder();
        builderAction.invoke(mapBuilder);
        return mapBuilder.build();
    }

    @InlineOnly
    public static final <K, V> K component1(Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "<this>");
        return entry.getKey();
    }

    @InlineOnly
    public static final <K, V> V component2(Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "<this>");
        return entry.getValue();
    }

    @InlineOnly
    public static final <K, V> boolean contains(Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.containsKey(k);
    }

    @InlineOnly
    public static final <K> boolean containsKey(Map<? extends K, ?> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.containsKey(k);
    }

    @InlineOnly
    public static final <K, V> boolean containsValue(Map<K, ? extends V> map, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.containsValue(v);
    }

    @NotNull
    public static <K, V> Map<K, V> emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        Intrinsics.checkNotNull(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filter(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (predicate.invoke(entry).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super K, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (predicate.invoke(entry.getKey()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterNot(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (!predicate.invoke(entry).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (!predicate.invoke(entry).booleanValue()) {
                destination.put(entry.getKey(), entry.getValue());
            }
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (predicate.invoke(entry).booleanValue()) {
                destination.put(entry.getKey(), entry.getValue());
            }
        }
        return destination;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super V, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (predicate.invoke(entry.getValue()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @InlineOnly
    public static final <K, V> V get(Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.get(k);
    }

    @InlineOnly
    public static final <K, V> V getOrElse(Map<K, ? extends V> map, K k, Function0<? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        V v = map.get(k);
        return v == null ? defaultValue.invoke() : v;
    }

    public static final <K, V> V getOrElseNullable(@NotNull Map<K, ? extends V> map, K k, @NotNull Function0<? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        V v = map.get(k);
        return (v != null || map.containsKey(k)) ? v : defaultValue.invoke();
    }

    public static final <K, V> V getOrPut(@NotNull Map<K, V> map, K k, @NotNull Function0<? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        V v = map.get(k);
        if (v != null) {
            return v;
        }
        V vInvoke = defaultValue.invoke();
        map.put(k, vInvoke);
        return vInvoke;
    }

    @SinceKotlin(version = "1.1")
    public static <K, V> V getValue(@NotNull Map<K, ? extends V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return (V) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, k);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> HashMap<K, V> hashMapOf() {
        return new HashMap<>();
    }

    /* JADX WARN: Incorrect types in method signature: <M::Ljava/util/Map<**>;:TR;R:Ljava/lang/Object;>(TM;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final Object ifEmpty(Map map, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return map.isEmpty() ? defaultValue.invoke() : map;
    }

    @InlineOnly
    public static final <K, V> boolean isNotEmpty(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return !map.isEmpty();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <K, V> boolean isNullOrEmpty(Map<? extends K, ? extends V> map) {
        return map == null || map.isEmpty();
    }

    @InlineOnly
    public static final <K, V> Iterator<Map.Entry<K, V>> iterator(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.entrySet().iterator();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> LinkedHashMap<K, V> linkedMapOf() {
        return new LinkedHashMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, R> Map<R, V> mapKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Object) it.next();
            linkedHashMap.put(transform.invoke(entry), entry.getValue());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Object) it.next();
            destination.put(transform.invoke(entry), entry.getValue());
        }
        return destination;
    }

    @NotNull
    public static <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        if (pairs.length <= 0) {
            return emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(pairs.length));
        toMap(pairs, linkedHashMap);
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, R> Map<K, R> mapValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Object) it.next();
            linkedHashMap.put(entry.getKey(), transform.invoke(entry));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M destination, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Object) it.next();
            destination.put(entry.getKey(), transform.invoke(entry));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Map mutableMap = toMutableMap(map);
        mutableMap.remove(k);
        return optimizeReadOnlyMap(mutableMap);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> void minusAssign(Map<K, V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        map.remove(k);
    }

    @InlineOnly
    @JvmName(name = "mutableIterator")
    public static final <K, V> Iterator<Map.Entry<K, V>> mutableIterator(Map<K, V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.entrySet().iterator();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> Map<K, V> mutableMapOf() {
        return new LinkedHashMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V> Map<K, V> optimizeReadOnlyMap(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? map : MapsKt__MapsJVMKt.toSingletonMap(map) : emptyMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <K, V> Map<K, V> orEmpty(Map<K, ? extends V> map) {
        return map == 0 ? emptyMap() : map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if (map.isEmpty()) {
            return MapsKt__MapsJVMKt.mapOf(pair);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(pair.first, pair.second);
        return linkedHashMap;
    }

    @InlineOnly
    public static final <K, V> void plusAssign(Map<? super K, ? super V> map, Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        map.put((Object) pair.first, (Object) pair.second);
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairs) {
            map.put((Object) pair.first, (Object) pair.second);
        }
    }

    @InlineOnly
    public static final <K, V> V remove(Map<? extends K, V> map, K k) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return (V) TypeIntrinsics.asMutableMap(map).remove(k);
    }

    @InlineOnly
    public static final <K, V> void set(Map<K, V> map, K k, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        map.put(k, v);
    }

    @NotNull
    public static <K, V> Map<K, V> toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            toMap(iterable, linkedHashMap);
            return optimizeReadOnlyMap(linkedHashMap);
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return emptyMap();
        }
        if (size == 1) {
            return MapsKt__MapsJVMKt.mapOf((Pair) (iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next()));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(collection.size()));
        toMap(iterable, linkedHashMap2);
        return linkedHashMap2;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new LinkedHashMap(map);
    }

    @InlineOnly
    public static final <K, V> Pair<K, V> toPair(Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "<this>");
        return new Pair<>(entry.getKey(), entry.getValue());
    }

    @NotNull
    public static final <K, V> HashMap<K, V> hashMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        HashMap<K, V> map = new HashMap<>(MapsKt__MapsJVMKt.mapCapacity(pairs.length));
        putAll(map, pairs);
        return map;
    }

    @NotNull
    public static final <K, V> LinkedHashMap<K, V> linkedMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>(MapsKt__MapsJVMKt.mapCapacity(pairs.length));
        toMap(pairs, linkedHashMap);
        return linkedHashMap;
    }

    @InlineOnly
    public static final <K, V> Map<K, V> mapOf() {
        return emptyMap();
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends K> keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        Map mutableMap = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll(((LinkedHashMap) mutableMap).keySet(), keys);
        return optimizeReadOnlyMap(mutableMap);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> void minusAssign(Map<K, V> map, Iterable<? extends K> keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        CollectionsKt__MutableCollectionsKt.removeAll(map.keySet(), keys);
    }

    @NotNull
    public static final <K, V> Map<K, V> mutableMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(pairs.length));
        putAll(linkedHashMap, pairs);
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull K[] keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        Map mutableMap = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll(((LinkedHashMap) mutableMap).keySet(), keys);
        return optimizeReadOnlyMap(mutableMap);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> void minusAssign(Map<K, V> map, K[] keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        CollectionsKt__MutableCollectionsKt.removeAll(map.keySet(), keys);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final <K, V> Map<K, V> buildMap(int i, @BuilderInference Function1<? super Map<K, V>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        MapBuilder mapBuilder = new MapBuilder(i);
        builderAction.invoke(mapBuilder);
        return mapBuilder.build();
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends K> keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        Map mutableMap = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll(((LinkedHashMap) mutableMap).keySet(), keys);
        return optimizeReadOnlyMap(mutableMap);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <K, V> void minusAssign(Map<K, V> map, Sequence<? extends K> keys) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        CollectionsKt__MutableCollectionsKt.removeAll(map.keySet(), keys);
    }

    @InlineOnly
    public static final <K, V> void plusAssign(Map<? super K, ? super V> map, Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        putAll(map, pairs);
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        if (map.isEmpty()) {
            return toMap(pairs);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        putAll(linkedHashMap, pairs);
        return linkedHashMap;
    }

    @InlineOnly
    public static final <K, V> void plusAssign(Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        putAll(map, pairs);
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairs) {
            map.put((Object) pair.first, (Object) pair.second);
        }
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        if (map.isEmpty()) {
            return toMap(pairs);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        putAll(linkedHashMap, pairs);
        return linkedHashMap;
    }

    @InlineOnly
    public static final <K, V> void plusAssign(Map<? super K, ? super V> map, Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        putAll(map, pairs);
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        putAll(linkedHashMap, pairs);
        return optimizeReadOnlyMap(linkedHashMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <K, V> void plusAssign(Map<? super K, ? super V> map, Map<K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(map2, "map");
        map.putAll(map2);
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable, @NotNull M destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, iterable);
        return destination;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Map<? extends K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Pair<? extends K, ? extends V>[] pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "<this>");
        int length = pairArr.length;
        if (length == 0) {
            return emptyMap();
        }
        if (length != 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(pairArr.length));
            toMap(pairArr, linkedHashMap);
            return linkedHashMap;
        }
        return MapsKt__MapsJVMKt.mapOf(pairArr[0]);
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairs) {
            map.put((Object) pair.first, (Object) pair.second);
        }
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Pair<? extends K, ? extends V>[] pairArr, @NotNull M destination) {
        Intrinsics.checkNotNullParameter(pairArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, pairArr);
        return destination;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        toMap(sequence, linkedHashMap);
        return optimizeReadOnlyMap(linkedHashMap);
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence, @NotNull M destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, sequence);
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static <K, V> Map<K, V> toMap(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return emptyMap();
        }
        if (size != 1) {
            return toMutableMap(map);
        }
        return MapsKt__MapsJVMKt.toSingletonMap(map);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Map<? extends K, ? extends V> map, @NotNull M destination) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        destination.putAll(map);
        return destination;
    }
}
