package androidx.work.multiprocess;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.OneTimeWorkRequest;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class RemoteWorkContinuation {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteWorkContinuation() {
    }

    @NonNull
    public static RemoteWorkContinuation combine(@NonNull List<RemoteWorkContinuation> continuations) {
        return continuations.get(0).combineInternal(continuations);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract RemoteWorkContinuation combineInternal(@NonNull List<RemoteWorkContinuation> continuations);

    @NonNull
    public abstract ListenableFuture<Void> enqueue();

    @NonNull
    public final RemoteWorkContinuation then(@NonNull OneTimeWorkRequest work) {
        return then(Collections.singletonList(work));
    }

    @NonNull
    public abstract RemoteWorkContinuation then(@NonNull List<OneTimeWorkRequest> work);
}
