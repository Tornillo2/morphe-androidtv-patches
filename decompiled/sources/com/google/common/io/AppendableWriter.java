package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class AppendableWriter extends Writer {
    public boolean closed;
    public final Appendable target;

    public AppendableWriter(Appendable target) {
        target.getClass();
        this.target = target;
    }

    public final void checkNotClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write to a closed writer.");
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        Appendable appendable = this.target;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        checkNotClosed();
        Appendable appendable = this.target;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        checkNotClosed();
        this.target.append(new String(cbuf, off, len));
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        checkNotClosed();
        this.target.append((char) c);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        checkNotClosed();
        this.target.append(c);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        str.getClass();
        checkNotClosed();
        this.target.append(str);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSeq) throws IOException {
        checkNotClosed();
        this.target.append(charSeq);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSeq, int start, int end) throws IOException {
        checkNotClosed();
        this.target.append(charSeq, start, end);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) throws IOException {
        str.getClass();
        checkNotClosed();
        this.target.append(str, off, len + off);
    }
}
