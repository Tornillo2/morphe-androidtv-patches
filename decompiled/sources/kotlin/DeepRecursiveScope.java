package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.7")
@RestrictsSuspension
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
public abstract class DeepRecursiveScope<T, R> {
    public DeepRecursiveScope() {
    }

    @Nullable
    public abstract Object callRecursive(T t, @NotNull Continuation<? super R> continuation);

    @Nullable
    public abstract <U, S> Object callRecursive(@NotNull DeepRecursiveFunction<U, S> deepRecursiveFunction, U u, @NotNull Continuation<? super S> continuation);

    @Deprecated(level = DeprecationLevel.ERROR, message = "'invoke' should not be called from DeepRecursiveScope. Use 'callRecursive' to do recursion in the heap instead of the call stack.", replaceWith = @ReplaceWith(expression = "this.callRecursive(value)", imports = {}))
    @NotNull
    public final Void invoke(@NotNull DeepRecursiveFunction<?, ?> deepRecursiveFunction, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(deepRecursiveFunction, "<this>");
        throw new UnsupportedOperationException("Should not be called from DeepRecursiveScope");
    }

    public DeepRecursiveScope(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
