package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
public interface SystemIdInfoDao {
    @Nullable
    @Query("SELECT * FROM SystemIdInfo WHERE work_spec_id=:workSpecId")
    SystemIdInfo getSystemIdInfo(@NonNull String workSpecId);

    @NonNull
    @Query("SELECT DISTINCT work_spec_id FROM SystemIdInfo")
    List<String> getWorkSpecIds();

    @Insert(onConflict = 1)
    void insertSystemIdInfo(@NonNull SystemIdInfo systemIdInfo);

    @Query("DELETE FROM SystemIdInfo where work_spec_id=:workSpecId")
    void removeSystemIdInfo(@NonNull String workSpecId);
}
