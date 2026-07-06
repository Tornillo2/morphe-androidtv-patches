package kotlinx.serialization.json.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSchemaCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,53:1\n381#2,7:54\n1#3:61\n*S KotlinDebug\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n*L\n25#1:54,7\n*E\n"})
public final class DescriptorSchemaCache {

    @NotNull
    public final Map<SerialDescriptor, Map<Key<Object>, Object>> map = new ConcurrentHashMap(16);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Key<T> {
    }

    @Nullable
    public final <T> T get(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Map<Key<Object>, Object> map = this.map.get(descriptor);
        T t = map != null ? (T) map.get(key) : null;
        if (t == null) {
            return null;
        }
        return t;
    }

    @NotNull
    public final <T> T getOrPut(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key, @NotNull Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        T t = (T) get(descriptor, key);
        if (t != null) {
            return t;
        }
        T tInvoke = defaultValue.invoke();
        set(descriptor, key, tInvoke);
        return tInvoke;
    }

    public final <T> void set(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key, @NotNull T value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Map<SerialDescriptor, Map<Key<Object>, Object>> map = this.map;
        Map<Key<Object>, Object> concurrentHashMap = map.get(descriptor);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>(2);
            map.put(descriptor, concurrentHashMap);
        }
        concurrentHashMap.put(key, value);
    }
}
