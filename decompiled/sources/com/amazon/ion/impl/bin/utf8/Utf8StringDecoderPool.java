package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Utf8StringDecoderPool extends Pool<Utf8StringDecoder> {
    public static final Utf8StringDecoderPool INSTANCE = new Utf8StringDecoderPool();

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Pool.Allocator<Utf8StringDecoder> {
        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
            return newInstance((Pool<Utf8StringDecoder>) pool);
        }

        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public Utf8StringDecoder newInstance(Pool<Utf8StringDecoder> pool) {
            return new Utf8StringDecoder(pool);
        }
    }

    public Utf8StringDecoderPool() {
        super(new AnonymousClass1());
    }

    public static Utf8StringDecoderPool getInstance() {
        return INSTANCE;
    }
}
