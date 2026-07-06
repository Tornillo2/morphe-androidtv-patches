package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class MoreExecutors$$ExternalSyntheticLambda1 implements Executor {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ Supplier f$1;

    public /* synthetic */ MoreExecutors$$ExternalSyntheticLambda1(Executor executor, Supplier supplier) {
        this.f$0 = executor;
        this.f$1 = supplier;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f$0.execute(Callables.threadRenaming(runnable, (Supplier<String>) this.f$1));
    }
}
