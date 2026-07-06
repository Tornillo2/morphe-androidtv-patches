package androidx.media3.exoplayer.source.chunk;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.mediaparser.InputReaderAdapterV30;
import androidx.media3.exoplayer.source.mediaparser.MediaParserUtil;
import androidx.media3.exoplayer.source.mediaparser.OutputConsumerAdapterV30;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DummyTrackOutput;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(30)
@UnstableApi
public final class MediaParserChunkExtractor implements ChunkExtractor {
    public static final ChunkExtractor.Factory FACTORY = new MediaParserChunkExtractor$$ExternalSyntheticLambda0();
    public static final String TAG = "MediaPrsrChunkExtractor";
    public final DummyTrackOutput dummyTrackOutput;
    public final InputReaderAdapterV30 inputReaderAdapter;
    public final MediaParser mediaParser;
    public final OutputConsumerAdapterV30 outputConsumerAdapter;
    public long pendingSeekUs;

    @Nullable
    public Format[] sampleFormats;

    @Nullable
    public ChunkExtractor.TrackOutputProvider trackOutputProvider;
    public final TrackOutputProviderAdapter trackOutputProviderAdapter;

    public static /* synthetic */ ChunkExtractor $r8$lambda$P_zo0CuG1K8L0KTBH9Zib0vOyvw(int i, Format format, boolean z, List list, TrackOutput trackOutput, PlayerId playerId) {
        if (MimeTypes.isText(format.containerMimeType)) {
            return null;
        }
        return new MediaParserChunkExtractor(i, format, list, playerId);
    }

    @SuppressLint({"WrongConstant"})
    public MediaParserChunkExtractor(int i, Format format, List<Format> list, PlayerId playerId) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30(format, i, true);
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        this.inputReaderAdapter = new InputReaderAdapterV30();
        String str = format.containerMimeType;
        str.getClass();
        String str2 = MimeTypes.isMatroska(str) ? "android.media.mediaparser.MatroskaParser" : "android.media.mediaparser.FragmentedMp4Parser";
        outputConsumerAdapterV30.containerMimeType = OutputConsumerAdapterV30.getMimeType(str2);
        MediaParser mediaParserCreateByName = MediaParser.createByName(str2, outputConsumerAdapterV30);
        this.mediaParser = mediaParserCreateByName;
        Boolean bool = Boolean.TRUE;
        mediaParserCreateByName.setParameter("android.media.mediaparser.matroska.disableCuesSeeking", bool);
        mediaParserCreateByName.setParameter("android.media.mediaparser.inBandCryptoInfo", bool);
        mediaParserCreateByName.setParameter("android.media.mediaparser.includeSupplementalData", bool);
        mediaParserCreateByName.setParameter("android.media.mediaparser.eagerlyExposeTrackType", bool);
        mediaParserCreateByName.setParameter("android.media.mediaparser.exposeDummySeekMap", bool);
        mediaParserCreateByName.setParameter("android.media.mediaParser.exposeChunkIndexAsMediaFormat", bool);
        mediaParserCreateByName.setParameter("android.media.mediaParser.overrideInBandCaptionDeclarations", bool);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(MediaParserUtil.toCaptionsMediaFormat(list.get(i2)));
        }
        this.mediaParser.setParameter("android.media.mediaParser.exposeCaptionFormats", arrayList);
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.Api31.setLogSessionIdOnMediaParser(this.mediaParser, playerId);
        }
        this.outputConsumerAdapter.muxedCaptionFormats = list;
        this.trackOutputProviderAdapter = new TrackOutputProviderAdapter();
        this.dummyTrackOutput = new DummyTrackOutput();
        this.pendingSeekUs = -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    @Nullable
    public ChunkIndex getChunkIndex() {
        return this.outputConsumerAdapter.lastChunkIndex;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void init(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider, long j, long j2) {
        this.trackOutputProvider = trackOutputProvider;
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = this.outputConsumerAdapter;
        outputConsumerAdapterV30.sampleTimestampUpperLimitFilterUs = j2;
        outputConsumerAdapterV30.extractorOutput = this.trackOutputProviderAdapter;
        this.pendingSeekUs = j;
    }

    public final void maybeExecutePendingSeek() {
        MediaParser.SeekMap seekMap = this.outputConsumerAdapter.dummySeekMap;
        long j = this.pendingSeekUs;
        if (j == -9223372036854775807L || seekMap == null) {
            return;
        }
        this.mediaParser.seek(MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(seekMap.getSeekPoints(j).first));
        this.pendingSeekUs = -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput extractorInput) throws IOException {
        maybeExecutePendingSeek();
        this.inputReaderAdapter.setDataReader(extractorInput, extractorInput.getLength());
        return this.mediaParser.advance(this.inputReaderAdapter);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void release() {
        this.mediaParser.release();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class TrackOutputProviderAdapter implements ExtractorOutput {
        public TrackOutputProviderAdapter() {
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void endTracks() {
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            mediaParserChunkExtractor.sampleFormats = mediaParserChunkExtractor.outputConsumerAdapter.getSampleFormats();
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public TrackOutput track(int i, int i2) {
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            ChunkExtractor.TrackOutputProvider trackOutputProvider = mediaParserChunkExtractor.trackOutputProvider;
            return trackOutputProvider != null ? trackOutputProvider.track(i, i2) : mediaParserChunkExtractor.dummyTrackOutput;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void seekMap(SeekMap seekMap) {
        }
    }
}
