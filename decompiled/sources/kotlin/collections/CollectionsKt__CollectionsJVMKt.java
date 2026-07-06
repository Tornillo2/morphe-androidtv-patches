package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.builders.ListBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nCollectionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionsJVM.kt\nkotlin/collections/CollectionsKt__CollectionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,124:1\n1#2:125\n*E\n"})
public class CollectionsKt__CollectionsJVMKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static <E> List<E> build(@NotNull List<E> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return ((ListBuilder) builder).build();
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final <E> List<E> buildListInternal(Function1<? super List<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        List listCreateListBuilder = createListBuilder();
        builderAction.invoke(listCreateListBuilder);
        return build(listCreateListBuilder);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final int checkCountOverflow(int i) {
        if (i >= 0) {
            return i;
        }
        CollectionsKt__CollectionsKt.throwCountOverflow();
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final int checkIndexOverflow(int i) {
        if (i >= 0) {
            return i;
        }
        CollectionsKt__CollectionsKt.throwIndexOverflow();
        throw null;
    }

    @InlineOnly
    public static final Object[] collectionToArray(Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        return CollectionToArray.toArray(collection);
    }

    @NotNull
    public static final <T> Object[] copyToArrayOfAny(@NotNull T[] tArr, boolean z) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (z && tArr.getClass().equals(Object[].class)) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static <E> List<E> createListBuilder() {
        return new ListBuilder(0, 1, null);
    }

    @NotNull
    public static <T> List<T> listOf(T t) {
        List<T> listSingletonList = Collections.singletonList(t);
        Intrinsics.checkNotNullExpressionValue(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        List<T> mutableList = CollectionsKt___CollectionsKt.toMutableList(iterable);
        Collections.shuffle(mutableList);
        return mutableList;
    }

    @NotNull
    public static <T> T[] terminateCollectionToArray(int i, @NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (i < array.length) {
            array[i] = null;
        }
        return array;
    }

    @InlineOnly
    public static final <T> List<T> toList(Enumeration<T> enumeration) {
        Intrinsics.checkNotNullParameter(enumeration, "<this>");
        ArrayList list = Collections.list(enumeration);
        Intrinsics.checkNotNullExpressionValue(list, "list(...)");
        return list;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final <E> List<E> buildListInternal(int i, Function1<? super List<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        ListBuilder listBuilder = new ListBuilder(i);
        builderAction.invoke(listBuilder);
        return listBuilder.build();
    }

    @InlineOnly
    public static final <T> T[] collectionToArray(Collection<?> collection, T[] array) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(collection, array);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static <E> List<E> createListBuilder(int i) {
        return new ListBuilder(i);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        List<T> mutableList = CollectionsKt___CollectionsKt.toMutableList(iterable);
        Collections.shuffle(mutableList, random);
        return mutableList;
    }
}
