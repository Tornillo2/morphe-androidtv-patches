package kotlinx.serialization.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class HashMapSerializer<K, V> extends MapLikeSerializer<K, V, Map<K, ? extends V>, HashMap<K, V>> {

    @NotNull
    public final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashMapSerializer(@NotNull KSerializer<K> kSerializer, @NotNull KSerializer<V> vSerializer) {
        super(kSerializer, vSerializer);
        Intrinsics.checkNotNullParameter(kSerializer, "kSerializer");
        Intrinsics.checkNotNullParameter(vSerializer, "vSerializer");
        this.descriptor = new HashMapClassDesc(kSerializer.getDescriptor(), vSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new HashMap();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(@NotNull HashMap<K, V> map, int i) {
        Intrinsics.checkNotNullParameter(map, "<this>");
    }

    @Override // kotlinx.serialization.internal.MapLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public Map<K, V> toResult(@NotNull HashMap<K, V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public HashMap<K, V> builder() {
        return new HashMap<>();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(@NotNull HashMap<K, V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.size() * 2;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public Iterator<Map.Entry<K, V>> collectionIterator(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.entrySet().iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map.size();
    }

    @Override // kotlinx.serialization.internal.MapLikeSerializer
    public void insertKeyValuePair(@NotNull HashMap<K, V> map, int i, K k, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        map.put(k, v);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public HashMap<K, V> toBuilder(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        HashMap<K, V> map2 = map instanceof HashMap ? (HashMap) map : null;
        return map2 == null ? new HashMap<>(map) : map2;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        HashMap map = (HashMap) obj;
        Intrinsics.checkNotNullParameter(map, "<this>");
        return map;
    }
}
