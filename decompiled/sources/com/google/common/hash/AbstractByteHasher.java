package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractByteHasher extends AbstractHasher {
    public final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public abstract void update(byte b);

    public void update(byte[] b) {
        update(b, 0, b.length);
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putByte(byte b) {
        update(b);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putChar(char c) {
        this.scratch.putChar(c);
        update(2);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putInt(int i) {
        this.scratch.putInt(i);
        update(4);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putLong(long l) {
        this.scratch.putLong(l);
        update(8);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putShort(short s) {
        this.scratch.putShort(s);
        update(2);
        return this;
    }

    public void update(byte[] b, int off, int len) {
        for (int i = off; i < off + len; i++) {
            update(b[i]);
        }
    }

    public void update(ByteBuffer b) {
        if (b.hasArray()) {
            update(b.array(), b.position() + b.arrayOffset(), b.remaining());
            b.position(b.limit());
            return;
        }
        for (int iRemaining = b.remaining(); iRemaining > 0; iRemaining--) {
            update(b.get());
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bytes, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        update(bytes, off, len);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putBytes(ByteBuffer bytes) {
        update(bytes);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public Hasher putBytes(byte[] bytes) {
        bytes.getClass();
        update(bytes);
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher update(int bytes) {
        try {
            update(this.scratch.array(), 0, bytes);
            return this;
        } finally {
            this.scratch.clear();
        }
    }
}
