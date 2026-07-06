package kotlin.contracts;

import kotlin.Function;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@ContractsDsl
@ExperimentalContracts
public interface ContractBuilder {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static /* synthetic */ CallsInPlace callsInPlace$default(ContractBuilder contractBuilder, Function function, InvocationKind invocationKind, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: callsInPlace");
            }
            if ((i & 2) != 0) {
                invocationKind = InvocationKind.UNKNOWN;
            }
            return contractBuilder.callsInPlace(function, invocationKind);
        }
    }

    @ContractsDsl
    @NotNull
    <R> CallsInPlace callsInPlace(@NotNull Function<? extends R> function, @NotNull InvocationKind invocationKind);

    @ContractsDsl
    @NotNull
    Returns returns();

    @ContractsDsl
    @NotNull
    Returns returns(@Nullable Object obj);

    @ContractsDsl
    @NotNull
    ReturnsNotNull returnsNotNull();
}
