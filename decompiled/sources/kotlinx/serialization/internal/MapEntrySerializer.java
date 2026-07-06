package kotlinx.serialization.internal;

import java.util.Map;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class MapEntrySerializer<K, V> extends KeyValueSerializer<K, V, Map.Entry<? extends K, ? extends V>> {

    @NotNull
    public final SerialDescriptor descriptor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MapEntry<K, V> implements Map.Entry<K, V>, KMappedMarker {
        public final K key;
        public final V value;

        public MapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public static MapEntry copy$default(MapEntry mapEntry, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = mapEntry.key;
            }
            if ((i & 2) != 0) {
                obj2 = mapEntry.value;
            }
            mapEntry.getClass();
            return new MapEntry(obj, obj2);
        }

        public final K component1() {
            return this.key;
        }

        public final V component2() {
            return this.value;
        }

        @NotNull
        public final MapEntry<K, V> copy(K k, V v) {
            return new MapEntry<>(k, v);
        }

        @Override // java.util.Map.Entry
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MapEntry)) {
                return false;
            }
            MapEntry mapEntry = (MapEntry) obj;
            return Intrinsics.areEqual(this.key, mapEntry.key) && Intrinsics.areEqual(this.value, mapEntry.value);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int iHashCode = (k == null ? 0 : k.hashCode()) * 31;
            V v = this.value;
            return iHashCode + (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @NotNull
        public String toString() {
            return "MapEntry(key=" + this.key + ", value=" + this.value + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapEntrySerializer(@NotNull final KSerializer<K> keySerializer, @NotNull final KSerializer<V> valueSerializer) {
        super(keySerializer, valueSerializer);
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        this.descriptor = SerialDescriptorsKt.buildSerialDescriptor("kotlin.collections.Map.Entry", StructureKind.MAP.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.internal.MapEntrySerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MapEntrySerializer.descriptor$lambda$0(keySerializer, valueSerializer, (ClassSerialDescriptorBuilder) obj);
            }
        });
    }

    public static final Unit descriptor$lambda$0(KSerializer kSerializer, KSerializer kSerializer2, ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "key", kSerializer.getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "value", kSerializer2.getDescriptor(), null, false, 12, null);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object toResult(Object obj, Object obj2) {
        return new MapEntry(obj, obj2);
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public K getKey(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "<this>");
        return entry.getKey();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public V getValue(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "<this>");
        return entry.getValue();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    @NotNull
    public Map.Entry<K, V> toResult(K k, V v) {
        return new MapEntry(k, v);
    }
}
