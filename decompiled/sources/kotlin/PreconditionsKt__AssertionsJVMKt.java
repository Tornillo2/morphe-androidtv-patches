package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nAssertionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AssertionsJVM.kt\nkotlin/PreconditionsKt__AssertionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,39:1\n1#2:40\n*E\n"})
public class PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m577assert(boolean z) {
    }

    @InlineOnly
    /* JADX INFO: renamed from: assert, reason: not valid java name */
    public static final void m578assert(boolean z, Function0<? extends Object> lazyMessage) {
        Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
    }
}
