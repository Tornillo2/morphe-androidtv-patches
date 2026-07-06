package kotlinx.coroutines.internal;

import kotlinx.coroutines.DebugStringsKt;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class OpDescriptor {
    @Nullable
    public abstract AtomicOp<?> getAtomicOp();

    public final boolean isEarlierThan(@NotNull OpDescriptor opDescriptor) {
        AtomicOp<?> atomicOp;
        AtomicOp<?> atomicOp2 = getAtomicOp();
        return (atomicOp2 == null || (atomicOp = opDescriptor.getAtomicOp()) == null || atomicOp2.getOpSequence() >= atomicOp.getOpSequence()) ? false : true;
    }

    @Nullable
    public abstract Object perform(@Nullable Object obj);

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + ObjectUtils.AT_SIGN + DebugStringsKt.getHexAddress(this);
    }
}
