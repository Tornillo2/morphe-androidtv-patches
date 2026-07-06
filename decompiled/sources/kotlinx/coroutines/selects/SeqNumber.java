package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SeqNumber {
    public static final /* synthetic */ AtomicLongFieldUpdater number$FU = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");

    @NotNull
    private volatile /* synthetic */ long number = 1;

    public final long next() {
        return number$FU.incrementAndGet(this);
    }
}
