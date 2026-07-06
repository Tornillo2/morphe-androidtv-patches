package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class MediaParserChunkExtractor$$ExternalSyntheticLambda0 implements ChunkExtractor.Factory {
    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
    public final ChunkExtractor createProgressiveMediaExtractor(int i, Format format, boolean z, List list, TrackOutput trackOutput, PlayerId playerId) {
        return MediaParserChunkExtractor.$r8$lambda$P_zo0CuG1K8L0KTBH9Zib0vOyvw(i, format, z, list, trackOutput, playerId);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
    public ChunkExtractor.Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
        return this;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
    public Format getOutputTextFormat(Format format) {
        return format;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
    public ChunkExtractor.Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return this;
    }
}
