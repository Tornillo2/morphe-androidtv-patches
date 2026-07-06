package androidx.media3.exoplayer.offline;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@WorkerThread
@UnstableApi
public interface DownloadIndex {
    @Nullable
    Download getDownload(String str) throws IOException;

    DownloadCursor getDownloads(int... iArr) throws IOException;
}
