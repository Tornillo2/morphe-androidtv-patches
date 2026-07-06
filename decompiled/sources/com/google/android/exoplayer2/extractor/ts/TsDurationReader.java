package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TsDurationReader {
    public static final String TAG = "TsDurationReader";
    public boolean isDurationRead;
    public boolean isFirstPcrValueRead;
    public boolean isLastPcrValueRead;
    public final int timestampSearchBytes;
    public final TimestampAdjuster pcrTimestampAdjuster = new TimestampAdjuster(0);
    public long firstPcrValue = -9223372036854775807L;
    public long lastPcrValue = -9223372036854775807L;
    public long durationUs = -9223372036854775807L;
    public final ParsableByteArray packetBuffer = new ParsableByteArray();

    public TsDurationReader(int i) {
        this.timestampSearchBytes = i;
    }

    public final int finishReadDuration(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = this.packetBuffer;
        byte[] bArr = Util.EMPTY_BYTE_ARRAY;
        parsableByteArray.getClass();
        parsableByteArray.reset(bArr, bArr.length);
        this.isDurationRead = true;
        extractorInput.resetPeekPosition();
        return 0;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getPcrTimestampAdjuster() {
        return this.pcrTimestampAdjuster;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput extractorInput, PositionHolder positionHolder, int i) throws IOException {
        if (i <= 0) {
            finishReadDuration(extractorInput);
            return 0;
        }
        if (!this.isLastPcrValueRead) {
            return readLastPcrValue(extractorInput, positionHolder, i);
        }
        if (this.lastPcrValue == -9223372036854775807L) {
            finishReadDuration(extractorInput);
            return 0;
        }
        if (!this.isFirstPcrValueRead) {
            return readFirstPcrValue(extractorInput, positionHolder, i);
        }
        long j = this.firstPcrValue;
        if (j == -9223372036854775807L) {
            finishReadDuration(extractorInput);
            return 0;
        }
        long jAdjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(this.lastPcrValue) - this.pcrTimestampAdjuster.adjustTsTimestamp(j);
        this.durationUs = jAdjustTsTimestamp;
        if (jAdjustTsTimestamp < 0) {
            Log.w("TsDurationReader", "Invalid duration: " + this.durationUs + ". Using TIME_UNSET instead.");
            this.durationUs = -9223372036854775807L;
        }
        finishReadDuration(extractorInput);
        return 0;
    }

    public final int readFirstPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i) throws IOException {
        int iMin = (int) Math.min(this.timestampSearchBytes, extractorInput.getLength());
        long j = 0;
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(iMin);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.data, 0, iMin);
        this.firstPcrValue = readFirstPcrValueFromBuffer(this.packetBuffer, i);
        this.isFirstPcrValueRead = true;
        return 0;
    }

    public final long readFirstPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i) {
        int i2 = parsableByteArray.limit;
        for (int i3 = parsableByteArray.position; i3 < i2; i3++) {
            if (parsableByteArray.data[i3] == 71) {
                long pcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, i3, i);
                if (pcrFromPacket != -9223372036854775807L) {
                    return pcrFromPacket;
                }
            }
        }
        return -9223372036854775807L;
    }

    public final int readLastPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i) throws IOException {
        long length = extractorInput.getLength();
        int iMin = (int) Math.min(this.timestampSearchBytes, length);
        long j = length - ((long) iMin);
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(iMin);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.data, 0, iMin);
        this.lastPcrValue = readLastPcrValueFromBuffer(this.packetBuffer, i);
        this.isLastPcrValueRead = true;
        return 0;
    }

    public final long readLastPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i) {
        int i2 = parsableByteArray.position;
        int i3 = parsableByteArray.limit;
        for (int i4 = i3 - 188; i4 >= i2; i4--) {
            if (TsUtil.isStartOfTsPacket(parsableByteArray.data, i2, i3, i4)) {
                long pcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, i4, i);
                if (pcrFromPacket != -9223372036854775807L) {
                    return pcrFromPacket;
                }
            }
        }
        return -9223372036854775807L;
    }
}
