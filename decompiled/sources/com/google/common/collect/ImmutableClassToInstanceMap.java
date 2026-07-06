package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"B"})
@GwtIncompatible
public final class ImmutableClassToInstanceMap<B> extends ForwardingMap<Class<? extends B>, B> implements ClassToInstanceMap<B>, Serializable {
    public static final ImmutableClassToInstanceMap<Object> EMPTY = new ImmutableClassToInstanceMap<>(RegularImmutableMap.EMPTY);
    public final ImmutableMap<Class<? extends B>, B> delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<B> {
        public final ImmutableMap.Builder<Class<? extends B>, B> mapBuilder = ImmutableMap.builder();

        public static <T> T cast(Class<T> cls, Object obj) {
            return (T) Primitives.wrap(cls).cast(obj);
        }

        public ImmutableClassToInstanceMap<B> build() {
            ImmutableMap<Class<? extends B>, B> immutableMapBuildOrThrow = this.mapBuilder.buildOrThrow();
            return immutableMapBuildOrThrow.isEmpty() ? ImmutableClassToInstanceMap.of() : new ImmutableClassToInstanceMap<>(immutableMapBuildOrThrow);
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(Class<T> key, T value) {
            this.mapBuilder.put(key, value);
            return this;
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> putAll(Map<? extends Class<? extends T>, ? extends T> map) {
            for (Map.Entry<? extends Class<? extends T>, ? extends T> entry : map.entrySet()) {
                Class key = entry.getKey();
                T value = entry.getValue();
                this.mapBuilder.put((Class<? extends B>) key, (B) cast(key, value));
            }
            return this;
        }
    }

    public static <B> Builder<B> builder() {
        return new Builder<>();
    }

    public static <B, S extends B> ImmutableClassToInstanceMap<B> copyOf(Map<? extends Class<? extends S>, ? extends S> map) {
        if (map instanceof ImmutableClassToInstanceMap) {
            return (ImmutableClassToInstanceMap) map;
        }
        Builder builder = new Builder();
        builder.putAll(map);
        return builder.build();
    }

    public static <B> ImmutableClassToInstanceMap<B> of() {
        return (ImmutableClassToInstanceMap<B>) EMPTY;
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Object delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ClassToInstanceMap
    public <T extends B> T getInstance(Class<T> cls) {
        ImmutableMap<Class<? extends B>, B> immutableMap = this.delegate;
        cls.getClass();
        return immutableMap.get(cls);
    }

    @Override // com.google.common.collect.ClassToInstanceMap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> type, T value) {
        throw new UnsupportedOperationException();
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableClassToInstanceMap(ImmutableMap<Class<? extends B>, B> delegate) {
        this.delegate = delegate;
    }

    public static <B, T extends B> ImmutableClassToInstanceMap<B> of(Class<T> type, T value) {
        return new ImmutableClassToInstanceMap<>(ImmutableMap.of(type, value));
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }
}
