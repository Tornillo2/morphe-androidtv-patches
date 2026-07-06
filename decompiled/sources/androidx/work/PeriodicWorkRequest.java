package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.WorkRequest;
import j$.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PeriodicWorkRequest extends WorkRequest {

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval));
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public Builder getThis() {
            return this;
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public PeriodicWorkRequest buildInternal() {
            if (this.mBackoffCriteriaSet && Build.VERSION.SDK_INT >= 23 && this.mWorkSpec.constraints.mRequiresDeviceIdle) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            if (this.mWorkSpec.expedited) {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
            return new PeriodicWorkRequest(this);
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public WorkRequest.Builder getThis() {
            return this;
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis());
        }

        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit, long flexInterval, @NonNull TimeUnit flexIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval), flexIntervalTimeUnit.toMillis(flexInterval));
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval, @NonNull Duration flexInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis(), flexInterval.toMillis());
        }
    }

    public PeriodicWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }
}
