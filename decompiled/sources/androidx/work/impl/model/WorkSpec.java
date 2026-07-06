package androidx.work.impl.model;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Entity(indices = {@Index({"schedule_requested_at"}), @Index({"period_start_time"})})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class WorkSpec {
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1;
    public static final String TAG = Logger.tagWithPrefix("WorkSpec");
    public static final Function<List<WorkInfoPojo>, List<WorkInfo>> WORK_INFO_MAPPER = new AnonymousClass1();

    @ColumnInfo(name = "backoff_delay_duration")
    public long backoffDelayDuration;

    @NonNull
    @ColumnInfo(name = "backoff_policy")
    public BackoffPolicy backoffPolicy;

    @NonNull
    @Embedded
    public Constraints constraints;

    @ColumnInfo(name = "run_in_foreground")
    public boolean expedited;

    @ColumnInfo(name = "flex_duration")
    public long flexDuration;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "initial_delay")
    public long initialDelay;

    @NonNull
    @ColumnInfo(name = TvContractCompat.PARAM_INPUT)
    public Data input;

    @ColumnInfo(name = "input_merger_class_name")
    public String inputMergerClassName;

    @ColumnInfo(name = "interval_duration")
    public long intervalDuration;

    @ColumnInfo(name = "minimum_retention_duration")
    public long minimumRetentionDuration;

    @NonNull
    @ColumnInfo(name = "out_of_quota_policy")
    public OutOfQuotaPolicy outOfQuotaPolicy;

    @NonNull
    @ColumnInfo(name = "output")
    public Data output;

    @ColumnInfo(name = "period_start_time")
    public long periodStartTime;

    @IntRange(from = 0)
    @ColumnInfo(name = "run_attempt_count")
    public int runAttemptCount;

    @ColumnInfo(name = "schedule_requested_at")
    public long scheduleRequestedAt;

    @NonNull
    @ColumnInfo(name = "state")
    public WorkInfo.State state;

    @NonNull
    @ColumnInfo(name = "worker_class_name")
    public String workerClassName;

    /* JADX INFO: renamed from: androidx.work.impl.model.WorkSpec$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Function<List<WorkInfoPojo>, List<WorkInfo>> {
        @Override // androidx.arch.core.util.Function
        public List<WorkInfo> apply(List<WorkInfoPojo> input) {
            if (input == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(input.size());
            Iterator<WorkInfoPojo> it = input.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toWorkInfo());
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IdAndState {

        @ColumnInfo(name = "id")
        public String id;

        @ColumnInfo(name = "state")
        public WorkInfo.State state;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) o;
            if (this.state != idAndState.state) {
                return false;
            }
            return this.id.equals(idAndState.id);
        }

        public int hashCode() {
            return this.state.hashCode() + (this.id.hashCode() * 31);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class WorkInfoPojo {

        @ColumnInfo(name = "id")
        public String id;

        @ColumnInfo(name = "output")
        public Data output;

        @Relation(entity = WorkProgress.class, entityColumn = "work_spec_id", parentColumn = "id", projection = {NotificationCompat.CATEGORY_PROGRESS})
        public List<Data> progress;

        @ColumnInfo(name = "run_attempt_count")
        public int runAttemptCount;

        @ColumnInfo(name = "state")
        public WorkInfo.State state;

        @Relation(entity = WorkTag.class, entityColumn = "work_spec_id", parentColumn = "id", projection = {"tag"})
        public List<String> tags;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof WorkInfoPojo)) {
                return false;
            }
            WorkInfoPojo workInfoPojo = (WorkInfoPojo) o;
            if (this.runAttemptCount != workInfoPojo.runAttemptCount) {
                return false;
            }
            String str = this.id;
            if (str == null ? workInfoPojo.id != null : !str.equals(workInfoPojo.id)) {
                return false;
            }
            if (this.state != workInfoPojo.state) {
                return false;
            }
            Data data = this.output;
            if (data == null ? workInfoPojo.output != null : !data.equals(workInfoPojo.output)) {
                return false;
            }
            List<String> list = this.tags;
            if (list == null ? workInfoPojo.tags != null : !list.equals(workInfoPojo.tags)) {
                return false;
            }
            List<Data> list2 = this.progress;
            List<Data> list3 = workInfoPojo.progress;
            return list2 != null ? list2.equals(list3) : list3 == null;
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            WorkInfo.State state = this.state;
            int iHashCode2 = (iHashCode + (state != null ? state.hashCode() : 0)) * 31;
            Data data = this.output;
            int iHashCode3 = (((iHashCode2 + (data != null ? data.hashCode() : 0)) * 31) + this.runAttemptCount) * 31;
            List<String> list = this.tags;
            int iHashCode4 = (iHashCode3 + (list != null ? list.hashCode() : 0)) * 31;
            List<Data> list2 = this.progress;
            return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
        }

        @NonNull
        public WorkInfo toWorkInfo() {
            List<Data> list = this.progress;
            return new WorkInfo(UUID.fromString(this.id), this.state, this.output, this.tags, (list == null || list.isEmpty()) ? Data.EMPTY : this.progress.get(0), this.runAttemptCount);
        }
    }

    public WorkSpec(@NonNull String id, @NonNull String workerClassName) {
        this.state = WorkInfo.State.ENQUEUED;
        Data data = Data.EMPTY;
        this.input = data;
        this.output = data;
        this.constraints = Constraints.NONE;
        this.backoffPolicy = BackoffPolicy.EXPONENTIAL;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        this.id = id;
        this.workerClassName = workerClassName;
    }

    public long calculateNextRunTime() {
        if (isBackedOff()) {
            return Math.min(WorkRequest.MAX_BACKOFF_MILLIS, this.backoffPolicy == BackoffPolicy.LINEAR ? this.backoffDelayDuration * ((long) this.runAttemptCount) : (long) Math.scalb(this.backoffDelayDuration, this.runAttemptCount - 1)) + this.periodStartTime;
        }
        if (!isPeriodic()) {
            long jCurrentTimeMillis = this.periodStartTime;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            return jCurrentTimeMillis + this.initialDelay;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        long j = this.periodStartTime;
        long j2 = j == 0 ? jCurrentTimeMillis2 + this.initialDelay : j;
        long j3 = this.flexDuration;
        long j4 = this.intervalDuration;
        if (j3 != j4) {
            return j2 + j4 + (j == 0 ? j3 * (-1) : 0L);
        }
        return j2 + (j != 0 ? j4 : 0L);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && WorkSpec.class == o.getClass()) {
            WorkSpec workSpec = (WorkSpec) o;
            if (this.initialDelay != workSpec.initialDelay || this.intervalDuration != workSpec.intervalDuration || this.flexDuration != workSpec.flexDuration || this.runAttemptCount != workSpec.runAttemptCount || this.backoffDelayDuration != workSpec.backoffDelayDuration || this.periodStartTime != workSpec.periodStartTime || this.minimumRetentionDuration != workSpec.minimumRetentionDuration || this.scheduleRequestedAt != workSpec.scheduleRequestedAt || this.expedited != workSpec.expedited || !this.id.equals(workSpec.id) || this.state != workSpec.state || !this.workerClassName.equals(workSpec.workerClassName)) {
                return false;
            }
            String str = this.inputMergerClassName;
            if (str == null ? workSpec.inputMergerClassName != null : !str.equals(workSpec.inputMergerClassName)) {
                return false;
            }
            if (this.input.equals(workSpec.input) && this.output.equals(workSpec.output) && this.constraints.equals(workSpec.constraints) && this.backoffPolicy == workSpec.backoffPolicy && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy) {
                return true;
            }
        }
        return false;
    }

    public boolean hasConstraints() {
        return !Constraints.NONE.equals(this.constraints);
    }

    public int hashCode() {
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.workerClassName, (this.state.hashCode() + (this.id.hashCode() * 31)) * 31, 31);
        String str = this.inputMergerClassName;
        int iHashCode = (this.output.hashCode() + ((this.input.hashCode() + ((iM + (str != null ? str.hashCode() : 0)) * 31)) * 31)) * 31;
        long j = this.initialDelay;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.intervalDuration;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.flexDuration;
        int iHashCode2 = (this.backoffPolicy.hashCode() + ((((this.constraints.hashCode() + ((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31)) * 31) + this.runAttemptCount) * 31)) * 31;
        long j4 = this.backoffDelayDuration;
        int i3 = (iHashCode2 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.periodStartTime;
        int i4 = (i3 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        long j6 = this.minimumRetentionDuration;
        int i5 = (i4 + ((int) (j6 ^ (j6 >>> 32)))) * 31;
        long j7 = this.scheduleRequestedAt;
        return this.outOfQuotaPolicy.hashCode() + ((((i5 + ((int) (j7 ^ (j7 >>> 32)))) * 31) + (this.expedited ? 1 : 0)) * 31);
    }

    public boolean isBackedOff() {
        return this.state == WorkInfo.State.ENQUEUED && this.runAttemptCount > 0;
    }

    public boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public void setBackoffDelayDuration(long backoffDelayDuration) {
        if (backoffDelayDuration > WorkRequest.MAX_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration exceeds maximum value", new Throwable[0]);
            backoffDelayDuration = 18000000;
        }
        if (backoffDelayDuration < 10000) {
            Logger.get().warning(TAG, "Backoff delay duration less than minimum value", new Throwable[0]);
            backoffDelayDuration = 10000;
        }
        this.backoffDelayDuration = backoffDelayDuration;
    }

    public void setPeriodic(long intervalDuration) {
        if (intervalDuration < 900000) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", 900000L), new Throwable[0]);
            intervalDuration = 900000;
        }
        setPeriodic(intervalDuration, intervalDuration);
    }

    @NonNull
    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("{WorkSpec: "), this.id, "}");
    }

    public void setPeriodic(long intervalDuration, long flexDuration) {
        if (intervalDuration < 900000) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", 900000L), new Throwable[0]);
            intervalDuration = 900000;
        }
        if (flexDuration < 300000) {
            Logger.get().warning(TAG, String.format("Flex duration lesser than minimum allowed value; Changed to %s", 300000L), new Throwable[0]);
            flexDuration = 300000;
        }
        if (flexDuration > intervalDuration) {
            Logger.get().warning(TAG, String.format("Flex duration greater than interval duration; Changed to %s", Long.valueOf(intervalDuration)), new Throwable[0]);
            flexDuration = intervalDuration;
        }
        this.intervalDuration = intervalDuration;
        this.flexDuration = flexDuration;
    }

    public WorkSpec(@NonNull WorkSpec other) {
        this.state = WorkInfo.State.ENQUEUED;
        Data data = Data.EMPTY;
        this.input = data;
        this.output = data;
        this.constraints = Constraints.NONE;
        this.backoffPolicy = BackoffPolicy.EXPONENTIAL;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        this.id = other.id;
        this.workerClassName = other.workerClassName;
        this.state = other.state;
        this.inputMergerClassName = other.inputMergerClassName;
        this.input = new Data(other.input);
        this.output = new Data(other.output);
        this.initialDelay = other.initialDelay;
        this.intervalDuration = other.intervalDuration;
        this.flexDuration = other.flexDuration;
        this.constraints = new Constraints(other.constraints);
        this.runAttemptCount = other.runAttemptCount;
        this.backoffPolicy = other.backoffPolicy;
        this.backoffDelayDuration = other.backoffDelayDuration;
        this.periodStartTime = other.periodStartTime;
        this.minimumRetentionDuration = other.minimumRetentionDuration;
        this.scheduleRequestedAt = other.scheduleRequestedAt;
        this.expedited = other.expedited;
        this.outOfQuotaPolicy = other.outOfQuotaPolicy;
    }
}
