package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.work.Data;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkProgress {

    @NonNull
    @ColumnInfo(name = NotificationCompat.CATEGORY_PROGRESS)
    public final Data mProgress;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "work_spec_id")
    public final String mWorkSpecId;

    public WorkProgress(@NonNull String workSpecId, @NonNull Data progress) {
        this.mWorkSpecId = workSpecId;
        this.mProgress = progress;
    }
}
