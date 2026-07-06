package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Dao
public interface PreferenceDao {
    @Nullable
    @Query("SELECT long_value FROM Preference where `key`=:key")
    Long getLongValue(@NonNull String key);

    @NonNull
    @Query("SELECT long_value FROM Preference where `key`=:key")
    LiveData<Long> getObservableLongValue(@NonNull String key);

    @Insert(onConflict = 1)
    void insertPreference(@NonNull Preference preference);
}
