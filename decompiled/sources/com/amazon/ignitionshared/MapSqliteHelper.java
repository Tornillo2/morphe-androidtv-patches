package com.amazon.ignitionshared;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class MapSqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "map_data_storage.db";
    public static final String DEVICE_ID_KEY = "serial.number";
    public static final String LEGACY_REFRESH_TOKEN_KEY = "com.amazon.dcp.sso.token.oauth.amazon.refresh_token";
    public static final String TAG = "MapSqliteHelper";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum MapTable {
        LegacyTokens("tokens", "token_key", "token_value", "token_deleted"),
        DeviceData("device_data", "device_data_key", "device_data_value", "device_data_deleted");

        public final String deletedColumn;
        public final String keyColumn;
        public final String name;
        public final String valueColumn;

        MapTable(String str, String str2, String str3, String str4) {
            this.name = str;
            this.keyColumn = str2;
            this.valueColumn = str3;
            this.deletedColumn = str4;
        }
    }

    @Inject
    public MapSqliteHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void clearTokensTable() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.execSQL("DROP TABLE IF EXISTS tokens");
                writableDatabase.close();
            } finally {
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "Failed to clear tokens table", e);
        }
    }

    public final String get(MapTable mapTable, String str) {
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                Cursor cursorQuery = readableDatabase.query(mapTable.name, new String[]{mapTable.keyColumn, mapTable.valueColumn}, "NOT " + mapTable.deletedColumn + " AND " + mapTable.keyColumn + " = ?", new String[]{str}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            int columnIndex = cursorQuery.getColumnIndex(mapTable.valueColumn);
                            if (columnIndex >= 0) {
                                String string = cursorQuery.getString(columnIndex);
                                cursorQuery.close();
                                readableDatabase.close();
                                return string;
                            }
                            throw new SQLiteException("Column: " + mapTable.valueColumn + " is not present in table.");
                        }
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                readableDatabase.close();
                return null;
            } finally {
            }
        } catch (SQLiteException e) {
            Log.e(TAG, String.format("An error occurred while trying to read value of key %s from SQLite table %s", str, mapTable.name), e);
            return null;
        }
    }

    public String getDeviceId() {
        return get(MapTable.DeviceData, DEVICE_ID_KEY);
    }

    public String getLegacyRefreshToken(String str) {
        return get(MapTable.LegacyTokens, str + "/com.amazon.dcp.sso.token.oauth.amazon.refresh_token");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
