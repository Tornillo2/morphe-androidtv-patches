package androidx.room.util;

import android.database.Cursor;
import androidx.annotation.RestrictTo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FtsTableInfo {
    public static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public FtsTableInfo(String str, Set<String> set, Set<String> set2) {
        this.name = str;
        this.columns = set;
        this.options = set2;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0087  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Set<java.lang.String> parseOptions(java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.FtsTableInfo.parseOptions(java.lang.String):java.util.Set");
    }

    public static FtsTableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return new FtsTableInfo(str, readColumns(supportSQLiteDatabase, str), readOptions(supportSQLiteDatabase, str));
    }

    public static Set<String> readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor cursorQuery = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        HashSet hashSet = new HashSet();
        try {
            if (cursorQuery.getColumnCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("name");
                while (cursorQuery.moveToNext()) {
                    hashSet.add(cursorQuery.getString(columnIndex));
                }
            }
            return hashSet;
        } finally {
            cursorQuery.close();
        }
    }

    public static Set<String> readOptions(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor cursorQuery = supportSQLiteDatabase.query("SELECT * FROM sqlite_master WHERE `name` = '" + str + "'");
        try {
            String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("sql")) : "";
            cursorQuery.close();
            return parseOptions(string);
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            FtsTableInfo ftsTableInfo = (FtsTableInfo) obj;
            String str = this.name;
            if (str == null ? ftsTableInfo.name != null : !str.equals(ftsTableInfo.name)) {
                return false;
            }
            Set<String> set = this.columns;
            if (set == null ? ftsTableInfo.columns != null : !set.equals(ftsTableInfo.columns)) {
                return false;
            }
            Set<String> set2 = this.options;
            Set<String> set3 = ftsTableInfo.options;
            if (set2 != null) {
                return set2.equals(set3);
            }
            if (set3 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Set<String> set = this.columns;
        int iHashCode2 = (iHashCode + (set != null ? set.hashCode() : 0)) * 31;
        Set<String> set2 = this.options;
        return iHashCode2 + (set2 != null ? set2.hashCode() : 0);
    }

    public String toString() {
        return "FtsTableInfo{name='" + this.name + "', columns=" + this.columns + ", options=" + this.options + '}';
    }

    public FtsTableInfo(String str, Set<String> set, String str2) {
        this.name = str;
        this.columns = set;
        this.options = parseOptions(str2);
    }
}
