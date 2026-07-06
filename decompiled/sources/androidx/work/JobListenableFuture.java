package androidx.work;

import androidx.work.impl.utils.futures.AbstractFuture;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class JobListenableFuture<R> implements ListenableFuture<R> {

    @NotNull
    public final Job job;

    @NotNull
    public final SettableFuture<R> underlying;

    public JobListenableFuture(@NotNull Job job, @NotNull SettableFuture<R> underlying) {
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(underlying, "underlying");
        this.job = job;
        this.underlying = underlying;
        job.invokeOnCompletion(new Function1<Throwable, Unit>(this) { // from class: androidx.work.JobListenableFuture.1
            public final /* synthetic */ JobListenableFuture<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable th) {
                if (th == null) {
                    if (!this.this$0.underlying.isDone()) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                } else {
                    if (th instanceof CancellationException) {
                        this.this$0.underlying.cancel(true);
                        return;
                    }
                    SettableFuture<R> settableFuture = this.this$0.underlying;
                    Throwable cause = th.getCause();
                    if (cause != null) {
                        th = cause;
                    }
                    settableFuture.setException(th);
                }
            }
        });
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.underlying.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.underlying.cancel(z);
    }

    public final void complete(R r) {
        this.underlying.set(r);
    }

    @Override // java.util.concurrent.Future
    public R get() {
        return this.underlying.get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.underlying.value instanceof AbstractFuture.Cancellation;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.underlying.isDone();
    }

    @Override // java.util.concurrent.Future
    public R get(long j, TimeUnit timeUnit) {
        return this.underlying.get(j, timeUnit);
    }

    public /* synthetic */ JobListenableFuture(Job job, SettableFuture settableFuture, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(job, (i & 2) != 0 ? SettableFuture.create() : settableFuture);
    }
}
