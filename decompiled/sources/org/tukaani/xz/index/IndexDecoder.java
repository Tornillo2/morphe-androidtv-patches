package org.tukaani.xz.index;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SeekableInputStream;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;

/* JADX INFO: loaded from: classes4.dex */
public class IndexDecoder extends IndexBase {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static /* synthetic */ Class class$org$tukaani$xz$index$IndexDecoder;
    public final BlockInfo info;
    public long largestBlockSize;
    public final int memoryUsage;
    public int pos;
    public final long streamPadding;
    public final long[] uncompressed;
    public final long[] unpadded;

    static {
        if (class$org$tukaani$xz$index$IndexDecoder == null) {
            class$org$tukaani$xz$index$IndexDecoder = class$("org.tukaani.xz.index.IndexDecoder");
        }
        $assertionsDisabled = true;
    }

    public IndexDecoder(SeekableInputStream seekableInputStream, StreamFlags streamFlags, long j, int i) throws IOException {
        super(new CorruptedInputException("XZ Index is corrupt"));
        BlockInfo blockInfo = new BlockInfo();
        this.info = blockInfo;
        this.largestBlockSize = 0L;
        this.pos = -1;
        blockInfo.streamFlags = streamFlags;
        this.streamPadding = j;
        long jPosition = (seekableInputStream.position() + streamFlags.backwardSize) - 4;
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedInputStream = new CheckedInputStream(seekableInputStream, crc32);
        if (checkedInputStream.read() != 0) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        try {
            long jDecodeVLI = DecoderUtil.decodeVLI(checkedInputStream);
            if (jDecodeVLI >= streamFlags.backwardSize / 2) {
                throw new CorruptedInputException("XZ Index is corrupt");
            }
            if (jDecodeVLI > 2147483647L) {
                throw new UnsupportedOptionsException("XZ Index has over 2147483647 Records");
            }
            int i2 = ((int) (((16 * jDecodeVLI) + 1023) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) + 1;
            this.memoryUsage = i2;
            if (i >= 0 && i2 > i) {
                throw new MemoryLimitException(i2, i);
            }
            int i3 = (int) jDecodeVLI;
            this.unpadded = new long[i3];
            this.uncompressed = new long[i3];
            int i4 = 0;
            while (i3 > 0) {
                long jDecodeVLI2 = DecoderUtil.decodeVLI(checkedInputStream);
                long jDecodeVLI3 = DecoderUtil.decodeVLI(checkedInputStream);
                if (seekableInputStream.position() > jPosition) {
                    throw new CorruptedInputException("XZ Index is corrupt");
                }
                CRC32 crc322 = crc32;
                this.unpadded[i4] = this.blocksSum + jDecodeVLI2;
                this.uncompressed[i4] = this.uncompressedSum + jDecodeVLI3;
                i4++;
                super.add(jDecodeVLI2, jDecodeVLI3);
                if (!$assertionsDisabled && i4 != this.recordCount) {
                    throw new AssertionError();
                }
                if (this.largestBlockSize < jDecodeVLI3) {
                    this.largestBlockSize = jDecodeVLI3;
                }
                i3--;
                crc32 = crc322;
            }
            CRC32 crc323 = crc32;
            int indexPaddingSize = getIndexPaddingSize();
            if (seekableInputStream.position() + ((long) indexPaddingSize) != jPosition) {
                throw new CorruptedInputException("XZ Index is corrupt");
            }
            while (true) {
                int i5 = indexPaddingSize - 1;
                if (indexPaddingSize <= 0) {
                    long value = crc323.getValue();
                    for (int i6 = 0; i6 < 4; i6++) {
                        if (((value >>> (i6 * 8)) & 255) != seekableInputStream.read()) {
                            throw new CorruptedInputException("XZ Index is corrupt");
                        }
                    }
                    return;
                }
                if (checkedInputStream.read() != 0) {
                    throw new CorruptedInputException("XZ Index is corrupt");
                }
                indexPaddingSize = i5;
            }
        } catch (EOFException unused) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public final BlockInfo getInfo() {
        int i = this.pos;
        BlockInfo blockInfo = this.info;
        if (i == 0) {
            blockInfo.compressedOffset = 0L;
            blockInfo.uncompressedOffset = 0L;
        } else {
            blockInfo.compressedOffset = (this.unpadded[i - 1] + 3) & (-4);
            blockInfo.uncompressedOffset = this.uncompressed[i - 1];
        }
        BlockInfo blockInfo2 = this.info;
        long j = this.unpadded[i];
        long j2 = blockInfo2.compressedOffset;
        blockInfo2.unpaddedSize = j - j2;
        blockInfo2.uncompressedSize = this.uncompressed[i] - blockInfo2.uncompressedOffset;
        blockInfo2.compressedOffset = j2 + 12;
        return blockInfo2;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getMemoryUsage() {
        return this.memoryUsage;
    }

    public BlockInfo getNext() {
        this.pos++;
        return getInfo();
    }

    public long getStreamAndPaddingSize() {
        return getStreamSize() + this.streamPadding;
    }

    public long getUncompressedSize() {
        return this.uncompressedSum;
    }

    public boolean hasNext() {
        return ((long) (this.pos + 1)) < this.recordCount;
    }

    public BlockInfo locate(long j) {
        if (!$assertionsDisabled && j >= this.uncompressedSum) {
            throw new AssertionError();
        }
        int length = this.unpadded.length - 1;
        int i = 0;
        while (i < length) {
            int i2 = ((length - i) / 2) + i;
            if (this.uncompressed[i2] <= j) {
                i = i2 + 1;
            } else {
                length = i2;
            }
        }
        this.pos = i;
        return getInfo();
    }
}
