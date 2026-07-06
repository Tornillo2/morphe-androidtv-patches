package com.amazon.ion.impl.bin;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WriteBuffer implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BITS_PER_SURROGATE = 10;
    public static final char HIGH_SURROGATE_FIRST = 55296;
    public static final char HIGH_SURROGATE_LAST = 56319;
    public static final long INT16_SIGN_MASK = 32768;
    public static final long INT24_SIGN_MASK = 8388608;
    public static final long INT32_SIGN_MASK = 2147483648L;
    public static final long INT40_SIGN_MASK = 549755813888L;
    public static final long INT48_SIGN_MASK = 140737488355328L;
    public static final long INT56_SIGN_MASK = 36028797018963968L;
    public static final long INT64_SIGN_MASK = Long.MIN_VALUE;
    public static final long INT8_SIGN_MASK = 128;
    public static final char LOW_SURROGATE_FIRST = 56320;
    public static final char LOW_SURROGATE_LAST = 57343;
    public static final int OCTET_MASK = 255;
    public static final int SURROGATE_BASE = 65536;
    public static final int UINT_2_OCTET_SHIFT = 8;
    public static final int UINT_3_OCTET_SHIFT = 16;
    public static final int UINT_4_OCTET_SHIFT = 24;
    public static final int UINT_5_OCTET_SHIFT = 32;
    public static final int UINT_6_OCTET_SHIFT = 40;
    public static final int UINT_7_OCTET_SHIFT = 48;
    public static final int UINT_8_OCTET_SHIFT = 56;
    public static final int UTF8_2_OCTET_MIN_VALUE = 128;
    public static final int UTF8_2_OCTET_PREFIX_MASK = 192;
    public static final int UTF8_2_OCTET_SHIFT = 6;
    public static final int UTF8_3_OCTET_MIN_VALUE = 2048;
    public static final int UTF8_3_OCTET_PREFIX_MASK = 224;
    public static final int UTF8_3_OCTET_SHIFT = 12;
    public static final int UTF8_4_OCTET_PREFIX_MASK = 240;
    public static final int UTF8_4_OCTET_SHIFT = 18;
    public static final int UTF8_BITS_PER_FOLLOW_OCTET = 6;
    public static final int UTF8_FOLLOW_MASK = 63;
    public static final int UTF8_FOLLOW_PREFIX_MASK = 128;
    public static final long VAR_INT_10_OCTET_MIN_VALUE = 4611686018427387904L;
    public static final long VAR_INT_10_OCTET_SHIFT = 62;
    public static final long VAR_INT_2_OCTET_MIN_VALUE = 64;
    public static final long VAR_INT_3_OCTET_MIN_VALUE = 8192;
    public static final long VAR_INT_4_OCTET_MIN_VALUE = 1048576;
    public static final long VAR_INT_5_OCTET_MIN_VALUE = 134217728;
    public static final long VAR_INT_6_OCTET_MIN_VALUE = 17179869184L;
    public static final long VAR_INT_7_OCTET_MIN_VALUE = 2199023255552L;
    public static final long VAR_INT_8_OCTET_MIN_VALUE = 281474976710656L;
    public static final long VAR_INT_9_OCTET_MIN_VALUE = 36028797018963968L;
    public static final long VAR_INT_BITS_PER_OCTET = 7;
    public static final long VAR_INT_BITS_PER_SIGNED_OCTET = 6;
    public static final long VAR_INT_FINAL_OCTET_SIGNAL_MASK = 128;
    public static final long VAR_INT_MASK = 127;
    public static final long VAR_INT_SIGNBIT_OFF_MASK = 0;
    public static final long VAR_INT_SIGNBIT_ON_MASK = 64;
    public static final long VAR_INT_SIGNED_OCTET_MASK = 63;
    public static final long VAR_SINT_2_OCTET_SHIFT = 13;
    public static final long VAR_SINT_3_OCTET_SHIFT = 20;
    public static final long VAR_SINT_4_OCTET_SHIFT = 27;
    public static final long VAR_SINT_5_OCTET_SHIFT = 34;
    public static final long VAR_UINT_2_OCTET_MIN_VALUE = 128;
    public static final long VAR_UINT_2_OCTET_SHIFT = 7;
    public static final long VAR_UINT_3_OCTET_MIN_VALUE = 16384;
    public static final long VAR_UINT_3_OCTET_SHIFT = 14;
    public static final long VAR_UINT_4_OCTET_MIN_VALUE = 2097152;
    public static final long VAR_UINT_4_OCTET_SHIFT = 21;
    public static final long VAR_UINT_5_OCTET_MIN_VALUE = 268435456;
    public static final long VAR_UINT_5_OCTET_SHIFT = 28;
    public static final long VAR_UINT_6_OCTET_MIN_VALUE = 34359738368L;
    public static final long VAR_UINT_6_OCTET_SHIFT = 35;
    public static final long VAR_UINT_7_OCTET_MIN_VALUE = 4398046511104L;
    public static final long VAR_UINT_7_OCTET_SHIFT = 42;
    public static final long VAR_UINT_8_OCTET_MIN_VALUE = 562949953421312L;
    public static final long VAR_UINT_8_OCTET_SHIFT = 49;
    public static final long VAR_UINT_9_OCTET_MIN_VALUE = 72057594037927936L;
    public static final long VAR_UINT_9_OCTET_SHIFT = 56;
    public final BlockAllocator allocator;
    public final List<Block> blocks;
    public Block current;
    public int index;

    public WriteBuffer(BlockAllocator blockAllocator) {
        this.allocator = blockAllocator;
        ArrayList arrayList = new ArrayList();
        this.blocks = arrayList;
        allocateNewBlock();
        this.index = 0;
        this.current = (Block) arrayList.get(0);
    }

    public final void allocateNewBlock() {
        this.blocks.add(this.allocator.allocateBlock());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Iterator<Block> it = this.blocks.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.blocks.clear();
    }

    public int getUInt8At(long j) {
        int iIndex = index(j);
        return this.blocks.get(iIndex).data[offset(j)] & 255;
    }

    public final int index(long j) {
        return (int) (j / ((long) this.allocator.getBlockSize()));
    }

    public final int offset(long j) {
        return (int) (j % ((long) this.allocator.getBlockSize()));
    }

    public long position() {
        return (((long) this.index) * ((long) this.allocator.getBlockSize())) + ((long) this.current.limit);
    }

    public int remaining() {
        return this.current.remaining();
    }

    public void reset() {
        close();
        allocateNewBlock();
        this.index = 0;
        this.current = this.blocks.get(0);
    }

    public void truncate(long j) {
        int iIndex = index(j);
        int iOffset = offset(j);
        Block block = this.blocks.get(iIndex);
        this.index = iIndex;
        block.limit = iOffset;
        this.current = block;
    }

    public void writeByte(byte b) {
        if (this.current.remaining() < 1) {
            if (this.index == this.blocks.size() - 1) {
                allocateNewBlock();
            }
            int i = this.index + 1;
            this.index = i;
            this.current = this.blocks.get(i);
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i2 = block.limit;
        bArr[i2] = b;
        block.limit = i2 + 1;
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        if (i2 > this.current.remaining()) {
            writeBytesSlow(bArr, i, i2);
            return;
        }
        Block block = this.current;
        System.arraycopy(bArr, i, block.data, block.limit, i2);
        block.limit += i2;
    }

    public final void writeBytesSlow(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            Block block = this.current;
            int iMin = Math.min(i2, block.remaining());
            System.arraycopy(bArr, i, block.data, block.limit, iMin);
            block.limit += iMin;
            i += iMin;
            i2 -= iMin;
            if (block.remaining() == 0) {
                if (this.index == this.blocks.size() - 1) {
                    allocateNewBlock();
                }
                int i3 = this.index + 1;
                this.index = i3;
                this.current = this.blocks.get(i3);
            }
        }
    }

    public void writeInt16(long j) {
        if (j < 0) {
            j = (-j) | 32768;
        }
        writeUInt16(j);
    }

    public void writeInt24(long j) {
        if (j < 0) {
            j = (-j) | INT24_SIGN_MASK;
        }
        writeUInt24(j);
    }

    public void writeInt32(long j) {
        if (j < 0) {
            j = (-j) | INT32_SIGN_MASK;
        }
        writeUInt32(j);
    }

    public void writeInt40(long j) {
        if (j < 0) {
            j = (-j) | INT40_SIGN_MASK;
        }
        writeUInt40(j);
    }

    public void writeInt48(long j) {
        if (j < 0) {
            j = (-j) | INT48_SIGN_MASK;
        }
        writeUInt48(j);
    }

    public void writeInt56(long j) {
        if (j < 0) {
            j = (-j) | 36028797018963968L;
        }
        writeUInt56(j);
    }

    public void writeInt64(long j) {
        if (j < 0) {
            j = (-j) | Long.MIN_VALUE;
        }
        writeUInt64(j);
    }

    public void writeInt8(long j) {
        if (j < 0) {
            j = (-j) | 128;
        }
        writeUInt8(j);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        for (int i = 0; i <= this.index; i++) {
            Block block = this.blocks.get(i);
            outputStream.write(block.data, 0, block.limit);
        }
    }

    public void writeUInt16(long j) {
        if (this.current.remaining() < 2) {
            writeUInt16Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 8);
        bArr[i + 1] = (byte) j;
        block.limit = i + 2;
    }

    public final void writeUInt16Slow(long j) {
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt24(long j) {
        if (this.current.remaining() < 3) {
            writeUInt24Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 16);
        bArr[i + 1] = (byte) (j >> 8);
        bArr[i + 2] = (byte) j;
        block.limit = i + 3;
    }

    public final void writeUInt24Slow(long j) {
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt32(long j) {
        if (this.current.remaining() < 4) {
            writeUInt32Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 24);
        bArr[i + 1] = (byte) (j >> 16);
        bArr[i + 2] = (byte) (j >> 8);
        bArr[i + 3] = (byte) j;
        block.limit = i + 4;
    }

    public final void writeUInt32Slow(long j) {
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt40(long j) {
        if (this.current.remaining() < 5) {
            writeUInt40Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 32);
        bArr[i + 1] = (byte) (j >> 24);
        bArr[i + 2] = (byte) (j >> 16);
        bArr[i + 3] = (byte) (j >> 8);
        bArr[i + 4] = (byte) j;
        block.limit = i + 5;
    }

    public final void writeUInt40Slow(long j) {
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt48(long j) {
        if (this.current.remaining() < 6) {
            writeUInt48Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 40);
        bArr[i + 1] = (byte) (j >> 32);
        bArr[i + 2] = (byte) (j >> 24);
        bArr[i + 3] = (byte) (j >> 16);
        bArr[i + 4] = (byte) (j >> 8);
        bArr[i + 5] = (byte) j;
        block.limit = i + 6;
    }

    public final void writeUInt48Slow(long j) {
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt56(long j) {
        if (this.current.remaining() < 7) {
            writeUInt56Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 48);
        bArr[i + 1] = (byte) (j >> 40);
        bArr[i + 2] = (byte) (j >> 32);
        bArr[i + 3] = (byte) (j >> 24);
        bArr[i + 4] = (byte) (j >> 16);
        bArr[i + 5] = (byte) (j >> 8);
        bArr[i + 6] = (byte) j;
        block.limit = i + 7;
    }

    public final void writeUInt56Slow(long j) {
        writeByte((byte) (j >> 48));
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt64(long j) {
        if (this.current.remaining() < 8) {
            writeUInt64Slow(j);
            return;
        }
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) (j >> 56);
        bArr[i + 1] = (byte) (j >> 48);
        bArr[i + 2] = (byte) (j >> 40);
        bArr[i + 3] = (byte) (j >> 32);
        bArr[i + 4] = (byte) (j >> 24);
        bArr[i + 5] = (byte) (j >> 16);
        bArr[i + 6] = (byte) (j >> 8);
        bArr[i + 7] = (byte) j;
        block.limit = i + 8;
    }

    public final void writeUInt64Slow(long j) {
        writeByte((byte) (j >> 56));
        writeByte((byte) (j >> 48));
        writeByte((byte) (j >> 40));
        writeByte((byte) (j >> 32));
        writeByte((byte) (j >> 24));
        writeByte((byte) (j >> 16));
        writeByte((byte) (j >> 8));
        writeByte((byte) j);
    }

    public void writeUInt8(long j) {
        writeByte((byte) j);
    }

    public void writeUInt8At(long j, long j2) {
        this.blocks.get(index(j)).data[offset(j)] = (byte) j2;
    }

    public int writeUTF8(CharSequence charSequence, int i, int i2) {
        int iWriteUTF8UpTo3Byte;
        if (i2 > this.current.remaining()) {
            return writeUTF8Slow(charSequence, i, i2);
        }
        Block block = this.current;
        int i3 = block.limit;
        int i4 = 0;
        char cCharAt = 0;
        while (i2 > 0) {
            cCharAt = charSequence.charAt(i);
            if (cCharAt >= 128) {
                break;
            }
            block.data[i3] = (byte) cCharAt;
            i4++;
            i++;
            i2--;
            i3++;
        }
        block.limit = i3;
        if (i2 <= 0) {
            return i4;
        }
        if (cCharAt < 2048) {
            iWriteUTF8UpTo3Byte = writeUTF8UpTo2Byte(charSequence, i, i2);
        } else {
            if (cCharAt >= 56320 && cCharAt <= 57343) {
                throw new IllegalArgumentException("Unpaired low surrogate: " + cCharAt);
            }
            iWriteUTF8UpTo3Byte = (cCharAt < 55296 || cCharAt > 56319) ? writeUTF8UpTo3Byte(charSequence, i, i2) : writeUTF8Slow(charSequence, i, i2);
        }
        return iWriteUTF8UpTo3Byte + i4;
    }

    public final int writeUTF8Slow(CharSequence charSequence, int i, int i2) {
        int i3 = 0;
        while (i2 > 0) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 56320 && cCharAt <= 57343) {
                throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unpaired low surrogate: ", cCharAt));
            }
            if (cCharAt >= 55296 && cCharAt <= 56319) {
                i++;
                i2--;
                if (i2 == 0) {
                    throw new IllegalArgumentException("Unpaired low surrogate at end of character sequence: " + cCharAt);
                }
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt2 < 56320 || cCharAt2 > 57343) {
                    throw new IllegalArgumentException("Low surrogate with unpaired high surrogate: " + cCharAt + " + " + ((int) cCharAt2));
                }
                int i4 = (((cCharAt - 55296) << 10) | (cCharAt2 - 56320)) + 65536;
                writeByte((byte) ((i4 >> 18) | 240));
                writeByte((byte) (((i4 >> 12) & 63) | 128));
                writeByte((byte) (((i4 >> 6) & 63) | 128));
                writeByte((byte) ((i4 & 63) | 128));
                i3 += 4;
            } else if (cCharAt < 128) {
                writeByte((byte) cCharAt);
                i3++;
            } else if (cCharAt < 2048) {
                writeByte((byte) ((cCharAt >> 6) | 192));
                writeByte((byte) ((cCharAt & '?') | 128));
                i3 += 2;
            } else {
                writeByte((byte) ((cCharAt >> '\f') | 224));
                writeByte((byte) (((cCharAt >> 6) & 63) | 128));
                writeByte((byte) ((cCharAt & '?') | 128));
                i3 += 3;
            }
            i++;
            i2--;
        }
        return i3;
    }

    public final int writeUTF8UpTo2Byte(CharSequence charSequence, int i, int i2) {
        if (i2 * 2 > this.current.remaining()) {
            return writeUTF8Slow(charSequence, i, i2);
        }
        Block block = this.current;
        int i3 = block.limit;
        char cCharAt = 0;
        int i4 = 0;
        while (i2 > 0) {
            cCharAt = charSequence.charAt(i);
            if (cCharAt >= 2048) {
                break;
            }
            if (cCharAt < 128) {
                block.data[i3] = (byte) cCharAt;
                i4++;
                i3++;
            } else {
                byte[] bArr = block.data;
                int i5 = i3 + 1;
                bArr[i3] = (byte) ((cCharAt >> 6) | 192);
                i3 += 2;
                bArr[i5] = (byte) (128 | (cCharAt & '?'));
                i4 += 2;
            }
            i++;
            i2--;
        }
        block.limit = i3;
        if (i2 <= 0) {
            return i4;
        }
        if (cCharAt < 56320 || cCharAt > 57343) {
            return ((cCharAt < 55296 || cCharAt > 56319) ? writeUTF8UpTo3Byte(charSequence, i, i2) : writeUTF8Slow(charSequence, i, i2)) + i4;
        }
        throw new IllegalArgumentException("Unpaired low surrogate: " + cCharAt);
    }

    public final int writeUTF8UpTo3Byte(CharSequence charSequence, int i, int i2) {
        if (i2 * 3 > this.current.remaining()) {
            return writeUTF8Slow(charSequence, i, i2);
        }
        Block block = this.current;
        int i3 = block.limit;
        int i4 = 0;
        while (i2 > 0) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 56320 && cCharAt <= 57343) {
                throw new IllegalArgumentException("Unpaired low surrogate: " + cCharAt);
            }
            if (cCharAt >= 55296 && cCharAt <= 56319) {
                break;
            }
            if (cCharAt < 128) {
                block.data[i3] = (byte) cCharAt;
                i4++;
                i3++;
            } else if (cCharAt < 2048) {
                byte[] bArr = block.data;
                int i5 = i3 + 1;
                bArr[i3] = (byte) ((cCharAt >> 6) | 192);
                i3 += 2;
                bArr[i5] = (byte) ((cCharAt & '?') | 128);
                i4 += 2;
            } else {
                byte[] bArr2 = block.data;
                bArr2[i3] = (byte) ((cCharAt >> '\f') | 224);
                int i6 = i3 + 2;
                bArr2[i3 + 1] = (byte) (((cCharAt >> 6) & 63) | 128);
                i3 += 3;
                bArr2[i6] = (byte) ((cCharAt & '?') | 128);
                i4 += 3;
            }
            i++;
            i2--;
        }
        block.limit = i3;
        return i2 > 0 ? writeUTF8Slow(charSequence, i, i2) + i4 : i4;
    }

    public int writeVarInt(long j) {
        long j2 = j < 0 ? 64L : 0L;
        if (j < 0) {
            j = -j;
        }
        if (j < 64) {
            writeUInt8((j & 63) | 128 | j2);
            return 1;
        }
        long j3 = j < 0 ? 1L : 0L;
        int iRemaining = this.current.remaining();
        if (j < 8192 && iRemaining >= 2) {
            writeVarUIntDirect2(j | (j3 << 13));
            return 2;
        }
        if (j < 1048576 && iRemaining >= 3) {
            writeVarUIntDirect3(j | (j3 << 20));
            return 3;
        }
        if (j < VAR_INT_5_OCTET_MIN_VALUE && iRemaining >= 4) {
            writeVarUIntDirect4(j | (j3 << 27));
            return 4;
        }
        if (j >= 17179869184L || iRemaining < 5) {
            return writeVarIntSlow(j, j2);
        }
        writeVarUIntDirect5(j | (j3 << 34));
        return 5;
    }

    public final int writeVarIntSlow(long j, long j2) {
        int i;
        if (j >= 4611686018427387904L) {
            writeUInt8(((j >> 62) & 63) | j2);
            i = 2;
        } else {
            i = 1;
        }
        if (j >= 36028797018963968L) {
            long j3 = j >> 56;
            writeUInt8(i == 1 ? (j3 & 63) | j2 : j3 & 127);
            i++;
        }
        if (j >= VAR_INT_8_OCTET_MIN_VALUE) {
            long j4 = j >> 49;
            writeUInt8(i == 1 ? (j4 & 63) | j2 : j4 & 127);
            i++;
        }
        if (j >= VAR_INT_7_OCTET_MIN_VALUE) {
            long j5 = j >> 42;
            writeUInt8(i == 1 ? (j5 & 63) | j2 : j5 & 127);
            i++;
        }
        if (j >= 17179869184L) {
            long j6 = j >> 35;
            writeUInt8(i == 1 ? (j6 & 63) | j2 : j6 & 127);
            i++;
        }
        if (j >= VAR_INT_5_OCTET_MIN_VALUE) {
            long j7 = j >> 28;
            writeUInt8(i == 1 ? (j7 & 63) | j2 : j7 & 127);
            i++;
        }
        if (j >= 1048576) {
            long j8 = j >> 21;
            writeUInt8(i == 1 ? (j8 & 63) | j2 : j8 & 127);
            i++;
        }
        if (j >= 8192) {
            long j9 = j >> 14;
            writeUInt8(i == 1 ? (j9 & 63) | j2 : j9 & 127);
            i++;
        }
        if (j >= 64) {
            long j10 = j >> 7;
            writeUInt8(i == 1 ? (j10 & 63) | j2 : j10 & 127);
            i++;
        }
        writeUInt8((i == 1 ? (j & 63) | j2 : j & 127) | 128);
        return i;
    }

    public int writeVarUInt(long j) {
        if (j < 128) {
            writeUInt8((j & 127) | 128);
            return 1;
        }
        if (j < 16384) {
            if (this.current.remaining() < 2) {
                return writeVarUIntSlow(j);
            }
            writeVarUIntDirect2(j);
            return 2;
        }
        if (j < 2097152) {
            if (this.current.remaining() < 3) {
                return writeVarUIntSlow(j);
            }
            writeVarUIntDirect3(j);
            return 3;
        }
        if (j < VAR_UINT_5_OCTET_MIN_VALUE) {
            if (this.current.remaining() < 4) {
                return writeVarUIntSlow(j);
            }
            writeVarUIntDirect4(j);
            return 4;
        }
        if (j >= VAR_UINT_6_OCTET_MIN_VALUE) {
            return writeVarUIntSlow(j);
        }
        if (this.current.remaining() < 5) {
            return writeVarUIntSlow(j);
        }
        writeVarUIntDirect5(j);
        return 5;
    }

    public void writeVarUIntDirect1At(long j, long j2) {
        writeUInt8At(j, (j2 & 127) | 128);
    }

    public final int writeVarUIntDirect2(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) ((j >> 7) & 127);
        bArr[i + 1] = (byte) ((j & 127) | 128);
        block.limit = i + 2;
        return 2;
    }

    public void writeVarUIntDirect2At(long j, long j2) {
        int iIndex = index(j);
        int iOffset = offset(j);
        if (iOffset + 2 > this.allocator.getBlockSize()) {
            writeVarUIntDirect2StraddlingAt(iIndex, iOffset, j2);
            return;
        }
        byte[] bArr = this.blocks.get(iIndex).data;
        bArr[iOffset] = (byte) ((j2 >> 7) & 127);
        bArr[iOffset + 1] = (byte) ((j2 & 127) | 128);
    }

    public final void writeVarUIntDirect2StraddlingAt(int i, int i2, long j) {
        this.blocks.get(i).data[i2] = (byte) ((j >> 7) & 127);
        this.blocks.get(i + 1).data[0] = (byte) ((j & 127) | 128);
    }

    public final int writeVarUIntDirect3(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) ((j >> 14) & 127);
        bArr[i + 1] = (byte) ((j >> 7) & 127);
        bArr[i + 2] = (byte) ((j & 127) | 128);
        block.limit = i + 3;
        return 3;
    }

    public final int writeVarUIntDirect4(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) ((j >> 21) & 127);
        bArr[i + 1] = (byte) ((j >> 14) & 127);
        bArr[i + 2] = (byte) ((j >> 7) & 127);
        bArr[i + 3] = (byte) ((j & 127) | 128);
        block.limit = i + 4;
        return 4;
    }

    public final int writeVarUIntDirect5(long j) {
        Block block = this.current;
        byte[] bArr = block.data;
        int i = block.limit;
        bArr[i] = (byte) ((j >> 28) & 127);
        bArr[i + 1] = (byte) ((j >> 21) & 127);
        bArr[i + 2] = (byte) ((j >> 14) & 127);
        bArr[i + 3] = (byte) ((j >> 7) & 127);
        bArr[i + 4] = (byte) ((j & 127) | 128);
        block.limit = i + 5;
        return 5;
    }

    public final int writeVarUIntSlow(long j) {
        int i;
        if (j >= VAR_UINT_9_OCTET_MIN_VALUE) {
            writeUInt8((j >> 56) & 127);
            i = 2;
        } else {
            i = 1;
        }
        if (j >= VAR_UINT_8_OCTET_MIN_VALUE) {
            writeUInt8((j >> 49) & 127);
            i++;
        }
        if (j >= VAR_UINT_7_OCTET_MIN_VALUE) {
            writeUInt8((j >> 42) & 127);
            i++;
        }
        if (j >= VAR_UINT_6_OCTET_MIN_VALUE) {
            writeUInt8((j >> 35) & 127);
            i++;
        }
        if (j >= VAR_UINT_5_OCTET_MIN_VALUE) {
            writeUInt8((j >> 28) & 127);
            i++;
        }
        if (j >= 2097152) {
            writeUInt8((j >> 21) & 127);
            i++;
        }
        if (j >= 16384) {
            writeUInt8((j >> 14) & 127);
            i++;
        }
        if (j >= 128) {
            writeUInt8((j >> 7) & 127);
            i++;
        }
        writeUInt8((j & 127) | 128);
        return i;
    }

    public void writeTo(OutputStream outputStream, long j, long j2) throws IOException {
        while (j2 > 0) {
            int iIndex = index(j);
            int iOffset = offset(j);
            Block block = this.blocks.get(iIndex);
            int iMin = (int) Math.min(block.data.length - iOffset, j2);
            outputStream.write(block.data, iOffset, iMin);
            long j3 = iMin;
            j += j3;
            j2 -= j3;
        }
    }

    public void writeBytes(byte[] bArr) {
        writeBytes(bArr, 0, bArr.length);
    }

    public int writeUTF8(CharSequence charSequence) {
        return writeUTF8(charSequence, 0, charSequence.length());
    }
}
