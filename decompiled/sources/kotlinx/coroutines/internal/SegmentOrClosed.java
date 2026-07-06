package kotlinx.coroutines.internal;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JvmInline
public final class SegmentOrClosed<S extends Segment<S>> {

    @Nullable
    public final Object value;

    public /* synthetic */ SegmentOrClosed(Object obj) {
        this.value = obj;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SegmentOrClosed m2132boximpl(Object obj) {
        return new SegmentOrClosed(obj);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2134equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof SegmentOrClosed) && Intrinsics.areEqual(obj, ((SegmentOrClosed) obj2).value);
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2135equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @NotNull
    /* JADX INFO: renamed from: getSegment-impl, reason: not valid java name */
    public static final S m2136getSegmentimpl(Object obj) {
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            throw new IllegalStateException("Does not contain segment");
        }
        if (obj != null) {
            return (S) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2137hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m2138isClosedimpl(Object obj) {
        return obj == ConcurrentLinkedListKt.CLOSED;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2139toStringimpl(Object obj) {
        return "SegmentOrClosed(value=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m2134equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2137hashCodeimpl(this.value);
    }

    public String toString() {
        return m2139toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2140unboximpl() {
        return this.value;
    }

    public static /* synthetic */ void getSegment$annotations() {
    }

    @NotNull
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <S extends Segment<S>> Object m2133constructorimpl(@Nullable Object obj) {
        return obj;
    }
}
