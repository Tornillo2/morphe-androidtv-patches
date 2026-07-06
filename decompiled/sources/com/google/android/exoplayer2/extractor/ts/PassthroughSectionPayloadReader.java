package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PassthroughSectionPayloadReader implements SectionPayloadReader {
    public Format format;
    public TrackOutput output;
    public TimestampAdjuster timestampAdjuster;

    public PassthroughSectionPayloadReader(String str) {
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = str;
        this.format = new Format(builder);
    }

    @EnsuresNonNull({"timestampAdjuster", "output"})
    public final void assertInitialized() {
        Assertions.checkStateNotNull(this.timestampAdjuster);
        Util.castNonNull(this.output);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.SectionPayloadReader
    public void consume(ParsableByteArray parsableByteArray) {
        assertInitialized();
        long lastAdjustedTimestampUs = this.timestampAdjuster.getLastAdjustedTimestampUs();
        long timestampOffsetUs = this.timestampAdjuster.getTimestampOffsetUs();
        if (lastAdjustedTimestampUs == -9223372036854775807L || timestampOffsetUs == -9223372036854775807L) {
            return;
        }
        Format format = this.format;
        if (timestampOffsetUs != format.subsampleOffsetUs) {
            Format.Builder builder = new Format.Builder(format);
            builder.subsampleOffsetUs = timestampOffsetUs;
            Format format2 = new Format(builder);
            this.format = format2;
            this.output.format(format2);
        }
        int iBytesLeft = parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, iBytesLeft);
        this.output.sampleMetadata(lastAdjustedTimestampUs, 1, iBytesLeft, 0, null);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.SectionPayloadReader
    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.timestampAdjuster = timestampAdjuster;
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 5);
        this.output = trackOutputTrack;
        trackOutputTrack.format(this.format);
    }
}
