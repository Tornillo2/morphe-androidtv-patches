package kotlinx.coroutines.debug.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DebugProbesImpl$dumpCoroutinesSynchronized$1$2 extends Lambda implements Function1<DebugProbesImpl.CoroutineOwner<?>, Boolean> {
    public static final DebugProbesImpl$dumpCoroutinesSynchronized$1$2 INSTANCE = new DebugProbesImpl$dumpCoroutinesSynchronized$1$2(1);

    public DebugProbesImpl$dumpCoroutinesSynchronized$1$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Boolean invoke(@NotNull DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
        return Boolean.valueOf(!DebugProbesImpl.INSTANCE.isFinished(coroutineOwner));
    }
}
