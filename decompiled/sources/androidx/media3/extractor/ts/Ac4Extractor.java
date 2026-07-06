package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Ac4Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new Ac4Extractor$$ExternalSyntheticLambda0();
    public static final int FRAME_HEADER_SIZE = 7;
    public static final int MAX_SNIFF_BYTES = 8192;
    public static final int READ_BUFFER_SIZE = 16384;
    public final Ac4Reader reader = new Ac4Reader();
    public final ParsableByteArray sampleData = new ParsableByteArray(16384);
    public boolean startedPacket;

    public static /* synthetic */ Extractor[] $r8$lambda$pIYORmUQfpRIHxmB4WmTODgFbng() {
        return new Extractor[]{new Ac4Extractor()};
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = extractorInput.read(this.sampleData.data, 0, 16384);
        if (i == -1) {
            return -1;
        }
        this.sampleData.setPosition(0);
        this.sampleData.setLimit(i);
        if (!this.startedPacket) {
            this.reader.timeUs = 0L;
            this.startedPacket = true;
        }
        this.reader.consume(this.sampleData);
        return 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        r9.resetPeekPosition();
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0042, code lost:
    
        if ((r4 - r3) < 8192) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
    
        return false;
     */
    @Override // androidx.media3.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean sniff(androidx.media3.extractor.ExtractorInput r9) throws java.io.IOException {
        /*
            r8 = this;
            androidx.media3.common.util.ParsableByteArray r0 = new androidx.media3.common.util.ParsableByteArray
            r1 = 10
            r0.<init>(r1)
            r2 = 0
            r3 = 0
        L9:
            byte[] r4 = r0.data
            r9.peekFully(r4, r2, r1)
            r0.setPosition(r2)
            int r4 = r0.readUnsignedInt24()
            r5 = 4801587(0x494433, float:6.728456E-39)
            if (r4 == r5) goto L5f
            r9.resetPeekPosition()
            r9.advancePeekPosition(r3)
            r4 = r3
        L21:
            r1 = 0
        L22:
            byte[] r5 = r0.data
            r6 = 7
            r9.peekFully(r5, r2, r6)
            r0.setPosition(r2)
            int r5 = r0.readUnsignedShort()
            r6 = 44096(0xac40, float:6.1792E-41)
            if (r5 == r6) goto L49
            r6 = 44097(0xac41, float:6.1793E-41)
            if (r5 == r6) goto L49
            r9.resetPeekPosition()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.148E-41)
            if (r1 < r5) goto L45
            return r2
        L45:
            r9.advancePeekPosition(r4)
            goto L21
        L49:
            r6 = 1
            int r1 = r1 + r6
            r7 = 4
            if (r1 < r7) goto L4f
            return r6
        L4f:
            byte[] r6 = r0.data
            int r5 = androidx.media3.extractor.Ac4Util.parseAc4SyncframeSize(r6, r5)
            r6 = -1
            if (r5 != r6) goto L59
            return r2
        L59:
            int r5 = r5 + (-7)
            r9.advancePeekPosition(r5)
            goto L22
        L5f:
            r4 = 3
            r0.skipBytes(r4)
            int r4 = r0.readSynchSafeInt()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r9.advancePeekPosition(r4)
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.Ac4Extractor.sniff(androidx.media3.extractor.ExtractorInput):boolean");
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
