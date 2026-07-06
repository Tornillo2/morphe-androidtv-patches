package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Entity
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Preference {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "key")
    public String mKey;

    @Nullable
    @ColumnInfo(name = "long_value")
    public Long mValue;

    public Preference(@NonNull String key, boolean value) {
        this(key, value ? 1L : 0L);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Preference)) {
            return false;
        }
        Preference preference = (Preference) o;
        if (!this.mKey.equals(preference.mKey)) {
            return false;
        }
        Long l = this.mValue;
        Long l2 = preference.mValue;
        return l != null ? l.equals(l2) : l2 == null;
    }

    public int hashCode() {
        int iHashCode = this.mKey.hashCode() * 31;
        Long l = this.mValue;
        return iHashCode + (l != null ? l.hashCode() : 0);
    }

    public Preference(@NonNull String key, long value) {
        this.mKey = key;
        this.mValue = Long.valueOf(value);
    }
}
