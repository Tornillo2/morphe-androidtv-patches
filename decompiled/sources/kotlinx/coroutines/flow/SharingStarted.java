package kotlinx.coroutines.flow;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface SharingStarted {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public static final SharingStarted Eagerly = new StartedEagerly();

        @NotNull
        public static final SharingStarted Lazily = new StartedLazily();

        public static SharingStarted WhileSubscribed$default(Companion companion, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = 0;
            }
            if ((i & 2) != 0) {
                j2 = Long.MAX_VALUE;
            }
            companion.getClass();
            return new StartedWhileSubscribed(j, j2);
        }

        @NotNull
        public final SharingStarted WhileSubscribed(long j, long j2) {
            return new StartedWhileSubscribed(j, j2);
        }

        @NotNull
        public final SharingStarted getEagerly() {
            return Eagerly;
        }

        @NotNull
        public final SharingStarted getLazily() {
            return Lazily;
        }
    }

    @NotNull
    Flow<SharingCommand> command(@NotNull StateFlow<Integer> stateFlow);
}
