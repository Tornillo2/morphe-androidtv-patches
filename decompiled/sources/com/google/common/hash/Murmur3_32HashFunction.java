package com.google.common.hash;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {
    public static final int C1 = -862048943;
    public static final int C2 = 461845907;
    public static final int CHUNK_SIZE = 4;
    public static final long serialVersionUID = 0;
    public final int seed;
    public final boolean supplementaryPlaneFix;
    public static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0, false);
    public static final HashFunction MURMUR3_32_FIXED = new Murmur3_32HashFunction(0, true);
    public static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED, true);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Murmur3_32Hasher extends AbstractHasher {
        public long buffer;
        public int h1;
        public int shift;
        public int length = 0;
        public boolean isDone = false;

        public Murmur3_32Hasher(int seed) {
            this.h1 = seed;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int iMixK1 = this.h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.h1 = iMixK1;
            return Murmur3_32HashFunction.fmix(iMixK1, this.length);
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ PrimitiveSink putByte(byte b) {
            putByte(b);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ PrimitiveSink putBytes(ByteBuffer buffer) {
            putBytes(buffer);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putChar(char c) {
            update(2, c);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putInt(int i) {
            update(4, i);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ PrimitiveSink putLong(long l) {
            putLong(l);
            return this;
        }

        public final void update(int nBytes, long update) {
            long j = this.buffer;
            int i = this.shift;
            long j2 = ((update & 4294967295L) << i) | j;
            this.buffer = j2;
            int i2 = (nBytes * 8) + i;
            this.shift = i2;
            this.length += nBytes;
            if (i2 >= 32) {
                this.h1 = Murmur3_32HashFunction.mixH1(this.h1, Murmur3_32HashFunction.mixK1((int) j2));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putByte(byte b) {
            update(1, b & 255);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ PrimitiveSink putBytes(byte[] bytes, int off, int len) {
            putBytes(bytes, off, len);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public PrimitiveSink putChar(char c) {
            update(2, c);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public PrimitiveSink putInt(int i) {
            update(4, i);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putLong(long l) {
            update(4, (int) l);
            update(4, l >>> 32);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putString(CharSequence input, Charset charset) {
            if (!charset.equals(StandardCharsets.UTF_8)) {
                return super.putString(input, charset);
            }
            int length = input.length();
            int i = 0;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                char cCharAt = input.charAt(i);
                char cCharAt2 = input.charAt(i + 1);
                char cCharAt3 = input.charAt(i + 2);
                char cCharAt4 = input.charAt(i + 3);
                if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                    break;
                }
                update(4, (cCharAt2 << '\b') | cCharAt | (cCharAt3 << 16) | (cCharAt4 << 24));
                i = i2;
            }
            while (i < length) {
                char cCharAt5 = input.charAt(i);
                if (cCharAt5 < 128) {
                    update(1, cCharAt5);
                } else if (cCharAt5 < 2048) {
                    update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(cCharAt5));
                } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(cCharAt5));
                } else {
                    int iCodePointAt = Character.codePointAt(input, i);
                    if (iCodePointAt == cCharAt5) {
                        putBytes(input.subSequence(i, length).toString().getBytes(charset));
                        return this;
                    }
                    i++;
                    update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(iCodePointAt));
                }
                i++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putBytes(byte[] bytes, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i = 0;
            while (true) {
                int i2 = i + 4;
                if (i2 > len) {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(bytes, i + off));
                i = i2;
            }
            while (i < len) {
                putByte(bytes[off + i]);
                i++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        @CanIgnoreReturnValue
        public Hasher putBytes(ByteBuffer buffer) {
            ByteOrder byteOrderOrder = buffer.order();
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            while (buffer.remaining() >= 4) {
                putInt(buffer.getInt());
            }
            while (buffer.hasRemaining()) {
                putByte(buffer.get());
            }
            buffer.order(byteOrderOrder);
            return this;
        }
    }

    public Murmur3_32HashFunction(int seed, boolean supplementaryPlaneFix) {
        this.seed = seed;
        this.supplementaryPlaneFix = supplementaryPlaneFix;
    }

    public static long charToThreeUtf8Bytes(char c) {
        return ((long) (c >>> '\f')) | 224 | ((long) ((((c >>> 6) & 63) | 128) << 8)) | ((long) (((c & '?') | 128) << 16));
    }

    public static long charToTwoUtf8Bytes(char c) {
        return ((long) (c >>> 6)) | 192 | ((long) (((c & '?') | 128) << 8));
    }

    public static long codePointToFourUtf8Bytes(int codePoint) {
        return ((long) (codePoint >>> 18)) | 240 | ((((long) ((codePoint >>> 12) & 63)) | 128) << 8) | ((((long) ((codePoint >>> 6) & 63)) | 128) << 16) | ((((long) (codePoint & 63)) | 128) << 24);
    }

    public static HashCode fmix(int h1, int length) {
        int i = h1 ^ length;
        int i2 = (i ^ (i >>> 16)) * (-2048144789);
        int i3 = (i2 ^ (i2 >>> 13)) * (-1028477387);
        return HashCode.fromInt(i3 ^ (i3 >>> 16));
    }

    public static int getIntLittleEndian(byte[] input, int offset) {
        return Ints.fromBytes(input[offset + 3], input[offset + 2], input[offset + 1], input[offset]);
    }

    public static int mixH1(int h1, int k1) {
        return (Integer.rotateLeft(h1 ^ k1, 13) * 5) - 430675100;
    }

    public static int mixK1(int k1) {
        return Integer.rotateLeft(k1 * (-862048943), 15) * 461845907;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    public boolean equals(Object object) {
        if (object instanceof Murmur3_32HashFunction) {
            Murmur3_32HashFunction murmur3_32HashFunction = (Murmur3_32HashFunction) object;
            if (this.seed == murmur3_32HashFunction.seed && this.supplementaryPlaneFix == murmur3_32HashFunction.supplementaryPlaneFix) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        int iMixH1 = this.seed;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 4;
            if (i3 > len) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1(getIntLittleEndian(input, i2 + off)));
            i2 = i3;
        }
        int i4 = i2;
        int i5 = 0;
        while (i4 < len) {
            i ^= (input[off + i4] & 255) << i5;
            i4++;
            i5 += 8;
        }
        return fmix(mixK1(i) ^ iMixH1, len);
    }

    public int hashCode() {
        return Murmur3_32HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int input) {
        return fmix(mixH1(this.seed, mixK1(input)), 4);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long input) {
        int i = (int) (input >>> 32);
        return fmix(mixH1(mixH1(this.seed, mixK1((int) input)), mixK1(i)), 8);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence input, Charset charset) {
        if (!charset.equals(StandardCharsets.UTF_8)) {
            byte[] bytes = input.toString().getBytes(charset);
            return hashBytes(bytes, 0, bytes.length);
        }
        int length = input.length();
        int iMixH1 = this.seed;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 4;
            if (i3 > length) {
                break;
            }
            char cCharAt = input.charAt(i);
            char cCharAt2 = input.charAt(i + 1);
            char cCharAt3 = input.charAt(i + 2);
            char cCharAt4 = input.charAt(i + 3);
            if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1((cCharAt2 << '\b') | cCharAt | (cCharAt3 << 16) | (cCharAt4 << 24)));
            i2 += 4;
            i = i3;
        }
        long jCharToThreeUtf8Bytes = 0;
        int i4 = 0;
        while (i < length) {
            char cCharAt5 = input.charAt(i);
            if (cCharAt5 < 128) {
                jCharToThreeUtf8Bytes |= ((long) cCharAt5) << i4;
                i4 += 8;
                i2++;
            } else if (cCharAt5 < 2048) {
                jCharToThreeUtf8Bytes |= charToTwoUtf8Bytes(cCharAt5) << i4;
                i4 += 16;
                i2 += 2;
            } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                jCharToThreeUtf8Bytes |= charToThreeUtf8Bytes(cCharAt5) << i4;
                i4 += 24;
                i2 += 3;
            } else {
                int iCodePointAt = Character.codePointAt(input, i);
                if (iCodePointAt == cCharAt5) {
                    byte[] bytes2 = input.toString().getBytes(charset);
                    return hashBytes(bytes2, 0, bytes2.length);
                }
                i++;
                jCharToThreeUtf8Bytes |= codePointToFourUtf8Bytes(iCodePointAt) << i4;
                if (this.supplementaryPlaneFix) {
                    i4 += 32;
                }
                i2 += 4;
            }
            if (i4 >= 32) {
                iMixH1 = mixH1(iMixH1, mixK1((int) jCharToThreeUtf8Bytes));
                jCharToThreeUtf8Bytes >>>= 32;
                i4 -= 32;
            }
            i++;
        }
        return fmix(mixK1((int) jCharToThreeUtf8Bytes) ^ iMixH1, i2);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence input) {
        int iMixK1 = this.seed;
        for (int i = 1; i < input.length(); i += 2) {
            iMixK1 = mixH1(iMixK1, mixK1(input.charAt(i - 1) | (input.charAt(i) << 16)));
        }
        if ((input.length() & 1) == 1) {
            iMixK1 ^= mixK1(input.charAt(input.length() - 1));
        }
        return fmix(iMixK1, input.length() * 2);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(new StringBuilder("Hashing.murmur3_32("), this.seed, ")");
    }
}
