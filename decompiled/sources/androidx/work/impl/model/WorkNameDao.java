package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
public interface WorkNameDao {
    @NonNull
    @Query("SELECT name FROM workname WHERE work_spec_id=:workSpecId")
    List<String> getNamesForWorkSpecId(@NonNull String workSpecId);

    @Query("SELECT work_spec_id FROM workname WHERE name=:name")
    List<String> getWorkSpecIdsWithName(String name);

    @Insert(onConflict = 5)
    void insert(WorkName workName);
}
