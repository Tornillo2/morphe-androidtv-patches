package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class LocalSymbolTableAsStruct extends LocalSymbolTable implements SymbolTableAsStruct {
    public final SymbolTableStructCache structCache;

    @Override // com.amazon.ion.impl.SymbolTableAsStruct
    public IonStruct getIonRepresentation(ValueFactory valueFactory) {
        return this.structCache.getIonRepresentation(valueFactory);
    }

    @Override // com.amazon.ion.impl.LocalSymbolTable
    public int putSymbol(String str) {
        int iPutSymbol = super.putSymbol(str);
        if (this.structCache.hasStruct()) {
            this.structCache.addSymbol(str, iPutSymbol);
        }
        return iPutSymbol;
    }

    public LocalSymbolTableAsStruct(LocalSymbolTableImports localSymbolTableImports, List<String> list, IonStruct ionStruct) {
        super(localSymbolTableImports, list);
        this.structCache = new SymbolTableStructCache(this, localSymbolTableImports.myImports, ionStruct);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Factory implements _Private_LocalSymbolTableFactory {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public final ValueFactory imageFactory;

        public Factory(ValueFactory valueFactory) {
            this.imageFactory = valueFactory;
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z) {
            ArrayList arrayList = new ArrayList();
            SymbolTable symbolTable = ionReader.getSymbolTable();
            LocalSymbolTableImports localSymbolTable = LocalSymbolTable.readLocalSymbolTable(ionReader, ionCatalog, z, arrayList, symbolTable);
            return localSymbolTable == null ? symbolTable : new LocalSymbolTableAsStruct(localSymbolTable, arrayList, null);
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr) {
            return new LocalSymbolTableAsStruct(new LocalSymbolTableImports(symbolTable, symbolTableArr), null, null);
        }

        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonStruct ionStruct) {
            IonReaderTreeSystem ionReaderTreeSystem = new IonReaderTreeSystem(ionStruct);
            ArrayList arrayList = new ArrayList();
            return new LocalSymbolTableAsStruct(LocalSymbolTable.readLocalSymbolTable(ionReaderTreeSystem, ionCatalog, false, arrayList, ionStruct.getSymbolTable()), arrayList, ionStruct);
        }
    }
}
