package com.amazon.ion.impl;

import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LocalSymbolTableImports {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final int[] myBaseSids;
    public final SymbolTable[] myImports;
    public final int myMaxId;

    public LocalSymbolTableImports(List<SymbolTable> list) {
        int size = list.size();
        SymbolTable[] symbolTableArr = (SymbolTable[]) list.toArray(new SymbolTable[size]);
        this.myImports = symbolTableArr;
        int[] iArr = new int[size];
        this.myBaseSids = iArr;
        this.myMaxId = prepBaseSids(iArr, symbolTableArr);
    }

    public static int prepBaseSids(int[] iArr, SymbolTable[] symbolTableArr) {
        SymbolTable symbolTable = symbolTableArr[0];
        iArr[0] = 0;
        int maxId = symbolTable.getMaxId();
        for (int i = 1; i < symbolTableArr.length; i++) {
            SymbolTable symbolTable2 = symbolTableArr[i];
            if (symbolTable2.isLocalTable() || symbolTable2.isSystemTable()) {
                throw new IllegalArgumentException("only non-system shared tables can be imported");
            }
            iArr[i] = maxId;
            maxId += symbolTableArr[i].getMaxId();
        }
        return maxId;
    }

    public boolean equalImports(LocalSymbolTableImports localSymbolTableImports) {
        return Arrays.equals(this.myImports, localSymbolTableImports.myImports);
    }

    public SymbolToken find(String str) {
        int i = 0;
        while (true) {
            SymbolTable[] symbolTableArr = this.myImports;
            if (i >= symbolTableArr.length) {
                return null;
            }
            SymbolToken symbolTokenFind = symbolTableArr[i].find(str);
            if (symbolTokenFind != null) {
                return new SymbolTokenImpl(symbolTokenFind.getText(), symbolTokenFind.getSid() + this.myBaseSids[i]);
            }
            i++;
        }
    }

    public String findKnownSymbol(int i) {
        SymbolTable[] symbolTableArr;
        int i2;
        if (i > this.myMaxId) {
            return null;
        }
        int i3 = 0;
        int i4 = 1;
        while (true) {
            symbolTableArr = this.myImports;
            if (i4 >= symbolTableArr.length || i <= (i2 = this.myBaseSids[i4])) {
                break;
            }
            i4++;
            i3 = i2;
        }
        return symbolTableArr[i4 - 1].findKnownSymbol(i - i3);
    }

    public int findSymbol(String str) {
        SymbolToken symbolTokenFind = find(str);
        if (symbolTokenFind == null) {
            return -1;
        }
        return ((SymbolTokenImpl) symbolTokenFind).mySid;
    }

    public SymbolTable[] getImportedTables() {
        SymbolTable[] symbolTableArr = this.myImports;
        int length = symbolTableArr.length - 1;
        SymbolTable[] symbolTableArr2 = new SymbolTable[length];
        if (length > 0) {
            System.arraycopy(symbolTableArr, 1, symbolTableArr2, 0, length);
        }
        return symbolTableArr2;
    }

    public SymbolTable[] getImportedTablesNoCopy() {
        return this.myImports;
    }

    public int getMaxId() {
        return this.myMaxId;
    }

    public SymbolTable getSystemSymbolTable() {
        return this.myImports[0];
    }

    public String toString() {
        return Arrays.toString(this.myImports);
    }

    public LocalSymbolTableImports(SymbolTable symbolTable, SymbolTable... symbolTableArr) {
        if (symbolTableArr != null && symbolTableArr.length > 0) {
            if (symbolTableArr[0].isSystemTable()) {
                this.myImports = (SymbolTable[]) symbolTableArr.clone();
            } else {
                SymbolTable[] symbolTableArr2 = new SymbolTable[symbolTableArr.length + 1];
                this.myImports = symbolTableArr2;
                symbolTableArr2[0] = symbolTable;
                System.arraycopy(symbolTableArr, 0, symbolTableArr2, 1, symbolTableArr.length);
            }
        } else {
            this.myImports = new SymbolTable[]{symbolTable};
        }
        SymbolTable[] symbolTableArr3 = this.myImports;
        int[] iArr = new int[symbolTableArr3.length];
        this.myBaseSids = iArr;
        this.myMaxId = prepBaseSids(iArr, symbolTableArr3);
    }
}
