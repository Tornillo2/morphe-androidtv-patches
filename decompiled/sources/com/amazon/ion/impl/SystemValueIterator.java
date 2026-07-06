package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl.IonBinary;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface SystemValueIterator extends Iterator<IonValue>, Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    boolean currentIsHidden();

    IonBinary.BufferManager getBuffer();

    IonCatalog getCatalog();

    SymbolTable getLocalSymbolTable();

    SymbolTable getSymbolTable();

    IonSystem getSystem();

    @Override // java.util.Iterator
    boolean hasNext();

    @Override // java.util.Iterator
    IonValue next();

    @Override // java.util.Iterator
    void remove();

    void resetBuffer();
}
