package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import j$.util.Objects;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class CharSequenceReader extends Reader {
    public int mark;
    public int pos;
    public CharSequence seq;

    public CharSequenceReader(CharSequence seq) {
        seq.getClass();
        this.seq = seq;
    }

    public final void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.seq = null;
    }

    public final boolean hasRemaining() {
        return remaining() > 0;
    }

    @Override // java.io.Reader
    public synchronized void mark(int readAheadLimit) throws IOException {
        Preconditions.checkArgument(readAheadLimit >= 0, "readAheadLimit (%s) may not be negative", readAheadLimit);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public synchronized int read() throws IOException {
        int iCharAt;
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i = this.pos;
            this.pos = i + 1;
            iCharAt = charSequence.charAt(i);
        } else {
            iCharAt = -1;
        }
        return iCharAt;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() throws IOException {
        checkOpen();
        return true;
    }

    public final int remaining() {
        Objects.requireNonNull(this.seq);
        return this.seq.length() - this.pos;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Reader
    public synchronized long skip(long n) throws IOException {
        int iMin;
        Preconditions.checkArgument(n >= 0, "n (%s) may not be negative", n);
        checkOpen();
        iMin = (int) Math.min(remaining(), n);
        this.pos += iMin;
        return iMin;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cbuf, int off, int len) throws IOException {
        Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (!hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(len, remaining());
        for (int i = 0; i < iMin; i++) {
            CharSequence charSequence = this.seq;
            int i2 = this.pos;
            this.pos = i2 + 1;
            cbuf[off + i] = charSequence.charAt(i2);
        }
        return iMin;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer target) throws IOException {
        target.getClass();
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (!hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(target.remaining(), remaining());
        for (int i = 0; i < iMin; i++) {
            CharSequence charSequence = this.seq;
            int i2 = this.pos;
            this.pos = i2 + 1;
            target.put(charSequence.charAt(i2));
        }
        return iMin;
    }
}
