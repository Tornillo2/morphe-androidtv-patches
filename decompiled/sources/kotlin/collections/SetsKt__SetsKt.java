package kotlin.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.builders.SetBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final <E> Set<E> buildSet(@BuilderInference Function1<? super Set<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        SetBuilder setBuilder = new SetBuilder();
        builderAction.invoke(setBuilder);
        return setBuilder.build();
    }

    @NotNull
    public static <T> Set<T> emptySet() {
        return EmptySet.INSTANCE;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <T> HashSet<T> hashSetOf() {
        return new HashSet<>();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <T> LinkedHashSet<T> linkedSetOf() {
        return new LinkedHashSet<>();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final <T> Set<T> mutableSetOf() {
        return new LinkedHashSet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        int size = set.size();
        return size != 0 ? size != 1 ? set : SetsKt__SetsJVMKt.setOf(set.iterator().next()) : EmptySet.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <T> Set<T> orEmpty(Set<? extends T> set) {
        return set == 0 ? EmptySet.INSTANCE : set;
    }

    @InlineOnly
    public static final <T> Set<T> setOf() {
        return EmptySet.INSTANCE;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T> Set<T> setOfNotNull(@Nullable T t) {
        return t != null ? SetsKt__SetsJVMKt.setOf(t) : EmptySet.INSTANCE;
    }

    @NotNull
    public static final <T> HashSet<T> hashSetOf(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        HashSet<T> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(elements.length));
        ArraysKt___ArraysKt.toCollection(elements, hashSet);
        return hashSet;
    }

    @NotNull
    public static final <T> LinkedHashSet<T> linkedSetOf(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet<T> linkedHashSet = new LinkedHashSet<>(MapsKt__MapsJVMKt.mapCapacity(elements.length));
        ArraysKt___ArraysKt.toCollection(elements, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> mutableSetOf(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(elements.length));
        ArraysKt___ArraysKt.toCollection(elements, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static <T> Set<T> setOf(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt___ArraysKt.toSet(elements);
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T> Set<T> setOfNotNull(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArraysKt___ArraysKt.filterNotNullTo(elements, linkedHashSet);
        return linkedHashSet;
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final <E> Set<E> buildSet(int i, @BuilderInference Function1<? super Set<E>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        SetBuilder setBuilder = new SetBuilder(i);
        builderAction.invoke(setBuilder);
        return setBuilder.build();
    }
}
