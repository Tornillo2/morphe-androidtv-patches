package androidx.collection.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LruHashMap<K, V> {

    @NotNull
    public final LinkedHashMap<K, V> map;

    public LruHashMap() {
        this(0, 0.0f, 3, null);
    }

    @Nullable
    public final V get(@NotNull K key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.map.get(key);
    }

    @NotNull
    public final Set<Map.Entry<K, V>> getEntries() {
        Set<Map.Entry<K, V>> setEntrySet = this.map.entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "map.entries");
        return setEntrySet;
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Nullable
    public final V put(@NotNull K key, @NotNull V value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return this.map.put(key, value);
    }

    @Nullable
    public final V remove(@NotNull K key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.map.remove(key);
    }

    public LruHashMap(int i, float f) {
        this.map = new LinkedHashMap<>(i, f, true);
    }

    public /* synthetic */ LruHashMap(int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i, (i2 & 2) != 0 ? 0.75f : f);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LruHashMap(@NotNull LruHashMap<? extends K, V> original) {
        this(0, 0.0f, 3, null);
        Intrinsics.checkNotNullParameter(original, "original");
        for (Map.Entry<? extends K, V> entry : original.getEntries()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}
