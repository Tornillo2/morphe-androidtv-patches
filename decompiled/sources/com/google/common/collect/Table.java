package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ImmutableTable, HashBasedTable, or another implementation")
@GwtCompatible
public interface Table<R, C, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Cell<R, C, V> {
        boolean equals(Object obj);

        @ParametricNullness
        C getColumnKey();

        @ParametricNullness
        R getRowKey();

        @ParametricNullness
        V getValue();

        int hashCode();
    }

    Set<Cell<R, C, V>> cellSet();

    void clear();

    Map<R, V> column(@ParametricNullness C columnKey);

    Set<C> columnKeySet();

    Map<C, Map<R, V>> columnMap();

    boolean contains(@CompatibleWith("R") Object rowKey, @CompatibleWith("C") Object columnKey);

    boolean containsColumn(@CompatibleWith("C") Object columnKey);

    boolean containsRow(@CompatibleWith("R") Object rowKey);

    boolean containsValue(@CompatibleWith(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) Object value);

    boolean equals(Object obj);

    V get(@CompatibleWith("R") Object rowKey, @CompatibleWith("C") Object columnKey);

    int hashCode();

    boolean isEmpty();

    @CanIgnoreReturnValue
    V put(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value);

    void putAll(Table<? extends R, ? extends C, ? extends V> table);

    @CanIgnoreReturnValue
    V remove(@CompatibleWith("R") Object rowKey, @CompatibleWith("C") Object columnKey);

    Map<C, V> row(@ParametricNullness R rowKey);

    Set<R> rowKeySet();

    Map<R, Map<C, V>> rowMap();

    int size();

    Collection<V> values();
}
