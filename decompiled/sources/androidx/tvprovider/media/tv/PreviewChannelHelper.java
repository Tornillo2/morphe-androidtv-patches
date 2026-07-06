package androidx.tvprovider.media.tv;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvContract;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.tvprovider.media.tv.PreviewChannel;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@WorkerThread
public class PreviewChannelHelper {
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 10000;
    public static final int DEFAULT_URL_CONNNECTION_TIMEOUT_MILLIS = 3000;
    public static final int INVALID_CONTENT_ID = -1;
    public static final String TAG = "PreviewChannelHelper";
    public final Context mContext;
    public final int mUrlConnectionTimeoutMillis;
    public final int mUrlReadTimeoutMillis;

    public PreviewChannelHelper(Context context) {
        this(context, 3000, 10000);
    }

    public final boolean addChannelLogo(long j, @NonNull PreviewChannel previewChannel) throws Throwable {
        boolean zCompress = false;
        if (!previewChannel.isLogoChanged()) {
            return false;
        }
        Bitmap logo = previewChannel.getLogo(this.mContext);
        if (logo == null) {
            logo = getLogoFromUri(previewChannel.getLogoUri());
        }
        try {
            OutputStream outputStreamOpenOutputStream = this.mContext.getContentResolver().openOutputStream(TvContract.buildChannelLogoUri(j));
            try {
                zCompress = logo.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.flush();
                outputStreamOpenOutputStream.close();
                return zCompress;
            } finally {
            }
        } catch (SQLiteException | IOException | NullPointerException e) {
            Log.i(TAG, ChannelLogoUtils$$ExternalSyntheticOutline0.m("Failed to add logo to the published channel (ID= ", j, ")"), e);
            return zCompress;
        }
    }

    public void deletePreviewChannel(long j) {
        this.mContext.getContentResolver().delete(TvContract.buildChannelUri(j), null, null);
    }

    public void deletePreviewProgram(long j) {
        this.mContext.getContentResolver().delete(ContentUris.withAppendedId(TvContractCompat.PreviewPrograms.CONTENT_URI, j), null, null);
    }

