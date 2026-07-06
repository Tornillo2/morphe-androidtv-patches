package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nPreconditions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Preconditions.kt\nkotlin/PreconditionsKt__PreconditionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,146:1\n1#2:147\n*E\n"})
public class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    public static final void check(boolean z) {
        if (!z) {
            throw new IllegalStateException("Check failed.");
        }
    }

    @InlineOnly
    public static final <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @InlineOnly
    public static final Void error(Object message) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new IllegalStateException(message.toString());
    }

    @InlineOnly
    public static final void require(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    @InlineOnly
    public static final <T> T requireNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    @InlineOnly
    public static final void check(boolean z, Function0<? extends Object> lazyMessage) {
        Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
        if (!z) {
            throw new IllegalStateException(lazyMessage.invoke().toString());
        }
    }

    @InlineOnly
    public static final <T> T checkNotNull(T t, Function0<? extends Object> lazyMessage) {
        Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(lazyMessage.invoke().toString());
    }

    @InlineOnly
    public static final void require(boolean z, Function0<? extends Object> lazyMessage) {
        Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
        if (!z) {
            throw new IllegalArgumentException(lazyMessage.invoke().toString());
        }
    }

    @InlineOnly
    public static final <T> T requireNotNull(T t, Function0<? extends Object> lazyMessage) {
        Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(lazyMessage.invoke().toString());
    }
}
