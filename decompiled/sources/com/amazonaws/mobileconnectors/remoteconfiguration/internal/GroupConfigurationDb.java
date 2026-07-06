package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class GroupConfigurationDb {
    public static final int COL_APP_CONFIG = 0;
    public static final int COL_ENTITY_TAG = 3;
    public static final int COL_ORIGIN = 4;
    public static final int COL_TIMESTAMP = 2;
    public static final int COL_VALUE = 1;
    public static final int DATABASE_VERSION = 2;
    public static final String DB_NAME = "GroupConfigurationCache.db";
    public static final Map<String, GroupConfigurationDb> INSTANCE_MAP = new HashMap();
    public static final String[] PROJECTION_REMOTE_CONFIGURATION = {GroupConfigurationTable.COLUMN_APP_CONFIG_ID, "json", "timestamp", "entity_tag", "origin"};
    public static final String SQL_CREATE_CONFIGURATION_TABLE = String.format(Locale.ROOT, "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER NOT NULL CHECK (%s IN (%d,%d,%d)), %s INTEGER, %s TEXT);", GroupConfigurationTable.TABLE_NAME, "_id", GroupConfigurationTable.COLUMN_APP_CONFIG_ID, "json", "origin", "origin", 1, 2, 3, "timestamp", "entity_tag");
    public static final String TAG = "GroupConfigurationDb";
    public final DBHelper dbHelper;
    public SQLiteDatabase mDatabase;
    public final AtomicInteger openCounter = new AtomicInteger(0);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class GroupConfigurationTable implements BaseColumns {
        public static final String COLUMN_APP_CONFIG_ID = "app_config_id";
        public static final String COLUMN_ENTITY_TAG = "entity_tag";
        public static final String COLUMN_JSON = "json";
        public static final String COLUMN_ORIGIN = "origin";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String TABLE_NAME = "configuration_group";
    }

    public GroupConfigurationDb(DBHelper dBHelper) {
        this.dbHelper = dBHelper;
    }

    public static GroupConfigurationDb getOrCreateInstance(Context context, String str) {
        GroupConfigurationDb groupConfigurationDb;
        Map<String, GroupConfigurationDb> map = INSTANCE_MAP;
        synchronized (map) {
            groupConfigurationDb = map.get(str);
            if (groupConfigurationDb == null) {
                DBHelper dBHelper = new DBHelper(context, str);
                try {
                    dBHelper.getWritableDatabase();
                } catch (DowngradeException unused) {
                    context.deleteDatabase(DBHelper.getDbFileNameFor(str));
                    dBHelper = new DBHelper(context, str);
                }
                GroupConfigurationDb groupConfigurationDb2 = new GroupConfigurationDb(dBHelper);
                INSTANCE_MAP.put(str, groupConfigurationDb2);
                groupConfigurationDb = groupConfigurationDb2;
            }
        }
        return groupConfigurationDb;
    }

    public final synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.openCounter.decrementAndGet() == 0 && (sQLiteDatabase = this.mDatabase) != null) {
            sQLiteDatabase.close();
        }
    }

    public final Configuration configurationFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getString(1) == null) {
            throw new ConfigurationNotFoundException("Configuration not found");
        }
        return new ConfigurationImpl(cursor.getString(1), cursor.isNull(2) ? null : new Date(cursor.getLong(2)));
    }

    public synchronized void deleteConfiguration(String str) {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                sQLiteDatabaseOpenDatabase.delete(GroupConfigurationTable.TABLE_NAME, "app_config_id = ?", new String[]{str});
                Log.d(TAG, "Deleted rows from configuration_group table");
                sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                sQLiteDatabaseOpenDatabase.endTransaction();
                closeDatabase();
            } catch (Throwable th) {
                th = th;
                if (sQLiteDatabaseOpenDatabase != null) {
                    sQLiteDatabaseOpenDatabase.endTransaction();
                }
                closeDatabase();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabaseOpenDatabase = null;
        }
    }

    public final ContentValues getContentValues(String str, Integer num, Long l, String str2) {
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put("json", str);
        } else {
            contentValues.putNull("json");
        }
        if (num != null) {
            contentValues.put("origin", num);
        } else {
            contentValues.putNull("origin");
        }
        if (l != null) {
            contentValues.put("timestamp", l);
        } else {
            contentValues.putNull("timestamp");
        }
        if (str2 != null) {
            contentValues.put("entity_tag", str2);
            return contentValues;
        }
        contentValues.putNull("entity_tag");
        return contentValues;
    }

    public final boolean hasConfiguration(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorQuery = sQLiteDatabase.query(GroupConfigurationTable.TABLE_NAME, new String[]{"_id"}, "app_config_id = ?", new String[]{str}, null, null, null, "1");
        if (cursorQuery == null) {
            return false;
        }
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public synchronized SQLiteDatabase openDatabase() {
        try {
            if (this.openCounter.incrementAndGet() == 1) {
                this.mDatabase = this.dbHelper.getWritableDatabase();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.mDatabase;
    }

    public synchronized Map<String, Configuration> readConfiguration() throws ConfigurationNotFoundException {
        HashMap map;
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        try {
            map = new HashMap();
            Cursor cursorQuery = null;
            try {
                sQLiteDatabaseOpenDatabase = openDatabase();
                try {
                    sQLiteDatabaseOpenDatabase.beginTransaction();
                    cursorQuery = sQLiteDatabaseOpenDatabase.query(GroupConfigurationTable.TABLE_NAME, PROJECTION_REMOTE_CONFIGURATION, null, null, null, null, null);
                    sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                    while (cursorQuery != null) {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        RemoteConfiguration remoteConfigurationRemoteConfigurationFromCursor = remoteConfigurationFromCursor(cursorQuery);
                        map.put(((RemoteConfigurationImpl) remoteConfigurationRemoteConfigurationFromCursor).mAppConfigurationId, ((RemoteConfigurationImpl) remoteConfigurationRemoteConfigurationFromCursor).mConfiguration);
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    sQLiteDatabaseOpenDatabase.endTransaction();
                    closeDatabase();
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (sQLiteDatabaseOpenDatabase != null) {
                        sQLiteDatabaseOpenDatabase.endTransaction();
                    }
                    closeDatabase();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabaseOpenDatabase = null;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return map;
    }

    public synchronized Map<String, RemoteConfiguration> readRemoteConfiguration() throws ConfigurationNotFoundException {
        HashMap map;
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        try {
            map = new HashMap();
            Cursor cursorQuery = null;
            try {
                sQLiteDatabaseOpenDatabase = openDatabase();
                try {
                    sQLiteDatabaseOpenDatabase.beginTransaction();
                    cursorQuery = sQLiteDatabaseOpenDatabase.query(GroupConfigurationTable.TABLE_NAME, PROJECTION_REMOTE_CONFIGURATION, null, null, null, null, null);
                    sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                    while (cursorQuery != null) {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        RemoteConfiguration remoteConfigurationRemoteConfigurationFromCursor = remoteConfigurationFromCursor(cursorQuery);
                        map.put(((RemoteConfigurationImpl) remoteConfigurationRemoteConfigurationFromCursor).mAppConfigurationId, remoteConfigurationRemoteConfigurationFromCursor);
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    sQLiteDatabaseOpenDatabase.endTransaction();
                    closeDatabase();
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (sQLiteDatabaseOpenDatabase != null) {
                        sQLiteDatabaseOpenDatabase.endTransaction();
                    }
                    closeDatabase();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabaseOpenDatabase = null;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return map;
    }

    public final RemoteConfiguration remoteConfigurationFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new RemoteConfigurationImpl(configurationFromCursor(cursor), cursor.getString(0), cursor.getInt(4), cursor.getString(3), false);
    }

    public void saveConfiguration(Map<String, RemoteConfiguration> map) {
        validateInputToSaveConfiguration(map);
        saveConfigurationPrivate(map);
    }

    public synchronized void saveConfigurationPrivate(Map<String, RemoteConfiguration> map) {
        Throwable th;
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                for (Map.Entry<String, RemoteConfiguration> entry : map.entrySet()) {
                    String key = entry.getKey();
                    RemoteConfiguration value = entry.getValue();
                    try {
                        saveConfigurationPrivate(sQLiteDatabaseOpenDatabase, key, value.getConfiguration().getAsJsonString(), value.getOrigin(), Long.valueOf(value.getConfiguration().getTimestamp().getTime()), value.getEntityTag());
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        if (sQLiteDatabaseOpenDatabase != null) {
                            sQLiteDatabaseOpenDatabase.endTransaction();
                        }
                        closeDatabase();
                        throw th;
                    }
                }
                sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                sQLiteDatabaseOpenDatabase.endTransaction();
                closeDatabase();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabaseOpenDatabase = null;
        }
    }

    public final void validateInputToSaveConfiguration(Map<String, RemoteConfiguration> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("GroupConfiguration should have at least one configuration");
        }
        for (Map.Entry<String, RemoteConfiguration> entry : map.entrySet()) {
            RemoteConfiguration value = entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException(String.format("The provided RemoteConfiguration for appConfigId %s is null", entry.getKey()));
            }
            if (value.getConfiguration() == null) {
                throw new IllegalArgumentException(String.format("The contained Configuration for appConfigId %s is null", entry.getKey()));
            }
            if (value.getConfiguration().getTimestamp() == null) {
                throw new IllegalArgumentException(String.format("The Configuration's timestamp for appConfigId %s is null", entry.getKey()));
            }
        }
    }

    public final void saveConfigurationPrivate(SQLiteDatabase sQLiteDatabase, String str, String str2, int i, Long l, String str3) {
        ContentValues contentValues = getContentValues(str2, Integer.valueOf(i), l, str3);
        if (hasConfiguration(sQLiteDatabase, str)) {
            int iUpdate = sQLiteDatabase.update(GroupConfigurationTable.TABLE_NAME, contentValues, "app_config_id = ?", new String[]{str});
            if (iUpdate == 1) {
                Log.d(TAG, "Updated 1 row in configuration_group table,\n" + contentValues);
                return;
            }
            throw new IllegalStateException(ObjectListKt$$ExternalSyntheticOutline1.m("Wanted to update 1 row, Updated ", iUpdate, " rows in configuration_group"));
        }
        contentValues.put(GroupConfigurationTable.COLUMN_APP_CONFIG_ID, str);
        sQLiteDatabase.insertOrThrow(GroupConfigurationTable.TABLE_NAME, null, contentValues);
        Log.d(TAG, "Inserted 1 row into configuration_group table,\n" + contentValues);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DBHelper extends SQLiteOpenHelper {
        public final File mDbFile;

        public DBHelper(Context context, String str) {
            super(context, getDbFileNameFor(str), (SQLiteDatabase.CursorFactory) null, 2);
            this.mDbFile = context.getDatabasePath(getDbFileNameFor(str));
        }

        public static String getDbFileNameFor(String str) {
            return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, "_GroupConfigurationCache.db");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws Throwable {
            Log.i(GroupConfigurationDb.TAG, "Creating Configuration DB version 2");
            setupDb(sQLiteDatabase);
        }

        public final void setupDb(SQLiteDatabase sQLiteDatabase) throws Throwable {
            boolean z = true;
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add(GroupConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.beginTransaction();
                try {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList.get(i);
                        i++;
                        String str = (String) obj;
                        sQLiteDatabase.execSQL(str);
                        Log.d(GroupConfigurationDb.TAG, "Executed sql, \n" + str);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    try {
                        Log.i(GroupConfigurationDb.TAG, "Created tables for version 2");
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            Log.e(GroupConfigurationDb.TAG, "Failed creating tables in GroupSyncDB. Attempt to delete GroupSyncDB ".concat(this.mDbFile.delete() ? "succeeded" : "failed"));
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    sQLiteDatabase.endTransaction();
                    throw th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }
}
