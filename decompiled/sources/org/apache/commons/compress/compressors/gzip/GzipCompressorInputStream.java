package org.apache.commons.compress.compressors.gzip;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.compressors.CompressorInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class GzipCompressorInputStream extends CompressorInputStream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int FCOMMENT = 16;
    public static final int FEXTRA = 4;
    public static final int FHCRC = 2;
    public static final int FNAME = 8;
    public static final int FRESERVED = 224;
    public final byte[] buf;
    public int bufUsed;
    public final CRC32 crc;
    public final boolean decompressConcatenated;
    public boolean endReached;
    public final InputStream in;
    public Inflater inf;
    public int memberSize;
    public final byte[] oneByte;

    public GzipCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    private boolean init(boolean z) throws IOException {
        int i = this.in.read();
        int i2 = this.in.read();
        if (i == -1 && !z) {
            return false;
        }
        if (i != 31 || i2 != 139) {
            throw new IOException(z ? "Input is not in the .gz format" : "Garbage after a valid .gz stream");
        }
        DataInputStream dataInputStream = new DataInputStream(this.in);
        int unsignedByte = dataInputStream.readUnsignedByte();
        if (unsignedByte != 8) {
            throw new IOException(ObjectListKt$$ExternalSyntheticOutline1.m("Unsupported compression method ", unsignedByte, " in the .gz header"));
        }
        int unsignedByte2 = dataInputStream.readUnsignedByte();
        if ((unsignedByte2 & 224) != 0) {
            throw new IOException("Reserved flags are set in the .gz header");
        }
        dataInputStream.readInt();
        dataInputStream.readUnsignedByte();
        dataInputStream.readUnsignedByte();
        if ((unsignedByte2 & 4) != 0) {
            int unsignedByte3 = (dataInputStream.readUnsignedByte() << 8) | dataInputStream.readUnsignedByte();
            while (true) {
                int i3 = unsignedByte3 - 1;
                if (unsignedByte3 <= 0) {
                    break;
                }
                dataInputStream.readUnsignedByte();
                unsignedByte3 = i3;
            }
        }
        if ((unsignedByte2 & 8) != 0) {
            while (dataInputStream.readUnsignedByte() != 0) {
            }
        }
        if ((unsignedByte2 & 16) != 0) {
            while (dataInputStream.readUnsignedByte() != 0) {
            }
        }
        if ((unsignedByte2 & 2) != 0) {
            dataInputStream.readShort();
        }
        this.inf.reset();
        this.crc.reset();
        this.memberSize = 0;
        return true;
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 2 && bArr[0] == 31 && bArr[1] == -117;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Inflater inflater = this.inf;
        if (inflater != null) {
            inflater.end();
            this.inf = null;
        }
        InputStream inputStream = this.in;
        if (inputStream != System.in) {
            inputStream.close();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & 255;
    }

    public final void readToNull(DataInputStream dataInputStream) throws IOException {
        while (dataInputStream.readUnsignedByte() != 0) {
        }
    }

    public GzipCompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.buf = new byte[8192];
        this.bufUsed = 0;
        this.inf = new Inflater(true);
        this.crc = new CRC32();
        this.endReached = false;
        this.oneByte = new byte[1];
        if (inputStream.markSupported()) {
            this.in = inputStream;
        } else {
            this.in = new BufferedInputStream(inputStream);
        }
        this.decompressConcatenated = z;
        init(true);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.endReached) {
            int i3 = 0;
            while (i2 > 0) {
                if (this.inf.needsInput()) {
                    this.in.mark(this.buf.length);
                    int i4 = this.in.read(this.buf);
                    this.bufUsed = i4;
                    if (i4 == -1) {
                        throw new EOFException();
                    }
                    this.inf.setInput(this.buf, 0, i4);
                }
                try {
                    int iInflate = this.inf.inflate(bArr, i, i2);
                    this.crc.update(bArr, i, iInflate);
                    this.memberSize += iInflate;
                    i += iInflate;
                    i2 -= iInflate;
                    i3 += iInflate;
                    count(iInflate);
                    if (this.inf.finished()) {
                        this.in.reset();
                        long remaining = this.bufUsed - this.inf.getRemaining();
                        if (this.in.skip(remaining) != remaining) {
                            throw new IOException();
                        }
                        this.bufUsed = 0;
                        DataInputStream dataInputStream = new DataInputStream(this.in);
                        long unsignedByte = 0;
                        for (int i5 = 0; i5 < 4; i5++) {
                            unsignedByte |= ((long) dataInputStream.readUnsignedByte()) << (i5 * 8);
                        }
                        if (unsignedByte != this.crc.getValue()) {
                            throw new IOException("Gzip-compressed data is corrupt (CRC32 error)");
                        }
                        int unsignedByte2 = 0;
                        for (int i6 = 0; i6 < 4; i6++) {
                            unsignedByte2 |= dataInputStream.readUnsignedByte() << (i6 * 8);
                        }
                        if (unsignedByte2 != this.memberSize) {
                            throw new IOException("Gzip-compressed data is corrupt(uncompressed size mismatch)");
                        }
                        if (!this.decompressConcatenated || !init(false)) {
                            this.inf.end();
                            this.inf = null;
                            this.endReached = true;
                            if (i3 != 0) {
                                return i3;
                            }
                        }
                    }
                } catch (DataFormatException unused) {
                    throw new IOException("Gzip-compressed data is corrupt");
                }
            }
            return i3;
        }
        return -1;
    }
}
