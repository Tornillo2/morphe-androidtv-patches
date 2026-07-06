package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class JobCancellationException extends CancellationException implements CopyableThrowable<JobCancellationException> {

    @JvmField
    @NotNull
    public final transient Job job;

    public JobCancellationException(@NotNull String str, @Nullable Throwable th, @NotNull Job job) {
        super(str);
        this.job = job;
        if (th != null) {
            initCause(th);
        }
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    public /* bridge */ /* synthetic */ Throwable createCopy() {
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JobCancellationException)) {
            return false;
        }
        JobCancellationException jobCancellationException = (JobCancellationException) obj;
        return Intrinsics.areEqual(jobCancellationException.getMessage(), getMessage()) && Intrinsics.areEqual(jobCancellationException.job, this.job) && Intrinsics.areEqual(jobCancellationException.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    @NotNull
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        Intrinsics.checkNotNull(message);
        int iHashCode = (this.job.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    @NotNull
    public String toString() {
        return super.toString() + "; job=" + this.job;
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    @Nullable
    public JobCancellationException createCopy() {
        return null;
    }
}
