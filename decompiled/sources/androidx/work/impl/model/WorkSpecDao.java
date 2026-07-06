package androidx.work.impl.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
@SuppressLint({"UnknownNullness"})
public interface WorkSpecDao {
    @Query("DELETE FROM workspec WHERE id=:id")
    void delete(String id);

    @Query("SELECT * FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT :maxLimit")
    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int maxLimit);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)")
    List<String> getAllUnfinishedWork();

    @Query("SELECT id FROM workspec")
    List<String> getAllWorkSpecIds();

    @Query("SELECT id FROM workspec")
    @Transaction
    LiveData<List<String>> getAllWorkSpecIdsLiveData();

    @Query("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(:schedulerLimit-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))")
    List<WorkSpec> getEligibleWorkForScheduling(int schedulerLimit);

    @Query("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=:id)")
    List<Data> getInputsFromPrerequisites(String id);

    @Query("SELECT * FROM workspec WHERE period_start_time >= :startingAt AND state IN (2, 3, 5) ORDER BY period_start_time DESC")
    List<WorkSpec> getRecentlyCompletedWork(long startingAt);

    @Query("SELECT * FROM workspec WHERE state=1")
    List<WorkSpec> getRunningWork();

    @Query("SELECT schedule_requested_at FROM workspec WHERE id=:id")
    LiveData<Long> getScheduleRequestedAtLiveData(@NonNull String id);

    @Query("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1")
    List<WorkSpec> getScheduledWork();

    @Query("SELECT state FROM workspec WHERE id=:id")
    WorkInfo.State getState(String id);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    List<String> getUnfinishedWorkWithName(@NonNull String name);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    List<String> getUnfinishedWorkWithTag(@NonNull String tag);

    @Query("SELECT * FROM workspec WHERE id=:id")
    WorkSpec getWorkSpec(String id);

    @Query("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String name);

    @Query("SELECT * FROM workspec WHERE id IN (:ids)")
    WorkSpec[] getWorkSpecs(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id=:id")
    @Transaction
    WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String id);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (:ids)")
    @Transaction
    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    @Transaction
    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String name);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    @Transaction
    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String tag);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (:ids)")
    @Transaction
    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    @Transaction
    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(String name);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    @Transaction
    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(String tag);

    @Query("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1")
    boolean hasUnfinishedWork();

    @Query("UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=:id")
    int incrementWorkSpecRunAttemptCount(String id);

    @Insert(onConflict = 5)
    void insertWorkSpec(WorkSpec workSpec);

    @Query("UPDATE workspec SET schedule_requested_at=:startTime WHERE id=:id")
    int markWorkSpecScheduled(@NonNull String id, long startTime);

    @Query("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))")
    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    @Query("UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)")
    int resetScheduledState();

    @Query("UPDATE workspec SET run_attempt_count=0 WHERE id=:id")
    int resetWorkSpecRunAttemptCount(String id);

    @Query("UPDATE workspec SET output=:output WHERE id=:id")
    void setOutput(String id, Data output);

    @Query("UPDATE workspec SET period_start_time=:periodStartTime WHERE id=:id")
    void setPeriodStartTime(String id, long periodStartTime);

    @Query("UPDATE workspec SET state=:state WHERE id IN (:ids)")
    int setState(WorkInfo.State state, String... ids);
}
