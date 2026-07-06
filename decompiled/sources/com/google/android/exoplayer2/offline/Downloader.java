package com.google.android.exoplayer2.offline;

import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface Downloader {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ProgressListener {
        void onProgress(long j, long j2, float f);
    }

    void cancel();

    void download(@Nullable ProgressListener progressListener) throws InterruptedException, IOException;

    void remove();
}
