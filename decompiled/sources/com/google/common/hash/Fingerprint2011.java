package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.hash.LittleEndianByteArray;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Fingerprint2011 extends AbstractNonStreamingHashFunction {
    public static final HashFunction FINGERPRINT_2011 = new Fingerprint2011();
    public static final long K0 = -6505348102511208375L;
    public static final long K1 = -8261664234251669945L;
    public static final long K2 = -4288712594273399085L;
    public static final long K3 = -4132994306676758123L;

    @VisibleForTesting
    public static long fingerprint(byte[] bytes, int offset, int length) {
        long jMurmurHash64WithSeed = length <= 32 ? murmurHash64WithSeed(bytes, offset, length, -1397348546323613475L) : length <= 64 ? hashLength33To64(bytes, offset, length) : fullFingerprint(bytes, offset, length);
        long jLoad64 = K0;
        long jLoad642 = length >= 8 ? LittleEndianByteArray.load64(bytes, offset) : -6505348102511208375L;
        if (length >= 9) {
            jLoad64 = LittleEndianByteArray.load64(bytes, (offset + length) - 8);
        }
        long jHash128to64 = hash128to64(jMurmurHash64WithSeed + jLoad64, jLoad642);
        return (jHash128to64 == 0 || jHash128to64 == 1) ? jHash128to64 - 2 : jHash128to64;
    }

    public static long fullFingerprint(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset);
        int i = offset + length;
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        long longLittleEndian = littleEndianBytes.getLongLittleEndian(bArr, i - 16) ^ K1;
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bArr, i - 56) ^ K0;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long j = length;
        weakHashLength32WithSeeds(bArr, i - 64, j, longLittleEndian, jArr);
        weakHashLength32WithSeeds(bArr, i - 32, j * K1, K0, jArr2);
        long[] jArr3 = jArr2;
        long jShiftMix = (shiftMix(jArr[1]) * K1) + longLittleEndian2;
        long jRotateRight = Long.rotateRight(jLoad64 + jShiftMix, 39) * K1;
        int i2 = (length - 1) & (-64);
        long jRotateRight2 = Long.rotateRight(longLittleEndian, 33) * K1;
        long j2 = jRotateRight;
        long j3 = jShiftMix;
        int i3 = offset;
        while (true) {
            long j4 = j2 + jRotateRight2 + jArr[0];
            LittleEndianByteArray.LittleEndianBytes littleEndianBytes2 = LittleEndianByteArray.byteArray;
            long jRotateRight3 = Long.rotateRight(littleEndianBytes2.getLongLittleEndian(bArr, i3 + 16) + j4, 37) * K1;
            long jRotateRight4 = Long.rotateRight(littleEndianBytes2.getLongLittleEndian(bArr, i3 + 48) + jRotateRight2 + jArr[1], 42) * K1;
            long j5 = jArr3[1] ^ jRotateRight3;
            long j6 = jRotateRight4 ^ jArr[0];
            long jRotateRight5 = Long.rotateRight(j3 ^ jArr3[0], 33);
            weakHashLength32WithSeeds(bArr, i3, jArr[1] * K1, jArr3[0] + j5, jArr);
            int i4 = i3;
            long[] jArr4 = jArr3;
            weakHashLength32WithSeeds(bytes, i4 + 32, jRotateRight5 + jArr3[1], j6, jArr4);
            i3 = i4 + 64;
            i2 -= 64;
            if (i2 == 0) {
                return hash128to64((shiftMix(j6) * K1) + hash128to64(jArr[0], jArr4[0]) + j5, hash128to64(jArr[1], jArr4[1]) + jRotateRight5);
            }
            bArr = bytes;
            jArr3 = jArr4;
            j3 = j5;
            jRotateRight2 = j6;
            j2 = jRotateRight5;
        }
    }

    @VisibleForTesting
    public static long hash128to64(long high, long low) {
        long j = (low ^ high) * K3;
        long j2 = (high ^ (j ^ (j >>> 47))) * K3;
        return (j2 ^ (j2 >>> 47)) * K3;
    }

    private static long hashLength33To64(byte[] bytes, int offset, int length) {
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset + 24);
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        int i = length + offset;
        int i2 = i - 16;
        long longLittleEndian = ((littleEndianBytes.getLongLittleEndian(bytes, i2) + ((long) length)) * K0) + littleEndianBytes.getLongLittleEndian(bytes, offset);
        long jRotateRight = Long.rotateRight(longLittleEndian + jLoad64, 52);
        long jRotateRight2 = Long.rotateRight(longLittleEndian, 37);
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bytes, offset + 8) + longLittleEndian;
        long jRotateRight3 = Long.rotateRight(longLittleEndian2, 7) + jRotateRight2;
        int i3 = offset + 16;
        long longLittleEndian3 = longLittleEndian2 + littleEndianBytes.getLongLittleEndian(bytes, i3);
        long j = jLoad64 + longLittleEndian3;
        long jRotateRight4 = Long.rotateRight(longLittleEndian3, 31) + jRotateRight + jRotateRight3;
        long longLittleEndian4 = littleEndianBytes.getLongLittleEndian(bytes, i3) + littleEndianBytes.getLongLittleEndian(bytes, i - 32);
        long longLittleEndian5 = littleEndianBytes.getLongLittleEndian(bytes, i - 8);
        long jRotateRight5 = Long.rotateRight(longLittleEndian4 + longLittleEndian5, 52);
        long jRotateRight6 = Long.rotateRight(longLittleEndian4, 37);
        long longLittleEndian6 = littleEndianBytes.getLongLittleEndian(bytes, i - 24) + longLittleEndian4;
        long jRotateRight7 = Long.rotateRight(longLittleEndian6, 7) + jRotateRight6;
        long longLittleEndian7 = littleEndianBytes.getLongLittleEndian(bytes, i2) + longLittleEndian6;
        return shiftMix((shiftMix(((longLittleEndian5 + longLittleEndian7 + jRotateRight4) * K0) + ((Long.rotateRight(longLittleEndian7, 31) + jRotateRight5 + jRotateRight7 + j) * K2)) * K0) + jRotateRight4) * K2;
    }

    @VisibleForTesting
    public static long murmurHash64WithSeed(byte[] bytes, int offset, int length, long seed) {
        int i = length & (-8);
        int i2 = length & 7;
        long jLoad64Safely = seed ^ (((long) length) * K3);
        for (int i3 = 0; i3 < i; i3 += 8) {
            jLoad64Safely = (jLoad64Safely ^ (shiftMix(LittleEndianByteArray.load64(bytes, offset + i3) * K3) * K3)) * K3;
        }
        if (i2 != 0) {
            jLoad64Safely = (LittleEndianByteArray.load64Safely(bytes, offset + i, i2) ^ jLoad64Safely) * K3;
        }
        return shiftMix(shiftMix(jLoad64Safely) * K3);
    }

    private static long shiftMix(long val) {
        return val ^ (val >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bytes, int offset, long seedA, long seedB, long[] output) {
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset);
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        long longLittleEndian = littleEndianBytes.getLongLittleEndian(bytes, offset + 8);
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bytes, offset + 16);
        long longLittleEndian3 = littleEndianBytes.getLongLittleEndian(bytes, offset + 24);
        long j = seedA + jLoad64;
        long j2 = longLittleEndian + j + longLittleEndian2;
        long jRotateRight = Long.rotateRight(j2, 23) + Long.rotateRight(seedB + j + longLittleEndian3, 51);
        output[0] = j2 + longLittleEndian3;
        output[1] = jRotateRight + j;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        return HashCode.fromLong(fingerprint(input, off, len));
    }

    public String toString() {
        return "Hashing.fingerprint2011()";
    }
}
