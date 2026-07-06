package com.amazon.ion.impl.bin;

import com.amazon.ion.IonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface _Private_IonRawWriter extends IonWriter {
    void addTypeAnnotationSymbol(int i);

    void setFieldNameSymbol(int i);

    void setTypeAnnotationSymbols(int... iArr);

    void writeString(byte[] bArr, int i, int i2) throws IOException;

    void writeSymbolToken(int i) throws IOException;
}
