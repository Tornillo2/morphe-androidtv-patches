package com.amazon.ion.impl.bin;

import com.amazon.ion.IonWriter;
import com.amazon.ion.impl.bin.AbstractIonWriter;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class _PrivateIon_HashTrampoline {
    public static final PooledBlockAllocatorProvider ALLOCATOR_PROVIDER = PooledBlockAllocatorProvider.getInstance();

    public static IonWriter newIonWriter(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        return new IonRawBinaryWriter(ALLOCATOR_PROVIDER, 32768, byteArrayOutputStream, AbstractIonWriter.WriteValueOptimization.NONE, IonRawBinaryWriter.StreamCloseMode.CLOSE, IonRawBinaryWriter.StreamFlushMode.FLUSH, IonRawBinaryWriter.PreallocationMode.PREALLOCATE_0, false);
    }
}
