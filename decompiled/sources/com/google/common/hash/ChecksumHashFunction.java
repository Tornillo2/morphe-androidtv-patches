package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
    public static final long serialVersionUID = 0;
    public final int bits;
    public final ImmutableSupplier<? extends Checksum> checksumSupplier;
    public final String toString;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ChecksumHasher extends AbstractByteHasher {
        public final Checksum checksum;

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            long value = this.checksum.getValue();
            return ChecksumHashFunction.this.bits == 32 ? HashCode.fromInt((int) value) : HashCode.fromLong(value);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            this.checksum.update(b);
        }

        public ChecksumHasher(Checksum checksum) {
            checksum.getClass();
            this.checksum = checksum;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bytes, int off, int len) {
            this.checksum.update(bytes, off, len);
        }
    }

    public ChecksumHashFunction(ImmutableSupplier<? extends Checksum> checksumSupplier, int bits, String toString) {
        checksumSupplier.getClass();
        this.checksumSupplier = checksumSupplier;
        Preconditions.checkArgument(bits == 32 || bits == 64, "bits (%s) must be either 32 or 64", bits);
        this.bits = bits;
        toString.getClass();
        this.toString = toString;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new ChecksumHasher(this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }
}
