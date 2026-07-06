package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SharedSymbolTable implements SymbolTable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final SymbolTable ION_1_0_SYSTEM_SYMTAB;
    public static final String[] SYSTEM_SYMBOLS = {SystemSymbols.ION, SystemSymbols.ION_1_0, SystemSymbols.ION_SYMBOL_TABLE, "name", "version", SystemSymbols.IMPORTS, SystemSymbols.SYMBOLS, SystemSymbols.MAX_ID, SystemSymbols.ION_SHARED_SYMBOL_TABLE};
    public final String myName;
    public final String[] mySymbolNames;
    public final Map<String, Integer> mySymbolsMap;
    public final int myVersion;

    static {
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            String[] strArr = SYSTEM_SYMBOLS;
            if (i >= strArr.length) {
                ION_1_0_SYSTEM_SYMTAB = new SharedSymbolTable(SystemSymbols.ION, 1, strArr, map);
                return;
            } else {
                String str = strArr[i];
                i++;
                map.put(str, Integer.valueOf(i));
            }
        }
    }

    public SharedSymbolTable(String str, int i, List<String> list, Map<String, Integer> map) {
        this.myName = str;
        this.myVersion = i;
        this.mySymbolsMap = map;
        this.mySymbolNames = (String[]) list.toArray(new String[list.size()]);
    }

    public static SymbolTable getSystemSymbolTable(int i) {
        if (i == 1) {
            return ION_1_0_SYSTEM_SYMTAB;
        }
        throw new IllegalArgumentException("only Ion 1.0 system symbols are supported");
    }

    public static SymbolTable newSharedSymbolTable(String str, int i, SymbolTable symbolTable, Iterator<String> it) {
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("name must be non-empty");
        }
        if (i < 1) {
            throw new IllegalArgumentException("version must be at least 1");
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        prepSymbolsListAndMap(symbolTable, it, arrayList, map);
        return new SharedSymbolTable(str, i, arrayList, map);
    }

    public static void prepSymbolsListAndMap(SymbolTable symbolTable, Iterator<String> it, List<String> list, Map<String, Integer> map) {
        int i = 1;
        if (symbolTable != null) {
            Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
            while (itIterateDeclaredSymbolNames.hasNext()) {
                String next = itIterateDeclaredSymbolNames.next();
                if (next != null) {
                    putToMapIfNotThere(map, next, i);
                }
                list.add(next);
                i++;
            }
        }
        while (it.hasNext()) {
            String next2 = it.next();
            if (map.get(next2) == null) {
                putToMapIfNotThere(map, next2, i);
                list.add(next2);
                i++;
            }
        }
    }

    private static void putToMapIfNotThere(Map<String, Integer> map, String str, int i) {
        Integer numPut = map.put(str, Integer.valueOf(i));
        if (numPut != null) {
            map.put(str, numPut);
        }
    }

    public static void transferNonExistingSymbols(List<String> list, Map<String, Integer> map) {
        int i = 1;
        for (String str : list) {
            if (str != null) {
                putToMapIfNotThere(map, str, i);
            }
            i++;
        }
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken find(String str) {
        str.getClass();
        Integer num = this.mySymbolsMap.get(str);
        if (num == null) {
            return null;
        }
        return new SymbolTokenImpl(this.mySymbolNames[num.intValue() - 1], num.intValue());
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs must be >= 0");
        }
        int i2 = i - 1;
        if (i == 0) {
            return null;
        }
        String[] strArr = this.mySymbolNames;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        Integer num = this.mySymbolsMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
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
        if (!isSystemTable()) {
            return null;
        }
        int i = this.myVersion;
        if (i == 1) {
            return SystemSymbols.ION_1_0;
        }
        throw new IonException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("unrecognized system version encountered: ", i));
    }

    @Override // com.amazon.ion.SymbolTable
    public int getMaxId() {
        return this.mySymbolNames.length;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getName() {
        return this.myName;
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
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSystemTable() {
        return SystemSymbols.ION.equals(this.myName);
    }

    @Override // com.amazon.ion.SymbolTable
    public Iterator<String> iterateDeclaredSymbolNames() {
        return DesugarCollections.unmodifiableList(Arrays.asList(this.mySymbolNames)).iterator();
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
        if (isSystemTable()) {
            return this;
        }
        return null;
    }

    public SharedSymbolTable(String str, int i, String[] strArr, Map<String, Integer> map) {
        this.myName = str;
        this.myVersion = i;
        this.mySymbolsMap = map;
        this.mySymbolNames = strArr;
    }

    public static SymbolTable newSharedSymbolTable(IonStruct ionStruct) {
        return newSharedSymbolTable(new IonReaderTreeSystem(ionStruct), false);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.ion.SymbolTable newSharedSymbolTable(com.amazon.ion.IonReader r7, boolean r8) {
        /*
            if (r8 != 0) goto L24
            com.amazon.ion.IonType r8 = r7.next()
            com.amazon.ion.IonType r0 = com.amazon.ion.IonType.STRUCT
            if (r8 != r0) goto Lb
            goto L24
        Lb:
            com.amazon.ion.IonException r7 = new com.amazon.ion.IonException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "invalid symbol table image passed into reader, "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r8 = " encountered when a struct was expected"
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        L24:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7.stepIn()
            r0 = -1
            r1 = 0
            r2 = r1
            r3 = -1
        L30:
            com.amazon.ion.IonType r4 = r7.next()
            if (r4 == 0) goto L95
            boolean r5 = r7.isNullValue()
            if (r5 == 0) goto L3d
            goto L30
        L3d:
            com.amazon.ion.SymbolToken r5 = r7.getFieldNameSymbol()
            int r5 = r5.getSid()
            if (r5 != r0) goto L4f
            java.lang.String r5 = r7.getFieldName()
            int r5 = com.amazon.ion.impl._Private_Utils.getSidForSymbolTableField(r5)
        L4f:
            r6 = 4
            if (r5 == r6) goto L8c
            r6 = 5
            if (r5 == r6) goto L83
            r6 = 7
            if (r5 == r6) goto L59
            goto L30
        L59:
            com.amazon.ion.IonType r5 = com.amazon.ion.IonType.LIST
            if (r4 != r5) goto L30
            r7.stepIn()
        L60:
            com.amazon.ion.IonType r4 = r7.next()
            if (r4 == 0) goto L7f
            com.amazon.ion.IonType r5 = com.amazon.ion.IonType.STRING
            if (r4 != r5) goto L7a
            boolean r4 = r7.isNullValue()
            if (r4 != 0) goto L7a
            java.lang.String r4 = r7.stringValue()
            int r5 = r4.length()
            if (r5 != 0) goto L7b
        L7a:
            r4 = r1
        L7b:
            r8.add(r4)
            goto L60
        L7f:
            r7.stepOut()
            goto L30
        L83:
            com.amazon.ion.IonType r5 = com.amazon.ion.IonType.INT
            if (r4 != r5) goto L30
            int r3 = r7.intValue()
            goto L30
        L8c:
            com.amazon.ion.IonType r5 = com.amazon.ion.IonType.STRING
            if (r4 != r5) goto L30
            java.lang.String r2 = r7.stringValue()
            goto L30
        L95:
            r7.stepOut()
            if (r2 == 0) goto Lbb
            int r7 = r2.length()
            if (r7 == 0) goto Lbb
            r7 = 1
            if (r3 >= r7) goto La4
            r3 = 1
        La4:
            boolean r7 = r8.isEmpty()
            if (r7 != 0) goto Lb3
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            transferNonExistingSymbols(r8, r7)
            goto Lb5
        Lb3:
            java.util.Map r7 = java.util.Collections.EMPTY_MAP
        Lb5:
            com.amazon.ion.impl.SharedSymbolTable r0 = new com.amazon.ion.impl.SharedSymbolTable
            r0.<init>(r2, r3, r8, r7)
            return r0
        Lbb:
            com.amazon.ion.IonException r7 = new com.amazon.ion.IonException
            java.lang.String r8 = "shared symbol table is malformed: field 'name' must be a non-empty string."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.SharedSymbolTable.newSharedSymbolTable(com.amazon.ion.IonReader, boolean):com.amazon.ion.SymbolTable");
    }

    @Override // com.amazon.ion.SymbolTable
    public void makeReadOnly() {
    }
}
