package androidx.room;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class RoomMasterTable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDENTITY_HASH = "identity_hash";
    public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)";
    public static final String DEFAULT_ID = "42";
    public static final String NAME = "room_master_table";
    public static final String READ_QUERY = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1";
    public static final String TABLE_NAME = "room_master_table";

    public static String createInsertQuery(String str) {
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '", str, "')");
    }
}
