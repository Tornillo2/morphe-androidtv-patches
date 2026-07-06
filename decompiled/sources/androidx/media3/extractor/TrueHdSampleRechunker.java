package androidx.media3.extractor;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TrueHdSampleRechunker {
    public int chunkFlags;
    public int chunkOffset;
    public int chunkSampleCount;
    public int chunkSize;
    public long chunkTimeUs;
    public boolean foundSyncframe;
    public final byte[] syncframePrefix = new byte[10];

    public void outputPendingSampleMetadata(TrackOutput trackOutput, @Nullable TrackOutput.CryptoData cryptoData) {
        if (this.chunkSampleCount > 0) {
            trackOutput.sampleMetadata(this.chunkTimeUs, this.chunkFlags, this.chunkSize, this.chunkOffset, cryptoData);
            this.chunkSampleCount = 0;
        }
    }

    public void reset() {
        this.foundSyncframe = false;
        this.chunkSampleCount = 0;
    }

    public void sampleMetadata(TrackOutput trackOutput, long j, int i, int i2, int i3, @Nullable TrackOutput.CryptoData cryptoData) {
        Assertions.checkState(this.chunkOffset <= i2 + i3, "TrueHD chunk samples must be contiguous in the sample queue.");
        if (this.foundSyncframe) {
            int i4 = this.chunkSampleCount;
            int i5 = i4 + 1;
            this.chunkSampleCount = i5;
            if (i4 == 0) {
                this.chunkTimeUs = j;
                this.chunkFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
            this.chunkOffset = i3;
            if (i5 >= 16) {
                outputPendingSampleMetadata(trackOutput, cryptoData);
            }
        }
    }

    public void startSample(ExtractorInput extractorInput) throws IOException {
        if (this.foundSyncframe) {
            return;
        }
        extractorInput.peekFully(this.syncframePrefix, 0, 10);
        extractorInput.resetPeekPosition();
        if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) == 0) {
            return;
        }
        this.foundSyncframe = true;
    }
}
