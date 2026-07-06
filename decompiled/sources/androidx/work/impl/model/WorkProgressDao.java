package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.work.Data;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface WorkProgressDao {
    @Query("DELETE from WorkProgress where work_spec_id=:workSpecId")
    void delete(@NonNull String workSpecId);

    @Query("DELETE FROM WorkProgress")
    void deleteAll();

    @Nullable
    @Query("SELECT progress FROM WorkProgress WHERE work_spec_id=:workSpecId")
    Data getProgressForWorkSpecId(@NonNull String workSpecId);

    @NonNull
    @Query("SELECT progress FROM WorkProgress WHERE work_spec_id IN (:workSpecIds)")
    List<Data> getProgressForWorkSpecIds(@NonNull List<String> workSpecIds);

    @Insert(onConflict = 1)
    void insert(@NonNull WorkProgress progress);
}
