package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {

    @NotNull
    public static final Companion Companion = new Companion();
    public int addingObserverCounter;
    public final boolean enforceMainThread;
    public boolean handlingEvent;

    @NotNull
    public final WeakReference<LifecycleOwner> lifecycleOwner;
    public boolean newEventOccurred;

    @NotNull
    public FastSafeIterableMap<LifecycleObserver, ObserverWithState> observerMap;

    @NotNull
    public ArrayList<Lifecycle.State> parentStates;

    @NotNull
    public Lifecycle.State state;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        @VisibleForTesting
        @NotNull
        public final LifecycleRegistry createUnsafe(@NotNull LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            return new LifecycleRegistry(owner, false);
        }

        @JvmStatic
        @NotNull
        public final Lifecycle.State min$lifecycle_runtime_release(@NotNull Lifecycle.State state1, @Nullable Lifecycle.State state) {
            Intrinsics.checkNotNullParameter(state1, "state1");
            return (state == null || state.compareTo(state1) >= 0) ? state1 : state;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ObserverWithState {

        @NotNull
        public LifecycleEventObserver lifecycleObserver;

        @NotNull
        public Lifecycle.State state;

        public ObserverWithState(@Nullable LifecycleObserver lifecycleObserver, @NotNull Lifecycle.State initialState) {
            Intrinsics.checkNotNullParameter(initialState, "initialState");
            Intrinsics.checkNotNull(lifecycleObserver);
            this.lifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver);
            this.state = initialState;
        }

        public final void dispatchEvent(@Nullable LifecycleOwner lifecycleOwner, @NotNull Lifecycle.Event event) {
            Intrinsics.checkNotNullParameter(event, "event");
            Lifecycle.State targetState = event.getTargetState();
            this.state = LifecycleRegistry.Companion.min$lifecycle_runtime_release(this.state, targetState);
            LifecycleEventObserver lifecycleEventObserver = this.lifecycleObserver;
            Intrinsics.checkNotNull(lifecycleOwner);
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
            this.state = targetState;
        }

        @NotNull
        public final LifecycleEventObserver getLifecycleObserver() {
            return this.lifecycleObserver;
        }

        @NotNull
        public final Lifecycle.State getState() {
            return this.state;
        }

        public final void setLifecycleObserver(@NotNull LifecycleEventObserver lifecycleEventObserver) {
            Intrinsics.checkNotNullParameter(lifecycleEventObserver, "<set-?>");
            this.lifecycleObserver = lifecycleEventObserver;
        }

        public final void setState(@NotNull Lifecycle.State state) {
            Intrinsics.checkNotNullParameter(state, "<set-?>");
            this.state = state;
        }
    }

    public /* synthetic */ LifecycleRegistry(LifecycleOwner lifecycleOwner, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, z);
    }

    @JvmStatic
    @VisibleForTesting
    @NotNull
    public static final LifecycleRegistry createUnsafe(@NotNull LifecycleOwner lifecycleOwner) {
        return Companion.createUnsafe(lifecycleOwner);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(@NotNull LifecycleObserver observer) {
        LifecycleOwner lifecycleOwner;
        Intrinsics.checkNotNullParameter(observer, "observer");
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State state = this.state;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(observer, state2);
        if (this.observerMap.putIfAbsent(observer, observerWithState) == null && (lifecycleOwner = this.lifecycleOwner.get()) != null) {
            boolean z = this.addingObserverCounter != 0 || this.handlingEvent;
            Lifecycle.State stateCalculateTargetState = calculateTargetState(observer);
            this.addingObserverCounter++;
            while (observerWithState.state.compareTo(stateCalculateTargetState) < 0 && this.observerMap.contains(observer)) {
                pushParentState(observerWithState.state);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.Companion.upFrom(observerWithState.state);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + observerWithState.state);
                }
                observerWithState.dispatchEvent(lifecycleOwner, eventUpFrom);
                popParentState();
                stateCalculateTargetState = calculateTargetState(observer);
            }
            if (!z) {
                sync();
            }
            this.addingObserverCounter--;
        }
    }

    public final void backwardPass(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> itDescendingIterator = this.observerMap.descendingIterator();
        while (true) {
            SafeIterableMap.ListIterator listIterator = (SafeIterableMap.ListIterator) itDescendingIterator;
            if (!listIterator.hasNext() || this.newEventOccurred) {
                return;
            }
            Map.Entry next = listIterator.next();
            Intrinsics.checkNotNullExpressionValue(next, "next()");
            LifecycleObserver lifecycleObserver = (LifecycleObserver) next.getKey();
            ObserverWithState observerWithState = (ObserverWithState) next.getValue();
            while (observerWithState.state.compareTo(this.state) > 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver)) {
                Lifecycle.Event eventDownFrom = Lifecycle.Event.Companion.downFrom(observerWithState.state);
                if (eventDownFrom == null) {
                    throw new IllegalStateException("no event down from " + observerWithState.state);
                }
                pushParentState(eventDownFrom.getTargetState());
                observerWithState.dispatchEvent(lifecycleOwner, eventDownFrom);
                popParentState();
            }
        }
    }

    public final Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        ObserverWithState value;
        Map.Entry<LifecycleObserver, ObserverWithState> entryCeil = this.observerMap.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = (entryCeil == null || (value = entryCeil.getValue()) == null) ? null : value.state;
        if (!this.parentStates.isEmpty()) {
            state = this.parentStates.get(r0.size() - 1);
        }
        Companion companion = Companion;
        return companion.min$lifecycle_runtime_release(companion.min$lifecycle_runtime_release(this.state, state2), state);
    }

    @SuppressLint({"RestrictedApi"})
    public final void enforceMainThreadIfNeeded(String str) {
        if (this.enforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Method ", str, " must be called on the main thread").toString());
        }
    }

    public final void forwardPass(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<LifecycleObserver, ObserverWithState>.IteratorWithAdditions iteratorWithAdditions = this.observerMap.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.newEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> next = iteratorWithAdditions.next();
            LifecycleObserver key = next.getKey();
            ObserverWithState value = next.getValue();
            while (value.state.compareTo(this.state) < 0 && !this.newEventOccurred && this.observerMap.contains(key)) {
                pushParentState(value.state);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.Companion.upFrom(value.state);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + value.state);
                }
                value.dispatchEvent(lifecycleOwner, eventUpFrom);
                popParentState();
            }
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    @NotNull
    public Lifecycle.State getCurrentState() {
        return this.state;
    }

    public int getObserverCount() {
        enforceMainThreadIfNeeded("getObserverCount");
        return this.observerMap.size();
    }

    public void handleLifecycleEvent(@NotNull Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(event, "event");
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    public final boolean isSynced() {
        if (this.observerMap.size() == 0) {
            return true;
        }
        Map.Entry<LifecycleObserver, ObserverWithState> entryEldest = this.observerMap.eldest();
        Intrinsics.checkNotNull(entryEldest);
        Lifecycle.State state = entryEldest.getValue().state;
        Map.Entry<LifecycleObserver, ObserverWithState> entryNewest = this.observerMap.newest();
        Intrinsics.checkNotNull(entryNewest);
        Lifecycle.State state2 = entryNewest.getValue().state;
        return state == state2 && this.state == state2;
    }

    @Deprecated(message = "Override [currentState].")
    @MainThread
    public void markState(@NotNull Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        enforceMainThreadIfNeeded("markState");
        setCurrentState(state);
    }

    public final void moveToState(Lifecycle.State state) {
        Lifecycle.State state2 = this.state;
        if (state2 == state) {
            return;
        }
        if (state2 == Lifecycle.State.INITIALIZED && state == Lifecycle.State.DESTROYED) {
            throw new IllegalStateException(("no event down from " + this.state + " in component " + this.lifecycleOwner.get()).toString());
        }
        this.state = state;
        if (this.handlingEvent || this.addingObserverCounter != 0) {
            this.newEventOccurred = true;
            return;
        }
        this.handlingEvent = true;
        sync();
        this.handlingEvent = false;
        if (this.state == Lifecycle.State.DESTROYED) {
            this.observerMap = new FastSafeIterableMap<>();
        }
    }

    public final void popParentState() {
        this.parentStates.remove(r0.size() - 1);
    }

    public final void pushParentState(Lifecycle.State state) {
        this.parentStates.add(state);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(@NotNull LifecycleObserver observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(observer);
    }

    public void setCurrentState(@NotNull Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(state);
    }

    public final void sync() {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while (!isSynced()) {
            this.newEventOccurred = false;
            Lifecycle.State state = this.state;
            Map.Entry<LifecycleObserver, ObserverWithState> entryEldest = this.observerMap.eldest();
            Intrinsics.checkNotNull(entryEldest);
            if (state.compareTo(entryEldest.getValue().state) < 0) {
                backwardPass(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> entryNewest = this.observerMap.newest();
            if (!this.newEventOccurred && entryNewest != null && this.state.compareTo(entryNewest.getValue().state) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
        this.newEventOccurred = false;
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner, boolean z) {
        this.enforceMainThread = z;
        this.observerMap = new FastSafeIterableMap<>();
        this.state = Lifecycle.State.INITIALIZED;
        this.parentStates = new ArrayList<>();
        this.lifecycleOwner = new WeakReference<>(lifecycleOwner);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LifecycleRegistry(@NotNull LifecycleOwner provider) {
        this(provider, true);
        Intrinsics.checkNotNullParameter(provider, "provider");
    }
}
