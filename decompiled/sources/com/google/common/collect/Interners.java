package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Interners {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InternerBuilder {
        public final MapMaker mapMaker;
        public boolean strong;

        public <E> Interner<E> build() {
            if (!this.strong) {
                this.mapMaker.weakKeys();
            }
            return new InternerImpl(this.mapMaker);
        }

        @CanIgnoreReturnValue
        public InternerBuilder concurrencyLevel(int concurrencyLevel) {
            this.mapMaker.concurrencyLevel(concurrencyLevel);
            return this;
        }

        @CanIgnoreReturnValue
        public InternerBuilder strong() {
            this.strong = true;
            return this;
        }

        @CanIgnoreReturnValue
        @GwtIncompatible("java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.strong = false;
            return this;
        }

        public InternerBuilder() {
            this.mapMaker = new MapMaker();
            this.strong = true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InternerFunction<E> implements Function<E, E> {
        public final Interner<E> interner;

        public InternerFunction(Interner<E> interner) {
            this.interner = interner;
        }

        @Override // com.google.common.base.Function
        public E apply(E input) {
            return this.interner.intern(input);
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object other) {
            if (other instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) other).interner);
            }
            return false;
        }

        public int hashCode() {
            return this.interner.hashCode();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class InternerImpl<E> implements Interner<E> {

        @VisibleForTesting
        public final MapMakerInternalMap<E, MapMaker.Dummy, ?, ?> map;

        @Override // com.google.common.collect.Interner
        public E intern(E e) {
            E e2;
            do {
                MapMakerInternalMap.InternalEntry entry = this.map.getEntry(e);
                if (entry != null && (e2 = (E) entry.getKey()) != null) {
                    return e2;
                }
            } while (this.map.putIfAbsent(e, MapMaker.Dummy.VALUE) != null);
            return e;
        }

        public InternerImpl(MapMaker mapMaker) {
            mapMaker.keyEquivalence(Equivalence.Equals.INSTANCE);
            this.map = MapMakerInternalMap.createWithDummyValues(mapMaker);
        }
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        interner.getClass();
        return new InternerFunction(interner);
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        InternerBuilder internerBuilder = new InternerBuilder();
        internerBuilder.strong = true;
        return internerBuilder.build();
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        InternerBuilder internerBuilder = new InternerBuilder();
        internerBuilder.strong = false;
        return internerBuilder.build();
    }
}
