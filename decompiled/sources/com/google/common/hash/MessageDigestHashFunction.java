package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public final class MessageDigestHashFunction extends AbstractHashFunction implements Serializable {
    public final int bytes;
    public final MessageDigest prototype;
    public final boolean supportsClone;
    public final String toString;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MessageDigestHasher extends AbstractByteHasher {
        public final int bytes;
        public final MessageDigest digest;
        public boolean done;

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return this.bytes == this.digest.getDigestLength() ? HashCode.fromBytesNoCopy(this.digest.digest()) : HashCode.fromBytesNoCopy(Arrays.copyOf(this.digest.digest(), this.bytes));
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            checkNotDone();
            this.digest.update(b);
        }

        public MessageDigestHasher(MessageDigest digest, int bytes) {
            this.digest = digest;
            this.bytes = bytes;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] b, int off, int len) {
            checkNotDone();
            this.digest.update(b, off, len);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(ByteBuffer bytes) {
            checkNotDone();
            this.digest.update(bytes);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final String algorithmName;
        public final int bytes;
        public final String toString;

        public final Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }

        public SerializedForm(String algorithmName, int bytes, String toString) {
            this.algorithmName = algorithmName;
            this.bytes = bytes;
            this.toString = toString;
        }
    }

    public MessageDigestHashFunction(String algorithmName, int bytes, String toString) {
        toString.getClass();
        this.toString = toString;
        MessageDigest messageDigest = getMessageDigest(algorithmName);
        this.prototype = messageDigest;
        int digestLength = messageDigest.getDigestLength();
        Preconditions.checkArgument(bytes >= 4 && bytes <= digestLength, "bytes (%s) must be >= 4 and < %s", bytes, digestLength);
        this.bytes = bytes;
        this.supportsClone = supportsClone(messageDigest);
    }

    public static MessageDigest getMessageDigest(String algorithmName) {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static boolean supportsClone(MessageDigest digest) {
        try {
            digest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bytes * 8;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MessageDigestHasher((MessageDigest) this.prototype.clone(), this.bytes);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MessageDigestHasher(getMessageDigest(this.prototype.getAlgorithm()), this.bytes);
    }

    public final void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public String toString() {
        return this.toString;
    }

    public Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString);
    }

    public MessageDigestHashFunction(String algorithmName, String toString) {
        MessageDigest messageDigest = getMessageDigest(algorithmName);
        this.prototype = messageDigest;
        this.bytes = messageDigest.getDigestLength();
        toString.getClass();
        this.toString = toString;
        this.supportsClone = supportsClone(messageDigest);
    }
}
