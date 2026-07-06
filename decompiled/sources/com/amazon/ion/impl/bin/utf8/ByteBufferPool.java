package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ByteBufferPool extends Pool<PoolableByteBuffer> {
    public static final ByteBufferPool INSTANCE = new ByteBufferPool();

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.utf8.ByteBufferPool$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Pool.Allocator<PoolableByteBuffer> {
        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
            return newInstance((Pool<PoolableByteBuffer>) pool);
        }

        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public PoolableByteBuffer newInstance(Pool<PoolableByteBuffer> pool) {
            return new PoolableByteBuffer(pool);
        }
    }

    public ByteBufferPool() {
        super(new AnonymousClass1());
    }

    public static ByteBufferPool getInstance() {
        return INSTANCE;
    }
}
