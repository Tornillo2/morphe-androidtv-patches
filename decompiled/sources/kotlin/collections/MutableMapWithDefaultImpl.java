package kotlin.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nMapWithDefault.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MutableMapWithDefaultImpl\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,111:1\n350#2,6:112\n*S KotlinDebug\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MutableMapWithDefaultImpl\n*L\n108#1:112,6\n*E\n"})
public final class MutableMapWithDefaultImpl<K, V> implements MutableMapWithDefault<K, V> {

    /* JADX INFO: renamed from: default, reason: not valid java name */
    @NotNull
    public final Function1<K, V> f3default;

    @NotNull
    public final Map<K, V> map;

    /* JADX WARN: Multi-variable type inference failed */
    public MutableMapWithDefaultImpl(@NotNull Map<K, V> map, @NotNull Function1<? super K, ? extends V> function1) {
        Intrinsics.checkNotNullParameter(map, "map");
        Intrinsics.checkNotNullParameter(function1, "default");
        this.map = map;
        this.f3default = function1;
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    @Nullable
    public V get(Object obj) {
        return this.map.get(obj);
    }

    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return this.map.entrySet();
    }

    @NotNull
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    @Override // kotlin.collections.MutableMapWithDefault, kotlin.collections.MapWithDefault
    @NotNull
    public Map<K, V> getMap() {
        return this.map;
    }

    @Override // kotlin.collections.MapWithDefault
    public V getOrImplicitDefault(K k) {
        Map<K, V> map = this.map;
        V v = map.get(k);
        return (v != null || map.containsKey(k)) ? v : this.f3default.invoke(k);
    }

    public int getSize() {
        return this.map.size();
    }

    @NotNull
    public Collection<V> getValues() {
        return this.map.values();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    @Nullable
    public V put(K k, V v) {
        return this.map.put(k, v);
    }

    @Override // java.util.Map
    public void putAll(@NotNull Map<? extends K, ? extends V> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        this.map.putAll(from);
    }

    @Override // java.util.Map
    @Nullable
    public V remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public final int size() {
        return this.map.size();
    }

    @NotNull
    public String toString() {
        return this.map.toString();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return this.map.values();
    }
}
