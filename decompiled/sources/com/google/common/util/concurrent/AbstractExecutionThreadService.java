package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class AbstractExecutionThreadService implements Service {
    public final Service delegate = new AnonymousClass1();

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractExecutionThreadService$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AbstractService {
        public static /* synthetic */ void $r8$lambda$dDI9BsSyWt53Yl2AuTWdlKAu5Vg(AnonymousClass1 anonymousClass1) throws Exception {
            anonymousClass1.getClass();
            try {
                AbstractExecutionThreadService.this.getClass();
                anonymousClass1.notifyStarted();
                if (anonymousClass1.isRunning()) {
                    try {
                        AbstractExecutionThreadService.this.run();
                    } catch (Throwable th) {
                        Platform.restoreInterruptIfIsInterruptedException(th);
                        try {
                            AbstractExecutionThreadService.this.getClass();
                        } catch (Exception e) {
                            Platform.restoreInterruptIfIsInterruptedException(e);
                            th.addSuppressed(e);
                        }
                        anonymousClass1.notifyFailed(th);
                        return;
                    }
                }
                AbstractExecutionThreadService.this.getClass();
                anonymousClass1.notifyStopped();
            } catch (Throwable th2) {
                Platform.restoreInterruptIfIsInterruptedException(th2);
                anonymousClass1.notifyFailed(th2);
            }
        }

        public AnonymousClass1() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            ((MoreExecutors$$ExternalSyntheticLambda1) MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), (Supplier<String>) new Supplier() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$1$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return AbstractExecutionThreadService.this.serviceName();
                }
            })).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    AbstractExecutionThreadService.AnonymousClass1.$r8$lambda$dDI9BsSyWt53Yl2AuTWdlKAu5Vg(this.f$0);
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStop() {
            AbstractExecutionThreadService.this.getClass();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public Executor executor() {
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                MoreExecutors.newThread(this.f$0.serviceName(), runnable).start();
            }
        };
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public abstract void run() throws Exception;

    public String serviceName() {
        return getClass().getSimpleName();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public String toString() {
        return serviceName() + " [" + this.delegate.state() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    public void shutDown() throws Exception {
    }

    public void startUp() throws Exception {
    }

    public void triggerShutdown() {
    }
}
