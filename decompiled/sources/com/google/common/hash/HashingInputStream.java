package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class HashingInputStream extends FilterInputStream {
    public final Hasher hasher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingInputStream(HashFunction hashFunction, InputStream in) {
        super(in);
        in.getClass();
        Hasher hasherNewHasher = hashFunction.newHasher();
        hasherNewHasher.getClass();
        this.hasher = hasherNewHasher;
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    @CanIgnoreReturnValue
    public int read() throws IOException {
        int i = ((FilterInputStream) this).in.read();
        if (i != -1) {
            this.hasher.putByte((byte) i);
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new IOException("reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    @CanIgnoreReturnValue
    public int read(byte[] bytes, int off, int len) throws IOException {
        int i = ((FilterInputStream) this).in.read(bytes, off, len);
        if (i != -1) {
            this.hasher.putBytes(bytes, off, i);
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int readlimit) {
    }
}
