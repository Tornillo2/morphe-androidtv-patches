package com.google.android.exoplayer2.offline;

import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface DownloadCursor extends Closeable {

    /* JADX INFO: renamed from: com.google.android.exoplayer2.offline.DownloadCursor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static boolean $default$isAfterLast(DownloadCursor downloadCursor) {
            return downloadCursor.getCount() == 0 || downloadCursor.getPosition() == downloadCursor.getCount();
        }

        public static boolean $default$isBeforeFirst(DownloadCursor downloadCursor) {
            return downloadCursor.getCount() == 0 || downloadCursor.getPosition() == -1;
        }

        public static boolean $default$isFirst(DownloadCursor downloadCursor) {
            return downloadCursor.getPosition() == 0 && downloadCursor.getCount() != 0;
        }

        public static boolean $default$isLast(DownloadCursor downloadCursor) {
            int count = downloadCursor.getCount();
            return downloadCursor.getPosition() == count + (-1) && count != 0;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    int getCount();

    Download getDownload();

    int getPosition();

    boolean isAfterLast();

    boolean isBeforeFirst();

    boolean isClosed();

    boolean isFirst();

    boolean isLast();

    boolean moveToFirst();

    boolean moveToLast();

    boolean moveToNext();

    boolean moveToPosition(int i);

    boolean moveToPrevious();
}
