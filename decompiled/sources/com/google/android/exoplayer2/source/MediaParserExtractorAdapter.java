package com.google.android.exoplayer2.source;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import android.net.Uri;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import androidx.media3.exoplayer.source.MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.mediaparser.InputReaderAdapterV30;
import com.google.android.exoplayer2.source.mediaparser.MediaParserUtil;
import com.google.android.exoplayer2.source.mediaparser.OutputConsumerAdapterV30;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(30)
public final class MediaParserExtractorAdapter implements ProgressiveMediaExtractor {
    public static final ProgressiveMediaExtractor.Factory FACTORY = new MediaParserExtractorAdapter$$ExternalSyntheticLambda0();
    public final InputReaderAdapterV30 inputReaderAdapter;
    public final MediaParser mediaParser;
    public final OutputConsumerAdapterV30 outputConsumerAdapter;
    public String parserName;

    @SuppressLint({"WrongConstant"})
    public MediaParserExtractorAdapter(PlayerId playerId) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30();
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        this.inputReaderAdapter = new InputReaderAdapterV30();
        MediaParser mediaParserCreate = MediaParser.create(outputConsumerAdapterV30, new String[0]);
        this.mediaParser = mediaParserCreate;
        Boolean bool = Boolean.TRUE;
        mediaParserCreate.setParameter("android.media.mediaparser.eagerlyExposeTrackType", bool);
        mediaParserCreate.setParameter("android.media.mediaparser.inBandCryptoInfo", bool);
        mediaParserCreate.setParameter("android.media.mediaparser.includeSupplementalData", bool);
        this.parserName = "android.media.mediaparser.UNKNOWN";
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.Api31.setLogSessionIdOnMediaParser(mediaParserCreate, playerId);
        }
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        if ("android.media.mediaparser.Mp3Parser".equals(this.parserName)) {
            this.outputConsumerAdapter.seekingDisabled = true;
        }
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        return this.inputReaderAdapter.currentPosition;
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> map, long j, long j2, ExtractorOutput extractorOutput) throws IOException {
        this.outputConsumerAdapter.extractorOutput = extractorOutput;
        this.inputReaderAdapter.setDataReader(dataReader, j2);
        this.inputReaderAdapter.currentPosition = j;
        String parserName = this.mediaParser.getParserName();
        if ("android.media.mediaparser.UNKNOWN".equals(parserName)) {
            this.mediaParser.advance(this.inputReaderAdapter);
            String parserName2 = this.mediaParser.getParserName();
            this.parserName = parserName2;
            this.outputConsumerAdapter.setSelectedParserName(parserName2);
            return;
        }
        if (parserName.equals(this.parserName)) {
            return;
        }
        String parserName3 = this.mediaParser.getParserName();
        this.parserName = parserName3;
        this.outputConsumerAdapter.setSelectedParserName(parserName3);
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        boolean zAdvance = this.mediaParser.advance(this.inputReaderAdapter);
        long andResetSeekPosition = this.inputReaderAdapter.getAndResetSeekPosition();
        positionHolder.position = andResetSeekPosition;
        if (zAdvance) {
            return andResetSeekPosition != -1 ? 1 : 0;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void release() {
        this.mediaParser.release();
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void seek(long j, long j2) {
        this.inputReaderAdapter.currentPosition = j;
        Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.outputConsumerAdapter.getSeekPoints(j2);
        MediaParser mediaParser = this.mediaParser;
        Object obj = seekPoints.second;
        mediaParser.seek(MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(obj).position == j ? MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(obj) : MediaParserExtractorAdapter$$ExternalSyntheticApiModelOutline0.m(seekPoints.first));
    }
}
