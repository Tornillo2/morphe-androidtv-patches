package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__DistinctKt$defaultAreEquivalent$1 extends Lambda implements Function2<Object, Object, Boolean> {
    public static final FlowKt__DistinctKt$defaultAreEquivalent$1 INSTANCE = new FlowKt__DistinctKt$defaultAreEquivalent$1(2);

    public FlowKt__DistinctKt$defaultAreEquivalent$1() {
        super(2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function2
    @NotNull
    public final Boolean invoke(@Nullable Object obj, @Nullable Object obj2) {
        return Boolean.valueOf(Intrinsics.areEqual(obj, obj2));
    }
}
