package androidx.room;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class RoomTrackingLiveData<T> extends LiveData<T> {
    public final Callable<T> mComputeFunction;
    public final InvalidationLiveDataContainer mContainer;
    public final RoomDatabase mDatabase;
    public final boolean mInTransaction;
    public final InvalidationTracker.Observer mObserver;
    public final AtomicBoolean mInvalid = new AtomicBoolean(true);
    public final AtomicBoolean mComputing = new AtomicBoolean(false);
    public final AtomicBoolean mRegisteredObserver = new AtomicBoolean(false);
    public final Runnable mRefreshRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.1
        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            boolean z;
            if (RoomTrackingLiveData.this.mRegisteredObserver.compareAndSet(false, true)) {
                RoomTrackingLiveData.this.mDatabase.getInvalidationTracker().addWeakObserver(RoomTrackingLiveData.this.mObserver);
            }
            do {
                if (RoomTrackingLiveData.this.mComputing.compareAndSet(false, true)) {
                    T tCall = null;
                    z = false;
                    while (RoomTrackingLiveData.this.mInvalid.compareAndSet(true, false)) {
                        try {
                            try {
                                tCall = RoomTrackingLiveData.this.mComputeFunction.call();
                                z = true;
                            } catch (Exception e) {
                                throw new RuntimeException("Exception while computing database live data.", e);
                            }
                        } finally {
                            RoomTrackingLiveData.this.mComputing.set(false);
                        }
                    }
                    if (z) {
                        RoomTrackingLiveData.this.postValue(tCall);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    return;
                }
            } while (RoomTrackingLiveData.this.mInvalid.get());
        }
    };
    public final Runnable mInvalidationRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.2
        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean zHasActiveObservers = RoomTrackingLiveData.this.hasActiveObservers();
            if (RoomTrackingLiveData.this.mInvalid.compareAndSet(false, true) && zHasActiveObservers) {
                RoomTrackingLiveData.this.getQueryExecutor().execute(RoomTrackingLiveData.this.mRefreshRunnable);
            }
        }
    };

    @SuppressLint({"RestrictedApi"})
    public RoomTrackingLiveData(RoomDatabase roomDatabase, InvalidationLiveDataContainer invalidationLiveDataContainer, boolean z, Callable<T> callable, String[] strArr) {
        this.mDatabase = roomDatabase;
        this.mInTransaction = z;
        this.mComputeFunction = callable;
        this.mContainer = invalidationLiveDataContainer;
        this.mObserver = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.RoomTrackingLiveData.3
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.mInvalidationRunnable);
            }
        };
    }

    public Executor getQueryExecutor() {
        return this.mInTransaction ? this.mDatabase.getTransactionExecutor() : this.mDatabase.getQueryExecutor();
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        this.mContainer.onActive(this);
        getQueryExecutor().execute(this.mRefreshRunnable);
    }

    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        this.mContainer.onInactive(this);
    }
}
