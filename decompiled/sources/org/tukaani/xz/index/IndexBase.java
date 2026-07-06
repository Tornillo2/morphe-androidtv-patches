package org.tukaani.xz.index;

import org.tukaani.xz.XZIOException;
import org.tukaani.xz.common.Util;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IndexBase {
    public final XZIOException invalidIndexException;
    public long blocksSum = 0;
    public long uncompressedSum = 0;
    public long indexListSize = 0;
    public long recordCount = 0;

    public IndexBase(XZIOException xZIOException) {
        this.invalidIndexException = xZIOException;
    }

    public void add(long j, long j2) throws XZIOException {
        this.blocksSum += (3 + j) & (-4);
        this.uncompressedSum += j2;
        this.indexListSize += (long) (Util.getVLISize(j2) + Util.getVLISize(j));
        this.recordCount++;
        if (this.blocksSum < 0 || this.uncompressedSum < 0 || getIndexSize() > 17179869184L || getStreamSize() < 0) {
            throw this.invalidIndexException;
        }
    }

    public int getIndexPaddingSize() {
        return (int) ((4 - getUnpaddedIndexSize()) & 3);
    }

    public long getIndexSize() {
        return (getUnpaddedIndexSize() + 3) & (-4);
    }

    public long getStreamSize() {
        return getIndexSize() + this.blocksSum + 12 + 12;
    }

    public final long getUnpaddedIndexSize() {
        return ((long) (Util.getVLISize(this.recordCount) + 1)) + this.indexListSize + 4;
    }
}
