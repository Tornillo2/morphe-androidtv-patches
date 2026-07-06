package com.google.android.exoplayer2.offline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.database.DatabaseIOException;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.VersionTable;
import com.google.android.exoplayer2.offline.DownloadCursor;
import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultDownloadIndex implements WritableDownloadIndex {
    public static final String COLUMN_BYTES_DOWNLOADED = "bytes_downloaded";
    public static final String COLUMN_CONTENT_LENGTH = "content_length";
    public static final String COLUMN_CUSTOM_CACHE_KEY = "custom_cache_key";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_FAILURE_REASON = "failure_reason";
    public static final String COLUMN_ID = "id";
    public static final int COLUMN_INDEX_BYTES_DOWNLOADED = 13;
    public static final int COLUMN_INDEX_CONTENT_LENGTH = 9;
    public static final int COLUMN_INDEX_CUSTOM_CACHE_KEY = 4;
    public static final int COLUMN_INDEX_DATA = 5;
    public static final int COLUMN_INDEX_FAILURE_REASON = 11;
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_KEY_SET_ID = 14;
    public static final int COLUMN_INDEX_MIME_TYPE = 1;
    public static final int COLUMN_INDEX_PERCENT_DOWNLOADED = 12;
    public static final int COLUMN_INDEX_START_TIME_MS = 7;
    public static final int COLUMN_INDEX_STATE = 6;
    public static final int COLUMN_INDEX_STOP_REASON = 10;
    public static final int COLUMN_INDEX_STREAM_KEYS = 3;
    public static final int COLUMN_INDEX_UPDATE_TIME_MS = 8;
    public static final int COLUMN_INDEX_URI = 2;
    public static final String COLUMN_KEY_SET_ID = "key_set_id";
    public static final String COLUMN_MIME_TYPE = "mime_type";
    public static final String COLUMN_PERCENT_DOWNLOADED = "percent_downloaded";
    public static final String COLUMN_START_TIME_MS = "start_time_ms";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_STOP_REASON = "stop_reason";
    public static final String COLUMN_STREAM_KEYS = "stream_keys";
    public static final String COLUMN_UPDATE_TIME_MS = "update_time_ms";
    public static final String COLUMN_URI = "uri";
    public static final String TABLE_PREFIX = "ExoPlayerDownloads";
    public static final String TABLE_SCHEMA = "(id TEXT PRIMARY KEY NOT NULL,mime_type TEXT,uri TEXT NOT NULL,stream_keys TEXT NOT NULL,custom_cache_key TEXT,data BLOB NOT NULL,state INTEGER NOT NULL,start_time_ms INTEGER NOT NULL,update_time_ms INTEGER NOT NULL,content_length INTEGER NOT NULL,stop_reason INTEGER NOT NULL,failure_reason INTEGER NOT NULL,percent_downloaded REAL NOT NULL,bytes_downloaded INTEGER NOT NULL,key_set_id BLOB NOT NULL)";

    @VisibleForTesting
    public static final int TABLE_VERSION = 3;
    public static final String TRUE = "1";
    public static final String WHERE_ID_EQUALS = "id = ?";
    public static final String WHERE_STATE_IS_DOWNLOADING = "state = 2";
    public final DatabaseProvider databaseProvider;
    public final Object initializationLock;

    @GuardedBy("initializationLock")
    public boolean initialized;
    public final String name;
    public final String tableName;
    public static final String WHERE_STATE_IS_TERMINAL = getStateQuery(3, 4);
    public static final String[] COLUMNS = {"id", "mime_type", "uri", "stream_keys", "custom_cache_key", "data", "state", "start_time_ms", "update_time_ms", "content_length", "stop_reason", "failure_reason", "percent_downloaded", "bytes_downloaded", "key_set_id"};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DownloadCursorImpl implements DownloadCursor {
        public final Cursor cursor;

        @Override // com.google.android.exoplayer2.offline.DownloadCursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.cursor.close();
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public int getCount() {
            return this.cursor.getCount();
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public Download getDownload() {
            return DefaultDownloadIndex.getDownloadForCurrentRow(this.cursor);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public int getPosition() {
            return this.cursor.getPosition();
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public /* synthetic */ boolean isAfterLast() {
            return DownloadCursor.CC.$default$isAfterLast(this);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public /* synthetic */ boolean isBeforeFirst() {
            return DownloadCursor.CC.$default$isBeforeFirst(this);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean isClosed() {
            return this.cursor.isClosed();
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public /* synthetic */ boolean isFirst() {
            return DownloadCursor.CC.$default$isFirst(this);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public /* synthetic */ boolean isLast() {
            return DownloadCursor.CC.$default$isLast(this);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean moveToFirst() {
            return moveToPosition(0);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean moveToLast() {
            return moveToPosition(getCount() - 1);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean moveToNext() {
            return moveToPosition(getPosition() + 1);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean moveToPosition(int i) {
            return this.cursor.moveToPosition(i);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadCursor
        public boolean moveToPrevious() {
            return moveToPosition(getPosition() - 1);
        }

        public DownloadCursorImpl(Cursor cursor) {
            this.cursor = cursor;
        }
    }

    public DefaultDownloadIndex(DatabaseProvider databaseProvider) {
        this(databaseProvider, "");
    }

    public static List<StreamKey> decodeStreamKeys(@Nullable String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : Util.split(str, ",")) {
                String[] strArrSplit = str2.split("\\.", -1);
                Assertions.checkState(strArrSplit.length == 3);
                arrayList.add(new StreamKey(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2])));
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    public static String encodeStreamKeys(List<StreamKey> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            StreamKey streamKey = list.get(i);
            sb.append(streamKey.periodIndex);
            sb.append('.');
            sb.append(streamKey.groupIndex);
            sb.append('.');
            sb.append(streamKey.streamIndex);
            sb.append(',');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static Download getDownloadForCurrentRow(Cursor cursor) {
        byte[] blob = cursor.getBlob(14);
        String string = cursor.getString(0);
        string.getClass();
        String string2 = cursor.getString(2);
        string2.getClass();
        DownloadRequest.Builder builder = new DownloadRequest.Builder(string, Uri.parse(string2));
        builder.mimeType = cursor.getString(1);
        builder.streamKeys = decodeStreamKeys(cursor.getString(3));
        if (blob.length <= 0) {
            blob = null;
        }
        builder.keySetId = blob;
        builder.customCacheKey = cursor.getString(4);
        builder.data = cursor.getBlob(5);
        DownloadRequest downloadRequestBuild = builder.build();
        DownloadProgress downloadProgress = new DownloadProgress();
        downloadProgress.bytesDownloaded = cursor.getLong(13);
        downloadProgress.percentDownloaded = cursor.getFloat(12);
        int i = cursor.getInt(6);
        return new Download(downloadRequestBuild, i, cursor.getLong(7), cursor.getLong(8), cursor.getLong(9), cursor.getInt(10), i == 4 ? cursor.getInt(11) : 0, downloadProgress);
    }

    public static Download getDownloadForCurrentRowV2(Cursor cursor) {
        String string = cursor.getString(0);
        string.getClass();
        String string2 = cursor.getString(2);
        string2.getClass();
        DownloadRequest.Builder builder = new DownloadRequest.Builder(string, Uri.parse(string2));
        builder.mimeType = inferMimeType(cursor.getString(1));
        builder.streamKeys = decodeStreamKeys(cursor.getString(3));
        builder.customCacheKey = cursor.getString(4);
        builder.data = cursor.getBlob(5);
        DownloadRequest downloadRequestBuild = builder.build();
        DownloadProgress downloadProgress = new DownloadProgress();
        downloadProgress.bytesDownloaded = cursor.getLong(13);
        downloadProgress.percentDownloaded = cursor.getFloat(12);
        int i = cursor.getInt(6);
        return new Download(downloadRequestBuild, i, cursor.getLong(7), cursor.getLong(8), cursor.getLong(9), cursor.getInt(10), i == 4 ? cursor.getInt(11) : 0, downloadProgress);
    }

    public static String getStateQuery(int... iArr) {
        if (iArr.length == 0) {
            return "1";
        }
        StringBuilder sb = new StringBuilder("state IN (");
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(iArr[i]);
        }
        sb.append(')');
        return sb.toString();
    }

    public static String inferMimeType(@Nullable String str) {
        return "dash".equals(str) ? "application/dash+xml" : "hls".equals(str) ? "application/x-mpegURL" : "ss".equals(str) ? "application/vnd.ms-sstr+xml" : "video/x-unknown";
    }

    public final void ensureInitialized() throws DatabaseIOException {
        synchronized (this.initializationLock) {
            if (this.initialized) {
                return;
            }
            try {
                int version = VersionTable.getVersion(this.databaseProvider.getReadableDatabase(), 0, this.name);
                if (version != 3) {
                    SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                    writableDatabase.beginTransactionNonExclusive();
                    try {
                        VersionTable.setVersion(writableDatabase, 0, this.name, 3);
                        List<Download> listLoadDownloadsFromVersion2 = version == 2 ? loadDownloadsFromVersion2(writableDatabase) : new ArrayList<>();
                        writableDatabase.execSQL("DROP TABLE IF EXISTS " + this.tableName);
                        writableDatabase.execSQL("CREATE TABLE " + this.tableName + " (id TEXT PRIMARY KEY NOT NULL,mime_type TEXT,uri TEXT NOT NULL,stream_keys TEXT NOT NULL,custom_cache_key TEXT,data BLOB NOT NULL,state INTEGER NOT NULL,start_time_ms INTEGER NOT NULL,update_time_ms INTEGER NOT NULL,content_length INTEGER NOT NULL,stop_reason INTEGER NOT NULL,failure_reason INTEGER NOT NULL,percent_downloaded REAL NOT NULL,bytes_downloaded INTEGER NOT NULL,key_set_id BLOB NOT NULL)");
                        Iterator<Download> it = listLoadDownloadsFromVersion2.iterator();
                        while (it.hasNext()) {
                            putDownloadInternal(it.next(), writableDatabase);
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                }
                this.initialized = true;
            } catch (SQLException e) {
                throw new DatabaseIOException((Throwable) e);
            }
        }
    }

    public final Cursor getCursor(String str, @Nullable String[] strArr) throws DatabaseIOException {
        try {
            return this.databaseProvider.getReadableDatabase().query(this.tableName, COLUMNS, str, strArr, null, null, "start_time_ms ASC");
        } catch (SQLiteException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @Override // com.google.android.exoplayer2.offline.DownloadIndex
    @Nullable
    public Download getDownload(String str) throws DatabaseIOException {
        ensureInitialized();
        try {
            Cursor cursor = getCursor("id = ?", new String[]{str});
            try {
                if (cursor.getCount() == 0) {
                    cursor.close();
                    return null;
                }
                cursor.moveToNext();
                Download downloadForCurrentRow = getDownloadForCurrentRow(cursor);
                cursor.close();
                return downloadForCurrentRow;
            } finally {
            }
        } catch (SQLiteException e) {
            throw new DatabaseIOException((Throwable) e);
        }
        throw new DatabaseIOException((Throwable) e);
    }

    @Override // com.google.android.exoplayer2.offline.DownloadIndex
    public DownloadCursor getDownloads(int... iArr) throws DatabaseIOException {
        ensureInitialized();
        return new DownloadCursorImpl(getCursor(getStateQuery(iArr), null));
    }

    public final List<Download> loadDownloadsFromVersion2(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        if (!Util.tableExists(sQLiteDatabase, this.tableName)) {
            return arrayList;
        }
        Cursor cursorQuery = sQLiteDatabase.query(this.tableName, new String[]{"id", "title", "uri", "stream_keys", "custom_cache_key", "data", "state", "start_time_ms", "update_time_ms", "content_length", "stop_reason", "failure_reason", "percent_downloaded", "bytes_downloaded"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(getDownloadForCurrentRowV2(cursorQuery));
            } finally {
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void putDownload(Download download) throws DatabaseIOException {
        ensureInitialized();
        try {
            putDownloadInternal(download, this.databaseProvider.getWritableDatabase());
        } catch (SQLiteException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    public final void putDownloadInternal(Download download, SQLiteDatabase sQLiteDatabase) {
        byte[] bArr = download.request.keySetId;
        if (bArr == null) {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", download.request.id);
        contentValues.put("mime_type", download.request.mimeType);
        contentValues.put("uri", download.request.uri.toString());
        contentValues.put("stream_keys", encodeStreamKeys(download.request.streamKeys));
        contentValues.put("custom_cache_key", download.request.customCacheKey);
        contentValues.put("data", download.request.data);
        contentValues.put("state", Integer.valueOf(download.state));
        contentValues.put("start_time_ms", Long.valueOf(download.startTimeMs));
        contentValues.put("update_time_ms", Long.valueOf(download.updateTimeMs));
        contentValues.put("content_length", Long.valueOf(download.contentLength));
        contentValues.put("stop_reason", Integer.valueOf(download.stopReason));
        contentValues.put("failure_reason", Integer.valueOf(download.failureReason));
        contentValues.put("percent_downloaded", Float.valueOf(download.progress.percentDownloaded));
        contentValues.put("bytes_downloaded", Long.valueOf(download.progress.bytesDownloaded));
        contentValues.put("key_set_id", bArr);
        sQLiteDatabase.replaceOrThrow(this.tableName, null, contentValues);
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void removeDownload(String str) throws DatabaseIOException {
        ensureInitialized();
        try {
            this.databaseProvider.getWritableDatabase().delete(this.tableName, "id = ?", new String[]{str});
        } catch (SQLiteException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void setDownloadingStatesToQueued() throws DatabaseIOException {
        ensureInitialized();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", (Integer) 0);
            this.databaseProvider.getWritableDatabase().update(this.tableName, contentValues, "state = 2", null);
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void setStatesToRemoving() throws DatabaseIOException {
        ensureInitialized();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", (Integer) 5);
            contentValues.put("failure_reason", (Integer) 0);
            this.databaseProvider.getWritableDatabase().update(this.tableName, contentValues, null, null);
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void setStopReason(int i) throws DatabaseIOException {
        ensureInitialized();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("stop_reason", Integer.valueOf(i));
            this.databaseProvider.getWritableDatabase().update(this.tableName, contentValues, WHERE_STATE_IS_TERMINAL, null);
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }

    public DefaultDownloadIndex(DatabaseProvider databaseProvider, String str) {
        this.name = str;
        this.databaseProvider = databaseProvider;
        this.tableName = RemoteInput$$ExternalSyntheticOutline0.m("ExoPlayerDownloads", str);
        this.initializationLock = new Object();
    }

    @Override // com.google.android.exoplayer2.offline.WritableDownloadIndex
    public void setStopReason(String str, int i) throws DatabaseIOException {
        ensureInitialized();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("stop_reason", Integer.valueOf(i));
            this.databaseProvider.getWritableDatabase().update(this.tableName, contentValues, WHERE_STATE_IS_TERMINAL + " AND id = ?", new String[]{str});
        } catch (SQLException e) {
            throw new DatabaseIOException((Throwable) e);
        }
    }
}
