package kotlin;

import java.io.Serializable;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@JvmInline
public final class Result<T> implements Serializable {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final Object value;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @InlineOnly
        @JvmName(name = "failure")
        public final <T> Object failure(Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            return ResultKt.createFailure(exception);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @InlineOnly
        @JvmName(name = "success")
        public final <T> Object success(T t) {
            return t;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Failure implements Serializable {

        @JvmField
        @NotNull
        public final Throwable exception;

        public Failure(@NotNull Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            this.exception = exception;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Failure) && Intrinsics.areEqual(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    @PublishedApi
    public /* synthetic */ Result(Object obj) {
        this.value = obj;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Result m579boximpl(Object obj) {
        return new Result(obj);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m581equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof Result) && Intrinsics.areEqual(obj, ((Result) obj2).value);
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m582equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @Nullable
    /* JADX INFO: renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m583exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    /* JADX INFO: renamed from: getOrNull-impl, reason: not valid java name */
    public static final T m584getOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return null;
        }
        return obj;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m585hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m586isFailureimpl(Object obj) {
        return obj instanceof Failure;
    }

    /* JADX INFO: renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m587isSuccessimpl(Object obj) {
        return !(obj instanceof Failure);
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m588toStringimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).toString();
        }
        return "Success(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m581equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m585hashCodeimpl(this.value);
    }

    @NotNull
    public String toString() {
        return m588toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m589unboximpl() {
        return this.value;
    }

    @PublishedApi
    public static /* synthetic */ void getValue$annotations() {
    }

    @PublishedApi
    @NotNull
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m580constructorimpl(@Nullable Object obj) {
        return obj;
    }
}
