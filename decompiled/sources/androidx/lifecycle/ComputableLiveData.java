package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class ComputableLiveData<T> {

    @NotNull
    public final LiveData<T> _liveData;

    @NotNull
    public final AtomicBoolean computing;

    @NotNull
    public final Executor executor;

    @NotNull
    public final AtomicBoolean invalid;

    @JvmField
    @NotNull
    public final Runnable invalidationRunnable;

    @NotNull
    public final LiveData<T> liveData;

    @JvmField
    @NotNull
    public final Runnable refreshRunnable;

    @JvmOverloads
    public ComputableLiveData() {
        this(null, 1, null);
    }

    public static final void invalidationRunnable$lambda$1(ComputableLiveData this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zHasActiveObservers = this$0.getLiveData().hasActiveObservers();
        if (this$0.invalid.compareAndSet(false, true) && zHasActiveObservers) {
            this$0.executor.execute(this$0.refreshRunnable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void refreshRunnable$lambda$0(ComputableLiveData this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        do {
            boolean z = false;
            if (this$0.computing.compareAndSet(false, true)) {
                Object objCompute = null;
                boolean z2 = false;
                while (this$0.invalid.compareAndSet(true, false)) {
                    try {
                        objCompute = this$0.compute();
                        z2 = true;
                    } catch (Throwable th) {
                        this$0.computing.set(false);
                        throw th;
                    }
                }
                if (z2) {
                    this$0.getLiveData().postValue(objCompute);
                }
                this$0.computing.set(false);
                z = z2;
            }
            if (!z) {
                return;
            }
        } while (this$0.invalid.get());
    }

    @WorkerThread
    public abstract T compute();

    @NotNull
    public final AtomicBoolean getComputing$lifecycle_livedata_release() {
        return this.computing;
    }

    @NotNull
    public final Executor getExecutor$lifecycle_livedata_release() {
        return this.executor;
    }

    @NotNull
    public final AtomicBoolean getInvalid$lifecycle_livedata_release() {
        return this.invalid;
    }

    @NotNull
    public LiveData<T> getLiveData() {
        return this.liveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.invalidationRunnable);
    }

    @JvmOverloads
    public ComputableLiveData(@NotNull Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.executor = executor;
        LiveData<T> liveData = new LiveData<T>(this) { // from class: androidx.lifecycle.ComputableLiveData$_liveData$1
            public final /* synthetic */ ComputableLiveData<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.lifecycle.LiveData
            public void onActive() {
                ComputableLiveData<T> computableLiveData = this.this$0;
                computableLiveData.executor.execute(computableLiveData.refreshRunnable);
            }
        };
        this._liveData = liveData;
        this.liveData = liveData;
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.refreshRunnable = new Runnable() { // from class: androidx.lifecycle.ComputableLiveData$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ComputableLiveData.refreshRunnable$lambda$0(this.f$0);
            }
        };
        this.invalidationRunnable = new Runnable() { // from class: androidx.lifecycle.ComputableLiveData$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ComputableLiveData.invalidationRunnable$lambda$1(this.f$0);
            }
        };
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ComputableLiveData(Executor executor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            executor = ArchTaskExecutor.sIOThreadExecutor;
            Intrinsics.checkNotNullExpressionValue(executor, "getIOThreadExecutor()");
        }
        this(executor);
    }

    @VisibleForTesting
    public static /* synthetic */ void getInvalidationRunnable$lifecycle_livedata_release$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getRefreshRunnable$lifecycle_livedata_release$annotations() {
    }
}
