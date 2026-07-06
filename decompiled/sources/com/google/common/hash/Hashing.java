package com.google.common.hash;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.errorprone.annotations.Immutable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Hashing {
    public static final int GOOD_FAST_HASH_SEED = (int) System.currentTimeMillis();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Immutable
    public static abstract class ChecksumType implements ImmutableSupplier<Checksum> {
        public final HashFunction hashFunction;
        public static final ChecksumType CRC_32 = new AnonymousClass1("CRC_32", 0, "Hashing.crc32()");
        public static final ChecksumType ADLER_32 = new AnonymousClass2("ADLER_32", 1, "Hashing.adler32()");
        public static final /* synthetic */ ChecksumType[] $VALUES = $values();

        /* JADX INFO: renamed from: com.google.common.hash.Hashing$ChecksumType$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum AnonymousClass1 extends ChecksumType {
            public AnonymousClass1(String $enum$name, int $enum$ordinal, String toString) {
                super($enum$name, $enum$ordinal, toString);
            }

            @Override // com.google.common.base.Supplier
            public Object get() {
                return new CRC32();
            }

            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new CRC32();
            }
        }

        /* JADX INFO: renamed from: com.google.common.hash.Hashing$ChecksumType$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum AnonymousClass2 extends ChecksumType {
            public AnonymousClass2(String $enum$name, int $enum$ordinal, String toString) {
                super($enum$name, $enum$ordinal, toString);
            }

            @Override // com.google.common.base.Supplier
            public Object get() {
                return new Adler32();
            }

            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new Adler32();
            }
        }

        public static /* synthetic */ ChecksumType[] $values() {
            return new ChecksumType[]{CRC_32, ADLER_32};
        }

        public static ChecksumType valueOf(String name) {
            return (ChecksumType) Enum.valueOf(ChecksumType.class, name);
        }

        public static ChecksumType[] values() {
            return (ChecksumType[]) $VALUES.clone();
        }

        public ChecksumType(String $enum$name, int $enum$ordinal, String toString) {
            this.hashFunction = new ChecksumHashFunction(this, 32, toString);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction {
        @Override // com.google.common.hash.HashFunction
        public int bits() {
            int iBits = 0;
            for (HashFunction hashFunction : this.functions) {
                iBits += hashFunction.bits();
            }
            return iBits;
        }

        public boolean equals(Object object) {
            if (object instanceof ConcatenatedHashFunction) {
                return Arrays.equals(this.functions, ((ConcatenatedHashFunction) object).functions);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.functions);
        }

        @Override // com.google.common.hash.AbstractCompositeHashFunction
        public HashCode makeHash(Hasher[] hashers) {
            byte[] bArr = new byte[bits() / 8];
            int iWriteBytesTo = 0;
            for (Hasher hasher : hashers) {
                HashCode hashCodeHash = hasher.hash();
                iWriteBytesTo += hashCodeHash.writeBytesTo(bArr, iWriteBytesTo, hashCodeHash.bits() / 8);
            }
            return HashCode.fromBytesNoCopy(bArr);
        }

        public ConcatenatedHashFunction(HashFunction... functions) {
            super(functions);
            for (HashFunction hashFunction : functions) {
                Preconditions.checkArgument(hashFunction.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", hashFunction.bits(), (Object) hashFunction);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LinearCongruentialGenerator {
        public long state;

        public LinearCongruentialGenerator(long seed) {
            this.state = seed;
        }

        public double nextDouble() {
            long j = (this.state * 2862933555777941757L) + 1;
            this.state = j;
            return ((double) (((int) (j >>> 33)) + 1)) / 2.147483648E9d;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Md5Holder {
        public static final HashFunction MD5 = new MessageDigestHashFunction("MD5", "Hashing.md5()");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sha1Holder {
        public static final HashFunction SHA_1 = new MessageDigestHashFunction("SHA-1", "Hashing.sha1()");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sha256Holder {
        public static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sha384Holder {
        public static final HashFunction SHA_384 = new MessageDigestHashFunction("SHA-384", "Hashing.sha384()");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sha512Holder {
        public static final HashFunction SHA_512 = new MessageDigestHashFunction("SHA-512", "Hashing.sha512()");
    }

    public static HashFunction adler32() {
        return ChecksumType.ADLER_32.hashFunction;
    }

    public static int checkPositiveAndMakeMultipleOf32(int bits) {
        Preconditions.checkArgument(bits > 0, "Number of bits must be positive");
        return (bits + 31) & (-32);
    }

    public static HashCode combineOrdered(Iterable<HashCode> hashCodes) {
        Iterator<HashCode> it = hashCodes.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int iBits = it.next().bits() / 8;
        byte[] bArr = new byte[iBits];
        Iterator<HashCode> it2 = hashCodes.iterator();
        while (it2.hasNext()) {
            byte[] bArrAsBytes = it2.next().asBytes();
            Preconditions.checkArgument(bArrAsBytes.length == iBits, "All hashcodes must have the same bit length.");
            for (int i = 0; i < bArrAsBytes.length; i++) {
                bArr[i] = (byte) ((bArr[i] * 37) ^ bArrAsBytes[i]);
            }
        }
        return new HashCode.BytesHashCode(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> hashCodes) {
        Iterator<HashCode> it = hashCodes.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int iBits = it.next().bits() / 8;
        byte[] bArr = new byte[iBits];
        Iterator<HashCode> it2 = hashCodes.iterator();
        while (it2.hasNext()) {
            byte[] bArrAsBytes = it2.next().asBytes();
            Preconditions.checkArgument(bArrAsBytes.length == iBits, "All hashcodes must have the same bit length.");
            for (int i = 0; i < bArrAsBytes.length; i++) {
                bArr[i] = (byte) (bArr[i] + bArrAsBytes[i]);
            }
        }
        return new HashCode.BytesHashCode(bArr);
    }

    public static HashFunction concatenating(HashFunction first, HashFunction second, HashFunction... rest) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(first);
        arrayList.add(second);
        Collections.addAll(arrayList, rest);
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static int consistentHash(HashCode hashCode, int buckets) {
        return consistentHash(hashCode.padToLong(), buckets);
    }

    public static HashFunction crc32() {
        return ChecksumType.CRC_32.hashFunction;
    }

    public static HashFunction crc32c() {
        return Crc32cHashFunction.CRC_32_C;
    }

    public static HashFunction farmHashFingerprint64() {
        return FarmHashFingerprint64.FARMHASH_FINGERPRINT_64;
    }

    public static HashFunction fingerprint2011() {
        return Fingerprint2011.FINGERPRINT_2011;
    }

    public static HashFunction goodFastHash(int minimumBits) {
        int iCheckPositiveAndMakeMultipleOf32 = checkPositiveAndMakeMultipleOf32(minimumBits);
        if (iCheckPositiveAndMakeMultipleOf32 == 32) {
            return Murmur3_32HashFunction.GOOD_FAST_HASH_32;
        }
        if (iCheckPositiveAndMakeMultipleOf32 <= 128) {
            return Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        }
        int i = (iCheckPositiveAndMakeMultipleOf32 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i];
        hashFunctionArr[0] = Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        int i2 = GOOD_FAST_HASH_SEED;
        for (int i3 = 1; i3 < i; i3++) {
            i2 += 1500450271;
            hashFunctionArr[i3] = new Murmur3_128HashFunction(i2);
        }
        return new ConcatenatedHashFunction(hashFunctionArr);
    }

    public static HashFunction hmacMd5(Key key) {
        return new MacHashFunction("HmacMD5", key, hmacToString("hmacMd5", key));
    }

    public static HashFunction hmacSha1(Key key) {
        return new MacHashFunction("HmacSHA1", key, hmacToString("hmacSha1", key));
    }

    public static HashFunction hmacSha256(Key key) {
        return new MacHashFunction("HmacSHA256", key, hmacToString("hmacSha256", key));
    }

    public static HashFunction hmacSha512(Key key) {
        return new MacHashFunction("HmacSHA512", key, hmacToString("hmacSha512", key));
    }

    public static String hmacToString(String methodName, Key key) {
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Hashing.", methodName, "(Key[algorithm=");
        sbM.append(key.getAlgorithm());
        sbM.append(", format=");
        sbM.append(key.getFormat());
        sbM.append("])");
        return sbM.toString();
    }

    @Deprecated
    public static HashFunction md5() {
        return Md5Holder.MD5;
    }

    public static HashFunction murmur3_128(int seed) {
        return new Murmur3_128HashFunction(seed);
    }

    @Deprecated
    public static HashFunction murmur3_32(int seed) {
        return new Murmur3_32HashFunction(seed, false);
    }

    public static HashFunction murmur3_32_fixed(int seed) {
        return new Murmur3_32HashFunction(seed, true);
    }

    @Deprecated
    public static HashFunction sha1() {
        return Sha1Holder.SHA_1;
    }

    public static HashFunction sha256() {
        return Sha256Holder.SHA_256;
    }

    public static HashFunction sha384() {
        return Sha384Holder.SHA_384;
    }

    public static HashFunction sha512() {
        return Sha512Holder.SHA_512;
    }

    public static HashFunction sipHash24() {
        return SipHashFunction.SIP_HASH_24;
    }

    public static int consistentHash(long input, int buckets) {
        int i = 0;
        Preconditions.checkArgument(buckets > 0, "buckets must be positive: %s", buckets);
        LinearCongruentialGenerator linearCongruentialGenerator = new LinearCongruentialGenerator(input);
        while (true) {
            int iNextDouble = (int) (((double) (i + 1)) / linearCongruentialGenerator.nextDouble());
            if (iNextDouble < 0 || iNextDouble >= buckets) {
                break;
            }
            i = iNextDouble;
        }
        return i;
    }

    public static HashFunction hmacMd5(byte[] key) {
        key.getClass();
        return hmacMd5(new SecretKeySpec(key, "HmacMD5"));
    }

    public static HashFunction hmacSha1(byte[] key) {
        key.getClass();
        return hmacSha1(new SecretKeySpec(key, "HmacSHA1"));
    }

    public static HashFunction hmacSha256(byte[] key) {
        key.getClass();
        return hmacSha256(new SecretKeySpec(key, "HmacSHA256"));
    }

    public static HashFunction hmacSha512(byte[] key) {
        key.getClass();
        return hmacSha512(new SecretKeySpec(key, "HmacSHA512"));
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128HashFunction.MURMUR3_128;
    }

    @Deprecated
    public static HashFunction murmur3_32() {
        return Murmur3_32HashFunction.MURMUR3_32;
    }

    public static HashFunction murmur3_32_fixed() {
        return Murmur3_32HashFunction.MURMUR3_32_FIXED;
    }

    public static HashFunction sipHash24(long k0, long k1) {
        return new SipHashFunction(2, 4, k0, k1);
    }

    public static HashFunction concatenating(Iterable<HashFunction> hashFunctions) {
        hashFunctions.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<HashFunction> it = hashFunctions.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Preconditions.checkArgument(!arrayList.isEmpty(), "number of hash functions (%s) must be > 0", arrayList.size());
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }
}
