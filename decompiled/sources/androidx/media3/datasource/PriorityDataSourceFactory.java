package androidx.media3.datasource;

import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
@Deprecated
public final class PriorityDataSourceFactory implements DataSource.Factory {
    public final int priority;
    public final PriorityTaskManager priorityTaskManager;
    public final DataSource.Factory upstreamFactory;

    public PriorityDataSourceFactory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager, int i) {
        this.upstreamFactory = factory;
        this.priorityTaskManager = priorityTaskManager;
        this.priority = i;
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}
