package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public final class MacHashFunction extends AbstractHashFunction {
    public final int bits;
    public final Key key;
    public final Mac prototype;
    public final boolean supportsClone;
    public final String toString;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MacHasher extends AbstractByteHasher {
        public boolean done;
        public final Mac mac;

        public final void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return HashCode.fromBytesNoCopy(this.mac.doFinal());
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            checkNotDone();
            this.mac.update(b);
        }

        public MacHasher(Mac mac) {
            this.mac = mac;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] b) {
            checkNotDone();
            this.mac.update(b);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] b, int off, int len) {
            checkNotDone();
            this.mac.update(b, off, len);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(ByteBuffer bytes) {
            checkNotDone();
            bytes.getClass();
            this.mac.update(bytes);
        }
    }

    public MacHashFunction(String algorithmName, Key key, String toString) {
        Mac mac = getMac(algorithmName, key);
        this.prototype = mac;
        key.getClass();
        this.key = key;
        toString.getClass();
        this.toString = toString;
        this.bits = mac.getMacLength() * 8;
        this.supportsClone = supportsClone(mac);
    }

    public static Mac getMac(String algorithmName, Key key) {
        try {
            Mac mac = Mac.getInstance(algorithmName);
            mac.init(key);
            return mac;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static boolean supportsClone(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MacHasher((Mac) this.prototype.clone());
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MacHasher(getMac(this.prototype.getAlgorithm(), this.key));
    }

    public String toString() {
        return this.toString;
    }
}
