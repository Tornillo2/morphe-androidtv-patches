package com.amazon.avod.mpb.media.playback.source;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ParsableNalUnitBitArray {
    public int bitOffset;
    public int byteLimit;
    public int byteOffset;

    @NotNull
    public byte[] data;

    public ParsableNalUnitBitArray(@NotNull byte[] data, int i, int i2) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.byteOffset = i;
        this.byteLimit = i2;
        assertValidOffset();
    }

    public final void assertValidOffset() {
        int i = this.byteOffset;
        if (i >= 0) {
            int i2 = this.byteLimit;
            if (i < i2) {
                return;
            }
            if (i == i2 && this.bitOffset == 0) {
                return;
            }
        }
        throw new IllegalStateException("Check failed.");
    }

    public final boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public final int readBits(int i) {
        int i2;
        this.bitOffset += i;
        int i3 = 0;
        while (true) {
            i2 = this.bitOffset;
            if (i2 <= 8) {
                break;
            }
            int i4 = i2 - 8;
            this.bitOffset = i4;
            byte[] bArr = this.data;
            int i5 = this.byteOffset;
            i3 |= (bArr[i5] & 255) << i4;
            if (!shouldSkipByte(i5 + 1)) {
                i = 1;
            }
            this.byteOffset = i5 + i;
        }
        byte[] bArr2 = this.data;
        int i6 = this.byteOffset;
        int i7 = ((-1) >>> (32 - i)) & (i3 | ((bArr2[i6] & 255) >> (8 - i2)));
        if (i2 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i6 + (shouldSkipByte(i6 + 1) ? 2 : 1);
        }
        assertValidOffset();
        return i7;
    }

    public final int readExpGolombCodeNum() {
        int i = 0;
        while (!readBit()) {
            i++;
        }
        return ((1 << i) - 1) + (i > 0 ? readBits(i) : 0);
    }

    public final int readSignedExpGolombCodedInt() {
        int expGolombCodeNum = readExpGolombCodeNum();
        return ((expGolombCodeNum + 1) / 2) * (expGolombCodeNum % 2 == 0 ? -1 : 1);
    }

    public final int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public final boolean shouldSkipByte(int i) {
        if (2 > i || i >= this.byteLimit) {
            return false;
        }
        byte[] bArr = this.data;
        return bArr[i] == 3 && bArr[i + (-2)] == 0 && bArr[i - 1] == 0;
    }

    public final void skipBit() {
        int i = this.bitOffset + 1;
        this.bitOffset = i;
        if (i == 8) {
            this.bitOffset = 0;
            int i2 = this.byteOffset;
            this.byteOffset = i2 + (shouldSkipByte(i2 + 1) ? 2 : 1);
        }
        assertValidOffset();
    }

    public final void skipBits(int i) {
        int i2 = this.byteOffset;
        int i3 = i / 8;
        int i4 = i2 + i3;
        this.byteOffset = i4;
        int i5 = (i - (i3 * 8)) + this.bitOffset;
        this.bitOffset = i5;
        if (i5 > 7) {
            this.byteOffset = i4 + 1;
            this.bitOffset = i5 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.byteOffset) {
                assertValidOffset();
                return;
            } else if (shouldSkipByte(i2)) {
                this.byteOffset++;
                i2 += 2;
            }
        }
    }
}
