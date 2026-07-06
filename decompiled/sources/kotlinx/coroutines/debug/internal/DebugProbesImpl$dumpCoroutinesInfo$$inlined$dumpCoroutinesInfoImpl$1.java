package kotlinx.coroutines.debug.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DebugProbesImpl$dumpCoroutinesInfo$$inlined$dumpCoroutinesInfoImpl$1 extends Lambda implements Function1<DebugProbesImpl.CoroutineOwner<?>, DebugCoroutineInfo> {
    public DebugProbesImpl$dumpCoroutinesInfo$$inlined$dumpCoroutinesInfoImpl$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final DebugCoroutineInfo invoke(@NotNull DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
        CoroutineContext context;
        if (DebugProbesImpl.INSTANCE.isFinished(coroutineOwner) || (context = coroutineOwner.info.getContext()) == null) {
            return null;
        }
        return new DebugCoroutineInfo(coroutineOwner.info, context);
    }
}
