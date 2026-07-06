package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import java.util.Arrays;
import kotlin.UShort;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class CompactHashing {
    public static final int BYTE_MASK = 255;
    public static final int BYTE_MAX_SIZE = 256;
    public static final int DEFAULT_SIZE = 3;
    public static final int HASH_TABLE_BITS_MASK = 31;
    public static final int HASH_TABLE_BITS_MAX_BITS = 5;
    public static final int MAX_SIZE = 1073741823;
    public static final int MIN_HASH_TABLE_SIZE = 4;
    public static final int MODIFICATION_COUNT_INCREMENT = 32;
    public static final int SHORT_MASK = 65535;
    public static final int SHORT_MAX_SIZE = 65536;
    public static final byte UNSET = 0;

    public static Object createTable(int buckets) {
        if (buckets < 2 || buckets > 1073741824 || Integer.highestOneBit(buckets) != buckets) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("must be power of 2 between 2^1 and 2^30: ", buckets));
        }
        return buckets <= 256 ? new byte[buckets] : buckets <= 65536 ? new short[buckets] : new int[buckets];
    }

    public static int getHashPrefix(int value, int mask) {
        return value & (~mask);
    }

    public static int getNext(int entry, int mask) {
        return entry & mask;
    }

    public static int maskCombine(int prefix, int suffix, int mask) {
        return (prefix & (~mask)) | (suffix & mask);
    }

    public static int newCapacity(int mask) {
        return (mask + 1) * (mask < 32 ? 4 : 2);
    }

    public static int remove(Object key, Object value, int mask, Object table, int[] entries, Object[] keys, Object[] values) {
        int i;
        int i2;
        int iSmearedHash = Hashing.smearedHash(key);
        int i3 = iSmearedHash & mask;
        int iTableGet = tableGet(table, i3);
        if (iTableGet == 0) {
            return -1;
        }
        int i4 = ~mask;
        int i5 = iSmearedHash & i4;
        int i6 = -1;
        while (true) {
            i = iTableGet - 1;
            i2 = entries[i];
            if ((i2 & i4) == i5 && Objects.equal(key, keys[i]) && (values == null || Objects.equal(value, values[i]))) {
                break;
            }
            int i7 = i2 & mask;
            if (i7 == 0) {
                return -1;
            }
            i6 = i;
            iTableGet = i7;
        }
        int i8 = i2 & mask;
        if (i6 == -1) {
            tableSet(table, i3, i8);
            return i;
        }
        entries[i6] = maskCombine(entries[i6], i8, mask);
        return i;
    }

    public static void tableClear(Object table) {
        if (table instanceof byte[]) {
            Arrays.fill((byte[]) table, (byte) 0);
        } else if (table instanceof short[]) {
            Arrays.fill((short[]) table, (short) 0);
        } else {
            Arrays.fill((int[]) table, 0);
        }
    }

    public static int tableGet(Object table, int index) {
        return table instanceof byte[] ? ((byte[]) table)[index] & 255 : table instanceof short[] ? ((short[]) table)[index] & UShort.MAX_VALUE : ((int[]) table)[index];
    }

    public static void tableSet(Object table, int index, int entry) {
        if (table instanceof byte[]) {
            ((byte[]) table)[index] = (byte) entry;
        } else if (table instanceof short[]) {
            ((short[]) table)[index] = (short) entry;
        } else {
            ((int[]) table)[index] = entry;
        }
    }

    public static int tableSize(int expectedSize) {
        return Math.max(4, Hashing.closedTableSize(expectedSize + 1, 1.0d));
    }
}
