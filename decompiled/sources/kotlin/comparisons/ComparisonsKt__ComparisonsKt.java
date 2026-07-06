package kotlin.comparisons;

import java.util.Comparator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ComparisonsKt__ComparisonsKt {

    /* JADX INFO: renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareBy$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2<T> implements Comparator {
        public final /* synthetic */ Function1<T, Comparable<?>> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function1<? super T, ? extends Comparable<?>> function1) {
            this.$selector = function1;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            Function1<T, Comparable<?>> function1 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(function1.invoke(t), function1.invoke(t2));
        }
    }

    /* JADX INFO: renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareByDescending$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1<T> implements Comparator {
        public final /* synthetic */ Function1<T, Comparable<?>> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super T, ? extends Comparable<?>> function1) {
            this.$selector = function1;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            Function1<T, Comparable<?>> function1 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(function1.invoke(t2), function1.invoke(t));
        }
    }

    @NotNull
    public static final <T> Comparator<T> compareBy(@NotNull final Function1<? super T, ? extends Comparable<?>>... selectors) {
        Intrinsics.checkNotNullParameter(selectors, "selectors");
        if (selectors.length > 0) {
            return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(obj, obj2, selectors);
                }
            };
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    @InlineOnly
    public static final <T> Comparator<T> compareByDescending(Function1<? super T, ? extends Comparable<?>> selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new AnonymousClass1(selector);
    }

    public static <T extends Comparable<?>> int compareValues(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        return t.compareTo(t2);
    }

    public static final <T> int compareValuesBy(T t, T t2, @NotNull Function1<? super T, ? extends Comparable<?>>... selectors) {
        Intrinsics.checkNotNullParameter(selectors, "selectors");
        if (selectors.length > 0) {
            return compareValuesByImpl$ComparisonsKt__ComparisonsKt(t, t2, selectors);
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static final <T> int compareValuesByImpl$ComparisonsKt__ComparisonsKt(T t, T t2, Function1<? super T, ? extends Comparable<?>>[] function1Arr) {
        for (Function1<? super T, ? extends Comparable<?>> function1 : function1Arr) {
            int iCompareValues = compareValues(function1.invoke(t), function1.invoke(t2));
            if (iCompareValues != 0) {
                return iCompareValues;
            }
        }
        return 0;
    }

    @NotNull
    public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
        NaturalOrderComparator naturalOrderComparator = NaturalOrderComparator.INSTANCE;
        Intrinsics.checkNotNull(naturalOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>");
        return naturalOrderComparator;
    }

    @NotNull
    public static final <T> Comparator<T> nullsFirst(@NotNull final Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt__ComparisonsKt.nullsFirst$lambda$3$ComparisonsKt__ComparisonsKt(comparator, obj, obj2);
            }
        };
    }

    public static final int nullsFirst$lambda$3$ComparisonsKt__ComparisonsKt(Comparator comparator, Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        return comparator.compare(obj, obj2);
    }

    @NotNull
    public static final <T> Comparator<T> nullsLast(@NotNull final Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt__ComparisonsKt.nullsLast$lambda$4$ComparisonsKt__ComparisonsKt(comparator, obj, obj2);
            }
        };
    }

    public static final int nullsLast$lambda$4$ComparisonsKt__ComparisonsKt(Comparator comparator, Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return 1;
        }
        if (obj2 == null) {
            return -1;
        }
        return comparator.compare(obj, obj2);
    }

    @NotNull
    public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        ReverseOrderComparator reverseOrderComparator = ReverseOrderComparator.INSTANCE;
        Intrinsics.checkNotNull(reverseOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder>");
        return reverseOrderComparator;
    }

    @NotNull
    public static final <T> Comparator<T> reversed(@NotNull Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        if (comparator instanceof ReversedComparator) {
            return ((ReversedComparator) comparator).comparator;
        }
        NaturalOrderComparator naturalOrderComparator = NaturalOrderComparator.INSTANCE;
        if (comparator.equals(naturalOrderComparator)) {
            ReverseOrderComparator reverseOrderComparator = ReverseOrderComparator.INSTANCE;
            Intrinsics.checkNotNull(reverseOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>");
            return reverseOrderComparator;
        }
        if (!comparator.equals(ReverseOrderComparator.INSTANCE)) {
            return new ReversedComparator(comparator);
        }
        Intrinsics.checkNotNull(naturalOrderComparator, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>");
        return naturalOrderComparator;
    }

    @NotNull
    public static final <T> Comparator<T> then(@NotNull final Comparator<T> comparator, @NotNull final Comparator<? super T> comparator2) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(comparator2, "comparator");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt__ComparisonsKt.then$lambda$1$ComparisonsKt__ComparisonsKt(comparator, comparator2, obj, obj2);
            }
        };
    }

    public static final int then$lambda$1$ComparisonsKt__ComparisonsKt(Comparator comparator, Comparator comparator2, Object obj, Object obj2) {
        int iCompare = comparator.compare(obj, obj2);
        return iCompare != 0 ? iCompare : comparator2.compare(obj, obj2);
    }

    @InlineOnly
    public static final <T> Comparator<T> thenBy(final Comparator<T> comparator, final Function1<? super T, ? extends Comparable<?>> selector) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenBy.1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                Function1<T, Comparable<?>> function1 = selector;
                return ComparisonsKt__ComparisonsKt.compareValues(function1.invoke(t), function1.invoke(t2));
            }
        };
    }

    @InlineOnly
    public static final <T> Comparator<T> thenByDescending(final Comparator<T> comparator, final Function1<? super T, ? extends Comparable<?>> selector) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenByDescending.1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                Function1<T, Comparable<?>> function1 = selector;
                return ComparisonsKt__ComparisonsKt.compareValues(function1.invoke(t2), function1.invoke(t));
            }
        };
    }

    @InlineOnly
    public static final <T> Comparator<T> thenComparator(final Comparator<T> comparator, final Function2<? super T, ? super T, Integer> comparison) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(comparison, "comparison");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenComparator.1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : comparison.invoke(t, t2).intValue();
            }
        };
    }

    @NotNull
    public static final <T> Comparator<T> thenDescending(@NotNull final Comparator<T> comparator, @NotNull final Comparator<? super T> comparator2) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(comparator2, "comparator");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt__ComparisonsKt.thenDescending$lambda$2$ComparisonsKt__ComparisonsKt(comparator, comparator2, obj, obj2);
            }
        };
    }

    public static final int thenDescending$lambda$2$ComparisonsKt__ComparisonsKt(Comparator comparator, Comparator comparator2, Object obj, Object obj2) {
        int iCompare = comparator.compare(obj, obj2);
        return iCompare != 0 ? iCompare : comparator2.compare(obj2, obj);
    }

    @InlineOnly
    public static final <T, K> Comparator<T> compareByDescending(final Comparator<? super K> comparator, final Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.2
            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Comparator<? super K> comparator2 = comparator;
                Function1<T, K> function1 = selector;
                return comparator2.compare((Object) function1.invoke(t2), (Object) function1.invoke(t));
            }
        };
    }

    @InlineOnly
    public static final <T extends Comparable<? super T>> Comparator<T> nullsFirst() {
        return nullsFirst(naturalOrder());
    }

    @InlineOnly
    public static final <T extends Comparable<? super T>> Comparator<T> nullsLast() {
        return nullsLast(naturalOrder());
    }

    @InlineOnly
    public static final <T, K> Comparator<T> thenBy(final Comparator<T> comparator, final Comparator<? super K> comparator2, final Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(comparator2, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenBy.2
            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                Comparator<? super K> comparator3 = comparator2;
                Function1<T, K> function1 = selector;
                return comparator3.compare((Object) function1.invoke(t), (Object) function1.invoke(t2));
            }
        };
    }

    @InlineOnly
    public static final <T, K> Comparator<T> thenByDescending(final Comparator<T> comparator, final Comparator<? super K> comparator2, final Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(comparator2, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.thenByDescending.2
            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                Comparator<? super K> comparator3 = comparator2;
                Function1<T, K> function1 = selector;
                return comparator3.compare((Object) function1.invoke(t2), (Object) function1.invoke(t));
            }
        };
    }

    @InlineOnly
    public static final <T> Comparator<T> compareBy(Function1<? super T, ? extends Comparable<?>> selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new AnonymousClass2(selector);
    }

    @InlineOnly
    public static final <T> int compareValuesBy(T t, T t2, Function1<? super T, ? extends Comparable<?>> selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        return compareValues(selector.invoke(t), selector.invoke(t2));
    }

    @InlineOnly
    public static final <T, K> Comparator<T> compareBy(final Comparator<? super K> comparator, final Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.3
            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Comparator<? super K> comparator2 = comparator;
                Function1<T, K> function1 = selector;
                return comparator2.compare((Object) function1.invoke(t), (Object) function1.invoke(t2));
            }
        };
    }

    @InlineOnly
    public static final <T, K> int compareValuesBy(T t, T t2, Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return comparator.compare(selector.invoke(t), selector.invoke(t2));
    }
}
