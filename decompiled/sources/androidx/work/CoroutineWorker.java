package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.impl.utils.futures.AbstractFuture;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import kotlin.Deprecated;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {

    @NotNull
    public final CoroutineDispatcher coroutineContext;

    @NotNull
    public final SettableFuture<ListenableWorker.Result> future;

    @NotNull
    public final CompletableJob job;

    /* JADX INFO: renamed from: androidx.work.CoroutineWorker$getForegroundInfoAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "androidx.work.CoroutineWorker$getForegroundInfoAsync$1", f = "CoroutineWorker.kt", i = {}, l = {134}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ JobListenableFuture<ForegroundInfo> $jobFuture;
        public Object L$0;
        public int label;
        public final /* synthetic */ CoroutineWorker this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00501(JobListenableFuture<ForegroundInfo> jobListenableFuture, CoroutineWorker coroutineWorker, Continuation<? super C00501> continuation) {
            super(2, continuation);
            this.$jobFuture = jobListenableFuture;
            this.this$0 = coroutineWorker;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new C00501(this.$jobFuture, this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                JobListenableFuture jobListenableFuture = (JobListenableFuture) this.L$0;
                ResultKt.throwOnFailure(obj);
                jobListenableFuture.complete(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            JobListenableFuture<ForegroundInfo> jobListenableFuture2 = this.$jobFuture;
            CoroutineWorker coroutineWorker = this.this$0;
            this.L$0 = jobListenableFuture2;
            this.label = 1;
            coroutineWorker.getForegroundInfo(this);
            throw null;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00501) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: androidx.work.CoroutineWorker$startWork$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "androidx.work.CoroutineWorker$startWork$1", f = "CoroutineWorker.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public C00511(Continuation<? super C00511> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return CoroutineWorker.this.new C00511(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineWorker coroutineWorker = CoroutineWorker.this;
                    this.label = 1;
                    obj = coroutineWorker.doWork(this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                CoroutineWorker.this.future.set((ListenableWorker.Result) obj);
            } catch (Throwable th) {
                CoroutineWorker.this.future.setException(th);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(@NotNull Context appContext, @NotNull WorkerParameters params) {
        super(appContext, params);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(params, "params");
        this.job = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        SettableFuture<ListenableWorker.Result> settableFutureCreate = SettableFuture.create();
        this.future = settableFutureCreate;
        settableFutureCreate.addListener(new Runnable() { // from class: androidx.work.CoroutineWorker.1
            @Override // java.lang.Runnable
            public final void run() {
                if (CoroutineWorker.this.future.value instanceof AbstractFuture.Cancellation) {
                    Job.DefaultImpls.cancel$default((Job) CoroutineWorker.this.job, (CancellationException) null, 1, (Object) null);
                }
            }
        }, getTaskExecutor().getBackgroundExecutor());
        this.coroutineContext = Dispatchers.getDefault();
    }

    public static /* synthetic */ Object getForegroundInfo$suspendImpl(CoroutineWorker coroutineWorker, Continuation continuation) {
        throw new IllegalStateException("Not implemented");
    }

    @Nullable
    public abstract Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation);

    @NotNull
    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    @Nullable
    public Object getForegroundInfo(@NotNull Continuation<? super ForegroundInfo> continuation) {
        getForegroundInfo$suspendImpl(this, continuation);
        throw null;
    }

    @Override // androidx.work.ListenableWorker
    @NotNull
    public final ListenableFuture<ForegroundInfo> getForegroundInfoAsync() {
        CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(getCoroutineContext().plus(completableJobJob$default));
        JobListenableFuture jobListenableFuture = new JobListenableFuture(completableJobJob$default, null, 2, null);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C00501(jobListenableFuture, this, null), 3, null);
        return jobListenableFuture;
    }

    @NotNull
    public final SettableFuture<ListenableWorker.Result> getFuture$work_runtime_ktx_release() {
        return this.future;
    }

    @NotNull
    public final CompletableJob getJob$work_runtime_ktx_release() {
        return this.job;
    }

    @Override // androidx.work.ListenableWorker
    public final void onStopped() {
        super.onStopped();
        this.future.cancel(false);
    }

    @Nullable
    public final Object setForeground(@NotNull ForegroundInfo foregroundInfo, @NotNull Continuation<? super Unit> continuation) throws Throwable {
        Object result;
        ListenableFuture<Void> foregroundAsync = setForegroundAsync(foregroundInfo);
        Intrinsics.checkNotNullExpressionValue(foregroundAsync, "setForegroundAsync(foregroundInfo)");
        if (foregroundAsync.isDone()) {
            try {
                result = foregroundAsync.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            foregroundAsync.addListener(new ListenableFutureKt$await$2$1(cancellableContinuationImpl, foregroundAsync), DirectExecutor.INSTANCE);
            cancellableContinuationImpl.invokeOnCancellation(new ListenableFutureKt$await$2$2(foregroundAsync));
            result = cancellableContinuationImpl.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    @Nullable
    public final Object setProgress(@NotNull Data data, @NotNull Continuation<? super Unit> continuation) throws Throwable {
        Object result;
        ListenableFuture<Void> progressAsync = setProgressAsync(data);
        Intrinsics.checkNotNullExpressionValue(progressAsync, "setProgressAsync(data)");
        if (progressAsync.isDone()) {
            try {
                result = progressAsync.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            progressAsync.addListener(new ListenableFutureKt$await$2$1(cancellableContinuationImpl, progressAsync), DirectExecutor.INSTANCE);
            cancellableContinuationImpl.invokeOnCancellation(new ListenableFutureKt$await$2$2(progressAsync));
            result = cancellableContinuationImpl.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    @Override // androidx.work.ListenableWorker
    @NotNull
    public final ListenableFuture<ListenableWorker.Result> startWork() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(getCoroutineContext().plus(this.job)), null, null, new C00511(null), 3, null);
        return this.future;
    }

    @Deprecated(message = "use withContext(...) inside doWork() instead.")
    public static /* synthetic */ void getCoroutineContext$annotations() {
    }
}
