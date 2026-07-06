package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AtomicDesc {
    public AtomicOp<?> atomicOp;

    public abstract void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj);

    @NotNull
    public final AtomicOp<?> getAtomicOp() {
        AtomicOp<?> atomicOp = this.atomicOp;
        if (atomicOp != null) {
            return atomicOp;
        }
        Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
        throw null;
    }

    @Nullable
    public abstract Object prepare(@NotNull AtomicOp<?> atomicOp);

    public final void setAtomicOp(@NotNull AtomicOp<?> atomicOp) {
        this.atomicOp = atomicOp;
    }
}
