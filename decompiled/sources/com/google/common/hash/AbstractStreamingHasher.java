package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractStreamingHasher extends AbstractHasher {
    public final ByteBuffer buffer;
    public final int bufferSize;
    public final int chunkSize;

    public AbstractStreamingHasher(int chunkSize, int bufferSize) {
        Preconditions.checkArgument(bufferSize % chunkSize == 0);
        this.buffer = ByteBuffer.allocate(bufferSize + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = bufferSize;
        this.chunkSize = chunkSize;
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        munch();
        this.buffer.flip();
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.limit());
        }
        return makeHash();
    }

    public abstract HashCode makeHash();

    public final void munch() {
        this.buffer.flip();
        while (this.buffer.remaining() >= this.chunkSize) {
            process(this.buffer);
        }
        this.buffer.compact();
    }

    public final void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    public abstract void process(ByteBuffer bb);

    public void processRemaining(ByteBuffer bb) {
        bb.position(bb.limit());
        bb.limit(this.chunkSize + 7);
        while (true) {
            int iPosition = bb.position();
            int i = this.chunkSize;
            if (iPosition >= i) {
                bb.limit(i);
                bb.flip();
                process(bb);
                return;
            }
            bb.putLong(0L);
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putByte(byte b) {
        putByte(b);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putBytes(ByteBuffer readBuffer) {
        putBytes(readBuffer);
        return this;
    }

    @CanIgnoreReturnValue
    public final Hasher putBytesInternal(ByteBuffer readBuffer) {
        if (readBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(readBuffer);
            munchIfFull();
            return this;
        }
        int iPosition = this.bufferSize - this.buffer.position();
        for (int i = 0; i < iPosition; i++) {
            this.buffer.put(readBuffer.get());
        }
        munch();
        while (readBuffer.remaining() >= this.chunkSize) {
            process(readBuffer);
        }
        this.buffer.put(readBuffer);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putChar(char c) {
        putChar(c);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putInt(int i) {
        putInt(i);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putLong(long l) {
        putLong(l);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putShort(short s) {
        putShort(s);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putByte(byte b) {
        this.buffer.put(b);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ PrimitiveSink putBytes(byte[] bytes, int off, int len) {
        putBytes(bytes, off, len);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putChar(char c) {
        this.buffer.putChar(c);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putInt(int i) {
        this.buffer.putInt(i);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putLong(long l) {
        this.buffer.putLong(l);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putShort(short s) {
        this.buffer.putShort(s);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putBytes(byte[] bytes, int off, int len) {
        putBytesInternal(ByteBuffer.wrap(bytes, off, len).order(ByteOrder.LITTLE_ENDIAN));
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    public final Hasher putBytes(ByteBuffer readBuffer) {
        ByteOrder byteOrderOrder = readBuffer.order();
        try {
            readBuffer.order(ByteOrder.LITTLE_ENDIAN);
            putBytesInternal(readBuffer);
            return this;
        } finally {
            readBuffer.order(byteOrderOrder);
        }
    }

    public AbstractStreamingHasher(int chunkSize) {
        this(chunkSize, chunkSize);
    }
}
