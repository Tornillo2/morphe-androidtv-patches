package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.builders.SetBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SetsKt__SetsJVMKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static <E> Set<E> build(@NotNull Set<E> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return ((SetBuilder) builder).build();
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final <E> Set<E> buildSetInternal(Function1<? super Set<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        SetBuilder setBuilder = new SetBuilder();
        builderAction.invoke(setBuilder);
        return setBuilder.build();
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final <E> Set<E> createSetBuilder() {
        return new SetBuilder();
    }

    @NotNull
    public static <T> Set<T> setOf(T t) {
        Set<T> setSingleton = Collections.singleton(t);
        Intrinsics.checkNotNullExpressionValue(setSingleton, "singleton(...)");
        return setSingleton;
    }

    @NotNull
    public static final <T> TreeSet<T> sortedSetOf(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        TreeSet<T> treeSet = new TreeSet<>();
        ArraysKt___ArraysKt.toCollection(elements, treeSet);
        return treeSet;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static <E> Set<E> createSetBuilder(int i) {
        return new SetBuilder(i);
    }

    @NotNull
    public static final <T> TreeSet<T> sortedSetOf(@NotNull Comparator<? super T> comparator, @NotNull T... elements) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(elements, "elements");
        TreeSet<T> treeSet = new TreeSet<>(comparator);
        ArraysKt___ArraysKt.toCollection(elements, treeSet);
        return treeSet;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    public static final <E> Set<E> buildSetInternal(int i, Function1<? super Set<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        SetBuilder setBuilder = new SetBuilder(i);
        builderAction.invoke(setBuilder);
        return setBuilder.build();
    }
}
