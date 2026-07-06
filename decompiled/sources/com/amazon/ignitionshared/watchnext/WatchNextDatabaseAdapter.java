package com.amazon.ignitionshared.watchnext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.reporting.Log;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 26)
@SuppressLint({"RestrictedApi"})
public class WatchNextDatabaseAdapter {
    public static final String TAG = "WatchNextPublisher";
    public final Context context;

    @Inject
    public WatchNextDatabaseAdapter(@ApplicationContext Context context) {
        this.context = context;
    }

    public Map<String, WatchNextProgram> getContentIdToProgramsMapForInserted() {
        HashMap map = new HashMap();
        try {
            Cursor cursorQuery = this.context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, WatchNextProgram.PROJECTION, null, null);
            try {
                if (cursorQuery == null) {
                    Log.d(TAG, "Cursor is null when attempting to fetch entries");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
                    WatchNextProgram watchNextProgramFromCursor = WatchNextProgram.fromCursor(cursorQuery);
                    map.put(watchNextProgramFromCursor.getContentId(), watchNextProgramFromCursor);
                }
                cursorQuery.close();
                return map;
            } finally {
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to fetch entries", e);
            return null;
        }
    }

    public long[] getInsertedProgramIds() {
        try {
            Cursor cursorQuery = this.context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, new String[]{"_id"}, null, null);
            try {
                if (cursorQuery == null) {
                    Log.d(TAG, "Cursor is null when attempting to fetch ids");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                long[] jArr = new long[cursorQuery.getCount()];
                int i = 0;
                for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
                    jArr[i] = cursorQuery.getLong(0);
                    i++;
                }
                cursorQuery.close();
                return jArr;
            } finally {
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to get ids", e);
            return null;
        }
    }
}
