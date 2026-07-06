package androidx.media3.exoplayer.source.chunk;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.DefaultExtractorInput;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class InitializationChunk extends Chunk {
    public final ChunkExtractor chunkExtractor;
    public volatile boolean loadCanceled;
    public long nextLoadPosition;
    public ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, @Nullable Object obj, ChunkExtractor chunkExtractor) {
        super(dataSource, dataSpec, 2, format, i, obj, -9223372036854775807L, -9223372036854775807L);
        this.chunkExtractor = chunkExtractor;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider) {
        this.trackOutputProvider = trackOutputProvider;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void load() throws IOException {
        if (this.nextLoadPosition == 0) {
            this.chunkExtractor.init(this.trackOutputProvider, -9223372036854775807L, -9223372036854775807L);
        }
        try {
            DataSpec dataSpecSubrange = this.dataSpec.subrange(this.nextLoadPosition);
            StatsDataSource statsDataSource = this.dataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(statsDataSource, dataSpecSubrange.position, statsDataSource.open(dataSpecSubrange));
            while (!this.loadCanceled && this.chunkExtractor.read(defaultExtractorInput)) {
                try {
                } finally {
                    this.nextLoadPosition = defaultExtractorInput.position - this.dataSpec.position;
                }
            }
        } finally {
            DataSourceUtil.closeQuietly(this.dataSource);
        }
    }
}
