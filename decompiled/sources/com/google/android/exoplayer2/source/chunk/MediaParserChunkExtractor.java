package com.google.android.exoplayer2.source.chunk;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.exoplayer.source.MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.source.mediaparser.InputReaderAdapterV30;
import com.google.android.exoplayer2.source.mediaparser.MediaParserUtil;
import com.google.android.exoplayer2.source.mediaparser.OutputConsumerAdapterV30;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(30)
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

    public static /* synthetic */ ChunkExtractor $r8$lambda$CuL0K8lRTgqY_JaKKwQ04JJB7YA(int i, Format format, boolean z, List list, TrackOutput trackOutput, PlayerId playerId) {
        if (!MimeTypes.isText(format.containerMimeType)) {
            return new MediaParserChunkExtractor(i, format, list, playerId);
        }
        Log.w("MediaPrsrChunkExtractor", "Ignoring an unsupported text track.");
        return null;
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

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    @Nullable
    public ChunkIndex getChunkIndex() {
        return this.outputConsumerAdapter.lastChunkIndex;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
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

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput extractorInput) throws IOException {
        maybeExecutePendingSeek();
        this.inputReaderAdapter.setDataReader(extractorInput, extractorInput.getLength());
        return this.mediaParser.advance(this.inputReaderAdapter);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    public void release() {
        this.mediaParser.release();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class TrackOutputProviderAdapter implements ExtractorOutput {
        public TrackOutputProviderAdapter() {
        }

        @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
        public void endTracks() {
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            mediaParserChunkExtractor.sampleFormats = mediaParserChunkExtractor.outputConsumerAdapter.getSampleFormats();
        }

        @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
        public TrackOutput track(int i, int i2) {
            MediaParserChunkExtractor mediaParserChunkExtractor = MediaParserChunkExtractor.this;
            ChunkExtractor.TrackOutputProvider trackOutputProvider = mediaParserChunkExtractor.trackOutputProvider;
            return trackOutputProvider != null ? trackOutputProvider.track(i, i2) : mediaParserChunkExtractor.dummyTrackOutput;
        }

        @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
        public void seekMap(SeekMap seekMap) {
        }
    }
}
