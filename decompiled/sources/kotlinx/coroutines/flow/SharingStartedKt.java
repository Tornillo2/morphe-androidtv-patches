package kotlinx.coroutines.flow;

import kotlin.time.Duration;
import kotlinx.coroutines.flow.SharingStarted;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SharingStartedKt {
    @NotNull
    /* JADX INFO: renamed from: WhileSubscribed-5qebJ5I, reason: not valid java name */
    public static final SharingStarted m2117WhileSubscribed5qebJ5I(@NotNull SharingStarted.Companion companion, long j, long j2) {
        return new StartedWhileSubscribed(Duration.m1936getInWholeMillisecondsimpl(j), Duration.m1936getInWholeMillisecondsimpl(j2));
    }

    /* JADX INFO: renamed from: WhileSubscribed-5qebJ5I$default, reason: not valid java name */
    public static SharingStarted m2118WhileSubscribed5qebJ5I$default(SharingStarted.Companion companion, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            Duration.Companion.getClass();
            j = Duration.ZERO;
        }
        if ((i & 2) != 0) {
            Duration.Companion.getClass();
            j2 = Duration.INFINITE;
        }
        return m2117WhileSubscribed5qebJ5I(companion, j, j2);
    }
}
