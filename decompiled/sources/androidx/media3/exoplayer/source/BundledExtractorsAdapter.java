package androidx.media3.exoplayer.source;

import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {

    @Nullable
    public Extractor extractor;

    @Nullable
    public ExtractorInput extractorInput;
    public final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor extractor = this.extractor;
        if (extractor == null) {
            return;
        }
        Extractor underlyingImplementation = extractor.getUnderlyingImplementation();
        if (underlyingImplementation instanceof Mp3Extractor) {
            ((Mp3Extractor) underlyingImplementation).disableSeeking = true;
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput extractorInput = this.extractorInput;
        if (extractorInput != null) {
            return extractorInput.getPosition();
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r1 = new androidx.media3.extractor.DefaultExtractorInput
            r2 = r8
            r3 = r11
            r5 = r13
            r1.<init>(r2, r3, r5)
            r7.extractorInput = r1
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto Lf
            return
        Lf:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
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
            androidx.media3.extractor.Extractor r14 = r7.extractor
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
            androidx.media3.common.util.Assertions.checkState(r14)
            r1.peekBufferPosition = r12
            goto L66
        L49:
            androidx.media3.extractor.Extractor r9 = r7.extractor
            if (r9 != 0) goto L55
            long r9 = r1.position
            int r13 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r13 != 0) goto L54
            goto L55
        L54:
            r11 = 0
        L55:
            androidx.media3.common.util.Assertions.checkState(r11)
            r1.peekBufferPosition = r12
            throw r8
        L5b:
            androidx.media3.extractor.Extractor r14 = r7.extractor
            if (r14 != 0) goto L42
            long r5 = r1.position
            int r14 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r14 != 0) goto L40
            goto L42
        L66:
            int r13 = r13 + 1
            goto L21
        L69:
            androidx.media3.extractor.Extractor r10 = r7.extractor
            if (r10 == 0) goto L73
        L6d:
            androidx.media3.extractor.Extractor r8 = r7.extractor
            r8.init(r15)
            return
        L73:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r10 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "None of the available extractors ("
            r11.<init>(r12)
            java.lang.String r8 = androidx.media3.common.util.Util.getCommaDelimitedSimpleClassNames(r8)
            r11.append(r8)
            java.lang.String r8 = ") could read the stream."
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            r9.getClass()
            r10.<init>(r8, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        Extractor extractor = this.extractor;
        extractor.getClass();
        ExtractorInput extractorInput = this.extractorInput;
        extractorInput.getClass();
        return extractor.read(extractorInput, positionHolder);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        Extractor extractor = this.extractor;
        if (extractor != null) {
            extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long j, long j2) {
        Extractor extractor = this.extractor;
        extractor.getClass();
        extractor.seek(j, j2);
    }
}
