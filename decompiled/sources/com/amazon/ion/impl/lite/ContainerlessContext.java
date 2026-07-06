package com.amazon.ion.impl.lite;

import com.amazon.ion.SymbolTable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ContainerlessContext implements IonContext {
    public final SymbolTable _symbols;
    public final IonSystemLite _system;

    public ContainerlessContext(IonSystemLite ionSystemLite, SymbolTable symbolTable) {
        this._system = ionSystemLite;
        this._symbols = symbolTable;
    }

    public static ContainerlessContext wrap(IonSystemLite ionSystemLite) {
        return new ContainerlessContext(ionSystemLite, null);
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonContainerLite getContextContainer() {
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public SymbolTable getContextSymbolTable() {
        return this._symbols;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonSystemLite getSystem() {
        return this._system;
    }

    public static ContainerlessContext wrap(IonSystemLite ionSystemLite, SymbolTable symbolTable) {
        return new ContainerlessContext(ionSystemLite, symbolTable);
    }
}
