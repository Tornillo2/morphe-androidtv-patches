package kotlin;

import kotlin.Result;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Result.kt\nkotlin/ResultKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,340:1\n1#2:341\n*E\n"})
public final class ResultKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Object createFailure(@NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new Result.Failure(exception);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T> R fold(Object obj, Function1<? super T, ? extends R> onSuccess, Function1<? super Throwable, ? extends R> onFailure) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        return thM583exceptionOrNullimpl == null ? onSuccess.invoke(obj) : onFailure.invoke(thM583exceptionOrNullimpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T extends R> R getOrDefault(Object obj, R r) {
        return obj instanceof Result.Failure ? r : obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T extends R> R getOrElse(Object obj, Function1<? super Throwable, ? extends R> onFailure) {
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        return thM583exceptionOrNullimpl == null ? obj : onFailure.invoke(thM583exceptionOrNullimpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> T getOrThrow(Object obj) throws Throwable {
        throwOnFailure(obj);
        return obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T> Object map(Object obj, Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        return !(obj instanceof Result.Failure) ? transform.invoke(obj) : obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T> Object mapCatching(Object obj, Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (obj instanceof Result.Failure) {
            return obj;
        }
        try {
            return transform.invoke(obj);
        } catch (Throwable th) {
            return createFailure(th);
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> Object onFailure(Object obj, Function1<? super Throwable, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        if (thM583exceptionOrNullimpl != null) {
            action.invoke(thM583exceptionOrNullimpl);
        }
        return obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> Object onSuccess(Object obj, Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (!(obj instanceof Result.Failure)) {
            action.invoke(obj);
        }
        return obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T extends R> Object recover(Object obj, Function1<? super Throwable, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        return thM583exceptionOrNullimpl == null ? obj : transform.invoke(thM583exceptionOrNullimpl);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R, T extends R> Object recoverCatching(Object obj, Function1<? super Throwable, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        if (thM583exceptionOrNullimpl == null) {
            return obj;
        }
        try {
            return transform.invoke(thM583exceptionOrNullimpl);
        } catch (Throwable th) {
            return createFailure(th);
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <R> Object runCatching(Function0<? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke();
        } catch (Throwable th) {
            return createFailure(th);
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final void throwOnFailure(@NotNull Object obj) throws Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T, R> Object runCatching(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke(t);
        } catch (Throwable th) {
            return createFailure(th);
        }
    }
}