    public Bitmap downloadBitmap(@NonNull Uri uri) throws Throwable {
        URLConnection uRLConnectionOpenConnection;
        InputStream inputStream = null;
        try {
            uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
            try {
                uRLConnectionOpenConnection.setConnectTimeout(this.mUrlConnectionTimeoutMillis);
                uRLConnectionOpenConnection.setReadTimeout(this.mUrlReadTimeoutMillis);
                inputStream = uRLConnectionOpenConnection.getInputStream();
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnectionOpenConnection).disconnect();
                }
                return bitmapDecodeStream;
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                    throw th;
                }
                ((HttpURLConnection) uRLConnectionOpenConnection).disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            uRLConnectionOpenConnection = null;
        }
    }

    public List<PreviewChannel> getAllChannels() {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, PreviewChannel.Columns.PROJECTION, null, null, null);
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                arrayList.add(PreviewChannel.fromCursor(cursorQuery));
            } while (cursorQuery.moveToNext());
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap getLogoFromUri(@androidx.annotation.NonNull android.net.Uri r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to get logo from the URI: "
            android.net.Uri r1 = r8.normalizeScheme()
            java.lang.String r1 = r1.getScheme()
            r2 = 0
            java.lang.String r3 = "android.resource"
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            if (r3 != 0) goto L2e
            java.lang.String r3 = "file"
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            if (r3 != 0) goto L2e
            java.lang.String r3 = "content"
            boolean r1 = r3.equals(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            if (r1 == 0) goto L24
            goto L2e
        L24:
            android.graphics.Bitmap r8 = r7.downloadBitmap(r8)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            goto L3d
        L29:
            r8 = move-exception
            goto L64
        L2b:
            r1 = move-exception
            r3 = r2
            goto L4b
        L2e:
            android.content.Context r1 = r7.mContext     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            java.io.InputStream r1 = r1.openInputStream(r8)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            r2 = r1
        L3d:
            if (r2 == 0) goto L42
            r2.close()     // Catch: java.io.IOException -> L42
        L42:
            r2 = r8
            goto L61
        L44:
            r8 = move-exception
            r2 = r1
            goto L64
        L47:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
        L4b:
            java.lang.String r4 = "PreviewChannelHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L62
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L62
            r5.append(r8)     // Catch: java.lang.Throwable -> L62
            java.lang.String r8 = r5.toString()     // Catch: java.lang.Throwable -> L62
            android.util.Log.e(r4, r8, r1)     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L61
            r3.close()     // Catch: java.io.IOException -> L61
        L61:
            return r2
        L62:
            r8 = move-exception
            r2 = r3
        L64:
            if (r2 == 0) goto L69
            r2.close()     // Catch: java.io.IOException -> L69
        L69:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.PreviewChannelHelper.getLogoFromUri(android.net.Uri):android.graphics.Bitmap");
    }

    public PreviewChannel getPreviewChannel(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContract.buildChannelUri(j), PreviewChannel.Columns.PROJECTION, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return PreviewChannel.fromCursor(cursorQuery);
    }

    public PreviewProgram getPreviewProgram(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContentUris.withAppendedId(TvContractCompat.PreviewPrograms.CONTENT_URI, j), null, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return PreviewProgram.fromCursor(cursorQuery);
    }

    public WatchNextProgram getWatchNextProgram(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContentUris.withAppendedId(TvContractCompat.WatchNextPrograms.CONTENT_URI, j), null, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return WatchNextProgram.fromCursor(cursorQuery);
    }

    public long publishChannel(@NonNull PreviewChannel previewChannel) throws IOException {
        try {
            Uri uriInsert = this.mContext.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, previewChannel.toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Channel insertion failed");
            }
            long id = ContentUris.parseId(uriInsert);
            if (addChannelLogo(id, previewChannel)) {
                return id;
            }
            deletePreviewChannel(id);
            throw new IOException("Failed to add logo, so channel (ID=" + id + ") was not created");
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public long publishDefaultChannel(@NonNull PreviewChannel previewChannel) throws IOException {
        long jPublishChannel = publishChannel(previewChannel);
        TvContractCompat.requestChannelBrowsable(this.mContext, jPublishChannel);
        return jPublishChannel;
    }

    public long publishPreviewProgram(@NonNull PreviewProgram previewProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, previewProgram.toContentValues(false)));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public long publishWatchNextProgram(@NonNull WatchNextProgram watchNextProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.WatchNextPrograms.CONTENT_URI, watchNextProgram.toContentValues(false)));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public void updatePreviewChannel(long j, @NonNull PreviewChannel previewChannel) throws IOException {
        PreviewChannel previewChannel2 = getPreviewChannel(j);
        if (previewChannel2 != null && previewChannel2.hasAnyUpdatedValues(previewChannel)) {
            updatePreviewChannelInternal(j, previewChannel);
        }
        if (previewChannel.isLogoChanged() && !addChannelLogo(j, previewChannel)) {
            throw new IOException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Fail to update channel (ID=", j, ") logo."));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void updatePreviewChannelInternal(long j, @NonNull PreviewChannel previewChannel) {
        this.mContext.getContentResolver().update(TvContract.buildChannelUri(j), previewChannel.toContentValues(), null, null);
    }

    public void updatePreviewProgram(long j, @NonNull PreviewProgram previewProgram) {
        PreviewProgram previewProgram2 = getPreviewProgram(j);
        if (previewProgram2 == null || !previewProgram2.hasAnyUpdatedValues(previewProgram)) {
            return;
        }
        updatePreviewProgramInternal(j, previewProgram);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void updatePreviewProgramInternal(long j, @NonNull PreviewProgram previewProgram) {
        this.mContext.getContentResolver().update(ContentUris.withAppendedId(TvContractCompat.PreviewPrograms.CONTENT_URI, j), previewProgram.toContentValues(false), null, null);
    }

    public void updateWatchNextProgram(@NonNull WatchNextProgram watchNextProgram, long j) {
        WatchNextProgram watchNextProgram2 = getWatchNextProgram(j);
        if (watchNextProgram2 == null || !watchNextProgram2.hasAnyUpdatedValues(watchNextProgram)) {
            return;
        }
        updateWatchNextProgram(j, watchNextProgram);
    }

    public PreviewChannelHelper(Context context, int i, int i2) {
        this.mContext = context;
        this.mUrlConnectionTimeoutMillis = i;
        this.mUrlReadTimeoutMillis = i2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void updateWatchNextProgram(long j, @NonNull WatchNextProgram watchNextProgram) {
        this.mContext.getContentResolver().update(ContentUris.withAppendedId(TvContractCompat.WatchNextPrograms.CONTENT_URI, j), watchNextProgram.toContentValues(false), null, null);
    }
}
