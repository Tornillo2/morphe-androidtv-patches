package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class AbstractIdleService implements Service {
    public final Supplier<String> threadNameSupplier = new ThreadNameSupplier();
    public final Service delegate = new DelegateService();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DelegateService extends AbstractService {
        public static /* synthetic */ void $r8$lambda$EsTjWlFYjw6SM3WwbDDqKg8hV8Q(DelegateService delegateService) throws Exception {
            delegateService.getClass();
            try {
                AbstractIdleService.this.shutDown();
                delegateService.notifyStopped();
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                delegateService.notifyFailed(th);
            }
        }

        public static /* synthetic */ void $r8$lambda$lZkWal2o9pxDCaVSm6dfm2w2dM4(DelegateService delegateService) throws Exception {
            delegateService.getClass();
            try {
                AbstractIdleService.this.startUp();
                delegateService.notifyStarted();
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                delegateService.notifyFailed(th);
            }
        }

        public DelegateService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            ((MoreExecutors$$ExternalSyntheticLambda1) MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier)).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService$DelegateService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    AbstractIdleService.DelegateService.$r8$lambda$lZkWal2o9pxDCaVSm6dfm2w2dM4(this.f$0);
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            ((MoreExecutors$$ExternalSyntheticLambda1) MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier)).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService$DelegateService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    AbstractIdleService.DelegateService.$r8$lambda$EsTjWlFYjw6SM3WwbDDqKg8hV8Q(this.f$0);
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractIdleService.this.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ThreadNameSupplier implements Supplier<String> {
        public ThreadNameSupplier() {
        }

        @Override // com.google.common.base.Supplier
        public String get() {
            return AbstractIdleService.this.serviceName() + StringUtils.SPACE + AbstractIdleService.this.delegate.state();
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
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractIdleService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                MoreExecutors.newThread(this.f$0.threadNameSupplier.get(), runnable).start();
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

    public String serviceName() {
        return getClass().getSimpleName();
    }

    public abstract void shutDown() throws Exception;

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public abstract void startUp() throws Exception;

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
}
