package kotlinx.coroutines.channels;

import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmInline
public final class ChannelResult<T> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Failed failed = new Failed();

    @Nullable
    public final Object holder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Closed extends Failed {

        @JvmField
        @Nullable
        public final Throwable cause;

        public Closed(@Nullable Throwable th) {
            this.cause = th;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        @NotNull
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @InternalCoroutinesApi
    public static final class Companion {
        public Companion() {
        }

        @InternalCoroutinesApi
        @NotNull
        /* JADX INFO: renamed from: closed-JP2dKIU, reason: not valid java name */
        public final <E> Object m2102closedJP2dKIU(@Nullable Throwable th) {
            return new Closed(th);
        }

        @InternalCoroutinesApi
        @NotNull
        /* JADX INFO: renamed from: failure-PtdJZtk, reason: not valid java name */
        public final <E> Object m2103failurePtdJZtk() {
            return ChannelResult.failed;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @InternalCoroutinesApi
        @NotNull
        /* JADX INFO: renamed from: success-JP2dKIU, reason: not valid java name */
        public final <E> Object m2104successJP2dKIU(E e) {
            return e;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Failed {
        @NotNull
        public String toString() {
            return "Failed";
        }
    }

    @PublishedApi
    public /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ChannelResult m2089boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2091equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).holder);
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2092equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @Nullable
    /* JADX INFO: renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m2093exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.cause;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    /* JADX INFO: renamed from: getOrNull-impl, reason: not valid java name */
    public static final T m2094getOrNullimpl(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getOrThrow-impl, reason: not valid java name */
    public static final T m2095getOrThrowimpl(Object obj) throws Throwable {
        Throwable th;
        if (!(obj instanceof Failed)) {
            return obj;
        }
        if (!(obj instanceof Closed) || (th = ((Closed) obj).cause) == null) {
            throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Trying to call 'getOrThrow' on a failed channel result: ", obj));
        }
        throw th;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2096hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m2097isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* JADX INFO: renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m2098isFailureimpl(Object obj) {
        return obj instanceof Failed;
    }

    /* JADX INFO: renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m2099isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2100toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m2091equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m2096hashCodeimpl(this.holder);
    }

    @NotNull
    public String toString() {
        return m2100toStringimpl(this.holder);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2101unboximpl() {
        return this.holder;
    }

    @PublishedApi
    public static /* synthetic */ void getHolder$annotations() {
    }

    @PublishedApi
    @NotNull
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m2090constructorimpl(@Nullable Object obj) {
        return obj;
    }
}
