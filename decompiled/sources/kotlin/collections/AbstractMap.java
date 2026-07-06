package kotlin.collections;

import androidx.activity.ComponentActivity$$ExternalSyntheticNonNull0;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
@SourceDebugExtension({"SMAP\nAbstractMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,153:1\n1761#2,3:154\n1740#2,3:157\n295#2,2:160\n*S KotlinDebug\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap\n*L\n28#1:154,3\n60#1:157,3\n141#1:160,2\n*E\n"})
public abstract class AbstractMap<K, V> implements Map<K, V>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public volatile Set<? extends K> _keys;

    @Nullable
    public volatile Collection<? extends V> _values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nAbstractMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,153:1\n1#2:154\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        public final boolean entryEquals$kotlin_stdlib(@NotNull Map.Entry<?, ?> e, @Nullable Object obj) {
            Intrinsics.checkNotNullParameter(e, "e");
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return Intrinsics.areEqual(e.getKey(), entry.getKey()) && Intrinsics.areEqual(e.getValue(), entry.getValue());
        }

        public final int entryHashCode$kotlin_stdlib(@NotNull Map.Entry<?, ?> e) {
            Intrinsics.checkNotNullParameter(e, "e");
            Object key = e.getKey();
            int iHashCode = key != null ? key.hashCode() : 0;
            Object value = e.getValue();
            return iHashCode ^ (value != null ? value.hashCode() : 0);
        }

        @NotNull
        public final String entryToString$kotlin_stdlib(@NotNull Map.Entry<?, ?> e) {
            Intrinsics.checkNotNullParameter(e, "e");
            StringBuilder sb = new StringBuilder();
            sb.append(e.getKey());
            sb.append('=');
            sb.append(e.getValue());
            return sb.toString();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final CharSequence toString$lambda$2(AbstractMap abstractMap, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return abstractMap.toString(it);
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean containsEntry$kotlin_stdlib(@Nullable Map.Entry<?, ?> entry) {
        if (entry == null) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        V v = get(key);
        if (Intrinsics.areEqual(value, v)) {
            return v != null || containsKey(key);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return implFindEntry(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        Set<Map.Entry<K, V>> entries = getEntries();
        if (ComponentActivity$$ExternalSyntheticNonNull0.m(entries) && entries.isEmpty()) {
            return false;
        }
        Iterator<T> it = entries.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((Map.Entry) it.next()).getValue(), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (getSize() != map.size()) {
            return false;
        }
        Set<Map.Entry<K, V>> setEntrySet = map.entrySet();
        if (ComponentActivity$$ExternalSyntheticNonNull0.m(setEntrySet) && setEntrySet.isEmpty()) {
            return true;
        }
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            if (!containsEntry$kotlin_stdlib((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Nullable
    public V get(Object obj) {
        Map.Entry<K, V> entryImplFindEntry = implFindEntry(obj);
        if (entryImplFindEntry != null) {
            return entryImplFindEntry.getValue();
        }
        return null;
    }

    public abstract Set<Map.Entry<K, V>> getEntries();

    @NotNull
    public Set<K> getKeys() {
        if (this._keys == null) {
            this._keys = new AbstractSet<K>(this) { // from class: kotlin.collections.AbstractMap$keys$1
                public final /* synthetic */ AbstractMap<K, V> this$0;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.this$0 = this;
                }

                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public boolean contains(Object obj) {
                    return this.this$0.containsKey(obj);
                }

                @Override // kotlin.collections.AbstractCollection
                public int getSize() {
                    return this.this$0.getSize();
                }

                @Override // kotlin.collections.AbstractSet, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
                public Iterator<K> iterator() {
                    return new AbstractMap$keys$1$iterator$1(this.this$0.getEntries().iterator());
                }
            };
        }
        Set<? extends K> set = this._keys;
        Intrinsics.checkNotNull(set);
        return set;
    }

    public int getSize() {
        return getEntries().size();
    }

    @NotNull
    public Collection<V> getValues() {
        if (this._values == null) {
            this._values = new AbstractCollection<V>(this) { // from class: kotlin.collections.AbstractMap.values.1
                public final /* synthetic */ AbstractMap<K, V> this$0;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.this$0 = this;
                }

                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public boolean contains(Object obj) {
                    return this.this$0.containsValue(obj);
                }

                @Override // kotlin.collections.AbstractCollection
                public int getSize() {
                    return this.this$0.getSize();
                }

                @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
                public Iterator<V> iterator() {
                    return new AbstractMap$values$1$iterator$1(this.this$0.getEntries().iterator());
                }
            };
        }
        Collection<? extends V> collection = this._values;
        Intrinsics.checkNotNull(collection);
        return collection;
    }

    @Override // java.util.Map
    public int hashCode() {
        return getEntries().hashCode();
    }

    public final Map.Entry<K, V> implFindEntry(K k) {
        Object next;
        Iterator<T> it = getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Map.Entry) next).getKey(), k)) {
                break;
            }
        }
        return (Map.Entry) next;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(getEntries(), ", ", "{", "}", 0, null, new Function1() { // from class: kotlin.collections.AbstractMap$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractMap.toString$lambda$2(this.f$0, (Map.Entry) obj);
            }
        }, 24, null);
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public final String toString(Map.Entry<? extends K, ? extends V> entry) {
        return toString(entry.getKey()) + '=' + toString(entry.getValue());
    }

    public final String toString(Object obj) {
        return obj == this ? "(this Map)" : String.valueOf(obj);
    }
}
