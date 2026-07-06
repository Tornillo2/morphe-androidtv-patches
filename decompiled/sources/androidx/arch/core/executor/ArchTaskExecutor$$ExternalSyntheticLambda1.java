package androidx.arch.core.executor;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ArchTaskExecutor$$ExternalSyntheticLambda1 implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
    }
}
