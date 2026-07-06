package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public interface Hasher extends PrimitiveSink {

    /* JADX INFO: renamed from: com.google.common.hash.Hasher$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    HashCode hash();

    @Deprecated
    int hashCode();

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putBoolean(boolean b);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putBoolean(boolean b);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putByte(byte b);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putByte(byte b);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putBytes(ByteBuffer bytes);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putBytes(byte[] bytes);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putBytes(byte[] bytes, int off, int len);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putBytes(ByteBuffer bytes);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putBytes(byte[] bytes);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putBytes(byte[] bytes, int off, int len);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putChar(char c);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putChar(char c);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putDouble(double d);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putDouble(double d);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putFloat(float f);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putFloat(float f);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putInt(int i);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putInt(int i);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putLong(long l);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putLong(long l);

    @CanIgnoreReturnValue
    <T> Hasher putObject(@ParametricNullness T instance, Funnel<? super T> funnel);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putShort(short s);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putShort(short s);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putString(CharSequence charSequence, Charset charset);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putString(CharSequence charSequence, Charset charset);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    Hasher putUnencodedChars(CharSequence charSequence);

    @Override // com.google.common.hash.PrimitiveSink
    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ PrimitiveSink putUnencodedChars(CharSequence charSequence);
}
