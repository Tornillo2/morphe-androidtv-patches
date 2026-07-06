package androidx.activity;

import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFullyDrawnReporter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FullyDrawnReporter.kt\nandroidx/activity/FullyDrawnReporter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,192:1\n1#2:193\n1855#3,2:194\n*S KotlinDebug\n*F\n+ 1 FullyDrawnReporter.kt\nandroidx/activity/FullyDrawnReporter\n*L\n157#1:194,2\n*E\n"})
public final class FullyDrawnReporter {

    @NotNull
    public final Executor executor;

    @NotNull
    public final Object lock;

    @GuardedBy("lock")
    @NotNull
    public final List<Function0<Unit>> onReportCallbacks;

    @NotNull
    public final Function0<Unit> reportFullyDrawn;

    @GuardedBy("lock")
    public boolean reportPosted;

    @NotNull
    public final Runnable reportRunnable;

    @GuardedBy("lock")
    public boolean reportedFullyDrawn;

    @GuardedBy("lock")
    public int reporterCount;

    public FullyDrawnReporter(@NotNull Executor executor, @NotNull Function0<Unit> reportFullyDrawn) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(reportFullyDrawn, "reportFullyDrawn");
        this.executor = executor;
        this.reportFullyDrawn = reportFullyDrawn;
        this.lock = new Object();
        this.onReportCallbacks = new ArrayList();
        this.reportRunnable = new Runnable() { // from class: androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FullyDrawnReporter.reportRunnable$lambda$2(this.f$0);
            }
        };
    }

    public static final void reportRunnable$lambda$2(FullyDrawnReporter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        synchronized (this$0.lock) {
            this$0.reportPosted = false;
            if (this$0.reporterCount == 0 && !this$0.reportedFullyDrawn) {
                this$0.reportFullyDrawn.invoke();
                this$0.fullyDrawnReported();
            }
        }
    }

    public final void addOnReportDrawnListener(@NotNull Function0<Unit> callback) {
        boolean z;
        Intrinsics.checkNotNullParameter(callback, "callback");
        synchronized (this.lock) {
            if (this.reportedFullyDrawn) {
                z = true;
            } else {
                this.onReportCallbacks.add(callback);
                z = false;
            }
        }
        if (z) {
            callback.invoke();
        }
    }

    public final void addReporter() {
        synchronized (this.lock) {
            if (!this.reportedFullyDrawn) {
                this.reporterCount++;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void fullyDrawnReported() {
        synchronized (this.lock) {
            try {
                this.reportedFullyDrawn = true;
                Iterator<T> it = this.onReportCallbacks.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).invoke();
                }
                this.onReportCallbacks.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isFullyDrawnReported() {
        boolean z;
        synchronized (this.lock) {
            z = this.reportedFullyDrawn;
        }
        return z;
    }

    public final void postWhenReportersAreDone() {
        if (this.reportPosted || this.reporterCount != 0) {
            return;
        }
        this.reportPosted = true;
        this.executor.execute(this.reportRunnable);
    }

    public final void removeOnReportDrawnListener(@NotNull Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        synchronized (this.lock) {
            this.onReportCallbacks.remove(callback);
        }
    }

    public final void removeReporter() {
        synchronized (this.lock) {
            try {
                if (!this.reportedFullyDrawn) {
                    int i = this.reporterCount;
                    if (i <= 0) {
                        throw new IllegalStateException("removeReporter() called when all reporters have already been removed.");
                    }
                    this.reporterCount = i - 1;
                    postWhenReportersAreDone();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
