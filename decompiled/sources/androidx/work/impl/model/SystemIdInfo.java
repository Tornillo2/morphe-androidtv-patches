package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemIdInfo {

    @ColumnInfo(name = "system_id")
    public final int systemId;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "work_spec_id")
    public final String workSpecId;

    public SystemIdInfo(@NonNull String workSpecId, int systemId) {
        this.workSpecId = workSpecId;
        this.systemId = systemId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemIdInfo)) {
            return false;
        }
        SystemIdInfo systemIdInfo = (SystemIdInfo) o;
        if (this.systemId != systemIdInfo.systemId) {
            return false;
        }
        return this.workSpecId.equals(systemIdInfo.workSpecId);
    }

    public int hashCode() {
        return (this.workSpecId.hashCode() * 31) + this.systemId;
    }
}
