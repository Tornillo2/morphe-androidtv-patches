package androidx.media3.datasource;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class PriorityDataSource implements DataSource {
    public final int priority;
    public final PriorityTaskManager priorityTaskManager;
    public final DataSource upstream;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements DataSource.Factory {
        public final int priority;
        public final PriorityTaskManager priorityTaskManager;
        public final DataSource.Factory upstreamFactory;

        public Factory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager, int i) {
            this.upstreamFactory = factory;
            this.priorityTaskManager = priorityTaskManager;
            this.priority = i;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public PriorityDataSource createDataSource() {
            return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
        }
    }

    public PriorityDataSource(DataSource dataSource, PriorityTaskManager priorityTaskManager, int i) {
        dataSource.getClass();
        this.upstream = dataSource;
        priorityTaskManager.getClass();
        this.priorityTaskManager = priorityTaskManager;
        this.priority = i;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.upstream.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.upstream.close();
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    public Uri getUri() {
        return this.upstream.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.open(dataSpec);
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.read(bArr, i, i2);
    }
}
