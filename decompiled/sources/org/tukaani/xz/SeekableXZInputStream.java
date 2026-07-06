package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;

/* JADX INFO: loaded from: classes4.dex */
public class SeekableXZInputStream extends SeekableInputStream {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static /* synthetic */ Class class$org$tukaani$xz$SeekableXZInputStream;
    public BlockInputStream blockDecoder;
    public Check check;
    public int checkTypes;
    public long curPos;
    public boolean endReached;
    public IOException exception;
    public SeekableInputStream in;
    public IndexDecoder index;
    public int indexMemoryUsage;
    public long largestBlockSize;
    public final int memoryLimit;
    public boolean seekNeeded;
    public long seekPos;
    public final ArrayList streams;
    public long uncompressedSize;

    static {
        if (class$org$tukaani$xz$SeekableXZInputStream == null) {
            class$org$tukaani$xz$SeekableXZInputStream = class$("org.tukaani.xz.SeekableXZInputStream");
        }
        $assertionsDisabled = true;
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream) throws IOException {
        this(seekableInputStream, -1);
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        BlockInputStream blockInputStream;
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.endReached || this.seekNeeded || (blockInputStream = this.blockDecoder) == null) {
            return 0;
        }
        return blockInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        SeekableInputStream seekableInputStream = this.in;
        if (seekableInputStream != null) {
            try {
                seekableInputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public int getCheckTypes() {
        return this.checkTypes;
    }

    public int getIndexMemoryUsage() {
        return this.indexMemoryUsage;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public final void initBlockDecoder(BlockInfo blockInfo) throws IOException {
        try {
            this.blockDecoder = null;
            this.blockDecoder = new BlockInputStream(this.in, this.check, this.memoryLimit, blockInfo.unpaddedSize, blockInfo.uncompressedSize);
        } catch (IndexIndicatorException unused) {
            throw new CorruptedInputException();
        } catch (MemoryLimitException e) {
            if (!$assertionsDisabled && this.memoryLimit < 0) {
                throw new AssertionError();
            }
            int memoryNeeded = e.getMemoryNeeded();
            int i = this.indexMemoryUsage;
            throw new MemoryLimitException(memoryNeeded + i, this.memoryLimit + i);
        }
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() {
        return this.uncompressedSize;
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        if (this.in != null) {
            return this.seekNeeded ? this.seekPos : this.curPos;
        }
        throw new XZIOException("Stream closed");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public final void seek() throws IOException {
        if (!this.seekNeeded) {
            if (this.index.hasNext()) {
                initBlockDecoder(this.index.getNext());
                return;
            }
            this.seekPos = this.curPos;
        }
        this.seekNeeded = false;
        long j = this.seekPos;
        if (j >= this.uncompressedSize) {
            this.curPos = j;
            this.blockDecoder = null;
            this.endReached = true;
            return;
        }
        this.endReached = false;
        int size = this.streams.size();
        if (!$assertionsDisabled && size < 1) {
            throw new AssertionError();
        }
        long uncompressedSize = 0;
        long streamAndPaddingSize = 0;
        while (true) {
            size--;
            IndexDecoder indexDecoder = (IndexDecoder) this.streams.get(size);
            this.index = indexDecoder;
            long uncompressedSize2 = indexDecoder.getUncompressedSize() + uncompressedSize;
            long j2 = this.seekPos;
            if (uncompressedSize2 > j2) {
                BlockInfo blockInfoLocate = this.index.locate(j2 - uncompressedSize);
                boolean z = $assertionsDisabled;
                if (!z && (blockInfoLocate.compressedOffset & 3) != 0) {
                    throw new AssertionError(blockInfoLocate.compressedOffset);
                }
                long j3 = blockInfoLocate.compressedOffset + streamAndPaddingSize;
                blockInfoLocate.compressedOffset = j3;
                long j4 = blockInfoLocate.uncompressedOffset + uncompressedSize;
                blockInfoLocate.uncompressedOffset = j4;
                if (!z && this.seekPos < j4) {
                    throw new AssertionError();
                }
                if (!z && this.seekPos >= blockInfoLocate.uncompressedSize + j4) {
                    throw new AssertionError();
                }
                long j5 = this.curPos;
                if (j5 <= j4 || j5 > this.seekPos) {
                    this.in.seek(j3);
                    this.check = Check.getInstance(blockInfoLocate.streamFlags.checkType);
                    initBlockDecoder(blockInfoLocate);
                    this.curPos = blockInfoLocate.uncompressedOffset;
                }
                long j6 = this.seekPos;
                long j7 = this.curPos;
                if (j6 > j7) {
                    long j8 = j6 - j7;
                    if (this.blockDecoder.skip(j8) != j8) {
                        throw new CorruptedInputException();
                    }
                }
                this.curPos = this.seekPos;
                return;
            }
            uncompressedSize += this.index.getUncompressedSize();
            streamAndPaddingSize += this.index.getStreamAndPaddingSize();
            if (!$assertionsDisabled && (streamAndPaddingSize & 3) != 0) {
                throw new AssertionError();
            }
        }
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i) throws IOException {
        this.indexMemoryUsage = 0;
        this.streams = new ArrayList();
        this.checkTypes = 0;
        this.blockDecoder = null;
        long j = 0;
        this.uncompressedSize = 0L;
        this.largestBlockSize = 0L;
        this.curPos = 0L;
        this.seekNeeded = false;
        this.endReached = false;
        this.exception = null;
        this.in = seekableInputStream;
        DataInputStream dataInputStream = new DataInputStream(seekableInputStream);
        seekableInputStream.seek(0L);
        byte[] bArr = XZ.HEADER_MAGIC;
        byte[] bArr2 = new byte[bArr.length];
        dataInputStream.readFully(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            throw new XZFormatException();
        }
        long length = seekableInputStream.length();
        if ((3 & length) != 0) {
            throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes");
        }
        byte[] bArr3 = new byte[12];
        int memoryUsage = i;
        while (true) {
            long j2 = j;
            while (length > j) {
                if (length < 12) {
                    throw new CorruptedInputException();
                }
                long j3 = j;
                long j4 = length - 12;
                seekableInputStream.seek(j4);
                dataInputStream.readFully(bArr3);
                if (bArr3[8] == 0 && bArr3[9] == 0 && bArr3[10] == 0 && bArr3[11] == 0) {
                    j2 += 4;
                    length -= 4;
                    j = j3;
                } else {
                    StreamFlags streamFlagsDecodeStreamFooter = DecoderUtil.decodeStreamFooter(bArr3);
                    if (streamFlagsDecodeStreamFooter.backwardSize >= j4) {
                        throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big");
                    }
                    this.check = Check.getInstance(streamFlagsDecodeStreamFooter.checkType);
                    this.checkTypes |= 1 << streamFlagsDecodeStreamFooter.checkType;
                    seekableInputStream.seek(j4 - streamFlagsDecodeStreamFooter.backwardSize);
                    try {
                        IndexDecoder indexDecoder = new IndexDecoder(seekableInputStream, streamFlagsDecodeStreamFooter, j2, memoryUsage);
                        this.index = indexDecoder;
                        this.indexMemoryUsage = indexDecoder.memoryUsage + this.indexMemoryUsage;
                        if (memoryUsage >= 0) {
                            memoryUsage -= indexDecoder.getMemoryUsage();
                            if (!$assertionsDisabled && memoryUsage < 0) {
                                throw new AssertionError();
                            }
                        }
                        if (this.largestBlockSize < this.index.getLargestBlockSize()) {
                            this.largestBlockSize = this.index.getLargestBlockSize();
                        }
                        long streamSize = this.index.getStreamSize() - 12;
                        if (j4 < streamSize) {
                            throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream");
                        }
                        long j5 = j4 - streamSize;
                        seekableInputStream.seek(j5);
                        dataInputStream.readFully(bArr3);
                        if (DecoderUtil.decodeStreamHeader(bArr3).checkType != streamFlagsDecodeStreamFooter.checkType) {
                            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
                        }
                        long uncompressedSize = this.index.getUncompressedSize() + this.uncompressedSize;
                        this.uncompressedSize = uncompressedSize;
                        if (uncompressedSize < j3) {
                            throw new UnsupportedOptionsException("XZ file is too big");
                        }
                        this.streams.add(this.index);
                        length = j5;
                        j = j3;
                    } catch (MemoryLimitException e) {
                        if (!$assertionsDisabled && memoryUsage < 0) {
                            throw new AssertionError();
                        }
                        int memoryNeeded = e.getMemoryNeeded();
                        int i2 = this.indexMemoryUsage;
                        throw new MemoryLimitException(memoryNeeded + i2, memoryUsage + i2);
                    }
                }
            }
            if (!$assertionsDisabled && length != j) {
                throw new AssertionError();
            }
            this.memoryLimit = memoryUsage;
            return;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        try {
            if (this.seekNeeded) {
                seek();
            }
            if (this.endReached) {
                return -1;
            }
            while (i2 > 0) {
                if (this.blockDecoder == null) {
                    seek();
                    if (this.endReached) {
                        break;
                    }
                }
                int i5 = this.blockDecoder.read(bArr, i, i2);
                if (i5 > 0) {
                    this.curPos += (long) i5;
                    i4 += i5;
                    i += i5;
                    i2 -= i5;
                } else if (i5 == -1) {
                    this.blockDecoder = null;
                }
            }
            return i4;
        } catch (IOException e) {
            e = e;
            if (e instanceof EOFException) {
                e = new CorruptedInputException();
            }
            this.exception = e;
            if (i4 != 0) {
                return i4;
            }
            throw e;
        }
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long j) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (j >= 0) {
            this.seekPos = j;
            this.seekNeeded = true;
        } else {
            StringBuffer stringBuffer = new StringBuffer("Negative seek position: ");
            stringBuffer.append(j);
            throw new XZIOException(stringBuffer.toString());
        }
    }
}
