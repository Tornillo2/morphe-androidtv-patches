package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Ac3Extractor implements Extractor {
    public static final int AC3_SYNC_WORD = 2935;
    public static final ExtractorsFactory FACTORY = new Ac3Extractor$$ExternalSyntheticLambda0();
    public static final int MAX_SNIFF_BYTES = 8192;
    public static final int MAX_SYNC_FRAME_SIZE = 2786;
    public final Ac3Reader reader = new Ac3Reader(null);
    public final ParsableByteArray sampleData = new ParsableByteArray(2786);
    public boolean startedPacket;

    public static /* synthetic */ Extractor[] $r8$lambda$TdvbYBRwKicaibsyhlcjSoxq8SY() {
        return new Extractor[]{new Ac3Extractor()};
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = extractorInput.read(this.sampleData.data, 0, 2786);
        if (i == -1) {
            return -1;
        }
        this.sampleData.setPosition(0);
        this.sampleData.setLimit(i);
        if (!this.startedPacket) {
            this.reader.packetStarted(0L, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.sampleData);
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x003c, code lost:
    
        if ((r4 - r3) < 8192) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0033, code lost:
    
        r8.resetPeekPosition();
        r4 = r4 + 1;
     */
    @Override // com.google.android.exoplayer2.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean sniff(com.google.android.exoplayer2.extractor.ExtractorInput r8) throws java.io.IOException {
        /*
            r7 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r0 = new com.google.android.exoplayer2.util.ParsableByteArray
            r1 = 10
            r0.<init>(r1)
            r2 = 0
            r3 = 0
        L9:
            byte[] r4 = r0.data
            r8.peekFully(r4, r2, r1)
            r0.setPosition(r2)
            int r4 = r0.readUnsignedInt24()
            r5 = 4801587(0x494433, float:6.728456E-39)
            if (r4 == r5) goto L59
            r8.resetPeekPosition()
            r8.advancePeekPosition(r3)
            r4 = r3
        L21:
            r1 = 0
        L22:
            byte[] r5 = r0.data
            r6 = 6
            r8.peekFully(r5, r2, r6)
            r0.setPosition(r2)
            int r5 = r0.readUnsignedShort()
            r6 = 2935(0xb77, float:4.113E-42)
            if (r5 == r6) goto L43
            r8.resetPeekPosition()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.148E-41)
            if (r1 < r5) goto L3f
            return r2
        L3f:
            r8.advancePeekPosition(r4)
            goto L21
        L43:
            r5 = 1
            int r1 = r1 + r5
            r6 = 4
            if (r1 < r6) goto L49
            return r5
        L49:
            byte[] r5 = r0.data
            int r5 = com.google.android.exoplayer2.audio.Ac3Util.parseAc3SyncframeSize(r5)
            r6 = -1
            if (r5 != r6) goto L53
            return r2
        L53:
            int r5 = r5 + (-6)
            r8.advancePeekPosition(r5)
            goto L22
        L59:
            r4 = 3
            r0.skipBytes(r4)
            int r4 = r0.readSynchSafeInt()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r8.advancePeekPosition(r4)
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.Ac3Extractor.sniff(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
