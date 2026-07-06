package com.amazon.ion.impl.bin;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BlockAllocatorProviders {
    public static final BlockAllocatorProvider BASIC_PROVIDER = new BasicBlockAllocatorProvider();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BasicBlockAllocatorProvider extends BlockAllocatorProvider {
        public BasicBlockAllocatorProvider() {
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocatorProvider
        public BlockAllocator vendAllocator(final int i) {
            return new BlockAllocator() { // from class: com.amazon.ion.impl.bin.BlockAllocatorProviders.BasicBlockAllocatorProvider.1
                @Override // com.amazon.ion.impl.bin.BlockAllocator
                public Block allocateBlock() {
                    return new Block(new byte[i]) { // from class: com.amazon.ion.impl.bin.BlockAllocatorProviders.BasicBlockAllocatorProvider.1.1
                        @Override // com.amazon.ion.impl.bin.Block, java.io.Closeable, java.lang.AutoCloseable
                        public void close() {
                        }
                    };
                }

                @Override // com.amazon.ion.impl.bin.BlockAllocator
                public int getBlockSize() {
                    return i;
                }

                @Override // com.amazon.ion.impl.bin.BlockAllocator, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
        }

        public BasicBlockAllocatorProvider(AnonymousClass1 anonymousClass1) {
        }
    }

    public static BlockAllocatorProvider basicProvider() {
        return BASIC_PROVIDER;
    }
}
