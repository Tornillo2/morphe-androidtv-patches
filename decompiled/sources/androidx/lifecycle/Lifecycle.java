package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Lifecycle {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public AtomicReference<Object> internalScopeRef = new AtomicReference<>();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;


        @NotNull
        public static final Companion Companion = new Companion();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {

            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[State.values().length];
                    try {
                        iArr[State.CREATED.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[State.STARTED.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[State.RESUMED.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[State.DESTROYED.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[State.INITIALIZED.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            public Companion() {
            }

            @JvmStatic
            @Nullable
            public final Event downFrom(@NotNull State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
                if (i == 1) {
                    return Event.ON_DESTROY;
                }
                if (i == 2) {
                    return Event.ON_STOP;
                }
                if (i != 3) {
                    return null;
                }
                return Event.ON_PAUSE;
            }

            @JvmStatic
            @Nullable
            public final Event downTo(@NotNull State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
                if (i == 1) {
                    return Event.ON_STOP;
                }
                if (i == 2) {
                    return Event.ON_PAUSE;
                }
                if (i != 4) {
                    return null;
                }
                return Event.ON_DESTROY;
            }

            @JvmStatic
            @Nullable
            public final Event upFrom(@NotNull State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
                if (i == 1) {
                    return Event.ON_START;
                }
                if (i == 2) {
                    return Event.ON_RESUME;
                }
                if (i != 5) {
                    return null;
                }
                return Event.ON_CREATE;
            }

            @JvmStatic
            @Nullable
            public final Event upTo(@NotNull State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
                if (i == 1) {
                    return Event.ON_CREATE;
                }
                if (i == 2) {
                    return Event.ON_START;
                }
                if (i != 3) {
                    return null;
                }
                return Event.ON_RESUME;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Event.values().length];
                try {
                    iArr[Event.ON_CREATE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Event.ON_STOP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Event.ON_START.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Event.ON_PAUSE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Event.ON_RESUME.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Event.ON_DESTROY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[Event.ON_ANY.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @JvmStatic
        @Nullable
        public static final Event downFrom(@NotNull State state) {
            return Companion.downFrom(state);
        }

        @JvmStatic
        @Nullable
        public static final Event downTo(@NotNull State state) {
            return Companion.downTo(state);
        }

        @JvmStatic
        @Nullable
        public static final Event upFrom(@NotNull State state) {
            return Companion.upFrom(state);
        }

        @JvmStatic
        @Nullable
        public static final Event upTo(@NotNull State state) {
            return Companion.upTo(state);
        }

        @NotNull
        public final State getTargetState() {
            switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                case 1:
                case 2:
                    return State.CREATED;
                case 3:
                case 4:
                    return State.STARTED;
                case 5:
                    return State.RESUMED;
                case 6:
                    return State.DESTROYED;
                default:
                    throw new IllegalArgumentException(this + " has no target state");
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public final boolean isAtLeast(@NotNull State state) {
            Intrinsics.checkNotNullParameter(state, "state");
            return compareTo(state) >= 0;
        }
    }

    @MainThread
    public abstract void addObserver(@NotNull LifecycleObserver lifecycleObserver);

    @MainThread
    @NotNull
    public abstract State getCurrentState();

    @NotNull
    public final AtomicReference<Object> getInternalScopeRef() {
        return this.internalScopeRef;
    }

    @MainThread
    public abstract void removeObserver(@NotNull LifecycleObserver lifecycleObserver);

    public final void setInternalScopeRef(@NotNull AtomicReference<Object> atomicReference) {
        Intrinsics.checkNotNullParameter(atomicReference, "<set-?>");
        this.internalScopeRef = atomicReference;
    }
}
