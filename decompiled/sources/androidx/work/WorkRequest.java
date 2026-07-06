package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import j$.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkRequest {
    public static final long DEFAULT_BACKOFF_DELAY_MILLIS = 30000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MAX_BACKOFF_MILLIS = 18000000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_BACKOFF_MILLIS = 10000;

    @NonNull
    public UUID mId;

    @NonNull
    public Set<String> mTags;

    @NonNull
    public WorkSpec mWorkSpec;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkRequest(@NonNull UUID id, @NonNull WorkSpec workSpec, @NonNull Set<String> tags) {
        this.mId = id;
        this.mWorkSpec = workSpec;
        this.mTags = tags;
    }

    @NonNull
    public UUID getId() {
        return this.mId;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getStringId() {
        return this.mId.toString();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Set<String> getTags() {
        return this.mTags;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkSpec getWorkSpec() {
        return this.mWorkSpec;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Builder<B extends Builder<?, ?>, W extends WorkRequest> {
        public WorkSpec mWorkSpec;
        public Class<? extends ListenableWorker> mWorkerClass;
        public boolean mBackoffCriteriaSet = false;
        public Set<String> mTags = new HashSet();
        public UUID mId = UUID.randomUUID();

        public Builder(@NonNull Class<? extends ListenableWorker> workerClass) {
            this.mWorkerClass = workerClass;
            this.mWorkSpec = new WorkSpec(this.mId.toString(), workerClass.getName());
            addTag(workerClass.getName());
        }

        @NonNull
        public final B addTag(@NonNull String str) {
            this.mTags.add(str);
            return (B) getThis();
        }

        @NonNull
        public final W build() {
            W w = (W) buildInternal();
            Constraints constraints = this.mWorkSpec.constraints;
            int i = Build.VERSION.SDK_INT;
            boolean z = (i >= 24 && constraints.hasContentUriTriggers()) || constraints.mRequiresBatteryNotLow || constraints.mRequiresCharging || (i >= 23 && constraints.mRequiresDeviceIdle);
            WorkSpec workSpec = this.mWorkSpec;
            if (workSpec.expedited) {
                if (z) {
                    throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
                }
                if (workSpec.initialDelay > 0) {
                    throw new IllegalArgumentException("Expedited jobs cannot be delayed");
                }
            }
            this.mId = UUID.randomUUID();
            WorkSpec workSpec2 = new WorkSpec(this.mWorkSpec);
            this.mWorkSpec = workSpec2;
            workSpec2.id = this.mId.toString();
            return w;
        }

        @NonNull
        public abstract W buildInternal();

        @NonNull
        public abstract B getThis();

        @NonNull
        public final B keepResultsForAtLeast(long j, @NonNull TimeUnit timeUnit) {
            this.mWorkSpec.minimumRetentionDuration = timeUnit.toMillis(j);
            return (B) getThis();
        }

        @NonNull
        public final B setBackoffCriteria(@NonNull BackoffPolicy backoffPolicy, long j, @NonNull TimeUnit timeUnit) {
            this.mBackoffCriteriaSet = true;
            WorkSpec workSpec = this.mWorkSpec;
            workSpec.backoffPolicy = backoffPolicy;
            workSpec.setBackoffDelayDuration(timeUnit.toMillis(j));
            return (B) getThis();
        }

        @NonNull
        public final B setConstraints(@NonNull Constraints constraints) {
            this.mWorkSpec.constraints = constraints;
            return (B) getThis();
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public B setExpedited(@NonNull OutOfQuotaPolicy outOfQuotaPolicy) {
            WorkSpec workSpec = this.mWorkSpec;
            workSpec.expedited = true;
            workSpec.outOfQuotaPolicy = outOfQuotaPolicy;
            return (B) getThis();
        }

        @NonNull
        public B setInitialDelay(long j, @NonNull TimeUnit timeUnit) {
            this.mWorkSpec.initialDelay = timeUnit.toMillis(j);
            if (Long.MAX_VALUE - System.currentTimeMillis() > this.mWorkSpec.initialDelay) {
                return (B) getThis();
            }
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }

        @NonNull
        @VisibleForTesting
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final B setInitialRunAttemptCount(int i) {
            this.mWorkSpec.runAttemptCount = i;
            return (B) getThis();
        }

        @NonNull
        @VisibleForTesting
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final B setInitialState(@NonNull WorkInfo.State state) {
            this.mWorkSpec.state = state;
            return (B) getThis();
        }

        @NonNull
        public final B setInputData(@NonNull Data data) {
            this.mWorkSpec.input = data;
            return (B) getThis();
        }

        @NonNull
        @VisibleForTesting
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final B setPeriodStartTime(long j, @NonNull TimeUnit timeUnit) {
            this.mWorkSpec.periodStartTime = timeUnit.toMillis(j);
            return (B) getThis();
        }

        @NonNull
        @VisibleForTesting
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final B setScheduleRequestedAt(long j, @NonNull TimeUnit timeUnit) {
            this.mWorkSpec.scheduleRequestedAt = timeUnit.toMillis(j);
            return (B) getThis();
        }

        @NonNull
        @RequiresApi(26)
        public final B keepResultsForAtLeast(@NonNull Duration duration) {
            this.mWorkSpec.minimumRetentionDuration = duration.toMillis();
            return (B) getThis();
        }

        @NonNull
        @RequiresApi(26)
        public final B setBackoffCriteria(@NonNull BackoffPolicy backoffPolicy, @NonNull Duration duration) {
            this.mBackoffCriteriaSet = true;
            WorkSpec workSpec = this.mWorkSpec;
            workSpec.backoffPolicy = backoffPolicy;
            workSpec.setBackoffDelayDuration(duration.toMillis());
            return (B) getThis();
        }

        @NonNull
        @RequiresApi(26)
        public B setInitialDelay(@NonNull Duration duration) {
            this.mWorkSpec.initialDelay = duration.toMillis();
            if (Long.MAX_VALUE - System.currentTimeMillis() > this.mWorkSpec.initialDelay) {
                return (B) getThis();
            }
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }
    }
}
