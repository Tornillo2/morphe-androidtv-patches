package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt__ZipKt$combine$6$1<T> extends Lambda implements Function0<T[]> {
    public final /* synthetic */ Flow<T>[] $flowArray;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$6$1(Flow<T>[] flowArr) {
        super(0);
        this.$flowArray = flowArr;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        invoke();
        throw null;
    }

    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final T[] invoke() {
        int length = this.$flowArray.length;
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
