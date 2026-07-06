package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new OggExtractor$$ExternalSyntheticLambda0();
    public static final int MAX_VERIFICATION_BYTES = 8;
    public ExtractorOutput output;
    public StreamReader streamReader;
    public boolean streamReaderInitialized;

    /* JADX INFO: renamed from: $r8$lambda$HTG9nvxjB8tiQQ5ZaM-YGLg8_aw, reason: not valid java name */
    public static Extractor[] m457$r8$lambda$HTG9nvxjB8tiQQ5ZaMYGLg8_aw() {
        return new Extractor[]{new OggExtractor()};
    }

    public static ParsableByteArray resetPosition(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(0);
        return parsableByteArray;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.output);
        if (this.streamReader == null) {
            if (!sniffInternal(extractorInput)) {
                throw ParserException.createForMalformedContainer("Failed to determine bitstream type", null);
            }
            extractorInput.resetPeekPosition();
        }
        if (!this.streamReaderInitialized) {
            TrackOutput trackOutputTrack = this.output.track(0, 1);
            this.output.endTracks();
            this.streamReader.init(this.output, trackOutputTrack);
            this.streamReaderInitialized = true;
        }
        return this.streamReader.read(extractorInput, positionHolder);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        StreamReader streamReader = this.streamReader;
        if (streamReader != null) {
            streamReader.seek(j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        try {
            return sniffInternal(extractorInput);
        } catch (ParserException unused) {
            return false;
        }
    }

    @EnsuresNonNullIf(expression = {"streamReader"}, result = true)
    public final boolean sniffInternal(ExtractorInput extractorInput) throws IOException {
        OggPageHeader oggPageHeader = new OggPageHeader();
        if (oggPageHeader.populate(extractorInput, true) && (oggPageHeader.type & 2) == 2) {
            int iMin = Math.min(oggPageHeader.bodySize, 8);
            ParsableByteArray parsableByteArray = new ParsableByteArray(iMin);
            extractorInput.peekFully(parsableByteArray.data, 0, iMin);
            parsableByteArray.setPosition(0);
            if (FlacReader.verifyBitstreamType(parsableByteArray)) {
                this.streamReader = new FlacReader();
            } else {
                parsableByteArray.setPosition(0);
                if (VorbisReader.verifyBitstreamType(parsableByteArray)) {
                    this.streamReader = new VorbisReader();
                } else {
                    parsableByteArray.setPosition(0);
                    if (OpusReader.peekPacketStartsWith(parsableByteArray, OpusReader.OPUS_ID_HEADER_SIGNATURE)) {
                        this.streamReader = new OpusReader();
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
