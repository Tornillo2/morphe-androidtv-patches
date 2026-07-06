package com.amazon.ion.impl;

import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SubstituteSymbolTable implements SymbolTable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final int myMaxId;
    public final String myName;
    public final SymbolTable myOriginalSymTab;
    public final int myVersion;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SymbolIterator implements Iterator<String> {
        public int myIndex = 0;
        public Iterator<String> myOriginalIterator;

        public SymbolIterator(Iterator<String> it) {
            this.myOriginalIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.myIndex < SubstituteSymbolTable.this.myMaxId;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public String next() {
            if (this.myIndex < SubstituteSymbolTable.this.myMaxId) {
                next = this.myOriginalIterator.hasNext() ? this.myOriginalIterator.next() : null;
                this.myIndex++;
            }
            return next;
        }
    }

    public SubstituteSymbolTable(String str, int i, int i2) {
        this.myOriginalSymTab = null;
        this.myName = str;
        this.myVersion = i;
        this.myMaxId = i2;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken find(String str) {
        SymbolTable symbolTable = this.myOriginalSymTab;
        if (symbolTable == null) {
            return null;
        }
        SymbolToken symbolTokenFind = symbolTable.find(str);
        if (symbolTokenFind == null || symbolTokenFind.getSid() <= this.myMaxId) {
            return symbolTokenFind;
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        SymbolTable symbolTable;
        if (i > this.myMaxId || (symbolTable = this.myOriginalSymTab) == null) {
            return null;
        }
        return symbolTable.findKnownSymbol(i);
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        int iFindSymbol;
        SymbolTable symbolTable = this.myOriginalSymTab;
        if (symbolTable == null || (iFindSymbol = symbolTable.findSymbol(str)) > this.myMaxId) {
            return -1;
        }
        return iFindSymbol;
    }

    @Override // com.amazon.ion.SymbolTable
    public int getImportedMaxId() {
        return 0;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable[] getImportedTables() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getIonVersionId() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public int getMaxId() {
        return this.myMaxId;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getName() {
        return this.myName;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public int getVersion() {
        return this.myVersion;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken intern(String str) {
        SymbolToken symbolTokenFind = find(str);
        if (symbolTokenFind != null) {
            return symbolTokenFind;
        }
        throw new ReadOnlyValueException(SymbolTable.class);
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isLocalTable() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isReadOnly() {
        return true;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSharedTable() {
        return true;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSubstitute() {
        return true;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSystemTable() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public Iterator<String> iterateDeclaredSymbolNames() {
        SymbolTable symbolTable = this.myOriginalSymTab;
        return new SymbolIterator(symbolTable != null ? symbolTable.iterateDeclaredSymbolNames() : Collections.EMPTY_LIST.iterator());
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }

    public SubstituteSymbolTable(SymbolTable symbolTable, int i, int i2) {
        this.myOriginalSymTab = symbolTable;
        this.myName = symbolTable.getName();
        this.myVersion = i;
        this.myMaxId = i2;
    }

    @Override // com.amazon.ion.SymbolTable
    public void makeReadOnly() {
    }
}
