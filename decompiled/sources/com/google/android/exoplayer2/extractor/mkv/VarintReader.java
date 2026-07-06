package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class VarintReader {
    public static final int STATE_BEGIN_READING = 0;
    public static final int STATE_READ_CONTENTS = 1;
    public static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    public int length;
    public final byte[] scratch = new byte[8];
    public int state;

    public static long assembleVarint(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= ~VARINT_LENGTH_MASKS[i - 1];
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }

    public static int parseUnsignedVarintLength(int i) {
        int i2 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (i2 >= jArr.length) {
                return -1;
            }
            if ((jArr[i2] & ((long) i)) != 0) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z2, int i) throws IOException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1L;
            }
            int unsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & 255);
            this.length = unsignedVarintLength;
            if (unsignedVarintLength == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int i2 = this.length;
        if (i2 > i) {
            this.state = 0;
            return -2L;
        }
        if (i2 != 1) {
            extractorInput.readFully(this.scratch, 1, i2 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z2);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
