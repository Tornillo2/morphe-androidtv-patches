package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EmptyMap implements Map, Serializable, KMappedMarker {

    @NotNull
    public static final EmptyMap INSTANCE = new EmptyMap();
    public static final long serialVersionUID = 8246714829545688274L;

    private final Object readResolve() {
        return INSTANCE;
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public boolean containsKey(@Nullable Object obj) {
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public final Set<Map.Entry> entrySet() {
        return EmptySet.INSTANCE;
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Map) && ((Map) obj).isEmpty();
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object get(Object obj) {
        return null;
    }

    @NotNull
    public Set<Map.Entry> getEntries() {
        return EmptySet.INSTANCE;
    }

    @NotNull
    public Set<Object> getKeys() {
        return EmptySet.INSTANCE;
    }

    public int getSize() {
        return 0;
    }

    @NotNull
    public Collection getValues() {
        return EmptyList.INSTANCE;
    }

    @Override // java.util.Map
    public int hashCode() {
        return 0;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return true;
    }

    @Override // java.util.Map
    public final Set<Object> keySet() {
        return EmptySet.INSTANCE;
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public Void remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return 0;
    }

    @NotNull
    public String toString() {
        return "{}";
    }

    @Override // java.util.Map
    public final Collection values() {
        return EmptyList.INSTANCE;
    }

    public boolean containsValue(@NotNull Void value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return false;
    }

    @Override // java.util.Map
    @Nullable
    public Void get(@Nullable Object obj) {
        return null;
    }

    public Void put(Object obj, Void r2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object remove(Object obj) {
        remove(obj);
        throw null;
    }
}
