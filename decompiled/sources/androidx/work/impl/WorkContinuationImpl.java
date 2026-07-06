package androidx.work.impl;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import androidx.work.ArrayCreatingInputMerger;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.workers.CombineContinuationsWorker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkContinuationImpl extends WorkContinuation {
    public static final String TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    public final List<String> mAllIds;
    public boolean mEnqueued;
    public final ExistingWorkPolicy mExistingWorkPolicy;
    public final List<String> mIds;
    public final String mName;
    public Operation mOperation;
    public final List<WorkContinuationImpl> mParents;
    public final List<? extends WorkRequest> mWork;
    public final WorkManagerImpl mWorkManagerImpl;

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @Nullable String name, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends WorkRequest> work, @Nullable List<WorkContinuationImpl> parents) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mName = name;
        this.mExistingWorkPolicy = existingWorkPolicy;
        this.mWork = work;
        this.mParents = parents;
        this.mIds = new ArrayList(work.size());
        this.mAllIds = new ArrayList();
        if (parents != null) {
            Iterator<WorkContinuationImpl> it = parents.iterator();
            while (it.hasNext()) {
                this.mAllIds.addAll(it.next().mAllIds);
            }
        }
        for (int i = 0; i < work.size(); i++) {
            String stringId = work.get(i).getStringId();
            this.mIds.add(stringId);
            this.mAllIds.add(stringId);
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Set<String> prerequisitesFor(WorkContinuationImpl continuation) {
        HashSet hashSet = new HashSet();
        List<WorkContinuationImpl> parents = continuation.getParents();
        if (parents != null && !parents.isEmpty()) {
            Iterator<WorkContinuationImpl> it = parents.iterator();
            while (it.hasNext()) {
                hashSet.addAll(it.next().getIds());
            }
        }
        return hashSet;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public WorkContinuation combineInternal(@NonNull List<WorkContinuation> continuations) {
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(CombineContinuationsWorker.class);
        builder.mWorkSpec.inputMergerClassName = ArrayCreatingInputMerger.class.getName();
        OneTimeWorkRequest oneTimeWorkRequestBuild = builder.build();
        ArrayList arrayList = new ArrayList(continuations.size());
        Iterator<WorkContinuation> it = continuations.iterator();
        while (it.hasNext()) {
            arrayList.add((WorkContinuationImpl) it.next());
        }
        return new WorkContinuationImpl(this.mWorkManagerImpl, null, ExistingWorkPolicy.KEEP, Collections.singletonList(oneTimeWorkRequestBuild), arrayList);
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public Operation enqueue() {
        if (this.mEnqueued) {
            Logger.get().warning(TAG, String.format("Already enqueued work ids (%s)", TextUtils.join(", ", this.mIds)), new Throwable[0]);
        } else {
            EnqueueRunnable enqueueRunnable = new EnqueueRunnable(this);
            this.mWorkManagerImpl.getWorkTaskExecutor().executeOnBackgroundThread(enqueueRunnable);
            this.mOperation = enqueueRunnable.mOperation;
        }
        return this.mOperation;
    }

    public List<String> getAllIds() {
        return this.mAllIds;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    @NonNull
    public List<String> getIds() {
        return this.mIds;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    public List<WorkContinuationImpl> getParents() {
        return this.mParents;
    }

    @NonNull
    public List<? extends WorkRequest> getWork() {
        return this.mWork;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public ListenableFuture<List<WorkInfo>> getWorkInfos() {
        StatusRunnable.AnonymousClass1 anonymousClass1 = new StatusRunnable.AnonymousClass1(this.mWorkManagerImpl, this.mAllIds);
        this.mWorkManagerImpl.getWorkTaskExecutor().executeOnBackgroundThread(anonymousClass1);
        return anonymousClass1.mFuture;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public LiveData<List<WorkInfo>> getWorkInfosLiveData() {
        return this.mWorkManagerImpl.getWorkInfosById(this.mAllIds);
    }

    @NonNull
    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasCycles() {
        return hasCycles(this, new HashSet());
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    public void markEnqueued() {
        this.mEnqueued = true;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public WorkContinuation then(@NonNull List<OneTimeWorkRequest> work) {
        return work.isEmpty() ? this : new WorkContinuationImpl(this.mWorkManagerImpl, this.mName, ExistingWorkPolicy.KEEP, work, Collections.singletonList(this));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean hasCycles(@NonNull WorkContinuationImpl continuation, @NonNull Set<String> visited) {
        visited.addAll(continuation.getIds());
        Set<String> setPrerequisitesFor = prerequisitesFor(continuation);
        Iterator<String> it = visited.iterator();
        while (it.hasNext()) {
            if (((HashSet) setPrerequisitesFor).contains(it.next())) {
                return true;
            }
        }
        List<WorkContinuationImpl> parents = continuation.getParents();
        if (parents != null && !parents.isEmpty()) {
            Iterator<WorkContinuationImpl> it2 = parents.iterator();
            while (it2.hasNext()) {
                if (hasCycles(it2.next(), visited)) {
                    return true;
                }
            }
        }
        visited.removeAll(continuation.getIds());
        return false;
    }

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @NonNull List<? extends WorkRequest> work) {
        this(workManagerImpl, null, ExistingWorkPolicy.KEEP, work, null);
    }

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @Nullable String name, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends WorkRequest> work) {
        this(workManagerImpl, name, existingWorkPolicy, work, null);
    }
}
