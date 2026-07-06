package kotlin.collections;

import android.R;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nGrouping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n1#1,291:1\n80#1,6:292\n53#1:298\n80#1,6:299\n80#1,6:305\n53#1:311\n80#1,6:312\n80#1,6:318\n53#1:324\n80#1,6:325\n80#1,6:331\n189#1:337\n80#1,6:338\n*S KotlinDebug\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n53#1:292,6\n112#1:298\n112#1:299,6\n143#1:305,6\n164#1:311\n164#1:312,6\n189#1:318,6\n211#1:324\n211#1:325,6\n239#1:331,6\n257#1:337\n257#1:338,6\n*E\n"})
public class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> aggregate(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(next);
            R.bool boolVar = (Object) linkedHashMap.get(objKeyOf);
            linkedHashMap.put(objKeyOf, operation.invoke(objKeyOf, boolVar, next, Boolean.valueOf(boolVar == null && !linkedHashMap.containsKey(objKeyOf))));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M destination, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(next);
            R.bool boolVar = (Object) destination.get(objKeyOf);
            destination.put(objKeyOf, operation.invoke(objKeyOf, boolVar, next, Boolean.valueOf(boolVar == null && !destination.containsKey(objKeyOf))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M destination) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            K kKeyOf = grouping.keyOf(itSourceIterator.next());
            Object obj = destination.get(kKeyOf);
            if (obj == null && !destination.containsKey(kKeyOf)) {
                obj = 0;
            }
            destination.put(kKeyOf, Integer.valueOf(((Number) obj).intValue() + 1));
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function2<? super K, ? super T, ? extends R> initialValueSelector, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(initialValueSelector, "initialValueSelector");
        Intrinsics.checkNotNullParameter(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(next);
            R rInvoke = (Object) linkedHashMap.get(objKeyOf);
            if (rInvoke == null && !linkedHashMap.containsKey(objKeyOf)) {
                rInvoke = initialValueSelector.invoke(objKeyOf, next);
            }
            linkedHashMap.put(objKeyOf, operation.invoke(objKeyOf, rInvoke, next));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M destination, @NotNull Function2<? super K, ? super T, ? extends R> initialValueSelector, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(initialValueSelector, "initialValueSelector");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(next);
            R rInvoke = (Object) destination.get(objKeyOf);
            if (rInvoke == null && !destination.containsKey(objKeyOf)) {
                rInvoke = initialValueSelector.invoke(objKeyOf, next);
            }
            destination.put(objKeyOf, operation.invoke(objKeyOf, rInvoke, next));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S, T extends S, K> Map<K, S> reduce(@NotNull Grouping<T, ? extends K> grouping, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            S sInvoke = (Object) itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(sInvoke);
            R.bool boolVar = (Object) linkedHashMap.get(objKeyOf);
            if (boolVar != null || linkedHashMap.containsKey(objKeyOf)) {
                sInvoke = operation.invoke(objKeyOf, boolVar, sInvoke);
            }
            linkedHashMap.put(objKeyOf, sInvoke);
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M destination, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            S sInvoke = (Object) itSourceIterator.next();
            Object objKeyOf = grouping.keyOf(sInvoke);
            R.bool boolVar = (Object) destination.get(objKeyOf);
            if (boolVar != null || destination.containsKey(objKeyOf)) {
                sInvoke = operation.invoke(objKeyOf, boolVar, sInvoke);
            }
            destination.put(objKeyOf, sInvoke);
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> grouping, @NotNull M destination, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            K kKeyOf = grouping.keyOf(next);
            R.color colorVar = (Object) destination.get(kKeyOf);
            if (colorVar == null && !destination.containsKey(kKeyOf)) {
                colorVar = (Object) r;
            }
            destination.put(kKeyOf, operation.invoke(colorVar, next));
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> grouping, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(grouping, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> itSourceIterator = grouping.sourceIterator();
        while (itSourceIterator.hasNext()) {
            ?? next = itSourceIterator.next();
            K kKeyOf = grouping.keyOf(next);
            R.color colorVar = (Object) linkedHashMap.get(kKeyOf);
            if (colorVar == null && !linkedHashMap.containsKey(kKeyOf)) {
                colorVar = (Object) r;
            }
            linkedHashMap.put(kKeyOf, operation.invoke(colorVar, next));
        }
        return linkedHashMap;
    }
}
