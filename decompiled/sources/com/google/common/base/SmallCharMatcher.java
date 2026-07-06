package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {
    public static final int C1 = -862048943;
    public static final int C2 = 461845907;
    public static final double DESIRED_LOAD_FACTOR = 0.5d;
    public static final int MAX_SIZE = 1023;
    public final boolean containsZero;
    public final long filter;
    public final char[] table;

    public SmallCharMatcher(char[] table, long filter, boolean containsZero, String description) {
        super(description);
        this.table = table;
        this.filter = filter;
        this.containsZero = containsZero;
    }

    @VisibleForTesting
    public static int chooseTableSize(int setSize) {
        if (setSize == 1) {
            return 2;
        }
        int iHighestOneBit = Integer.highestOneBit(setSize - 1) << 1;
        while (((double) iHighestOneBit) * 0.5d < setSize) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static CharMatcher from(BitSet chars, String description) {
        int i;
        int iCardinality = chars.cardinality();
        boolean z = chars.get(0);
        int iChooseTableSize = chooseTableSize(iCardinality);
        char[] cArr = new char[iChooseTableSize];
        int i2 = iChooseTableSize - 1;
        int iNextSetBit = chars.nextSetBit(0);
        long j = 0;
        while (iNextSetBit != -1) {
            long j2 = (1 << iNextSetBit) | j;
            int iSmear = smear(iNextSetBit);
            while (true) {
                i = iSmear & i2;
                if (cArr[i] == 0) {
                    break;
                }
                iSmear = i + 1;
            }
            cArr[i] = (char) iNextSetBit;
            iNextSetBit = chars.nextSetBit(iNextSetBit + 1);
            j = j2;
        }
        return new SmallCharMatcher(cArr, j, z, description);
    }

    public static int smear(int hashCode) {
        return Integer.rotateLeft(hashCode * (-862048943), 15) * 461845907;
    }

    public final boolean checkFilter(int c) {
        return ((this.filter >> c) & 1) == 1;
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c) {
        if (c == 0) {
            return this.containsZero;
        }
        if (!checkFilter(c)) {
            return false;
        }
        int length = this.table.length - 1;
        int iSmear = smear(c) & length;
        int i = iSmear;
        do {
            char c2 = this.table[i];
            if (c2 == 0) {
                return false;
            }
            if (c2 == c) {
                return true;
            }
            i = (i + 1) & length;
        } while (i != iSmear);
        return false;
    }

    @Override // com.google.common.base.CharMatcher
    public void setBits(BitSet table) {
        if (this.containsZero) {
            table.set(0);
        }
        for (char c : this.table) {
            if (c != 0) {
                table.set(c);
            }
        }
    }
}
