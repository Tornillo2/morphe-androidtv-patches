package com.google.android.exoplayer2.upstream.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.WorkerThread;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.database.DatabaseIOException;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.VersionTable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CacheFileMetadataIndex {
    public static final String[] COLUMNS = {"name", "length", "last_touch_timestamp"};
    public static final int COLUMN_INDEX_LAST_TOUCH_TIMESTAMP = 2;
    public static final int COLUMN_INDEX_LENGTH = 1;
    public static final int COLUMN_INDEX_NAME = 0;
    public static final String COLUMN_LAST_TOUCH_TIMESTAMP = "last_touch_timestamp";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_NAME = "name";
    public static final String TABLE_PREFIX = "ExoPlayerCacheFileMetadata";
    public static final String TABLE_SCHEMA = "(name TEXT PRIMARY KEY NOT NULL,length INTEGER NOT NULL,last_touch_timestamp INTEGER NOT NULL)";
    public static final int TABLE_VERSION = 1;
    public static final String WHERE_NAME_EQUALS = "name = ?";
    public final DatabaseProvider databaseProvider;
    public String tableName;

    public CacheFileMetadataIndex(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    @WorkerThread
    public static void delete(DatabaseProvider databaseProvider, long j) throws DatabaseIOException {
        String hexString = Long.toHexString(j);
        try {
            String tableName = getTableName(hexString);
            SQLiteDatabase writableDatabase = databaseProvider.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                VersionTable.removeVersion(writableDatabase, 2, hexString);
                dropTable(writableDatabase, tableName);
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
    }

    public static String getTableName(String str) {
        return RemoteInput$$ExternalSyntheticOutline0.m("ExoPlayerCacheFileMetadata", str);
    }

    @WorkerThread
    public Map<String, CacheFileMetadata> getAll() throws DatabaseIOException {
        try {
            Cursor cursor = getCursor();
            try {
                HashMap map = new HashMap(cursor.getCount());
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    string.getClass();
                    map.put(string, new CacheFileMetadata(cursor.getLong(1), cursor.getLong(2)));
                }
                cursor.close();
                return map;
            } finally {
            }
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    public final Cursor getCursor() {
        this.tableName.getClass();
        return this.databaseProvider.getReadableDatabase().query(this.tableName, COLUMNS, null, null, null, null, null);
    }

    @WorkerThread
    public void initialize(long j) throws DatabaseIOException {
        try {
            String hexString = Long.toHexString(j);
            this.tableName = getTableName(hexString);
            if (VersionTable.getVersion(this.databaseProvider.getReadableDatabase(), 2, hexString) != 1) {
                SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    VersionTable.setVersion(writableDatabase, 2, hexString, 1);
                    dropTable(writableDatabase, this.tableName);
                    writableDatabase.execSQL("CREATE TABLE " + this.tableName + " (name TEXT PRIMARY KEY NOT NULL,length INTEGER NOT NULL,last_touch_timestamp INTEGER NOT NULL)");
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @WorkerThread
    public void remove(String str) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            this.databaseProvider.getWritableDatabase().delete(this.tableName, "name = ?", new String[]{str});
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @WorkerThread
    public void removeAll(Set<String> set) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    writableDatabase.delete(this.tableName, "name = ?", new String[]{it.next()});
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @WorkerThread
    public void set(String str, long j, long j2) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put("length", Long.valueOf(j));
            contentValues.put("last_touch_timestamp", Long.valueOf(j2));
            writableDatabase.replaceOrThrow(this.tableName, null, contentValues);
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }
}
