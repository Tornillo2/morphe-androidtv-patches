package androidx.work;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WorkInfo {

    @NonNull
    public UUID mId;

    @NonNull
    public Data mOutputData;

    @NonNull
    public Data mProgress;
    public int mRunAttemptCount;

    @NonNull
    public State mState;

    @NonNull
    public Set<String> mTags;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        public boolean isFinished() {
            return this == SUCCEEDED || this == FAILED || this == CANCELLED;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkInfo(@NonNull UUID id, @NonNull State state, @NonNull Data outputData, @NonNull List<String> tags, @NonNull Data progress, int runAttemptCount) {
        this.mId = id;
        this.mState = state;
        this.mOutputData = outputData;
        this.mTags = new HashSet(tags);
        this.mProgress = progress;
        this.mRunAttemptCount = runAttemptCount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || WorkInfo.class != o.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) o;
        if (this.mRunAttemptCount == workInfo.mRunAttemptCount && this.mId.equals(workInfo.mId) && this.mState == workInfo.mState && this.mOutputData.equals(workInfo.mOutputData) && this.mTags.equals(workInfo.mTags)) {
            return this.mProgress.equals(workInfo.mProgress);
        }
        return false;
    }

    @NonNull
    public UUID getId() {
        return this.mId;
    }

    @NonNull
    public Data getOutputData() {
        return this.mOutputData;
    }

    @NonNull
    public Data getProgress() {
        return this.mProgress;
    }

    @IntRange(from = 0)
    public int getRunAttemptCount() {
        return this.mRunAttemptCount;
    }

    @NonNull
    public State getState() {
        return this.mState;
    }

    @NonNull
    public Set<String> getTags() {
        return this.mTags;
    }

    public int hashCode() {
        return ((this.mProgress.hashCode() + ((this.mTags.hashCode() + ((this.mOutputData.hashCode() + ((this.mState.hashCode() + (this.mId.hashCode() * 31)) * 31)) * 31)) * 31)) * 31) + this.mRunAttemptCount;
    }

    public String toString() {
        return "WorkInfo{mId='" + this.mId + "', mState=" + this.mState + ", mOutputData=" + this.mOutputData + ", mTags=" + this.mTags + ", mProgress=" + this.mProgress + '}';
    }
}
