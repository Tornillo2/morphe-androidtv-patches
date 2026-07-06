package org.apache.commons.compress.archivers.dump;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TapeInputStream extends FilterInputStream {
    public static final int recordSize = 1024;
    public byte[] blockBuffer;
    public int blockSize;
    public long bytesRead;
    public int currBlkIdx;
    public boolean isCompressed;
    public int readOffset;

    /* JADX INFO: renamed from: org.apache.commons.compress.archivers.dump.TapeInputStream$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE;

        static {
            int[] iArr = new int[DumpArchiveConstants.COMPRESSION_TYPE.values().length];
            $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE = iArr;
            try {
                iArr[DumpArchiveConstants.COMPRESSION_TYPE.ZLIB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.BZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.LZO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public TapeInputStream(InputStream inputStream) {
        super(inputStream);
        this.blockBuffer = new byte[1024];
        this.currBlkIdx = -1;
        this.blockSize = 1024;
        this.readOffset = 1024;
        this.isCompressed = false;
        this.bytesRead = 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i = this.readOffset;
        int i2 = this.blockSize;
        return i < i2 ? i2 - i : ((FilterInputStream) this).in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (((FilterInputStream) this).in == null || ((FilterInputStream) this).in == System.in) {
            return;
        }
        ((FilterInputStream) this).in.close();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public byte[] peek() throws IOException {
        if (this.readOffset == this.blockSize) {
            readBlock(true);
        }
        byte[] bArr = new byte[1024];
        System.arraycopy(this.blockBuffer, this.readOffset, bArr, 0, 1024);
        return bArr;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
    }

    public final boolean readBlock(boolean z) throws IOException {
        if (((FilterInputStream) this).in == null) {
            throw new IOException("input buffer is closed");
        }
        if (!this.isCompressed || this.currBlkIdx == -1) {
            readFully(this.blockBuffer, 0, this.blockSize);
            this.bytesRead += (long) this.blockSize;
        } else {
            readFully(this.blockBuffer, 0, 4);
            this.bytesRead += 4;
            int iConvert32 = DumpArchiveUtil.convert32(this.blockBuffer, 0);
            if ((iConvert32 & 1) == 1) {
                int i = iConvert32 >> 1;
                int i2 = (iConvert32 >> 4) & 268435455;
                byte[] bArr = new byte[i2];
                readFully(bArr, 0, i2);
                this.bytesRead += (long) i2;
                if (z) {
                    int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.find(i & 3).ordinal()];
                    if (i3 != 1) {
                        if (i3 == 2) {
                            throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                        }
                        if (i3 != 3) {
                            throw new UnsupportedCompressionAlgorithmException();
                        }
                        throw new UnsupportedCompressionAlgorithmException("LZO");
                    }
                    try {
                        Inflater inflater = new Inflater();
                        inflater.setInput(bArr, 0, i2);
                        if (inflater.inflate(this.blockBuffer) != this.blockSize) {
                            throw new ShortFileException();
                        }
                        inflater.end();
                    } catch (DataFormatException e) {
                        throw new DumpArchiveException("bad data", e);
                    }
                } else {
                    Arrays.fill(this.blockBuffer, (byte) 0);
                }
            } else {
                readFully(this.blockBuffer, 0, this.blockSize);
                this.bytesRead += (long) this.blockSize;
            }
        }
        this.currBlkIdx++;
        this.readOffset = 0;
        return true;
    }

    public final boolean readFully(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = ((FilterInputStream) this).in.read(bArr, i + i3, i2 - i3);
            if (i4 == -1) {
                throw new ShortFileException();
            }
            i3 += i4;
        }
        return true;
    }

    public byte[] readRecord() throws IOException {
        byte[] bArr = new byte[1024];
        if (-1 != read(bArr, 0, 1024)) {
            return bArr;
        }
        throw new ShortFileException();
    }

    public void resetBlockSize(int i, boolean z) throws IOException {
        this.isCompressed = z;
        int i2 = i * 1024;
        this.blockSize = i2;
        byte[] bArr = this.blockBuffer;
        byte[] bArr2 = new byte[i2];
        this.blockBuffer = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 1024);
        readFully(this.blockBuffer, 1024, this.blockSize - 1024);
        this.currBlkIdx = 0;
        this.readOffset = 1024;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j % PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID != 0) {
            throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
        }
        while (j2 < j) {
            int i = this.readOffset;
            int i2 = this.blockSize;
            if (i == i2) {
                readBlock(j - j2 < ((long) i2));
            }
            int i3 = this.readOffset;
            long j3 = j - j2;
            long j4 = ((long) i3) + j3;
            int i4 = this.blockSize;
            if (j4 > i4) {
                j3 = i4 - i3;
            }
            this.readOffset = (int) (((long) i3) + j3);
            j2 += j3;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 % 1024 != 0) {
            throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
        }
        int i3 = 0;
        while (i3 < i2) {
            if (this.readOffset == this.blockSize) {
                readBlock(true);
            }
            int i4 = this.readOffset;
            int i5 = i2 - i3;
            int i6 = i4 + i5;
            int i7 = this.blockSize;
            if (i6 > i7) {
                i5 = i7 - i4;
            }
            System.arraycopy(this.blockBuffer, i4, bArr, i, i5);
            this.readOffset += i5;
            i3 += i5;
            i += i5;
        }
        return i3;
    }
}
