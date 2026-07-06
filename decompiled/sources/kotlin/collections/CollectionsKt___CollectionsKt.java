package kotlin.collections;

import android.R;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__AppendableKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_Collections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,3800:1\n295#1,2:3801\n528#1,7:3803\n543#1,6:3810\n865#1,2:3817\n796#1:3819\n1878#1,2:3820\n797#1,2:3822\n1880#1:3824\n799#1:3825\n1878#1,3:3826\n817#1,2:3829\n855#1,2:3831\n1267#1,4:3841\n1236#1,4:3845\n1252#1,4:3849\n1299#1,4:3853\n1460#1,5:3857\n1475#1,5:3862\n1516#1,3:3867\n1519#1,3:3877\n1534#1,3:3880\n1537#1,3:3890\n1634#1,3:3907\n1604#1,4:3910\n1593#1:3914\n1878#1,2:3915\n1880#1:3918\n1594#1:3919\n1878#1,3:3920\n1625#1:3923\n1869#1:3924\n1870#1:3926\n1626#1:3927\n1869#1,2:3928\n1878#1,3:3930\n2967#1,3:3933\n2970#1,6:3937\n2992#1,3:3943\n2995#1,7:3947\n865#1,2:3954\n827#1:3956\n855#1,2:3957\n827#1:3959\n855#1,2:3960\n827#1:3962\n855#1,2:3963\n3522#1,8:3969\n3550#1,7:3977\n3581#1,10:3984\n1#2:3816\n1#2:3917\n1#2:3925\n1#2:3936\n1#2:3946\n37#3:3833\n36#3,3:3834\n37#3:3837\n36#3,3:3838\n384#4,7:3870\n384#4,7:3883\n384#4,7:3893\n384#4,7:3900\n32#5,2:3965\n32#5,2:3967\n*S KotlinDebug\n*F\n+ 1 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n174#1:3801,2\n184#1:3803,7\n194#1:3810,6\n774#1:3817,2\n785#1:3819\n785#1:3820,2\n785#1:3822,2\n785#1:3824\n785#1:3825\n796#1:3826,3\n808#1:3829,2\n827#1:3831,2\n1194#1:3841,4\n1209#1:3845,4\n1223#1:3849,4\n1286#1:3853,4\n1374#1:3857,5\n1387#1:3862,5\n1491#1:3867,3\n1491#1:3877,3\n1504#1:3880,3\n1504#1:3890,3\n1563#1:3907,3\n1573#1:3910,4\n1583#1:3914\n1583#1:3915,2\n1583#1:3918\n1583#1:3919\n1593#1:3920,3\n1617#1:3923\n1617#1:3924\n1617#1:3926\n1617#1:3927\n1625#1:3928,2\n2767#1:3930,3\n3067#1:3933,3\n3067#1:3937,6\n3084#1:3943,3\n3084#1:3947,7\n3260#1:3954,2\n3268#1:3956\n3268#1:3957,2\n3278#1:3959\n3278#1:3960,2\n3288#1:3962\n3288#1:3963,2\n3511#1:3969,8\n3539#1:3977,7\n3568#1:3984,10\n1583#1:3917\n1617#1:3925\n3067#1:3936\n3084#1:3946\n1042#1:3833\n1042#1:3834,3\n1089#1:3837\n1089#1:3838,3\n1491#1:3870,7\n1504#1:3883,7\n1518#1:3893,7\n1536#1:3900,7\n3456#1:3965,2\n3498#1:3967,2\n*E\n"})
public class CollectionsKt___CollectionsKt extends CollectionsKt___CollectionsJvmKt {
    public static /* synthetic */ Object $r8$lambda$BxIWqBmJzcr8XhZUTf0HOKSqQtU(int i, int i2) {
        elementAt$lambda$0$CollectionsKt___CollectionsKt(i, i2);
        throw null;
    }

