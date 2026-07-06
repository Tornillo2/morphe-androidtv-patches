package org.tukaani.xz;

import java.io.IOException;
import org.tukaani.xz.simple.SimpleFilter;

/* JADX INFO: loaded from: classes4.dex */
public class SimpleOutputStream extends FinishableOutputStream {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static final int TMPBUF_SIZE = 4096;
    public static /* synthetic */ Class class$org$tukaani$xz$SimpleOutputStream;
    public FinishableOutputStream out;
    public final SimpleFilter simpleFilter;
    public final byte[] tmpbuf = new byte[4096];
    public int pos = 0;
    public int unfiltered = 0;
    public IOException exception = null;
    public boolean finished = false;

    static {
        if (class$org$tukaani$xz$SimpleOutputStream == null) {
            class$org$tukaani$xz$SimpleOutputStream = class$("org.tukaani.xz.SimpleOutputStream");
        }
        $assertionsDisabled = true;
    }

    public SimpleOutputStream(FinishableOutputStream finishableOutputStream, SimpleFilter simpleFilter) {
        finishableOutputStream.getClass();
        this.out = finishableOutputStream;
        this.simpleFilter = simpleFilter;
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getMemoryUsage() {
        return 5;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            if (!this.finished) {
                try {
                    writePending();
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
        writePending();
        try {
            this.out.finish();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw new UnsupportedOptionsException("Flushing is not supported");
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public final void writePending() throws IOException {
        if (!$assertionsDisabled && this.finished) {
            throw new AssertionError();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        try {
            this.out.write(this.tmpbuf, this.pos, this.unfiltered);
            this.finished = true;
        } catch (IOException e) {
            this.exception = e;
            throw e;
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
            int iMin = Math.min(i2, 4096 - (this.pos + this.unfiltered));
            System.arraycopy(bArr, i, this.tmpbuf, this.pos + this.unfiltered, iMin);
            i += iMin;
            i2 -= iMin;
            int i4 = this.unfiltered + iMin;
            this.unfiltered = i4;
            int iCode = this.simpleFilter.code(this.tmpbuf, this.pos, i4);
            if (!$assertionsDisabled && iCode > this.unfiltered) {
                throw new AssertionError();
            }
            this.unfiltered -= iCode;
            try {
                this.out.write(this.tmpbuf, this.pos, iCode);
                int i5 = this.pos + iCode;
                this.pos = i5;
                int i6 = this.unfiltered;
                if (i5 + i6 == 4096) {
                    byte[] bArr2 = this.tmpbuf;
                    System.arraycopy(bArr2, i5, bArr2, 0, i6);
                    this.pos = 0;
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
