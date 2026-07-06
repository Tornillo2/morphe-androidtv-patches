package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexHash;

/* JADX INFO: loaded from: classes4.dex */
public class SingleXZInputStream extends InputStream {
    public Check check;
    public InputStream in;
    public int memoryLimit;
    public StreamFlags streamHeaderFlags;
    public BlockInputStream blockDecoder = null;
    public final IndexHash indexHash = new IndexHash();
    public boolean endReached = false;
    public IOException exception = null;

    public SingleXZInputStream(InputStream inputStream) throws IOException {
        initialize(inputStream, -1);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        BlockInputStream blockInputStream = this.blockDecoder;
        if (blockInputStream == null) {
            return 0;
        }
        return blockInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public String getCheckName() {
        return this.check.getName();
    }

    public int getCheckType() {
        return this.streamHeaderFlags.checkType;
    }

    public final void initialize(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[12];
        new DataInputStream(inputStream).readFully(bArr);
        initialize(inputStream, i, bArr);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    public final void validateStreamFooter() throws IOException {
        byte[] bArr = new byte[12];
        new DataInputStream(this.in).readFully(bArr);
        StreamFlags streamFlagsDecodeStreamFooter = DecoderUtil.decodeStreamFooter(bArr);
        if (!DecoderUtil.areStreamFlagsEqual(this.streamHeaderFlags, streamFlagsDecodeStreamFooter) || this.indexHash.getIndexSize() != streamFlagsDecodeStreamFooter.backwardSize) {
            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
        }
    }

    public SingleXZInputStream(InputStream inputStream, int i) throws IOException {
        initialize(inputStream, i);
    }

    public final void initialize(InputStream inputStream, int i, byte[] bArr) throws IOException {
        this.in = inputStream;
        this.memoryLimit = i;
        StreamFlags streamFlagsDecodeStreamHeader = DecoderUtil.decodeStreamHeader(bArr);
        this.streamHeaderFlags = streamFlagsDecodeStreamHeader;
        this.check = Check.getInstance(streamFlagsDecodeStreamHeader.checkType);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
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
        if (this.endReached) {
            return -1;
        }
        int i4 = 0;
        while (i2 > 0) {
            try {
                if (this.blockDecoder == null) {
                    try {
                        this.blockDecoder = new BlockInputStream(this.in, this.check, this.memoryLimit, -1L, -1L);
                    } catch (IndexIndicatorException unused) {
                        this.indexHash.validate(this.in);
                        validateStreamFooter();
                        this.endReached = true;
                        if (i4 > 0) {
                            return i4;
                        }
                        return -1;
                    }
                }
                int i5 = this.blockDecoder.read(bArr, i, i2);
                if (i5 > 0) {
                    i4 += i5;
                    i += i5;
                    i2 -= i5;
                } else if (i5 == -1) {
                    this.indexHash.add(this.blockDecoder.getUnpaddedSize(), this.blockDecoder.getUncompressedSize());
                    this.blockDecoder = null;
                }
            } catch (IOException e) {
                this.exception = e;
                if (i4 == 0) {
                    throw e;
                }
            }
        }
        return i4;
    }

    public SingleXZInputStream(InputStream inputStream, int i, byte[] bArr) throws IOException {
        initialize(inputStream, i, bArr);
    }
}
