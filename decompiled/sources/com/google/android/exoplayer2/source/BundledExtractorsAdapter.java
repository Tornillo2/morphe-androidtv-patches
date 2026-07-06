package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {

    @Nullable
    public Extractor extractor;

    @Nullable
    public ExtractorInput extractorInput;
    public final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor extractor = this.extractor;
        if (extractor instanceof Mp3Extractor) {
            ((Mp3Extractor) extractor).disableSeeking = true;
        }
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput extractorInput = this.extractorInput;
        if (extractorInput != null) {
            return extractorInput.getPosition();
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(com.google.android.exoplayer2.upstream.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, com.google.android.exoplayer2.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            com.google.android.exoplayer2.extractor.DefaultExtractorInput r1 = new com.google.android.exoplayer2.extractor.DefaultExtractorInput
            r2 = r8
            r3 = r11
            r5 = r13
            r1.<init>(r2, r3, r5)
            r7.extractorInput = r1
            com.google.android.exoplayer2.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto Lf
            return
        Lf:
            com.google.android.exoplayer2.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            com.google.android.exoplayer2.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            r11 = 1
            r12 = 0
            if (r10 != r11) goto L1f
            r8 = r8[r12]
            r7.extractor = r8
            goto L6d
        L1f:
            int r10 = r8.length
            r13 = 0
        L21:
            if (r13 >= r10) goto L69
            r14 = r8[r13]
            boolean r0 = r14.sniff(r1)     // Catch: java.lang.Throwable -> L30 java.io.EOFException -> L33
            if (r0 == 0) goto L35
            r7.extractor = r14     // Catch: java.lang.Throwable -> L30 java.io.EOFException -> L33
            r1.peekBufferPosition = r12
            goto L69
        L30:
            r0 = move-exception
            r8 = r0
            goto L49
        L33:
            goto L5b
        L35:
            com.google.android.exoplayer2.extractor.Extractor r14 = r7.extractor
            if (r14 != 0) goto L42
            long r5 = r1.position
            int r14 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r14 != 0) goto L40
            goto L42
        L40:
            r14 = 0
            goto L43
        L42:
            r14 = 1
        L43:
            com.google.android.exoplayer2.util.Assertions.checkState(r14)
            r1.peekBufferPosition = r12
            goto L66
        L49:
            com.google.android.exoplayer2.extractor.Extractor r9 = r7.extractor
            if (r9 != 0) goto L55
            long r9 = r1.position
            int r13 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r13 != 0) goto L54
            goto L55
        L54:
            r11 = 0
        L55:
            com.google.android.exoplayer2.util.Assertions.checkState(r11)
            r1.peekBufferPosition = r12
            throw r8
        L5b:
            com.google.android.exoplayer2.extractor.Extractor r14 = r7.extractor
            if (r14 != 0) goto L42
            long r5 = r1.position
            int r14 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r14 != 0) goto L40
            goto L42
        L66:
            int r13 = r13 + 1
            goto L21
        L69:
            com.google.android.exoplayer2.extractor.Extractor r10 = r7.extractor
            if (r10 == 0) goto L73
        L6d:
            com.google.android.exoplayer2.extractor.Extractor r8 = r7.extractor
            r8.init(r15)
            return
        L73:
            com.google.android.exoplayer2.source.UnrecognizedInputFormatException r10 = new com.google.android.exoplayer2.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "None of the available extractors ("
            r11.<init>(r12)
            java.lang.String r8 = com.google.android.exoplayer2.util.Util.getCommaDelimitedSimpleClassNames(r8)
            r11.append(r8)
            java.lang.String r8 = ") could read the stream."
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            r9.getClass()
            r10.<init>(r8, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.BundledExtractorsAdapter.init(com.google.android.exoplayer2.upstream.DataReader, android.net.Uri, java.util.Map, long, long, com.google.android.exoplayer2.extractor.ExtractorOutput):void");
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        Extractor extractor = this.extractor;
        extractor.getClass();
        ExtractorInput extractorInput = this.extractorInput;
        extractorInput.getClass();
        return extractor.read(extractorInput, positionHolder);
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void release() {
        Extractor extractor = this.extractor;
        if (extractor != null) {
            extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor
    public void seek(long j, long j2) {
        Extractor extractor = this.extractor;
        extractor.getClass();
        extractor.seek(j, j2);
    }
}
