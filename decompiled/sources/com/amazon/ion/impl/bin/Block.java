package com.amazon.ion.impl.bin;

import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Block implements Closeable {
    public final byte[] data;
    public int limit = 0;

    public Block(byte[] bArr) {
        this.data = bArr;
    }

    public final int capacity() {
        return this.data.length;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public final int remaining() {
        return this.data.length - this.limit;
    }

    public final void reset() {
        this.limit = 0;
    }
}
