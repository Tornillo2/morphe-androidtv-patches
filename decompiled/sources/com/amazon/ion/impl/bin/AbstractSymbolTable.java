package com.amazon.ion.impl.bin;

import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSymbolTable implements SymbolTable {
    public final String name;
    public final int version;

    public AbstractSymbolTable(String str, int i) {
        this.name = str;
        this.version = i;
    }

    @Override // com.amazon.ion.SymbolTable
    public final int findSymbol(String str) {
        SymbolToken symbolTokenFind = find(str);
        if (symbolTokenFind == null) {
            return -1;
        }
        return symbolTokenFind.getSid();
    }

    @Override // com.amazon.ion.SymbolTable
    public final String getIonVersionId() {
        return SystemSymbols.ION_1_0;
    }

    @Override // com.amazon.ion.SymbolTable
    public final String getName() {
        return this.name;
    }

    @Override // com.amazon.ion.SymbolTable
    public final int getVersion() {
        return this.version;
    }

    @Override // com.amazon.ion.SymbolTable
    public final void writeTo(IonWriter ionWriter) throws IOException {
        if (isSharedTable()) {
            ionWriter.setTypeAnnotationSymbols(Symbols.systemSymbol(9));
        } else {
            if (!isLocalTable()) {
                throw new IllegalStateException("Invalid symbol table, neither shared nor local");
            }
            ionWriter.setTypeAnnotationSymbols(Symbols.systemSymbol(3));
        }
        ionWriter.stepIn(IonType.STRUCT);
        if (isSharedTable()) {
            ionWriter.setFieldNameSymbol(Symbols.systemSymbol(4));
            ionWriter.writeString(this.name);
            ionWriter.setFieldNameSymbol(Symbols.systemSymbol(5));
            ionWriter.writeInt(this.version);
        }
        SymbolTable[] importedTables = getImportedTables();
        if (importedTables != null && importedTables.length > 0) {
            ionWriter.setFieldNameSymbol(Symbols.systemSymbol(6));
            ionWriter.stepIn(IonType.LIST);
            for (SymbolTable symbolTable : importedTables) {
                ionWriter.stepIn(IonType.STRUCT);
                ionWriter.setFieldNameSymbol(Symbols.systemSymbol(4));
                ionWriter.writeString(symbolTable.getName());
                ionWriter.setFieldNameSymbol(Symbols.systemSymbol(5));
                ionWriter.writeInt(symbolTable.getVersion());
                ionWriter.setFieldNameSymbol(Symbols.systemSymbol(8));
                ionWriter.writeInt(symbolTable.getMaxId());
                ionWriter.stepOut();
            }
            ionWriter.stepOut();
        }
        ionWriter.setFieldNameSymbol(Symbols.systemSymbol(7));
        ionWriter.stepIn(IonType.LIST);
        Iterator<String> itIterateDeclaredSymbolNames = iterateDeclaredSymbolNames();
        while (itIterateDeclaredSymbolNames.hasNext()) {
            ionWriter.writeString(itIterateDeclaredSymbolNames.next());
        }
        ionWriter.stepOut();
        ionWriter.stepOut();
    }

    @Override // com.amazon.ion.SymbolTable
    public void makeReadOnly() {
    }
}
