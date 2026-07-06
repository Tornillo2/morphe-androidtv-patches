package com.amazon.ion.impl.lite;

import com.amazon.ion.SymbolTable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TopLevelContext implements IonContext {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final IonDatagramLite _datagram;
    public final SymbolTable _symbols;

    public TopLevelContext(SymbolTable symbolTable, IonDatagramLite ionDatagramLite) {
        this._symbols = symbolTable;
        this._datagram = ionDatagramLite;
    }

    public static TopLevelContext wrap(SymbolTable symbolTable, IonDatagramLite ionDatagramLite) {
        return new TopLevelContext(symbolTable, ionDatagramLite);
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonContainerLite getContextContainer() {
        return this._datagram;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public SymbolTable getContextSymbolTable() {
        return this._symbols;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonSystemLite getSystem() {
        return this._datagram._system;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonDatagramLite getContextContainer() {
        return this._datagram;
    }
}
