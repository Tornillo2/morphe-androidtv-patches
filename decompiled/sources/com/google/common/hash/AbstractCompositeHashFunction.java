package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    public static final long serialVersionUID = 0;
    public final HashFunction[] functions;

    /* JADX INFO: renamed from: com.google.common.hash.AbstractCompositeHashFunction$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Hasher {
        public final /* synthetic */ AbstractCompositeHashFunction this$0;
        public final /* synthetic */ Hasher[] val$hashers;

        public AnonymousClass1(final AbstractCompositeHashFunction this$0, final Hasher[] val$hashers) {
            this.val$hashers = val$hashers;
            this.this$0 = this$0;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return this.this$0.makeHash(this.val$hashers);
        }

        @Override // com.google.common.hash.Hasher
        public <T> Hasher putObject(@ParametricNullness T instance, Funnel<? super T> funnel) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putObject(instance, funnel);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBoolean(boolean b) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBoolean(b);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putByte(b);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putChar(c);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putDouble(double d) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putDouble(d);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putFloat(float f) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putFloat(f);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putInt(i);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putLong(long l) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putLong(l);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putShort(short s) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putShort(s);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence chars, Charset charset) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putString(chars, charset);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putUnencodedChars(CharSequence chars) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putUnencodedChars(chars);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bytes) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBytes(bytes);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bytes, int off, int len) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBytes(bytes, off, len);
            }
            return this;
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer bytes) {
            int iPosition = bytes.position();
            for (Hasher hasher : this.val$hashers) {
                bytes.position(iPosition);
                hasher.putBytes(bytes);
            }
            return this;
        }
    }

    public AbstractCompositeHashFunction(HashFunction... functions) {
        for (HashFunction hashFunction : functions) {
            hashFunction.getClass();
        }
        this.functions = functions;
    }

    public final Hasher fromHashers(Hasher[] hashers) {
        return new AnonymousClass1(this, hashers);
    }

    public abstract HashCode makeHash(Hasher[] hashers);

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i = 0; i < length; i++) {
            hasherArr[i] = this.functions[i].newHasher();
        }
        return new AnonymousClass1(this, hasherArr);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int expectedInputSize) {
        Preconditions.checkArgument(expectedInputSize >= 0);
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i = 0; i < length; i++) {
            hasherArr[i] = this.functions[i].newHasher(expectedInputSize);
        }
        return new AnonymousClass1(this, hasherArr);
    }
}
