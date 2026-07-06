package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Hashing {
    public static final long C1 = -862048943;
    public static final long C2 = 461845907;
    public static final int MAX_TABLE_SIZE = 1073741824;

    public static int closedTableSize(int expectedEntries, double loadFactor) {
        int iMax = Math.max(expectedEntries, 2);
        int iHighestOneBit = Integer.highestOneBit(iMax);
        if (iMax <= ((int) (loadFactor * ((double) iHighestOneBit)))) {
            return iHighestOneBit;
        }
        int i = iHighestOneBit << 1;
        if (i > 0) {
            return i;
        }
        return 1073741824;
    }

    public static boolean needsResizing(int size, int tableSize, double loadFactor) {
        return ((double) size) > loadFactor * ((double) tableSize) && tableSize < 1073741824;
    }

    public static int smear(int hashCode) {
        return (int) (((long) Integer.rotateLeft((int) (((long) hashCode) * C1), 15)) * C2);
    }

    public static int smearedHash(Object o) {
        return smear(o == null ? 0 : o.hashCode());
    }
}
