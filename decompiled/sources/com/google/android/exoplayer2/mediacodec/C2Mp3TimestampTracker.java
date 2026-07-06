package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Log;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class C2Mp3TimestampTracker {
    public static final long DECODER_DELAY_FRAMES = 529;
    public static final String TAG = "C2Mp3TimestampTracker";
    public long anchorTimestampUs;
    public long processedFrames;
    public boolean seenInvalidMpegAudioHeader;

    public final long getBufferTimestampUs(long j) {
        return Math.max(0L, ((this.processedFrames - 529) * 1000000) / j) + this.anchorTimestampUs;
    }

    public long getLastOutputBufferPresentationTimeUs(Format format) {
        return getBufferTimestampUs(format.sampleRate);
    }

    public void reset() {
        this.anchorTimestampUs = 0L;
        this.processedFrames = 0L;
        this.seenInvalidMpegAudioHeader = false;
    }

    public long updateAndGetPresentationTimeUs(Format format, DecoderInputBuffer decoderInputBuffer) {
        if (this.processedFrames == 0) {
            this.anchorTimestampUs = decoderInputBuffer.timeUs;
        }
        if (this.seenInvalidMpegAudioHeader) {
            return decoderInputBuffer.timeUs;
        }
        ByteBuffer byteBuffer = decoderInputBuffer.data;
        byteBuffer.getClass();
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i = (i << 8) | (byteBuffer.get(i2) & 255);
        }
        int mpegAudioFrameSampleCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(i);
        if (mpegAudioFrameSampleCount != -1) {
            long bufferTimestampUs = getBufferTimestampUs(format.sampleRate);
            this.processedFrames += (long) mpegAudioFrameSampleCount;
            return bufferTimestampUs;
        }
        this.seenInvalidMpegAudioHeader = true;
        this.processedFrames = 0L;
        this.anchorTimestampUs = decoderInputBuffer.timeUs;
        Log.w(TAG, "MPEG audio header is invalid.");
        return decoderInputBuffer.timeUs;
    }
}
