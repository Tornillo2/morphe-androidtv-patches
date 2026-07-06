package androidx.tvprovider.media.tv;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvContract;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@WorkerThread
public class ChannelLogoUtils {
    public static final int CONNECTION_TIMEOUT_MS_FOR_URLCONNECTION = 3000;
    public static final int READ_TIMEOUT_MS_FOR_URLCONNECTION = 10000;
    public static final String TAG = "ChannelLogoUtils";

    @Deprecated
    public ChannelLogoUtils() {
    }

    public static URLConnection getUrlConnection(String str) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        uRLConnectionOpenConnection.setConnectTimeout(3000);
        uRLConnectionOpenConnection.setReadTimeout(10000);
        return uRLConnectionOpenConnection;
    }

    public static Bitmap loadChannelLogo(@NonNull Context context, long j) {
        try {
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(TvContract.buildChannelLogoUri(j)));
        } catch (FileNotFoundException e) {
            Log.i(TAG, ChannelLogoUtils$$ExternalSyntheticOutline0.m("Channel logo for channel (ID:", j, ") not found."), e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0057 A[PHI: r1 r2
      0x0057: PHI (r1v7 java.net.URLConnection) = (r1v6 java.net.URLConnection), (r1v11 java.net.URLConnection) binds: [B:41:0x0083, B:29:0x0055] A[DONT_GENERATE, DONT_INLINE]
      0x0057: PHI (r2v5 android.graphics.Bitmap) = (r2v11 android.graphics.Bitmap), (r2v8 android.graphics.Bitmap) binds: [B:41:0x0083, B:29:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean storeChannelLogo(@androidx.annotation.NonNull android.content.Context r8, long r9, @androidx.annotation.NonNull android.net.Uri r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Failed to get logo from the URI: "
            android.net.Uri r1 = r11.normalizeScheme()
            java.lang.String r1 = r1.getScheme()
            r2 = 0
            java.lang.String r3 = "android.resource"
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            if (r3 != 0) goto L3f
            java.lang.String r3 = "file"
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            if (r3 != 0) goto L3f
            java.lang.String r3 = "content"
            boolean r1 = r3.equals(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            if (r1 == 0) goto L24
            goto L3f
        L24:
            java.lang.String r1 = r11.toString()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            java.net.URLConnection r1 = getUrlConnection(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            java.io.InputStream r3 = r1.getInputStream()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L34
            goto L48
        L31:
            r8 = move-exception
            goto L94
        L34:
            r3 = move-exception
            r4 = r2
            goto L64
        L37:
            r8 = move-exception
            r1 = r2
            goto L94
        L3b:
            r3 = move-exception
            r1 = r2
            r4 = r1
            goto L64
        L3f:
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            java.io.InputStream r3 = r1.openInputStream(r11)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            r1 = r2
        L48:
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60
            if (r3 == 0) goto L53
            r3.close()     // Catch: java.io.IOException -> L52
            goto L53
        L52:
        L53:
            boolean r11 = r1 instanceof java.net.HttpURLConnection
            if (r11 == 0) goto L86
        L57:
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1
            r1.disconnect()
            goto L86
        L5d:
            r8 = move-exception
            r2 = r3
            goto L94
        L60:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
        L64:
            java.lang.String r5 = "ChannelLogoUtils"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L92
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L92
            r6.append(r11)     // Catch: java.lang.Throwable -> L92
            java.lang.String r11 = "\n"
            r6.append(r11)     // Catch: java.lang.Throwable -> L92
            java.lang.String r11 = r6.toString()     // Catch: java.lang.Throwable -> L92
            android.util.Log.i(r5, r11, r3)     // Catch: java.lang.Throwable -> L92
            if (r4 == 0) goto L81
            r4.close()     // Catch: java.io.IOException -> L80
            goto L81
        L80:
        L81:
            boolean r11 = r1 instanceof java.net.HttpURLConnection
            if (r11 == 0) goto L86
            goto L57
        L86:
            if (r2 == 0) goto L90
            boolean r8 = storeChannelLogo(r8, r9, r2)
            if (r8 == 0) goto L90
            r8 = 1
            goto L91
        L90:
            r8 = 0
        L91:
            return r8
        L92:
            r8 = move-exception
            r2 = r4
        L94:
            if (r2 == 0) goto L9b
            r2.close()     // Catch: java.io.IOException -> L9a
            goto L9b
        L9a:
        L9b:
            boolean r9 = r1 instanceof java.net.HttpURLConnection
            if (r9 == 0) goto La4
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1
            r1.disconnect()
        La4:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.ChannelLogoUtils.storeChannelLogo(android.content.Context, long, android.net.Uri):boolean");
    }

    public static boolean storeChannelLogo(@NonNull Context context, long j, @NonNull Bitmap bitmap) {
        try {
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(TvContract.buildChannelLogoUri(j));
            try {
                boolean zCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.flush();
                outputStreamOpenOutputStream.close();
                return zCompress;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (outputStreamOpenOutputStream != null) {
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (SQLiteException | IOException e) {
            Log.i(TAG, "Failed to store the logo to the system content provider.\n", e);
            return false;
        }
    }
}
