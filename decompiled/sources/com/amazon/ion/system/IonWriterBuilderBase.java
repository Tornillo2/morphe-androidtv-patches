package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.system.IonWriterBuilderBase;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonWriterBuilderBase<T extends IonWriterBuilderBase> extends IonWriterBuilder {
    public IonCatalog myCatalog;
    public SymbolTable[] myImports;

    public IonWriterBuilderBase() {
    }

    public static SymbolTable[] safeCopy(SymbolTable[] symbolTableArr) {
        return (symbolTableArr == null || symbolTableArr.length == 0) ? symbolTableArr : (SymbolTable[]) symbolTableArr.clone();
    }

    public abstract T copy();

    public IonCatalog getCatalog() {
        return this.myCatalog;
    }

    public SymbolTable[] getImports() {
        return safeCopy(this.myImports);
    }

    public abstract T immutable();

    public abstract T mutable();

    public void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.myCatalog = ionCatalog;
    }

    public void setImports(SymbolTable... symbolTableArr) {
        mutationCheck();
        this.myImports = safeCopy(symbolTableArr);
    }

    public T withCatalog(IonCatalog ionCatalog) {
        T t = (T) mutable();
        t.setCatalog(ionCatalog);
        return t;
    }

    public T withImports(SymbolTable... symbolTableArr) {
        T t = (T) mutable();
        t.setImports(symbolTableArr);
        return t;
    }

    public IonWriterBuilderBase(IonWriterBuilderBase ionWriterBuilderBase) {
        this.myCatalog = ionWriterBuilderBase.myCatalog;
        this.myImports = ionWriterBuilderBase.myImports;
    }
}
