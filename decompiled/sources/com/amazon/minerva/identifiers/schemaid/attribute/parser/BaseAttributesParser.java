package com.amazon.minerva.identifiers.schemaid.attribute.parser;

import j$.util.Objects;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseAttributesParser {
    public static final int NUMBER_OF_BITS = 32;
    public static final Pattern PATTERN_ATTRIBUTE = Pattern.compile("[0-9a-f]{8}");

    public static BitSet createBitSet(String str) {
        Objects.requireNonNull(str, "hexAttributeStr can not be null.");
        if (!PATTERN_ATTRIBUTE.matcher(str).matches()) {
            throw new IllegalArgumentException("hexAttributeStr should be a 8-digits hex-encoded String.");
        }
        byte[] bArr = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bArr[3 - i] = Long.valueOf(str.substring(i * 2, (i + 1) * 2), 16).byteValue();
        }
        return BitSet.valueOf(bArr);
    }

    public static BitSet createEmptyBitSet() {
        return BitSet.valueOf(new long[]{Long.valueOf(TarConstants.VERSION_POSIX, 16).longValue()});
    }

    public static String getAttributesHexString(BitSet bitSet) {
        byte[] bArrCopyOf = Arrays.copyOf(bitSet.toByteArray(), 4);
        StringBuilder sb = new StringBuilder();
        for (int length = bArrCopyOf.length - 1; length >= 0; length--) {
            sb.append(String.format(Locale.US, "%02x", Byte.valueOf(bArrCopyOf[length])));
        }
        return sb.toString();
    }

    public static boolean getBoolean(BitSet bitSet, int i) {
        return getInteger(bitSet, i, i) == 1;
    }

    public static int getInteger(BitSet bitSet, int i, int i2) {
        if (isIndexOutOfBounds(i, i2)) {
            throw new IndexOutOfBoundsException("Index should be in the range of [0, 32)");
        }
        int iPow = 0;
        for (int i3 = 0; i3 <= i2 - i; i3++) {
            if (bitSet.get(i + i3)) {
                iPow += (int) Math.pow(2.0d, i3);
            }
        }
        return iPow;
    }

    public static boolean isIndexOutOfBounds(int i, int i2) {
        return i < 0 || i >= 32 || i2 < 0 || i2 >= 32 || i > i2;
    }

    public static void setBitPositionsForValue(BitSet bitSet, int i, int i2, int i3) {
        if (isIndexOutOfBounds(i2, i3)) {
            throw new IndexOutOfBoundsException("Index should be in the range of [0, 32)");
        }
        BitSet bitSetValueOf = BitSet.valueOf(new byte[]{(byte) i});
        for (int i4 = i2; i4 <= i3; i4++) {
            bitSet.set(i4, bitSetValueOf.get(i4 - i2));
        }
    }
}
