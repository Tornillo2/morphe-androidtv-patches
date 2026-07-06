package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.text.AlphabetConverter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ThumbnailStreamOpener {
    public static final FileService DEFAULT_SERVICE = new FileService();
    public static final String TAG = "ThumbStreamOpener";
    public final ArrayPool byteArrayPool;
    public final ContentResolver contentResolver;
    public final List<ImageHeaderParser> parsers;
    public final ThumbnailQuery query;
    public final FileService service;

    public ThumbnailStreamOpener(List<ImageHeaderParser> list, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver) {
        this(list, DEFAULT_SERVICE, thumbnailQuery, arrayPool, contentResolver);
    }

    public int getOrientation(Uri uri) {
        InputStream inputStreamOpenInputStream = null;
        try {
            try {
                inputStreamOpenInputStream = this.contentResolver.openInputStream(uri);
                int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, inputStreamOpenInputStream, this.byteArrayPool);
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return orientation;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException | NullPointerException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to open uri: " + uri, e);
            }
            if (inputStreamOpenInputStream == null) {
                return -1;
            }
            try {
                inputStreamOpenInputStream.close();
                return -1;
            } catch (IOException unused3) {
                return -1;
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x001d: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]) (LINE:30), block:B:11:0x001d */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getPath(@androidx.annotation.NonNull android.net.Uri r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "ThumbStreamOpener"
            java.lang.String r1 = "Failed to query for thumbnail for Uri: "
            r2 = 0
            com.bumptech.glide.load.data.mediastore.ThumbnailQuery r3 = r6.query     // Catch: java.lang.Throwable -> L27 java.lang.SecurityException -> L29
            android.database.Cursor r3 = r3.query(r7)     // Catch: java.lang.Throwable -> L27 java.lang.SecurityException -> L29
            if (r3 == 0) goto L21
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L1c java.lang.SecurityException -> L1f
            if (r4 == 0) goto L21
            r4 = 0
            java.lang.String r7 = r3.getString(r4)     // Catch: java.lang.Throwable -> L1c java.lang.SecurityException -> L1f
            r3.close()
            return r7
        L1c:
            r7 = move-exception
            r2 = r3
            goto L47
        L1f:
            r4 = move-exception
            goto L2b
        L21:
            if (r3 == 0) goto L46
            r3.close()
            return r2
        L27:
            r7 = move-exception
            goto L47
        L29:
            r4 = move-exception
            r3 = r2
        L2b:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch: java.lang.Throwable -> L1c
            if (r5 == 0) goto L41
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L1c
            r5.append(r7)     // Catch: java.lang.Throwable -> L1c
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Throwable -> L1c
            android.util.Log.d(r0, r7, r4)     // Catch: java.lang.Throwable -> L1c
        L41:
            if (r3 == 0) goto L46
            r3.close()
        L46:
            return r2
        L47:
            if (r2 == 0) goto L4c
            r2.close()
        L4c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.mediastore.ThumbnailStreamOpener.getPath(android.net.Uri):java.lang.String");
    }

    public final boolean isValid(File file) {
        return this.service.exists(file) && 0 < this.service.length(file);
    }

    public InputStream open(Uri uri) throws Throwable {
        String path = getPath(uri);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = this.service.get(path);
        if (!isValid(file)) {
            return null;
        }
        Uri uriFromFile = Uri.fromFile(file);
        try {
            return this.contentResolver.openInputStream(uriFromFile);
        } catch (NullPointerException e) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + AlphabetConverter.ARROW + uriFromFile).initCause(e));
        }
    }

    public ThumbnailStreamOpener(List<ImageHeaderParser> list, FileService fileService, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver) {
        this.service = fileService;
        this.query = thumbnailQuery;
        this.byteArrayPool = arrayPool;
        this.contentResolver = contentResolver;
        this.parsers = list;
    }
}
