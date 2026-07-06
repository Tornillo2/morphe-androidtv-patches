package com.amazon.ion.impl;

import com.amazon.ion.IonList;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.lite.ValueFactoryLite;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SymbolTableStructCache {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final int firstLocalSid;
    public IonStruct image;
    public final SymbolTable[] importedTables;
    public final SymbolTable symbolTable;

    public SymbolTableStructCache(SymbolTable symbolTable, SymbolTable[] symbolTableArr, IonStruct ionStruct) {
        this.symbolTable = symbolTable;
        this.importedTables = symbolTableArr;
        this.firstLocalSid = symbolTable.getImportedMaxId() + 1;
        this.image = ionStruct;
    }

    public void addSymbol(String str, int i) {
        ValueFactory system = this.image.getSystem();
        IonValue ionValueNewEmptyList = this.image.get(SystemSymbols.SYMBOLS);
        while (ionValueNewEmptyList != null && ionValueNewEmptyList.getType() != IonType.LIST) {
            this.image.remove(ionValueNewEmptyList);
            ionValueNewEmptyList = this.image.get(SystemSymbols.SYMBOLS);
        }
        if (ionValueNewEmptyList == null) {
            ionValueNewEmptyList = ((ValueFactoryLite) system).newEmptyList();
            this.image.put(SystemSymbols.SYMBOLS, ionValueNewEmptyList);
        }
        ((IonList) ionValueNewEmptyList).add(i - this.firstLocalSid, (IonValue) ((ValueFactoryLite) system).newString(str));
    }

    public IonStruct getIonRepresentation(ValueFactory valueFactory) {
        IonStruct ionStruct;
        synchronized (this) {
            try {
                if (this.image == null) {
                    makeIonRepresentation(valueFactory);
                }
                ionStruct = this.image;
            } catch (Throwable th) {
                throw th;
            }
        }
        return ionStruct;
    }

    public boolean hasStruct() {
        return this.image != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean] */
    public final void makeIonRepresentation(ValueFactory valueFactory) {
        ?? IsSystemTable;
        IonStruct ionStructNewEmptyStruct = valueFactory.newEmptyStruct();
        this.image = ionStructNewEmptyStruct;
        ionStructNewEmptyStruct.addTypeAnnotation(SystemSymbols.ION_SYMBOL_TABLE);
        SymbolTable[] symbolTableArr = this.importedTables;
        if (symbolTableArr.length > 0 && (IsSystemTable = symbolTableArr[0].isSystemTable()) < this.importedTables.length) {
            IonList ionListNewEmptyList = valueFactory.newEmptyList();
            ?? r0 = IsSystemTable;
            while (true) {
                SymbolTable[] symbolTableArr2 = this.importedTables;
                if (r0 >= symbolTableArr2.length) {
                    break;
                }
                SymbolTable symbolTable = symbolTableArr2[r0];
                IonStruct ionStructNewEmptyStruct2 = valueFactory.newEmptyStruct();
                ionStructNewEmptyStruct2.add("name", valueFactory.newString(symbolTable.getName()));
                ionStructNewEmptyStruct2.add("version", valueFactory.newInt(symbolTable.getVersion()));
                ionStructNewEmptyStruct2.add(SystemSymbols.MAX_ID, valueFactory.newInt(symbolTable.getMaxId()));
                ionListNewEmptyList.add((IonValue) ionStructNewEmptyStruct2);
                r0++;
            }
            this.image.add(SystemSymbols.IMPORTS, ionListNewEmptyList);
        }
        if (this.symbolTable.getMaxId() <= this.symbolTable.getImportedMaxId()) {
            return;
        }
        Iterator<String> itIterateDeclaredSymbolNames = this.symbolTable.iterateDeclaredSymbolNames();
        int importedMaxId = this.symbolTable.getImportedMaxId();
        while (true) {
            importedMaxId++;
            if (!itIterateDeclaredSymbolNames.hasNext()) {
                return;
            } else {
                addSymbol(itIterateDeclaredSymbolNames.next(), importedMaxId);
            }
        }
    }
}
