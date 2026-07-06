package com.amazon.ion.impl.bin;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PooledBlockAllocatorProvider extends BlockAllocatorProvider {
    public static final PooledBlockAllocatorProvider INSTANCE = new PooledBlockAllocatorProvider();
    public final ConcurrentMap<Integer, BlockAllocator> allocators = new ConcurrentHashMap();

    public static PooledBlockAllocatorProvider getInstance() {
        return INSTANCE;
    }

    @Override // com.amazon.ion.impl.bin.BlockAllocatorProvider
    public BlockAllocator vendAllocator(int i) {
        BlockAllocator blockAllocatorPutIfAbsent;
        if (i <= 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid block size: ", i));
        }
        BlockAllocator pooledBlockAllocator = this.allocators.get(Integer.valueOf(i));
        return (pooledBlockAllocator != null || (blockAllocatorPutIfAbsent = this.allocators.putIfAbsent(Integer.valueOf(i), (pooledBlockAllocator = new PooledBlockAllocator(i)))) == null) ? pooledBlockAllocator : blockAllocatorPutIfAbsent;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PooledBlockAllocator extends BlockAllocator {
        public static final int FREE_CAPACITY = 67108864;
        public final int blockLimit;
        public final int blockSize;
        public final AtomicInteger size = new AtomicInteger(0);
        public final ConcurrentLinkedQueue<Block> freeBlocks = new ConcurrentLinkedQueue<>();

        public PooledBlockAllocator(int i) {
            this.blockSize = i;
            this.blockLimit = 67108864 / i;
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocator
        public Block allocateBlock() {
            Block blockPoll = this.freeBlocks.poll();
            if (blockPoll == null) {
                return new Block(new byte[this.blockSize]) { // from class: com.amazon.ion.impl.bin.PooledBlockAllocatorProvider.PooledBlockAllocator.1
                    @Override // com.amazon.ion.impl.bin.Block, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        int andIncrement = PooledBlockAllocator.this.size.getAndIncrement();
                        PooledBlockAllocator pooledBlockAllocator = PooledBlockAllocator.this;
                        if (andIncrement >= pooledBlockAllocator.blockLimit) {
                            pooledBlockAllocator.size.decrementAndGet();
                        } else {
                            this.limit = 0;
                            pooledBlockAllocator.freeBlocks.add(this);
                        }
                    }
                };
            }
            this.size.decrementAndGet();
            return blockPoll;
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocator
        public int getBlockSize() {
            return this.blockSize;
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocator, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }
}
