package org.tukaani.xz;

import java.io.DataOutputStream;
import java.io.IOException;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes4.dex */
public class LZMA2OutputStream extends FinishableOutputStream {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static final int COMPRESSED_SIZE_MAX = 65536;
    public static /* synthetic */ Class class$org$tukaani$xz$LZMA2OutputStream;
    public boolean dictResetNeeded;
    public final LZEncoder lz;
    public final LZMAEncoder lzma;
    public FinishableOutputStream out;
    public final DataOutputStream outData;
    public final int props;
    public final RangeEncoder rc;
    public boolean stateResetNeeded = true;
    public boolean propsNeeded = true;
    public int pendingSize = 0;
    public boolean finished = false;
    public IOException exception = null;

    static {
        if (class$org$tukaani$xz$LZMA2OutputStream == null) {
            class$org$tukaani$xz$LZMA2OutputStream = class$("org.tukaani.xz.LZMA2OutputStream");
        }
        $assertionsDisabled = true;
    }

    public LZMA2OutputStream(FinishableOutputStream finishableOutputStream, LZMA2Options lZMA2Options) {
        this.dictResetNeeded = true;
        finishableOutputStream.getClass();
        this.out = finishableOutputStream;
        this.outData = new DataOutputStream(finishableOutputStream);
        RangeEncoder rangeEncoder = new RangeEncoder(65536);
        this.rc = rangeEncoder;
        int dictSize = lZMA2Options.getDictSize();
        LZMAEncoder lZMAEncoder = LZMAEncoder.getInstance(rangeEncoder, lZMA2Options.getLc(), lZMA2Options.getLp(), lZMA2Options.getPb(), lZMA2Options.getMode(), dictSize, getExtraSizeBefore(dictSize), lZMA2Options.getNiceLen(), lZMA2Options.getMatchFinder(), lZMA2Options.getDepthLimit());
        this.lzma = lZMAEncoder;
        LZEncoder lZEncoder = lZMAEncoder.getLZEncoder();
        this.lz = lZEncoder;
        byte[] presetDict = lZMA2Options.getPresetDict();
        if (presetDict != null && presetDict.length > 0) {
            lZEncoder.setPresetDict(dictSize, presetDict);
            this.dictResetNeeded = false;
        }
        this.props = lZMA2Options.getLc() + ((lZMA2Options.getLp() + (lZMA2Options.getPb() * 5)) * 9);
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getExtraSizeBefore(int i) {
        if (65536 > i) {
            return 65536 - i;
        }
        return 0;
    }

    public static int getMemoryUsage(LZMA2Options lZMA2Options) {
        int dictSize = lZMA2Options.getDictSize();
        return LZMAEncoder.getMemoryUsage(lZMA2Options.getMode(), dictSize, getExtraSizeBefore(dictSize), lZMA2Options.getMatchFinder()) + 70;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            if (!this.finished) {
                try {
                    writeEndMarker();
                } catch (IOException unused) {
                }
            }
            try {
                this.out.close();
            } catch (IOException e) {
                if (this.exception == null) {
                    this.exception = e;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        writeEndMarker();
        try {
            this.out.finish();
            this.finished = true;
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        try {
            this.lz.setFlushing();
            while (this.pendingSize > 0) {
                this.lzma.encodeForLZMA2();
                writeChunk();
            }
            this.out.flush();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public final void writeChunk() throws IOException {
        int iFinish = this.rc.finish();
        int uncompressedSize = this.lzma.getUncompressedSize();
        boolean z = $assertionsDisabled;
        if (!z && iFinish <= 0) {
            throw new AssertionError(iFinish);
        }
        if (!z && uncompressedSize <= 0) {
            throw new AssertionError(uncompressedSize);
        }
        if (iFinish + 2 < uncompressedSize) {
            writeLZMA(uncompressedSize, iFinish);
        } else {
            this.lzma.reset();
            uncompressedSize = this.lzma.getUncompressedSize();
            if (!z && uncompressedSize <= 0) {
                throw new AssertionError(uncompressedSize);
            }
            writeUncompressed(uncompressedSize);
        }
        this.pendingSize -= uncompressedSize;
        this.lzma.resetUncompressedSize();
        this.rc.reset();
    }

    public final void writeEndMarker() throws IOException {
        if (!$assertionsDisabled && this.finished) {
            throw new AssertionError();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        this.lz.setFinishing();
        while (this.pendingSize > 0) {
            try {
                this.lzma.encodeForLZMA2();
                writeChunk();
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
        this.out.write(0);
        this.finished = true;
    }

    public final void writeLZMA(int i, int i2) throws IOException {
        int i3 = i - 1;
        this.outData.writeByte((this.propsNeeded ? this.dictResetNeeded ? 224 : 192 : this.stateResetNeeded ? 160 : 128) | (i3 >>> 16));
        this.outData.writeShort(i3);
        this.outData.writeShort(i2 - 1);
        if (this.propsNeeded) {
            this.outData.writeByte(this.props);
        }
        this.rc.write(this.out);
        this.propsNeeded = false;
        this.stateResetNeeded = false;
        this.dictResetNeeded = false;
    }

    public final void writeUncompressed(int i) throws IOException {
        while (true) {
            int i2 = 1;
            if (i <= 0) {
                this.stateResetNeeded = true;
                return;
            }
            int iMin = Math.min(i, 65536);
            DataOutputStream dataOutputStream = this.outData;
            if (!this.dictResetNeeded) {
                i2 = 2;
            }
            dataOutputStream.writeByte(i2);
            this.outData.writeShort(iMin - 1);
            this.lz.copyUncompressed(this.out, i, iMin);
            i -= iMin;
            this.dictResetNeeded = false;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        while (i2 > 0) {
            try {
                int iFillWindow = this.lz.fillWindow(bArr, i, i2);
                i += iFillWindow;
                i2 -= iFillWindow;
                this.pendingSize += iFillWindow;
                if (this.lzma.encodeForLZMA2()) {
                    writeChunk();
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
