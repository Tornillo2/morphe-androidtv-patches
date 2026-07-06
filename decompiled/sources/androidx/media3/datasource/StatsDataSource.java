package androidx.media3.datasource;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class StatsDataSource implements DataSource {
    public long bytesRead;
    public final DataSource dataSource;
    public Uri lastOpenedUri;
    public Map<String, List<String>> lastResponseHeaders;

    public StatsDataSource(DataSource dataSource) {
        dataSource.getClass();
        this.dataSource = dataSource;
        this.lastOpenedUri = Uri.EMPTY;
        this.lastResponseHeaders = Collections.EMPTY_MAP;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.dataSource.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.dataSource.close();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public Uri getLastOpenedUri() {
        return this.lastOpenedUri;
    }

    public Map<String, List<String>> getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    public Uri getUri() {
        return this.dataSource.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.lastOpenedUri = dataSpec.uri;
        this.lastResponseHeaders = Collections.EMPTY_MAP;
        long jOpen = this.dataSource.open(dataSpec);
        Uri uri = this.dataSource.getUri();
        uri.getClass();
        this.lastOpenedUri = uri;
        this.lastResponseHeaders = this.dataSource.getResponseHeaders();
        return jOpen;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.dataSource.read(bArr, i, i2);
        if (i3 != -1) {
            this.bytesRead += (long) i3;
        }
        return i3;
    }

    public void resetBytesRead() {
        this.bytesRead = 0L;
    }
}
