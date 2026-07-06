package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.impl.model.WorkSpec;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
public interface RawWorkInfoDao {
    @NonNull
    @RawQuery(observedEntities = {WorkSpec.class})
    List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(@NonNull SupportSQLiteQuery query);

    @NonNull
    @RawQuery(observedEntities = {WorkSpec.class})
    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(@NonNull SupportSQLiteQuery query);
}
