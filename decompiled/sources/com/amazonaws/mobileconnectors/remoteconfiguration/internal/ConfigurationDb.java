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
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ConfigurationDb {
    public static final int COL_ENTITY_TAG = 2;
    public static final int COL_ORIGIN = 3;
    public static final int COL_TIMESTAMP = 1;
    public static final int COL_VALUE = 0;
    public static final int DATABASE_VERSION = 2;
    public static final String DB_NAME = "ConfigurationCache.db";
    public static final String SQL_CREATE_CONFIGURATION_TABLE = "CREATE TABLE configuration (_id INTEGER PRIMARY KEY, json TEXT, origin INTEGER NOT NULL CHECK (origin IN (1,2,3)), timestamp INTEGER, entity_tag TEXT);";
    public static final String TAG = "ConfigurationDb";
    public final DBHelper dbHelper;
    public SQLiteDatabase mDatabase;
    public final AtomicInteger openCounter = new AtomicInteger(0);
    public static final String[] PROJECTION_CONFIGURATION = {"json", "timestamp"};
    public static final String[] PROJECTION_REMOTE_CONFIGURATION = {"json", "timestamp", "entity_tag", "origin"};
    public static final Map<String, ConfigurationDb> INSTANCE_MAP = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConfigurationTable implements BaseColumns {
        public static final String COLUMN_ENTITY_TAG = "entity_tag";
        public static final String COLUMN_JSON = "json";
        public static final String COLUMN_ORIGIN = "origin";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String TABLE_NAME = "configuration";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DBHelper extends SQLiteOpenHelper {
        public final File mDbFile;

        public DBHelper(Context context, String str) {
            super(context, getDbFileNameFor(str), (SQLiteDatabase.CursorFactory) null, 2);
            this.mDbFile = context.getDatabasePath(getDbFileNameFor(str));
        }

        public static String getDbFileNameFor(String str) {
            return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, "_ConfigurationCache.db");
        }

        public File getDbFile() {
            return this.mDbFile;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws Throwable {
            Log.i(ConfigurationDb.TAG, "Creating Configuration DB version 2");
            setupDb(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            throw new DowngradeException();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i != 1 || i2 != 2) {
                throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Illegal upgrade: ", i, " to ", i2));
            }
            Log.i(ConfigurationDb.TAG, "Upgrading database schema from " + i + " to " + i2);
            sQLiteDatabase.execSQL("DROP INDEX e_tag_idx_configuration;");
            sQLiteDatabase.execSQL("DROP INDEX configuration_type_idx_configuration;");
            sQLiteDatabase.execSQL("ALTER TABLE configuration RENAME TO configuration_old;");
            sQLiteDatabase.execSQL(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
            sQLiteDatabase.execSQL("INSERT INTO configuration(_id, json, origin, entity_tag, timestamp) SELECT _id, value, CASE WHEN e_tag IS NULL THEN 1 ELSE 2 END AS origin, e_tag, last_load_time FROM configuration_old WHERE configuration_type = 1;");
            sQLiteDatabase.execSQL("DROP TABLE configuration_old;");
            Log.d(ConfigurationDb.TAG, "Database upgrade completed");
        }

        public final void setupDb(SQLiteDatabase sQLiteDatabase) throws Throwable {
            boolean z = true;
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.beginTransaction();
                try {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList.get(i);
                        i++;
                        String str = (String) obj;
                        sQLiteDatabase.execSQL(str);
                        Log.d(ConfigurationDb.TAG, "Executed sql, \n" + str);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    try {
                        Log.i(ConfigurationDb.TAG, "Created tables for version 2");
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            Log.e(ConfigurationDb.TAG, "Failed creating tables in SyncDB. Attempt to delete SyncDB ".concat(this.mDbFile.delete() ? "succeeded" : "failed"));
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
    }

    public ConfigurationDb(DBHelper dBHelper) {
        this.dbHelper = dBHelper;
    }

    public static ConfigurationDb getOrCreateInstance(Context context, String str) {
        ConfigurationDb configurationDb;
        String strReplace = str.replace(SchemaId.METRIC_SCHEMA_ID_DELIMITER, "");
        Map<String, ConfigurationDb> map = INSTANCE_MAP;
        synchronized (map) {
            configurationDb = map.get(strReplace);
            if (configurationDb == null) {
                DBHelper dBHelper = new DBHelper(context, strReplace);
                try {
                    dBHelper.getWritableDatabase();
                } catch (DowngradeException unused) {
                    context.deleteDatabase(DBHelper.getDbFileNameFor(strReplace));
                    dBHelper = new DBHelper(context, strReplace);
                }
                ConfigurationDb configurationDb2 = new ConfigurationDb(dBHelper);
                INSTANCE_MAP.put(strReplace, configurationDb2);
                configurationDb = configurationDb2;
            }
        }
        return configurationDb;
    }

    public final synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.openCounter.decrementAndGet() == 0 && (sQLiteDatabase = this.mDatabase) != null) {
            sQLiteDatabase.close();
        }
    }

    public final Configuration configurationFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getString(0) == null) {
            throw new ConfigurationNotFoundException("Configuration not found");
        }
        return new ConfigurationImpl(cursor.getString(0), cursor.isNull(1) ? null : new Date(cursor.getLong(1)));
    }

    public synchronized void deleteConfiguration() {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Throwable th;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                sQLiteDatabaseOpenDatabase.delete("configuration", null, null);
                Log.d(TAG, "Deleted rows from configuration table");
                sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                sQLiteDatabaseOpenDatabase.endTransaction();
                closeDatabase();
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabaseOpenDatabase != null) {
                    sQLiteDatabaseOpenDatabase.endTransaction();
                }
                closeDatabase();
                throw th;
            }
        } catch (Throwable th3) {
            sQLiteDatabaseOpenDatabase = null;
            th = th3;
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

    public final boolean hasConfiguration(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("configuration", new String[]{"_id"}, null, null, null, null, null, "1");
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

    public synchronized Configuration readConfiguration() throws ConfigurationNotFoundException {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Configuration configurationConfigurationFromCursor;
        Cursor cursor = null;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                Cursor cursorQuery = sQLiteDatabaseOpenDatabase.query("configuration", PROJECTION_CONFIGURATION, null, null, null, null, null);
                sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    throw new ConfigurationNotFoundException("Configuration not found");
                }
                configurationConfigurationFromCursor = configurationFromCursor(cursorQuery);
                cursorQuery.close();
                sQLiteDatabaseOpenDatabase.endTransaction();
                closeDatabase();
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
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
        return configurationConfigurationFromCursor;
    }

    public synchronized RemoteConfiguration readRemoteConfiguration(String str) {
        Throwable th;
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                Cursor cursorQuery = sQLiteDatabaseOpenDatabase.query("configuration", PROJECTION_REMOTE_CONFIGURATION, null, null, null, null, null);
                try {
                    sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                    if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        sQLiteDatabaseOpenDatabase.endTransaction();
                        closeDatabase();
                        return null;
                    }
                    RemoteConfiguration remoteConfigurationRemoteConfigurationFromCursor = remoteConfigurationFromCursor(cursorQuery, str);
                    cursorQuery.close();
                    sQLiteDatabaseOpenDatabase.endTransaction();
                    closeDatabase();
                    return remoteConfigurationRemoteConfigurationFromCursor;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabaseOpenDatabase != null) {
                        sQLiteDatabaseOpenDatabase.endTransaction();
                    }
                    closeDatabase();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabaseOpenDatabase = null;
        }
    }

    public final RemoteConfiguration remoteConfigurationFromCursor(Cursor cursor, String str) {
        if (cursor == null) {
            return null;
        }
        return new RemoteConfigurationImpl(configurationFromCursor(cursor), str, cursor.getInt(3), cursor.getString(2), false);
    }

    public void saveConfiguration(RemoteConfiguration remoteConfiguration) throws Throwable {
        if (remoteConfiguration == null) {
            throw new NullPointerException("The RemoteConfiguration may not be null");
        }
        if (remoteConfiguration.getConfiguration() == null) {
            throw new NullPointerException("The contained Configuration may not be null");
        }
        if (remoteConfiguration.getConfiguration().getTimestamp() == null) {
            throw new NullPointerException("The Configuration's timestamp may not be null");
        }
        saveConfiguration(remoteConfiguration.getConfiguration().getAsJsonString(), remoteConfiguration.getOrigin(), Long.valueOf(remoteConfiguration.getConfiguration().getTimestamp().getTime()), remoteConfiguration.getEntityTag());
    }

    public final void saveConfigurationPrivate(SQLiteDatabase sQLiteDatabase, String str, int i, Long l, String str2) {
        ContentValues contentValues = getContentValues(str, Integer.valueOf(i), l, str2);
        if (!hasConfiguration(sQLiteDatabase)) {
            sQLiteDatabase.insertOrThrow("configuration", null, contentValues);
            Log.d(TAG, "Inserted 1 row into configuration table,\n" + contentValues);
            return;
        }
        int iUpdate = sQLiteDatabase.update("configuration", contentValues, null, null);
        if (iUpdate != 1) {
            throw new IllegalStateException(ObjectListKt$$ExternalSyntheticOutline1.m("Updated ", iUpdate, " rows while was intending to update one and only one row in configuration"));
        }
        Log.d(TAG, "Updated 1 row in configuration table,\n" + contentValues);
    }

    public synchronized void saveConfiguration(String str, int i, Long l, String str2) throws Throwable {
        Throwable th;
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        try {
            try {
                Checks.checkNotNull(str, "configuration cannot be null");
                Checks.checkNotNull(l, "timestamp cannot be null for non-default configuration");
                try {
                    sQLiteDatabaseOpenDatabase = openDatabase();
                    try {
                        sQLiteDatabaseOpenDatabase.beginTransaction();
                        try {
                            saveConfigurationPrivate(sQLiteDatabaseOpenDatabase, str, i, l, str2);
                            sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                            sQLiteDatabaseOpenDatabase.endTransaction();
                            closeDatabase();
                        } catch (Throwable th2) {
                            th = th2;
                            th = th;
                            if (sQLiteDatabaseOpenDatabase != null) {
                                sQLiteDatabaseOpenDatabase.endTransaction();
                            }
                            closeDatabase();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    sQLiteDatabaseOpenDatabase = null;
                }
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            throw th;
        }
    }
}
