package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class HashingOutputStream extends FilterOutputStream {
    public final Hasher hasher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingOutputStream(HashFunction hashFunction, OutputStream out) {
        super(out);
        out.getClass();
        Hasher hasherNewHasher = hashFunction.newHasher();
        hasherNewHasher.getClass();
        this.hasher = hasherNewHasher;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.hasher.putByte((byte) b);
        ((FilterOutputStream) this).out.write(b);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bytes, int off, int len) throws IOException {
        this.hasher.putBytes(bytes, off, len);
        ((FilterOutputStream) this).out.write(bytes, off, len);
    }
}
