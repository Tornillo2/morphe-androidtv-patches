package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    public final HashMap<K, SafeIterableMap.Entry<K, V>> mHashMap = new HashMap<>();

    @Nullable
    public Map.Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return this.mHashMap.get(k).mPrevious;
        }
        return null;
    }

    public boolean contains(K k) {
        return this.mHashMap.containsKey(k);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    @Nullable
    public SafeIterableMap.Entry<K, V> get(K k) {
        return this.mHashMap.get(k);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        SafeIterableMap.Entry<K, V> entry = get(k);
        if (entry != null) {
            return entry.mValue;
        }
        this.mHashMap.put(k, put(k, v));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V remove(@NonNull K k) {
        V v = (V) super.remove(k);
        this.mHashMap.remove(k);
        return v;
    }
}
