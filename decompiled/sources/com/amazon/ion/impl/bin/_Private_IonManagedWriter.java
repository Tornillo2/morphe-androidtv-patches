package com.amazon.ion.impl.bin;

import com.amazon.ion.IonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface _Private_IonManagedWriter extends IonWriter {
    _Private_IonRawWriter getRawWriter();

    void requireLocalSymbolTable() throws IOException;
}
