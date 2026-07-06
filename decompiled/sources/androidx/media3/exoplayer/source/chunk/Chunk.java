package androidx.media3.exoplayer.source.chunk;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.upstream.Loader;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class Chunk implements Loader.Loadable {
    public final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long endTimeUs;
    public final long loadTaskId;
    public final long startTimeUs;
    public final Format trackFormat;

    @Nullable
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int type;

    public Chunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, int i2, @Nullable Object obj, long j, long j2) {
        this.dataSource = new StatsDataSource(dataSource);
        dataSpec.getClass();
        this.dataSpec = dataSpec;
        this.type = i;
        this.trackFormat = format;
        this.trackSelectionReason = i2;
        this.trackSelectionData = obj;
        this.startTimeUs = j;
        this.endTimeUs = j2;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    public final long bytesLoaded() {
        return this.dataSource.bytesRead;
    }

    public final long getDurationUs() {
        return this.endTimeUs - this.startTimeUs;
    }

    public final Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.lastResponseHeaders;
    }

    public final Uri getUri() {
        return this.dataSource.lastOpenedUri;
    }
}
