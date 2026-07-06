package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ByFunctionOrdering;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.time.Duration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class ServiceManager implements ServiceManagerBridge {
    public final ImmutableList<Service> services;
    public final ServiceManagerState state;
    public static final LazyLogger logger = new LazyLogger(ServiceManager.class);
    public static final ListenerCallQueue.Event<Listener> HEALTHY_EVENT = new AnonymousClass1();
    public static final ListenerCallQueue.Event<Listener> STOPPED_EVENT = new AnonymousClass2();

    /* JADX INFO: renamed from: com.google.common.util.concurrent.ServiceManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements ListenerCallQueue.Event<Listener> {
        public String toString() {
            return "healthy()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.getClass();
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.ServiceManager$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements ListenerCallQueue.Event<Listener> {
        public String toString() {
            return "stopped()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.getClass();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EmptyServiceManagerWarning extends Throwable {
        public EmptyServiceManagerWarning() {
        }

        public EmptyServiceManagerWarning(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FailedService extends Throwable {
        public FailedService(Service service) {
            super(service.toString(), service.failureCause(), false, false);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NoOpService extends AbstractService {
        public NoOpService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStart() throws Exception {
            notifyStarted();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStop() throws Exception {
            notifyStopped();
        }

        public NoOpService(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ServiceListener extends Service.Listener {
        public final Service service;
        public final WeakReference<ServiceManagerState> state;

        public ServiceListener(Service service, WeakReference<ServiceManagerState> state) {
            this.service = service;
            this.state = state;
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void failed(Service.State from, Throwable failure) throws Exception {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                if ((!(this.service instanceof NoOpService)) & (from != Service.State.STARTING)) {
                    ServiceManager.logger.get().log(Level.SEVERE, "Service " + this.service + " has failed in the " + from + " state.", failure);
                }
                serviceManagerState.transitionService(this.service, from, Service.State.FAILED);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void running() throws Exception {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, Service.State.STARTING, Service.State.RUNNING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void starting() throws Exception {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, Service.State.NEW, Service.State.STARTING);
                if (this.service instanceof NoOpService) {
                    return;
                }
                ServiceManager.logger.get().log(Level.FINE, "Starting {0}.", this.service);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void stopping(Service.State from) throws Exception {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, from, Service.State.STOPPING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void terminated(Service.State from) throws Exception {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                if (!(this.service instanceof NoOpService)) {
                    ServiceManager.logger.get().log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.service, from});
                }
                serviceManagerState.transitionService(this.service, from, Service.State.TERMINATED);
            }
        }
    }

    public ServiceManager(Iterable<? extends Service> services) {
        ImmutableList<Service> immutableListCopyOf = ImmutableList.copyOf(services);
        if (immutableListCopyOf.isEmpty()) {
            logger.get().log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", (Throwable) new EmptyServiceManagerWarning());
            immutableListCopyOf = ImmutableList.of(new NoOpService());
        }
        ServiceManagerState serviceManagerState = new ServiceManagerState(immutableListCopyOf);
        this.state = serviceManagerState;
        this.services = immutableListCopyOf;
        WeakReference weakReference = new WeakReference(serviceManagerState);
        UnmodifiableIterator<Service> it = immutableListCopyOf.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            next.addListener(new ServiceListener(next, weakReference), DirectExecutor.INSTANCE);
            Preconditions.checkArgument(next.state() == Service.State.NEW, "Can only manage NEW services, %s", next);
        }
        this.state.markReady();
    }

    public void addListener(Listener listener, Executor executor) {
        this.state.addListener(listener, executor);
    }

    public void awaitHealthy() {
        this.state.awaitHealthy();
    }

    public void awaitStopped() {
        this.state.awaitStopped();
    }

    public boolean isHealthy() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            if (!it.next().isRunning()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.util.concurrent.ServiceManagerBridge
    public ImmutableMultimap servicesByState() {
        return this.state.servicesByState();
    }

    @CanIgnoreReturnValue
    public ServiceManager startAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            Preconditions.checkState(it.next().state() == Service.State.NEW, "Not all services are NEW, cannot start %s", this);
        }
        UnmodifiableIterator<Service> it2 = this.services.iterator();
        while (it2.hasNext()) {
            Service next = it2.next();
            try {
                this.state.tryStartTiming(next);
                next.startAsync();
            } catch (IllegalStateException e) {
                logger.get().log(Level.WARNING, "Unable to start Service " + next, (Throwable) e);
            }
        }
        return this;
    }

    @IgnoreJRERequirement
    public ImmutableMap<Service, Duration> startupDurations() {
        return ImmutableMap.copyOf(Maps.transformValues(this.state.startupTimes(), new ServiceManager$$ExternalSyntheticLambda0()));
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.state.startupTimes();
    }

    @CanIgnoreReturnValue
    public ServiceManager stopAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            it.next().stopAsync();
        }
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper("ServiceManager");
        toStringHelper.addHolder("services", Collections2.filter(this.services, new Predicates.NotPredicate(new Predicates.InstanceOfPredicate(NoOpService.class))));
        return toStringHelper.toString();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ServiceManagerState {
        public final Monitor.Guard awaitHealthGuard;
        public final ListenerCallQueue<Listener> listeners;
        public final Monitor monitor = new Monitor(false);
        public final int numberOfServices;

        @GuardedBy("monitor")
        public boolean ready;

        @GuardedBy("monitor")
        public final SetMultimap<Service.State, Service> servicesByState;

        @GuardedBy("monitor")
        public final IdentityHashMap<Service, Stopwatch> startupTimers;

        @GuardedBy("monitor")
        public final Multiset<Service.State> states;
        public final Monitor.Guard stoppedGuard;

        @GuardedBy("monitor")
        public boolean transitioned;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class AwaitHealthGuard extends Monitor.Guard {
            public AwaitHealthGuard() {
                super(ServiceManagerState.this.monitor);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                int iCount = ServiceManagerState.this.states.count(Service.State.RUNNING);
                ServiceManagerState serviceManagerState = ServiceManagerState.this;
                return iCount == serviceManagerState.numberOfServices || serviceManagerState.states.contains(Service.State.STOPPING) || ServiceManagerState.this.states.contains(Service.State.TERMINATED) || ServiceManagerState.this.states.contains(Service.State.FAILED);
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final class StoppedGuard extends Monitor.Guard {
            public StoppedGuard() {
                super(ServiceManagerState.this.monitor);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                return ServiceManagerState.this.states.count(Service.State.FAILED) + ServiceManagerState.this.states.count(Service.State.TERMINATED) == ServiceManagerState.this.numberOfServices;
            }
        }

        public ServiceManagerState(ImmutableCollection<Service> services) {
            SetMultimap<Service.State, Service> setMultimapBuild = new MultimapBuilder.AnonymousClass4(Service.State.class).linkedHashSetValues(2).build();
            this.servicesByState = setMultimapBuild;
            this.states = setMultimapBuild.keys();
            this.startupTimers = new IdentityHashMap<>();
            this.awaitHealthGuard = new AwaitHealthGuard();
            this.stoppedGuard = new StoppedGuard();
            this.listeners = new ListenerCallQueue<>();
            this.numberOfServices = services.size();
            setMultimapBuild.putAll(Service.State.NEW, services);
        }

        public void addListener(Listener listener, Executor executor) {
            this.listeners.addListener(listener, executor);
        }

        public void awaitHealthy() {
            this.monitor.enterWhenUninterruptibly(this.awaitHealthGuard);
            try {
                checkHealthy();
            } finally {
                this.monitor.leave();
            }
        }

        public void awaitStopped() {
            this.monitor.enterWhenUninterruptibly(this.stoppedGuard);
            this.monitor.leave();
        }

        @GuardedBy("monitor")
        public void checkHealthy() {
            Multiset<Service.State> multiset = this.states;
            Service.State state = Service.State.RUNNING;
            if (multiset.count(state) != this.numberOfServices) {
                IllegalStateException illegalStateException = new IllegalStateException("Expected to be healthy after starting. The following services are not running: " + Multimaps.filterKeys((SetMultimap) this.servicesByState, (Predicate) new Predicates.NotPredicate(Predicates.equalTo(state))));
                Iterator<Service> it = this.servicesByState.get(Service.State.FAILED).iterator();
                while (it.hasNext()) {
                    illegalStateException.addSuppressed(new FailedService(it.next()));
                }
                throw illegalStateException;
            }
        }

        public void dispatchListenerEvents() throws Exception {
            Preconditions.checkState(!this.monitor.lock.isHeldByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
            this.listeners.dispatch();
        }

        public void enqueueFailedEvent(final Service service) {
            ListenerCallQueue<Listener> listenerCallQueue = this.listeners;
            ListenerCallQueue.Event<Listener> event = new ListenerCallQueue.Event<Listener>(this) { // from class: com.google.common.util.concurrent.ServiceManager.ServiceManagerState.1
                public final /* synthetic */ ServiceManagerState this$0;

                {
                    this.this$0 = this;
                }

                public String toString() {
                    return "failed({service=" + service + "})";
                }

                @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
                public void call(Listener listener) {
                    listener.getClass();
                }
            };
            listenerCallQueue.enqueueHelper(event, event);
        }

        public void enqueueHealthyEvent() {
            ListenerCallQueue<Listener> listenerCallQueue = this.listeners;
            ListenerCallQueue.Event<Listener> event = ServiceManager.HEALTHY_EVENT;
            listenerCallQueue.enqueueHelper(event, event);
        }

        public void enqueueStoppedEvent() {
            ListenerCallQueue<Listener> listenerCallQueue = this.listeners;
            ListenerCallQueue.Event<Listener> event = ServiceManager.STOPPED_EVENT;
            listenerCallQueue.enqueueHelper(event, event);
        }

        public void markReady() {
            this.monitor.enter();
            try {
                if (!this.transitioned) {
                    this.ready = true;
                    return;
                }
                ArrayList arrayList = new ArrayList();
                UnmodifiableIterator<Service> it = servicesByState().values().iterator();
                while (it.hasNext()) {
                    Service next = it.next();
                    if (next.state() != Service.State.NEW) {
                        arrayList.add(next);
                    }
                }
                throw new IllegalArgumentException("Services started transitioning asynchronously before the ServiceManager was constructed: " + arrayList);
            } finally {
                this.monitor.leave();
            }
        }

        public ImmutableSetMultimap<Service.State, Service> servicesByState() {
            ImmutableSetMultimap.Builder builder = new ImmutableSetMultimap.Builder();
            this.monitor.enter();
            try {
                for (Map.Entry<Service.State, Service> entry : this.servicesByState.entries()) {
                    if (!(entry.getValue() instanceof NoOpService)) {
                        builder.put((Map.Entry) entry);
                    }
                }
                this.monitor.leave();
                return builder.build();
            } catch (Throwable th) {
                this.monitor.leave();
                throw th;
            }
        }

        public ImmutableMap<Service, Long> startupTimes() {
            this.monitor.enter();
            try {
                ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(this.startupTimers.size());
                for (Map.Entry<Service, Stopwatch> entry : this.startupTimers.entrySet()) {
                    Service key = entry.getKey();
                    Stopwatch value = entry.getValue();
                    if (!value.isRunning && !(key instanceof NoOpService)) {
                        arrayListNewArrayListWithCapacity.add(new ImmutableEntry(key, Long.valueOf(value.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                this.monitor.leave();
                NaturalOrdering naturalOrdering = NaturalOrdering.INSTANCE;
                ServiceManager$ServiceManagerState$$ExternalSyntheticLambda0 serviceManager$ServiceManagerState$$ExternalSyntheticLambda0 = new ServiceManager$ServiceManagerState$$ExternalSyntheticLambda0();
                naturalOrdering.getClass();
                Collections.sort(arrayListNewArrayListWithCapacity, new ByFunctionOrdering(serviceManager$ServiceManagerState$$ExternalSyntheticLambda0, naturalOrdering));
                return ImmutableMap.copyOf(arrayListNewArrayListWithCapacity);
            } catch (Throwable th) {
                this.monitor.leave();
                throw th;
            }
        }

        public void transitionService(Service service, Service.State from, Service.State to) throws Exception {
            service.getClass();
            Preconditions.checkArgument(from != to);
            this.monitor.enter();
            try {
                this.transitioned = true;
                if (this.ready) {
                    Preconditions.checkState(this.servicesByState.remove(from, service), "Service %s not at the expected location in the state map %s", service, from);
                    Preconditions.checkState(this.servicesByState.put(to, service), "Service %s in the state map unexpectedly at %s", service, to);
                    Stopwatch stopwatchCreateStarted = this.startupTimers.get(service);
                    if (stopwatchCreateStarted == null) {
                        stopwatchCreateStarted = Stopwatch.createStarted();
                        this.startupTimers.put(service, stopwatchCreateStarted);
                    }
                    Service.State state = Service.State.RUNNING;
                    if (to.compareTo(state) >= 0 && stopwatchCreateStarted.isRunning) {
                        stopwatchCreateStarted.stop();
                        if (!(service instanceof NoOpService)) {
                            ServiceManager.logger.get().log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatchCreateStarted});
                        }
                    }
                    Service.State state2 = Service.State.FAILED;
                    if (to == state2) {
                        enqueueFailedEvent(service);
                    }
                    if (this.states.count(state) == this.numberOfServices) {
                        enqueueHealthyEvent();
                    } else if (this.states.count(Service.State.TERMINATED) + this.states.count(state2) == this.numberOfServices) {
                        enqueueStoppedEvent();
                    }
                }
                this.monitor.leave();
                dispatchListenerEvents();
            } catch (Throwable th) {
                this.monitor.leave();
                dispatchListenerEvents();
                throw th;
            }
        }

        public void tryStartTiming(Service service) {
            this.monitor.enter();
            try {
                if (this.startupTimers.get(service) == null) {
                    this.startupTimers.put(service, Stopwatch.createStarted());
                }
            } finally {
                this.monitor.leave();
            }
        }

        public void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (this.monitor.waitForUninterruptibly(this.stoppedGuard, timeout, unit)) {
                    return;
                }
                throw new TimeoutException("Timeout waiting for the services to stop. The following services have not stopped: " + Multimaps.filterKeys((SetMultimap) this.servicesByState, (Predicate) new Predicates.NotPredicate(new Predicates.InPredicate(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
            } finally {
                this.monitor.leave();
            }
        }

        public void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (!this.monitor.waitForUninterruptibly(this.awaitHealthGuard, timeout, unit)) {
                    throw new TimeoutException("Timeout waiting for the services to become healthy. The following services have not started: " + Multimaps.filterKeys((SetMultimap) this.servicesByState, (Predicate) new Predicates.InPredicate(ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
                }
                checkHealthy();
            } finally {
                this.monitor.leave();
            }
        }
    }

    @IgnoreJRERequirement
    public void awaitHealthy(Duration timeout) throws TimeoutException {
        awaitHealthy(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @IgnoreJRERequirement
    public void awaitStopped(Duration timeout) throws TimeoutException {
        awaitStopped(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
    }

    @Override // com.google.common.util.concurrent.ServiceManagerBridge
    public ImmutableSetMultimap<Service.State, Service> servicesByState() {
        return this.state.servicesByState();
    }

    public void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
        this.state.awaitHealthy(timeout, unit);
    }

    public void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
        this.state.awaitStopped(timeout, unit);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Listener {
        public void healthy() {
        }

        public void stopped() {
        }

        public void failure(Service service) {
        }
    }
}
