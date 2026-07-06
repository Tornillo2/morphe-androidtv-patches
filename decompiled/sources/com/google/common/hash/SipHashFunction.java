package com.google.common.hash;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public final class SipHashFunction extends AbstractHashFunction implements Serializable {
    public static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    public static final long serialVersionUID = 0;
    public final int c;
    public final int d;
    public final long k0;
    public final long k1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SipHasher extends AbstractStreamingHasher {
        public static final int CHUNK_SIZE = 8;
        public long b;
        public final int c;
        public final int d;
        public long finalM;
        public long v0;
        public long v1;
        public long v2;
        public long v3;

        public SipHasher(int c, int d, long k0, long k1) {
            super(8, 8);
            this.b = 0L;
            this.finalM = 0L;
            this.c = c;
            this.d = d;
            this.v0 = 8317987319222330741L ^ k0;
            this.v1 = 7237128888997146477L ^ k1;
            this.v2 = 7816392313619706465L ^ k0;
            this.v3 = 8387220255154660723L ^ k1;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j = this.finalM ^ (this.b << 56);
            this.finalM = j;
            processM(j);
            this.v2 ^= 255;
            sipRound(this.d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer buffer) {
            this.b += 8;
            processM(buffer.getLong());
        }

        public final void processM(long m) {
            this.v3 ^= m;
            sipRound(this.c);
            this.v0 = m ^ this.v0;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer buffer) {
            this.b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        public final void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                long j = this.v0;
                long j2 = this.v1;
                this.v0 = j + j2;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j2, 13);
                long jRotateLeft = Long.rotateLeft(this.v3, 16);
                long j3 = this.v1;
                long j4 = this.v0;
                this.v1 = j3 ^ j4;
                this.v3 = jRotateLeft ^ this.v2;
                long jRotateLeft2 = Long.rotateLeft(j4, 32);
                long j5 = this.v2;
                long j6 = this.v1;
                this.v2 = j5 + j6;
                this.v0 = jRotateLeft2 + this.v3;
                this.v1 = Long.rotateLeft(j6, 17);
                long jRotateLeft3 = Long.rotateLeft(this.v3, 21);
                long j7 = this.v1;
                long j8 = this.v2;
                this.v1 = j7 ^ j8;
                this.v3 = jRotateLeft3 ^ this.v0;
                this.v2 = Long.rotateLeft(j8, 32);
            }
        }
    }

    public SipHashFunction(int c, int d, long k0, long k1) {
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", c);
        Preconditions.checkArgument(d > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", d);
        this.c = c;
        this.d = d;
        this.k0 = k0;
        this.k1 = k1;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public boolean equals(Object object) {
        if (object instanceof SipHashFunction) {
            SipHashFunction sipHashFunction = (SipHashFunction) object;
            if (this.c == sipHashFunction.c && this.d == sipHashFunction.d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((SipHashFunction.class.hashCode() ^ this.c) ^ this.d)) ^ this.k0) ^ this.k1);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.c, this.d, this.k0, this.k1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Hashing.sipHash");
        sb.append(this.c);
        sb.append("");
        sb.append(this.d);
        sb.append("(");
        sb.append(this.k0);
        sb.append(", ");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.k1, ")");
    }
}
