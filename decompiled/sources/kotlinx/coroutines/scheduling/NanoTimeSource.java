package kotlinx.coroutines.scheduling;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class NanoTimeSource extends SchedulerTimeSource {

    @NotNull
    public static final NanoTimeSource INSTANCE = new NanoTimeSource();

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public long nanoTime() {
        return System.nanoTime();
    }
}
