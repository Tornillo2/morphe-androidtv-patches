package androidx.work.impl.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
public interface DependencyDao {
    @Query("SELECT work_spec_id FROM dependency WHERE prerequisite_id=:id")
    List<String> getDependentWorkIds(String id);

    @Query("SELECT prerequisite_id FROM dependency WHERE work_spec_id=:id")
    List<String> getPrerequisites(String id);

    @Query("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=:id AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)")
    boolean hasCompletedAllPrerequisites(String id);

    @Query("SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=:id")
    boolean hasDependents(String id);

    @Insert(onConflict = 5)
    void insertDependency(Dependency dependency);
}
