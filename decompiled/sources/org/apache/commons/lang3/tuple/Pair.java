package org.apache.commons.lang3.tuple;

import j$.util.Objects;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import org.apache.commons.lang3.builder.CompareToBuilder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {
    public static final long serialVersionUID = 4954918890077093841L;

    public static <L, R> Pair<L, R> of(L l, R r) {
        return new ImmutablePair(l, r);
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return Objects.equals(getLeft(), entry.getKey()) && Objects.equals(getValue(), entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final L getKey() {
        return getLeft();
    }

    public abstract L getLeft();

    public abstract R getRight();

    @Override // java.util.Map.Entry
    public R getValue() {
        return getRight();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return (getLeft() == null ? 0 : getLeft().hashCode()) ^ (getValue() != null ? getValue().hashCode() : 0);
    }

    public String toString() {
        return "(" + getLeft() + ',' + getRight() + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(Pair<L, R> pair) {
        CompareToBuilder compareToBuilder = new CompareToBuilder();
        compareToBuilder.append(getLeft(), pair.getLeft(), (Comparator<?>) null);
        compareToBuilder.append(getRight(), pair.getRight(), (Comparator<?>) null);
        return compareToBuilder.toComparison();
    }

    public String toString(String str) {
        return String.format(str, getLeft(), getRight());
    }
}
