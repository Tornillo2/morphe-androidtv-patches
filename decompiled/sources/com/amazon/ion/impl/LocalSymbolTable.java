package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.util.IonTextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class LocalSymbolTable implements SymbolTable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_CAPACITY = 16;
    public static final Factory DEFAULT_LST_FACTORY = new Factory();
    public boolean isReadOnly;
    public final int myFirstLocalSid;
    public final LocalSymbolTableImports myImportsList;
    public String[] mySymbolNames;
    public int mySymbolsCount;
    public final Map<String, Integer> mySymbolsMap;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Factory implements _Private_LocalSymbolTableFactory {
        public Factory() {
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z) {
            ArrayList arrayList = new ArrayList();
            SymbolTable symbolTable = ionReader.getSymbolTable();
            LocalSymbolTableImports localSymbolTable = LocalSymbolTable.readLocalSymbolTable(ionReader, ionCatalog, z, arrayList, symbolTable);
            return localSymbolTable == null ? symbolTable : new LocalSymbolTable(localSymbolTable, arrayList);
        }

        public Factory(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr) {
            return new LocalSymbolTable(new LocalSymbolTableImports(symbolTable, symbolTableArr), (List<String>) null);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SymbolIterator implements Iterator<String> {
        public int _idx = 0;
        public final String[] mySymbolNames;
        public final int mySymbolsCount;

        public SymbolIterator(String[] strArr, int i) {
            this.mySymbolNames = strArr;
            this.mySymbolsCount = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._idx < this.mySymbolsCount;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public String next() {
            int i = this._idx;
            if (i >= this.mySymbolsCount) {
                throw new NoSuchElementException();
            }
            String[] strArr = this.mySymbolNames;
            this._idx = i + 1;
            return strArr[i];
        }
    }

    public LocalSymbolTable(LocalSymbolTableImports localSymbolTableImports, List<String> list) {
        if (list == null || list.isEmpty()) {
            this.mySymbolsCount = 0;
            this.mySymbolNames = _Private_Utils.EMPTY_STRING_ARRAY;
        } else {
            int size = list.size();
            this.mySymbolsCount = size;
            this.mySymbolNames = (String[]) list.toArray(new String[size]);
        }
        this.myImportsList = localSymbolTableImports;
        this.myFirstLocalSid = localSymbolTableImports.myMaxId + 1;
        this.mySymbolsMap = new HashMap((int) Math.ceil(((double) this.mySymbolsCount) / 0.75d));
        buildSymbolsMap();
    }

    public static void prepImportsList(List<SymbolTable> list, IonReader ionReader, IonCatalog ionCatalog) {
        SymbolTable oneImport;
        ionReader.stepIn();
        while (true) {
            IonType next = ionReader.next();
            if (next == null) {
                ionReader.stepOut();
                return;
            } else if (!ionReader.isNullValue() && next == IonType.STRUCT && (oneImport = readOneImport(ionReader, ionCatalog)) != null) {
                list.add(oneImport);
            }
        }
    }

    public static void putToMapIfNotThere(Map<String, Integer> map, String str, int i) {
        Integer numPut = map.put(str, Integer.valueOf(i));
        if (numPut != null) {
            map.put(str, numPut);
        }
    }

    public static LocalSymbolTableImports readLocalSymbolTable(IonReader ionReader, IonCatalog ionCatalog, boolean z, List<String> list, SymbolTable symbolTable) {
        if (!z) {
            ionReader.next();
        }
        ionReader.stepIn();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ionReader.getSymbolTable().getSystemSymbolTable());
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            IonType next = ionReader.next();
            if (next == null) {
                ionReader.stepOut();
                if (!z2 || !symbolTable.isLocalTable()) {
                    return new LocalSymbolTableImports(arrayList);
                }
                LocalSymbolTable localSymbolTable = (LocalSymbolTable) symbolTable;
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    localSymbolTable.putSymbol(it.next());
                }
                return null;
            }
            if (!ionReader.isNullValue()) {
                int sid = ionReader.getFieldNameSymbol().getSid();
                if (sid == -1) {
                    sid = _Private_Utils.getSidForSymbolTableField(ionReader.getFieldName());
                }
                if (sid != 6) {
                    if (sid != 7) {
                        continue;
                    } else {
                        if (z4) {
                            throw new IonException("Multiple symbol fields found within a single local symbol table.");
                        }
                        if (next == IonType.LIST) {
                            ionReader.stepIn();
                            while (true) {
                                IonType next2 = ionReader.next();
                                if (next2 == null) {
                                    break;
                                }
                                list.add(next2 == IonType.STRING ? ionReader.stringValue() : null);
                            }
                            ionReader.stepOut();
                        }
                        z4 = true;
                    }
                } else {
                    if (z3) {
                        throw new IonException("Multiple imports fields found within a single local symbol table.");
                    }
                    if (next == IonType.LIST) {
                        prepImportsList(arrayList, ionReader, ionCatalog);
                    } else if (next == IonType.SYMBOL && SystemSymbols.ION_SYMBOL_TABLE.equals(ionReader.stringValue())) {
                        z2 = true;
                    }
                    z3 = true;
                }
            }
        }
    }

    public static SymbolTable readOneImport(IonReader ionReader, IonCatalog ionCatalog) {
        ionReader.stepIn();
        String strStringValue = null;
        int iIntValue = -1;
        int maxId = -1;
        while (true) {
            IonType next = ionReader.next();
            if (next == null) {
                break;
            }
            if (!ionReader.isNullValue()) {
                int sid = ionReader.getFieldNameSymbol().getSid();
                if (sid == -1) {
                    sid = _Private_Utils.getSidForSymbolTableField(ionReader.getFieldName());
                }
                if (sid != 4) {
                    if (sid != 5) {
                        if (sid == 8 && next == IonType.INT) {
                            maxId = ionReader.intValue();
                        }
                    } else if (next == IonType.INT) {
                        iIntValue = ionReader.intValue();
                    }
                } else if (next == IonType.STRING) {
                    strStringValue = ionReader.stringValue();
                }
            }
        }
        ionReader.stepOut();
        if (strStringValue == null || strStringValue.length() == 0 || strStringValue.equals(SystemSymbols.ION)) {
            return null;
        }
        if (iIntValue < 1) {
            iIntValue = 1;
        }
        SymbolTable table = ionCatalog != null ? ionCatalog.getTable(strStringValue, iIntValue) : null;
        if (maxId < 0) {
            if (table == null || iIntValue != table.getVersion()) {
                String string = "Import of shared table " + IonTextUtils.printString(strStringValue) + " lacks a valid max_id field, but an exact match was not found in the catalog";
                if (table != null) {
                    StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(string, " (found version ");
                    sbM.append(table.getVersion());
                    sbM.append(")");
                    string = sbM.toString();
                }
                throw new IonException(string);
            }
            maxId = table.getMaxId();
        }
        return table == null ? new SubstituteSymbolTable(strStringValue, iIntValue, maxId) : (table.getVersion() == iIntValue && table.getMaxId() == maxId) ? table : new SubstituteSymbolTable(table, iIntValue, maxId);
    }

    public static String unknownSymbolName(int i) {
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("$", i);
    }

    public static final void validateSymbol(String str) {
        if (str == null) {
            throw new IllegalArgumentException("symbols must not be null");
        }
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= 55296 && cCharAt <= 57343) {
                if (cCharAt >= 56320) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("unpaired trailing surrogate in symbol name at position ", i));
                }
                i++;
                if (i == str.length()) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("unmatched leading surrogate in symbol name at position ", i));
                }
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 56320 || cCharAt2 > 57343) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("unmatched leading surrogate in symbol name at position ", i));
                }
            }
            i++;
        }
    }

    public final void buildSymbolsMap() {
        int i = this.myFirstLocalSid;
        int i2 = 0;
        while (true) {
            String[] strArr = this.mySymbolNames;
            if (i2 >= strArr.length) {
                return;
            }
            String str = strArr[i2];
            if (str != null) {
                putToMapIfNotThere(this.mySymbolsMap, str, i);
            }
            i2++;
            i++;
        }
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken find(String str) {
        Integer num;
        String[] strArr;
        str.getClass();
        SymbolToken symbolTokenFind = this.myImportsList.find(str);
        if (symbolTokenFind == null) {
            synchronized (this) {
                num = this.mySymbolsMap.get(str);
                strArr = this.mySymbolNames;
            }
            if (num != null) {
                return new SymbolTokenImpl(strArr[num.intValue() - this.myFirstLocalSid], num.intValue());
            }
        }
        return symbolTokenFind;
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        String[] strArr;
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs must be >= 0");
        }
        int i2 = this.myFirstLocalSid;
        if (i < i2) {
            return this.myImportsList.findKnownSymbol(i);
        }
        int i3 = i - i2;
        synchronized (this) {
            strArr = this.mySymbolNames;
        }
        if (i3 < strArr.length) {
            return strArr[i3];
        }
        return null;
    }

    public final int findLocalSymbol(String str) {
        Integer num;
        synchronized (this) {
            num = this.mySymbolsMap.get(str);
        }
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        int iFindSymbol = this.myImportsList.findSymbol(str);
        return iFindSymbol == -1 ? findLocalSymbol(str) : iFindSymbol;
    }

    @Override // com.amazon.ion.SymbolTable
    public int getImportedMaxId() {
        return this.myImportsList.myMaxId;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable[] getImportedTables() {
        return this.myImportsList.getImportedTables();
    }

    public SymbolTable[] getImportedTablesNoCopy() {
        return this.myImportsList.myImports;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getIonVersionId() {
        return this.myImportsList.myImports[0].getIonVersionId();
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized int getMaxId() {
        return this.mySymbolsCount + this.myImportsList.myMaxId;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getName() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
        return this.myImportsList.myImports[0];
    }

    @Override // com.amazon.ion.SymbolTable
    public int getVersion() {
        return 0;
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized SymbolToken intern(String str) {
        SymbolToken symbolTokenFind;
        symbolTokenFind = find(str);
        if (symbolTokenFind == null) {
            validateSymbol(str);
            symbolTokenFind = new SymbolTokenImpl(str, putSymbol(str));
        }
        return symbolTokenFind;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isLocalTable() {
        return true;
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSharedTable() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSubstitute() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSystemTable() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized Iterator<String> iterateDeclaredSymbolNames() {
        return new SymbolIterator(this.mySymbolNames, this.mySymbolsCount);
    }

    public synchronized LocalSymbolTable makeCopy() {
        return new LocalSymbolTable(this, getMaxId());
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized void makeReadOnly() {
        this.isReadOnly = true;
    }

    public int putSymbol(String str) {
        int i;
        if (this.isReadOnly) {
            throw new ReadOnlyValueException(SymbolTable.class);
        }
        int i2 = this.mySymbolsCount;
        String[] strArr = this.mySymbolNames;
        if (i2 == strArr.length) {
            int i3 = i2 * 2;
            if (i3 < 16) {
                i3 = 16;
            }
            String[] strArr2 = new String[i3];
            System.arraycopy(strArr, 0, strArr2, 0, i2);
            this.mySymbolNames = strArr2;
        }
        if (str != null) {
            i = this.mySymbolsCount + this.myFirstLocalSid;
            putToMapIfNotThere(this.mySymbolsMap, str, i);
        } else {
            i = -1;
        }
        String[] strArr3 = this.mySymbolNames;
        int i4 = this.mySymbolsCount;
        strArr3[i4] = str;
        this.mySymbolsCount = i4 + 1;
        return i;
    }

    public boolean symtabExtends(SymbolTable symbolTable) {
        LocalSymbolTable localSymbolTable = (LocalSymbolTable) symbolTable;
        if (getMaxId() < localSymbolTable.getMaxId() || !this.myImportsList.equalImports(localSymbolTable.myImportsList)) {
            return false;
        }
        int i = localSymbolTable.mySymbolsCount;
        if (i == 0) {
            return true;
        }
        if (this.mySymbolsCount < i) {
            return false;
        }
        String[] strArr = localSymbolTable.mySymbolNames;
        int i2 = i - 1;
        if (!_Private_Utils.safeEquals(this.mySymbolNames[i2], strArr[i2])) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!_Private_Utils.safeEquals(this.mySymbolNames[i3], strArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "(LocalSymbolTable max_id:" + getMaxId() + ')';
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }

    public synchronized LocalSymbolTable makeCopy(int i) {
        return new LocalSymbolTable(this, i);
    }

    public LocalSymbolTable(LocalSymbolTable localSymbolTable, int i) {
        this.isReadOnly = false;
        this.myFirstLocalSid = localSymbolTable.myFirstLocalSid;
        LocalSymbolTableImports localSymbolTableImports = localSymbolTable.myImportsList;
        this.myImportsList = localSymbolTableImports;
        int i2 = i - localSymbolTableImports.myMaxId;
        this.mySymbolsCount = i2;
        this.mySymbolNames = _Private_Utils.copyOf(localSymbolTable.mySymbolNames, i2);
        if (i == localSymbolTable.getMaxId()) {
            this.mySymbolsMap = new HashMap(localSymbolTable.mySymbolsMap);
        } else {
            this.mySymbolsMap = new HashMap(this.mySymbolsCount);
            buildSymbolsMap();
        }
    }
}
