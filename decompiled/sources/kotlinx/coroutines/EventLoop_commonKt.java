package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EventLoop_commonKt {
    public static final long MAX_DELAY_NS = 4611686018427387903L;
    public static final long MAX_MS = 9223372036854L;
    public static final long MS_TO_NS = 1000000;
    public static final int SCHEDULE_COMPLETED = 1;
    public static final int SCHEDULE_DISPOSED = 2;
    public static final int SCHEDULE_OK = 0;

    @NotNull
    public static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");

    @NotNull
    public static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");

    public static final long delayNanosToMillis(long j) {
        return j / 1000000;
    }

    public static final long delayToNanos(long j) {
        if (j <= 0) {
            return 0L;
        }
        if (j >= MAX_MS) {
            return Long.MAX_VALUE;
        }
        return j * 1000000;
    }

    public static /* synthetic */ void getCLOSED_EMPTY$annotations() {
    }

    public static /* synthetic */ void getDISPOSED_TASK$annotations() {
    }
}
