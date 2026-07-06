package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Utf8StringEncoderPool extends Pool<Utf8StringEncoder> {
    public static final Utf8StringEncoderPool INSTANCE = new Utf8StringEncoderPool();

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.utf8.Utf8StringEncoderPool$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Pool.Allocator<Utf8StringEncoder> {
        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
            return newInstance((Pool<Utf8StringEncoder>) pool);
        }

        @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
        public Utf8StringEncoder newInstance(Pool<Utf8StringEncoder> pool) {
            return new Utf8StringEncoder(pool);
        }
    }

    public Utf8StringEncoderPool() {
        super(new AnonymousClass1());
    }

    public static Utf8StringEncoderPool getInstance() {
        return INSTANCE;
    }
}
