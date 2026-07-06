package androidx.media3.exoplayer.upstream;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DefaultAllocator implements Allocator {
    public static final int AVAILABLE_EXTRA_CAPACITY = 100;
    public int allocatedCount;
    public Allocation[] availableAllocations;
    public int availableCount;
    public final int individualAllocationSize;

    @Nullable
    public final byte[] initialAllocationBlock;
    public int targetBufferSize;
    public final boolean trimOnReset;

    public DefaultAllocator(boolean z, int i) {
        this(z, i, 0);
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized Allocation allocate() {
        Allocation allocation;
        try {
            int i = this.allocatedCount + 1;
            this.allocatedCount = i;
            int i2 = this.availableCount;
            if (i2 > 0) {
                Allocation[] allocationArr = this.availableAllocations;
                int i3 = i2 - 1;
                this.availableCount = i3;
                allocation = allocationArr[i3];
                allocation.getClass();
                this.availableAllocations[this.availableCount] = null;
            } else {
                Allocation allocation2 = new Allocation(new byte[this.individualAllocationSize], 0);
                Allocation[] allocationArr2 = this.availableAllocations;
                if (i > allocationArr2.length) {
                    this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, allocationArr2.length * 2);
                }
                allocation = allocation2;
            }
        } catch (Throwable th) {
            throw th;
        }
        return allocation;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void release(Allocation allocation) {
        Allocation[] allocationArr = this.availableAllocations;
        int i = this.availableCount;
        this.availableCount = i + 1;
        allocationArr[i] = allocation;
        this.allocatedCount--;
        notifyAll();
    }

    public synchronized void reset() {
        if (this.trimOnReset) {
            setTargetBufferSize(0);
        }
    }

    public synchronized void setTargetBufferSize(int i) {
        boolean z = i < this.targetBufferSize;
        this.targetBufferSize = i;
        if (z) {
            trim();
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void trim() {
        try {
            int i = 0;
            int iMax = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
            int i2 = this.availableCount;
            if (iMax >= i2) {
                return;
            }
            if (this.initialAllocationBlock != null) {
                int i3 = i2 - 1;
                while (i <= i3) {
                    Allocation allocation = this.availableAllocations[i];
                    allocation.getClass();
                    if (allocation.data == this.initialAllocationBlock) {
                        i++;
                    } else {
                        Allocation allocation2 = this.availableAllocations[i3];
                        allocation2.getClass();
                        if (allocation2.data != this.initialAllocationBlock) {
                            i3--;
                        } else {
                            Allocation[] allocationArr = this.availableAllocations;
                            allocationArr[i] = allocation2;
                            allocationArr[i3] = allocation;
                            i3--;
                            i++;
                        }
                    }
                }
                iMax = Math.max(iMax, i);
                if (iMax >= this.availableCount) {
                    return;
                }
            }
            Arrays.fill(this.availableAllocations, iMax, this.availableCount, (Object) null);
            this.availableCount = iMax;
        } catch (Throwable th) {
            throw th;
        }
    }

    public DefaultAllocator(boolean z, int i, int i2) {
        Assertions.checkArgument(i > 0);
        Assertions.checkArgument(i2 >= 0);
        this.trimOnReset = z;
        this.individualAllocationSize = i;
        this.availableCount = i2;
        this.availableAllocations = new Allocation[i2 + 100];
        if (i2 <= 0) {
            this.initialAllocationBlock = null;
            return;
        }
        this.initialAllocationBlock = new byte[i2 * i];
        for (int i3 = 0; i3 < i2; i3++) {
            this.availableAllocations[i3] = new Allocation(this.initialAllocationBlock, i3 * i);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void release(@Nullable Allocator.AllocationNode allocationNode) {
        while (allocationNode != null) {
            try {
                Allocation[] allocationArr = this.availableAllocations;
                int i = this.availableCount;
                this.availableCount = i + 1;
                allocationArr[i] = allocationNode.getAllocation();
                this.allocatedCount--;
                allocationNode = allocationNode.next();
            } catch (Throwable th) {
                throw th;
            }
        }
        notifyAll();
    }
}