    public static final <T> boolean all(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (!predicate.invoke(it.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean any(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof Collection ? !((Collection) iterable).isEmpty() : iterable.iterator().hasNext();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <T> Iterable<T> asIterable(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable;
    }

    @NotNull
    public static <T> Sequence<T> asSequence(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(iterable);
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associate(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(it.next());
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K> Map<K, T> associateBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (T t : iterable) {
            linkedHashMap.put(keySelector.invoke(t), t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, ? super T>> M associateByTo(@NotNull Iterable<? extends T> iterable, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : iterable) {
            destination.put(keySelector.invoke(t), t);
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull Iterable<? extends T> iterable, @NotNull M destination, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(it.next());
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <K, V> Map<K, V> associateWith(@NotNull Iterable<? extends K> iterable, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (K k : iterable) {
            linkedHashMap.put(k, valueSelector.invoke(k));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateWithTo(@NotNull Iterable<? extends K> iterable, @NotNull M destination, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (K k : iterable) {
            destination.put(k, valueSelector.invoke(k));
        }
        return destination;
    }

    @JvmName(name = "averageOfByte")
    public static final double averageOfByte(@NotNull Iterable<Byte> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Byte> it = iterable.iterator();
        double dByteValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dByteValue += (double) it.next().byteValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dByteValue / ((double) i);
    }

    @JvmName(name = "averageOfDouble")
    public static final double averageOfDouble(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        double dDoubleValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dDoubleValue += it.next().doubleValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dDoubleValue / ((double) i);
    }

    @JvmName(name = "averageOfFloat")
    public static final double averageOfFloat(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        double dFloatValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dFloatValue += (double) it.next().floatValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dFloatValue / ((double) i);
    }

    @JvmName(name = "averageOfInt")
    public static final double averageOfInt(@NotNull Iterable<Integer> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Integer> it = iterable.iterator();
        double dIntValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dIntValue += (double) it.next().intValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dIntValue / ((double) i);
    }

    @JvmName(name = "averageOfLong")
    public static final double averageOfLong(@NotNull Iterable<Long> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Long> it = iterable.iterator();
        double dLongValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dLongValue += it.next().longValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dLongValue / ((double) i);
    }

    @JvmName(name = "averageOfShort")
    public static final double averageOfShort(@NotNull Iterable<Short> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Short> it = iterable.iterator();
        double dShortValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dShortValue += (double) it.next().shortValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dShortValue / ((double) i);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<List<T>> chunked(@NotNull Iterable<? extends T> iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return windowed(iterable, i, i, true);
    }

    @InlineOnly
    public static final <T> T component1(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(0);
    }

    @InlineOnly
    public static final <T> T component2(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(1);
    }

    @InlineOnly
    public static final <T> T component3(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(2);
    }

    @InlineOnly
    public static final <T> T component4(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(3);
    }

    @InlineOnly
    public static final <T> T component5(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(4);
    }

    public static <T> boolean contains(@NotNull Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(t) : indexOf(iterable, t) >= 0;
    }

    public static final <T> int count(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        Iterator<? extends T> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i;
    }

    @NotNull
    public static final <T> List<T> distinct(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return toList(toMutableSet(iterable));
    }

    @NotNull
    public static final <T, K> List<T> distinctBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (hashSet.add(selector.invoke(t))) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static <T> List<T> drop(@NotNull Iterable<? extends T> iterable, int i) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return toList(iterable);
        }
        if (iterable instanceof Collection) {
            int size = ((Collection) iterable).size() - i;
            if (size <= 0) {
                return EmptyList.INSTANCE;
            }
            if (size == 1) {
                return CollectionsKt__CollectionsJVMKt.listOf(last(iterable));
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    List list = (List) iterable;
                    int size2 = list.size();
                    while (i < size2) {
                        arrayList.add(list.get(i));
                        i++;
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(i);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i2 = 0;
        for (T t : iterable) {
            if (i2 >= i) {
                arrayList.add(t);
            } else {
                i2++;
            }
        }
        return CollectionsKt__CollectionsKt.optimizeReadOnlyList(arrayList);
    }

    @NotNull
    public static final <T> List<T> dropLast(@NotNull List<? extends T> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int size = list.size() - i;
        if (size < 0) {
            size = 0;
        }
        return take(list, size);
    }

    @NotNull
    public static final <T> List<T> dropLastWhile(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (!list.isEmpty()) {
            ListIterator<? extends T> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                if (!predicate.invoke(listIterator.previous()).booleanValue()) {
                    return take(list, listIterator.nextIndex() + 1);
                }
            }
        }
        return EmptyList.INSTANCE;
    }

    @NotNull
    public static final <T> List<T> dropWhile(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (T t : iterable) {
            if (z) {
                arrayList.add(t);
            } else if (!predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
                z = true;
            }
        }
        return arrayList;
    }

    public static final <T> T elementAt(@NotNull Iterable<? extends T> iterable, final int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof List ? (T) ((List) iterable).get(i) : (T) elementAtOrElse(iterable, i, new Function1() { // from class: kotlin.collections.CollectionsKt___CollectionsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CollectionsKt___CollectionsKt.elementAt$lambda$0$CollectionsKt___CollectionsKt(i, ((Integer) obj).intValue());
                throw null;
            }
        });
    }

    public static final Object elementAt$lambda$0$CollectionsKt___CollectionsKt(int i, int i2) {
        throw new IndexOutOfBoundsException("Collection doesn't contain element at index " + i + '.');
    }

    public static final <T> T elementAtOrElse(@NotNull Iterable<? extends T> iterable, int i, @NotNull Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (iterable instanceof List) {
            List list = (List) iterable;
            return (i < 0 || i >= list.size()) ? defaultValue.invoke(Integer.valueOf(i)) : (T) list.get(i);
        }
        if (i < 0) {
            return defaultValue.invoke(Integer.valueOf(i));
        }
        int i2 = 0;
        for (T t : iterable) {
            int i3 = i2 + 1;
            if (i == i2) {
                return t;
            }
            i2 = i3;
        }
        return defaultValue.invoke(Integer.valueOf(i));
    }

    @Nullable
    public static final <T> T elementAtOrNull(@NotNull Iterable<? extends T> iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) getOrNull((List) iterable, i);
        }
        if (i < 0) {
            return null;
        }
        int i2 = 0;
        for (T t : iterable) {
            int i3 = i2 + 1;
            if (i == i2) {
                return t;
            }
            i2 = i3;
        }
        return null;
    }

    @NotNull
    public static final <T> List<T> filter(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterIndexed(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (predicate.invoke(Integer.valueOf(i), t).booleanValue()) {
                arrayList.add(t);
            }
            i = i2;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterIndexedTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (predicate.invoke(Integer.valueOf(i), t).booleanValue()) {
                destination.add(t);
            }
            i = i2;
        }
        return destination;
    }

    public static final <R> List<R> filterIsInstance(Iterable<?> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = iterable.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        it.next();
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <R, C extends Collection<? super R>> C filterIsInstanceTo(Iterable<?> iterable, C destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<?> it = iterable.iterator();
        if (!it.hasNext()) {
            return destination;
        }
        it.next();
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> List<T> filterNot(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterNotNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ArrayList arrayList = new ArrayList();
        filterNotNullTo(iterable, arrayList);
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super T>, T> C filterNotNullTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t : iterable) {
            if (t != null) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterNotTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : iterable) {
            if (!predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : iterable) {
            if (predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @InlineOnly
    public static final <T> T find(Iterable<? extends T> iterable, Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        return null;
    }

    @InlineOnly
    public static final <T> T findLast(Iterable<? extends T> iterable, Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                t = (T) obj;
            }
        }
        return t;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public static final <T> T first(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOf(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        R rInvoke;
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "transform");
        while (true) {
            if (!itM.hasNext()) {
                rInvoke = null;
                break;
            }
            rInvoke = function1.invoke((Object) itM.next());
            if (rInvoke != null) {
                break;
            }
        }
        if (rInvoke != null) {
            return rInvoke;
        }
        throw new NoSuchElementException("No element of the collection was transformed to a non-null value.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOfOrNull(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "transform");
        while (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            if (rInvoke != null) {
                return rInvoke;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @Nullable
    public static final <T> T firstOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        return null;
    }

    @NotNull
    public static final <T, R> List<R> flatMap(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(it.next()));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapIndexedIterable(Iterable<? extends T> iterable, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedIterableTo(Iterable<? extends T> iterable, C destination, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequence")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapIndexedSequence(Iterable<? extends T> iterable, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequenceTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedSequenceTo(Iterable<? extends T> iterable, C destination, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequence")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapSequence(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(it.next()));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequenceTo")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapSequenceTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(it.next()));
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C flatMapTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(it.next()));
        }
        return destination;
    }

    public static final <T, R> R fold(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            r = operation.invoke(r, it.next());
        }
        return r;
    }

    public static final <T, R> R foldIndexed(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            r = operation.invoke(Integer.valueOf(i), r, t);
            i = i2;
        }
        return r;
    }

    public static final <T, R> R foldRight(@NotNull List<? extends T> list, R r, @NotNull Function2<? super T, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (!list.isEmpty()) {
            ListIterator<? extends T> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                r = operation.invoke(listIterator.previous(), r);
            }
        }
        return r;
    }

    public static final <T, R> R foldRightIndexed(@NotNull List<? extends T> list, R r, @NotNull Function3<? super Integer, ? super T, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (!list.isEmpty()) {
            ListIterator<? extends T> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                r = operation.invoke(Integer.valueOf(listIterator.previousIndex()), listIterator.previous(), r);
            }
        }
        return r;
    }

    @HidesMembers
    public static final <T> void forEach(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Unit> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "action");
        while (itM.hasNext()) {
            function1.invoke((Object) itM.next());
        }
    }

    public static final <T> void forEachIndexed(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            action.invoke(Integer.valueOf(i), t);
            i = i2;
        }
    }

    @InlineOnly
    public static final <T> T getOrElse(List<? extends T> list, int i, Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= list.size()) ? defaultValue.invoke(Integer.valueOf(i)) : list.get(i);
    }

    @Nullable
    public static <T> T getOrNull(@NotNull List<? extends T> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    @NotNull
    public static final <T, K> Map<K, List<T>> groupBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : iterable) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, List<T>>> M groupByTo(@NotNull Iterable<? extends T> iterable, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : iterable) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(t);
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Grouping<T, K> groupingBy(@NotNull final Iterable<? extends T> iterable, @NotNull final Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        return new Grouping<T, K>() { // from class: kotlin.collections.CollectionsKt___CollectionsKt.groupingBy.1
            @Override // kotlin.collections.Grouping
            public K keyOf(T t) {
                return keySelector.invoke(t);
            }

            @Override // kotlin.collections.Grouping
            public Iterator<T> sourceIterator() {
                return iterable.iterator();
            }
        };
    }

    public static final <T> int indexOf(@NotNull Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(t);
        }
        int i = 0;
        for (T t2 : iterable) {
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual(t, t2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfFirst(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        int i = 0;
        while (itM.hasNext()) {
            R.color colorVar = (Object) itM.next();
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (function1.invoke(colorVar).booleanValue()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfLast(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        int i = -1;
        int i2 = 0;
        while (itM.hasNext()) {
            R.color colorVar = (Object) itM.next();
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (function1.invoke(colorVar).booleanValue()) {
                i = i2;
            }
            i2++;
        }
        return i;
    }

    @NotNull
    public static final <T> Set<T> intersect(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(iterable);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <T, A extends Appendable> A joinTo(@NotNull Iterable<? extends T> iterable, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (T t : iterable) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            StringsKt__AppendableKt.appendElement(buffer, t, function1);
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ Appendable joinTo$default(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(iterable, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (i2 & 64) != 0 ? null : function1);
        return appendable;
    }

    @NotNull
    public static final <T> String joinToString(@NotNull Iterable<? extends T> iterable, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(iterable, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(iterable, charSequence, charSequence2, charSequence3, i, charSequence5, function12);
    }

    public static final <T> T last(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        T t = null;
        boolean z = false;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final <T> int lastIndexOf(@NotNull Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).lastIndexOf(t);
        }
        int i = -1;
        int i2 = 0;
        for (T t2 : iterable) {
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual(t, t2)) {
                i = i2;
            }
            i2++;
        }
        return i;
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                t = (T) obj;
            }
        }
        return t;
    }

    @NotNull
    public static final <T, R> List<R> map(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(transform.invoke(it.next()));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexed(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            arrayList.add(transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexedNotNull(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            R rInvoke = transform.invoke(Integer.valueOf(i), t);
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
            i = i2;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            R rInvoke = transform.invoke(Integer.valueOf(i), t);
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
            i = i2;
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            destination.add(transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @NotNull
    public static final <T, R> List<R> mapNotNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            R rInvoke = transform.invoke(it.next());
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            R rInvoke = transform.invoke(it.next());
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapTo(@NotNull Iterable<? extends T> iterable, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            destination.add(transform.invoke(it.next()));
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T maxByOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <T, R extends Comparable<? super R>> T maxByOrThrow(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double maxOf(Iterable<? extends T> iterable, Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R maxOfOrNull(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) itM.next());
        while (itM.hasNext()) {
            R rInvoke2 = function1.invoke((Object) itM.next());
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWith(Iterable<? extends T> iterable, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWithOrNull(Iterable<? extends T> iterable, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: maxOrNull, reason: collision with other method in class */
    public static final Double m1019maxOrNull(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, it.next().doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final double maxOrThrow(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, it.next().doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T maxWithOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final <T> T maxWithOrThrow(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T minByOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <T, R extends Comparable<? super R>> T minByOrThrow(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double minOf(Iterable<? extends T> iterable, Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R minOfOrNull(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) itM.next());
        while (itM.hasNext()) {
            R rInvoke2 = function1.invoke((Object) itM.next());
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWith(Iterable<? extends T> iterable, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWithOrNull(Iterable<? extends T> iterable, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: minOrNull, reason: collision with other method in class */
    public static final Double m1027minOrNull(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, it.next().doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final double minOrThrow(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, it.next().doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T minWithOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final <T> T minWithOrThrow(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @NotNull
    public static final <T> List<T> minus(@NotNull Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        boolean z = false;
        for (T t2 : iterable) {
            boolean z2 = true;
            if (!z && Intrinsics.areEqual(t2, t)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    @InlineOnly
    public static final <T> List<T> minusElement(Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return minus(iterable, t);
    }

    public static final <T> boolean none(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).isEmpty() : !iterable.iterator().hasNext();
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, C extends Iterable<? extends T>> C onEach(@NotNull C c, @NotNull Function1<? super T, Unit> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(c, "<this>", function1, "action");
        while (itM.hasNext()) {
            function1.invoke((Object) itM.next());
        }
        return c;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, C extends Iterable<? extends T>> C onEachIndexed(@NotNull C c, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(c, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int i = 0;
        for (T t : c) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            action.invoke(Integer.valueOf(i), t);
            i = i2;
        }
        return c;
    }

    @NotNull
    public static final <T> Pair<List<T>, List<T>> partition(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (T t : iterable) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            } else {
                arrayList2.add(t);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return plus((Collection) iterable, (Object) t);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, iterable);
        arrayList.add(t);
        return arrayList;
    }

    @InlineOnly
    public static final <T> List<T> plusElement(Iterable<? extends T> iterable, T t) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return plus(iterable, t);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> T random(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return (T) random(collection, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> T randomOrNull(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return (T) randomOrNull(collection, Random.Default);
    }

    public static final <S, T extends S> S reduce(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        S next = it.next();
        while (it.hasNext()) {
            next = operation.invoke(next, it.next());
        }
        return next;
    }

    public static final <S, T extends S> S reduceIndexed(@NotNull Iterable<? extends T> iterable, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        S next = it.next();
        int i = 1;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            next = operation.invoke(Integer.valueOf(i), next, it.next());
            i = i2;
        }
        return next;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceIndexedOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        S next = it.next();
        int i = 1;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            next = operation.invoke(Integer.valueOf(i), next, it.next());
            i = i2;
        }
        return next;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        S next = it.next();
        while (it.hasNext()) {
            next = operation.invoke(next, it.next());
        }
        return next;
    }

    public static final <S, T extends S> S reduceRight(@NotNull List<? extends T> list, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        if (!listIterator.hasPrevious()) {
            throw new UnsupportedOperationException("Empty list can't be reduced.");
        }
        S sPrevious = listIterator.previous();
        while (listIterator.hasPrevious()) {
            sPrevious = operation.invoke(listIterator.previous(), sPrevious);
        }
        return sPrevious;
    }

    public static final <S, T extends S> S reduceRightIndexed(@NotNull List<? extends T> list, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        if (!listIterator.hasPrevious()) {
            throw new UnsupportedOperationException("Empty list can't be reduced.");
        }
        S sPrevious = listIterator.previous();
        while (listIterator.hasPrevious()) {
            sPrevious = operation.invoke(Integer.valueOf(listIterator.previousIndex()), listIterator.previous(), sPrevious);
        }
        return sPrevious;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceRightIndexedOrNull(@NotNull List<? extends T> list, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        if (!listIterator.hasPrevious()) {
            return null;
        }
        S sPrevious = listIterator.previous();
        while (listIterator.hasPrevious()) {
            sPrevious = operation.invoke(Integer.valueOf(listIterator.previousIndex()), listIterator.previous(), sPrevious);
        }
        return sPrevious;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceRightOrNull(@NotNull List<? extends T> list, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        if (!listIterator.hasPrevious()) {
            return null;
        }
        S sPrevious = listIterator.previous();
        while (listIterator.hasPrevious()) {
            sPrevious = operation.invoke(listIterator.previous(), sPrevious);
        }
        return sPrevious;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Iterable<T> requireNoNulls(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new IllegalArgumentException("null element found in " + iterable + '.');
            }
        }
        return iterable;
    }

    @NotNull
    public static final <T> List<T> reversed(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return toList(iterable);
        }
        List<T> mutableList = toMutableList(iterable);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFold(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 9);
        if (iCollectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault + 1);
        arrayList.add(r);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            r = operation.invoke(r, it.next());
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFoldIndexed(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 9);
        if (iCollectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault + 1);
        arrayList.add(r);
        Iterator<? extends T> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            r = operation.invoke(Integer.valueOf(i), r, it.next());
            arrayList.add(r);
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> List<S> runningReduce(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        S next = it.next();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        arrayList.add(next);
        while (it.hasNext()) {
            next = operation.invoke(next, it.next());
            arrayList.add(next);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> List<S> runningReduceIndexed(@NotNull Iterable<? extends T> iterable, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        S next = it.next();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        arrayList.add(next);
        int i = 1;
        while (it.hasNext()) {
            next = operation.invoke(Integer.valueOf(i), next, it.next());
            arrayList.add(next);
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> scan(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 9);
        if (iCollectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault + 1);
        arrayList.add(r);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            r = operation.invoke(r, it.next());
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> scanIndexed(@NotNull Iterable<? extends T> iterable, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 9);
        if (iCollectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault + 1);
        arrayList.add(r);
        Iterator<? extends T> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            r = operation.invoke(Integer.valueOf(i), r, it.next());
            arrayList.add(r);
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void shuffle(@NotNull List<T> list, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list); lastIndex > 0; lastIndex--) {
            int iNextInt = random.nextInt(lastIndex + 1);
            list.set(iNextInt, list.set(lastIndex, list.get(iNextInt)));
        }
    }

    public static final <T> T single(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        T t = null;
        boolean z = false;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Collection contains more than one matching element.");
                }
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "predicate");
        boolean z = false;
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                if (z) {
                    return null;
                }
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        return null;
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull List<? extends T> list, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : toList(list.subList(indices.first, indices.last + 1));
    }

    public static final <T, R extends Comparable<? super R>> void sortBy(@NotNull List<T> list, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (list.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
        }
    }

    public static final <T, R extends Comparable<? super R>> void sortByDescending(@NotNull List<T> list, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (list.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
        }
    }

    public static final <T extends Comparable<? super T>> void sortDescending(@NotNull List<T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        CollectionsKt__MutableCollectionsJVMKt.sortWith(list, ComparisonsKt__ComparisonsKt.reverseOrder());
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sorted(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            List<T> mutableList = toMutableList(iterable);
            CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
            return mutableList;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return toList(iterable);
        }
        Object[] array = collection.toArray(new Comparable[0]);
        ArraysKt___ArraysJvmKt.sort(array);
        return ArraysKt___ArraysJvmKt.asList(array);
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iterable, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedByDescending(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iterable, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sortedDescending(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return sortedWith(iterable, ComparisonsKt__ComparisonsKt.reverseOrder());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> List<T> sortedWith(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!(iterable instanceof Collection)) {
            List<T> mutableList = toMutableList(iterable);
            CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, comparator);
            return mutableList;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return toList(iterable);
        }
        Object[] array = collection.toArray(new Object[0]);
        ArraysKt___ArraysJvmKt.sortWith(array, comparator);
        return ArraysKt___ArraysJvmKt.asList(array);
    }

    @NotNull
    public static final <T> Set<T> subtract(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(iterable);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> int sumBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Integer> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        int iIntValue = 0;
        while (itM.hasNext()) {
            iIntValue += function1.invoke((Object) itM.next()).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> double sumByDouble(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        double dDoubleValue = 0.0d;
        while (itM.hasNext()) {
            dDoubleValue += function1.invoke((Object) itM.next()).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfByte")
    public static final int sumOfByte(@NotNull Iterable<Byte> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Byte> it = iterable.iterator();
        int iByteValue = 0;
        while (it.hasNext()) {
            iByteValue += it.next().byteValue();
        }
        return iByteValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final <T> double sumOfDouble(Iterable<? extends T> iterable, Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        double dDoubleValue = 0.0d;
        while (itM.hasNext()) {
            dDoubleValue += function1.invoke((Object) itM.next()).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfFloat")
    public static final float sumOfFloat(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        float fFloatValue = 0.0f;
        while (it.hasNext()) {
            fFloatValue += it.next().floatValue();
        }
        return fFloatValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final <T> int sumOfInt(Iterable<? extends T> iterable, Function1<? super T, Integer> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        int iIntValue = 0;
        while (itM.hasNext()) {
            iIntValue += function1.invoke((Object) itM.next()).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final <T> long sumOfLong(Iterable<? extends T> iterable, Function1<? super T, Long> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        long jLongValue = 0;
        while (itM.hasNext()) {
            jLongValue += function1.invoke((Object) itM.next()).longValue();
        }
        return jLongValue;
    }

    @JvmName(name = "sumOfShort")
    public static final int sumOfShort(@NotNull Iterable<Short> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Short> it = iterable.iterator();
        int iShortValue = 0;
        while (it.hasNext()) {
            iShortValue += it.next().shortValue();
        }
        return iShortValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> int sumOfUInt(Iterable<? extends T> iterable, Function1<? super T, UInt> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        int i = 0;
        while (itM.hasNext()) {
            i += function1.invoke((Object) itM.next()).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> long sumOfULong(Iterable<? extends T> iterable, Function1<? super T, ULong> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        long j = 0;
        while (itM.hasNext()) {
            j += function1.invoke((Object) itM.next()).data;
        }
        return j;
    }

    @NotNull
    public static <T> List<T> take(@NotNull Iterable<? extends T> iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (iterable instanceof Collection) {
            if (i >= ((Collection) iterable).size()) {
                return toList(iterable);
            }
            if (i == 1) {
                return CollectionsKt__CollectionsJVMKt.listOf(first(iterable));
            }
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator<? extends T> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return CollectionsKt__CollectionsKt.optimizeReadOnlyList(arrayList);
    }

    @NotNull
    public static final <T> List<T> takeLast(@NotNull List<? extends T> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int size = list.size();
        if (i >= size) {
            return toList(list);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(last((List) list));
        }
        ArrayList arrayList = new ArrayList(i);
        if (list instanceof RandomAccess) {
            for (int i2 = size - i; i2 < size; i2++) {
                arrayList.add(list.get(i2));
            }
        } else {
            ListIterator<? extends T> listIterator = list.listIterator(size - i);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> takeLastWhile(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (list.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (!predicate.invoke(listIterator.previous()).booleanValue()) {
                listIterator.next();
                int size = list.size() - listIterator.nextIndex();
                if (size == 0) {
                    return EmptyList.INSTANCE;
                }
                ArrayList arrayList = new ArrayList(size);
                while (listIterator.hasNext()) {
                    arrayList.add(listIterator.next());
                }
                return arrayList;
            }
        }
        return toList(list);
    }

    @NotNull
    public static final <T> List<T> takeWhile(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!predicate.invoke(t).booleanValue()) {
                break;
            }
            arrayList.add(t);
        }
        return arrayList;
    }

    @NotNull
    public static boolean[] toBooleanArray(@NotNull Collection<Boolean> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        boolean[] zArr = new boolean[collection.size()];
        Iterator<Boolean> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            zArr[i] = it.next().booleanValue();
            i++;
        }
        return zArr;
    }

    @NotNull
    public static final byte[] toByteArray(@NotNull Collection<Byte> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        byte[] bArr = new byte[collection.size()];
        Iterator<Byte> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            bArr[i] = it.next().byteValue();
            i++;
        }
        return bArr;
    }

    @NotNull
    public static final char[] toCharArray(@NotNull Collection<Character> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        char[] cArr = new char[collection.size()];
        Iterator<Character> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            cArr[i] = it.next().charValue();
            i++;
        }
        return cArr;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C toCollection(@NotNull Iterable<? extends T> iterable, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    @NotNull
    public static final double[] toDoubleArray(@NotNull Collection<Double> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        double[] dArr = new double[collection.size()];
        Iterator<Double> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            dArr[i] = it.next().doubleValue();
            i++;
        }
        return dArr;
    }

    @NotNull
    public static final float[] toFloatArray(@NotNull Collection<Float> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        float[] fArr = new float[collection.size()];
        Iterator<Float> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            fArr[i] = it.next().floatValue();
            i++;
        }
        return fArr;
    }

    @NotNull
    public static <T> HashSet<T> toHashSet(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        HashSet<T> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 12)));
        toCollection(iterable, hashSet);
        return hashSet;
    }

    @NotNull
    public static final int[] toIntArray(@NotNull Collection<Integer> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    @NotNull
    public static <T> List<T> toList(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return CollectionsKt__CollectionsKt.optimizeReadOnlyList(toMutableList(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return EmptyList.INSTANCE;
        }
        if (size != 1) {
            return toMutableList(collection);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
    }

    @NotNull
    public static final long[] toLongArray(@NotNull Collection<Long> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        long[] jArr = new long[collection.size()];
        Iterator<Long> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    @NotNull
    public static final <T> List<T> toMutableList(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return toMutableList((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        toCollection(iterable, arrayList);
        return arrayList;
    }

    @NotNull
    public static final <T> Set<T> toMutableSet(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        toCollection(iterable, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static <T> Set<T> toSet(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            toCollection(iterable, linkedHashSet);
            return SetsKt__SetsKt.optimizeReadOnlySet(linkedHashSet);
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return EmptySet.INSTANCE;
        }
        if (size == 1) {
            return SetsKt__SetsJVMKt.setOf(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(collection.size()));
        toCollection(iterable, linkedHashSet2);
        return linkedHashSet2;
    }

    @NotNull
    public static final short[] toShortArray(@NotNull Collection<Short> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        short[] sArr = new short[collection.size()];
        Iterator<Short> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            sArr[i] = it.next().shortValue();
            i++;
        }
        return sArr;
    }

    @NotNull
    public static final <T> Set<T> union(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(iterable);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<List<T>> windowed(@NotNull Iterable<? extends T> iterable, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            ArrayList arrayList = new ArrayList();
            Iterator itWindowedIterator = SlidingWindowKt.windowedIterator(iterable.iterator(), i, i2, z, false);
            while (itWindowedIterator.hasNext()) {
                arrayList.add((List) itWindowedIterator.next());
            }
            return arrayList;
        }
        List list = (List) iterable;
        int size = list.size();
        ArrayList arrayList2 = new ArrayList((size / i2) + (size % i2 == 0 ? 0 : 1));
        int i3 = 0;
        while (i3 >= 0 && i3 < size) {
            int i4 = size - i3;
            if (i <= i4) {
                i4 = i;
            }
            if (i4 < i && !z) {
                return arrayList2;
            }
            ArrayList arrayList3 = new ArrayList(i4);
            for (int i5 = 0; i5 < i4; i5++) {
                arrayList3.add(list.get(i5 + i3));
            }
            arrayList2.add(arrayList3);
            i3 += i2;
        }
        return arrayList2;
    }

    public static /* synthetic */ List windowed$default(Iterable iterable, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(iterable, i, i2, z);
    }

    @NotNull
    public static final <T> Iterable<IndexedValue<T>> withIndex(@NotNull final Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.CollectionsKt___CollectionsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return iterable.iterator();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull Iterable<? extends T> iterable, @NotNull R[] other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = other.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10), length));
        int i = 0;
        for (T t : iterable) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(t, other[i]));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> List<R> zipWithNext(@NotNull Iterable<? extends T> iterable, @NotNull Function2<? super T, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        R.color next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            arrayList.add(transform.invoke(next, next2));
            next = next2;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> List<R> chunked(@NotNull Iterable<? extends T> iterable, int i, @NotNull Function1<? super List<? extends T>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return windowed(iterable, i, i, true, transform);
    }

    @InlineOnly
    public static final <T> List<T> plusElement(Collection<? extends T> collection, T t) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return plus((Collection) collection, (Object) t);
    }

    @SinceKotlin(version = "1.3")
    public static final <T> T random(@NotNull Collection<? extends T> collection, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        return (T) elementAt(collection, random.nextInt(collection.size()));
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T randomOrNull(@NotNull Collection<? extends T> collection, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (collection.isEmpty()) {
            return null;
        }
        return (T) elementAt(collection, random.nextInt(collection.size()));
    }

    public static /* synthetic */ List windowed$default(Iterable iterable, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(iterable, i, i2, z, function1);
    }

    public static final <T> boolean any(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (predicate.invoke(it.next()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull Iterable<? extends T> iterable, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : iterable) {
            destination.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return destination;
    }

    @InlineOnly
    public static final <T> int count(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return collection.size();
    }

    public static final <T> boolean none(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (predicate.invoke(it.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> List<T> requireNoNulls(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new IllegalArgumentException("null element found in " + list + '.');
            }
        }
        return list;
    }

    public static final <T> int count(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return 0;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (predicate.invoke(it.next()).booleanValue() && (i = i + 1) < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i;
    }

    @InlineOnly
    public static final <T> T elementAt(List<? extends T> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.get(i);
    }

    @NotNull
    public static <T> List<T> toMutableList(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return new ArrayList(collection);
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associateBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (T t : iterable) {
            linkedHashMap.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    public static final <T> int indexOf(@NotNull List<? extends T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.indexOf(t);
    }

    public static final <T> int lastIndexOf(@NotNull List<? extends T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.lastIndexOf(t);
    }

    @NotNull
    public static final <T> List<T> minus(@NotNull Iterable<? extends T> iterable, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.length == 0) {
            return toList(iterable);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!ArraysKt___ArraysKt.contains(elements, t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Collection<? extends T> collection, T t) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(t);
        return arrayList;
    }

    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends R> other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = iterable.iterator();
        Iterator<? extends R> it2 = other.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(transform.invoke(it.next(), it2.next()));
        }
        return arrayList;
    }

    @InlineOnly
    public static final <T> T elementAtOrNull(List<? extends T> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return (T) getOrNull(list, i);
    }

    @Nullable
    public static <T> T firstOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return (T) list.get(0);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull List<? extends T> list, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(list.get(it.next().intValue()));
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    @InlineOnly
    public static final <T> T findLast(List<? extends T> list, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            T tPrevious = listIterator.previous();
            if (predicate.invoke(tPrevious).booleanValue()) {
                return tPrevious;
            }
        }
        return null;
    }

    public static final <T> T first(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) first((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return (T) list.get(list.size() - 1);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.size() == 1) {
                return (T) list.get(0);
            }
            return null;
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    @JvmName(name = "sumOfDouble")
    public static final double sumOfDouble(@NotNull Iterable<Double> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Double> it = iterable.iterator();
        double dDoubleValue = 0.0d;
        while (it.hasNext()) {
            dDoubleValue += it.next().doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfInt")
    public static final int sumOfInt(@NotNull Iterable<Integer> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Integer> it = iterable.iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            iIntValue += it.next().intValue();
        }
        return iIntValue;
    }

    @JvmName(name = "sumOfLong")
    public static final long sumOfLong(@NotNull Iterable<Long> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Long> it = iterable.iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            jLongValue += it.next().longValue();
        }
        return jLongValue;
    }

    @InlineOnly
    public static final <T> T elementAtOrElse(List<? extends T> list, int i, Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= list.size()) ? defaultValue.invoke(Integer.valueOf(i)) : list.get(i);
    }

    public static final <T> int indexOfFirst(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Iterator<? extends T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (predicate.invoke(it.next()).booleanValue()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfLast(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (predicate.invoke(listIterator.previous()).booleanValue()) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public static final <T> T last(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) last((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                next = it.next();
            }
            return next;
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: maxOrNull, reason: collision with other method in class */
    public static final Float m1020maxOrNull(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = it.next().floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.max(fFloatValue, it.next().floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    /* JADX INFO: renamed from: maxOrThrow, reason: collision with other method in class */
    public static final float m1021maxOrThrow(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        if (it.hasNext()) {
            float fFloatValue = it.next().floatValue();
            while (it.hasNext()) {
                fFloatValue = Math.max(fFloatValue, it.next().floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: minOrNull, reason: collision with other method in class */
    public static final Float m1028minOrNull(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = it.next().floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.min(fFloatValue, it.next().floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    /* JADX INFO: renamed from: minOrThrow, reason: collision with other method in class */
    public static final float m1029minOrThrow(@NotNull Iterable<Float> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<Float> it = iterable.iterator();
        if (it.hasNext()) {
            float fFloatValue = it.next().floatValue();
            while (it.hasNext()) {
                fFloatValue = Math.min(fFloatValue, it.next().floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Iterable<? extends T> iterable, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (iterable instanceof Collection) {
            return plus((Collection) iterable, (Object[]) elements);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, iterable);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }

    public static <T> T single(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) single((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T next = it.next();
            if (it.hasNext()) {
                throw new IllegalArgumentException("Collection has more than one element.");
            }
            return next;
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<Pair<T, T>> zipWithNext(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            arrayList.add(new Pair(next, next2));
            next = next2;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull Iterable<? extends T> iterable, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : iterable) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Double m1017maxOfOrNull(Iterable<? extends T> iterable, Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Double m1025minOfOrNull(Iterable<? extends T> iterable, Function1<? super T, Double> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @NotNull
    public static final <T> List<T> minus(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection collectionConvertToListIfNotCollection = CollectionsKt__MutableCollectionsKt.convertToListIfNotCollection(elements);
        if (collectionConvertToListIfNotCollection.isEmpty()) {
            return toList(iterable);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!collectionConvertToListIfNotCollection.contains(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<Pair<T, R>> zip(@NotNull Iterable<? extends T> iterable, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = other.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10), length));
        int i = 0;
        for (T t : iterable) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(t, other[i]));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K, V> Map<K, List<V>> groupBy(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : iterable) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T> float m1015maxOf(Iterable<? extends T> iterable, Function1<? super T, Float> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (itM.hasNext()) {
            float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
            while (itM.hasNext()) {
                fFloatValue = Math.max(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T> float m1023minOf(Iterable<? extends T> iterable, Function1<? super T, Float> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (itM.hasNext()) {
            float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
            while (itM.hasNext()) {
                fFloatValue = Math.min(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> List<R> windowed(@NotNull Iterable<? extends T> iterable, int i, int i2, boolean z, @NotNull Function1<? super List<? extends T>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        if ((iterable instanceof RandomAccess) && (iterable instanceof List)) {
            List list = (List) iterable;
            int size = list.size();
            int i3 = 0;
            ArrayList arrayList = new ArrayList((size / i2) + (size % i2 == 0 ? 0 : 1));
            MovingSubList movingSubList = new MovingSubList(list);
            while (i3 >= 0 && i3 < size) {
                int i4 = size - i3;
                if (i <= i4) {
                    i4 = i;
                }
                if (!z && i4 < i) {
                    return arrayList;
                }
                movingSubList.move(i3, i4 + i3);
                arrayList.add(transform.invoke(movingSubList));
                i3 += i2;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator itWindowedIterator = SlidingWindowKt.windowedIterator(iterable.iterator(), i, i2, z, true);
        while (itWindowedIterator.hasNext()) {
            arrayList2.add(transform.invoke((List) itWindowedIterator.next()));
        }
        return arrayList2;
    }

    public static final <T> T first(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    @Nullable
    public static <T> T firstOrNull(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Collection<? extends T> collection, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList(collection.size() + elements.length);
        arrayList.addAll(collection);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }

    @Nullable
    public static <T> T singleOrNull(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Nullable
    public static <T> T lastOrNull(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    @Nullable
    public static final <T> T lastOrNull(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            T tPrevious = listIterator.previous();
            if (predicate.invoke(tPrevious).booleanValue()) {
                return tPrevious;
            }
        }
        return null;
    }

    public static <T> T last(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(CollectionsKt__CollectionsKt.getLastIndex(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T maxOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (next.compareTo(next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    @NotNull
    /* JADX INFO: renamed from: maxOrThrow, reason: collision with other method in class */
    public static final <T extends Comparable<? super T>> T m1022maxOrThrow(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                T next2 = it.next();
                if (next.compareTo(next2) < 0) {
                    next = next2;
                }
            }
            return next;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static <T extends Comparable<? super T>> T minOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (next.compareTo(next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    @NotNull
    /* JADX INFO: renamed from: minOrThrow, reason: collision with other method in class */
    public static final <T extends Comparable<? super T>> T m1030minOrThrow(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                T next2 = it.next();
                if (next.compareTo(next2) > 0) {
                    next = next2;
                }
            }
            return next;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (iterable instanceof Collection) {
            return plus((Collection) iterable, (Iterable) elements);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, iterable);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }

    public static final <T> T single(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Iterator<? extends T> it = iterable.iterator();
        Iterator<? extends R> it2 = other.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(new Pair(it.next(), it2.next()));
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> minus(@NotNull Iterable<? extends T> iterable, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        List list = SequencesKt___SequencesKt.toList(elements);
        if (list.isEmpty()) {
            return toList(iterable);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!list.contains(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    public static final <T> T last(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ListIterator<? extends T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            T tPrevious = listIterator.previous();
            if (predicate.invoke(tPrevious).booleanValue()) {
                return tPrevious;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Collection<? extends T> collection, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            Collection collection2 = (Collection) elements;
            ArrayList arrayList = new ArrayList(collection2.size() + collection.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList2, elements);
        return arrayList2;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Float m1018maxOfOrNull(Iterable<? extends T> iterable, Function1<? super T, Float> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
        while (itM.hasNext()) {
            fFloatValue = Math.max(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Float m1026minOfOrNull(Iterable<? extends T> iterable, Function1<? super T, Float> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
        while (itM.hasNext()) {
            fFloatValue = Math.min(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m1016maxOf(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            while (itM.hasNext()) {
                R rInvoke2 = function1.invoke((Object) itM.next());
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m1024minOf(Iterable<? extends T> iterable, Function1<? super T, ? extends R> function1) {
        Iterator itM = CollectionsKt___CollectionsJvmKt$$ExternalSyntheticOutline0.m(iterable, "<this>", function1, "selector");
        if (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            while (itM.hasNext()) {
                R rInvoke2 = function1.invoke((Object) itM.next());
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Iterable<? extends T> iterable, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, iterable);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> plus(@NotNull Collection<? extends T> collection, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList(collection.size() + 10);
        arrayList.addAll(collection);
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }
}
