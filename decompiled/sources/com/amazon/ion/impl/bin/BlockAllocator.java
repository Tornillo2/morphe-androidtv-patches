package com.amazon.ion.impl.bin;

import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BlockAllocator implements Closeable {
    public abstract Block allocateBlock();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract int getBlockSize();
}
