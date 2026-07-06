package kotlinx.coroutines.flow.internal;

import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SubscriptionCountStateFlow extends SharedFlowImpl<Integer> implements StateFlow<Integer> {
    public SubscriptionCountStateFlow(int i) {
        super(1, Integer.MAX_VALUE, BufferOverflow.DROP_OLDEST);
        tryEmit(Integer.valueOf(i));
    }

    public final boolean increment(int i) {
        boolean zTryEmit;
        synchronized (this) {
            zTryEmit = tryEmit(Integer.valueOf(getLastReplayedLocked().intValue() + i));
        }
        return zTryEmit;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.flow.StateFlow
    @NotNull
    public Integer getValue() {
        Integer numValueOf;
        synchronized (this) {
            numValueOf = Integer.valueOf(getLastReplayedLocked().intValue());
        }
        return numValueOf;
    }
}
