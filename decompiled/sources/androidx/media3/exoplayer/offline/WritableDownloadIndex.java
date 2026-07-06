package androidx.media3.exoplayer.offline;

import androidx.annotation.WorkerThread;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@WorkerThread
@UnstableApi
public interface WritableDownloadIndex extends DownloadIndex {
    void putDownload(Download download) throws IOException;

    void removeDownload(String str) throws IOException;

    void setDownloadingStatesToQueued() throws IOException;

    void setStatesToRemoving() throws IOException;

    void setStopReason(int i) throws IOException;

    void setStopReason(String str, int i) throws IOException;
}
