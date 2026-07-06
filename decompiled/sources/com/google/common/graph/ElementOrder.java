package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.NaturalOrdering;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@Beta
public final class ElementOrder<T> {
    public final Comparator<T> comparator;
    public final Type type;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Type {
        UNORDERED,
        STABLE,
        INSERTION,
        SORTED
    }

    public ElementOrder(Type type, Comparator<T> comparator) {
        type.getClass();
        this.type = type;
        this.comparator = comparator;
        Preconditions.checkState((type == Type.SORTED) == (comparator != null));
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, NaturalOrdering.INSTANCE);
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator) {
        Type type = Type.SORTED;
        comparator.getClass();
        return new ElementOrder<>(type, comparator);
    }

    public static <S> ElementOrder<S> stable() {
        return new ElementOrder<>(Type.STABLE, null);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            return comparator;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public <K extends T, V> Map<K, V> createMap(int expectedSize) {
        int iOrdinal = this.type.ordinal();
        if (iOrdinal == 0) {
            return Maps.newHashMapWithExpectedSize(expectedSize);
        }
        if (iOrdinal == 1 || iOrdinal == 2) {
            return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
        }
        if (iOrdinal == 3) {
            return new TreeMap(comparator());
        }
        throw new AssertionError();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        return this.type == elementOrder.type && Objects.equal(this.comparator, elementOrder.comparator);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.type, this.comparator});
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.addHolder("type", this.type);
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            stringHelper.addHolder("comparator", comparator);
        }
        return stringHelper.toString();
    }

    public Type type() {
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }
}
