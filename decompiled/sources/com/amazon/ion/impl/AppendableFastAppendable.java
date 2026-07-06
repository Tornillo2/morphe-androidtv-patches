package com.amazon.ion.impl;

import com.amazon.ion.util._Private_FastAppendable;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AppendableFastAppendable implements _Private_FastAppendable, Closeable, Flushable {
    public final Appendable _out;

    public AppendableFastAppendable(Appendable appendable) {
        appendable.getClass();
        this._out = appendable;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence) throws IOException {
        this._out.append(charSequence);
        return this;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(char c) throws IOException {
        this._out.append(c);
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendUtf16(char c) throws IOException {
        this._out.append(c);
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendUtf16Surrogate(char c, char c2) throws IOException {
        this._out.append(c);
        this._out.append(c2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Appendable appendable = this._out;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        Appendable appendable = this._out;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    @Override // java.lang.Appendable
    public Appendable append(char c) throws IOException {
        this._out.append(c);
        return this;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(CharSequence charSequence) throws IOException {
        this._out.append(charSequence);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
        this._out.append(charSequence, i, i2);
        return this;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(CharSequence charSequence, int i, int i2) throws IOException {
        this._out.append(charSequence, i, i2);
    }
}
