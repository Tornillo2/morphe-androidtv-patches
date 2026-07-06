package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.hash.LittleEndianByteArray;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    public static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();
    public static final long K0 = -4348849565147123417L;
    public static final long K1 = -5435081209227447693L;
    public static final long K2 = -7286425919675154353L;

    @VisibleForTesting
    public static long fingerprint(byte[] bytes, int offset, int length) {
        return length <= 32 ? length <= 16 ? hashLength0to16(bytes, offset, length) : hashLength17to32(bytes, offset, length) : length <= 64 ? hashLength33To64(bytes, offset, length) : hashLength65Plus(bytes, offset, length);
    }

    public static long hashLength0to16(byte[] bytes, int offset, int length) {
        if (length >= 8) {
            long j = (((long) length) * 2) + K2;
            long jLoad64 = LittleEndianByteArray.load64(bytes, offset) + K2;
            long longLittleEndian = LittleEndianByteArray.byteArray.getLongLittleEndian(bytes, (offset + length) - 8);
            return hashLength16((Long.rotateRight(longLittleEndian, 37) * j) + jLoad64, (Long.rotateRight(jLoad64, 25) + longLittleEndian) * j, j);
        }
        if (length >= 4) {
            return hashLength16(((long) length) + ((((long) LittleEndianByteArray.load32(bytes, offset)) & 4294967295L) << 3), ((long) LittleEndianByteArray.load32(bytes, (offset + length) - 4)) & 4294967295L, ((long) (length * 2)) + K2);
        }
        if (length <= 0) {
            return K2;
        }
        return shiftMix((((long) ((bytes[offset] & 255) + ((bytes[(length >> 1) + offset] & 255) << 8))) * K2) ^ (((long) (length + ((bytes[(length - 1) + offset] & 255) << 2))) * K0)) * K2;
    }

    public static long hashLength16(long u, long v, long mul) {
        long j = (u ^ v) * mul;
        long j2 = ((j ^ (j >>> 47)) ^ v) * mul;
        return (j2 ^ (j2 >>> 47)) * mul;
    }

    public static long hashLength17to32(byte[] bytes, int offset, int length) {
        long j = (((long) length) * 2) + K2;
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset) * K1;
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        long longLittleEndian = littleEndianBytes.getLongLittleEndian(bytes, offset + 8);
        int i = offset + length;
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bytes, i - 8) * j;
        return hashLength16(Long.rotateRight(longLittleEndian2, 30) + Long.rotateRight(jLoad64 + longLittleEndian, 43) + (littleEndianBytes.getLongLittleEndian(bytes, i - 16) * K2), Long.rotateRight(longLittleEndian + K2, 18) + jLoad64 + longLittleEndian2, j);
    }

    public static long hashLength33To64(byte[] bytes, int offset, int length) {
        long j = (((long) length) * 2) + K2;
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset) * K2;
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        long longLittleEndian = littleEndianBytes.getLongLittleEndian(bytes, offset + 8);
        int i = offset + length;
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bytes, i - 8) * j;
        long jRotateRight = Long.rotateRight(longLittleEndian2, 30) + Long.rotateRight(jLoad64 + longLittleEndian, 43) + (littleEndianBytes.getLongLittleEndian(bytes, i - 16) * K2);
        long jHashLength16 = hashLength16(jRotateRight, longLittleEndian2 + Long.rotateRight(longLittleEndian + K2, 18) + jLoad64, j);
        long longLittleEndian3 = littleEndianBytes.getLongLittleEndian(bytes, offset + 16) * j;
        long longLittleEndian4 = littleEndianBytes.getLongLittleEndian(bytes, offset + 24);
        long longLittleEndian5 = (littleEndianBytes.getLongLittleEndian(bytes, i - 32) + jRotateRight) * j;
        return hashLength16(Long.rotateRight(longLittleEndian5, 30) + Long.rotateRight(longLittleEndian3 + longLittleEndian4, 43) + ((littleEndianBytes.getLongLittleEndian(bytes, i - 24) + jHashLength16) * j), Long.rotateRight(longLittleEndian4 + jLoad64, 18) + longLittleEndian3 + longLittleEndian5, j);
    }

    public static long hashLength65Plus(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long j = 81;
        long j2 = K1;
        long j3 = (j * K1) + 113;
        long jShiftMix = shiftMix((j3 * K2) + 113) * K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset) + (j * K2);
        char c = 1;
        int i = length - 1;
        int i2 = ((i / 64) * 64) + offset;
        int i3 = i & 63;
        int i4 = i2 + i3;
        int i5 = i4 - 63;
        int i6 = offset;
        while (true) {
            long j4 = jLoad64 + j3 + jArr[0];
            long j5 = j2;
            LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
            long jRotateRight = Long.rotateRight(littleEndianBytes.getLongLittleEndian(bArr, i6 + 8) + j4, 37) * j5;
            long jRotateRight2 = Long.rotateRight(littleEndianBytes.getLongLittleEndian(bArr, i6 + 48) + j3 + jArr[c], 42) * j5;
            long j6 = jRotateRight ^ jArr2[c];
            long longLittleEndian = littleEndianBytes.getLongLittleEndian(bArr, i6 + 40) + jArr[0] + jRotateRight2;
            long jRotateRight3 = Long.rotateRight(jShiftMix + jArr2[0], 33) * j5;
            int i7 = i3;
            int i8 = i6;
            weakHashLength32WithSeeds(bArr, i8, jArr[c] * j5, j6 + jArr2[0], jArr);
            long[] jArr3 = jArr;
            weakHashLength32WithSeeds(bArr, i8 + 32, jArr2[1] + jRotateRight3, littleEndianBytes.getLongLittleEndian(bArr, i8 + 16) + longLittleEndian, jArr2);
            i6 = i8 + 64;
            if (i6 == i2) {
                long j7 = ((255 & j6) << 1) + j5;
                long j8 = jArr2[0] + ((long) i7);
                jArr2[0] = j8;
                long j9 = jArr3[0] + j8;
                jArr3[0] = j9;
                jArr2[0] = jArr2[0] + j9;
                long jRotateRight4 = Long.rotateRight(littleEndianBytes.getLongLittleEndian(bArr, i4 - 55) + jRotateRight3 + longLittleEndian + jArr3[0], 37) * j7;
                long jRotateRight5 = Long.rotateRight(littleEndianBytes.getLongLittleEndian(bArr, i4 - 15) + longLittleEndian + jArr3[1], 42) * j7;
                long j10 = jRotateRight4 ^ (jArr2[1] * 9);
                long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bArr, i4 - 23) + (jArr3[0] * 9) + jRotateRight5;
                long jRotateRight6 = Long.rotateRight(j6 + jArr2[0], 33) * j7;
                weakHashLength32WithSeeds(bArr, i5, jArr3[1] * j7, jArr2[0] + j10, jArr3);
                weakHashLength32WithSeeds(bArr, i4 - 31, jArr2[1] + jRotateRight6, littleEndianBytes.getLongLittleEndian(bArr, i4 - 47) + longLittleEndian2, jArr2);
                return hashLength16((shiftMix(longLittleEndian2) * K0) + hashLength16(jArr3[0], jArr2[0], j7) + j10, hashLength16(jArr3[1], jArr2[1], j7) + jRotateRight6, j7);
            }
            jShiftMix = j6;
            jLoad64 = jRotateRight3;
            bArr = bytes;
            i3 = i7;
            j2 = j5;
            j3 = longLittleEndian;
            jArr = jArr3;
            c = 1;
        }
    }

    public static long shiftMix(long val) {
        return val ^ (val >>> 47);
    }

    public static void weakHashLength32WithSeeds(byte[] bytes, int offset, long seedA, long seedB, long[] output) {
        long jLoad64 = LittleEndianByteArray.load64(bytes, offset);
        LittleEndianByteArray.LittleEndianBytes littleEndianBytes = LittleEndianByteArray.byteArray;
        long longLittleEndian = littleEndianBytes.getLongLittleEndian(bytes, offset + 8);
        long longLittleEndian2 = littleEndianBytes.getLongLittleEndian(bytes, offset + 16);
        long longLittleEndian3 = littleEndianBytes.getLongLittleEndian(bytes, offset + 24);
        long j = seedA + jLoad64;
        long j2 = longLittleEndian + j + longLittleEndian2;
        long jRotateRight = Long.rotateRight(j2, 44) + Long.rotateRight(seedB + j + longLittleEndian3, 21);
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
        return "Hashing.farmHashFingerprint64()";
    }
}
