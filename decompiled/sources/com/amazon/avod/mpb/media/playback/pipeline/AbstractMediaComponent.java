package com.amazon.avod.mpb.media.playback.pipeline;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@NotThreadSafe
@SourceDebugExtension({"SMAP\nAbstractMediaComponent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractMediaComponent.kt\ncom/amazon/avod/mpb/media/playback/pipeline/AbstractMediaComponent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,78:1\n1#2:79\n*E\n"})
public abstract class AbstractMediaComponent {

    @NotNull
    public volatile State state = State.UNCONFIGURED;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class State {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ State[] $VALUES;
        public static final State UNCONFIGURED = new State("UNCONFIGURED", 0);
        public static final State IDLE = new State("IDLE", 1);
        public static final State RUNNING = new State(DebugCoroutineInfoImplKt.RUNNING, 2);

        public static final /* synthetic */ State[] $values() {
            return new State[]{UNCONFIGURED, IDLE, RUNNING};
        }

        static {
            State[] stateArr$values = $values();
            $VALUES = stateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stateArr$values);
        }

        public State(String str, int i) {
        }

        @NotNull
        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public final String appendCurrentStateToErrorMessage(String str) {
        return str + " current state: " + this.state;
    }

    @VisibleForTesting(otherwise = 4)
    public final void configure$core_mpb_release() {
        if (this.state != State.UNCONFIGURED) {
            throw new IllegalStateException(appendCurrentStateToErrorMessage("Can only call configure() when in the UNCONFIGURED state").toString());
        }
        this.state = State.IDLE;
    }

    @NotNull
    public final State getState() {
        return this.state;
    }

    public final boolean isIdle() {
        return this.state == State.IDLE;
    }

    public final boolean isRunning() {
        return this.state == State.RUNNING;
    }

    public final boolean isUnconfigured() {
        return this.state == State.UNCONFIGURED;
    }

    public void release() {
        if (this.state != State.IDLE && this.state != State.UNCONFIGURED) {
            throw new IllegalStateException(appendCurrentStateToErrorMessage("Can only call release() when in the IDLE/UNCONFIGURED state").toString());
        }
        this.state = State.UNCONFIGURED;
    }

    public void start() throws MediaPipelineBackendException {
        if (this.state != State.IDLE) {
            throw new IllegalStateException(appendCurrentStateToErrorMessage("Can only call start() when in the IDLE state").toString());
        }
        this.state = State.RUNNING;
    }

    public void stop() {
        if (this.state != State.RUNNING) {
            throw new IllegalStateException(appendCurrentStateToErrorMessage("Can only call stop() when in the RUNNING state").toString());
        }
        this.state = State.IDLE;
    }
}
