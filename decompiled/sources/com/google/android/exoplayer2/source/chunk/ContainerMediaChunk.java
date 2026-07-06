package com.google.android.exoplayer2.source.chunk;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceUtil;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ContainerMediaChunk extends BaseMediaChunk {
    public final int chunkCount;
    public final ChunkExtractor chunkExtractor;
    public volatile boolean loadCanceled;
    public boolean loadCompleted;
    public long nextLoadPosition;
    public final long sampleOffsetUs;

    public ContainerMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, @Nullable Object obj, long j, long j2, long j3, long j4, long j5, int i2, long j6, ChunkExtractor chunkExtractor) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3, j4, j5);
        this.chunkCount = i2;
        this.sampleOffsetUs = j6;
        this.chunkExtractor = chunkExtractor;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public long getNextChunkIndex() {
        return this.chunkIndex + ((long) this.chunkCount);
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void load() throws IOException {
        if (this.nextLoadPosition == 0) {
            BaseMediaChunkOutput baseMediaChunkOutput = this.output;
            Assertions.checkStateNotNull(baseMediaChunkOutput);
            baseMediaChunkOutput.setSampleOffsetUs(this.sampleOffsetUs);
            ChunkExtractor chunkExtractor = this.chunkExtractor;
            ChunkExtractor.TrackOutputProvider trackOutputProvider = getTrackOutputProvider(baseMediaChunkOutput);
            long j = this.clippedStartTimeUs;
            long j2 = j == -9223372036854775807L ? -9223372036854775807L : j - this.sampleOffsetUs;
            long j3 = this.clippedEndTimeUs;
            chunkExtractor.init(trackOutputProvider, j2, j3 != -9223372036854775807L ? j3 - this.sampleOffsetUs : -9223372036854775807L);
        }
        try {
            DataSpec dataSpecSubrange = this.dataSpec.subrange(this.nextLoadPosition);
            StatsDataSource statsDataSource = this.dataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(statsDataSource, dataSpecSubrange.position, statsDataSource.open(dataSpecSubrange));
            do {
                try {
                    if (this.loadCanceled) {
                        break;
                    }
                } finally {
                    this.nextLoadPosition = defaultExtractorInput.position - this.dataSpec.position;
                }
            } while (this.chunkExtractor.read(defaultExtractorInput));
            DataSourceUtil.closeQuietly(this.dataSource);
            this.loadCompleted = !this.loadCanceled;
        } catch (Throwable th) {
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }

    public ChunkExtractor.TrackOutputProvider getTrackOutputProvider(BaseMediaChunkOutput baseMediaChunkOutput) {
        return baseMediaChunkOutput;
    }
}
