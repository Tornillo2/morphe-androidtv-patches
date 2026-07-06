package androidx.collection;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MutableMapEntry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
    public final int index;

    @NotNull
    public final Object[] keys;

    @NotNull
    public final Object[] values;

    public MutableMapEntry(@NotNull Object[] keys, @NotNull Object[] values, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(values, "values");
        this.keys = keys;
        this.values = values;
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return (K) this.keys[this.index];
    }

    @NotNull
    public final Object[] getKeys() {
        return this.keys;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return (V) this.values[this.index];
    }

    @NotNull
    public final Object[] getValues() {
        return this.values;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        Object[] objArr = this.values;
        int i = this.index;
        V v2 = (V) objArr[i];
        objArr[i] = v;
        return v2;
    }

    public static /* synthetic */ void getKey$annotations() {
    }

    public static /* synthetic */ void getValue$annotations() {
    }
}
