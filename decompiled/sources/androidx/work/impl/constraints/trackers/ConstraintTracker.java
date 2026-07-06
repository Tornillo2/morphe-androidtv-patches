package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ConstraintTracker<T> {
    public static final String TAG = Logger.tagWithPrefix("ConstraintTracker");
    public final Context mAppContext;
    public T mCurrentState;
    public final TaskExecutor mTaskExecutor;
    public final Object mLock = new Object();
    public final Set<ConstraintListener<T>> mListeners = new LinkedHashSet();

    public ConstraintTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        this.mAppContext = context.getApplicationContext();
        this.mTaskExecutor = taskExecutor;
    }

    public void addListener(ConstraintListener<T> listener) {
        synchronized (this.mLock) {
            try {
                if (this.mListeners.add(listener)) {
                    if (this.mListeners.size() == 1) {
                        this.mCurrentState = getInitialState();
                        Logger.get().debug(TAG, String.format("%s: initial state = %s", getClass().getSimpleName(), this.mCurrentState), new Throwable[0]);
                        startTracking();
                    }
                    listener.onConstraintChanged(this.mCurrentState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract T getInitialState();

    public void removeListener(ConstraintListener<T> listener) {
        synchronized (this.mLock) {
            try {
                if (this.mListeners.remove(listener) && this.mListeners.isEmpty()) {
                    stopTracking();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setState(T newState) {
        synchronized (this.mLock) {
            try {
                T t = this.mCurrentState;
                if (t != newState && (t == null || !t.equals(newState))) {
                    this.mCurrentState = newState;
                    final ArrayList arrayList = new ArrayList(this.mListeners);
                    this.mTaskExecutor.getMainThreadExecutor().execute(new Runnable() { // from class: androidx.work.impl.constraints.trackers.ConstraintTracker.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((ConstraintListener) it.next()).onConstraintChanged(ConstraintTracker.this.mCurrentState);
                            }
                        }
                    });
                }
            } finally {
            }
        }
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}
