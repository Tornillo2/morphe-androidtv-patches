package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class NonCancellable extends AbstractCoroutineContextElement implements Job {

    @NotNull
    public static final NonCancellable INSTANCE = new NonCancellable();

    @NotNull
    public static final String message = "NonCancellable can be used only as an argument for 'withContext', direct usages of its API are prohibited";

    public NonCancellable() {
        super(Job.Key);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    @NotNull
    public ChildHandle attachChild(@NotNull ChildJob childJob) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    @NotNull
    public CancellationException getCancellationException() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public Sequence<Job> getChildren() {
        return EmptySequence.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public SelectClause0 getOnJoin() {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    @NotNull
    public DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCompleted() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    @Nullable
    public Object join(@NotNull Continuation<? super Unit> continuation) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public boolean start() {
        return false;
    }

    @NotNull
    public String toString() {
        return "NonCancellable";
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public void cancel(@Nullable CancellationException cancellationException) {
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    @NotNull
    public DisposableHandle invokeOnCompletion(boolean z, boolean z2, @NotNull Function1<? super Throwable, Unit> function1) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable th) {
        return false;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public static /* synthetic */ void getChildren$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public static /* synthetic */ void getOnJoin$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public static /* synthetic */ void isActive$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public static /* synthetic */ void isCancelled$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = message)
    public static /* synthetic */ void isCompleted$annotations() {
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public Job plus(@NotNull Job job) {
        return job;
    }
}
