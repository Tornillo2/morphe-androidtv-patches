package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class AbstractService implements Service {
    public static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_RUNNING_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_STARTING_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_NEW_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_RUNNING_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STARTING_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STOPPING_EVENT;
    public static final ListenerCallQueue.Event<Service.Listener> STARTING_EVENT = new AnonymousClass1();
    public static final ListenerCallQueue.Event<Service.Listener> RUNNING_EVENT = new AnonymousClass2();
    public final Monitor monitor = new Monitor(false);
    public final Monitor.Guard isStartable = new IsStartableGuard();
    public final Monitor.Guard isStoppable = new IsStoppableGuard();
    public final Monitor.Guard hasReachedRunning = new HasReachedRunningGuard();
    public final Monitor.Guard isStopped = new IsStoppedGuard();
    public final ListenerCallQueue<Service.Listener> listeners = new ListenerCallQueue<>();
    public volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements ListenerCallQueue.Event<Service.Listener> {
        public String toString() {
            return "starting()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.starting();
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements ListenerCallQueue.Event<Service.Listener> {
        public String toString() {
            return "running()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.running();
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 implements ListenerCallQueue.Event<Service.Listener> {
        public final /* synthetic */ Service.State val$from;

        public AnonymousClass3(final Service.State val$from) {
            this.val$from = val$from;
        }

        public String toString() {
            return "terminated({from = " + this.val$from + "})";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.terminated(this.val$from);
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 implements ListenerCallQueue.Event<Service.Listener> {
        public final /* synthetic */ Service.State val$from;

        public AnonymousClass4(final Service.State val$from) {
            this.val$from = val$from;
        }

        public String toString() {
            return "stopping({from = " + this.val$from + "})";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.stopping(this.val$from);
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$util$concurrent$Service$State;

        static {
            int[] iArr = new int[Service.State.values().length];
            $SwitchMap$com$google$common$util$concurrent$Service$State = iArr;
            try {
                iArr[Service.State.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.STARTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.STOPPING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class HasReachedRunningGuard extends Monitor.Guard {
        public HasReachedRunningGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.snapshot.externalState().compareTo(Service.State.RUNNING) >= 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class IsStartableGuard extends Monitor.Guard {
        public IsStartableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.snapshot.externalState() == Service.State.NEW;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class IsStoppableGuard extends Monitor.Guard {
        public IsStoppableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.snapshot.externalState().compareTo(Service.State.RUNNING) <= 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class IsStoppedGuard extends Monitor.Guard {
        public IsStoppedGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.snapshot.externalState().compareTo(Service.State.TERMINATED) >= 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StateSnapshot {
        public final Throwable failure;
        public final boolean shutdownWhenStartupFinishes;
        public final Service.State state;

        public StateSnapshot(Service.State internalState) {
            this(internalState, false, null);
        }

        public Service.State externalState() {
            return (this.shutdownWhenStartupFinishes && this.state == Service.State.STARTING) ? Service.State.STOPPING : this.state;
        }

        public Throwable failureCause() {
            Service.State state = this.state;
            Preconditions.checkState(state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", state);
            Throwable th = this.failure;
            Objects.requireNonNull(th);
            return th;
        }

        public StateSnapshot(Service.State internalState, boolean shutdownWhenStartupFinishes, Throwable failure) {
            Preconditions.checkArgument(!shutdownWhenStartupFinishes || internalState == Service.State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", internalState);
            Preconditions.checkArgument((failure != null) == (internalState == Service.State.FAILED), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", internalState, failure);
            this.state = internalState;
            this.shutdownWhenStartupFinishes = shutdownWhenStartupFinishes;
            this.failure = failure;
        }
    }

    static {
        Service.State state = Service.State.STARTING;
        STOPPING_FROM_STARTING_EVENT = new AnonymousClass4(state);
        Service.State state2 = Service.State.RUNNING;
        STOPPING_FROM_RUNNING_EVENT = new AnonymousClass4(state2);
        TERMINATED_FROM_NEW_EVENT = new AnonymousClass3(Service.State.NEW);
        TERMINATED_FROM_STARTING_EVENT = new AnonymousClass3(state);
        TERMINATED_FROM_RUNNING_EVENT = new AnonymousClass3(state2);
        TERMINATED_FROM_STOPPING_EVENT = new AnonymousClass3(Service.State.STOPPING);
    }

    public static ListenerCallQueue.Event<Service.Listener> stoppingEvent(Service.State from) {
        return new AnonymousClass4(from);
    }

    public static ListenerCallQueue.Event<Service.Listener> terminatedEvent(Service.State from) {
        return new AnonymousClass3(from);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.listeners.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    @GuardedBy("monitor")
    public final void checkCurrentState(Service.State expected) {
        Service.State stateExternalState = this.snapshot.externalState();
        if (stateExternalState != expected) {
            if (stateExternalState == Service.State.FAILED) {
                throw new IllegalStateException("Expected the service " + this + " to be " + expected + ", but the service has FAILED", this.snapshot.failureCause());
            }
            throw new IllegalStateException("Expected the service " + this + " to be " + expected + ", but was " + stateExternalState);
        }
    }

    public final void dispatchListenerEvents() throws Exception {
        if (this.monitor.lock.isHeldByCurrentThread()) {
            return;
        }
        this.listeners.dispatch();
    }

    @ForOverride
    public abstract void doStart();

    @ForOverride
    public abstract void doStop();

    public final void enqueueFailedEvent(final Service.State from, final Throwable cause) {
        ListenerCallQueue<Service.Listener> listenerCallQueue = this.listeners;
        ListenerCallQueue.Event<Service.Listener> event = new ListenerCallQueue.Event<Service.Listener>(this) { // from class: com.google.common.util.concurrent.AbstractService.5
            public final /* synthetic */ AbstractService this$0;

            {
                this.this$0 = this;
            }

            public String toString() {
                return "failed({from = " + from + ", cause = " + cause + "})";
            }

            @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
            public void call(Service.Listener listener) {
                listener.failed(from, cause);
            }
        };
        listenerCallQueue.enqueueHelper(event, event);
    }

    public final void enqueueRunningEvent() {
        ListenerCallQueue<Service.Listener> listenerCallQueue = this.listeners;
        ListenerCallQueue.Event<Service.Listener> event = RUNNING_EVENT;
        listenerCallQueue.enqueueHelper(event, event);
    }

    public final void enqueueStartingEvent() {
        ListenerCallQueue<Service.Listener> listenerCallQueue = this.listeners;
        ListenerCallQueue.Event<Service.Listener> event = STARTING_EVENT;
        listenerCallQueue.enqueueHelper(event, event);
    }

    public final void enqueueStoppingEvent(Service.State from) {
        if (from == Service.State.STARTING) {
            ListenerCallQueue<Service.Listener> listenerCallQueue = this.listeners;
            ListenerCallQueue.Event<Service.Listener> event = STOPPING_FROM_STARTING_EVENT;
            listenerCallQueue.enqueueHelper(event, event);
        } else {
            if (from != Service.State.RUNNING) {
                throw new AssertionError();
            }
            ListenerCallQueue<Service.Listener> listenerCallQueue2 = this.listeners;
            ListenerCallQueue.Event<Service.Listener> event2 = STOPPING_FROM_RUNNING_EVENT;
            listenerCallQueue2.enqueueHelper(event2, event2);
        }
    }

    public final void enqueueTerminatedEvent(Service.State from) {
        switch (AnonymousClass6.$SwitchMap$com$google$common$util$concurrent$Service$State[from.ordinal()]) {
            case 1:
                ListenerCallQueue<Service.Listener> listenerCallQueue = this.listeners;
                ListenerCallQueue.Event<Service.Listener> event = TERMINATED_FROM_NEW_EVENT;
                listenerCallQueue.enqueueHelper(event, event);
                return;
            case 2:
                ListenerCallQueue<Service.Listener> listenerCallQueue2 = this.listeners;
                ListenerCallQueue.Event<Service.Listener> event2 = TERMINATED_FROM_STARTING_EVENT;
                listenerCallQueue2.enqueueHelper(event2, event2);
                return;
            case 3:
                ListenerCallQueue<Service.Listener> listenerCallQueue3 = this.listeners;
                ListenerCallQueue.Event<Service.Listener> event3 = TERMINATED_FROM_RUNNING_EVENT;
                listenerCallQueue3.enqueueHelper(event3, event3);
                return;
            case 4:
                ListenerCallQueue<Service.Listener> listenerCallQueue4 = this.listeners;
                ListenerCallQueue.Event<Service.Listener> event4 = TERMINATED_FROM_STOPPING_EVENT;
                listenerCallQueue4.enqueueHelper(event4, event4);
                return;
            case 5:
            case 6:
                throw new AssertionError();
            default:
                return;
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.snapshot.externalState() == Service.State.RUNNING;
    }

    public final void notifyFailed(Throwable cause) throws Exception {
        cause.getClass();
        this.monitor.enter();
        try {
            Service.State stateExternalState = this.snapshot.externalState();
            int i = AnonymousClass6.$SwitchMap$com$google$common$util$concurrent$Service$State[stateExternalState.ordinal()];
            if (i != 1) {
                if (i == 2 || i == 3 || i == 4) {
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, cause);
                    enqueueFailedEvent(stateExternalState, cause);
                } else if (i != 5) {
                }
                return;
            }
            throw new IllegalStateException("Failed while in state:" + stateExternalState, cause);
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    public final void notifyStarted() throws Exception {
        this.monitor.enter();
        try {
            if (this.snapshot.state != Service.State.STARTING) {
                IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.snapshot.state);
                notifyFailed(illegalStateException);
                throw illegalStateException;
            }
            if (this.snapshot.shutdownWhenStartupFinishes) {
                this.snapshot = new StateSnapshot(Service.State.STOPPING);
                doStop();
            } else {
                this.snapshot = new StateSnapshot(Service.State.RUNNING);
                enqueueRunningEvent();
            }
            this.monitor.leave();
            dispatchListenerEvents();
        } catch (Throwable th) {
            this.monitor.leave();
            dispatchListenerEvents();
            throw th;
        }
    }

    public final void notifyStopped() throws Exception {
        this.monitor.enter();
        try {
            Service.State stateExternalState = this.snapshot.externalState();
            switch (AnonymousClass6.$SwitchMap$com$google$common$util$concurrent$Service$State[stateExternalState.ordinal()]) {
                case 1:
                case 5:
                case 6:
                    throw new IllegalStateException("Cannot notifyStopped() when the service is " + stateExternalState);
                case 2:
                case 3:
                case 4:
                    this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                    enqueueTerminatedEvent(stateExternalState);
                    break;
            }
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() throws Exception {
        if (!this.monitor.enterIf(this.isStartable)) {
            throw new IllegalStateException("Service " + this + " has already been started");
        }
        try {
            this.snapshot = new StateSnapshot(Service.State.STARTING);
            enqueueStartingEvent();
            doStart();
        } finally {
            try {
            } finally {
            }
        }
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.snapshot.externalState();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() throws Exception {
        if (!this.monitor.enterIf(this.isStoppable)) {
            return this;
        }
        try {
            Service.State stateExternalState = this.snapshot.externalState();
            switch (AnonymousClass6.$SwitchMap$com$google$common$util$concurrent$Service$State[stateExternalState.ordinal()]) {
                case 1:
                    this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                    enqueueTerminatedEvent(Service.State.NEW);
                    break;
                case 2:
                    Service.State state = Service.State.STARTING;
                    this.snapshot = new StateSnapshot(state, true, null);
                    enqueueStoppingEvent(state);
                    break;
                case 3:
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    enqueueStoppingEvent(Service.State.RUNNING);
                    doStop();
                    break;
                case 4:
                case 5:
                case 6:
                    throw new AssertionError("isStoppable is incorrectly implemented, saw: " + stateExternalState);
            }
        } finally {
            try {
            } finally {
            }
        }
        return this;
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + this.snapshot.externalState() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, timeout, unit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach the RUNNING state.");
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, timeout, unit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach a terminal state. Current state: " + this.snapshot.externalState());
        }
    }

    @ForOverride
    public void doCancelStart() {
    }
}
