package kotlin.jvm.optionals;

import j$.util.Optional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class OptionalsKt {
    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T> Sequence<T> asSequence(@NotNull Optional<? extends T> optional) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        return optional.isPresent() ? ArraysKt___ArraysKt.asSequence(new Object[]{optional.get()}) : EmptySequence.INSTANCE;
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final <T> T getOrDefault(@NotNull Optional<? extends T> optional, T t) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        return optional.isPresent() ? optional.get() : t;
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final <T> T getOrElse(@NotNull Optional<? extends T> optional, @NotNull Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return optional.isPresent() ? optional.get() : defaultValue.invoke();
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <T> T getOrNull(@NotNull Optional<T> optional) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        return optional.orElse(null);
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T, C extends Collection<? super T>> C toCollection(@NotNull Optional<T> optional, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (optional.isPresent()) {
            T t = optional.get();
            Intrinsics.checkNotNullExpressionValue(t, "get(...)");
            destination.add(t);
        }
        return destination;
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T> List<T> toList(@NotNull Optional<? extends T> optional) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        return optional.isPresent() ? CollectionsKt__CollectionsJVMKt.listOf(optional.get()) : EmptyList.INSTANCE;
    }

    @SinceKotlin(version = "1.8")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T> Set<T> toSet(@NotNull Optional<? extends T> optional) {
        Intrinsics.checkNotNullParameter(optional, "<this>");
        return optional.isPresent() ? SetsKt__SetsJVMKt.setOf(optional.get()) : EmptySet.INSTANCE;
    }
}
