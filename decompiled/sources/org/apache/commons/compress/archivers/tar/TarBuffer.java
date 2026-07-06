package org.apache.commons.compress.archivers.tar;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarBuffer {
    public static final int DEFAULT_BLKSIZE = 10240;
    public static final int DEFAULT_RCDSIZE = 512;
    public final byte[] blockBuffer;
    public final int blockSize;
    public int currBlkIdx;
    public int currRecIdx;
    public InputStream inStream;
    public OutputStream outStream;
    public final int recordSize;
    public final int recsPerBlock;

    public TarBuffer(InputStream inputStream) {
        this(inputStream, 10240);
    }

    public void close() throws IOException {
        if (this.outStream == null) {
            InputStream inputStream = this.inStream;
            if (inputStream != null) {
                if (inputStream != System.in) {
                    inputStream.close();
                }
                this.inStream = null;
                return;
            }
            return;
        }
        flushBlock();
        OutputStream outputStream = this.outStream;
        if (outputStream == System.out || outputStream == System.err) {
            return;
        }
        outputStream.close();
        this.outStream = null;
    }

    public void flushBlock() throws IOException {
        if (this.outStream == null) {
            throw new IOException("writing to an input buffer");
        }
        if (this.currRecIdx > 0) {
            writeBlock();
        }
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getCurrentBlockNum() {
        return this.currBlkIdx;
    }

    public int getCurrentRecordNum() {
        return this.currRecIdx - 1;
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public boolean isEOFRecord(byte[] bArr) {
        int recordSize = getRecordSize();
        for (int i = 0; i < recordSize; i++) {
            if (bArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean readBlock() throws IOException {
        if (this.inStream == null) {
            throw new IOException("reading from an output buffer");
        }
        this.currRecIdx = 0;
        int i = this.blockSize;
        int i2 = 0;
        while (true) {
            if (i <= 0) {
                break;
            }
            long j = this.inStream.read(this.blockBuffer, i2, i);
            if (j != -1) {
                i2 = (int) (((long) i2) + j);
                i = (int) (((long) i) - j);
            } else {
                if (i2 == 0) {
                    return false;
                }
                Arrays.fill(this.blockBuffer, i2, i + i2, (byte) 0);
            }
        }
        this.currBlkIdx++;
        return true;
    }

    public byte[] readRecord() throws IOException {
        if (this.inStream == null) {
            if (this.outStream == null) {
                throw new IOException("input buffer is closed");
            }
            throw new IOException("reading from an output buffer");
        }
        if (this.currRecIdx >= this.recsPerBlock && !readBlock()) {
            return null;
        }
        int i = this.recordSize;
        byte[] bArr = new byte[i];
        System.arraycopy(this.blockBuffer, this.currRecIdx * i, bArr, 0, i);
        this.currRecIdx++;
        return bArr;
    }

    public void skipRecord() throws IOException {
        if (this.inStream == null) {
            throw new IOException("reading (via skip) from an output buffer");
        }
        if (this.currRecIdx < this.recsPerBlock || readBlock()) {
            this.currRecIdx++;
        }
    }

    public void tryToConsumeSecondEOFRecord() throws IOException {
        boolean zMarkSupported = this.inStream.markSupported();
        if (zMarkSupported) {
            this.inStream.mark(this.recordSize);
        }
        try {
            if (isEOFRecord(readRecord()) || !zMarkSupported) {
            }
        } finally {
            if (zMarkSupported) {
                this.inStream.reset();
            }
        }
    }

    public final void writeBlock() throws IOException {
        OutputStream outputStream = this.outStream;
        if (outputStream == null) {
            throw new IOException("writing to an input buffer");
        }
        outputStream.write(this.blockBuffer, 0, this.blockSize);
        this.outStream.flush();
        this.currRecIdx = 0;
        this.currBlkIdx++;
        Arrays.fill(this.blockBuffer, (byte) 0);
    }

    public void writeRecord(byte[] bArr) throws IOException {
        if (this.outStream == null) {
            if (this.inStream != null) {
                throw new IOException("writing to an input buffer");
            }
            throw new IOException("Output buffer is closed");
        }
        if (bArr.length != this.recordSize) {
            StringBuilder sb = new StringBuilder("record to write has length '");
            sb.append(bArr.length);
            sb.append("' which is not the record size of '");
            throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.recordSize, "'"));
        }
        if (this.currRecIdx >= this.recsPerBlock) {
            writeBlock();
        }
        byte[] bArr2 = this.blockBuffer;
        int i = this.currRecIdx;
        int i2 = this.recordSize;
        System.arraycopy(bArr, 0, bArr2, i * i2, i2);
        this.currRecIdx++;
    }

    public TarBuffer(InputStream inputStream, int i) {
        this(inputStream, null, i, 512);
    }

    public TarBuffer(InputStream inputStream, int i, int i2) {
        this(inputStream, null, i, i2);
    }

    public TarBuffer(OutputStream outputStream) {
        this(outputStream, 10240);
    }

    public TarBuffer(OutputStream outputStream, int i) {
        this(null, outputStream, i, 512);
    }

    public TarBuffer(OutputStream outputStream, int i, int i2) {
        this(null, outputStream, i, i2);
    }

    public TarBuffer(InputStream inputStream, OutputStream outputStream, int i, int i2) {
        this.inStream = inputStream;
        this.outStream = outputStream;
        this.blockSize = i;
        this.recordSize = i2;
        int i3 = i / i2;
        this.recsPerBlock = i3;
        this.blockBuffer = new byte[i];
        if (inputStream != null) {
            this.currBlkIdx = -1;
            this.currRecIdx = i3;
        } else {
            this.currBlkIdx = 0;
            this.currRecIdx = 0;
        }
    }

    public void writeRecord(byte[] bArr, int i) throws IOException {
        if (this.outStream == null) {
            if (this.inStream == null) {
                throw new IOException("Output buffer is closed");
            }
            throw new IOException("writing to an input buffer");
        }
        if (this.recordSize + i <= bArr.length) {
            if (this.currRecIdx >= this.recsPerBlock) {
                writeBlock();
            }
            byte[] bArr2 = this.blockBuffer;
            int i2 = this.currRecIdx;
            int i3 = this.recordSize;
            System.arraycopy(bArr, i, bArr2, i2 * i3, i3);
            this.currRecIdx++;
            return;
        }
        StringBuilder sb = new StringBuilder("record has length '");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb, bArr.length, "' with offset '", i, "' which is less than the record size of '");
        throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.recordSize, "'"));
    }
}
