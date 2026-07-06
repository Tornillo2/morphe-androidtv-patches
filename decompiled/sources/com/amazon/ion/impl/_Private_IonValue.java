package com.amazon.ion.impl;

import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.io.PrintWriter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_IonValue extends IonValue {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SymbolTableProvider {
        SymbolTable getSymbolTable();
    }

    void dump(PrintWriter printWriter);

    int findTypeAnnotation(String str);

    SymbolTable getAssignedSymbolTable();

    int getElementId();

    SymbolToken getFieldNameSymbol(SymbolTableProvider symbolTableProvider);

    SymbolToken[] getTypeAnnotationSymbols(SymbolTableProvider symbolTableProvider);

    void setSymbolTable(SymbolTable symbolTable);

    String validate();
}
