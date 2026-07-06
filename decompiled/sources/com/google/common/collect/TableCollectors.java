package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.common.collect.TableCollectors;
import com.google.common.collect.Tables;
import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.stream.Collector;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
@IgnoreJRERequirement
public final class TableCollectors {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ImmutableTableCollectorState<R, C, V> {
        public final List<MutableCell<R, C, V>> insertionOrder;
        public final Table<R, C, MutableCell<R, C, V>> table;

        public ImmutableTableCollectorState() {
            this.insertionOrder = new ArrayList();
            this.table = HashBasedTable.create();
        }

        public ImmutableTableCollectorState<R, C, V> combine(ImmutableTableCollectorState<R, C, V> other, BinaryOperator<V> merger) {
            for (MutableCell<R, C, V> mutableCell : other.insertionOrder) {
                put(mutableCell.row, mutableCell.column, mutableCell.value, merger);
            }
            return this;
        }

        public void put(R row, C column, V value, BinaryOperator<V> merger) {
            MutableCell<R, C, V> mutableCell = this.table.get(row, column);
            if (mutableCell != null) {
                mutableCell.merge(value, merger);
                return;
            }
            MutableCell<R, C, V> mutableCell2 = new MutableCell<>(row, column, value);
            this.insertionOrder.add(mutableCell2);
            this.table.put(row, column, mutableCell2);
        }

        public ImmutableTable<R, C, V> toTable() {
            return ImmutableTable.copyOf(this.insertionOrder);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class MutableCell<R, C, V> extends Tables.AbstractCell<R, C, V> {
        public final C column;
        public final R row;
        public V value;

        public MutableCell(R row, C column, V value) {
            Preconditions.checkNotNull(row, "row");
            this.row = row;
            Preconditions.checkNotNull(column, "column");
            this.column = column;
            Preconditions.checkNotNull(value, "value");
            this.value = value;
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.column;
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.row;
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return this.value;
        }

        public void merge(V v, BinaryOperator<V> binaryOperator) {
            Preconditions.checkNotNull(v, "value");
            V v2 = (V) binaryOperator.apply(this.value, v);
            Preconditions.checkNotNull(v2, "mergeFunction.apply");
            this.value = v2;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$3-3A1XfZzco682IseNG_qNCs0GM, reason: not valid java name */
    public static /* synthetic */ Object m531$r8$lambda$33A1XfZzco682IseNG_qNCs0GM(Object obj, Object obj2) {
        throw new IllegalStateException("Conflicting values " + obj + " and " + obj2);
    }

    /* JADX INFO: renamed from: $r8$lambda$T8fDK34-rAHKumRsqXl1_gT1epo, reason: not valid java name */
    public static /* synthetic */ ImmutableTableCollectorState m532$r8$lambda$T8fDK34rAHKumRsqXl1_gT1epo(BinaryOperator binaryOperator, ImmutableTableCollectorState immutableTableCollectorState, ImmutableTableCollectorState immutableTableCollectorState2) {
        immutableTableCollectorState.combine(immutableTableCollectorState2, binaryOperator);
        return immutableTableCollectorState;
    }

    public static /* synthetic */ ImmutableTableCollectorState $r8$lambda$Z9vH2iHKBklpK5u3iDKRf_pjDWE() {
        return new ImmutableTableCollectorState();
    }

    public static /* synthetic */ Table $r8$lambda$butTipWhgtbxfBh3mwO9KSqqMF0(BinaryOperator binaryOperator, Table table, Table table2) {
        for (Table.Cell cell : table2.cellSet()) {
            mergeTables(table, cell.getRowKey(), cell.getColumnKey(), cell.getValue(), binaryOperator);
        }
        return table;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <R, C, V> void mergeTables(Table<R, C, V> table, @ParametricNullness R row, @ParametricNullness C column, V value, BinaryOperator<V> mergeFunction) {
        value.getClass();
        V v = table.get(row, column);
        if (v == null) {
            table.put(row, column, value);
            return;
        }
        Object objApply = mergeFunction.apply(v, value);
        if (objApply == null) {
            table.remove(row, column);
        } else {
            table.put(row, column, objApply);
        }
    }

    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(final Function<? super T, ? extends R> rowFunction, final Function<? super T, ? extends C> columnFunction, final Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(rowFunction, "rowFunction");
        Preconditions.checkNotNull(columnFunction, "columnFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        return Collector.CC.of(new TableCollectors$$ExternalSyntheticLambda7(), new BiConsumer() { // from class: com.google.common.collect.TableCollectors$$ExternalSyntheticLambda8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableTable.Builder) obj).put(rowFunction.apply(obj2), columnFunction.apply(obj2), valueFunction.apply(obj2));
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new TableCollectors$$ExternalSyntheticLambda9(), new TableCollectors$$ExternalSyntheticLambda10(), new Collector.Characteristics[0]);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> rowFunction, Function<? super T, ? extends C> columnFunction, Function<? super T, ? extends V> valueFunction, Supplier<I> tableSupplier) {
        return toTable(rowFunction, columnFunction, valueFunction, new TableCollectors$$ExternalSyntheticLambda0(), tableSupplier);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(final Function<? super T, ? extends R> rowFunction, final Function<? super T, ? extends C> columnFunction, final Function<? super T, ? extends V> valueFunction, final BinaryOperator<V> mergeFunction, Supplier<I> tableSupplier) {
        rowFunction.getClass();
        columnFunction.getClass();
        valueFunction.getClass();
        mergeFunction.getClass();
        tableSupplier.getClass();
        return Collector.CC.of(tableSupplier, new BiConsumer() { // from class: com.google.common.collect.TableCollectors$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Table table = (Table) obj;
                TableCollectors.mergeTables(table, rowFunction.apply(obj2), columnFunction.apply(obj2), valueFunction.apply(obj2), mergeFunction);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.TableCollectors$$ExternalSyntheticLambda2
            public /* synthetic */ BiFunction andThen(Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Table table = (Table) obj;
                TableCollectors.$r8$lambda$butTipWhgtbxfBh3mwO9KSqqMF0(mergeFunction, table, (Table) obj2);
                return table;
            }
        }, new Collector.Characteristics[0]);
    }

    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(final Function<? super T, ? extends R> rowFunction, final Function<? super T, ? extends C> columnFunction, final Function<? super T, ? extends V> valueFunction, final BinaryOperator<V> mergeFunction) {
        Preconditions.checkNotNull(rowFunction, "rowFunction");
        Preconditions.checkNotNull(columnFunction, "columnFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        Preconditions.checkNotNull(mergeFunction, "mergeFunction");
        return Collector.CC.of(new TableCollectors$$ExternalSyntheticLambda3(), new BiConsumer() { // from class: com.google.common.collect.TableCollectors$$ExternalSyntheticLambda4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TableCollectors.ImmutableTableCollectorState immutableTableCollectorState = (TableCollectors.ImmutableTableCollectorState) obj;
                immutableTableCollectorState.put(rowFunction.apply(obj2), columnFunction.apply(obj2), valueFunction.apply(obj2), mergeFunction);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.TableCollectors$$ExternalSyntheticLambda5
            public /* synthetic */ BiFunction andThen(Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                TableCollectors.ImmutableTableCollectorState immutableTableCollectorState = (TableCollectors.ImmutableTableCollectorState) obj;
                immutableTableCollectorState.combine((TableCollectors.ImmutableTableCollectorState) obj2, mergeFunction);
                return immutableTableCollectorState;
            }
        }, new TableCollectors$$ExternalSyntheticLambda6(), new Collector.Characteristics[0]);
    }
}
