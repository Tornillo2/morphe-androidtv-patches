package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
public interface HashFunction {
    int bits();

    HashCode hashBytes(ByteBuffer input);

    HashCode hashBytes(byte[] input);

    HashCode hashBytes(byte[] input, int off, int len);

    HashCode hashInt(int input);

    HashCode hashLong(long input);

    <T> HashCode hashObject(@ParametricNullness T instance, Funnel<? super T> funnel);

    HashCode hashString(CharSequence input, Charset charset);

    HashCode hashUnencodedChars(CharSequence input);

    Hasher newHasher();

    Hasher newHasher(int expectedInputSize);
}
