package com.amazon.ion.impl;

import com.amazon.ion.SymbolTable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_SymtabExtendsCache {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public SymbolTable myReaderSymtab;
    public int myReaderSymtabMaxId;
    public boolean myResult;
    public SymbolTable myWriterSymtab;
    public int myWriterSymtabMaxId;

    public boolean symtabsCompat(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (this.myWriterSymtab == symbolTable && this.myReaderSymtab == symbolTable2 && this.myWriterSymtabMaxId == symbolTable.getMaxId() && this.myReaderSymtabMaxId == symbolTable2.getMaxId()) {
            return this.myResult;
        }
        this.myResult = _Private_Utils.symtabExtends(symbolTable, symbolTable2);
        this.myWriterSymtab = symbolTable;
        this.myReaderSymtab = symbolTable2;
        this.myWriterSymtabMaxId = symbolTable.getMaxId();
        this.myReaderSymtabMaxId = symbolTable2.getMaxId();
        return this.myResult;
    }
}
