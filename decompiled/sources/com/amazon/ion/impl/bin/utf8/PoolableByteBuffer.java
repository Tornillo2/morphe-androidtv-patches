package com.amazon.ion.impl.bin.utf8;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PoolableByteBuffer extends Poolable<PoolableByteBuffer> {
    public static final int BUFFER_SIZE_IN_BYTES = 4096;
    public final ByteBuffer buffer;

    public PoolableByteBuffer(Pool<PoolableByteBuffer> pool) {
        super(pool);
        this.buffer = ByteBuffer.allocate(4096);
    }

    @Override // com.amazon.ion.impl.bin.utf8.Poolable, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }
}
