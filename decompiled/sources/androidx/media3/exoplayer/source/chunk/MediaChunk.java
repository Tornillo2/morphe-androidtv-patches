package androidx.media3.exoplayer.source.chunk;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public MediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, @Nullable Object obj, long j, long j2, long j3) {
        super(dataSource, dataSpec, 1, format, i, obj, j, j2);
        format.getClass();
        this.chunkIndex = j3;
    }

    public long getNextChunkIndex() {
        long j = this.chunkIndex;
        if (j != -1) {
            return j + 1;
        }
        return -1L;
    }

    public abstract boolean isLoadCompleted();
}
