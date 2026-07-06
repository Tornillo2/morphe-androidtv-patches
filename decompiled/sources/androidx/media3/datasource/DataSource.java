package androidx.media3.datasource;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DataSource extends DataReader {

    /* JADX INFO: renamed from: androidx.media3.datasource.DataSource$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        @UnstableApi
        DataSource createDataSource();
    }

    @UnstableApi
    void addTransferListener(TransferListener transferListener);

    @UnstableApi
    void close() throws IOException;

    @UnstableApi
    Map<String, List<String>> getResponseHeaders();

    @Nullable
    @UnstableApi
    Uri getUri();

    @UnstableApi
    long open(DataSpec dataSpec) throws IOException;
}
