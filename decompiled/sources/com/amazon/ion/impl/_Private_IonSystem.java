package com.amazon.ion.impl;

import com.amazon.ion.IonContainer;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_IonSystem extends IonSystem {
    boolean isStreamCopyOptimized();

    SymbolTable newSharedSymbolTable(IonStruct ionStruct);

    IonReader newSystemReader(IonValue ionValue);

    IonReader newSystemReader(InputStream inputStream);

    IonReader newSystemReader(Reader reader);

    IonReader newSystemReader(String str);

    IonReader newSystemReader(byte[] bArr);

    IonReader newSystemReader(byte[] bArr, int i, int i2);

    IonWriter newTreeSystemWriter(IonContainer ionContainer);

    IonWriter newTreeWriter(IonContainer ionContainer);

    Iterator<IonValue> systemIterate(IonReader ionReader);

    Iterator<IonValue> systemIterate(Reader reader);

    Iterator<IonValue> systemIterate(String str);

    boolean valueIsSharedSymbolTable(IonValue ionValue);
}
