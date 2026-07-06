package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.SymbolTable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_LocalSymbolTableFactory {
    SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z);

    SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr);
}
