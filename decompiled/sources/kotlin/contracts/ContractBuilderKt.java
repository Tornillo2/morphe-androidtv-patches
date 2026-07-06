package kotlin.contracts;

import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.ContractsDsl;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ContractBuilderKt {
    @SinceKotlin(version = "1.3")
    @ContractsDsl
    @InlineOnly
    @ExperimentalContracts
    public static final void contract(Function1<? super ContractBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
    }
}
