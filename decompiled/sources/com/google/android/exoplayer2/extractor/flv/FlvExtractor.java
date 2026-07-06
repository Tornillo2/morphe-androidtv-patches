package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FlvExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new FlvExtractor$$ExternalSyntheticLambda0();
    public static final int FLV_HEADER_SIZE = 9;
    public static final int FLV_TAG = 4607062;
    public static final int FLV_TAG_HEADER_SIZE = 11;
    public static final int STATE_READING_FLV_HEADER = 1;
    public static final int STATE_READING_TAG_DATA = 4;
    public static final int STATE_READING_TAG_HEADER = 3;
    public static final int STATE_SKIPPING_TO_TAG_HEADER = 2;
    public static final int TAG_TYPE_AUDIO = 8;
    public static final int TAG_TYPE_SCRIPT_DATA = 18;
    public static final int TAG_TYPE_VIDEO = 9;
    public AudioTagPayloadReader audioReader;
    public int bytesToNextTagHeader;
    public ExtractorOutput extractorOutput;
    public long mediaTagTimestampOffsetUs;
    public boolean outputFirstSample;
    public boolean outputSeekMap;
    public int tagDataSize;
    public long tagTimestampUs;
    public int tagType;
    public VideoTagPayloadReader videoReader;
    public final ParsableByteArray scratch = new ParsableByteArray(4);
    public final ParsableByteArray headerBuffer = new ParsableByteArray(9);
    public final ParsableByteArray tagHeaderBuffer = new ParsableByteArray(11);
    public final ParsableByteArray tagData = new ParsableByteArray();
    public final ScriptTagPayloadReader metadataReader = new ScriptTagPayloadReader();
    public int state = 1;

    public static /* synthetic */ Extractor[] $r8$lambda$wSfJ_54lnASe4n8rYplxFGihsFU() {
        return new Extractor[]{new FlvExtractor()};
    }

    @RequiresNonNull({"extractorOutput"})
    public final void ensureReadyForMediaOutput() {
        if (this.outputSeekMap) {
            return;
        }
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        this.outputSeekMap = true;
    }

    public final long getCurrentTimestampUs() {
        if (this.outputFirstSample) {
            return this.mediaTagTimestampOffsetUs + this.tagTimestampUs;
        }
        if (this.metadataReader.durationUs == -9223372036854775807L) {
            return 0L;
        }
        return this.tagTimestampUs;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    public final ParsableByteArray prepareTagData(ExtractorInput extractorInput) throws IOException {
        int i = this.tagDataSize;
        ParsableByteArray parsableByteArray = this.tagData;
        byte[] bArr = parsableByteArray.data;
        if (i > bArr.length) {
            parsableByteArray.reset(new byte[Math.max(bArr.length * 2, i)], 0);
        } else {
            parsableByteArray.setPosition(0);
        }
        this.tagData.setLimit(this.tagDataSize);
        extractorInput.readFully(this.tagData.data, 0, this.tagDataSize);
        return this.tagData;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        while (true) {
            int i = this.state;
            if (i != 1) {
                if (i == 2) {
                    skipToTagHeader(extractorInput);
                } else if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException();
                    }
                    if (readTagData(extractorInput)) {
                        return 0;
                    }
                } else if (!readTagHeader(extractorInput)) {
                    return -1;
                }
            } else if (!readFlvHeader(extractorInput)) {
                return -1;
            }
        }
    }

    @RequiresNonNull({"extractorOutput"})
    public final boolean readFlvHeader(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.readFully(this.headerBuffer.data, 0, 9, true)) {
            return false;
        }
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int unsignedByte = this.headerBuffer.readUnsignedByte();
        boolean z = (unsignedByte & 4) != 0;
        boolean z2 = (unsignedByte & 1) != 0;
        if (z && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
        }
        if (z2 && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9, 2));
        }
        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = this.headerBuffer.readInt() - 5;
        this.state = 2;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"extractorOutput"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean readTagData(com.google.android.exoplayer2.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.getCurrentTimestampUs()
            int r2 = r9.tagType
            r3 = 8
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 1
            if (r2 != r3) goto L23
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r3 = r9.audioReader
            if (r3 == 0) goto L23
            r9.ensureReadyForMediaOutput()
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r2 = r9.audioReader
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
        L21:
            r0 = 1
            goto L6b
        L23:
            r3 = 9
            if (r2 != r3) goto L39
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r3 = r9.videoReader
            if (r3 == 0) goto L39
            r9.ensureReadyForMediaOutput()
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r2 = r9.videoReader
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            goto L21
        L39:
            r3 = 18
            if (r2 != r3) goto L64
            boolean r2 = r9.outputSeekMap
            if (r2 != 0) goto L64
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r2 = r9.metadataReader
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r0 = r9.metadataReader
            long r1 = r0.durationUs
            int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r3 == 0) goto L21
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = r9.extractorOutput
            com.google.android.exoplayer2.extractor.IndexSeekMap r7 = new com.google.android.exoplayer2.extractor.IndexSeekMap
            long[] r8 = r0.keyFrameTagPositions
            long[] r0 = r0.keyFrameTimesUs
            r7.<init>(r8, r0, r1)
            r3.seekMap(r7)
            r9.outputSeekMap = r6
            goto L21
        L64:
            int r0 = r9.tagDataSize
            r10.skipFully(r0)
            r10 = 0
            r0 = 0
        L6b:
            boolean r1 = r9.outputFirstSample
            if (r1 != 0) goto L83
            if (r10 == 0) goto L83
            r9.outputFirstSample = r6
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r10 = r9.metadataReader
            long r1 = r10.durationUs
            int r10 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r10 != 0) goto L7f
            long r1 = r9.tagTimestampUs
            long r1 = -r1
            goto L81
        L7f:
            r1 = 0
        L81:
            r9.mediaTagTimestampOffsetUs = r1
        L83:
            r10 = 4
            r9.bytesToNextTagHeader = r10
            r10 = 2
            r9.state = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.flv.FlvExtractor.readTagData(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }

    public final boolean readTagHeader(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.readFully(this.tagHeaderBuffer.data, 0, 11, true)) {
            return false;
        }
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (((long) (this.tagHeaderBuffer.readUnsignedByte() << 24)) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        if (j == 0) {
            this.state = 1;
            this.outputFirstSample = false;
        } else {
            this.state = 3;
        }
        this.bytesToNextTagHeader = 0;
    }

    public final void skipToTagHeader(ExtractorInput extractorInput) throws IOException {
        extractorInput.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.state = 3;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.data, 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != 4607062) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & 250) != 0) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        int i = this.scratch.readInt();
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        return this.scratch.readInt() == 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
