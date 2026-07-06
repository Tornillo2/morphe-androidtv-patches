package kotlin.time;

import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "2.1")
@ExperimentalTime
public interface Clock {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class System implements Clock {

        @NotNull
        public static final System INSTANCE = new System();

        @Override // kotlin.time.Clock
        @NotNull
        public Instant now() {
            return InstantJvmKt.systemClockNow();
        }
    }

    @NotNull
    Instant now();
}
